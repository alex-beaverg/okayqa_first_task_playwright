package qa.okay.pages.main_pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import qa.okay.base.BaseElement;
import qa.okay.pages.sub_pages.widgets.ProgressBarPage;
import qa.okay.pages.sub_pages.widgets.SliderPage;

import static qa.okay.utils.PropertyGetter.getLocator;

public class WidgetsPage extends BaseElement {
    private final Page page;
    private final Locator sliderItem;
    private final Locator progressBarItem;

    public WidgetsPage(Page page) {
        super(page);
        this.page = page;
        sliderItem = this.page.locator(getLocator("WidgetsPage.sliderItemLocator"));
        progressBarItem = this.page.locator(getLocator("WidgetsPage.progressBarItemLocator"));
    }

    @Step(value = "Clicking Sub menu 'Slider' item step")
    public SliderPage clickSliderItem() {
        sliderItem.click();
        return new SliderPage(page);
    }

    @Step(value = "Clicking Sub menu 'Progress Bar' item step")
    public ProgressBarPage clickProgressBarItem() {
        progressBarItem.click();
        return new ProgressBarPage(page);
    }
}
