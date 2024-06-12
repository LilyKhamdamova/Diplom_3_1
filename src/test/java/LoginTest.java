import APIInformation.DriverFactory;
import LoginClient.LoginHelper;
import UIObjects.ButtonClass;
import UserClient.UserCredentials;
import UserClient.UserHelper;
import io.restassured.response.Response;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginTest
        //extends BaseTest
 {

private WebDriver driver;
private ButtonClass buttonClass;
protected String accessToken;
//private LoginHelper loginHelper;

@Rule
public DriverFactory driverFactory = new DriverFactory();


    @Test
    public void validLoginPersonalAccountButtonTest() {


        driver = driverFactory.getDriver();
        buttonClass = new ButtonClass();
        LoginHelper loginHelper = new LoginHelper(driver, buttonClass);

        UserCredentials registrationCredentials = UserCredentials.random();
        Response response = UserHelper.createValidUser(registrationCredentials);
        UserHelper.verifyUserCreationSuccess(response);

        accessToken = response.jsonPath().getString("accessToken");

        String userEmail = registrationCredentials.getEmail();
        String userPassword = registrationCredentials.getPassword();

        driver.get(APIInformation.Addresses.BASE_URI);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(150));
        wait.until(ExpectedConditions.elementToBeClickable(buttonClass.personalAccountButton));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(buttonClass.loader));
        loginHelper.clickButton(buttonClass.personalAccountButton);
        loginHelper.loginUser(userEmail, userPassword);
        loginHelper.waitForElementVisibility(buttonClass.makeOrderButton, "Кнопка 'Оформить заказ' появилась после успешного входа");

    }

    @Test
    public void validLoginEnterToAccountButtonTest() {

        driver = driverFactory.getDriver();
        buttonClass = new ButtonClass();
        LoginHelper loginHelper = new LoginHelper(driver, buttonClass);

        UserCredentials registrationCredentials = UserCredentials.random();
        Response response = UserHelper.createValidUser(registrationCredentials);
        UserHelper.verifyUserCreationSuccess(response);

        accessToken = response.jsonPath().getString("accessToken");

        String userEmail = registrationCredentials.getEmail();
        String userPassword = registrationCredentials.getPassword();

        driver.get(APIInformation.Addresses.BASE_URI);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(150));

        wait.until(ExpectedConditions.elementToBeClickable(buttonClass.personalAccountButton));
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonClass.personalAccountButton));
        loginHelper.clickButton(buttonClass.enterToAccountButton);
        loginHelper.loginUser(userEmail, userPassword);
        loginHelper.waitForElementVisibility(buttonClass.makeOrderButton, "Кнопка 'Оформить заказ' появилась после успешного входа");

    }

    @Test
    public void validLoginFromRegistrationFormTest() {

        driver = driverFactory.getDriver();
        buttonClass = new ButtonClass();
        LoginHelper loginHelper = new LoginHelper(driver, buttonClass);

        UserCredentials registrationCredentials = UserCredentials.random();
        Response response = UserHelper.createValidUser(registrationCredentials);
        UserHelper.verifyUserCreationSuccess(response);

        accessToken = response.jsonPath().getString("accessToken");

        String userEmail = registrationCredentials.getEmail();
        String userPassword = registrationCredentials.getPassword();

        driver.get(APIInformation.Addresses.BASE_URI);

        loginHelper.waitForElementInvisibility(buttonClass.loader,
                "Дождаться, что пропал загрузчик");
        loginHelper.waitForElementVisibility(buttonClass.personalAccountButton,
                "Появилась кнопка 'Личный Кабинет'");
        loginHelper.clickButton(buttonClass.personalAccountButton);
        loginHelper.clickButton(buttonClass.registrationButton);
        loginHelper.clickButton(buttonClass.enterButton);
        loginHelper.loginUser(userEmail, userPassword);
        loginHelper.waitForElementVisibility(buttonClass.makeOrderButton, "Кнопка 'Оформить заказ' появилась после успешного входа");

    }

    @Test
    public void validLoginForgotPasswordTest() {

        driver = driverFactory.getDriver();
        buttonClass = new ButtonClass();
        LoginHelper loginHelper = new LoginHelper(driver, buttonClass);

        UserCredentials registrationCredentials = UserCredentials.random();
        Response response = UserHelper.createValidUser(registrationCredentials);
        UserHelper.verifyUserCreationSuccess(response);

        accessToken = response.jsonPath().getString("accessToken");

        String userEmail = registrationCredentials.getEmail();
        String userPassword = registrationCredentials.getPassword();

        driver.get(APIInformation.Addresses.BASE_URI);
        loginHelper.waitForElementInvisibility(buttonClass.loader,
                "Дождаться, что пропал загрузчик");
        loginHelper.waitForElementVisibility(buttonClass.personalAccountButton,
                "Появилась кнопка 'Личный Кабинет'");
        loginHelper.clickButton(buttonClass.personalAccountButton);
        loginHelper.clickButton(buttonClass.enterForgotPassword);
        loginHelper.clickButton(buttonClass.enterButton);
        loginHelper.loginUser(userEmail, userPassword);
        loginHelper.waitForElementVisibility(buttonClass.makeOrderButton, "Кнопка 'Оформить заказ' появилась после успешного входа");

    }
     @After
     public void tearDown() {
         if (accessToken != null && !accessToken.isEmpty()) {
             UserHelper.deleteUserInformation(accessToken);
         }
     }
}

