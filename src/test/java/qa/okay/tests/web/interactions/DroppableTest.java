package qa.okay.tests.web.interactions;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import qa.okay.pages.HomePage;
import qa.okay.pages.main_pages.InteractionsPage;
import qa.okay.pages.sub_pages.interactions.DroppablePage;
import qa.okay.tests.web.base.BaseWebTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static qa.okay.utils.PropertyGetter.getData;

@Epic(value = "Demo QA dot com")
@Feature(value = "Web")
@Story(value = "Interactions")
public class DroppableTest extends BaseWebTest {

    @Test(description = "Verify 'Drag and drop' of element operation Test")
    @Description(value = "Verify 'Drag and drop' of element operation")
    @Severity(SeverityLevel.NORMAL)
    public void verifyDragAndDropOperationTest() {
        HomePage homePage = getHomePage();

        InteractionsPage interactionsPage = homePage.getMainMenu().clickInteractionsItem();
        assertThat(interactionsPage.getMainPagesTextLocator()).hasText(getData("MainPages.text"));

        DroppablePage droppablePage = interactionsPage.clickDroppableItem();
        assertThat(droppablePage.getSubPagesTitleLocator()).hasText(getData("SubPages.Droppable.title"));

        droppablePage.dragAndDropElement();
        droppablePage.checkTextFromDropHere();
    }
}
