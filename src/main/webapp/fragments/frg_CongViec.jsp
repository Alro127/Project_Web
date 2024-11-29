<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:choose>
	<c:when test="${not empty congViecs}">
		<c:forEach var="congViec" items="${congViecs}">
			<div class="col-md-4 mb-4">
				<div class="card">
					<img src="anh.jpg" class="card-img-top img-fluid" alt="Công việc">
					<div class="card-body">
						<h5 class="card-title">${congViec.ten}</h5>
						<p class="card-text">
							<strong>Công ty:</strong> ${congViec.idCT} <br> <strong>Lương:</strong>
							${congViec.luong} VND <br> <strong>Địa điểm:</strong>
							${congViec.diaDiem}
						</p>
					</div>
				</div>
			</div>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<p>Không có công việc nào để hiển thị.</p>
	</c:otherwise>
</c:choose>
