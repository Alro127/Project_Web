package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import utils.AuthUtil;
import utils.CSRFTokenManager;

import java.io.IOException;
import java.io.*;
import java.sql.*;

import beans.TaiKhoan;

@WebServlet("/uploadAvatar")
public class UploadAvatarServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        // Đọc ảnh từ request
        Part filePart = request.getPart("avatar");
        InputStream fileContent = filePart.getInputStream();

        Connection conn = null;
        PreparedStatement pstmt = null;
        
        if (!AuthUtil.authorizeRole(request, response, "UngVien")) return;
        
        HttpSession session = request.getSession(false);
        
        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("account");

        try {
            // Kết nối đến cơ sở dữ liệu
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "username", "password");

            // Lưu ảnh vào database (giả sử IdUV là 1 cho ví dụ)
            String sql = "UPDATE UngVien SET Avatar = ? WHERE IdUV = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setBinaryStream(1, fileContent);
            pstmt.setInt(2, taiKhoan.getId()); 

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
            	//CSRFTokenManager.generateToken(request);
                out.print("{\"success\": true}");
            } else {
                out.print("{\"success\": false, \"message\": \"Không tìm thấy người dùng.\"}");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.print("{\"success\": false, \"message\": \"" + e.getMessage() + "\"}");
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
