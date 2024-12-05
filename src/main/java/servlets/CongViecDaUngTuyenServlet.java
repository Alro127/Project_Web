package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import beans.HoSo;
import dao.HoSoDAO;

/**
 * Servlet implementation class CongViecDaUngTuyenServlet
 */
public class CongViecDaUngTuyenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CongViecDaUngTuyenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);  
		if (session == null) {
			response.sendRedirect("Login.jsp"); 
			return;
		}

		// Kiểm tra xem session có chứa id người dùng không
		String idUVStr = (String) session.getAttribute("id");
		if (idUVStr == null) {
			response.sendRedirect("Login.jsp"); 
			return;
		}
		int idUV= Integer.parseInt(idUVStr);
		
		List<HoSo> hoSos = new ArrayList<>();
		
		try {
	        hoSos = HoSoDAO.GetListHoSoByIdUV(idUV);
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

	    int totalHoSos = hoSos.size();
	    int totalPages = (int) Math.ceil((double) totalHoSos / pageSize);

	    int startIndex = (page - 1) * pageSize;
	    int endIndex = Math.min(startIndex + pageSize, totalHoSos);
	    List<HoSo> pagedHoSos = hoSos.subList(startIndex, endIndex);

	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");

	    Map<String, Object> responseData = new HashMap<>();
	    responseData.put("hoSos", pagedHoSos);
	    responseData.put("totalPages", totalPages);
	    responseData.put("currentPage", page);

	    String jsonResponse = new Gson().toJson(responseData);
	    System.out.println(jsonResponse); 
	    response.getWriter().write(jsonResponse);

	    // Nếu không phải AJAX, bạn có thể chuyển hướng sang JSP
	    if (!"true".equals(request.getParameter("ajax"))) {
	        request.getRequestDispatcher("CongViecDaUngTuyen.jsp").forward(request, response);
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
