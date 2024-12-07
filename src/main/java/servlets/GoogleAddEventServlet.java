package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;

import authentication.GoogleCredential;
import dao.CongTyDAO;

/**
 * Servlet implementation class GoogleAddEventServlet
 */
public class GoogleAddEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoogleAddEventServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            // Lấy thông tin xác thực
			HttpSession session = request.getSession(true);
            Credential credential = (Credential) session.getAttribute("Credential");

            // Tạo Google Calendar Service với credential đã lấy
            Calendar service = new Calendar.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), credential)
                    .setApplicationName(APPLICATION_NAME)
                    .build();
 
            // Lấy thông tin sự kiện từ request
            String title = request.getParameter("eventTitle");
            String start = request.getParameter("eventStart");
            String end = request.getParameter("eventEnd");
            
            // Chuyển sang dạng dữ thời gian phù hợp
            start = start + ":00";
            end = end + ":00";
           
            List<EventAttendee> attendeesEmail = new ArrayList<>();

            // Thêm người tham gia vào danh sách
            // Lấy từ CSDL
            String role = session.getAttribute("role").toString();
            if (role.equals("CongTy")) {
            	int id = Integer.parseInt((String)session.getAttribute("id"));
            	List<String> emails = CongTyDAO.getEmailOfEmployeesFromCompany(id);
                for (String email : emails) {
					attendeesEmail.add(new EventAttendee().setEmail(email));
					System.out.println(email);
				}
			}
            
            // Tạo sự kiện và thêm vào Google Calendar
            Event event = new Event()
                    .setSummary(title)
                    .setStart(new EventDateTime().setDateTime(new DateTime(start)).setTimeZone("UTC+7"))
                    .setEnd(new EventDateTime().setDateTime(new DateTime(end)).setTimeZone("Asia/Ho_Chi_Minh"))
                    .setAttendees(attendeesEmail);
              
            service.events().insert("primary", event).execute();
            response.setStatus(HttpServletResponse.SC_OK);
                   
	        // Chuyển tiếp tới trang trang để fetch event
	        response.sendRedirect(request.getContextPath() + "/GoogleCalendarEventsServlet");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
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
