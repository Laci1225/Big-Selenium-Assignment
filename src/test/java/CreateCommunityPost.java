import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreateCommunityPost extends PageBase {

    public CreateCommunityPost(WebDriver driver) {
        super(driver);
    }

    public void makeANewPost() {
        WebElement createPostButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@id='account-homepage']//a[contains(text(), 'make new post') or contains(@href, 'https://post.craigslist.org/c/')]")
        ));
        createPostButton.click();
    }

    public void selectCommunity(String type) {
        WebElement selectRadio = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[contains(text(), '"+type+"')]/input[@type='radio']")
        ));
        selectRadio.click();
    }
}
