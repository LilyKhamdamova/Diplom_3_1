//////import APIInformation.DriverFactory;
//////import LoginClient.LoginHelper;
//////import UIObjects.ButtonClass;
//////import UserClient.UserCredentials;
//////import UserClient.UserHelper;
//////import io.qameta.allure.junit4.DisplayName;
//////import io.restassured.response.Response;
//////import jdk.jfr.Description;
//////import org.junit.Before;
//////import org.junit.Rule;
//////import org.junit.Test;
//////import org.openqa.selenium.support.ui.ExpectedConditions;
//////import org.openqa.selenium.support.ui.WebDriverWait;
//////
//////import java.time.Duration;
//////
//////public class BackToConstructorTest
//////        //extends BaseTest
//////{
//////
//////    //private ButtonClass buttonClass;
//////    //private LoginHelper loginHelper;
//////    protected String accessToken;
//////    ButtonClass buttonClass = new ButtonClass();
//////    LoginHelper loginHelper = new LoginHelper(driver, buttonClass);
//////
//////@Rule public DriverFactory driver = new DriverFactory();
//////
//////
////////    @Before
////////    public void setUp() {
////////        super.setUp(); // Вызов метода setUp() из базового класса
////////
////////    }
//////
//////    @Test
//////    @DisplayName("Переход в конструктор из личного кабинета")
//////    @Description("Этот тест проверяет, что после входа в личный кабинет и нажатия на логотип, пользователь перенаправляется в конструктор.")
//////    public void toGetConstructorFromPersonalAccount() {
//////        UserCredentials registrationCredentials = UserCredentials.random();
//////        Response response = UserHelper.createValidUser(registrationCredentials);
//////        UserHelper.verifyUserCreationSuccess(response);
//////
//////        // Получить accessToken из ответа
//////        accessToken = response.jsonPath().getString("accessToken");
//////
//////        String userEmail = registrationCredentials.getEmail();
//////        String userPassword = registrationCredentials.getPassword();
//////
//////        driver.get(APIInformation.Addresses.BASE_URI);
//////
//////
//////        // Вход в аккаунт
//////        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//////        wait.until(ExpectedConditions.invisibilityOfElementLocated(buttonClass.loader));// Ожидание видимости элемента
//////        loginHelper.clickButton(buttonClass.personalAccountButton);
//////        loginHelper.loginUser(userEmail, userPassword);
//////
//////        // Переход в личный кабинет и нажатие на логотип для перехода в конструктор
//////        wait.until(ExpectedConditions.invisibilityOfElementLocated(buttonClass.loader));
//////        loginHelper.clickButton(buttonClass.personalAccountButton);
//////        loginHelper.clickLogoButton(buttonClass.logo);
//////
//////        // Проверка, что отображается конструктор, проверяя наличие элемента
//////        loginHelper.waitForElementVisibility(buttonClass.makeOrderButton, "Видно надпись 'Оформить заказ'");
//////    }
//////}
////
////
////import APIInformation.DriverFactory;
////import LoginClient.LoginHelper;
////import UIObjects.ButtonClass;
////import UserClient.UserCredentials;
////import UserClient.UserHelper;
////import io.qameta.allure.junit4.DisplayName;
////import io.restassured.response.Response;
////import jdk.jfr.Description;
////import org.junit.Before;
////import org.junit.Rule;
////import org.junit.Test;
////import org.openqa.selenium.WebDriver;
////import org.openqa.selenium.support.ui.ExpectedConditions;
////import org.openqa.selenium.support.ui.WebDriverWait;
////
////import java.time.Duration;
////
////public class BackToConstructorTest {
////    private WebDriver driver;
////    private ButtonClass buttonClass;
////    private LoginHelper loginHelper;
////    private String accessToken;
////
////    @Rule
////    public DriverFactory driverFactory = new DriverFactory();
////
////    @Before
////    public void setUp() {
////        //System.setProperty("browser", "yandex");
////        driverFactory.initDriver();
////        driver = driverFactory.getDriver();
////        buttonClass = new ButtonClass();
////        loginHelper = new LoginHelper(driver, buttonClass);
////    }
////
////    @Test
////    @DisplayName("Переход в конструктор из личного кабинета")
////    @Description("Этот тест проверяет, что после входа в личный кабинет и нажатия на логотип, пользователь перенаправляется в конструктор.")
////    public void toGetConstructorFromPersonalAccount() {
////        UserCredentials registrationCredentials = UserCredentials.random();
////        Response response = UserHelper.createValidUser(registrationCredentials);
////        UserHelper.verifyUserCreationSuccess(response);
////
////        // Получить accessToken из ответа
////        accessToken = response.jsonPath().getString("accessToken");
////
////        String userEmail = registrationCredentials.getEmail();
////        String userPassword = registrationCredentials.getPassword();
////
////        driver.get(APIInformation.Addresses.BASE_URI);
////
////        // Вход в аккаунт
////        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
////        wait.until(ExpectedConditions.invisibilityOfElementLocated(buttonClass.loader)); // Ожидание невидимости элемента
////        loginHelper.clickButton(buttonClass.personalAccountButton);
////        loginHelper.loginUser(userEmail, userPassword);
////
////        // Переход в личный кабинет и нажатие на логотип для перехода в конструктор
////        wait.until(ExpectedConditions.invisibilityOfElementLocated(buttonClass.loader));
////        loginHelper.clickButton(buttonClass.personalAccountButton);
////        loginHelper.clickLogoButton(buttonClass.logo);
////
////        // Проверка, что отображается конструктор, проверяя наличие элемента
////        loginHelper.waitForElementVisibility(buttonClass.makeOrderButton, "Видно надпись 'Оформить заказ'");
////    }
////}
//
//import APIInformation.DriverFactory;
//import LoginClient.LoginHelper;
//import UIObjects.ButtonClass;
//import UserClient.UserCredentials;
//import UserClient.UserHelper;
//import io.qameta.allure.junit4.DisplayName;
//import io.restassured.response.Response;
//import jdk.jfr.Description;
//import org.junit.Rule;
//import org.junit.Test;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//
//public class BackToConstructorTest {
//    private WebDriver driver;
//    private ButtonClass buttonClass;
//    private LoginHelper loginHelper;
//    private String accessToken;
//
//    @Rule
//    public DriverFactory driverFactory = new DriverFactory();
//
//    @Test
//    @DisplayName("Переход в конструктор из личного кабинета")
//    @Description("Этот тест проверяет, что после входа в личный кабинет и нажатия на логотип, пользователь перенаправляется в конструктор.")
//    public void toGetConstructorFromPersonalAccount() {
//        // Получение WebDriver из DriverFactory
//        driver = driverFactory.getDriver();
//        buttonClass = new ButtonClass();
//        loginHelper = new LoginHelper(driver, buttonClass);
//
//        // Создание нового пользователя и проверка успеха
//        UserCredentials registrationCredentials = UserCredentials.random();
//        Response response = UserHelper.createValidUser(registrationCredentials);
//        UserHelper.verifyUserCreationSuccess(response);
//
//        // Получение accessToken из ответа
//        accessToken = response.jsonPath().getString("accessToken");
//
//        String userEmail = registrationCredentials.getEmail();
//        String userPassword = registrationCredentials.getPassword();
//
//        driver.get(APIInformation.Addresses.BASE_URI);
//
//        // Вход в аккаунт
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(buttonClass.loader)); // Ожидание невидимости элемента
//        loginHelper.clickButton(buttonClass.personalAccountButton);
//        loginHelper.loginUser(userEmail, userPassword);
//
//        // Переход в личный кабинет и нажатие на логотип для перехода в конструктор
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(buttonClass.loader));
//        loginHelper.clickButton(buttonClass.personalAccountButton);
//        loginHelper.clickLogoButton(buttonClass.logo);
//
//        // Проверка, что отображается конструктор, проверяя наличие элемента
//        loginHelper.waitForElementVisibility(buttonClass.makeOrderButton, "Видно надпись 'Оформить заказ'");
//    }
//}
import APIInformation.DriverFactory;
import LoginClient.LoginHelper;
import UIObjects.ButtonClass;
import UserClient.UserCredentials;
import UserClient.UserHelper;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BackToConstructorTest {

    @Rule
    public DriverFactory driverFactory = new DriverFactory();

    @Test
    @DisplayName("Переход в конструктор из личного кабинета")
    @Description("Этот тест проверяет, что после входа в личный кабинет и нажатия на логотип, пользователь перенаправляется в конструктор.")
    public void toGetConstructorFromPersonalAccount() {
        // Установка системного свойства для использования Яндекс Браузера
        System.setProperty("browser", "yandex");

        // Инициализация драйвера через фабрику
        driverFactory.initDriver(System.getProperty("browser"));
        WebDriver driver = driverFactory.getDriver();
        ButtonClass buttonClass = new ButtonClass();
        LoginHelper loginHelper = new LoginHelper(driver, buttonClass);

        // Создание нового пользователя и проверка успеха
        UserCredentials registrationCredentials = UserCredentials.random();
        Response response = UserHelper.createValidUser(registrationCredentials);
        UserHelper.verifyUserCreationSuccess(response);

        // Получение accessToken из ответа
        String accessToken = response.jsonPath().getString("accessToken");

        String userEmail = registrationCredentials.getEmail();
        String userPassword = registrationCredentials.getPassword();

        driver.get(APIInformation.Addresses.BASE_URI);

        // Вход в аккаунт
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(buttonClass.loader)); // Ожидание невидимости элемента
        loginHelper.clickButton(buttonClass.personalAccountButton);
        loginHelper.loginUser(userEmail, userPassword);

        // Переход в личный кабинет и нажатие на логотип для перехода в конструктор
        wait.until(ExpectedConditions.invisibilityOfElementLocated(buttonClass.loader));
        loginHelper.clickButton(buttonClass.personalAccountButton);
        loginHelper.clickLogoButton(buttonClass.logo);

        // Проверка, что отображается конструктор, проверяя наличие элемента
        loginHelper.waitForElementVisibility(buttonClass.makeOrderButton, "Видно надпись 'Оформить заказ'");
        if (accessToken != null && !accessToken.isEmpty()) {
            UserHelper.deleteUserInformation(accessToken);
        }
    }
}
