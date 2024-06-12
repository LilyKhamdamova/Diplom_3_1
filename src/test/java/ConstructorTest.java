//import APIInformation.DriverFactory;
//import LoginClient.LoginHelper;
//import UIObjects.ButtonClass;
//import UIObjects.ButtonClient;
//import UserClient.UserCredentials;
//import UserClient.UserHelper;
//import io.restassured.response.Response;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//
//public class ConstructorTest
//
//{
//    private WebDriver driver;
//    private ButtonClass buttonClass;
//    private ButtonClient buttonClient;
//    protected String accessToken;
//    private LoginHelper loginHelper;
//
//    @Rule
//    public DriverFactory driverFactory = new DriverFactory();
//
//    @Test
//    public void switchIngredientsConstructorTestSauce() {
//// Установка системного свойства для использования Яндекс Браузера
//         System.setProperty("browser", "yandex");
//
//        // Инициализация драйвера через фабрику
//        driverFactory.initDriver(System.getProperty("browser"));
//        driver = driverFactory.getDriver();
//        buttonClass = new ButtonClass();
//        buttonClient = new ButtonClient(driver);
//        LoginHelper loginHelper = new LoginHelper(driver, buttonClass);
//
//        UserCredentials registrationCredentials = UserCredentials.random();
//        Response response = UserHelper.createValidUser(registrationCredentials);
//
//        UserHelper.verifyUserCreationSuccess(response);
//        accessToken = response.jsonPath().getString("accessToken");
//
//        var userEmail = registrationCredentials.getEmail();
//        var userPassword = registrationCredentials.getPassword();
//
//        driver.get(APIInformation.Addresses.BASE_URI);
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(150));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonClass.personalAccountButton));
//
//        loginHelper.clickButton(buttonClass.personalAccountButton);
//        loginHelper.loginUser(userEmail, userPassword);
//
//        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonClass.sauce));
//
//        // Проверка перехода к разделу "Соусы"
//        buttonClient.checkSwitchBetweenSection(buttonClass.sauce, wait);
//
//        // Проверка перехода к разделу "Начинки"
//        buttonClient.checkSwitchBetweenSection(buttonClass.filling, wait);
//
//        // Проверка перехода к разделу "Булки"
//        buttonClient.checkSwitchBetweenSection(buttonClass.ban, wait);
//    }
//}
package APIInformation;

import LoginClient.LoginHelper;
import RegistrationClient.RegistrationClient;
import UIObjects.ButtonClass;
import UIObjects.ButtonClient;
import UserClient.UserCredentials;
import UserClient.UserHelper;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ConstructorTest {
    protected String accessToken;

    @Rule
    public DriverFactory driverFactory = new DriverFactory();


    @Test
    public void switchIngredientsConstructorTestSauce() {


        WebDriver driver = driverFactory.getDriver();
        ButtonClass buttonClass = new ButtonClass();
        ButtonClient buttonClient = new ButtonClient(driver);
        LoginHelper loginHelper = new LoginHelper(driver, buttonClass);


        UserCredentials registrationCredentials = UserCredentials.random();
        Response response = UserHelper.createValidUser(registrationCredentials);

        UserHelper.verifyUserCreationSuccess(response);
        accessToken = response.jsonPath().getString("accessToken");

        var userEmail = registrationCredentials.getEmail();
        var userPassword = registrationCredentials.getPassword();

        driver.get(APIInformation.Addresses.BASE_URI);

        loginHelper.waitForElementVisibility(buttonClass.personalAccountButton,
                "Появилась кнопка 'Личный Кабинет'");

        loginHelper.clickButton(buttonClass.personalAccountButton);
        loginHelper.loginUser(userEmail, userPassword);
        loginHelper.waitForElementVisibility(buttonClass.sauce,
                "Появилась надпись 'Соусы'");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(150));

        // Проверка перехода к разделу "Соусы"
        buttonClient.checkSwitchBetweenSection(buttonClass.sauce, wait);

        // Проверка перехода к разделу "Начинки"
        buttonClient.checkSwitchBetweenSection(buttonClass.filling, wait);

        // Проверка перехода к разделу "Булки"
        buttonClient.checkSwitchBetweenSection(buttonClass.ban, wait);


        }
    @After
    public void tearDown() {
        if (accessToken != null && !accessToken.isEmpty()) {
            UserHelper.deleteUserInformation(accessToken);
        }
    }
}
