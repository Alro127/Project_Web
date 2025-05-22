<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>

<div class="container">
	<div class="row">
		<c:forEach var="congViecLienQuan" items="${congViecLienQuans}"
			begin="0" end="4">
			<div class="col-12 mb-4 py-0">
				<a href="ChiTietCongViecServlet?id=${congViecLienQuan.idCongViec}"
					class="text-decoration-none text-dark">
					<div class="py-3 px-3 bg-light shadow rounded">
						<h5 class="card-title">
						    <strong>
						        <!-- Giới hạn độ dài tên công việc, nếu tên dài hơn 30 ký tự thì cắt và thêm "..." -->
						        <c:choose>
						            <c:when test="${fn:length(congViecLienQuan.ten) > 30}">
						                ${fn:substring(congViecLienQuan.ten, 0, 30)}...
						            </c:when>
						            <c:otherwise>
						                ${congViecLienQuan.ten}
						            </c:otherwise>
						        </c:choose>
						    </strong>
						</h5>

						<div class="d-flex mt-2">
							<img src="${congViecLienQuan.logo}"
								class="card-img-top img-fluid job-img" alt="Công việc"
								>
							<div class="card-body ms-3">

								<h6 class="font-weight-bold font-rem-1-1" >${congViecLienQuan.tenCongTy}</h6>
								<p class="card-text">
									<!-- Định dạng lương với dấu phân cách hàng nghìn -->
									<strong><i class="bi bi-currency-dollar text-warning"></i></strong>
									<fmt:formatNumber value="${congViecLienQuan.luong}"
										type="number" pattern="#,###" />
									VND <br>

									<!-- Kiểm tra và hiển thị số năm kinh nghiệm hoặc "Không yêu cầu" -->
									<strong><i class="bi bi-briefcase text-success"></i></strong>
									<c:choose>
										<c:when test="${congViecLienQuan.namKinhNghiem > 0}">
					                    ${congViecLienQuan.namKinhNghiem} năm
					                </c:when>
										<c:otherwise>
					                    Không yêu cầu kinh nghiệm
					                </c:otherwise>
									</c:choose>
									<br> <strong><i
										class="bi bi-geo-alt text-primary"></i></strong>
									${congViecLienQuan.diaDiem} <br>
								</p>
							</div>
						</div>

					</div>


				</a>
			</div>

		</c:forEach>
	</div>
</div>

