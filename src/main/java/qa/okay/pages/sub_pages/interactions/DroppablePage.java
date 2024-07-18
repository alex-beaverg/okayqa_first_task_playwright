package qa.okay.pages.sub_pages.interactions;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import qa.okay.base.BaseElement;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static qa.okay.utils.PropertyGetter.*;

public class DroppablePage extends BaseElement {
    private final Page page;
    private final Locator dragMeElement;
    private final Locator dropHereElement;
    private final Locator dropHereElementText;

    public DroppablePage(Page page) {
        super(page);
        this.page = page;
        dragMeElement = this.page.locator(getLocator("DroppablePage.dragMeElementLocator"));
        dropHereElement = this.page.locator(getLocator("DroppablePage.dropHereElementLocator"));
        dropHereElementText = this.page.locator(getLocator("DroppablePage.dropHereElementTextLocator"));
    }

    @Step(value = "Dragging and dropping element step")
    public void dragAndDropElement() {
        dragMeElement.dragTo(dropHereElement);
    }

    @Step(value = "Checking text from 'Drop Here' element step")
    public void checkTextFromDropHere() {
        assertThat(dropHereElementText).hasText(getData("Droppable.text"));
    }
}
