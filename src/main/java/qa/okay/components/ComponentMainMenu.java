package qa.okay.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import qa.okay.base.BaseElement;
import qa.okay.pages.main_pages.AlertsFramesWindowsPage;
import qa.okay.pages.main_pages.FormsPage;
import qa.okay.pages.main_pages.InteractionsPage;
import qa.okay.pages.main_pages.WidgetsPage;

import static qa.okay.utils.PropertyGetter.getLocator;

public class ComponentMainMenu extends BaseElement {
    private final Page page;
    private final Locator formsItem;
    private final Locator alertsFramesWindowsItem;
    private final Locator widgetsItem;
    private final Locator interactionsItem;

    public ComponentMainMenu(Page page) {
        super(page);
        this.page = page;
        formsItem = page.locator(getLocator("MainMenu.formsItemLocator"));
        alertsFramesWindowsItem = page.locator(getLocator("MainMenu.alertsFramesWindowsItemLocator"));
        widgetsItem = page.locator(getLocator("MainMenu.widgetsItemLocator"));
        interactionsItem = page.locator(getLocator("MainMenu.interactionsItemLocator"));
    }

    @Step(value = "Clicking Main menu 'Forms' item step")
    public FormsPage clickFormsItem() {
        formsItem.click();
        return new FormsPage(page);
    }

    @Step(value = "Clicking Main menu 'Alerts, Frames & Windows' item step")
    public AlertsFramesWindowsPage clickAlertsFramesWindowsItem() {
        alertsFramesWindowsItem.click();
        return new AlertsFramesWindowsPage(page);
    }

    @Step(value = "Clicking Main menu 'Widgets' item step")
    public WidgetsPage clickWidgetsItem() {
        widgetsItem.click();
        return new WidgetsPage(page);
    }

    @Step(value = "Clicking Main menu 'Interactions' item step")
    public InteractionsPage clickInteractionsItem() {
        interactionsItem.click();
        return new InteractionsPage(page);
    }
}
