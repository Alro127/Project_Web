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

            // Lọc công việc
            String linhVuc = request.getParameter("linhVuc");
            String tinhThanh = request.getParameter("tinhThanh");
            String ten = request.getParameter("ten");

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
                                     .filter(cv -> cv.getTen().toLowerCase().contains(ten.toLowerCase()))
                                     .collect(Collectors.toList());
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

            int totalCongViecs = congViecs.size();
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
                request.getRequestDispatcher("TrangCongViecLienQuan.jsp").forward(request, response);
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
