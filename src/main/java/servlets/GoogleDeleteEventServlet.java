package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;

import authentication.GoogleCredential;

/**
 * Servlet implementation class GoogleDeleteEventServlet
 */
public class GoogleDeleteEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoogleDeleteEventServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String eventId = request.getParameter("eventId");
		 if (eventId != null && !eventId.isEmpty()) 
		 {
	            try {
	            	// Lấy thông tin xác thực
	    			HttpSession session = request.getSession(true);
	                Credential credential = (Credential) session.getAttribute("Credential");

	                // Tạo Google Calendar Service với credential đã lấy
	                Calendar service = new Calendar.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), credential)
	                        .setApplicationName(APPLICATION_NAME)
	                        .build();

	                // Xóa sự kiện từ Google Calendar bằng eventId
	                service.events().delete("primary", eventId).execute();

	                // Trả về phản hồi thành công
	                response.setStatus(HttpServletResponse.SC_OK);
	            } catch (IOException e) {
	                e.printStackTrace();
	                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "IO error occurred");
	            }
	        } else {
	            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Event ID is missing");
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
