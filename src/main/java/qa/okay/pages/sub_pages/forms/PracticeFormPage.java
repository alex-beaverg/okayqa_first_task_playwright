package qa.okay.pages.sub_pages.forms;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import qa.okay.base.BaseElement;
import qa.okay.components.ModalResultTable;
import qa.okay.domain.web.Student;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static qa.okay.utils.PropertyGetter.getLocator;

public class PracticeFormPage extends BaseElement {
    private final Page page;
    private final Locator firstNameInput;
    private final Locator lastNameInput;
    private final Locator emailInput;
    private final List<Locator> genderRadioButtons;
    private final Locator phoneInput;
    private final Locator dateOfBirthInput;
    private final Locator subjectsInput;
    private final List<Locator> hobbyCheckBoxes;
    private final Locator uploadPictureInput;
    private final Locator addressTextArea;
    private final Locator stateInput;
    private final Locator cityInput;
    private final Locator submitButton;

    public PracticeFormPage(Page page) {
        super(page);
        this.page = page;
        firstNameInput = this.page.locator(getLocator("PracticeFormPage.firstNameInputLocator"));
        lastNameInput = this.page.locator(getLocator("PracticeFormPage.lastNameInputLocator"));
        emailInput = this.page.locator(getLocator("PracticeFormPage.emailInputLocator"));
        genderRadioButtons = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            String nth = "nth=" + i;
            genderRadioButtons.add(this.page.locator(getLocator("PracticeFormPage.genderRadioButtonsLocator")).locator(nth));
        }
        phoneInput = this.page.locator(getLocator("PracticeFormPage.phoneInputLocator"));
        dateOfBirthInput = this.page.locator(getLocator("PracticeFormPage.dateOfBirthInputLocator"));
        subjectsInput = this.page.locator(getLocator("PracticeFormPage.subjectsInputLocator"));
        hobbyCheckBoxes = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            String nth = "nth=" + i;
            hobbyCheckBoxes.add(this.page.locator(getLocator("PracticeFormPage.hobbyCheckBoxesLocator")).locator(nth));
        }
        uploadPictureInput = this.page.locator(getLocator("PracticeFormPage.uploadPictureInputLocator"));
        addressTextArea = this.page.locator(getLocator("PracticeFormPage.addressTextAreaLocator"));
        stateInput = this.page.locator(getLocator("PracticeFormPage.stateInputLocator"));
        cityInput = this.page.locator(getLocator("PracticeFormPage.cityInputLocator"));
        submitButton = this.page.locator(getLocator("PracticeFormPage.submitButtonLocator"));
    }

    @Step(value = "Entering first name sub-step")
    private void enterFirstName(String firstName) {
        firstNameInput.fill(firstName);
    }

    @Step(value = "Entering last name sub-step")
    private void enterLastName(String lastName) {
        lastNameInput.fill(lastName);
    }

    @Step(value = "Entering email sub-step")
    private void enterEmail(String email) {
        emailInput.fill(email);
    }

    @Step(value = "Choosing gender radio button sub-step")
    private void clickGenderRadioButton(String gender) {
        for (Locator radio : genderRadioButtons) {
            if (radio.textContent().equals(gender)) {
                radio.click();
            }
        }
    }

    @Step(value = "Entering phone number sub-step")
    private void enterPhone(String phone) {
        phoneInput.fill(phone);
    }

    @Step(value = "Entering date of birth sub-step")
    private void enterDateOfBirth(String dateOfBirth) {
        dateOfBirthInput.selectText();
        dateOfBirthInput.fill(dateOfBirth);
        dateOfBirthInput.press("Escape");
    }

    @Step(value = "Entering subjects sub-step")
    private void enterSubjects(String subjects) {
        scrollPage(subjectsInput);
        List<String> subjectList = Arrays.stream(subjects.split(" ")).collect(Collectors.toList());
        for (String subject : subjectList) {
            subjectsInput.fill(subject);
            subjectsInput.press("Control+Enter");
        }
    }

    @Step(value = "Choosing hobby check boxes sub-step")
    private void clickHobbyCheckBoxes(String hobbies) {
        List<String> hobbyList = Arrays.stream(hobbies.split(" ")).collect(Collectors.toList());
        for (String hobby : hobbyList) {
            for (Locator checkBox : hobbyCheckBoxes) {
                if (checkBox.textContent().equals(hobby)) {
                    checkBox.click();
                }
            }
        }
    }

    @Step(value = "Uploading picture sub-step")
    private void uploadPicture(String path) {
        uploadPictureInput.setInputFiles(Paths.get(path));
    }

    @Step(value = "Entering address sub-step")
    private void enterAddress(String address) {
        addressTextArea.fill(address);
    }

    @Step(value = "Entering state sub-step")
    private void enterState(String state) {
        stateInput.fill(state);
        stateInput.press("Control+Enter");
    }

    @Step(value = "Entering city sub-step")
    private void enterCity(String city) {
        cityInput.fill(city);
        cityInput.press("Control+Enter");
    }

    @Step(value = "Clicking submit button sub-step")
    private void clickSubmitButton() {
        scrollPage(submitButton);
        submitButton.click();
    }

    @Step(value = "Filling in Practice Form step")
    public ModalResultTable fillInForm(Student student) {
        enterFirstName(student.getFirstName());
        enterLastName(student.getLastName());
        enterEmail(student.getEmail());
        clickGenderRadioButton(student.getGender());
        enterPhone(student.getPhone());
        enterDateOfBirth(student.getDateOfBirth());
        enterSubjects(student.getSubjects());
        clickHobbyCheckBoxes(student.getHobbies());
        uploadPicture(student.getPicturePath());
        enterAddress(student.getAddress());
        enterState(student.getState());
        enterCity(student.getCity());
        clickSubmitButton();
        return new ModalResultTable(page);
    }
}
