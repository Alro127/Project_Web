package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Events;
import com.google.api.services.calendar.model.Event;




/**
 * Servlet implementation class GoogleCalendarEventsServlet
 */
public class GoogleCalendarEventsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoogleCalendarEventsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Xác thực và lấy token từ session
        HttpSession session = request.getSession();
        String accessToken = (String) session.getAttribute("access_token");

        if (accessToken == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return;
        }

        try {
            // Tạo dịch vụ Google Calendar
            GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);
            Calendar service = new Calendar.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    JacksonFactory.getDefaultInstance(),
                    credential)
                    .setApplicationName("Google Calendar Java Quickstart")
                    .build();

            // Lấy danh sách sự kiện trong tháng
            Events events = service.events().list("primary")
                    .setTimeMin(new DateTime(System.currentTimeMillis()))
                    .setMaxResults(10)
                    .setOrderBy("startTime")
                    .setSingleEvents(true)
                    .execute();

            // Hiển thị các sự kiện
            List<Event> items = events.getItems();
            for (Event event : items) {
                System.out.println(event.getSummary());
            }

            // Đưa sự kiện vào request hoặc session để hiển thị trên trang
            request.setAttribute("events", items);
            request.getRequestDispatcher("/showCalendar.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching calendar events.");
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
