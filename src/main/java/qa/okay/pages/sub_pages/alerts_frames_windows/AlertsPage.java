package qa.okay.pages.sub_pages.alerts_frames_windows;

import com.microsoft.playwright.Dialog;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import org.testng.Assert;
import qa.okay.base.BaseElement;

import static qa.okay.utils.PropertyGetter.getData;
import static qa.okay.utils.PropertyGetter.getLocator;

public class AlertsPage extends BaseElement {
    private final Page page;
    private final Locator alertButton;
    private final Locator timerAlertButton;
    private final Locator confirmButton;
    private final Locator promptButton;
    private final Locator confirmResult;
    private final Locator promptResult;

    public AlertsPage(Page page) {
        super(page);
        this.page = page;
        alertButton = this.page.locator(getLocator("AlertsPage.alertButtonLocator"));
        timerAlertButton = this.page.locator(getLocator("AlertsPage.timerAlertButtonLocator"));
        confirmButton = this.page.locator(getLocator("AlertsPage.confirmButtonLocator"));
        promptButton = this.page.locator(getLocator("AlertsPage.promptButtonLocator"));
        confirmResult = this.page.locator(getLocator("AlertsPage.confirmResultLocator"));
        promptResult = this.page.locator(getLocator("AlertsPage.promptResultLocator"));
    }

    @Step(value = "Checking 'Alert' message step")
    public void checkAlertMessage() {
        page.onDialog(dialog -> {
            Assert.assertEquals(dialog.message(), getData("Alerts.alertMessage"), "Messages aren't equal!");
            dialog.accept();
        });
        alertButton.click();
    }

    @Step(value = "Checking 'Alert with delay' message step")
    public void checkTimerAlertMessage() {
        page.onDialog(dialog -> {
            Assert.assertEquals(dialog.message(), getData("Alerts.timerAlertMessage"), "Messages aren't equal!");
            dialog.accept();
        });
        timerAlertButton.click();
        page.waitForTimeout(5500);
    }

    @Step(value = "Getting 'Alert Confirm' OK result step")
    public Locator getConfirmOkResult() {
        page.onDialog(Dialog::accept);
        confirmButton.click();
        return confirmResult;
    }

    @Step(value = "Getting 'Alert Confirm' Cancel result step")
    public Locator getConfirmCancelResult() {
        page.onDialog(Dialog::dismiss);
        confirmButton.click();
        return confirmResult;
    }

    @Step(value = "Getting 'Alert Prompt' element step")
    public Locator getPromptResult(String message) {
        page.onDialog(dialog -> dialog.accept(message));
        promptButton.click();
        return promptResult;
    }
}
