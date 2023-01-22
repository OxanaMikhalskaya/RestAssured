package services;

import dto.pet.StatusDTO;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetPetByStatusApi {
    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private static final String BASE_PATH = "/pet/findByStatus";
    private RequestSpecification specification;

    public GetPetByStatusApi() {
        specification =  given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON);
    }

    public ValidatableResponse getPetByStatus(StatusDTO status) {
        return given(specification)
                     .basePath(BASE_PATH)
                     .log().all()
                .when()
                     .get()
                .then()
                    .log().all();
    }
}