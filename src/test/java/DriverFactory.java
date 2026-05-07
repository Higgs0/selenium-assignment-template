import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    private final TestConfig config;

    public DriverFactory(TestConfig config) {
        this.config = config;
    }

    public WebDriver createDriver() {
        WebDriver driver;

        if ("firefox".equalsIgnoreCase(config.browser())) {
            FirefoxOptions options = new FirefoxOptions();
            if (config.headless()) {
                options.addArguments("-headless");
            }
            driver = new FirefoxDriver(options);
        } else {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            if (config.headless()) {
                options.addArguments("--headless");
            }
            driver = new ChromeDriver(options);
        }

        driver.manage().window().setSize(new Dimension(config.windowWidth(), config.windowHeight()));
        return driver;
    }
}
