package services;

import dto.NewUserDTO;
import dto.pet.NewPetDTO;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class NewPetApi {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private static final String BASE_PATH = "/pet";
    private RequestSpecification specification;

    public NewPetApi() {
       specification = given()
                          .baseUri (BASE_URL)
                          .contentType(ContentType.JSON);
    }

    public ValidatableResponse createPet(NewPetDTO pet) {
        return given(specification)
                      .basePath(BASE_PATH)
                      .body(pet)
                      .log().all()
                .when()
                      .post()
                .then()
                       .log().all();
    }
}