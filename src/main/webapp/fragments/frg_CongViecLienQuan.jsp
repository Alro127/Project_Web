<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<div class="container">
	<div class="row">
		<c:forEach var="congViecLienQuan" items="${congViecLienQuans}"
			begin="0" end="4">
			<div class="col-12 mb-4 py-0">
				<a href="ChiTietCongViecServlet?id=${congViec.idCongViec}"
					class="text-decoration-none text-dark">
					<div class="d-flex py-3 px-3 bg-light shadow rounded">
						<img src="${congViec.logo}" class="card-img-top img-fluid"
							alt="Công việc"
							style="width: 100px; height: 100px; object-fit: cover;">
						<div class="card-body ms-3">
							<h5 class="card-title">
								<strong>${congViecLienQuan.ten}</strong>
							</h5>
							<h6 class="font-weight-bold" style="font-size: 1.1rem;">${congViecLienQuan.tenCongTy}</h6>
							<p class="card-text">
								<strong><i class="bi bi-currency-dollar text-warning"></i>
									</strong> ${congViecLienQuan.luong} VND <br> <strong><i
									class="bi bi-geo-alt text-primary"></i></strong>
								${congViecLienQuan.diaDiem}
							</p>
						</div>
					</div>
				</a>
			</div>

		</c:forEach>
	</div>
</div>

