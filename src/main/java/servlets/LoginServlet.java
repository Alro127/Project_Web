package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.mysql.cj.Session;

import beans.TaiKhoan;
import dao.TaiKhoanDAO;

import beans.CongTy;
import beans.User;

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
		String destination = "Login.jsp?error=1";
		TaiKhoan tk = new TaiKhoan();
		tk.setUsername(request.getParameter("username"));
		tk.setPassword(request.getParameter("password"));
		boolean isAuthenticated = TaiKhoanDAO.AuthenticationAccount(tk);
		if (isAuthenticated) {
			int id = TaiKhoanDAO.getID(tk.getUsername());
			HttpSession session = request.getSession(true);
			List<String> information = TaiKhoanDAO.getInformationForSession(id);
			// Kiểm tra xem list có dữ liệu hay không
			if (information != null && !information.isEmpty()) {
			    // Lưu các thông tin từ List vào session
			    session.setAttribute("id", information.get(0));          // Lưu id
			    session.setAttribute("id_google", information.get(1));    // Lưu id_google
			    session.setAttribute("id_facebook", information.get(2));  // Lưu id_facebook
			    session.setAttribute("access_token", information.get(3)); // Lưu access_token
			    session.setAttribute("refresh_token", information.get(4)); // Lưu refresh_token				
			}
			destination = "TrangGioiThieu.jsp";
		}
		response.sendRedirect(destination);
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
			
			// Lấy id từ username và password
			
			// Lấy thực thể từ id, nếu là công ty thì đẫn tới trang quản lý, nếu là ứng viên thì ở trang giới thiệu
			
			// Nếu thông tin hợp lệ, lưu thông tin vào session
			HttpSession session = request.getSession();
			session.setAttribute("user", username); 
			User.Id = 1;

			//Giả sử id đang là công ty
			request.getRequestDispatcher("CongViecServlet").forward(request, response);
			
			//request.getRequestDispatcher("CongViecServlet").forward(request, response);
		} else {
			// Nếu thông tin không hợp lệ, trả về trang login với thông báo lỗi
			request.setAttribute("errorMessage", "Invalid username or password");
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
	}

}
