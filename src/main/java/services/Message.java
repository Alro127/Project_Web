package services;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Message {
    public static void alertAndRedirect(HttpServletRequest request, HttpServletResponse response, String message, String redirectPath) throws IOException {
        String context = request.getContextPath(); // /Project_Web
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<script>");
            out.println("alert('" + message.replace("'", "\\'") + "');");
            out.println("window.location.href='" + context + "/" + redirectPath + "';");
            out.println("</script>");
        }
    }
}
