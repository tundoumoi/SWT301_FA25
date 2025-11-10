package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.nio.file.Path;
import java.time.Duration;

/**
 * Shared helpers for the Practice Form page.
 */
public class BasePage5 {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public BasePage5(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void click(By locator) {
        waitForVisibility(locator).click();
    }

    protected void type(By locator, String text) {
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected void pressEnter(By locator) {
        waitForVisibility(locator).sendKeys(Keys.ENTER);
    }

    protected void scrollIntoView(By locator) {
        WebElement element = waitForVisibility(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void setValueByJs(By locator, String value) {
        WebElement element = waitForVisibility(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1];", element, value);
    }

    protected void upload(By locator, Path absolutePath) {
        waitForVisibility(locator).sendKeys(absolutePath.toString());
    }

    protected boolean isDisplayed(By locator) {
        try {
            return waitForVisibility(locator).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}
