<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.2.0/fullcalendar.min.css" rel="stylesheet" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> <!-- Thêm jQuery -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.2.0/fullcalendar.min.js"></script>
</head>
<body>
    <div id="calendar"></div>
    
    <script>
        $(document).ready(function() {
            $('#calendar').fullCalendar({
                events: [
                    <%-- Lặp qua các sự kiện từ Google Calendar --%>
                    <c:forEach var="event" items="${events}">
                        {
                            title: '${event.getSummary()}',
                            start: '${event.getStart().getDateTime()}',
                            end: '${event.getEnd().getDateTime()}'
                        },
                    </c:forEach>
                ]
            });
        });
    </script>
</body>
</html>
