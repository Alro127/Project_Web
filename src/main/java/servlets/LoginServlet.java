package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");

        if ("logout".equals(action)) {
            // Xóa thông tin đăng nhập khỏi session
            HttpSession session = request.getSession(false); // Lấy session hiện tại, không tạo mới
            if (session != null) {
                session.invalidate(); // Hủy session
            }

            // Chuyển hướng về trang login
            response.sendRedirect("Login.jsp");
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// Giả lập kiểm tra thông tin đăng nhập (ở đây là hardcode, thay bằng DB nếu cần)
		if ("admin".equals(username) && "12345".equals(password)) {
			// Nếu thông tin hợp lệ, lưu thông tin vào session
			HttpSession session = request.getSession();
			session.setAttribute("user", username); // Lưu username (hoặc đối tượng user thực sự)

			request.getRequestDispatcher("CongViecServlet").forward(request, response);
		} else {
			// Nếu thông tin không hợp lệ, trả về trang login với thông báo lỗi
			request.setAttribute("errorMessage", "Invalid username or password");
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
	}

}
