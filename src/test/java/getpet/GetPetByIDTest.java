package getpet;

import dto.UserOutDTO;
import dto.pet.NewPetOutDTO;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import services.GetPetByIDApi;

public class GetPetByIDTest {
/*
проверка, что мы можем получить животное по ID
 */
    @Test
    public void getPetByID() {
    GetPetByIDApi getPetByIDApi = new GetPetByIDApi();

    ValidatableResponse validatableResponse = getPetByIDApi.getPetByID();

    validatableResponse
            .statusCode (200);

        NewPetOutDTO newPetOutDTO =  validatableResponse.extract ().as (NewPetOutDTO.class);

        Assertions.assertAll ("check response",
                ()-> Assertions.assertEquals (5, newPetOutDTO.getId()),
                ()-> Assertions.assertEquals ("doggie", newPetOutDTO.getName())
        );
   }
}