package qa.okay.tests.web.widgets;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import qa.okay.pages.HomePage;
import qa.okay.pages.main_pages.WidgetsPage;
import qa.okay.pages.sub_pages.widgets.ProgressBarPage;
import qa.okay.tests.web.base.BaseWebTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static qa.okay.utils.PropertyGetter.getData;

@Epic(value = "Demo QA dot com")
@Feature(value = "Web")
@Story(value = "Widgets")
public class ProgressBarTest extends BaseWebTest {

    @Test(description = "Verify completing and reset Progress Bar actions Test")
    @Description(value = "Verify completing and reset Progress Bar actions")
    @Severity(SeverityLevel.NORMAL)
    public void verifyCompletingAndResetProgressBarActionsTest() {
        ProgressBarPage progressBarPage = getProgressBarPage();
        progressBarPage.clickStartStopButton();
        progressBarPage.checkProgressBarComplete();
        progressBarPage.clickResetButton();
        progressBarPage.checkProgressBarReset();
    }

    @Step(value = "Getting Progress Bar Page step")
    private ProgressBarPage getProgressBarPage() {
        HomePage homePage = getHomePage();

        WidgetsPage widgetsPage = homePage.getMainMenu().clickWidgetsItem();
        assertThat(widgetsPage.getMainPagesTextLocator()).hasText(getData("MainPages.text"));

        ProgressBarPage progressBarPage = widgetsPage.clickProgressBarItem();
        assertThat(progressBarPage.getSubPagesTitleLocator()).hasText(getData("SubPages.ProgressBar.title"));

        return progressBarPage;
    }
}
