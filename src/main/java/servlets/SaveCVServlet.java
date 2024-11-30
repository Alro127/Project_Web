package servlets;

import beans.CV;
import dao.CVDAO;
import java.io.*;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

import beans.CongViec;
import dao.CongViecDAO;

public class SaveCVServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy dữ liệu từ form
        String fullName = request.getParameter("fullName");
        String position = request.getParameter("position");
        String personalInfo = request.getParameter("personalInfo");
        String careerGoals = request.getParameter("careerGoals");

        // Chuyển đổi từ String sang Date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date educationStart = null, educationEnd = null, experienceStart = null, experienceEnd = null;

        try {
            educationStart = new Date(sdf.parse(request.getParameter("educationStart")).getTime());
            educationEnd = new Date(sdf.parse(request.getParameter("educationEnd")).getTime());
            experienceStart = new Date(sdf.parse(request.getParameter("experienceStart")).getTime());
            experienceEnd = new Date(sdf.parse(request.getParameter("experienceEnd")).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String educationSchool = request.getParameter("educationSchool");
        String educationMajor = request.getParameter("educationMajor");
        String educationDescription = request.getParameter("educationDescription");

        String experienceCompany = request.getParameter("experienceCompany");
        String experiencePosition = request.getParameter("experiencePosition");
        String experienceDescription = request.getParameter("experienceDescription");

        String certificates = request.getParameter("certificates");
        String skills = request.getParameter("skills");
        String hobbies = request.getParameter("hobbies");

        // Tạo đối tượng CV
        CV cv = new CV(fullName, position, personalInfo, careerGoals, educationStart, educationEnd,
                       educationSchool, educationMajor, educationDescription, experienceStart, experienceEnd,
                       experienceCompany, experiencePosition, experienceDescription, certificates, skills, hobbies);

        try {
            boolean success = CVDAO.addCV(cv);
            if (success) {
                response.sendRedirect("cv-list.jsp"); // Redirect after successful save
            } else {
                response.getWriter().println("Error while saving CV.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
