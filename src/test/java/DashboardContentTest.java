import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DashboardContentTest extends BaseSeleniumTest {

    @Test
    void dashboardShowsRecentTransactionsAndPaymentActionAfterLogin() {
        DashboardPage dashboardPage = loginToDashboard();

        assertThat(dashboardPage.hasRecentTransactionsSection()).isTrue();
        assertThat(dashboardPage.visibleTransactionCount()).isGreaterThanOrEqualTo(5);
        assertThat(dashboardPage.hasKnownTransactionMerchant("Starbucks")).isTrue();
        assertThat(dashboardPage.hasPayNowAction()).isTrue();
    }

    @Test
    void importantApplicationChecksCanRunFromPageDataList() {
        DashboardPage dashboardPage = loginToDashboard();
        List<String> checks = Arrays.asList(
                "title",
                "dashboard",
                "balance",
                "transactions",
                "merchant"
        );

        for (String check : checks) {
            if ("title".equals(check)) {
                assertThat(driver.getTitle()).startsWith("ACME");
            } else if ("dashboard".equals(check)) {
                assertThat(dashboardPage.isOpen()).isTrue();
            } else if ("balance".equals(check)) {
                assertThat(dashboardPage.totalBalance()).contains("$350");
            } else if ("transactions".equals(check)) {
                assertThat(dashboardPage.visibleTransactionCount()).isGreaterThanOrEqualTo(5);
            } else if ("merchant".equals(check)) {
                assertThat(dashboardPage.hasKnownTransactionMerchant("Starbucks")).isTrue();
            }
        }
    }

    @Test
    void browserHistoryCanReturnFromDashboardToLoginPage() {
        DashboardPage dashboardPage = loginToDashboard();
        assertThat(dashboardPage.isOpen()).isTrue();

        driver.navigate().back();

        assertThat(driver.getTitle()).isEqualTo("ACME Demo App by Applitools");
    }

    private DashboardPage loginToDashboard() {
        return openLoginPage()
                .enterUsername(config.username())
                .enterPassword(config.password())
                .submitLogin();
    }
}
