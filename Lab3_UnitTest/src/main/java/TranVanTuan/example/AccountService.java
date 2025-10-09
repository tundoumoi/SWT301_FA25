package TranVanTuan.example;

import java.util.Set;
import java.util.regex.Pattern;

public class AccountService {

    // ===== Regex cho các trường =====
    private static final String USERNAME_REGEX = "^[a-zA-Z][a-zA-Z0-9._-]{2,19}$";
    private static final String PASSWORD_REGEX =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
    private static final String EMAIL_REGEX =
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    private static final Set<String> RESERVED_USERNAMES = Set.of(
            "admin", "administrator", "root", "system", "guest"
    );

    // ===== Phương thức kiểm tra username =====
    public boolean isValidUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            System.out.println("❌ Username không được để trống.");
            return false;
        }
        if (!Pattern.matches(USERNAME_REGEX, username)) {
            System.out.println("❌ Username phải bắt đầu bằng chữ cái và dài 3–20 ký tự (chỉ gồm chữ, số, ., _, -).");
            return false;
        }
        if (RESERVED_USERNAMES.contains(username.toLowerCase())) {
            return false;
        }
        return true;
    }

    // ===== Phương thức kiểm tra password =====
    public boolean isValidPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            System.out.println("❌ Password không được để trống.");
            return false;
        }
        if (!Pattern.matches(PASSWORD_REGEX, password)) {
            System.out.println("❌ Password phải có ít nhất 8 ký tự, bao gồm chữ hoa, chữ thường, số và ký tự đặc biệt.");
            return false;
        }
        return true;
    }

    // ===== Phương thức kiểm tra email =====
    public boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            System.out.println("❌ Email không được để trống.");
            return false;
        }
        if (!Pattern.matches(EMAIL_REGEX, email)) {
            System.out.println("❌ Email không đúng định dạng.");
            return false;
        }
        return true;
    }

    // ===== Phương thức đăng ký =====
    public boolean registerAccount(String username, String password, String email) {
        System.out.println("=== Kiểm tra đăng ký tài khoản ===");
        boolean validUsername = isValidUsername(username);
        boolean validPassword = isValidPassword(password);
        boolean validEmail = isValidEmail(email);

        if (validUsername && validPassword && validEmail) {
            System.out.println("✅ Đăng ký thành công cho user: " + username);
            return true;
        } else {
            System.out.println("❌ Đăng ký thất bại. Vui lòng kiểm tra lại thông tin.");
            return false;
        }
    }
}

