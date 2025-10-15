package tranvantuan.example;

import java.util.logging.Logger;

class SQLInjectionExample {

    private SQLInjectionExample() {
        throw new AssertionError("No instances");
    }

    private static final Logger LOGGER = Logger.getLogger(SQLInjectionExample.class.getName());


    static void main() {
        String userInput = "' OR '1'='1";
        String query = "SELECT * FROM users WHERE username = '" + userInput + "'";
        LOGGER.info(() -> "Executing query: " + query);
    }
}
