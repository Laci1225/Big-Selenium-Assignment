import org.openqa.selenium.By;

public class Locators {
    public static final By USERNAME_INPUT = By.xpath("//input[@id='inputEmailHandle']");
    public static final By LOGOUT_LINK = By.xpath("//a[contains(@href, 'logout')]");
    public static final By PROFILE_ICON = By.xpath("//div[@id='actionIconsGoHere']//a[contains(@href, 'https://accounts.craigslist.org/login/home')]");
    public static final By LOCATION_PICKER = By.xpath("//a[contains(@class, 'cl-location-picker-link')]");
    public static final By FORM_PICKER = By.xpath("//form[contains(@class, 'picker')]");
    public static final By POSTING_FORM = By.id("postingForm");
    public static final By IMAGE_UPLOADER = By.xpath("//div[contains(@id, 'uploader')]");
    public static final By IMAGE_BOX = By.xpath("//figure[contains(@class, 'imgbox')]");
    public static final By PREVIEW_NOTIFICATION = By.xpath("//h1[contains(@class, 'preview-notification')]");

    public static final String COMMUNITY_RADIO_TEMPLATE = "//label[contains(., '%s')]/input[@type='radio']";
    public static final String CATEGORY_RADIO_TEMPLATE = "//span[@class='option-label' and contains(text(), '%s')]/ancestor::label[@class='radio-option']/input";
    public static final By POST_TITLE_INPUT = By.xpath("//input[@id='PostingTitle']");
    public static final By POST_BODY_TEXTAREA = By.xpath("//textarea[@id='PostingBody']");
    public static final By CONTINUE_BUTTON = By.xpath("//button[@type='submit' and @name='go' and @value='continue']");
    public static final By IMAGE_UPLOAD_INPUT = By.xpath("//input[@type='file' and @multiple]");
    public static final By DONE_BUTTON = By.xpath("//button[@type='submit' and @name='go' and @class='done']");
    public static final By POST_ADVERTISEMENT = By.xpath("//button[@type='submit' and @name='go' and @class='button' and @value='Continue']");


    public static final By PASSWORD_INPUT = By.xpath("//input[@type='password' and @id='inputPassword']");
    public static final By SUBMIT_LOGIN_BUTTON = By.xpath("//button[@id='login' and @type='submit']");

    public static final By BACK_TO_MAIN_PAGE_BUTTON = By.xpath("//a[@href='https://www.craigslist.org']");

    public static final By NEW_POST_BUTTON = By.xpath("//div[@id='account-homepage']//a[contains(text(), 'make new post') or contains(@href, 'https://post.craigslist.org/c/')]");

}