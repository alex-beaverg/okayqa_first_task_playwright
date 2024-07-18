package qa.okay.base;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

import static qa.okay.utils.PropertyGetter.getLocator;

public class BaseElement {
    private final Page page;
    private final Locator mainPagesText;
    private final Locator subPagesTitle;

    public BaseElement(Page page) {
        this.page = page;
        mainPagesText = this.page.locator(getLocator("MainPages.textLocator"));
        subPagesTitle = this.page.locator(getLocator("SubPages.titleLocator"));
    }

    public Locator getMainPagesTextLocator() {
        return mainPagesText;
    }

    public Locator getSubPagesTitleLocator() {
        return subPagesTitle;
    }

    @Step(value = "Scrolling to element")
    protected void scrollPage(Locator locator) {
        locator.evaluate("e => e.scrollTop += 100");
    }
}
