package qa.okay.tests.web.base;

import com.microsoft.playwright.*;
import io.qameta.allure.Step;
import org.testng.annotations.*;
import qa.okay.pages.HomePage;
import qa.okay.utils.BrowserService;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class BaseWebTest {
    protected Browser browser;
    protected BrowserContext context;

    @BeforeClass(alwaysRun = true)
    @Step(value = "Browser launching step")
    protected void launchBrowser() {
        browser = BrowserService.getBrowser();
    }

    @BeforeMethod(alwaysRun = true)
    @Step(value = "Context creating step")
    protected void createContext() {
        context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1920, 911));
    }

    @AfterMethod(alwaysRun = true)
    @Step(value = "Context closing step")
    protected void closeContext() {
        context.close();
    }

    @AfterClass(alwaysRun = true)
    @Step(value = "Browser closing step")
    protected void closeBrowser() {
        BrowserService.closeBrowser();
    }

    @Step(value = "Getting Home Page step")
    protected HomePage getHomePage() {
        Page page = context.newPage();
        HomePage homePage = new HomePage(page);
        assertThat(page).hasURL(homePage.getHomeUrl());
        return homePage;
    }
}
