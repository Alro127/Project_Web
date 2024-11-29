<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
/* Sidebar styles */
.sidebar {
	height: 100vh;
	width: 250px;
	position: fixed;
	transition: all 0.3s;
}

.sidebar.collapsed {
	width: 80px;
}

.sidebar .nav-link {
	color: #fff;
	display: flex;
	align-items: center;
	gap: 20px;
	font-size: 16px; /* Cỡ chữ cho từng liên kết */
	font-weight: bold;
}

.sidebar.collapsed .nav-link span, .sidebar.collapsed .bi span {
	display: none;
}

.btn {
	border: none;
}
</style>
<div id="sidebar" class="sidebar d-flex flex-column bg-dark-blue shadow collapsed">
	<button id="toggleSidebar" class="mt-3 mb-3 btn bg-dark-blue ">
		<i class="bi bi-list text-light fs-3"><span class="ms-2">CVHub</span></i>
	</button>
	<a href="#" class="nav-link hover-coral"> <i
		class="ms-4 py-2 fs-3 fw-bold bi bi-upload"></i> <span>Đăng CV</span>
	</a> <a href="#" class="nav-link hover-coral"> <i
		class="ms-4 py-2 fs-3 fw-bold bi bi-file-earmark-text"></i> <span>Quản
			lý CV</span>
	</a> <a href="#" class="nav-link hover-coral"> <i
		class="ms-4 py-2 fs-3 fw-bold bi bi-search"></i> <span>Tìm việc</span>
	</a> <a href="#" class="nav-link hover-coral"> <i
		class="ms-4 py-2 fs-3 fw-bold bi bi-building"></i> <span>Công ty đã
			ứng tuyển</span>
	</a> <a href="#" class="nav-link hover-coral"> <i
		class="ms-4 py-2 fs-3 fw-bold bi bi-calendar-check"></i> <span>Lịch
			hẹn</span>
	</a> <a href="#" class="nav-link hover-coral"> <i
		class="ms-4 py-2 fs-3 fw-bold bi bi-person-gear"></i> <span>Quản lý
			tài khoản</span>
	</a>


</div>

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script>
        // Toggle sidebar
        const sidebar = document.getElementById('sidebar');
        const toggleButton = document.getElementById('toggleSidebar');

        toggleButton.addEventListener('click', () => {
            sidebar.classList.toggle('collapsed');
            const icon = toggleButton.querySelector('i');
        });
    </script>
