import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import TranVanTuan.example.Calculator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {

    static Calculator calculator;

    @BeforeAll
    static void initAll() {
        calculator = new Calculator();
    }

    @AfterAll
    static void cleanupAll() {
        calculator = null;
    }

    //Calculator calculator = new Calculator();
    @DisplayName("Kiểm tra phép cộng với hai số dương")
    @Test
    void testAddition() {
        assertEquals(5, calculator.add(2, 3), "Addition should return 5");
    }


    @Test
    void testDivine() {
        assertEquals(2, new Calculator().divide(6, 3));
    }

    @Test
    void devineByZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Calculator().divide(10, 0);
        });
        assertEquals("Division by zero is not allowed.", exception.getMessage());
    }

    @ParameterizedTest(name = "test {index} => {0} * {1} = {2}")
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void testMultiply(int a, int b, int expected) {
        int result = new Calculator().multiply(a, b);
        assertEquals(expected, result,() -> a + " + "+ b +" should be " + expected);
    }

}
