package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import okhttp3.Response;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Events;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfo;

import authentication.GoogleCredential;
import dao.CongTyDAO;
import dao.TaiKhoanDAO;

import com.google.api.services.calendar.model.Event;




/**
 * Servlet implementation class GoogleCalendarEventsServlet
 */
public class GoogleCalendarEventsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
    private static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoogleCalendarEventsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
        	final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        	HttpSession session = req.getSession(true);
        	String id_google = (String) session.getAttribute("id_google");
        	String email = (String) session.getAttribute("email");
    		String accessToken = (String) session.getAttribute("access_token");
    		String refreshToken = (String) session.getAttribute("refresh_token");
    		int id = Integer.parseInt((String) session.getAttribute("id"));
        	Credential credential = (Credential)session.getAttribute("Credential");
        	if (credential == null) {
        		if (accessToken == null) {
        			credential = new authentication.GoogleCredential(getServletContext()).getCredentials();
            		// Nếu mà đăng nhập mà không dùng google ý, thì giờ sẽ đăng nhập để sử dụng lịch
            		session.setAttribute("Credential", credential);
            		// Cập nhật token mới vào session
    		        session.setAttribute("access_token", credential.getAccessToken());
    		        accessToken = (String) session.getAttribute("access_token");
				}
        		else {
        			credential = new Credential(BearerToken.authorizationHeaderAccessMethod())
            		        .setAccessToken(accessToken);
				}
        	}
        	System.out.println("Expires in seconds: " + credential.getExpiresInSeconds());

        	if ((credential.getExpiresInSeconds() != null && credential.getExpiresInSeconds() <= 60) || 
        			credential.getExpiresInSeconds() == null) {
    		    try {
    		    	GoogleCredential googleCredential = new GoogleCredential(getServletContext());
    		    	TokenResponse tokenResponse = googleCredential.refreshAccessToken(refreshToken);
    		    	accessToken = tokenResponse.getAccessToken();
    		        if (accessToken == null) {
    		            // Không thể làm mới access token
    		            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access token expired and refresh failed.");
    		            return;
    		        }
    		        credential = new Credential(BearerToken.authorizationHeaderAccessMethod())
            		        .setAccessToken(accessToken);
    		        
    		    } catch (IOException e) {
    		        throw new ServletException("Error refreshing access token.", e);
    		    }
    		}
        	// Tạo OAuth2 service
	        Oauth2 oauth2 = new Oauth2.Builder(new NetHttpTransport(), new JacksonFactory(), credential)
	                .setApplicationName("Your Application Name")
	                .build();
	        
	        // Cập nhật vào biến
	        
	        Userinfo userInfo = oauth2.userinfo().get().execute();
	        accessToken = credential.getAccessToken();
	        if (credential.getRefreshToken() != null) {
	        	 refreshToken = credential.getRefreshToken();
			}
	        id_google = userInfo.getId();
	        email = userInfo.getEmail();
	        

	        
	        // Cập nhật token và credential mới vào session
	        session.setAttribute("id_google",  id_google);
	        session.setAttribute("email", email);
	        session.setAttribute("access_token", accessToken);
	        session.setAttribute("Credential", credential);
	        
	        
        	TaiKhoanDAO.StoreTokens(accessToken, refreshToken, id, email, id_google);
        	Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                     .setApplicationName(APPLICATION_NAME)
                     .build();
            Events events = service.events().list("primary")
					/* .setMaxResults(10) */
                    .setOrderBy("startTime")
                    .setSingleEvents(true)
                    .execute();

            List<Event> items = events.getItems();
            List <String> emails = CongTyDAO.getEmailOfEmployeesFromCompany(id);
            req.setAttribute("emails", email);
            session.setAttribute("emails", emails);
            // Truyền danh sách sự kiện vào request
            req.setAttribute("events", items);
            
            // Chuyển tiếp tới trang showCalendar.jsp
            req.getRequestDispatcher("CalendarTest.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
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
