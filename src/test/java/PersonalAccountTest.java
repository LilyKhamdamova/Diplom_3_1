import APIInformation.DriverFactory;
import LoginClient.LoginHelper;
import UIObjects.ButtonClass;
import UserClient.UserCredentials;
import UserClient.UserHelper;
import io.restassured.response.Response;

import org.junit.*;
import org.openqa.selenium.WebDriver;


public class PersonalAccountTest
{

    protected String accessToken;

    @Rule
    public DriverFactory driverFactory = new DriverFactory();


    @Test
    public void EnterToPersonalAccountTest() {

        WebDriver driver = driverFactory.getDriver();
        ButtonClass buttonClass = new ButtonClass();
        LoginHelper loginHelper = new LoginHelper(driver, buttonClass);

        UserCredentials registrationCredentials = UserCredentials.random();
        Response response = UserHelper.createValidUser(registrationCredentials);
        UserHelper.verifyUserCreationSuccess(response);

        //Получить accessToken из ответа
        accessToken = response.jsonPath().getString("accessToken");

        var userEmail = registrationCredentials.getEmail();
        var userPassword = registrationCredentials.getPassword();

        driver.get(APIInformation.Addresses.BASE_URI);
        loginHelper.waitForElementVisibility(buttonClass.personalAccountButton,
                "Появилась кнопка 'Личный Кабинет'");
        driver.findElement(buttonClass.personalAccountButton).click();
        driver.findElement(buttonClass.userEmailToEnter).sendKeys(userEmail);
        driver.findElement(buttonClass.userPasswordToEnter).sendKeys(userPassword);
        driver.findElement(buttonClass.confirmButton).click();
        driver.findElement(buttonClass.personalAccountButton).click();

        // Проверка, что после входа появляется кнопка "Профиль"
        loginHelper.waitForElementVisibility(buttonClass.profileSign,
                "Появилась кнопка 'Профиль'");
        Assert.assertTrue("Видно надпись\"Профиль\"",
                driver.findElement(buttonClass.profileSign).isDisplayed());
    }
    @After
    public void tearDown() {
        if (accessToken != null && !accessToken.isEmpty()) {
            UserHelper.deleteUserInformation(accessToken);
        }
    }
}
