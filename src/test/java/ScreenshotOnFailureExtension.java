import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotOnFailureExtension implements AfterTestExecutionCallback {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    public static void setDriver(WebDriver driver) {
        DRIVER.set(driver);
    }

    public static void clearDriver() {
        DRIVER.remove();
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws IOException {
        if (!context.getExecutionException().isPresent() || DRIVER.get() == null) {
            return;
        }

        File screenshot = ((TakesScreenshot) DRIVER.get()).getScreenshotAs(OutputType.FILE);
        Path targetDirectory = Paths.get("screenshots");
        Files.createDirectories(targetDirectory);
        Files.copy(screenshot.toPath(), targetDirectory.resolve(context.getRequiredTestMethod().getName() + ".png"));
    }
}
