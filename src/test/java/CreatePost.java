import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreatePost extends PageBase {

    private static final String COMMUNITY_RADIO_TEMPLATE = "//label[contains(., '%s')]/input[@type='radio']";
    private static final String CATEGORY_RADIO_TEMPLATE = "//span[@class='option-label' and contains(text(), '%s')]/ancestor::label[@class='radio-option']/input";
    private static final By POST_TITLE_INPUT = By.xpath("//input[@id='PostingTitle']");
    private static final By POST_BODY_TEXTAREA = By.xpath("//textarea[@id='PostingBody']");
    private static final By CONTINUE_BUTTON = By.xpath("//button[@type='submit' and @name='go' and @value='continue']");
    private static final By IMAGE_UPLOAD_INPUT = By.xpath("//input[@type='file' and @multiple]");
    private static final By DONE_BUTTON = By.xpath("//button[@type='submit' and @name='go' and @class='done']");

    public CreatePost(WebDriver driver) {
        super(driver);
    }

    public void selectCommunity(String type) {
        By communityRadio = By.xpath(String.format(COMMUNITY_RADIO_TEMPLATE, type));
        WebElement selectRadio = wait.until(ExpectedConditions.elementToBeClickable(communityRadio));
        selectRadio.click();
    }

    public void selectCategory(String name) {
        By categoryRadio = By.xpath(String.format(CATEGORY_RADIO_TEMPLATE, name));
        WebElement selectRadio = wait.until(ExpectedConditions.elementToBeClickable(categoryRadio));
        selectRadio.click();
    }

    public void fillPostDetails(String name, String description) {
        WebElement titleInput = wait.until(ExpectedConditions.visibilityOfElementLocated(POST_TITLE_INPUT));
        titleInput.sendKeys(name);

        WebElement descriptionTextarea = wait.until(ExpectedConditions.visibilityOfElementLocated(POST_BODY_TEXTAREA));
        descriptionTextarea.sendKeys(description);
    }

    public void clickContinueButton() {
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(CONTINUE_BUTTON));
        continueButton.click();
    }

    public void uploadImage() {
        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(IMAGE_UPLOAD_INPUT));
        fileInput.sendKeys("/opt/google/chrome/product_logo_256.png");
    }

    public void clickDoneWithImage() {
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(DONE_BUTTON));
        continueButton.click();
    }
}
