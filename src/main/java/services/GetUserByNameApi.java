package services;

import dto.pet.StatusDTO;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class GetUserByNameApi {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private static final String BASE_PATH = "/user/{username}";
    private RequestSpecification specification;


    public GetUserByNameApi() {
        specification =  given()
                .pathParams ("username", "Savva")
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON);
    }

    public ValidatableResponse getUserByName() {
        return given(specification)
                    .basePath(BASE_PATH)
                    .log().all()
                .when()
                    .get()
                .then()
                    .log().all();
    }
}
