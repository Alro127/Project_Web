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
import java.util.stream.Collectors;

import com.google.gson.Gson;

import beans.CongViec;
import dao.CongViecDAO;
import dao.CongViecYeuThichDAO;

/**
 * Servlet implementation class CongViecLienQuanServlet
 */
public class CongViecLienQuanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CongViecLienQuanServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String idStr = request.getParameter("id");

        try {
            int id = Integer.parseInt(idStr);
            request.setAttribute("idCongViec", id);

            CongViec congViec = CongViecDAO.getCongViecById(id);
            if (congViec == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Công việc không tồn tại");
                return;
            }

            List<CongViec> congViecs = CongViecDAO.GetListCongViec();
            List<CongViec> congViecLienQuans = CongViec.sortBySimilarity(congViec, congViecs);

            // Lấy danh sách lĩnh vực và tỉnh thành
            List<String> linhVucs = CongViecDAO.getListLinhVuc();
            List<String> tinhThanhs = CongViecDAO.getListTinhThanh();

            request.setAttribute("linhVucs", linhVucs);
            request.setAttribute("tinhThanhs", tinhThanhs);
            double minLuong = CongViec.findMinLuong(congViecLienQuans);
    		double maxLuong = CongViec.findMaxLuong(congViecLienQuans);
            request.setAttribute("minLuong", minLuong);
    		request.setAttribute("maxLuong", maxLuong);
            // Lọc công việc
            String linhVuc = request.getParameter("linhVuc");
            String tinhThanh = request.getParameter("tinhThanh");
            String ten = request.getParameter("ten");
            String kinhNghiem = request.getParameter("kinhNghiem");
    	    String luongKhoiDiemHienTai = request.getParameter("luongKhoiDiemHienTai");
    	    String LuongKetThucHienTai = request.getParameter("LuongKetThucHienTai");
    	    
            congViecLienQuans = CongViec.LocLinhVuc(congViecLienQuans, linhVuc);
            congViecLienQuans = CongViec.LocTinhThanh(congViecLienQuans, tinhThanh);
            congViecLienQuans = CongViec.LocTen(congViecLienQuans, ten);
            congViecLienQuans = CongViec.LocLinhVuc(congViecLienQuans, linhVuc);
            congViecLienQuans = CongViec.LocTinhThanh(congViecLienQuans, tinhThanh);
            congViecLienQuans = CongViec.LocTen(congViecLienQuans, ten);
    	    if (kinhNghiem != null) {
    	    	congViecLienQuans = CongViec.findInExperince(congViecLienQuans, Integer.parseInt(kinhNghiem));
    		}
    	    if (luongKhoiDiemHienTai != null && LuongKetThucHienTai != null) {
    	    	congViecLienQuans = CongViec.findInRangeLuong(congViecLienQuans, Double.parseDouble(luongKhoiDiemHienTai), Double.parseDouble(LuongKetThucHienTai));
    		}
            // Phân trang
            int pageSize = 30;
            int page = 1;
            String pageStr = request.getParameter("page");

            if (pageStr != null && !pageStr.isEmpty()) {
                try {
                    page = Integer.parseInt(pageStr);
                } catch (NumberFormatException e) {
                    page = 1;
                }
            }

            int totalCongViecs = congViecLienQuans.size();
            int totalPages = (int) Math.ceil((double) totalCongViecs / pageSize);
            int startIndex = (page - 1) * pageSize;
            int endIndex = Math.min(startIndex + pageSize, totalCongViecs);

            List<CongViec> pagedCongViecs = congViecLienQuans.subList(startIndex, endIndex);

            boolean isAjax = "true".equals(request.getParameter("ajax"));
            if (isAjax) {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                Map<String, Object> responseData = new HashMap<>();
                responseData.put("congViecs", pagedCongViecs);
                responseData.put("totalPages", totalPages);
                responseData.put("currentPage", page);

                Gson gson = new Gson();
                String json = gson.toJson(responseData);
                response.getWriter().write(json);
            } else {
                request.setAttribute("congViecs", congViecLienQuans);
                request.getRequestDispatcher("/WEB-INF/views/TrangCongViecLienQuan.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID không hợp lệ");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi hệ thống");
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
