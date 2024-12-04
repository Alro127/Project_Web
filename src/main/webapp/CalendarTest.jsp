<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Google Calendar Events</title>
<link
	href="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.min.css"
	rel="stylesheet" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"
	rel="stylesheet"> 
<script
	src="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<link href="assets/css/style.css" rel="stylesheet">

<style>
/* Đổi màu các ngày (ô lịch) */
.fc-daygrid-day-number {
	color: #3d405b;
	font-weight: bold;
	text-decoration: none;
}

.fc-col-header-cell-cushion {
	color: white;
	font-weight: bold;
	text-decoration: none;
}

/* Đổi màu header (thứ trong tuần) */
.fc-col-header-cell {
	background-color: #3d405b;
	font-weight: bold;
}

/* Bỏ underline khi hover sự kiện */
.fc-event {
	text-decoration: none !important;
	color: #fff !important; /* Đảm bảo sự kiện dễ đọc */
	background-color: #28a745 !important; /* Đổi màu sự kiện */
	border: none !important; /* Loại bỏ viền */
}

/* Highlight ngày hôm nay */
.fc-day-today {
	background-color: #e9f7ef; /* Màu xanh nhạt */
}
</style>

</head>
<body class="bg-light-grey">
	<jsp:include page="fragments/topNavAcc.jsp"></jsp:include>
	<div class="d-flex mt-5">

		<jsp:include page="fragments/sidebar_CongTy.jsp" />

		<div class="container my-5">
			<div class="d-flex justify-content-end mb-3">
				<button id="addEventBtn" class="btn bg-dark-blue text-light me-2">Add
					Event</button>
				<button id="deleteEventBtn" class="btn bg-coral text-light">Delete
					Event</button>
			</div>
			<div id="calendar" class="border rounded bg-white shadow-sm p-3"></div>
		</div>

		<!-- Modal for Adding Event -->
		<div class="modal fade" id="addEventModal" tabindex="-1"
			aria-labelledby="addEventModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<form id="addEventForm" action="GoogleAddEventServlet"
						method="post" onsubmit="convertDateTime()">
						<div class="modal-header">
							<h5 class="modal-title" id="addEventModalLabel">Add New
								Event</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<div class="mb-3">
								<label for="eventTitle" class="form-label">Event Title:</label>
								<input type="text" id="eventTitle" name="eventTitle"
									class="form-control" required>
							</div>
							<div class="mb-3">
								<label for="eventStart" class="form-label">Start Time:</label> <input
									type="datetime-local" id="eventStart" name="eventStart"
									class="form-control" required>
							</div>
							<div class="mb-3">
								<label for="eventEnd" class="form-label">End Time:</label> <input
									type="datetime-local" id="eventEnd" name="eventEnd"
									class="form-control" required>
							</div>
						</div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-success">Add Event</button>
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Close</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div id="deleteEventModal" class="modal fade" tabindex="-1"
			aria-labelledby="deleteEventModalLabel" aria-hidden="true">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <h5 class="modal-title">Delete Event</h5>
		                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
		            </div>
		            <div class="modal-body">
		                <p>Are you sure you want to delete this event?</p>
		            </div>
		            <div class="modal-footer">
		                <button id="confirmDeleteBtn" class="btn btn-danger">Yes</button>
		                <button id="cancelDeleteBtn" class="btn btn-secondary">No</button>
		            </div>
		        </div>
		    </div>
		</div>
		
		
	</div>

	<script>
		document.addEventListener('DOMContentLoaded', function() {
	    var calendarEl = document.getElementById('calendar');
	
	    // Chuyển các sự kiện từ Servlet sang dạng JSON để FullCalendar có thể hiểu
	    var events = ${events};
	    
	    var eventData = events.map(function(event) {
		    return {
		    	id: event.id,
		        title: event.summary || "No Title",  // Đặt giá trị mặc định nếu không có summary
		        start: event.start ? (event.start.dateTime || event.start.date) : null, // Kiểm tra start
		        end: event.end ? (event.end.dateTime || event.end.date) : null // Kiểm tra end
		    };
		});
	
	    var calendar = new FullCalendar.Calendar(calendarEl, {
	        timeZone: 'UTC',
	        initialView: 'dayGridMonth',
	        events: eventData,
	        eventOverlap: true,
	        eventDidMount: function(info) {
	            console.log(`Rendered Event: ${info.event.title} from ${info.event.start} to ${info.event.end}`);
	        },
	        eventClick: function(info) {
	            // Hiển thị modal xác nhận xóa sự kiện
	            var eventId = info.event.id;
	            showDeleteConfirmation(eventId);
	        }
	    });
	
	    calendar.render();
	
	    // Show modal for adding events
	    document.getElementById('addEventBtn').addEventListener('click', function() {
	        var modal = new bootstrap.Modal(document.getElementById('addEventModal'));
	        modal.show();
	    });
		});

		function convertDateTime() {
	    // Convert datetime-local values to include seconds
	    var startTime = document.getElementById("eventStart").value;
	    var endTime = document.getElementById("eventEnd").value;
	
	        document.getElementById("eventStart").value = startTime;
	        document.getElementById("eventEnd").value = endTime;
	    }
	
	    function showDeleteConfirmation(eventId) {
	    	var modalElement = document.getElementById('deleteEventModal');
	        var modal = new bootstrap.Modal(modalElement); // Tạo một instance modal
	        
	        // Gán sự kiện cho nút Confirm
	        document.getElementById('confirmDeleteBtn').onclick = function() {
	            deleteEvent(eventId);
	            modal.hide(); // Đóng modal sau khi xóa
	        };
	        
	        document.getElementById('cancelDeleteBtn').onclick = function() {
	            modal.hide(); // Đóng modal sau khi xóa
	        };
	        // Hiển thị modal
	        modal.show();
	    }
	
	    function deleteEvent(eventId) {
	        var xhr = new XMLHttpRequest();
	        xhr.open('POST', 'GoogleDeleteEventServlet', true);
	        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	        xhr.onreadystatechange = function() {
	            if (xhr.readyState == 4 && xhr.status == 200) {
	                // Sau khi xóa thành công, làm mới lại lịch
	                alert('Event deleted successfully');
	                location.reload();  // Tải lại trang để cập nhật danh sách sự kiện
	            }
	        };
	        xhr.send('eventId=' + eventId);  // Gửi ID sự kiện để xóa
	    }
	

</script>

</body>
</html>
