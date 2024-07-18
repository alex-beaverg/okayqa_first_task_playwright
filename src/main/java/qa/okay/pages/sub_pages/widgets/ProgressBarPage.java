package qa.okay.pages.sub_pages.widgets;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import qa.okay.base.BaseElement;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static qa.okay.utils.PropertyGetter.getLocator;

public class ProgressBarPage extends BaseElement {
    private final Page page;
    private final Locator startStopButton;
    private final Locator resetButton;
    private final Locator progressBarMin;
    private final Locator progressBarMax;

    public ProgressBarPage(Page page) {
        super(page);
        this.page = page;
        startStopButton = this.page.locator(getLocator("ProgressBarPage.startStopButtonLocator"));
        resetButton = this.page.locator(getLocator("ProgressBarPage.resetButtonLocator"));
        progressBarMin = this.page.locator(getLocator("ProgressBarPage.progressBarMinLocator"));
        progressBarMax = this.page.locator(getLocator("ProgressBarPage.progressBarMaxLocator"));
    }

    @Step(value = "Clicking 'Start progress bar' button step")
    public void clickStartStopButton() {
        startStopButton.click();
    }

    @Step(value = "Clicking 'Reset progress bar' button step")
    public void clickResetButton() {
        resetButton.click();
    }

    @Step(value = "Checking completing of Progress Bar action step")
    public void checkProgressBarComplete() {
        page.waitForTimeout(10500);
        assertThat(progressBarMax).hasAttribute("aria-valuenow", "100");
    }

    @Step(value = "Checking reset of Progress Bar action step")
    public void checkProgressBarReset() {
        assertThat(progressBarMin).hasAttribute("aria-valuenow", "0");
    }
}
