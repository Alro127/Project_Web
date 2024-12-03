package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import beans.CongViec;
import dao.CongViecDAO;

/**
 * Servlet implementation class QuanLyTinDangServlet
 */
public class QuanLyTinDangServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuanLyTinDangServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		List<CongViec> congViecs = new ArrayList<>();
	    try {
	    	//int id = Integer.parseInt(request.getParameter("id"));
	        congViecs = CongViecDAO.GetListCongViecByIdCT(Integer.parseInt((String)request.getSession(true).getAttribute("id")));
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    int pageSize = 20;
	    int page = 1;  // Mặc định là trang đầu tiên

	    // Lấy tham số 'page' từ yêu cầu
	    String pageStr = request.getParameter("page");
	    if (pageStr != null && !pageStr.isEmpty()) {
	        try {
	            page = Integer.parseInt(pageStr);
	        } catch (NumberFormatException e) {
	            page = 1;  // Nếu tham số không hợp lệ, sử dụng trang đầu tiên
	        }
	    }

	    // Tính toán số trang (tổng số công việc / số công việc mỗi trang)
	    int totalCongViecs = congViecs.size();
	    int totalPages = (int) Math.ceil((double) totalCongViecs / pageSize);

	    // Lấy công việc cho trang hiện tại
	    int startIndex = (page - 1) * pageSize;
	    int endIndex = Math.min(startIndex + pageSize, totalCongViecs);
	    List<CongViec> pagedCongViecs = congViecs.subList(startIndex, endIndex);

	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");

	    Map<String, Object> responseData = new HashMap<>();
	    responseData.put("congViecs", pagedCongViecs);
	    responseData.put("totalPages", totalPages);
	    responseData.put("currentPage", page);

	    // Chỉ trả về JSON một lần
	    String jsonResponse = new Gson().toJson(responseData);
	    System.out.println(jsonResponse); // Kiểm tra dữ liệu JSON trước khi trả về
	    response.getWriter().write(jsonResponse);

	    // Nếu không phải AJAX, bạn có thể chuyển hướng sang JSP
	    if (!"true".equals(request.getParameter("ajax"))) {
	        request.getRequestDispatcher("QuanLyTinDang.jsp").forward(request, response);
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
