package tranvantuan.example;

import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

class UnreachableCodeExample {

    private static final Logger LOGGER = Logger.getLogger(UnreachableCodeExample.class.getName());
    private UnreachableCodeExample() {
        throw new AssertionError("No instances allowed");
    }
    public static int getNumber() {
        return ThreadLocalRandom.current().nextInt(0, 100);
    }
    static void main() {
        LOGGER.log(Level.INFO, "Result = {0}", getNumber());
    }
}
