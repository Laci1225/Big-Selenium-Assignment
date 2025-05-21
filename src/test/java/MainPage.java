import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends PageBase {

    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://budapest.craigslist.org/");
    }

    public void pushProfileIconButton() {
        WebElement mobileMenuButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@id='actionIconsGoHere']" +
                        "//a[contains(@href, 'https://accounts.craigslist.org/login/home')]")
        ));
        mobileMenuButton.click();
    }
}