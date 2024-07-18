package qa.okay.pages.sub_pages.widgets;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import qa.okay.base.BaseElement;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static qa.okay.utils.PropertyGetter.getData;
import static qa.okay.utils.PropertyGetter.getLocator;

public class SliderPage extends BaseElement {
    private final Page page;
    private final Locator slider;
    private final Locator sliderValue;

    public SliderPage(Page page) {
        super(page);
        this.page = page;
        slider = this.page.locator(getLocator("SliderPage.sliderLocator"));
        sliderValue = this.page.locator(getLocator("SliderPage.sliderValueLocator"));
    }

    @Step(value = "Moving Slider to max position step")
    public void moveSliderToMax() {
        slider.hover();
        page.mouse().down();
        page.mouse().move(1500, 0);
        page.mouse().up();
    }

    @Step(value = "Moving Slider to min position step")
    public void moveSliderToMin() {
        slider.hover();
        page.mouse().down();
        page.mouse().move(0, 0);
        page.mouse().up();
    }

    @Step(value = "Checking Slider max value from text input step")
    public void checkSliderMaxValue() {
        assertThat(sliderValue).hasAttribute("value", getData("Slider.maxValue"));
    }

    @Step(value = "Checking Slider min value from text input step")
    public void checkSliderMinValue() {
        assertThat(sliderValue).hasAttribute("value", getData("Slider.minValue"));
    }
}
