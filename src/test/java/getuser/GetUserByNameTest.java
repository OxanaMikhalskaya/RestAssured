package getuser;

import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import services.GetUserByNameApi;

public class GetUserByNameTest {

    /*
    ��������, ��� �� ����� �������� ������������ �� �����
     */

    @Test
    public void getUserByName () {
        GetUserByNameApi getUserByNameApi = new GetUserByNameApi();

        ValidatableResponse validatableResponse = getUserByNameApi.getUserByName();

        validatableResponse
                .statusCode (200);
    }
}