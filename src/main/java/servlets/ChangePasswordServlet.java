package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.CSRFTokenManager;

import java.io.IOException;
import java.io.PrintWriter;

import dao.TaiKhoanDAO;

public class ChangePasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy các tham số từ form
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String username = request.getParameter("username"); // Giả sử bạn truyền username từ frontend.

        // Gọi phương thức trong TaiKhoanDAO để cập nhật mật khẩu
        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();

        // Kiểm tra mật khẩu cũ và cập nhật mật khẩu mới
        boolean isUpdated = taiKhoanDAO.updateTaiKhoan(username, oldPassword, newPassword);

        // Trả về kết quả cho client (Dưới đây là một ví dụ trả về JSON)
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        if (isUpdated) {
        	//CSRFTokenManager.generateToken(request);
            out.write("{\"status\":\"success\", \"message\":\"Mật khẩu đã được thay đổi thành công!\"}");
        } else {
            out.write("{\"status\":\"error\", \"message\":\"Mật khẩu cũ không đúng!\"}");
        }
    }
}
