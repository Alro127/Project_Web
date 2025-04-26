package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.json.JSONObject;

import beans.CongTy;
import dao.CongTyDAO;
import dao.TaiKhoanDAO;

/**
 * Servlet implementation class FacebookLoginServlet
 */
public class FacebookLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FacebookLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accessToken = request.getParameter("access_token");

        if (accessToken != null && !accessToken.isEmpty()) {
            try {
                // Xác minh access token qua Facebook Graph API
                String url = "https://graph.facebook.com/me?access_token=" + accessToken + "&fields=id,name,email";
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                String destination = "";
                
                
                con.setRequestMethod("GET");
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer responseBuffer = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    responseBuffer.append(inputLine);
                }
                in.close();

                // Xử lý dữ liệu trả về từ Facebook Graph API
                JSONObject myResponse = new JSONObject(responseBuffer.toString());
                String id_facebook = myResponse.getString("id");

                // Lưu thông tin vào session
                HttpSession session = request.getSession();
                session.setAttribute("id_facebook", id_facebook);
                
                if (!TaiKhoanDAO.isIDExisted(id_facebook, "id_facebook")) {
					TaiKhoanDAO.AddAccountByID(id_facebook, "id_facebook");
				}
                int id = TaiKhoanDAO.getID("id_facebook", id_facebook);
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
				    if (session.getAttribute("role").equals("UngVien")) {
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
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Facebook login failed.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing access token.");
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
