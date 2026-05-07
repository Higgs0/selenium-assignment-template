import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

class CrossBrowserTest {

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "firefox"})
    void loginPageCanOpenInMultipleBrowsers(String browser) {
        System.setProperty("browser", browser);
        System.setProperty("browser.headless", "true");

        TestConfig config = new TestConfig();
        WebDriver driver = new DriverFactory(config).createDriver();
        try {
            driver.get(config.baseUrl());
            LoginPage loginPage = new LoginPage(driver, config.timeoutSeconds());

            assertThat(loginPage.isOpen()).isTrue();
            assertThat(driver.getTitle()).isEqualTo("ACME Demo App by Applitools");
        } finally {
            driver.quit();
            System.clearProperty("browser");
        }
    }
}
