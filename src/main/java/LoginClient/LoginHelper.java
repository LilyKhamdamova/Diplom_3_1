package LoginClient;
//
//import UIObjects.ButtonClass;
//import org.junit.Assert;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//
//public class LoginHelper {
//    private WebDriver driver;
//    private ButtonClass buttonClass;
//
//    public LoginHelper(WebDriver driver, ButtonClass buttonClass) {
//        this.driver = driver;
//        this.buttonClass = buttonClass;
//    }
//
//    public void clickButton(By button) {
//        driver.findElement(button).click();
//    }
//
//    public void loginUser(String userEmail, String userPassword) {
//        driver.findElement(buttonClass.userEmailToEnter).sendKeys(userEmail);
//        driver.findElement(buttonClass.userPasswordToEnter).sendKeys(userPassword);
//        driver.findElement(buttonClass.confirmButton).click();
//    }
//
//    public void waitForElementVisibility(By element, String message) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
//        Assert.assertTrue(message, driver.findElement(element).isDisplayed());
//    }
//
//    public void clickLogoButton(By button) {
//        driver.findElement(button).click();
//    }
//}
////    public void fetchAuthTokenFromLocalStorage()
////    {
////        WebDriver driver1 = driverRule.getDriver;
////    }
//
//
import UIObjects.ButtonClass;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginHelper {
    private WebDriver driver;
    private ButtonClass buttonClass;

    public LoginHelper(WebDriver driver, ButtonClass buttonClass) {
        this.driver = driver;
        this.buttonClass = buttonClass;
    }

    @Step("Нажать кнопку: {button}")
    public void clickButton(By button) {
        driver.findElement(button).click();
    }

    @Step("Войти в систему с электронной почтой: {userEmail}, паролем: {userPassword}")
    public void loginUser(String userEmail, String userPassword) {
        driver.findElement(buttonClass.userEmailToEnter).sendKeys(userEmail);
        driver.findElement(buttonClass.userPasswordToEnter).sendKeys(userPassword);
        driver.findElement(buttonClass.confirmButton).click();
    }

    @Step("Дождаться видимости элемента: {element}")
    public void waitForElementVisibility(By element, String message) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        Assert.assertTrue(message, driver.findElement(element).isDisplayed());
    }

    @Step ("Нажать кнопку логотипа: {button}")
    public void clickLogoButton(By button) {
        driver.findElement(button).click();
    }
}
