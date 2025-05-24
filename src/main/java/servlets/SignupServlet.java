package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.LogSanitizer;
import utils.PasswordUtil;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.graph.SuccessorsFunction;

import beans.TaiKhoan;

import dao.CongTyDAO;
import dao.TaiKhoanDAO;
import dao.UngVienDAO;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet(urlPatterns = {"/signup/ungvien", "/signup/congty"})
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(SignupServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignupServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		 	HttpSession session = request.getSession(true);
		 	String destination = "????";
	        String uri = request.getRequestURI();
	        String role;
	        if (uri.endsWith("ungvien"))
	        {
	        	session.setAttribute("role", "UngVien");
	        	destination = "/Signup.jsp";
	        }
	        else {
				if (uri.endsWith("congty")) {
					session.setAttribute("role", "CongTy");
					destination = "/Signup.jsp";
				}
			}
	        response.sendRedirect(request.getContextPath() + destination);

	    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session.getAttribute("role") == null) {
			 LOGGER.error("Attempted signup without a valid session or role. User IP: {}", request.getRemoteAddr());
	         response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid session or role. Please start over.");
	         return;  // Dừng lại, không tiếp tục xử lý
		}
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirm-password");
		String email = request.getParameter("email");
		
		// Xử lý role tại server
		String role = session.getAttribute("role").toString();
		/*
		 * String uri = request.getRequestURI(); // Lấy URI, ví dụ
		 * /yourapp/signup/congty if (uri.endsWith("/ungvien")) { role = "UngVien"; }
		 * else if (uri.endsWith("/congty")) { role = "CongTy"; } else {
		 * response.sendError(HttpServletResponse.SC_BAD_REQUEST,
		 * "Endpoint không hợp lệ."); return; }
		 */

		  // Sanitize dữ liệu đầu vào để tránh log injection
        String safeUsername = LogSanitizer.sanitizeForLog(username);
        String safeEmail = LogSanitizer.sanitizeForLog(email);
        String safeRole = LogSanitizer.sanitizeForLog(role);
        LOGGER.info("Attempting signup for user: {}, email: {}, role: {}", safeUsername, safeEmail, safeRole);
		String destination = "Signup.jsp?error=1";
		
		if (!TaiKhoan.isValidUserNamePassword(username, password, confirmPassword)) {
			 LOGGER.warn("Invalid signup input for user: {}", safeUsername);
            // Trả về lỗi nếu thiếu username hoặc password
            request.setAttribute("message", "Tên đăng nhập và mật khẩu không hợp lệ");
            request.getRequestDispatcher(destination).forward(request, response);
            return;
        }
		// Check tồn tại
		// boolean isRegistered = registerUser(username, password, role);
		//boolean isRegistered = true;
		String hashedPassword = PasswordUtil.encryptPassword(password);
		if (TaiKhoanDAO.isExistedAccount(username, hashedPassword)) {
			// Nếu đăng ký thất bại, hiển thị thông báo lỗi
			LOGGER.warn("Signup failed: username already exists - {}", safeUsername);
            request.setAttribute("message", "Tên đăng nhập đã tồn tại hoặc có lỗi xảy ra.");
            
        } else {
			/*
			 * if (role == "Employee") response.sendRedirect("ThongTinUngVien.jsp"); else
			 * response.sendRedirect("ThongTinCongTy.jsp");
			 */
        	
        	if (TaiKhoanDAO.AddAccount(username, hashedPassword, email, role))
        	{
        		destination = "Login.jsp";
        		int id = TaiKhoanDAO.getID("username", username);
            	if (role.equals("UngVien")) {
    				UngVienDAO.addUngVienAfterSignUP(id, email);
    			}
            	else {
    				CongTyDAO.addCongTyAfterSignUP(id, email);
    			}
            	LOGGER.info("Signup successful for user: {}", safeUsername);
        	} 
        	else 
        	{
        		LOGGER.error("Signup failed due to DB error for user: {}", safeUsername);
        		request.setAttribute("message", "Có lỗi, vui lòng thử lại");
			}
      	
        }
		request.getRequestDispatcher(destination).forward(request, response);
		//doGet(request, response);
	}

}
