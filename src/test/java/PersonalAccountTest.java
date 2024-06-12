import APIInformation.DriverFactory;
import LoginClient.LoginHelper;
import RegistrationClient.RegistrationClient;
import UIObjects.ButtonClass;
import UIObjects.ButtonClient;
import UserClient.UserCredentials;
import UserClient.UserHelper;
import io.restassured.response.Response;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class PersonalAccountTest  //extends BaseTest
{

    protected String accessToken;

    @Rule
    public DriverFactory driverFactory = new DriverFactory();


    @Test
    public void EnterToPersonalAccountTest() {

        // Установка системного свойства для использования Яндекс Браузера
        System.setProperty("browser", "yandex");

        // Инициализация драйвера через фабрику
        driverFactory.initDriver(System.getProperty("browser"));
        WebDriver driver = driverFactory.getDriver();
        ButtonClass buttonClass = new ButtonClass();
        LoginHelper loginHelper = new LoginHelper(driver, buttonClass);
        RegistrationClient registrationClient = new RegistrationClient(driver, buttonClass);

        UserCredentials registrationCredentials = UserCredentials.random();
        Response response = UserHelper.createValidUser(registrationCredentials);
        UserHelper.verifyUserCreationSuccess(response);

        //Получить accessToken из ответа
        accessToken = response.jsonPath().getString("accessToken");

        var userEmail = registrationCredentials.getEmail();
        var userPassword = registrationCredentials.getPassword();

        driver.get(APIInformation.Addresses.BASE_URI);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonClass.personalAccountButton));
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonClass.personalAccountButton));
        driver.findElement(buttonClass.personalAccountButton).click();
        driver.findElement(buttonClass.userEmailToEnter).sendKeys(userEmail);
        driver.findElement(buttonClass.userPasswordToEnter).sendKeys(userPassword);
        driver.findElement(buttonClass.confirmButton).click();
        driver.findElement(buttonClass.personalAccountButton).click();

        // Проверка, что после входа появляется кнопка "Профиль"
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonClass.profileSign));
        Assert.assertTrue("Видно надпись\"Профиль\"",
                driver.findElement(buttonClass.profileSign).isDisplayed());
        if (accessToken != null && !accessToken.isEmpty()) {
            UserHelper.deleteUserInformation(accessToken);
        }
    }
}
