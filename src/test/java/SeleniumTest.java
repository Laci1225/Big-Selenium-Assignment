import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.net.MalformedURLException;

public class SeleniumTest {

    private static final By USERNAME_INPUT = By.xpath("//input[@id='inputEmailHandle']");
    private static final By LOGOUT_LINK = By.xpath("//a[contains(@href, 'logout')]");
    private static final By PROFILE_ICON = By.xpath("//div[@id='actionIconsGoHere']//a[contains(@href, 'https://accounts.craigslist.org/login/home')]");
    private static final By LOCATION_PICKER = By.xpath("//a[contains(@class, 'cl-location-picker-link')]");
    private static final By FORM_PICKER = By.xpath("//form[contains(@class, 'picker')]");
    private static final By POSTING_FORM = By.id("postingForm");
    private static final By IMAGE_UPLOADER = By.xpath("//div[contains(@id, 'uploader')]");
    private static final By IMAGE_BOX = By.xpath("//figure[contains(@class, 'imgbox')]");
    private static final By PREVIEW_NOTIFICATION = By.xpath("//h1[contains(@class, 'preview-notification')]");

    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage mainPage;

    @Before
    public void setUp() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        this.driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        this.driver.manage().window().maximize();
        this.wait = new WebDriverWait(this.driver, 10);
        this.mainPage = new MainPage(this.driver);
        LoginPage loginPage = new LoginPage(this.driver);
        mainPage.pushProfileIconButton(PROFILE_ICON);
        mainPage.waitAndReturnElement(USERNAME_INPUT);
        loginPage.pushSubmitLoginButton();
        loginPage.waitAndReturnElement(LOGOUT_LINK);
        loginPage.backToMainPage();
        loginPage.waitAndReturnElement(PROFILE_ICON);
    }

    @Test
    public void testReadTitle() {
        String title = mainPage.getTitle();
        Assert.assertTrue(title.toLowerCase().contains("craigslist"));
        mainPage.assertElementVisible(LOCATION_PICKER);
        mainPage.assertElementTextContains(LOCATION_PICKER, "budapest");
    }

    @Test
    public void testLogout() {
        mainPage.pushProfileIconButton(PROFILE_ICON);
        WebElement logoutButton = driver.findElement(LOGOUT_LINK);
        logoutButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME_INPUT));
    }

    @Test
    public void testBrowserBackNavigation() {
        mainPage.pushProfileIconButton(PROFILE_ICON);
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGOUT_LINK));

        driver.navigate().back();

        WebElement profileIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(PROFILE_ICON));
        Assert.assertTrue(profileIcon.isDisplayed());
    }

    @Test
    public void testJavascriptExecutorScrollAndHighlight() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(2000);
        js.executeScript("window.scrollTo(document.body.scrollHeight, 0);");

        WebElement locationPicker = driver.findElement(LOCATION_PICKER);
        js.executeScript("arguments[0].style.border='3px solid red'", locationPicker);

        Assert.assertTrue(locationPicker.isDisplayed());
    }

    @Test
    public void testCreatePostAndReturnToMain() {
        mainPage.pushProfileIconButton(PROFILE_ICON);
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGOUT_LINK));

        CreatePost createPost = new CreatePost(this.driver);
        createPost.makeANewPost();

        wait.until(ExpectedConditions.presenceOfElementLocated(FORM_PICKER));
        createPost.selectCommunity("community");

        wait.until(ExpectedConditions.presenceOfElementLocated(FORM_PICKER));
        createPost.selectCategory("groups");

        wait.until(ExpectedConditions.presenceOfElementLocated(POSTING_FORM));
        createPost.fillPostDetails("Free kittens", "We have 4 kittens looking for a family.");
        createPost.clickContinueButton();

        wait.until(ExpectedConditions.presenceOfElementLocated(IMAGE_UPLOADER));
        createPost.uploadImage();
        wait.until(ExpectedConditions.presenceOfElementLocated(IMAGE_BOX));
        createPost.clickDoneWithImage();

        wait.until(ExpectedConditions.presenceOfElementLocated(PREVIEW_NOTIFICATION));
        createPost.backToMainPage();
    }

    /*
    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
    */
}
