package qa.okay.tests.web.alerts_frames_windows;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import qa.okay.pages.HomePage;
import qa.okay.pages.main_pages.AlertsFramesWindowsPage;
import qa.okay.pages.sub_pages.alerts_frames_windows.AlertsPage;
import qa.okay.tests.web.base.BaseWebTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static qa.okay.utils.PropertyGetter.getData;

@Epic(value = "Demo QA dot com")
@Feature(value = "Web")
@Story(value = "Alerts, Frames & Windows")
public class AlertsTest extends BaseWebTest {

    @Test(description = "Verify Alert message Test")
    @Description(value = "Verify Alert message")
    @Severity(SeverityLevel.NORMAL)
    public void verifyAlertMessageTest() {
        AlertsPage alertsPage = getAlertsPage();
        alertsPage.checkAlertMessage();
    }

    @Test(description = "Verify Alert message after timer (5 sec) Test")
    @Description(value = "Verify Alert message after timer (5 sec)")
    @Severity(SeverityLevel.NORMAL)
    public void verifyTimerAlertMessageTest() {
        AlertsPage alertsPage = getAlertsPage();
        alertsPage.checkTimerAlertMessage();
    }

    @Test(description = "Verify Alert (Confirm) 'OK' message Test")
    @Description(value = "Verify Alert (Confirm) 'OK' message")
    @Severity(SeverityLevel.NORMAL)
    public void verifyConfirmOkMessageTest() {
        AlertsPage alertsPage = getAlertsPage();
        assertThat(alertsPage.getConfirmOkResult()).hasText(getData("Alerts.confirmOkResult"));
    }

    @Test(description = "Verify Alert (Confirm) 'Cancel' message Test")
    @Description(value = "Verify Alert (Confirm) 'Cancel' message")
    @Severity(SeverityLevel.NORMAL)
    public void verifyConfirmCancelMessageTest() {
        AlertsPage alertsPage = getAlertsPage();
        assertThat(alertsPage.getConfirmCancelResult()).hasText(getData("Alerts.confirmCancelResult"));
    }

    @Test(description = "Verify Alert (Prompt) message Test")
    @Description(value = "Verify Alert (Prompt) message")
    @Severity(SeverityLevel.NORMAL)
    public void verifyPromptMessageTest() {
        AlertsPage alertsPage = getAlertsPage();
        assertThat(alertsPage.getPromptResult(getData("Alerts.promptMessage"))).containsText(getData("Alerts.promptMessage"));
    }

    @Step(value = "Getting Alerts Page step")
    private AlertsPage getAlertsPage() {
        HomePage homePage = getHomePage();

        AlertsFramesWindowsPage alertsFramesWindowsPage = homePage.getMainMenu().clickAlertsFramesWindowsItem();
        assertThat(alertsFramesWindowsPage.getMainPagesTextLocator()).hasText(getData("MainPages.text"));

        AlertsPage alertsPage = alertsFramesWindowsPage.clickAlertsItem();
        assertThat(alertsPage.getSubPagesTitleLocator()).hasText(getData("SubPages.Alerts.title"));

        return alertsPage;
    }
}
