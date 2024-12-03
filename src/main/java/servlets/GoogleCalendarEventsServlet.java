package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Events;

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
				}
        		else {
        			credential = new Credential(BearerToken.authorizationHeaderAccessMethod())
            		        .setAccessToken(accessToken);
				}
        	}
        	if (credential.getExpiresInSeconds() != null && credential.getExpiresInSeconds() <= 60) {
    		    try {
    		        credential.setRefreshToken(refreshToken);
    		        if (!credential.refreshToken()) {
    		            // Không thể làm mới access token
    		            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access token expired and refresh failed.");
    		            return;
    		        }
    		        // Cập nhật token mới vào session
    		        session.setAttribute("access_token", credential.getAccessToken());
    		    } catch (IOException e) {
    		        throw new ServletException("Error refreshing access token.", e);
    		    }
    		}
        	TaiKhoanDAO.StoreTokens(accessToken, refreshToken, id, email);
        	Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                     .setApplicationName(APPLICATION_NAME)
                     .build();
            Events events = service.events().list("primary")
					/* .setMaxResults(10) */
                    .setOrderBy("startTime")
                    .setSingleEvents(true)
                    .execute();

            List<Event> items = events.getItems();
            
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
