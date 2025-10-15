package tranvantuan.example;

import java.util.logging.Level;
import java.util.logging.Logger;

class CatchGenericExceptionExample {

    private static final Logger LOGGER =
            Logger.getLogger(CatchGenericExceptionExample.class.getName());

    private CatchGenericExceptionExample() {
        throw new AssertionError("No instances allowed");
    }

    static void main(String[] args) {

        String s = resolveInput(args);

        if (s == null || s.isEmpty()) {
            LOGGER.log(Level.WARNING, "Input 's' is null or empty; cannot compute length.");
            return;
        }

        int len = s.length();
        LOGGER.log(Level.INFO, "Length of s = {0}", len);
    }

    private static String resolveInput(String[] args) {
        if (args != null && args.length > 0) {
            return args[0];
        }
        String fromProp = System.getProperty("app.input");
        if (fromProp != null && !fromProp.isEmpty()) {
            return fromProp;
        }
        return System.getenv("APP_INPUT");
    }
}
