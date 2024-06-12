import APIInformation.DriverFactory;
import LoginClient.LoginHelper;
import RegistrationClient.RegistrationClient;
import UIObjects.ButtonClass;
import UserClient.UserHelper;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class RegistrationTest
{

    private WebDriver driver;
    private ButtonClass buttonClass;
    protected String accessToken;
    private RegistrationClient registrationClient;

    @Rule
    public DriverFactory driverFactory = new DriverFactory();


    @Test
    public void validRegistrationTest() {

        driver = driverFactory.getDriver();
        buttonClass = new ButtonClass();
        LoginHelper loginHelper = new LoginHelper(driver, buttonClass);
        registrationClient = new RegistrationClient(driver,buttonClass);

        // Генерация случайных данных пользователя и выполнение регистрации
        String password = "TestPassword";
        String userName = RandomStringUtils.randomAlphabetic(5, 15);
        String userEmail = RandomStringUtils.randomAlphabetic(5, 15) + "@yandex.ru";

        driver.get(APIInformation.Addresses.BASE_URI);
        loginHelper.waitForElementVisibility(buttonClass.personalAccountButton,
                "Появилась кнопка 'Личный Кабинет'");
        registrationClient.clickToRegistrationButton(buttonClass.personalAccountButton);
        registrationClient.registerNewUser(userName, userEmail, password);
        loginHelper.waitForElementVisibility(buttonClass.personalAccountButton,
                "Появилась кнопка 'Личный Кабинет'");
        loginHelper.waitForElementVisibility(buttonClass.enterButton, "Появилась кнопка входа");
        loginHelper.loginUser(userEmail, password);
        // Ожидание появления кнопки оформления заказа
        registrationClient.waitForElementVisibility(buttonClass.makeBurgerSign, "Кнопка оформления заказа видна");
        accessToken = loginHelper.fetchAuthTokenFromLocalStorage();
    }

    @Test
    public void invalidPasswordTest() {

        driver = driverFactory.getDriver();
        buttonClass = new ButtonClass();
        LoginHelper loginHelper = new LoginHelper(driver, buttonClass);
        registrationClient = new RegistrationClient(driver,buttonClass);


        String password = "Test";
        String userName = RandomStringUtils.randomAlphabetic(5, 15);
        String userEmail = RandomStringUtils.randomAlphabetic(5, 15) + "@yandex.ru";
        driver.get(APIInformation.Addresses.BASE_URI);

        // Генерация случайных данных пользователя и выполнение регистрации
        loginHelper.waitForElementVisibility(buttonClass.personalAccountButton,
                "Появилась кнопка 'Личный Кабинет'");
        registrationClient.clickToRegistrationButton(buttonClass.personalAccountButton);
        registrationClient.registerNewUser(userName, userEmail, password);


        // Проверка, что отображается сообщение об ошибке для неверного пароля
        Assert.assertTrue("Сообщение об ошибке для неверного пароля не отображается",
                driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[3]/div/p"))
                        .isDisplayed());
        }

    @After
    public void tearDown() {
        if (accessToken != null && !accessToken.isEmpty()) {
            UserHelper.deleteUserInformation(accessToken);
        }
    }
    }
