package servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.Platform;
import org.json.JSONObject;

import beans.CongTy;
import beans.CongViec;
import beans.TaiKhoan;
import dao.CongTyDAO;
import dao.CongViecDAO;
import dao.TaiKhoanDAO;

/**
 * Servlet implementation class TaiKhoanCongTy
 */
public class TaiKhoanCongTyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaiKhoanCongTyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TaiKhoan tk = TaiKhoanDAO.getTaiKhoanById(Integer.parseInt((String)request.getSession(true).getAttribute("id")));
		CongTy congTy = new CongTy();
	    try {
	        congTy = CongTyDAO.GetCongTyById(Integer.parseInt((String)request.getSession(true).getAttribute("id")));
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    request.setAttribute("congTy", congTy);
	    request.setAttribute("tk", tk);
	    request.getRequestDispatcher("/TaiKhoanCongTy.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		HttpSession session = request.getSession(true);
		int id = Integer.parseInt((String)session.getAttribute("id"));
		BufferedReader reader = request.getReader();
        StringBuilder jsonString = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonString.append(line);
        }

        try {
            JSONObject json = new JSONObject(jsonString.toString());
            String imageSrc = json.getString("imageSrc"); // Base64 c?a ?nh
            String fileName = json.getString("fileName"); // Tên t?p t? client

            // Tách ph?n Base64 d? l?y d? li?u
            String base64Image = imageSrc.split(",")[1];
            byte[] imageBytes = Base64.getDecoder().decode(base64Image);

            // Xác d?nh du?ng d?n luu ?nh
            String relativePath = "src/main/webapp/assets/images/act";
            String absolutePath = "D:\\Project\\Project_Web\\src\\main\\webapp\\assets\\images\\act";  
            String aString = getServletContext().getRealPath("");
			/*
			 * for (String path : absolutePath) { System.out.println(path); }
			 */
            File directory = new File(absolutePath);
            if (!directory.exists()) {
                directory.mkdirs(); // T?o thu m?c n?u chua t?n t?i
            }
            
            // Ðu?ng d?n d?y d? c?a ?nh
            String fullImagePath = absolutePath + File.separator + fileName;

            // Luu t?p vào thu m?c và csdl
            try (FileOutputStream fos = new FileOutputStream(fullImagePath)) {
                fos.write(imageBytes);
                CongTyDAO.addHinhAnhHoatDong(id, "assets/images/act"+ File.separator + fileName);
            }

            // Tr? du?ng d?n tuong d?i v? client
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("status", "success");
            jsonResponse.put("imagePath", relativePath + "/" + fileName); // Ðu?ng d?n ?nh tr? v?

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonResponse.toString());
        } catch (Exception e) {
            e.printStackTrace();

            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("status", "error");
            jsonResponse.put("message", "L?i khi x? lý ?nh!");

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonResponse.toString());
        }
	}

}
