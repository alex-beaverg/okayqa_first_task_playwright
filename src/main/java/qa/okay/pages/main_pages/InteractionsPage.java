package qa.okay.pages.main_pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import qa.okay.base.BaseElement;
import qa.okay.pages.sub_pages.interactions.DroppablePage;

import static qa.okay.utils.PropertyGetter.getLocator;

public class InteractionsPage extends BaseElement {
    private final Page page;
    private final Locator droppableItem;

    public InteractionsPage(Page page) {
        super(page);
        this.page = page;
        droppableItem = this.page.locator(getLocator("InteractionsPage.droppableItemLocator"));
    }

    @Step(value = "Clicking Sub menu 'Droppable' item step")
    public DroppablePage clickDroppableItem() {
        droppableItem.click();
        return new DroppablePage(page);
    }
}
