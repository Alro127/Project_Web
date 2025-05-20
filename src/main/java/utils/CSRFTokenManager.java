package utils;

import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;

public class CSRFTokenManager {
    public static void generateToken(HttpServletRequest request) {
        String token = UUID.randomUUID().toString();
        request.getSession().setAttribute("csrfToken", token);
    }
}
