package qa.okay.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import qa.okay.base.BaseElement;

import java.util.ArrayList;
import java.util.List;

import static qa.okay.utils.PropertyGetter.getLocator;

public class ModalResultTable extends BaseElement {
    private final Page page;
    private final List<Locator> results;

    public ModalResultTable(Page page) {
        super(page);
        this.page = page;
        results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String nth = "nth=" + i;
            results.add(this.page.locator(getLocator("ModalResultTable.resultsLocator")).locator(nth));
        }
    }

    @Step(value = "Getting Practice Form result table step")
    public List<Locator> getResults() {
        return results;
    }
}
