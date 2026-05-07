import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends BasePage {
    private final By financialOverview = By.xpath("//h6[contains(., 'Financial Overview')]");
    private final By recentTransactions = By.xpath("//h6[contains(., 'Recent Transactions')]");
    private final By totalBalance = By.xpath("//*[normalize-space()='Total Balance']/following::*[contains(normalize-space(), '$350')][1]");
    private final By payNowButton = By.xpath("//*[self::a or self::button][contains(@class, 'btn') and normalize-space()='Pay Now']");
    private final By transactionRows = By.xpath("//table//tr[td]");

    public DashboardPage(WebDriver driver, int timeoutSeconds) {
        super(driver, timeoutSeconds);
    }

    public boolean isOpen() {
        wait.until(ExpectedConditions.urlContains("app.html"));
        return visible(financialOverview).isDisplayed();
    }

    public String totalBalance() {
        return textOf(totalBalance);
    }

    public int visibleTransactionCount() {
        scrollIntoView(recentTransactions);
        return driver.findElements(transactionRows).size();
    }

    public boolean hasKnownTransactionMerchant(String merchantName) {
        scrollIntoView(recentTransactions);
        return visible(By.xpath("//*[contains(normalize-space(), '" + merchantName + "')]")).isDisplayed();
    }

    public boolean hasRecentTransactionsSection() {
        scrollIntoView(recentTransactions);
        return visible(recentTransactions).isDisplayed();
    }

    public boolean hasPayNowAction() {
        return visible(payNowButton).isDisplayed();
    }
}
