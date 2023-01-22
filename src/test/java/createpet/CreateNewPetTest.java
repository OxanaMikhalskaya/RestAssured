package createpet;

import dto.pet.Category;
import dto.pet.NewPetDTO;
import dto.pet.TagsItem;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import services.NewPetApi;

import java.util.List;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;

public class CreateNewPetTest {

    /*
    “ест кейс
    ѕроверить, что пользователь может добавить в маганин новое животное,
    заполнив соответствующие пол€
     */

    @Test
    public void createNewPet() {
        NewPetApi newPetApi = new NewPetApi ( );

        Category category = Category.builder ( )
                .id (5)
                .name ("Fluffy")
                .build();

        TagsItem tagsItem = TagsItem.builder ( )
                .id (5)
                .name ("Fluffy")
                .build ( );

        NewPetDTO newPet = NewPetDTO.builder ( )
                .id (5)
                .name ("Fluffy")
                .category (category)
                .photoUrls (List.of ("wwww.picture.net", "www.photo.net"))
                .tags (List.of (tagsItem))
                .status ("available")
                .build ( );

// проверка с помощью ValidatableResponse и Hamcrest Matchers
        ValidatableResponse response = newPetApi.createPet (newPet);

        response
                .statusCode (200)
                .body(JsonSchemaValidator.matchesJsonSchema ("schema/NewPet.json"))
                .body ("id", equalTo (5))
                .body ("name", equalTo ("Fluffy"))
                .body ("status", equalTo ("available"));

        // ѕрооверка с помощью Soft Assertion
        NewPetDTO newPetDTO = response.extract ( ).body ( ).as (NewPetDTO.class);

        Assertions.assertAll ("check response",
                () -> Assertions.assertEquals (category, newPetDTO.getCategory ( )),
                () -> Assertions.assertEquals ("Fluffy", newPetDTO.getName ( )),
                () -> Assertions.assertEquals (5, newPetDTO.getId ( )),
                () -> Assertions.assertEquals (List.of (tagsItem), newPetDTO.getTags ( )),
                () -> Assertions.assertEquals ("available", newPetDTO.getStatus ( )),
                () -> Assertions.assertEquals (List.of ("wwww.picture.net", "www.photo.net"), newPetDTO.getPhotoUrls ( ))
        );
    }


     /*
        ѕроверка, что пользователь может добавить в магазин новое животное, если заполнит только им€
         */

    @Test
    public void createPet() {
        NewPetApi newPetApi = new NewPetApi();
        Category category1 = Category.builder ( )
                .name ("Fluffy1")
                .build();

        TagsItem tagsItem1 = TagsItem.builder ( )
                .name("Fluffy1")
                .build();


        NewPetDTO newPet1 = NewPetDTO.builder ( )
                .name ("Fluffy1")
                .category (category1)
                .tags (List.of (tagsItem1))
                .build ();


        // проверка с помощью ValidatableResponse и Hamcrest Matchers
        ValidatableResponse response = newPetApi.createPet (newPet1);

        response
                .statusCode (200)
                .body(JsonSchemaValidator.matchesJsonSchema ("schema/NewPet.json"))
                .body("name", equalTo("Fluffy1"));

    }
}