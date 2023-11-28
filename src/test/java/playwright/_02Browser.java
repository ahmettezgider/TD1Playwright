package playwright;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;
import utils.Utils;

import static utils.Utils.sleep;

public class _02Browser {

    @Test
    public void testHeadless(){

        Playwright playwright = Playwright.create();

        // Browser browser = playwright.webkit().launch();            // webkit, safary secilir
        // Browser browser = playwright.firefox().launch();            // firefox secilir
        Browser browser = playwright.chromium().launch(             // chromium tabanli browser seciliyor
                new BrowserType.LaunchOptions()
                        .setHeadless(false)             // headless = false, default: headless=true
        );

        Page page = browser.newPage();

        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        sleep(10);
        page.close();

        browser.close();

        playwright.close();

    }

    @Test
    public void testChannel(){

        Playwright playwright = Playwright.create();

        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setChannel("chrome")           // broser türü belirlenir: chrome, msedge, chrome-beta, msedge-beta or msedge-dev.
                        //.setChannel("msedge")
        );

        Page page = browser.newPage();

        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        sleep(10);
        page.close();

        browser.close();

        playwright.close();

    }


    @Test
    public void testSlowMotion(){

        Playwright playwright = Playwright.create();

        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setChannel("chrome")
                        .setSlowMo(2000)
        );

        Page page = browser.newPage();

        page.navigate("https://www.saucedemo.com/");

        page.locator("#user-name").fill("standard_user");
        page.locator("//input[@id='password']").fill("secret_sauce");
        page.locator("input[name='login-button']").click();
        page.locator("(//button[.='Add to cart'])[1]").click();
        page.locator("//div[.='Sauce Labs Backpack']").click();
        page.locator("//button[.='Back to products']").click();

        sleep(5);
        page.close();

        browser.close();

        playwright.close();

    }


    @Test
    public void browserMulti(){

        Playwright playwright = Playwright.create();

        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setChannel("chrome")
                        .setSlowMo(500)
        );

        Browser browser1 = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setChannel("msedge")
                        .setSlowMo(500)
        );

        Page page = browser.newPage();
        Page page1 = browser1.newPage();

        page.navigate("https://www.saucedemo.com/");

        page.locator("#user-name").fill("standard_user");
        page.locator("//input[@id='password']").fill("secret_sauce");
        page.locator("input[name='login-button']").click();
        page.locator("(//button[.='Add to cart'])[1]").click();
        page.locator("//div[.='Sauce Labs Backpack']").click();
        page.locator("//button[.='Back to products']").click();

        page1.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        page1.locator("input[name='username']").fill("Admin");
        page1.locator("input[name='password']").fill("admin123");
        page1.locator("//button[contains(.,'Login')]").click();
        page1.locator("//a[contains(.,'Admin')]").click();

        page1.close();
        browser1.close();
        page.close();
        browser.close();
        playwright.close();
    }


    @Test
    public void testBrowserContent(){
        Playwright playwright = Playwright.create();

        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setChannel("chrome")
        );

        BrowserContext context = browser.newContext();

        Page page = context.newPage();

        page.navigate("https://www.saucedemo.com/");
        page.locator("#user-name").fill("standard_user");
        page.locator("//input[@id='password']").fill("secret_sauce");
        page.locator("input[name='login-button']").click();
        browser.close();
        playwright.close();
    }


}
