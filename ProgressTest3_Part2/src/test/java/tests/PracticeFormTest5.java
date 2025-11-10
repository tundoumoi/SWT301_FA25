package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import pages.PracticeFormPage5;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for the DemoQA Practice Form.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PracticeFormTest5 extends BaseTest5 {

    private static PracticeFormPage5 formPage;

    @BeforeAll
    static void setUpPage() {
        formPage = new PracticeFormPage5(driver);
    }

    @Test
    @Order(1)
    @DisplayName("Happy path – submit full form successfully")
    void shouldSubmitFormSuccessfully() {
        formPage.open()
                .enterName("John", "Doe")
                .enterEmail("john.doe@example.com")
                .chooseGender("Male")
                .enterPhone("0987654321")
                .setBirthDate("15 May 1995")
                .addSubject("Maths")
                .addSubject("English")
                .selectHobby("Sports")
                .selectHobby("Reading")
                .uploadPicture(Path.of("src/test/resources/avatar.png").toAbsolutePath())
                .enterAddress("123 Nguyen Trai, HCMC")
                .selectState("NCR")
                .selectCity("Delhi")
                .submit();

        assertTrue(formPage.isSuccessModalVisible(), "Modal should appear after successful submit");
        assertEquals("John Doe", formPage.getModalValue("Student Name"));
        assertEquals("john.doe@example.com", formPage.getModalValue("Student Email"));
        assertEquals("Male", formPage.getModalValue("Gender"));
        assertEquals("0987654321", formPage.getModalValue("Mobile"));
        assertEquals("15 May,1995", formPage.getModalValue("Date of Birth"));
    }

    @ParameterizedTest(name = "Invalid data #{index}: {0} / {1}")
    @Order(2)
    @CsvSource({
        "john@example, 0123456789",
        "john.doe@example.com, 12345",
        "john.doe@example.com, 09876"   // thiếu 5 chữ số -> không submit
    })
    void shouldBlockInvalidEmailOrPhone(String email, String phone) {
        formPage.open()
                .enterName("Jane", "Doe")
                .enterEmail(email)
                .chooseGender("Female")
                .enterPhone(phone)
                .submit();

        assertFalse(formPage.isSuccessModalVisible(), "Modal must not appear for invalid data");
    }

    @Test
    @Order(3)
    @DisplayName("Mandatory validation – no fields filled")
    void shouldRequireMandatoryFields() {
        formPage.open().submit();
        assertFalse(formPage.isSuccessModalVisible());
    }

    @ParameterizedTest(name = "CSV row #{index}: {0} {1}")
    @CsvFileSource(resources = "/practice-form-data5.csv", numLinesToSkip = 1)
    void shouldSubmitFormWithCsvData(String firstName,
                                     String lastName,
                                     String email,
                                     String gender,
                                     String phone,
                                     String birthDate,
                                     String subjects,
                                     String hobbies,
                                     String state,
                                     String city,
                                     String address,
                                     String picturePath) {

        formPage.open()
                .enterName(firstName, lastName)
                .enterEmail(email)
                .chooseGender(gender)
                .enterPhone(phone)
                .setBirthDate(birthDate);

        for (String subject : subjects.split("\\|")) {
            formPage.addSubject(subject.trim());
        }
        for (String hobby : hobbies.split("\\|")) {
            formPage.selectHobby(hobby.trim());
        }

        formPage.uploadPicture(Path.of(picturePath).toAbsolutePath())
                .enterAddress(address)
                .selectState(state)
                .selectCity(city)
                .submit();

        assertTrue(formPage.isSuccessModalVisible());
        assertEquals(firstName + " " + lastName, formPage.getModalValue("Student Name"));
        assertEquals(email, formPage.getModalValue("Student Email"));
    }

}
