package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;

import beans.CV;
import dao.CVDAO;


public class QuanLyCVServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public QuanLyCVServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
    		HttpSession session = request.getSession();
    		String idTaiKhoan= (String) session.getAttribute("id");
    		int id = Integer.parseInt(idTaiKhoan);
        	CVDAO cvdao = new CVDAO();
            List<CV> cvList= cvdao.getAllCVbyIdUV(id);
            // Đưa CV vào thuộc tính của request để truy cập trong JSP
            request.setAttribute("cvList", cvList);
            // Chuyển hướng tới trang JSP để hiển thị
            RequestDispatcher dispatcher = request.getRequestDispatcher("QuanLyCV.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            // Hiển thị lỗi nếu có vấn đề trong quá trình lấy dữ liệu
            response.getWriter().println("Error loading CV data: " + e.getMessage());
        }
	}*/
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            String idTaiKhoan = (String) session.getAttribute("id");

            if (idTaiKhoan == null) {
                // Nếu không có session, chuyển hướng đến trang đăng nhập
                response.sendRedirect("login.jsp");
                return;
            }

            int id = Integer.parseInt(idTaiKhoan);

            CVDAO cvdao = new CVDAO();
            List<CV> cvList = cvdao.getAllCVbyIdUV(id);

            // Kiểm tra xem yêu cầu có phải là AJAX không
            String requestType = request.getHeader("X-Requested-With");

            if ("XMLHttpRequest".equals(requestType)) {
                // Nếu yêu cầu là AJAX, trả về dữ liệu dưới dạng JSON
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                // Kiểm tra xem cvList có chứa dữ liệu không
                if (cvList == null || cvList.isEmpty()) {
                    response.getWriter().write("{\"error\": \"Không có CV nào.\"}");
                } else {
                    // Sử dụng Gson để chuyển danh sách cvList thành JSON
                    Gson gson = new Gson();
                    String json = gson.toJson(cvList);

                    // Gửi dữ liệu JSON về phía client
                    response.getWriter().write(json);
                    System.out.println(json); // Log dữ liệu để kiểm tra
                }
            } else {
                // Nếu không phải là yêu cầu AJAX, chuyển tiếp đến JSP
                request.setAttribute("cvList", cvList);
                RequestDispatcher dispatcher = request.getRequestDispatcher("QuanLyCV.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gửi lỗi về phía client nếu có vấn đề khi lấy dữ liệu
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"Lỗi khi tải dữ liệu CV.\"}");
        } catch (NumberFormatException e) {
            // Xử lý lỗi khi chuyển đổi idTaiKhoan thành số nguyên
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"ID tài khoản không hợp lệ.\"}");
        }
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
