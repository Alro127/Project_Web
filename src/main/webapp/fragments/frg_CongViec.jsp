<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
    <c:when test="${not empty congViecs}">
        <div class="container">
            <div class="row">
				<h4 class="col-12 mb-4">Công việc đang tuyển</h4>
				<c:forEach var="congViec" items="${congViecs}">
					<div class="col-12 col-md-4 mb-4 py-0">
						<div class="d-flex py-3 px-3 bg-light shadow rounded">
							<img
								src="https://ibrand.vn/wp-content/uploads/2024/07/mbbank-logo-5.png"
								class="card-img-top img-fluid" alt="Công việc"
								style="width: 100px; height: 100px; object-fit: cover;">
							<div class="card-body ms-3">
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
			</div>
        </div>
    </c:when>
    <c:otherwise>
        <p>Không có công việc nào để hiển thị.</p>
    </c:otherwise>
</c:choose>


