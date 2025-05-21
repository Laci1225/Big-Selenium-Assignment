import org.openqa.selenium.*;

public class MainPage extends PageBase {

    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://budapest.craigslist.org/");
    }

    public LoginPage pushProfileIconButtonToLogIn() {
        waitAndClick(Locators.PROFILE_ICON);
        return new LoginPage(driver);
    }

    public ProfilePage pushProfileIconButtonToViewProfile() {
        waitAndClick(Locators.PROFILE_ICON);
        return new ProfilePage(driver);
    }
}
