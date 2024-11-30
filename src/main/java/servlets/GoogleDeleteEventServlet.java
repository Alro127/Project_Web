package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;

import authentication.GoogleCredential;

/**
 * Servlet implementation class GoogleDeleteEventServlet
 */
public class GoogleDeleteEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
	                // Lấy đối tượng GoogleCredential để truy cập vào Google Calendar API
	                GoogleCredential googleCredential = new GoogleCredential(getServletContext());
	                Calendar service = new Calendar.Builder(
	                        GoogleNetHttpTransport.newTrustedTransport(),
	                        JacksonFactory.getDefaultInstance(),
	                        googleCredential.getCredentials())
	                        .setApplicationName("Google Calendar API")
	                        .build();

	                // Xóa sự kiện từ Google Calendar bằng eventId
	                service.events().delete("primary", eventId).execute();

	                // Trả về phản hồi thành công
	                response.setStatus(HttpServletResponse.SC_OK);
	            } catch (GeneralSecurityException e) {
	                e.printStackTrace();
	                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Security error occurred");
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
