import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.github.cdimascio.dotenv.Dotenv;

public class LoginPage extends PageBase {

    private static final Dotenv dotenv = Dotenv.load();

    private static final By USERNAME_INPUT = By.xpath("//input[@id='inputEmailHandle']");
    private static final By PASSWORD_INPUT = By.xpath("//input[@type='password' and @id='inputPassword']");
    private static final By SUBMIT_LOGIN_BUTTON = By.xpath("//button[@id='login' and @type='submit']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void pushSubmitLoginButton() {
        String usernameEnv = dotenv.get("CRAIGSLIST_USERNAME");
        String passwordEnv = dotenv.get("CRAIGSLIST_PASSWORD");

        if (usernameEnv == null || passwordEnv == null) {
            throw new RuntimeException("Environment variables for login credentials not set.");
        }

        WebElement username = driver.findElement(USERNAME_INPUT);
        username.sendKeys(usernameEnv);

        WebElement password = driver.findElement(PASSWORD_INPUT);
        password.sendKeys(passwordEnv);

        WebElement submitLoginButton = driver.findElement(SUBMIT_LOGIN_BUTTON);
        submitLoginButton.click();
    }
}
