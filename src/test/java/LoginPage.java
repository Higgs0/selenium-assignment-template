import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By rememberMeCheckbox = By.cssSelector(".form-check-input");
    private final By signInButton = By.id("log-in");
    private final By logoImage = By.cssSelector("img[src*='logo-big']");

    public LoginPage(WebDriver driver, int timeoutSeconds) {
        super(driver, timeoutSeconds);
    }

    public boolean isOpen() {
        return visible(signInButton).isDisplayed() && visible(logoImage).isDisplayed();
    }

    public LoginPage enterUsername(String username) {
        visible(usernameInput).clear();
        visible(usernameInput).sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        visible(passwordInput).clear();
        visible(passwordInput).sendKeys(password);
        return this;
    }

    public LoginPage enableRememberMe() {
        if (!visible(rememberMeCheckbox).isSelected()) {
            clickable(rememberMeCheckbox).click();
        }
        return this;
    }

    public boolean rememberMeIsSelected() {
        return visible(rememberMeCheckbox).isSelected();
    }

    public DashboardPage submitLogin() {
        clickable(signInButton).click();
        return new DashboardPage(driver, timeoutSeconds);
    }
}
