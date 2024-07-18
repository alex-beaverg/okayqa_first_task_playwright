package qa.okay.pages.main_pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import qa.okay.base.BaseElement;
import qa.okay.pages.sub_pages.forms.PracticeFormPage;

import static qa.okay.utils.PropertyGetter.getLocator;

public class FormsPage extends BaseElement {
    private final Page page;
    private final Locator practiceFormItem;

    public FormsPage(Page page) {
        super(page);
        this.page = page;
        practiceFormItem = this.page.locator(getLocator("FormsPage.practiceFormItemLocator"));
    }

    @Step(value = "Clicking Sub menu 'Practice Form' item step")
    public PracticeFormPage clickPracticeFormItem() {
        practiceFormItem.click();
        return new PracticeFormPage(page);
    }
}
