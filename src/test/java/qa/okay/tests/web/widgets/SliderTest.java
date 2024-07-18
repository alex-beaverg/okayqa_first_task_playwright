package qa.okay.tests.web.widgets;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import qa.okay.pages.HomePage;
import qa.okay.pages.main_pages.WidgetsPage;
import qa.okay.pages.sub_pages.widgets.SliderPage;
import qa.okay.tests.web.base.BaseWebTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static qa.okay.utils.PropertyGetter.getData;

@Epic(value = "Demo QA dot com")
@Feature(value = "Web")
@Story(value = "Widgets")
public class SliderTest extends BaseWebTest {

    @Test(description = "Verify moving Slider to max position Test")
    @Description(value = "Verify moving Slider to max position")
    @Severity(SeverityLevel.NORMAL)
    public void verifyMovingSliderToMaxTest() {
        SliderPage sliderPage = getSliderPage();
        sliderPage.moveSliderToMax();
        sliderPage.checkSliderMaxValue();
    }

    @Test(description = "Verify moving Slider to min position Test")
    @Description(value = "Verify moving Slider to min position")
    @Severity(SeverityLevel.NORMAL)
    public void verifyMovingSliderToMinTest() {
        SliderPage sliderPage = getSliderPage();
        sliderPage.moveSliderToMin();
        sliderPage.checkSliderMinValue();
    }

    @Step(value = "Getting Slider Page step")
    private SliderPage getSliderPage() {
        HomePage homePage = getHomePage();

        WidgetsPage widgetsPage = homePage.getMainMenu().clickWidgetsItem();
        assertThat(widgetsPage.getMainPagesTextLocator()).hasText(getData("MainPages.text"));

        SliderPage sliderPage = widgetsPage.clickSliderItem();
        assertThat(sliderPage.getSubPagesTitleLocator()).hasText(getData("SubPages.Slider.title"));

        return sliderPage;
    }
}
