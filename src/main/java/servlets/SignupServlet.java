package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class SignupServlet
 */
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirm-password");
		String role = "Company";
		
		if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            // Trả về lỗi nếu thiếu username hoặc password
            request.setAttribute("errorMessage", "Tên đăng nhập và mật khẩu không được để trống.");
            request.getRequestDispatcher("Signup.jsp").forward(request, response);
            return;
        }
		
		if (!password.equals(confirmPassword)) {
            // Trả về lỗi nếu mật khẩu không khớp
            request.setAttribute("errorMessage", "Mật khẩu không khớp.");
            request.getRequestDispatcher("Signup.jsp").forward(request, response);
            return;
        }
		
		// Check tồn tại
		//	boolean isRegistered = registerUser(username, password, role);
		boolean isRegistered = true;
		if (isRegistered) {
            if (role == "Employee")
            	response.sendRedirect("ThongTinUngVien.jsp");
            else
            	response.sendRedirect("ThongTinCongTy.jsp");
        } else {
            // Nếu đăng ký thất bại, hiển thị thông báo lỗi
            request.setAttribute("errorMessage", "Tên đăng nhập đã tồn tại hoặc có lỗi xảy ra.");
            request.getRequestDispatcher("Signup.jsp").forward(request, response);
        }
		doGet(request, response);
	}

}
