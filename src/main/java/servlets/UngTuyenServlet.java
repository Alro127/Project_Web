package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.AuthUtil;
import utils.CSRFTokenManager;

import java.io.IOException;

import beans.TaiKhoan;
import dao.CVDAO;
import dao.CongViecDAO;
import dao.HoSoDAO;

/**
 * Servlet implementation class UngTuyenServlet
 */
public class UngTuyenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UngTuyenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!AuthUtil.authorizeRole(request, response, "UngVien")) return;
        
        HttpSession session = request.getSession(false);
        
        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("account");

        try {
            String idCVStr = request.getParameter("idCV");
            String idCongViecStr = request.getParameter("idCongViec");

            if (idCVStr == null || idCongViecStr == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu tham số idCV hoặc idCongViec.");
                return;
            }

            int idCV = Integer.parseInt(idCVStr);
            int idCongViec = Integer.parseInt(idCongViecStr);

            if (!CVDAO.isCVBelongsToUser(idCV, taiKhoan.getId())) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền gửi hồ sơ này.");
                return;
            }

            // Gọi DAO xử lý
            boolean isSuccessful = HoSoDAO.AddHoSo(idCV, idCongViec);

            response.setContentType("text/html;charset=UTF-8");
            if (isSuccessful) {
                CongViecDAO.updateLuotNop(idCongViec);
                response.getWriter().write("<script>alert('Ứng tuyển thành công!'); window.location.href=document.referrer;</script>");
            } else {
                response.getWriter().write("<script>alert('Bạn đã gửi hồ sơ này rồi!'); window.location.href=document.referrer;</script>");
            }

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "idCV hoặc idCongViec không hợp lệ.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi hệ thống.");
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
