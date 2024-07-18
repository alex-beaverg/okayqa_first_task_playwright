package qa.okay.tests.web.forms;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import qa.okay.components.ModalResultTable;
import qa.okay.domain.web.Student;
import qa.okay.tests.web.base.BaseWebTest;
import qa.okay.pages.HomePage;
import qa.okay.pages.main_pages.FormsPage;
import qa.okay.pages.sub_pages.forms.PracticeFormPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static qa.okay.services.StudentService.createStudentFromData;
import static qa.okay.services.StudentService.createStudentFromResultTable;
import static qa.okay.utils.PropertyGetter.getData;

@Epic(value = "Demo QA dot com")
@Feature(value = "Web")
@Story(value = "Forms")
public class PracticeFormTest extends BaseWebTest {

    @Test(description = "Verify entering to the Practice Form data Test")
    @Description(value = "Verify entering to the Practice Form data")
    @Severity(SeverityLevel.NORMAL)
    public void verifyPracticeFormTest() {
        HomePage homePage = getHomePage();
        FormsPage formsPage = homePage.getMainMenu().clickFormsItem();
        assertThat(formsPage.getMainPagesTextLocator()).hasText(getData("MainPages.text"));

        PracticeFormPage practiceFormPage = formsPage.clickPracticeFormItem();
        assertThat(practiceFormPage.getSubPagesTitleLocator()).hasText(getData("SubPages.PracticeForm.title"));

        Student studentFromData = createStudentFromData();
        ModalResultTable resultTable = practiceFormPage.fillInForm(studentFromData);
        Student studentFromResultTable = createStudentFromResultTable(resultTable);

        // Using TestNG Assertions to compare objects:
        SoftAssert sa = new SoftAssert();
        sa.assertEquals(studentFromData, studentFromResultTable, "Students aren't equal!");
        sa.assertTrue(studentFromData.getPicturePath().contains(studentFromResultTable.getPicturePath()), "Students' Pictures aren't equal!");
        sa.assertAll();
    }
}
