import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreatePost extends PageBase {

    public CreatePost(WebDriver driver) {
        super(driver);
    }

    public void selectCommunity(String type) {
        waitAndClick(By.xpath(String.format(Locators.COMMUNITY_RADIO_TEMPLATE, type)));
    }

    public void selectCategory(String name) {
        waitAndClick(By.xpath(String.format(Locators.CATEGORY_RADIO_TEMPLATE, name)));
    }

    public void fillPostDetails(String name, String description) {
        waitAndFill(Locators.POST_TITLE_INPUT, name);
        waitAndFill(Locators.POST_BODY_TEXTAREA, description);
    }

    public void clickContinueButton() {
        waitAndClick(Locators.CONTINUE_BUTTON);
    }

    public void uploadImage() {
        waitAndUpload(Locators.IMAGE_UPLOAD_INPUT,"/opt/google/chrome/product_logo_256.png");
    }

    public void clickDoneWithImage() {
        waitAndClick(Locators.DONE_BUTTON);
    }

    public PostConfirmationPage postAdvertisement() {
        waitAndClick(Locators.POST_ADVERTISEMENT);
        return new PostConfirmationPage(driver);

    }
}
