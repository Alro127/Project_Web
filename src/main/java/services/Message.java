package services;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletResponse;

public class Message {
	public static void alertAndRedirect(HttpServletResponse response, String message, String redirectUrl) throws IOException {
	    response.setContentType("text/html;charset=UTF-8");
	    try (PrintWriter out = response.getWriter()) {
	        out.println("<script>");
	        out.println("alert('" + message.replace("'", "\\'") + "');");
	        out.println("window.location.href='" + redirectUrl + "';");
	        out.println("</script>");
	    }
	}
}
