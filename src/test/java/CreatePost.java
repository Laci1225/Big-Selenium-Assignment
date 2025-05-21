import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreatePost extends PageBase {

    public CreatePost(WebDriver driver) {
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
                By.xpath("//label[contains(., '"+type+"')]/input[@type='radio']")
        ));
        selectRadio.click();
    }

    public void selectCategory(String name) {
        WebElement selectRadio = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@class='option-label' and contains(text(), '" + name + "')]/ancestor::label[@class='radio-option']/input")
        ));
        selectRadio.click();
    }

    public void fillPostDetails(String name, String description) {
        WebElement titleInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@id='PostingTitle']")
        ));
        titleInput.sendKeys(name);

        WebElement descriptionTextarea = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//textarea[@id='PostingBody']")
        ));
        descriptionTextarea.sendKeys(description);
    }

    public void clickContinueButton() {
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@type='submit' and @name='go' and @value='continue']")
        ));
        continueButton.click();
    }

    public void uploadImage() {
        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//input[@type='file' and @multiple]")
        ));
        fileInput.sendKeys("/opt/google/chrome/product_logo_256.png");
    }

    public void clickDoneWithImage() {
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@type='submit' and @name='go' and @class='done']")
        ));
        continueButton.click();
    }
}
