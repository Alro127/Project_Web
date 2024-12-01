<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Google Calendar Events</title>
    <link href="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.min.css" rel="stylesheet" />
    <script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.min.js"></script>
    <link rel="icon" href="data:,">
    
</head>
<body>

<h1>Google Calendar Events</h1>
<div id="addEventModal" style="display:none;z-index: 1000; position: relative; top: 0">
    <h3>Add New Event</h3>
    <form id="addEventForm" action="GoogleAddEventServlet" method="post" onsubmit="convertDateTime()">
	    <label for="eventTitle">Event Title:</label>
	    <input type="text" id="eventTitle" name="eventTitle" required>
	    
	    <label for="eventStart">Start Time:</label>
	    <input type="datetime-local" id="eventStart" name="eventStart" required>
	    
	    <label for="eventEnd">End Time:</label>
	    <input type="datetime-local" id="eventEnd" name="eventEnd" required>
	    
	    <button type="submit">Add Event</button>
	</form>
</div>
<button id="addEventBtn">Add Event</button>
<button id="deleteEventBtn">Delete Event</button>
<div id="calendar"></div>




<script>
document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    
    // Chuyển các sự kiện từ Servlet sang dạng JSON để FullCalendar có thể hiểu
    var events = ${events};
    console.log(events);
	
    // Chuyển đổi các sự kiện từ Java (List<Event>) thành JSON cho FullCalendar
    var eventData = events.map(function(event) {
	    return {
	        title: event.summary || "No Title",  // Đặt giá trị mặc định nếu không có summary
	        start: event.start ? (event.start.dateTime || event.start.date) : null, // Kiểm tra start
	        end: event.end ? (event.end.dateTime || event.end.date) : null // Kiểm tra end
	    };
	});

    console.log(eventData);

    var calendar = new FullCalendar.Calendar(calendarEl, {
    	timeZone: 'UTC',
        initialView: 'dayGridMonth',
        events: eventData,
        eventOverlap: true,
        eventDidMount: function(info) {
            console.log(`Rendered Event: ${info.event.title} from ${info.event.start} to ${info.event.end}`);
        }
    });

    calendar.render();
    
 	// Thêm sự kiện mới
    document.getElementById('addEventBtn').addEventListener('click', function() {
        document.getElementById('addEventModal').style.display = 'block';
    });
    
    function convertDateTime() {
        // Lấy giá trị từ input datetime-local
        var startTime = document.getElementById("eventStart").value;
        var endTime = document.getElementById("eventEnd").value;

        startTime = startTime + ":00";  // Đảm bảo có giây
        endTime = endTime + ":00";      // Đảm bảo có giây

        document.getElementById("eventStart").value = startTime;
        document.getElementById("eventEnd").value = endTime;
    }
});
</script>

</body>
</html>
