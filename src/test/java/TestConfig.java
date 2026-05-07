import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestConfig {
    private final Properties properties = new Properties();

    public TestConfig() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("test.properties")) {
            if (input == null) {
                throw new IllegalStateException("Missing test.properties from test resources");
            }
            properties.load(input);
        } catch (IOException exception) {
            throw new IllegalStateException("Cannot load test configuration", exception);
        }
    }

    public String baseUrl() {
        return getRequired("base.url");
    }

    public String username() {
        return getRequired("username");
    }

    public String password() {
        return getRequired("password");
    }

    public int timeoutSeconds() {
        return Integer.parseInt(getRequired("timeout.seconds"));
    }

    public int windowWidth() {
        return Integer.parseInt(getRequired("window.width"));
    }

    public int windowHeight() {
        return Integer.parseInt(getRequired("window.height"));
    }

    public boolean headless() {
        return Boolean.parseBoolean(System.getProperty("browser.headless", "true"));
    }

    public String browser() {
        return System.getProperty("browser", "chrome");
    }

    private String getRequired(String key) {
        String value = properties.getProperty(key);
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalStateException("Missing config value: " + key);
        }
        return value;
    }
}
