import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends PageBase {

    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://budapest.craigslist.org/");
    }

    public LoginPage pushProfileIconButtonToLogIn(By icon) {
        WebElement mobileMenuButton = wait.until(ExpectedConditions.elementToBeClickable(icon));
        mobileMenuButton.click();
        return new LoginPage(driver);
    }

    public ProfilePage pushProfileIconButtonToViewProfile(By icon) {
        WebElement mobileMenuButton = wait.until(ExpectedConditions.elementToBeClickable(icon));
        mobileMenuButton.click();
        return new ProfilePage(driver);
    }
}
