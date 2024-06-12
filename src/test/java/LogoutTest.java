import APIInformation.DriverFactory;
import LoginClient.LoginHelper;
import RegistrationClient.RegistrationClient;
import UIObjects.ButtonClass;
import UserClient.UserCredentials;
import UserClient.UserHelper;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LogoutTest

{
    private WebDriver driver;
    private ButtonClass buttonClass;
    protected String accessToken;


    @Rule
    public DriverFactory driverFactory = new DriverFactory();


    @Test
    public void validLogoutTest() {
        // Установка системного свойства для использования Яндекс Браузера
        System.setProperty("browser", "yandex");

        // Инициализация драйвера через фабрику
        driverFactory.initDriver(System.getProperty("browser"));
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(150));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(buttonClass.loader));
        wait.until(ExpectedConditions.elementToBeClickable(buttonClass.personalAccountButton));
        loginHelper.clickButton(buttonClass.personalAccountButton);
        loginHelper.clickButton(buttonClass.registrationButton);
        loginHelper.clickButton(buttonClass.enterButton);
        loginHelper.loginUser(userEmail, userPassword);
        loginHelper.waitForElementVisibility(buttonClass.makeOrderButton, "Кнопка 'Оформить заказ' появилась после успешного входа");

        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonClass.personalAccountButton));
        loginHelper.clickButton(buttonClass.personalAccountButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonClass.logOutButton));
        loginHelper.clickButton(buttonClass.logOutButton);
        // Проверка, что после входа появляется кнопка "Личный кабинет"
        registrationClient.waitForElementVisibility(buttonClass.saveButton, "Кнопка 'Войти' появилась после успешного выхода" );
        if (accessToken != null && !accessToken.isEmpty()) {
            UserHelper.deleteUserInformation(accessToken);
        }
    }
}
