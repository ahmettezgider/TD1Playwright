package playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static utils.Utils.sleep;

public class _05Locators {
    Playwright playwright;
    Browser browser;
    Page page;

    String urlOrangeHRM = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

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
    public void test1(){
        page.navigate(urlOrangeHRM);
        //Locator username = page.locator("input[name='username']");
        //username.fill("Admin");

        Locator username = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("username"));
        username.focus();
        username.fill("Admin");

        Locator password = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("password"));
        password.fill("admin123");

        Locator submit = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"));
        submit.click();

        Locator admin = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Admin"));

        // testng
        Assert.assertTrue(admin.isVisible());

        // playwright
        assertThat(admin).isVisible();

        //Locator pim = page.getByText("PIM");
        Locator pim = page.getByText("PIM", new Page.GetByTextOptions().setExact(true));
        pim.hover();
        sleep(3);
        pim.click();

    }


    @Test
    public void testFocus(){
        page.navigate(urlOrangeHRM);
        Locator username = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("username"));
        Locator password = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("password"));

        username.focus();
        sleep(2);

        password.focus();
        sleep(2);

        username.focus();
        sleep(2);

        password.focus();
        sleep(2);

    }







}
