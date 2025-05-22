package utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    // Hàm mã hóa mật khẩu
    public static String encryptPassword(String plainPassword) {
        // Tạo salt ngẫu nhiên và mã hóa password
        String hashed = BCrypt.hashpw(plainPassword, BCrypt.gensalt(12)); // 12 là "cost" – càng cao càng an toàn (và chậm hơn)
        return hashed;
    }

    // Hàm kiểm tra mật khẩu khi đăng nhập
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

}