package playwright;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static utils.Utils.sleep;

public class _03Page {

    Playwright playwright;
    Browser browser;

    Page page;

    @BeforeTest
    public void beforeTest(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setChannel("chrome")
                        .setHeadless(false)
        );

        page = browser.newPage();

    }


    @AfterTest
    public void afterTest(){
        sleep(3);
        page.close();
        browser.close();
        playwright.close();
    }


    @Test
    public void testNavigate(){
        page.navigate("https://www.saucedemo.com/");
    }

    @Test
    public void testScreenshot(){
        page.navigate("https://www.saucedemo.com/");

        page.screenshot(
                new Page.ScreenshotOptions()
                        .setPath(Paths.get("screenshots/page.png"))
        );

        page.locator("input[name='login-button']").screenshot(
                new Locator.ScreenshotOptions()
                        .setPath(Paths.get("screenshots/button.png"))
        );

    }

    @Test
    public void testHighlight(){
        page.navigate("https://www.saucedemo.com/");

        page.locator("input[name='login-button']").highlight();
        sleep(5);
    }




}
