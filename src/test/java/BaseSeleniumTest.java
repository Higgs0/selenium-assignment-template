import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

@ExtendWith(ScreenshotOnFailureExtension.class)
public abstract class BaseSeleniumTest {
    protected TestConfig config;
    protected WebDriver driver;

    @BeforeEach
    void setUp() {
        config = new TestConfig();
        driver = new DriverFactory(config).createDriver();
        ScreenshotOnFailureExtension.setDriver(driver);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        ScreenshotOnFailureExtension.clearDriver();
    }

    protected LoginPage openLoginPage() {
        driver.get(config.baseUrl());
        return new LoginPage(driver, config.timeoutSeconds());
    }
}
