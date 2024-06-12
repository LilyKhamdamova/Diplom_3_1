package APIInformation;


import UserClient.UserHelper;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverFactory extends ExternalResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(DriverFactory.class);
    private WebDriver driver;
    public String accessToken;

    public WebDriver getDriver() {
        return driver;
    }

    private void initFirefox() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        LOGGER.info("Initialized Firefox Driver");
    }

    private void initChrome() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        LOGGER.info("Initialized Chrome Driver");
    }

    private void initYandex() {

        WebDriverManager.chromedriver().driverVersion("122.0.6261.128").setup();
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
        driver = new ChromeDriver(options);
        LOGGER.info("Initialized Yandex Browser Driver");
    }


    public void initDriver(String browser) {
        if ("firefox".equals(browser)) {
            initFirefox();
        } else if ("yandex".equals(browser)) {
            initYandex();
        } else {
            initChrome();
        }
    }


    @Override
    protected void before() throws Throwable {
        initDriver(System.getProperty("browser"));
        LOGGER.info("Driver initialized for browser");
    }

    @Override
    protected void after() {
        if (driver != null) {
            driver.quit();
            LOGGER.info("Driver quit successfully");
        }
    }
}