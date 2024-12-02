package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import authentication.GoogleCredential;
import beans.CongTy;
import dao.CongTyDAO;
import dao.TaiKhoanDAO;

/**
 * Servlet implementation class LoginGoogleServlet
 */
public class LoginGoogleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginGoogleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     try {
	    	 	// Lấy được credntial để làm ăn
	    	 	GoogleCredential getCredential = new GoogleCredential(getServletContext());
		        Credential credential = getCredential.getCredentials();
		        String destination = "";
		        
		        // Đảm bảo có credential trong toàn bộ phiên làm việc nếu đăng nhập bằng google
		        HttpSession session = request.getSession(true);
		        session.setAttribute("Credential", credential);
		        
		        String accessToken = credential.getAccessToken();
		        String refreshToken = credential.getRefreshToken();
		        System.out.println("Access Token: " + accessToken);
		        System.out.println("Refresh Token: " + refreshToken);
		        
		        // Sử dụng credential để tạo đối tượng Oauth2 nhằm lấy thông tin user
		        Oauth2 oauth2 = new Oauth2.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, credential)
		                .setApplicationName("Google OAuth Example")
		                .build();
		        
		        // Lấy thông tin người dùng
		        com.google.api.services.oauth2.model.Userinfo userinfo = oauth2.userinfo().get().execute();
		        String id_google = userinfo.getId();
		        
		        session.setAttribute("id_google", id_google);
		        
		        // Nếu đăng nhập lần đầu thì sẽ thêm vào CSDL
		        if (!TaiKhoanDAO.isIDExisted(id_google, "id_google"))
		        {
		        	TaiKhoanDAO.AddAccountByID(id_google, "id_google");
		        }
		        int id = TaiKhoanDAO.getID("id_google", id_google);
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
				    if (session.getAttribute("role") == "UngVien") {
				    	destination = "CongViecServlet";
					}
				    else {
						destination = "TaiKhoanCongTyServlet";
						CongTy ct = CongTyDAO.GetCongTyById(Integer.parseInt((String)session.getAttribute("id")));
						session.setAttribute("name", ct.getTenCongTy());
					}
				}
		        // Chuyển hướng người dùng đến trang tiếp theo sau khi đăng nhập thành công
		        response.sendRedirect(destination);
		} catch (Exception e) {
			e.printStackTrace();
		}
 
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
