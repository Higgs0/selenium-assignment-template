import java.util.UUID;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LoginFlowTest extends BaseSeleniumTest {

    @Test
    void loginPageShowsExpectedTitleAndFormControls() {
        LoginPage loginPage = openLoginPage();

        assertThat(driver.getTitle()).isEqualTo("ACME Demo App by Applitools");
        assertThat(loginPage.isOpen()).isTrue();
    }

    @Test
    void userCanFillLoginFormAndOpenDashboard() {
        DashboardPage dashboardPage = openLoginPage()
                .enterUsername(config.username())
                .enterPassword(config.password())
                .submitLogin();

        assertThat(dashboardPage.isOpen()).isTrue();
        assertThat(dashboardPage.totalBalance()).contains("$350");
    }

    @Test
    void userCanLoginWithRandomGeneratedCredentials() {
        String randomUsername = "student-" + UUID.randomUUID();
        String randomPassword = "Password-" + UUID.randomUUID();

        DashboardPage dashboardPage = openLoginPage()
                .enterUsername(randomUsername)
                .enterPassword(randomPassword)
                .submitLogin();

        assertThat(dashboardPage.isOpen()).isTrue();
    }

    @Test
    void userCanSelectRememberMeBeforeSigningIn() {
        LoginPage loginPage = openLoginPage()
                .enterUsername(config.username())
                .enterPassword(config.password())
                .enableRememberMe();

        assertThat(loginPage.rememberMeIsSelected()).isTrue();
    }
}
