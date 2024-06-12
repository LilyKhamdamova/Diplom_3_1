import APIInformation.DriverFactory;
import LoginClient.LoginHelper;
import UIObjects.ButtonClass;
import UserClient.UserCredentials;
import UserClient.UserHelper;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class BackToConstructorTest {

    @Rule
    public DriverFactory driverFactory = new DriverFactory();

    String accessToken;

    @Test
    @DisplayName("Переход в конструктор из личного кабинета")
    @Description("Этот тест проверяет, что после входа в личный кабинет и нажатия на логотип, пользователь перенаправляется в конструктор.")
    public void toGetConstructorFromPersonalAccount() {

        WebDriver driver = driverFactory.getDriver();
        ButtonClass buttonClass = new ButtonClass();
        LoginHelper loginHelper = new LoginHelper(driver, buttonClass);

        // Создание нового пользователя и проверка успеха
        UserCredentials registrationCredentials = UserCredentials.random();
        Response response = UserHelper.createValidUser(registrationCredentials);
        UserHelper.verifyUserCreationSuccess(response);

        // Получение accessToken из ответа
        accessToken = response.jsonPath().getString("accessToken");

        String userEmail = registrationCredentials.getEmail();
        String userPassword = registrationCredentials.getPassword();

        driver.get(APIInformation.Addresses.BASE_URI);

        // Вход в аккаунт
        loginHelper.waitForElementInvisibility(buttonClass.loader, "Дождаться, что пропал загрузчик");
        loginHelper.clickButton(buttonClass.personalAccountButton);
        loginHelper.loginUser(userEmail, userPassword);

        // Переход в личный кабинет и нажатие на логотип для перехода в конструктор
        loginHelper.waitForElementInvisibility(buttonClass.loader,
                "Дождаться, что пропал загрузчик");
        loginHelper.clickButton(buttonClass.personalAccountButton);
        loginHelper.clickLogoButton(buttonClass.logo);

        // Проверка, что отображается конструктор, проверяя наличие элемента
        loginHelper.waitForElementVisibility(buttonClass.makeOrderButton, "Видно надпись 'Оформить заказ'");
    }
    @After
        public void tearDown() {
        if (accessToken != null && !accessToken.isEmpty()) {
            UserHelper.deleteUserInformation(accessToken);
        }
        }
}