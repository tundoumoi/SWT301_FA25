package tranvantuan.example;

import java.util.logging.Level;
import java.util.logging.Logger;

class OverarchingExceptionExample {

    private static final Logger LOGGER =
            Logger.getLogger(OverarchingExceptionExample.class.getName());

    private OverarchingExceptionExample() {
        throw new AssertionError("No instances allowed");
    }

    static void main() {
        int[] arr = new int[3];
        arr[0] = 10;
        arr[1] = 20;
        arr[2] = 30;

        String indexProp = System.getProperty("app.index"); // -Dapp.index=1
        Integer index = parseIndex(indexProp);

        if (index == null) {
            LOGGER.log(Level.INFO, "Index is not provided or invalid. Using default index = 0");
        }

        int safeIndex = Math.clamp(index != null ? index : 0, 0, arr.length - 1);

        int value = arr[safeIndex];
        LOGGER.log(Level.INFO, "Value at index {0} = {1}", new Object[]{safeIndex, value});

        int sum = 0;
        for (int v : arr) sum += v;
        LOGGER.log(Level.INFO, "Sum of array = {0}", sum);
    }

    private static Integer parseIndex(String raw) {
        if (raw == null || raw.isEmpty()) {
            return null;
        }
        try {
            return Integer.parseInt(raw.trim());
        } catch (NumberFormatException _) {
            LOGGER.log(Level.FINE, "Invalid index format: \"{0}\"", raw);
            return null;
        }
    }
}
