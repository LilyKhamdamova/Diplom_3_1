package RegistrationClient;

import UIObjects.ButtonClass;
import UserClient.UserCredentials;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationClient {

    protected String accessToken;
    protected UserCredentials registrationCredentials;
    private WebDriver driver;
    private ButtonClass buttonClass;

    public RegistrationClient(WebDriver driver, ButtonClass buttonClass) {
        this.driver = driver;
        this.buttonClass = buttonClass;
    }

    @Step("Нажать на кнопку регистрации")
    public void clickToRegistrationButton(By button) {
        driver.findElement(button).click();
    }

    @Step("Зарегистрировать нового пользователя с именем: {userName}, адресом электронной почты: {userEmail}, и паролем: {passwordValid}")
    public void registerNewUser(String userName, String userEmail, String passwordValid) {
        driver.findElement(buttonClass.registrationButton).click();

        // Заполнение формы регистрации
        driver.findElement(buttonClass.userNameInput).sendKeys(userName);
        driver.findElement(buttonClass.userEmailInput).sendKeys(userEmail);
        driver.findElement(buttonClass.userPasswordInput).sendKeys(passwordValid);
        driver.findElement(buttonClass.confirmButton).click();
    }

    @Step("Дождаться видимости элемента")
    public void waitForElementVisibility(By element, String message) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        assert driver.findElement(element).isDisplayed() : message;
    }

    public void setRegistrationCredentials(UserCredentials registrationCredentials) {
        this.registrationCredentials = registrationCredentials;
    }

}
