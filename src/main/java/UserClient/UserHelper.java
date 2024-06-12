package UserClient;

import APIInformation.Addresses;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.net.HttpURLConnection;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class UserHelper {

    @Step("Создать пользователя с валидными данными")
    public static Response createValidUser(UserCredentials userCredentials) {
        return given()
                .contentType(ContentType.JSON)
                .body(userCredentials).log().all()
                .when()
                .post(APIInformation.Addresses.BASE_URI + APIInformation.Addresses.REGISTER)
                .then()
                .log().all()
                .extract().response();
    }

    @Step("Удалить информацию о пользователе")
    public static void deleteUserInformation(String accessToken) {
        if (accessToken == null || accessToken.isEmpty()) {
            throw new IllegalArgumentException("Token cannot be null or empty");
        }
        given()
                .header("Authorization", accessToken).log().all()
                .contentType(ContentType.JSON)
                .when()
                .delete(Addresses.BASE_URI + Addresses.USER)
                .then()
                .log().all()
                .statusCode(HttpURLConnection.HTTP_ACCEPTED)
                .body("success", equalTo(true))
                .body("message", equalTo("User successfully removed"))
                .extract().response();
    }

    @Step("Проверить успешное создание пользователя")
    public static void verifyUserCreationSuccess(Response response) {
        response.then()
                .log().all()
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("success", equalTo(true));
    }
}
