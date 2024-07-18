package qa.okay.utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

public class BrowserService {
    private static Playwright playwright;
    private static Browser browser;

    private BrowserService() { }

    public static Browser getBrowser() {
        if (browser == null) {
            playwright = Playwright.create();
            String browserName = System.getProperty("browser");
            if (browserName == null) {
                browserName = PropertyGetter.getProperty("browser");
            }
            BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions().setHeadless(false);
            if (browserName.equals("firefox")) {
                browser = playwright.firefox().launch(launchOptions);
            } else if (browserName.equals("edge")) {
                browser = playwright.chromium().launch(launchOptions.setChannel("msedge"));
            } else {
                browser = playwright.chromium().launch(launchOptions);
            }
        }
        return browser;
    }

    public static void closeBrowser() {
        browser.close();
        browser = null;
        playwright.close();
    }
}
