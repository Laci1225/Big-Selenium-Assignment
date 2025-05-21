import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageBase {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public PageBase(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    protected WebElement waitAndReturnElement(By locator){
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator);
    }

    public String getElementText(By locator) {
        WebElement element = waitAndReturnElement(locator);
        return element.getText();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void backToMainPage() {
        WebElement backToMainPageButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href='https://www.craigslist.org']")
        ));
        backToMainPageButton.click();
    }

    public void assertElementTextContains(By locator, String expectedText) {
        String actualText = getElementText(locator);
        Assert.assertTrue(
                "Expected text to contain: " + expectedText + ", but got: " + actualText,
                actualText.contains(expectedText)
        );
    }

    public void assertElementVisible(By locator) {
        WebElement element = waitAndReturnElement(locator);
        Assert.assertTrue(
                "Expected element to be displayed: " + locator.toString(),
                element.isDisplayed())
        ;
    }
}