package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import utils.DriverFactory5;

/**
 * Base test wiring for Exercise 5.
 */
public abstract class BaseTest5 {

    protected static WebDriver driver;

    @BeforeAll
    static void setUpBase() {
        driver = DriverFactory5.createDriver();
    }

    @AfterAll
    static void tearDownBase() {
        if (driver != null) {
            driver.quit();
        }
    }
}
