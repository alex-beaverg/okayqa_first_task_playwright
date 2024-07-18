package qa.okay.pages.sub_pages.alerts_frames_windows;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import qa.okay.base.BaseElement;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static qa.okay.utils.PropertyGetter.getLocator;

public class FramesPage extends BaseElement {
    private final Page page;
    private final Locator frame1Header;
    private final Locator frame2Header;

    public FramesPage(Page page) {
        super(page);
        this.page = page;
        String frameHeaderLocator = getLocator("FramesPage.frameHeaderLocator");
        frame1Header = this.page.frameLocator(getLocator("FramesPage.frame1Locator")).locator(frameHeaderLocator);
        frame2Header = this.page.frameLocator(getLocator("FramesPage.frame2Locator")).locator(frameHeaderLocator);
    }

    @Step(value = "Compare frame headers step")
    public void compareFrameHeaders() {
        assertThat(frame1Header).hasText(frame2Header.textContent());
    }
}
