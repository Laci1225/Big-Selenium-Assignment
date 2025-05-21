import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageBase {

    protected final WebDriver driver;
    protected final WebDriverWait wait;


    public PageBase(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    protected WebElement waitAndReturnElement(By locator){
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator);
    }

    protected void waitAndClick(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    protected void waitAndFill(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.sendKeys(text);
    }

    protected void waitAndUpload(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        element.sendKeys(text);
    }

    public String getElementText(By locator) {
        WebElement element = waitAndReturnElement(locator);
        return element.getText();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void backToMainPage() {
        waitAndClick(Locators.BACK_TO_MAIN_PAGE_BUTTON);
    }

    public void assertElementTextContains(By locator, String expectedText) {
        String actualText = getElementText(locator);
        Assert.assertTrue(actualText.contains(expectedText));
    }

    public void assertElementVisible(By locator) {
        WebElement element = waitAndReturnElement(locator);
        Assert.assertTrue(element.isDisplayed());
    }
}
