package playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.Media;
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
                        .setSlowMo(1000)
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

    @Test
    public void videoSave(){

        BrowserContext context = browser.newContext(
                new Browser.NewContextOptions()
                        .setRecordVideoDir(Paths.get("screenshots/"))
                        .setRecordVideoSize(640,480)
        );

        page = context.newPage();
        page.navigate("https://www.saucedemo.com/");

        page.locator("#user-name").fill("standard_user");
        page.locator("//input[@id='password']").fill("secret_sauce");
        page.locator("input[name='login-button']").click();
        page.locator("(//button[.='Add to cart'])[1]").click();
        page.locator("//div[.='Sauce Labs Backpack']").click();
        page.locator("//button[.='Back to products']").click();

    }


    @Test
    public void bringFront() {

        page.navigate("https://www.google.com");

        BrowserContext context = browser.newContext(
                new Browser.NewContextOptions()
                        .setRecordVideoDir(Paths.get("screenshots/"))
                        .setLocale("de-DE")
        );

        Page page2 = context.newPage();
        page2.navigate("https://www.saucedemo.com/");

        Page page1 = context.newPage();
        page1.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        page2.bringToFront();
        page2.locator("#user-name").fill("standard_user");
        page1.bringToFront();
        page1.locator("input[name='username']").fill("Admin");

        page2.bringToFront();
        page2.locator("//input[@id='password']").fill("secret_sauce");
        page1.bringToFront();
        page1.locator("input[name='password']").fill("admin123");

        page2.bringToFront();
        page2.locator("input[name='login-button']").click();
        page1.bringToFront();
        page1.locator("button[type='submit']").click();

        page2.bringToFront();
        page2.locator("//div[.='Sauce Labs Backpack']").click();
        page1.bringToFront();
        page1.locator("//a[contains(.,'Admin')]").click();

    }

    @Test
    public void setLocale() {

        BrowserContext context = browser.newContext(
                new Browser.NewContextOptions()
                        .setLocale("de-DE")
        );

        Page page1 = context.newPage();
        page1.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        page1.bringToFront();
        page1.locator("input[name='username']").fill("Admin");

        page1.bringToFront();
        page1.locator("input[name='password']").fill("admin123");

        page1.bringToFront();
        page1.locator("button[type='submit']").click();

        page1.bringToFront();
        page1.locator("//a[contains(.,'Admin')]").click();

    }


    @Test
    public void saveAsPdf(){
        page.navigate("https://demowebshop.tricentis.com/");

        page.emulateMedia(new Page.EmulateMediaOptions().setMedia(Media.SCREEN));
        page.pdf(
                new Page.PdfOptions()
                        .setPath(Paths.get("screenshots/tricentis.pdf"))
                        .setLandscape(true)
        );

    }


    @Test
    public void viewPortSize(){
        sleep(3);
        page.setViewportSize(700, 600);
        page.navigate("https://demowebshop.tricentis.com/");
        sleep(3);
        page.setViewportSize(1300, 700);


    }


}
