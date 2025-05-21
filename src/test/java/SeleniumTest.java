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

    @Before
    public void SetUp() throws MalformedURLException{
        ChromeOptions options = new ChromeOptions();
        this.driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        this.driver.manage().window().maximize();
        this.wait = new WebDriverWait(this.driver, 10);
    }

    @Test
    public void testSelenium(){
        MainPage mainPage = new MainPage(this.driver);
        mainPage.pushLoginButton();
        mainPage.waitAndReturnElement(
                By.xpath("//input[@id='inputEmailHandle']")
        );

        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.pushSubmitLoginButton();
        loginPage.waitAndReturnElement(
                By.xpath("//a[contains(@href, 'logout')]")
        );
        /*WebElement logOutButton = this.driver.findElement(
                By.xpath("//a[contains(@href, 'logout')]")
        );
        logOutButton.click();
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@id='inputEmailHandle']")
        ));
        LoginPage loginPage1 = new LoginPage(this.driver);
        loginPage1.pushSubmitLoginButton();*/

        CreateCommunityPost createCommunityPost = new CreateCommunityPost(this.driver);
        createCommunityPost.makeANewPost();

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//form[contains(@class, 'picker')]")
        ));
        createCommunityPost.selectCommunity("community");

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//form[contains(@class, 'picker')]")
        ));
        createCommunityPost.selectCategory("groups");

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.id("postingForm")
        ));
        createCommunityPost.fillPostDetails(
                "Free kittens",
                "We have 4 kittens looking for a family."
        );
        createCommunityPost.clickContinueButton();

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@id, 'uploader')]")
        ));
        createCommunityPost.uploadImage();
    }
/*
    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }*/
}