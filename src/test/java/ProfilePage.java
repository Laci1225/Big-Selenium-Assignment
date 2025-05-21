import org.openqa.selenium.WebDriver;

public class ProfilePage extends PageBase {


    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public CreatePost makeANewPost() {
        waitAndClick(Locators.NEW_POST_BUTTON);
        return new CreatePost(driver);
    }

}
