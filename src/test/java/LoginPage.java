import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.github.cdimascio.dotenv.Dotenv;


public class LoginPage extends PageBase {
    private static final Dotenv dotenv = Dotenv.load();

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void pushSubmitLoginButton() {
        String usernameEnv = dotenv.get("CRAIGSLIST_USERNAME");
        String passwordEnv = dotenv.get("CRAIGSLIST_PASSWORD");

        if (usernameEnv == null || passwordEnv == null) {
            throw new RuntimeException("Environment variables for login credentials not set.");
        }

        WebElement username = this.driver.findElement(
                By.xpath("//input[@id='inputEmailHandle']")
        );
        username.sendKeys(usernameEnv);
        WebElement password = this.driver.findElement(
                By.xpath("//input[@type='password' and @id='inputPassword']")
        );
        password.sendKeys(passwordEnv);

        WebElement submitLoginButton = this.driver.findElement(
                By.xpath("//button[@id='login' and @type='submit']")
        );
        submitLoginButton.click();
    }
}