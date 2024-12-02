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
        // Chuyển đổi từ String sang Date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date educationStart = null, educationEnd = null, experienceStart = null, experienceEnd = null, dob = null;

        try {
        	dob = new Date(sdf.parse(request.getParameter("dob")).getTime());
            educationStart = new Date(sdf.parse(request.getParameter("educationStart")).getTime());
            educationEnd = new Date(sdf.parse(request.getParameter("educationEnd")).getTime());
            experienceStart = new Date(sdf.parse(request.getParameter("experienceStart")).getTime());
            experienceEnd = new Date(sdf.parse(request.getParameter("experienceEnd")).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Lấy dữ liệu từ form
        String fullName = request.getParameter("fullName");
        String position = request.getParameter("position");
        String gender = request.getParameter("gender");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String location = request.getParameter("location");
        String address = request.getParameter("address");
        String introduction = request.getParameter("introduction");
        String careerGoals = request.getParameter("careerGoals");
        
        // Tạo đối tượng CV và gán thông tin vào
        CV cv = new CV();
        cv.getUngvien().setFullName(fullName);
        cv.setPosition(position);
        cv.getUngvien().setGender(gender);
        cv.getUngvien().setDob(dob);
        cv.getUngvien().setPhone(phone);
        cv.getUngvien().setEmail(email);
        cv.getUngvien().setLocation(location);
        cv.getUngvien().setAddress(address);
        cv.getUngvien().setIntroduction(introduction);
        cv.setCareerGoals(careerGoals);
        
        String educationSchool = request.getParameter("educationSchool");
        String educationMajor = request.getParameter("educationMajor");
        String educationDescription = request.getParameter("educationDescription");

        String experienceCompany = request.getParameter("experienceCompany");
        String experiencePosition = request.getParameter("experiencePosition");
        String experienceDescription = request.getParameter("experienceDescription");
        
        String certificates = request.getParameter("certificates");
        String skills = request.getParameter("skills");


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
