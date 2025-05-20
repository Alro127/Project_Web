package servlets;

import beans.CV;
import dao.CVDAO;
import java.io.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

import beans.CongViec;
import dao.CongViecDAO;

public class LoadCVServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Gọi phương thức từ CVDAO để lấy danh sách CV
    	int IdCV = Integer.parseInt(request.getParameter("id"));
    	String mode = request.getParameter("mode");
        try {
        	CVDAO cvdao = new CVDAO();
            CV cv= cvdao.getCVbyId(IdCV);
            // Đưa CV vào thuộc tính của request để truy cập trong JSP
            request.setAttribute("cv", cv);
            
            // Chuyển hướng tới trang JSP để hiển thị
            if(mode.equalsIgnoreCase("view")) {
            	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/fragments/frg_ViewCV.jsp");
            	dispatcher.forward(request, response);
            }
            else {
            	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/ThongTinCV_edit.jsp");
            	dispatcher.forward(request, response);
            }
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Hiển thị lỗi nếu có vấn đề trong quá trình lấy dữ liệu
            response.getWriter().println("Error loading CV data: " + e.getMessage());
        }
    }
}
