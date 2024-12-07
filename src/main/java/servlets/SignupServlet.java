package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.google.common.graph.SuccessorsFunction;

import beans.TaiKhoan;
import dao.CongTyDAO;
import dao.TaiKhoanDAO;
import dao.UngVienDAO;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
		String confirmPassword = request.getParameter("confirm-password");
		String email = request.getParameter("email");
		String role = request.getParameter("role");
		
		String destination = "Signup.jsp?error=1";
		
		if (!TaiKhoan.isValidUserNamePassword(username, password, confirmPassword)) {
            // Trả về lỗi nếu thiếu username hoặc password
            request.setAttribute("message", "Tên đăng nhập và mật khẩu không hợp lệ");
            request.getRequestDispatcher(destination).forward(request, response);
            return;
        }
		// Check tồn tại
		// boolean isRegistered = registerUser(username, password, role);
		//boolean isRegistered = true;
		if (TaiKhoanDAO.isExistedAccount(username, password)) {
			// Nếu đăng ký thất bại, hiển thị thông báo lỗi
            request.setAttribute("message", "Tên đăng nhập đã tồn tại hoặc có lỗi xảy ra.");
            
        } else {
			/*
			 * if (role == "Employee") response.sendRedirect("ThongTinUngVien.jsp"); else
			 * response.sendRedirect("ThongTinCongTy.jsp");
			 */
        	
        	if (TaiKhoanDAO.AddAccount(username, password, email, role))
        	{
        		int id = TaiKhoanDAO.getID("username", username);
            	if (role.equals("UngVien")) {
    				UngVienDAO.addUngVienAfterSignUP(id, email);
    			}
            	else {
    				CongTyDAO.addCongTyAfterSignUP(id, email);
    			}
        	} 
        	else 
        	{
        		request.setAttribute("message", "Có lỗi, vui lòng thử lại");
			}
      	
        }
		request.getRequestDispatcher(destination).forward(request, response);
		doGet(request, response);
	}

}
