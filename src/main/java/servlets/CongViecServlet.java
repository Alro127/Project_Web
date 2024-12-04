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
import java.util.stream.Collectors;

import com.google.gson.Gson;

import beans.CongViec;
import dao.CongViecDAO;

/**
 * Servlet implementation class CongViecServlet
 */
public class CongViecServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CongViecServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	private Map<String, Integer> countJobsByField(List<CongViec> congViecs) {
	    // Sử dụng Map để lưu lĩnh vực và số lượng công việc
	    Map<String, Integer> fieldCountMap = new HashMap<>();

	    // Điền dữ liệu vào Map
	    for (CongViec congViec : congViecs) {
	        String field = congViec.getLinhVuc(); // Lấy lĩnh vực từ đối tượng CongViec
	        fieldCountMap.put(field, fieldCountMap.getOrDefault(field, 0) + 1);
	    }

	    return fieldCountMap;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    // Lấy toàn bộ danh sách công việc từ cơ sở dữ liệu
	    List<CongViec> congViecs = new ArrayList<>();
	    try {
	        congViecs = CongViecDAO.GetListCongViec();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    Map<String, Integer> jobCountsByField = countJobsByField(congViecs);
	    request.setAttribute("jobCountsByField", jobCountsByField);
		
		List<String> linhVucs = new ArrayList<>();
		try {
			linhVucs = CongViecDAO.getListLinhVuc();
		} catch (Exception e) {
			e.printStackTrace();
		}	  
		request.setAttribute("linhVucs", linhVucs);
		
		List<String> tinhThanhs = new ArrayList<>();
		try {
			tinhThanhs = CongViecDAO.getListTinhThanh();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("tinhThanhs", tinhThanhs);
		
		// Lọc theo lĩnh vực và tỉnh thành (nếu có)
	    String linhVuc = request.getParameter("linhVuc");
	    String tinhThanh = request.getParameter("tinhThanh");
	    String ten = request.getParameter("ten");  // Lấy giá trị tìm kiếm theo tên công việc
	    if (linhVuc != null && !linhVuc.isEmpty()) {
	        congViecs = congViecs.stream()
	                             .filter(cv -> linhVuc.equals(cv.getLinhVuc()))
	                             .collect(Collectors.toList());
	    }

	    if (tinhThanh != null && !tinhThanh.isEmpty()) {
	        congViecs = congViecs.stream()
	                             .filter(cv -> tinhThanh.equals(cv.getDiaDiem()))
	                             .collect(Collectors.toList());
	    }
		
	    if (ten != null && !ten.isEmpty()) {
	        congViecs = congViecs.stream()
	                             .filter(cv -> cv.getTen().toLowerCase().contains(ten.toLowerCase())) // So sánh không phân biệt chữ hoa chữ thường
	                             .collect(Collectors.toList());
	    }
		// Số công việc mỗi trang
	    int pageSize = 9;
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
	    //responseData.put("listLinhvuc", linhVucs);

	    // Chỉ trả về JSON một lần
	    String jsonResponse = new Gson().toJson(responseData);
	    response.getWriter().write(jsonResponse);
	    
	    // Nếu không phải AJAX, bạn có thể chuyển hướng sang JSP
	    if (!"true".equals(request.getParameter("ajax"))) {
	        request.getRequestDispatcher("TrangGioiThieu.jsp").forward(request, response);
	    }

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
