package tranvantuan.example;
import java.util.logging.Level;
import java.util.logging.Logger;
class InterfaceNamingInconsistencyExample {
    private static final Logger LOGGER = Logger.getLogger(InterfaceNamingInconsistencyExample.class.getName());
    static void main() {
        LoginHandler handler = new SimpleLoginHandler();
        boolean success = handler.login("alice", "s3ret");
        LOGGER.log(Level.INFO, "Login result: {0}", success);
    }
    interface LoginHandler {
        boolean login(String username, String password);
    }
    static class SimpleLoginHandler implements LoginHandler {
        @Override
        public boolean login(String username, String password) {
            return username != null && !username.isEmpty()
                    && password != null && !password.isEmpty();
        }
    }
}
