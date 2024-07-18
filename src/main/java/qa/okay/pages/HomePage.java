package qa.okay.pages;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import qa.okay.base.BaseElement;
import qa.okay.components.ComponentMainMenu;

import static qa.okay.utils.PropertyGetter.getProperty;

public class HomePage extends BaseElement {
    private final String homeUrl;
    private final Page page;
    private final ComponentMainMenu mainMenu;

    public HomePage(Page page) {
        super(page);
        this.page = page;
        homeUrl = getProperty("home_url");
        this.page.navigate(homeUrl);
        mainMenu = new ComponentMainMenu(this.page);
    }

    @Step(value = "Getting Home page URL step")
    public String getHomeUrl() {
        return homeUrl;
    }

    @Step(value = "Getting Main Menu step")
    public ComponentMainMenu getMainMenu() {
        return mainMenu;
    }
}
