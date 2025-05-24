<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String nonce = (String) request.getAttribute("cspNonce");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng Nhập</title>

<!-- Style with nonce -->
<style nonce="<%= nonce %>">
:root {
	--first-color: #3d405b;
	--second-color: #ff8160;
	--third-color: #f4f1de;
	--background-color: #f0f0f0;
}
body {
	font-family: "Arial", sans-serif;
	background-color: var(--background-color);
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
	background-image: linear-gradient(to bottom right, rgba(61, 64, 91, 0.8), var(--second-color));
	color: var(--third-color);
}
.login-container {
	background-color: white;
	padding: 40px;
	border-radius: 10px;
	box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2);
	width: 300px;
	text-align: center;
}
h2 { margin-bottom: 20px; color: var(--first-color); }
.form-group {
	margin-bottom: 5px;
	display: flex;
	flex-direction: column;
	text-align: left;
}
.form-group label {
	display: block;
	margin-bottom: 5px;
	font-weight: bold;
	color: var(--first-color);
}
.form-group input {
	padding: 10px;
	border: 1px solid #ced4da;
	border-radius: 5px;
	transition: border-color 0.3s;
}
.form-group input:focus {
	border-color: var(--first-color);
	outline: none;
}
.form-group button {
	width: 100%;
	padding: 10px;
	background-color: var(--second-color);
	color: white;
	border: none;
	border-radius: 5px;
	font-weight: bold;
	cursor: pointer;
	transition: background-color 0.3s;
	margin-top: 2px;
}
.form-group button:hover {
	background-color: #e56a4e;
}
.form-footer {
	margin-top: 15px;
	font-size: 0.9em;
	color: var(--first-color);
}
.form-footer a {
	color: var(--second-color);
	text-decoration: none;
}
.form-footer a:hover {
	text-decoration: underline;
}
#loginGoogle {
	width: 300px;
	height: 34.93px;
	border-radius: 4px;
	background: #ffff;
}
#loginGoogle:hover {
	cursor: pointer !important;
	background: #ccc !important;
}
</style>

<!-- Google JS -->
<script src="https://accounts.google.com/gsi/client" async defer nonce="<%= nonce %>"></script>
</head>

<body>
	<div class="login-container">
		<h2>Đăng Nhập</h2>
		<form action="LoginServlet" method="POST">
			<input type="hidden" name="csrfToken" value="<c:out value='${csrfToken}'/>">
			<div class="form-group">
				<label for="username">Tên đăng nhập:</label>
				<input type="text" id="username" name="username" placeholder="Nhập tên đăng nhập" required />
			</div>
			<div class="form-group">
				<label for="password">Mật khẩu:</label>
				<input type="password" id="password" name="password" placeholder="Nhập mật khẩu" required />
			</div>
			<div class="form-group">
				<button type="submit">Đăng Nhập</button>
			</div>
		</form>

		<button id="loginGoogle" class="custom-button">Đăng nhập với Google</button>


		<div class="form-footer">
		    <c:choose>
		        <c:when test="${param.role == 'UngVien'}">
		            <p>Chưa có tài khoản? <a href="${pageContext.request.contextPath}/signup/ungvien">Đăng ký tài khoản</a></p>
		        </c:when>
		        <c:when test="${param.role == 'CongTy'}">
		            <p>Chưa có tài khoản? <a href="${pageContext.request.contextPath}/signup/congty">Đăng ký tài khoản</a></p>
		        </c:when>
		    </c:choose>
		</div>

	</div>

	<!-- JavaScript xử lý -->
	<script nonce="<%= nonce %>">
	function checkLoginState() {
	    FB.getLoginStatus(function(response) {
	        if (response.status === 'connected') {
	            const accessToken = response.authResponse.accessToken;
	            window.location.href = "facebook_login?access_token=" + accessToken;
	        } else {
	            console.log("User cancelled login or did not fully authorize.");
	        }
	    });
	}
	</script>

	<script nonce="<%= nonce %>">
	function checkLoginMessage() {
	    const urlParams = new URLSearchParams(window.location.search);
	    if (urlParams.get('error') === '1') {
	        alert("Login failed! Please check your username and password.");
	        removeErrorParam();
	    }
	    if (urlParams.get('success') === '1') {
	        alert("Successfully signed up! You can log in now!");
	        removeSuccessParam();
	    }
	}
	function removeErrorParam() {
	    const url = new URL(window.location.href);
	    url.searchParams.delete('error');
	    window.history.replaceState({}, document.title, url);
	}
	function removeSuccessParam() {
	    const url = new URL(window.location.href);
	    url.searchParams.delete('success');
	    window.history.replaceState({}, document.title, url);
	}
	</script>

	<script nonce="<%= nonce %>">
	function getUrlParameter(name) {
	    const urlParams = new URLSearchParams(window.location.search);
	    return urlParams.get(name);
	}
	function redirectToGoogleLogin() {
	    const role = getUrlParameter('role');
	    if (role) {
	        window.location.href = 'LoginGoogleServlet?role=' + role;
	    } else {
	        window.location.href = 'LoginGoogleServlet';
	    }
	}
	</script>

	<script nonce="<%= nonce %>">
	function handleCredentialResponse(response) {
	    console.log('Đăng nhập không kích hoạt: ', response);
	}
	</script>


	<script nonce="<%= nonce %>">
	document.addEventListener("DOMContentLoaded", function () {
	    checkLoginMessage();
	});
	
	</script>
	<script nonce="<%= nonce %>">
    document.addEventListener("DOMContentLoaded", function () {
        const googleBtn = document.getElementById("loginGoogle");
        if (googleBtn) {
            googleBtn.addEventListener("click", redirectToGoogleLogin);
        }
    });
</script>
</body>
</html>
