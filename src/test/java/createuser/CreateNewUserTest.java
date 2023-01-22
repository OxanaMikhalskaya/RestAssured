package createuser;


import dto.NewUserDTO;
import dto.UserOutDTO;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import services.NewUserApi;

import static org.hamcrest.Matchers.equalTo;

public class CreateNewUserTest {

    @Test
    public void createNewUser() {
        NewUserApi newUserApi = new NewUserApi();

        NewUserDTO user = NewUserDTO.builder()
                .id (1)
                .username("Savva")
                .firstName("Savva")
                .lastName("Savva")
                .password("pass")
                .userStatus(1)
                .phone("12345")
                .build();


        ValidatableResponse response = newUserApi.createUser(user);

        response
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema ("schema/NewUser.json"))
                .body("code", equalTo(200))
                .body("type", equalTo("unknown"))
                .body("message", equalTo("1"));

               UserOutDTO userOutDTO =  response.extract ().body().as (UserOutDTO.class);

        Assertions.assertAll ("check response",
                ()-> Assertions.assertEquals (200, userOutDTO.getCode()),
                ()-> Assertions.assertEquals ("unknown", userOutDTO.getType()),
                ()-> Assertions.assertEquals ("1", userOutDTO.getMessage())
        );
    }
}