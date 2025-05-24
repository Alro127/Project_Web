package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.AuthUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;

import beans.CV;
import beans.TaiKhoan;
import dao.CVDAO;


public class QuanLyCVServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public QuanLyCVServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	if (!AuthUtil.authorizeRole(request, response, "UngVien")) return;
		
		HttpSession session = request.getSession(false);
		
		TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("account");
    	
        try {

            int id = taiKhoan.getId();

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
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/QuanLyCV.jsp");
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
