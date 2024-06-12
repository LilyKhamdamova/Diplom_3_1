package UIObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ButtonClient {
    private WebDriver driver;

    public ButtonClient(WebDriver driver) {
        this.driver = driver;
    }

    public void checkSwitchBetweenSection(By buttonClass, WebDriverWait wait) {
        driver.findElement(buttonClass).click();
        wait.until(ExpectedConditions.attributeContains(buttonClass, "class", "current"));
    }
}
