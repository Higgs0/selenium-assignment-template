import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static org.assertj.core.api.Assertions.assertThat;

class BrowserStateTest extends BaseSeleniumTest {

    @Test
    void testCanAddReadAndDeleteCookie() {
        driver.get(config.baseUrl());

        Cookie cookie = new Cookie("assignment-cookie", "selenium-demo");
        driver.manage().addCookie(cookie);

        assertThat(driver.manage().getCookieNamed("assignment-cookie").getValue())
                .isEqualTo("selenium-demo");

        driver.manage().deleteCookieNamed("assignment-cookie");

        assertThat(driver.manage().getCookieNamed("assignment-cookie")).isNull();
    }
}
