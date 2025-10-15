package tranvantuan.example;

import java.util.logging.Logger;

class HardcodedCredentialsExample {

    private HardcodedCredentialsExample() {
        throw new AssertionError("No instances");
    }

    private static final Logger LOGGER = Logger.getLogger(HardcodedCredentialsExample.class.getName());

    static void main() {
        String username = "admin";
        String password = "123456"; // hardcoded password
        if (authenticate(username, password)) {
            LOGGER.info("Access granted");
        } else {
            LOGGER.warning("Access denied");
        }
    }

    private static boolean authenticate(String user, String pass) {
        // Dummy authentication logic
        return user.equals("admin") && pass.equals("123456");
    }
}
