package services;

import dto.NewUserDTO;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.Filter;
import io.restassured.http.*;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.*;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.security.KeyStore;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class NewUserApi {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private static final String BASE_PATH = "/user";
    private RequestSpecification spec;

    public NewUserApi() {
        spec =  given()
                    .baseUri(BASE_URL)
                    .contentType(ContentType.JSON);
    }

    public ValidatableResponse createUser(NewUserDTO user) {
       return given(spec)
                     .basePath(BASE_PATH)
                     .body(user)
                     .log().all()
                .when()
                     .post()
                .then()
                     .log().all();
    }
}