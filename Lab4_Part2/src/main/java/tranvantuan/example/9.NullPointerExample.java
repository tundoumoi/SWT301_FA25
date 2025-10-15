package tranvantuan.example;
import java.util.logging.Level;
import java.util.logging.Logger;

class NullPointerExample {

    private static final Logger LOGGER = Logger.getLogger(NullPointerExample.class.getName());
    private NullPointerExample() {
        throw new AssertionError("No instances allowed");
    }
    static void main() {
        String text = System.getProperty("app.text");

        if (text == null || text.isEmpty()) {
            LOGGER.log(Level.WARNING, "Input 'text' is null or empty; nothing to process.");
            return;
        }

        int length = text.length();
        LOGGER.log(Level.INFO, "Length of text = {0}", length);
    }
}
