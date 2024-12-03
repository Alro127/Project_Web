package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import beans.CongViec;
import beans.User;
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
	    
	    List<String> linhVucs = new ArrayList<>();
		try {
			linhVucs = CongViecDAO.getListLinhVuc();
		} catch (Exception e) {
			e.printStackTrace();
		}	  
		request.setAttribute("linhVucs", linhVucs);
	    
		// Lấy param
		String linhVuc = request.getParameter("linhVuc");
		String thoiGian = request.getParameter("luotXem");
		String luotXem = request.getParameter("luotXem");
		String luotNop = request.getParameter("luotNop");
		String searchText = request.getParameter("searchText");
		if (linhVuc != null && !linhVuc.isEmpty()) {
		        congViecs = congViecs.stream()
		                             .filter(cv -> linhVuc.equals(cv.getLinhVuc()))
		                             .collect(Collectors.toList());
		}
		if (thoiGian != null && !thoiGian.isEmpty()) {
			 if ("1".equals(thoiGian)) {
			        // Sắp xếp theo thời gian từ mới nhất đến cũ nhất
			        congViecs = congViecs.stream()
			                             .sorted(Comparator.comparing(CongViec::getThoiGianDang).reversed()) // Sắp xếp theo thời gian đăng
			                             .collect(Collectors.toList());
			    } else if ("2".equals(thoiGian)) {
			        // Sắp xếp theo thời gian từ cũ nhất đến mới nhất
			        congViecs = congViecs.stream()
			                             .sorted(Comparator.comparing(CongViec::getThoiGianDang)) // Sắp xếp theo thời gian đăng
			                             .collect(Collectors.toList());
			    }
		}
		if (luotXem != null && !luotXem.isEmpty()) {
			 if ("1".equals(luotXem)) {
			        // Sắp xếp theo lượt xem cao nhất tới thấp nhất
			        congViecs = congViecs.stream()
			                             .sorted(Comparator.comparing(CongViec::getLuotXem).reversed()) // Sắp xếp theo thời gian đăng
			                             .collect(Collectors.toList());
			    } else if ("2".equals(luotXem)) {
			        // Sắp xếp theo lượt xem thấp nhất tới cao nhất
			        congViecs = congViecs.stream()
			                             .sorted(Comparator.comparing(CongViec::getLuotXem)) // Sắp xếp theo thời gian đăng
			                             .collect(Collectors.toList());
			    }
		}
		if (luotNop != null && !luotNop.isEmpty()) {
			 if ("1".equals(luotNop)) {
			        // Sắp xếp theo lượt xem cao nhất tới thấp nhất
			        congViecs = congViecs.stream()
			                             .sorted(Comparator.comparing(CongViec::getLuotNop).reversed()) // Sắp xếp theo thời gian đăng
			                             .collect(Collectors.toList());
			    } else if ("2".equals(luotNop)) {
			        // Sắp xếp theo lượt xem thấp nhất tới cao nhất
			        congViecs = congViecs.stream()
			                             .sorted(Comparator.comparing(CongViec::getLuotNop)) // Sắp xếp theo thời gian đăng
			                             .collect(Collectors.toList());
			    }
		}
		if (searchText != null && !searchText.isEmpty()) {
		        congViecs = congViecs.stream()
		                             .filter(cv -> cv.getTen().toLowerCase().contains(searchText.toLowerCase())) // So sánh không phân biệt chữ hoa chữ thường
		                             .collect(Collectors.toList());
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
