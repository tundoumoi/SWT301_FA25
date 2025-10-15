package tranvantuan.example;

import java.util.logging.Level;
import java.util.logging.Logger;

// Lớp hằng số (thay cho interface Constants)
final class Constants {
    private Constants() { throw new AssertionError("No instances"); }
    static final int MAX_USERS = 100;
}

class InterfaceFieldModificationExample {

    private InterfaceFieldModificationExample() {
        throw new AssertionError("No instances");
    }

    // Dùng chữ ký main chuẩn để tránh cảnh báo khác
    static void main() {
        final Logger logger = Logger.getLogger(InterfaceFieldModificationExample.class.getName());

        // Sử dụng hằng số để hết "Field 'MAX_USERS' is never used"
        int capacity = Constants.MAX_USERS;
        logger.log(Level.INFO, "Max users: {0}", capacity);

    }
}
