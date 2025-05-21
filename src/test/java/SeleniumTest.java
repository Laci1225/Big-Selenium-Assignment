import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.net.MalformedURLException;

public class SeleniumTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage mainPage;

    @Before
    public void setUp() throws MalformedURLException{
        ChromeOptions options = new ChromeOptions();
        this.driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        this.driver.manage().window().maximize();
        this.wait = new WebDriverWait(this.driver, 10);
        this.mainPage = new MainPage(this.driver);
        LoginPage loginPage = new LoginPage(this.driver);
        mainPage.pushProfileIconButton();
        mainPage.waitAndReturnElement(By.xpath("//input[@id='inputEmailHandle']"));
        loginPage.pushSubmitLoginButton();
        loginPage.waitAndReturnElement(By.xpath("//a[contains(@href, 'logout')]"));
        loginPage.backToMainPage();
        loginPage.waitAndReturnElement(By.xpath("//div[@id='actionIconsGoHere']" +
                "//a[contains(@href, 'https://accounts.craigslist.org/login/home')]"));
    }

    @Test
    public void testReadTitle() {
        String title = mainPage.getTitle();
        Assert.assertTrue(title.toLowerCase().contains("craigslist"));
        mainPage.assertElementVisible(By.xpath("//a[contains(@class, 'cl-location-picker-link')]"));
        mainPage.assertElementTextContains(By.xpath("//a[contains(@class, 'cl-location-picker-link')]"), "budapest");
    }

    @Test
    public void testSelenium() {
        mainPage.pushProfileIconButton();
        mainPage.waitAndReturnElement(
                By.xpath("//input[@id='inputEmailHandle']")
        );

        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.pushSubmitLoginButton();
        loginPage.waitAndReturnElement(
                By.xpath("//a[contains(@href, 'logout')]")
        );
    }

    @Test
    public void testLogout() {
        mainPage.pushProfileIconButton();
        WebElement logoutButton = driver.findElement(By.xpath("//a[contains(@href, 'logout')]"));
        logoutButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@id='inputEmailHandle']")
        ));
    }

    @Test
    public void testBrowserBackNavigation() {
        mainPage.pushProfileIconButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, 'logout')]")));

        driver.navigate().back();

        WebElement mainIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='actionIconsGoHere']")
        ));

        Assert.assertTrue(mainIcon.isDisplayed());
    }

    @Test
    public void testJavascriptExecutorScrollAndHighlight() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(2000);
        js.executeScript("window.scrollTo(document.body.scrollHeight, 0);");
        WebElement locationPicker = driver.findElement(By.xpath("//a[contains(@class, 'cl-location-picker-link')]"));
        js.executeScript("arguments[0].style.border='3px solid red'", locationPicker);

        Assert.assertTrue(locationPicker.isDisplayed());
    }

    @Test
    public void testCreatePostAndReturnToMain() {
        mainPage.pushProfileIconButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, 'logout')]")));

        CreatePost createPost = new CreatePost(this.driver);
        createPost.makeANewPost();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//form[contains(@class, 'picker')]")));
        createPost.selectCommunity("community");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//form[contains(@class, 'picker')]")));
        createPost.selectCategory("groups");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("postingForm")));
        createPost.fillPostDetails("Free kittens", "We have 4 kittens looking for a family.");
        createPost.clickContinueButton();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@id, 'uploader')]")));
        createPost.uploadImage();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//figure[contains(@class, 'imgbox')]")));
        createPost.clickDoneWithImage();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(@class, 'preview-notification')]")));
        createPost.backToMainPage();
    }
/*
    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }*/
}