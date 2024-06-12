import APIInformation.DriverFactory;
import LoginClient.LoginHelper;
import RegistrationClient.RegistrationClient;
import UIObjects.ButtonClass;
import UserClient.UserCredentials;
import UserClient.UserHelper;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;


public class LogoutTest

{
    private WebDriver driver;
    private ButtonClass buttonClass;
    protected String accessToken;


    @Rule
    public DriverFactory driverFactory = new DriverFactory();


    @Test
    public void validLogoutTest() {

        driver = driverFactory.getDriver();
        buttonClass = new ButtonClass();
        LoginHelper loginHelper = new LoginHelper(driver, buttonClass);
        RegistrationClient registrationClient = new RegistrationClient(driver,buttonClass);

        UserCredentials registrationCredentials = UserCredentials.random();
        Response response = UserHelper.createValidUser(registrationCredentials);

        UserHelper.verifyUserCreationSuccess(response);
        //Получить accessToken из ответа
        accessToken = response.jsonPath().getString("accessToken");

        var userEmail = registrationCredentials.getEmail();
        var userPassword = registrationCredentials.getPassword();

        driver.get(APIInformation.Addresses.BASE_URI);
        loginHelper.waitForElementInvisibility(buttonClass.loader,
                "Дождаться, что пропал загрузчик");
        loginHelper.waitForElementVisibility(buttonClass.personalAccountButton,
                "Появилась кнопка 'Личный Кабинет'");
        loginHelper.clickButton(buttonClass.personalAccountButton);
        loginHelper.clickButton(buttonClass.registrationButton);
        loginHelper.clickButton(buttonClass.enterButton);
        loginHelper.loginUser(userEmail, userPassword);
        loginHelper.waitForElementVisibility(buttonClass.makeOrderButton,
                "Кнопка 'Оформить заказ' появилась после успешного входа");
        loginHelper.waitForElementVisibility(buttonClass.personalAccountButton,
                "Появилась кнопка 'Личный Кабинет'");
        loginHelper.clickButton(buttonClass.personalAccountButton);
        loginHelper.waitForElementVisibility(buttonClass.logOutButton,
                "Кнопка 'Выйти' появилась");
        loginHelper.clickButton(buttonClass.logOutButton);
        registrationClient.waitForElementVisibility(buttonClass.confirmButton, "Кнопка 'Войти' появилась после успешного выхода" );
    }
    @After
    public void tearDown() {
        if (accessToken != null && !accessToken.isEmpty()) {
            UserHelper.deleteUserInformation(accessToken);
        }
    }
}
