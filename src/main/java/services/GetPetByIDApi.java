package services;

import dto.NewUserDTO;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class GetPetByIDApi {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private static final String BASE_PATH = "pet/5";
    private RequestSpecification specification;



    public GetPetByIDApi() {
        specification =  given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON);
    }

    public ValidatableResponse getPetByID() {
        return given(specification)
                     .basePath(BASE_PATH)
                    .log().all()
                .when()
                    .get()
                .then()
                     .log().all();
    }
}