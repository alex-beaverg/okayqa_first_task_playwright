package qa.okay.tests.web.alerts_frames_windows;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import qa.okay.pages.HomePage;
import qa.okay.pages.main_pages.AlertsFramesWindowsPage;
import qa.okay.pages.sub_pages.alerts_frames_windows.AlertsPage;
import qa.okay.pages.sub_pages.alerts_frames_windows.FramesPage;
import qa.okay.tests.web.base.BaseWebTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static qa.okay.utils.PropertyGetter.getData;

@Epic(value = "Demo QA dot com")
@Feature(value = "Web")
@Story(value = "Alerts, Frames & Windows")
public class FramesTest extends BaseWebTest {

    @Test(description = "Compare messages from different frames Test")
    @Description(value = "Compare messages from different frames")
    @Severity(SeverityLevel.NORMAL)
    public void compareMessagesFromDifferentFramesTest() {
        HomePage homePage = getHomePage();

        AlertsFramesWindowsPage alertsFramesWindowsPage = homePage.getMainMenu().clickAlertsFramesWindowsItem();
        assertThat(alertsFramesWindowsPage.getMainPagesTextLocator()).hasText(getData("MainPages.text"));

        FramesPage framesPage = alertsFramesWindowsPage.clickFramesItem();
        assertThat(framesPage.getSubPagesTitleLocator()).hasText(getData("SubPages.Frames.title"));

        framesPage.compareFrameHeaders();
    }
}
