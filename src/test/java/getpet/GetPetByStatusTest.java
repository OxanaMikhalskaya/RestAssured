package getpet;

import dto.pet.StatusDTO;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import services.GetPetByStatusApi;


public class GetPetByStatusTest {

    /*
    проверка, что мы можем получить список животных по статусу
     */
    @Test
            public void setGetPetByStatus () {
    String[] status = {"available"};

    GetPetByStatusApi getPetByStatusApi = new GetPetByStatusApi ( );
    StatusDTO statusDTO = new StatusDTO ( ).builder ( )
            .status (new String[]{"available", "pending", "sold"})
            .build ( );

    ValidatableResponse response = getPetByStatusApi.getPetByStatus (statusDTO);
        response
                .statusCode (200);

  }
}