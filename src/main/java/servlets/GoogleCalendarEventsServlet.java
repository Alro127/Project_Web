package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Events;
import com.google.api.services.calendar.model.Event;




/**
 * Servlet implementation class GoogleCalendarEventsServlet
 */
public class GoogleCalendarEventsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
    private static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	/* private static final String TOKENS_DIRECTORY_PATH = "tokens"; */

	/*
	 * private static final List<String> SCOPES =
	 * Collections.singletonList(CalendarScopes.CALENDAR_READONLY); private static
	 * final String CREDENTIALS_FILE_PATH = "credential/credentials.json";
	 */

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoogleCalendarEventsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/*
	 * private Credential getCredentials() throws IOException,
	 * GeneralSecurityException { InputStream in =
	 * getServletContext().getResourceAsStream(
	 * "/WEB-INF/classes/credential/credentials.json");
	 * 
	 * GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
	 * new InputStreamReader(in));
	 * 
	 * GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
	 * GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, clientSecrets,
	 * SCOPES) .setDataStoreFactory(new
	 * com.google.api.client.util.store.FileDataStoreFactory(new
	 * java.io.File(TOKENS_DIRECTORY_PATH))) .setAccessType("offline") .build();
	 * 
	 * LocalServerReceiver receiver = new
	 * LocalServerReceiver.Builder().setPort(8888).build(); return new
	 * AuthorizationCodeInstalledApp(flow, receiver).authorize("user"); }
	 */
 
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            authentication.GoogleCredential googleCredential = new authentication.GoogleCredential(getServletContext());
            
            Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, googleCredential.getCredentials())
                    .setApplicationName(APPLICATION_NAME)
                    .build();

            
            // Fetch events
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
