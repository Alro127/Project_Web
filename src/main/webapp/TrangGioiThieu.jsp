<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String nonce = (String) request.getAttribute("cspNonce");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Giới Thiệu</title>

    <!-- External Libraries -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" nonce="<%= nonce %>"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet" nonce="<%= nonce %>">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" 
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
          crossorigin="anonymous" nonce="<%= nonce %>">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
            crossorigin="anonymous" nonce="<%= nonce %>"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-slider/10.6.2/bootstrap-slider.min.js" nonce="<%= nonce %>"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/noUiSlider/15.6.0/nouislider.min.css" rel="stylesheet" nonce="<%= nonce %>">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/noUiSlider/15.6.0/nouislider.min.js" nonce="<%= nonce %>"></script>

    <!-- Local Assets -->
    <link href="assets/css/style.css" rel="stylesheet" nonce="<%= nonce %>">
</head>
<body class="bg-light-grey">

    <!-- Header Navigation -->
    <c:choose>
        <c:when test="${not empty sessionScope.id}">
            <jsp:include page="fragments/topNavAcc.jsp"></jsp:include>
        </c:when>
        <c:otherwise>
            <jsp:include page="fragments/topNav.jsp"></jsp:include>
        </c:otherwise>
    </c:choose>

    <!-- Main Content -->
    <div class="container mt-5">
        <jsp:include page="fragments/frg_Banner.jsp" />
        <div class="bg-white rounded p-3 shadow-sm mt-4 mb-4"
             style="background-color: rgba(255, 255, 255, 0.5);" nonce="<%= nonce %>">
            <jsp:include page="fragments/frg_TimKiemVaLoc.jsp" />
            <jsp:include page="fragments/frg_DeXuat.jsp" />
        </div>

        <!-- Job Section -->
        <h4 class="col-12 mb-4">Công việc đang tuyển</h4>
        <jsp:include page="fragments/frg_CongViec.jsp" />
    </div>

    <!-- Footer -->
    <jsp:include page="fragments/footer.jsp" />

    <!-- Local JS Files -->
    <script src="js/PhanTrangGioiThieu.js" nonce="<%= nonce %>"></script>
    <script src="js/LocSlider.js" nonce="<%= nonce %>"></script>
</body>
</html>
