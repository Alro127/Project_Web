package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.AuthUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import beans.CongViec;
import beans.TaiKhoan;
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
		
		if (!AuthUtil.authorizeRole(request, response, "CongTy")) return;
        
        HttpSession session = request.getSession(false);
        
        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("account");
		
		List<CongViec> congViecs = new ArrayList<>();
	    try {
	        congViecs = CongViecDAO.GetListCongViecByIdCT(taiKhoan.getId());
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
		String thoiGian = request.getParameter("thoiGian");
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
	    
	    String isAjax = request.getParameter("ajax");

	    if ("true".equals(isAjax)) {
	        // Trả JSON nếu là AJAX
	        Map<String, Object> responseData = new HashMap<>();
	        responseData.put("congViecs", pagedCongViecs);
	        responseData.put("totalPages", totalPages);
	        responseData.put("currentPage", page);

	        String jsonResponse = new Gson().toJson(responseData);
	        response.setContentType("application/json");
	        response.getWriter().write(jsonResponse);
	    } else {
	        // Nếu không phải AJAX thì forward
	        request.setAttribute("congViecs", pagedCongViecs);
	        request.setAttribute("totalPages", totalPages);
	        request.setAttribute("currentPage", page);
	        request.getRequestDispatcher("/WEB-INF/views/QuanLyTinDang.jsp").forward(request, response);
	    }

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    if (!AuthUtil.authorizeRole(request, response, "CongTy")) return;

	    try {
	        int idCongViec = Integer.parseInt(request.getParameter("id"));
	        TaiKhoan taiKhoan = (TaiKhoan) request.getSession(false).getAttribute("account");

	        // ✅ Kiểm tra công việc có thuộc về công ty đang đăng nhập không
	        if (!CongViecDAO.isCongViecBelongsToCompany(idCongViec, taiKhoan.getId())) {
	            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền xóa công việc này.");
	            return;
	        }

	        if (CongViecDAO.DeleteCongViecById(idCongViec)) {
	            response.getWriter().println("<script>alert('Công việc đã được xóa thành công!');</script>");
	        } else {
	            response.getWriter().println("<script>alert('Có lỗi xảy ra khi xóa công việc.');</script>");
	        }

	    } catch (NumberFormatException e) {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID không hợp lệ.");
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi hệ thống.");
	    }

	    doGet(request, response);
	}


}
