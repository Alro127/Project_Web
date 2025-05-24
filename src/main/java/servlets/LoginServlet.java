package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.CSRFTokenManager;
import utils.LogSanitizer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.cj.Session;

import beans.TaiKhoan;
import beans.UngVien;
import dao.CongTyDAO;
import dao.TaiKhoanDAO;
import dao.UngVienDAO;
import beans.CongTy;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);
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
		
		String safeUserName = LogSanitizer.sanitizeForLog(tk.getUsername());
		logger.info("Attempting login for user: {}", safeUserName);
		
		boolean isAuthenticated = TaiKhoanDAO.AuthenticationAccount(tk);
		if (isAuthenticated) {
			int id = TaiKhoanDAO.getID("username", tk.getUsername());
			HttpSession session = request.getSession(true);
			
			TaiKhoan taiKhoan = TaiKhoanDAO.getTaiKhoanById(id);
			session.setAttribute("account", taiKhoan);
			
			if ("UngVien".equals(taiKhoan.getRole())) {
		    	destination = "CongViecServlet";
		    	UngVien uv = UngVienDAO.getUngVienById(id);
		    	session.setAttribute("name", uv.getFullName());
			}
		    else if ("CongTy".equals(taiKhoan.getRole())){
				destination = "TaiKhoanCongTyServlet";
				CongTy ct = CongTyDAO.GetCongTyById(id);
				session.setAttribute("name", ct.getTenCongTy());
			}
		    else {
				destination = "Login.jsp";
			}
			
			List<String> information = TaiKhoanDAO.getInformationForSession(id);
			// Kiểm tra xem list có dữ liệu hay không
			if (information != null && !information.isEmpty()) {
			    // Lưu các thông tin từ List vào session
			    session.setAttribute("id", information.get(0));          // Lưu id
			    session.setAttribute("id_google", information.get(1));    // Lưu id_google
			    session.setAttribute("id_facebook", information.get(2));  // Lưu id_facebook
			    session.setAttribute("access_token", information.get(3)); // Lưu access_token
			    session.setAttribute("refresh_token", information.get(4)); // Lưu refresh_token	
			    session.setAttribute("role", information.get(5));
			    session.setAttribute("email", information.get(6));
					logger.info("User {} logged in as UngVien", safeUserName);
					logger.info("User {} logged in as CongTy", safeUserName);
					logger.warn("Unknown role for user {}: {}", safeUserName);
			}
		}
		else {
			 logger.warn("Authentication failed for user: {}", safeUserName);
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
		doGet(request, response);

	}

}
