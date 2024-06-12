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


public class RegistrationTest  //extends BaseTest
{

    private WebDriver driver;
    private ButtonClass buttonClass;
    protected String accessToken;
    //private LoginHelper loginHelper;
    private RegistrationClient registrationClient;

    @Rule
    public DriverFactory driverFactory = new DriverFactory();


    @Test
    public void validRegistrationTest() {

        // Установка системного свойства для использования Яндекс Браузера
        System.setProperty("browser", "yandex");

        // Инициализация драйвера через фабрику
        driverFactory.initDriver(System.getProperty("browser"));
        driver = driverFactory.getDriver();
        buttonClass = new ButtonClass();
        LoginHelper loginHelper = new LoginHelper(driver, buttonClass);
        registrationClient = new RegistrationClient(driver,buttonClass);

        // Генерация случайных данных пользователя и выполнение регистрации
        String password = "TestPassword";
        String userName = RandomStringUtils.randomAlphabetic(5, 15);
        String userEmail = RandomStringUtils.randomAlphabetic(5, 15) + "@yandex.ru";

        driver.get(APIInformation.Addresses.BASE_URI);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonClass.personalAccountButton));
        registrationClient.clickToRegistrationButton(buttonClass.personalAccountButton);
        registrationClient.registerNewUser(userName, userEmail, password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonClass.personalAccountButton));
        loginHelper.clickButton(buttonClass.enterButton);
        loginHelper.loginUser(userEmail, password);
        // Ожидание появления кнопки оформления заказа
        registrationClient.waitForElementVisibility(buttonClass.makeBurger, "Кнопка оформления заказа видна");
        if (accessToken != null && !accessToken.isEmpty()) {
            UserHelper.deleteUserInformation(accessToken);
        }
    }

    @Test
    public void invalidPasswordTest() {
        // Установка системного свойства для использования Яндекс Браузера
        System.setProperty("browser", "yandex");

        // Инициализация драйвера через фабрику
        driverFactory.initDriver(System.getProperty("browser"));
        driver = driverFactory.getDriver();
        buttonClass = new ButtonClass();
        //LoginHelper loginHelper = new LoginHelper(driver, buttonClass);
        registrationClient = new RegistrationClient(driver,buttonClass);


        String password = "Test";
        String userName = RandomStringUtils.randomAlphabetic(5, 15);
        String userEmail = RandomStringUtils.randomAlphabetic(5, 15) + "@yandex.ru";
        driver.get(APIInformation.Addresses.BASE_URI);

        // Генерация случайных данных пользователя и выполнение регистрации
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonClass.personalAccountButton));
        registrationClient.clickToRegistrationButton(buttonClass.personalAccountButton);
        registrationClient.registerNewUser(userName, userEmail, password);


        // Проверка, что отображается сообщение об ошибке для неверного пароля
        Assert.assertTrue("Сообщение об ошибке для неверного пароля не отображается",
                driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[3]/div/p"))
                        .isDisplayed());
        if (accessToken != null && !accessToken.isEmpty()) {
            UserHelper.deleteUserInformation(accessToken);
        }
    }
}
