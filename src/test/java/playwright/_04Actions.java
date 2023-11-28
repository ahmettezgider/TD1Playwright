package playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static utils.Utils.sleep;

public class _04Actions {

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
    public void dragDrop(){

        page.navigate("https://demoqa.com/droppable");
        sleep(2);

        String source = "#draggable";
        String target = "#droppable";
        page.dragAndDrop(source, target);
        sleep(3);

        page.reload();
        page.dragAndDrop(source, target,
                new Page.DragAndDropOptions()
                        .setTargetPosition(70, 10)
        );
        sleep(3);

        page.reload();
        page.dragAndDrop(source, target,
                new Page.DragAndDropOptions()
                        .setTargetPosition(70, 100)
        );
        sleep(3);





    }
}
