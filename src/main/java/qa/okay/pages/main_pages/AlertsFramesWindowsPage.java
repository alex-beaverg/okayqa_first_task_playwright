package qa.okay.pages.main_pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import qa.okay.base.BaseElement;
import qa.okay.pages.sub_pages.alerts_frames_windows.AlertsPage;
import qa.okay.pages.sub_pages.alerts_frames_windows.FramesPage;

import static qa.okay.utils.PropertyGetter.getLocator;

public class AlertsFramesWindowsPage extends BaseElement {
    private final Page page;
    private final Locator alertsItem;
    private final Locator framesItem;

    public AlertsFramesWindowsPage(Page page) {
        super(page);
        this.page = page;
        alertsItem = this.page.locator(getLocator("AlertsFramesWindowsPage.alertsItemLocator"));
        framesItem = this.page.locator(getLocator("AlertsFramesWindowsPage.framesItemLocator"));
    }

    @Step(value = "Clicking Sub menu 'Alerts' item step")
    public AlertsPage clickAlertsItem() {
        alertsItem.click();
        return new AlertsPage(page);
    }

    @Step(value = "Clicking Sub menu 'Frames' item step")
    public FramesPage clickFramesItem() {
        framesItem.click();
        return new FramesPage(page);
    }
}
