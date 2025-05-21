import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.net.MalformedURLException;
import java.util.Arrays;

public class SeleniumTest {

    private WebDriver driver;
    private MainPage mainPage;


    @Before
    public void setUp() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");
        this.driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        this.driver.manage().window().maximize();
        this.mainPage = new MainPage(this.driver);

        LoginPage loginPage = mainPage.pushProfileIconButtonToLogIn();
        loginPage.assertElementVisible(Locators.USERNAME_INPUT);
        loginPage.submitLoginForm();
        loginPage.assertElementVisible(Locators.LOGOUT_LINK);
        loginPage.backToMainPage();
        loginPage.assertElementVisible(Locators.PROFILE_ICON);
    }

    @Test
    public void testMainPageTitleAndLocationPicker() {
        String title = mainPage.getTitle();
        Assert.assertTrue(title.toLowerCase().contains("craigslist"));
        mainPage.assertElementVisible(Locators.LOCATION_PICKER);
        mainPage.assertElementTextContains(Locators.LOCATION_PICKER, "budapest");
    }

    @Test
    public void testUserLogoutFlow() {
        ProfilePage profilePage =  mainPage.pushProfileIconButtonToViewProfile();
        profilePage.waitAndClick(Locators.LOGOUT_LINK);
        profilePage.assertElementVisible(Locators.USERNAME_INPUT);
    }

    @Test
    public void testStaticPages() throws Exception {
        new StaticPage(driver).testAllStaticPages();
    }

    @Test
    public void testBrowserBackNavigationAfterViewingProfile() {
        ProfilePage profilePage = mainPage.pushProfileIconButtonToViewProfile();
        profilePage.assertElementVisible(Locators.LOGOUT_LINK);
        profilePage.navigateBack();
        mainPage.assertElementVisible(Locators.PROFILE_ICON);
    }

    @Test
    public void testJavascriptExecutorScrollAndHighlight() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) mainPage.driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(2000);
        js.executeScript("window.scrollTo(document.body.scrollHeight, 0);");

        WebElement locationPicker = mainPage.driver.findElement(Locators.LOCATION_PICKER);
        js.executeScript("arguments[0].style.border='3px solid red'", locationPicker);

        Assert.assertTrue(locationPicker.isDisplayed());
    }

    @Test
    public void testCreatePostAndReturnToMain() {
        ProfilePage profilePage = mainPage.pushProfileIconButtonToViewProfile();
        profilePage.assertElementVisible(Locators.LOGOUT_LINK);

        CreatePost createPost = profilePage.makeANewPost();

        createPost.assertElementVisible(Locators.FORM_PICKER);
        createPost.selectCommunity("community");

        createPost.assertElementVisible(Locators.FORM_PICKER);
        createPost.selectCategory("groups");

        createPost.assertElementVisible(Locators.POSTING_FORM);
        createPost.fillPostDetails("Free kittens", "We have 4 kittens looking for a family.");
        createPost.clickContinueButton();

        createPost.assertElementVisible(Locators.IMAGE_UPLOADER);
        createPost.uploadImage();
        createPost.assertElementVisible(Locators.IMAGE_BOX);
        createPost.clickDoneWithImage();
        createPost.assertElementVisible(Locators.IMAGE_BOX);
        /*
        PostConfirmationPage postConfirmationPage =  createPost.postAdvertisement();

        postConfirmationPage.assertElementVisible(Locators.PREVIEW_NOTIFICATION);
        postConfirmationPage.backToMainPage();
        */
    }

/*
    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }*/

}
