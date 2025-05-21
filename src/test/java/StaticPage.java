import org.junit.Test;
import org.openqa.selenium.By;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class StaticPage extends PageBase {

    public StaticPage(WebDriver driver) {
        super(driver);
    }
    private static final List<PageToTest> PAGES = Arrays.asList(
            new PageToTest("https://budapest.craigslist.org/about", By.xpath("//span[contains(text(), 'about craigslist')]")),
            new PageToTest("https://budapest.craigslist.org/search/jobs", By.xpath("//span[contains(text(), 'posted today')]")),
            new PageToTest("https://budapest.craigslist.org/sitemap.html", By.xpath("//a[contains(text(), 'budapest homepage')]"))
    );


    @Test
    public void testAllStaticPages(){
        for (PageToTest page : PAGES) {
            driver.get(page.url);
            assertElementVisible(page.expectedElement);
        }
    }

    private static class PageToTest {
        String url;
        By expectedElement;

        PageToTest(String url, By expectedElement) {
            this.url = url;
            this.expectedElement = expectedElement;
        }
    }
}
