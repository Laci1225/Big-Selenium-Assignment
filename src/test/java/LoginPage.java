import org.openqa.selenium.WebDriver;
import io.github.cdimascio.dotenv.Dotenv;

public class LoginPage extends PageBase {

    private static final Dotenv envConfig = Dotenv.load();

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void submitLoginForm() {
        String usernameEnv = envConfig.get("CRAIGSLIST_USERNAME");
        String passwordEnv = envConfig.get("CRAIGSLIST_PASSWORD");

        if (usernameEnv == null || passwordEnv == null) {
            throw new RuntimeException("Environment variables for login credentials not set.");
        }

        waitAndFill(Locators.USERNAME_INPUT, usernameEnv);
        waitAndFill(Locators.PASSWORD_INPUT, passwordEnv);
        waitAndClick(Locators.SUBMIT_LOGIN_BUTTON);
    }
}
