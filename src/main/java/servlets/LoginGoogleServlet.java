package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;

import authentication.GoogleLogin;

/**
 * Servlet implementation class LoginGoogleServlet
 */
public class LoginGoogleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginGoogleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // Lấy id_token từ query parameters (trong URL)
        String idTokenString = request.getParameter("id_token");
        
        if (idTokenString == null || idTokenString.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing ID token.");
            return;
        }

        try {
            // Xác minh token Google và lấy thông tin người dùng
            GoogleIdToken.Payload payload = GoogleLogin.verifyToken(idTokenString);
            String userId = payload.getSubject();
            String email = payload.getEmail();
            String name = (String) payload.get("name");

            // Lưu thông tin người dùng vào session hoặc cookie nếu cần thiết
            HttpSession session = request.getSession();
            session.setAttribute("userId", userId);
            session.setAttribute("email", email);
            session.setAttribute("name", name);

            // Redirect đến trang chính hoặc trang mà bạn muốn
            response.sendRedirect("TrangGioiThieu.jsp");

        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid ID token.");
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
