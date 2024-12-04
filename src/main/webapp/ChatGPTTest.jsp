<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>ChatGPT Integration</title>
<script>
	    async function sendMessage() {
	        const message = document.getElementById('message').value;
	        const response = await fetch('ChatGPTServlet', {
	            method: 'POST',
	            headers: {
	                'Content-Type': 'application/x-www-form-urlencoded',
	            },
	            body: `message=${message}`,
	        });
	
	        if (response.ok && response.headers.get('Content-Type').includes('application/json')) {
	            try {
	                const data = await response.json();
	                if (data.choices && data.choices.length > 0 && data.choices[0].message && data.choices[0].message.content) {
	                    document.getElementById('response').innerText = data.choices[0].message.content;
	                } else {
	                    document.getElementById('response').innerText = 'API trả về dữ liệu rỗng.';
	                }
	            } catch (error) {
	                document.getElementById('response').innerText = `Lỗi khi phân tích dữ liệu: ${error.message}`;
	            }
	        } else {
	            const errorDetails = await response.text();
	            document.getElementById('response').innerText = `Lỗi ${response.status}: ${response.statusText}. Chi tiết: ${errorDetails}`;
	        }
	    }

	</script>


</head>
<body>
	<h1>ChatGPT Integration</h1>
	<textarea id="message" name="message"
		placeholder="Enter your message here"></textarea>
	<button onclick="sendMessage()">Send</button>
	<div id="response" style="margin-top: 20px;"></div>
</body>
</html>