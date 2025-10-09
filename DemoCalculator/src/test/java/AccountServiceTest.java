import TranVanTuan.example.AccountService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import org.junit.jupiter.api.*;
import java.io.FileWriter;
import java.io.IOException;

public class AccountServiceTest {
    private static AccountService service;
    private static FileWriter writer;

    @BeforeAll
    static void setup() throws IOException {
        service = new AccountService();
        // Ghi header file kết quả
        String path = "../DemoCalculator/src/test/resources/UnitTestResult.csv";
        writer = new FileWriter(path);
        writer.write("username,password,email,expected,actual,result\n");
        System.out.println("📁 File saved to: " + path);
    }

    @AfterAll
    static void tearDown() throws IOException {
        writer.close();
    }

    @ParameterizedTest(name = "Test {index}: {0}, {1}, {2}")
    @CsvFileSource(resources = "test-data.csv", numLinesToSkip = 1)
    void testRegister(String username, String password, String email, boolean expected) throws IOException {
        boolean actual = service.registerAccount(username, password, email);
        String result = (expected == actual) ? "PASS" : "FAIL";

        // Ghi kết quả test vào file
        writer.write(String.format("%s,%s,%s,%s,%s,%s\n",
                username, password, email, expected, actual, result));

        // Kiểm tra kết quả
        Assertions.assertEquals(expected, actual,
                () -> "Failed for: " + username + ", " + password + ", " + email);
    }
}
