package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.nio.file.Path;
import java.util.List;

/**
 * Page Object for https://demoqa.com/automation-practice-form.
 */
public class PracticeFormPage5 extends BasePage5 {

    private static final String URL = "https://demoqa.com/automation-practice-form";

    private final By firstName = By.id("firstName");
    private final By lastName = By.id("lastName");
    private final By userEmail = By.id("userEmail");
    private final By phone = By.id("userNumber");
    private final By dobInput = By.id("dateOfBirthInput");
    private final By subjectsInput = By.id("subjectsInput");
    private final By address = By.id("currentAddress");
    private final By upload = By.id("uploadPicture");
    private final By stateDropdown = By.id("state");
    private final By cityDropdown = By.id("city");
    private final By submitBtn = By.id("submit");
    private final By thanksModal = By.id("example-modal-sizes-title-lg");
    private final By modalRows = By.cssSelector(".table-responsive tbody tr");


    public PracticeFormPage5(WebDriver driver) {
        super(driver);
    }

    protected void safeClick(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }


    public PracticeFormPage5 open() {
        driver.get(URL);
        closeAdsIfPresent();
        return this;
    }

    public PracticeFormPage5 enterName(String first, String last) {
        type(firstName, first);
        type(lastName, last);
        return this;
    }

    public PracticeFormPage5 enterEmail(String email) {
        type(userEmail, email);
        return this;
    }

    public PracticeFormPage5 chooseGender(String genderLabel) {
        safeClick(By.xpath("//label[text()='" + genderLabel + "']"));
        return this;
    }

    public PracticeFormPage5 enterPhone(String value) {
        type(phone, value);
        return this;
    }

    public PracticeFormPage5 setBirthDate(String formattedDate) {
        WebElement input = waitForVisibility(dobInput);
        input.sendKeys(Keys.CONTROL + "a");
        input.sendKeys(formattedDate);
        input.sendKeys(Keys.ENTER);
        return this;
    }

    public PracticeFormPage5 addSubject(String subject) {
        type(subjectsInput, subject);
        pressEnter(subjectsInput);
        return this;
    }

    public PracticeFormPage5 selectHobby(String hobbyLabel) {
        scrollIntoView(By.xpath("//label[text()='" + hobbyLabel + "']"));
        safeClick(By.xpath("//label[text()='" + hobbyLabel + "']"));
        return this;
    }

    public PracticeFormPage5 uploadPicture(Path file) {
        upload(upload, file);
        return this;
    }

    public PracticeFormPage5 enterAddress(String value) {
        type(address, value);
        return this;
    }

    public PracticeFormPage5 selectState(String stateName) {
        scrollIntoView(stateDropdown);
        safeClick(stateDropdown);
        safeClick(By.xpath("//div[text()='" + stateName + "']"));
        return this;
    }

    public PracticeFormPage5 selectCity(String cityName) {
        safeClick(cityDropdown);
        safeClick(By.xpath("//div[text()='" + cityName + "']"));
        return this;
    }

    public PracticeFormPage5 submit() {
        scrollIntoView(submitBtn);
        safeClick(submitBtn);
        return this;
    }

    public boolean isSuccessModalVisible() {
        return isDisplayed(thanksModal);
    }

    public String getModalValue(String label) {
        return driver.findElement(By.xpath("//td[text()='" + label + "']/following-sibling::td")).getText();
    }

    public int getModalRowCount() {
        List<WebElement> rows = driver.findElements(modalRows);
        return rows.size();
    }

    private void closeAdsIfPresent() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "document.querySelectorAll('#fixedban, footer, .Advertisement-728x90').forEach(e => e.style.display='none');"
        );
    }


}
