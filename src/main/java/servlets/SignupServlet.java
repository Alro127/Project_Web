package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.TaiKhoanDAO;

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
		
		String destination = "Signup.jsp?error=1";
		
		if (!TaiKhoanDAO.isValidUserNamePassword(username, password, confirmPassword)) {
            // Trả về lỗi nếu thiếu username hoặc password
            request.setAttribute("errorMessage", "Tên đăng nhập và mật khẩu không hợp lệ");
            request.getRequestDispatcher("Signup.jsp").forward(request, response);
            return;
        }
		// Check tồn tại
		//	boolean isRegistered = registerUser(username, password, role);
		boolean isRegistered = true;
		if (TaiKhoanDAO.isExistedAccount(username, password)) {
			// Nếu đăng ký thất bại, hiển thị thông báo lỗi
            //request.setAttribute("errorMessage", "Tên đăng nhập đã tồn tại hoặc có lỗi xảy ra.");
            
        } else {
			/*
			 * if (role == "Employee") response.sendRedirect("ThongTinUngVien.jsp"); else
			 * response.sendRedirect("ThongTinCongTy.jsp");
			 */
        	TaiKhoanDAO.AddAccount(username, password);
        	destination = "Login.jsp?success=1";
        }
		request.getRequestDispatcher(destination).forward(request, response);
		doGet(request, response);
	}

}
