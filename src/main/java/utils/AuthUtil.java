package utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import beans.TaiKhoan;

public class AuthUtil {

    // ✅ Kiểm tra đăng nhập
    public static boolean isAuthenticated(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("account") == null) {
            response.sendRedirect("Login.jsp");
            return false;
        }
        return true;
    }

    // ✅ Kiểm tra đúng vai trò
    public static boolean authorizeRole(HttpServletRequest request, HttpServletResponse response, String requiredRole) throws IOException {
        if (!isAuthenticated(request, response)) return false;

        TaiKhoan account = (TaiKhoan) request.getSession(false).getAttribute("account");
        if (!requiredRole.equals(account.getRole())) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }

        return true;
    }

    // ✅ Kiểm tra đúng người đang sở hữu tài nguyên (vd: ?id=...)
    public static boolean authorizeUserById(HttpServletRequest request, HttpServletResponse response, String requiredRole, int targetId) throws IOException {
        if (!authorizeRole(request, response, requiredRole)) return false;

        TaiKhoan account = (TaiKhoan) request.getSession(false).getAttribute("account");
        if (account.getId() != targetId) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN); // Không được phép xem tài nguyên của người khác
            return false;
        }

        return true;
    }
}
