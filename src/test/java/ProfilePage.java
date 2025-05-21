import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePage extends PageBase {

    private static final By MAKE_NEW_POST = By.xpath("//div[@id='account-homepage']//a[contains(text(), 'make new post') or contains(@href, 'https://post.craigslist.org/c/')]");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public CreatePost makeANewPost() {
        WebElement createPostButton = wait.until(ExpectedConditions.elementToBeClickable(MAKE_NEW_POST));
        createPostButton.click();
        return new CreatePost(driver);
    }

}
