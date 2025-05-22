package servlets;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.CV;
import beans.ChungChi;
import beans.HocVan;
import beans.KinhNghiem;
import beans.KyNang;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.CSRFTokenManager;
import conn.SQLServerConnection;
import dao.CVDAO;
import filters.HTMLSanitizer;

public class SaveCVServlet extends HttpServlet {

	private final ObjectMapper objectMapper = new ObjectMapper();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Thiết lập kiểu trả về là JSON
	    response.setContentType("application/json");
	    PrintWriter out = response.getWriter();
	    
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	    // Đọc dữ liệu JSON từ request body
	    StringBuilder stringBuilder = new StringBuilder();
	    String line;
	    try (BufferedReader reader = request.getReader()) {
	        while ((line = reader.readLine()) != null) {
	            stringBuilder.append(line);
	        }
	    }
	    String jsonString = stringBuilder.toString();
	    
	    try {
	        // Chuyển chuỗi JSON thành đối tượng JSONObject
	        JSONObject jsonObject = new JSONObject(jsonString);

	        // Lấy các giá trị từ JSON
	        String position = jsonObject.optString("position");
	        String careerGoals = jsonObject.optString("careerGoals");
	        JSONArray educationArray = jsonObject.optJSONArray("educationData");
	        JSONArray experienceArray = jsonObject.optJSONArray("experienceData");
	        JSONArray certificateArray = jsonObject.optJSONArray("certificateData");
	        JSONArray skillArray = jsonObject.optJSONArray("skillData");
	        String mode =jsonObject.optString("mode");
	        String IdCV = jsonObject.optString("IdCV");

	        // Chuyển dữ liệu thành các đối tượng entity
			/*
			 * CareerGoals careerGoalsEntity = new CareerGoals();
			 * careerGoalsEntity.setGoal(careerGoals);
			 */
	        
	        List<HocVan> educationList = new ArrayList<>();
	        for (int i = 0; i < educationArray.length(); i++) {
	            JSONObject educationObject = educationArray.getJSONObject(i);
	            HocVan educationEntity = new HocVan();
	            String startStr = educationObject.optString("start");
	            String endStr = educationObject.optString("end");
	            
	            try {
	                if (!startStr.isEmpty()) {
	                    // Chuyển chuỗi start thành java.sql.Date
	                    java.util.Date startUtilDate = dateFormat.parse(startStr); // Chuyển chuỗi thành java.util.Date
	                    educationEntity.setStart(new java.sql.Date(startUtilDate.getTime())); // Chuyển thành java.sql.Date
	                }
	                if (!endStr.isEmpty()) {
	                    // Chuyển chuỗi end thành java.sql.Date
	                    java.util.Date endUtilDate = dateFormat.parse(endStr); // Chuyển chuỗi thành java.util.Date
	                    educationEntity.setEnd(new java.sql.Date(endUtilDate.getTime())); // Chuyển thành java.sql.Date
	                }
	            } catch (Exception e) {
	                // Xử lý lỗi nếu chuỗi ngày không hợp lệ
	                e.printStackTrace();
	            }
	            educationEntity.setSchool(HTMLSanitizer.sanitizeInput(educationObject.optString("school")));
	            educationEntity.setMajor(HTMLSanitizer.sanitizeInput(educationObject.optString("major")));
	            educationEntity.setDescription(HTMLSanitizer.sanitizeInput(educationObject.optString("description")));
				/*
				 * educationEntity.setSchool(educationObject.optString("school"));
				 * educationEntity.setMajor(educationObject.optString("major"));
				 * educationEntity.setDescription(educationObject.optString("description"));
				 */
	            educationList.add(educationEntity);
	        }
	        
	        List<KinhNghiem> experienceList = new ArrayList<>();
	        for (int i = 0; i < experienceArray.length(); i++) {
	            JSONObject experienceObject = experienceArray.getJSONObject(i);
	            KinhNghiem experienceEntity = new KinhNghiem();
	            
	            String startStr = experienceObject.optString("start");
	            String endStr = experienceObject.optString("end");
	            
	            try {
	                if (!startStr.isEmpty()) {
	                    // Chuyển chuỗi start thành java.sql.Date
	                    java.util.Date startUtilDate = dateFormat.parse(startStr); // Chuyển chuỗi thành java.util.Date
	                    experienceEntity.setStart(new java.sql.Date(startUtilDate.getTime())); // Chuyển thành java.sql.Date
	                }
	                if (!endStr.isEmpty()) {
	                    // Chuyển chuỗi end thành java.sql.Date
	                    java.util.Date endUtilDate = dateFormat.parse(endStr); // Chuyển chuỗi thành java.util.Date
	                    experienceEntity.setEnd(new java.sql.Date(endUtilDate.getTime())); // Chuyển thành java.sql.Date
	                }
	            } catch (Exception e) {
	                // Xử lý lỗi nếu chuỗi ngày không hợp lệ
	                e.printStackTrace();
	            }
				/*
				 * experienceEntity.setCompany(experienceObject.optString("company"));
				 * experienceEntity.setPosition(experienceObject.optString("position"));
				 * experienceEntity.setDescription(experienceObject.optString("description"));
				 */
	            
	            experienceEntity.setCompany(HTMLSanitizer.sanitizeInput(experienceObject.optString("company")));
	            experienceEntity.setPosition(HTMLSanitizer.sanitizeInput(experienceObject.optString("position")));
	            experienceEntity.setDescription(HTMLSanitizer.sanitizeInput(experienceObject.optString("description")));

	            experienceList.add(experienceEntity);
	        }
	        
	        List<ChungChi> certificateList = new ArrayList<>();
	        for (int i = 0; i < certificateArray.length(); i++) {
	            JSONObject certificateObject = certificateArray.getJSONObject(i);
	            ChungChi certificateEntity = new ChungChi();
				/* certificateEntity.setName(certificateObject.optString("name")); */
	            certificateEntity.setName(HTMLSanitizer.sanitizeInput(certificateObject.optString("name")));
	            certificateList.add(certificateEntity);
	        }
	        
	        List<KyNang> skillList = new ArrayList<>();
	        for (int i = 0; i < skillArray.length(); i++) {
	            JSONObject skillObject = skillArray.getJSONObject(i);
	            KyNang skillEntity = new KyNang();
				/*
				 * skillEntity.setName(skillObject.optString("name"));
				 * skillEntity.setLevel(skillObject.optString("level"));
				 */
	            skillEntity.setName(HTMLSanitizer.sanitizeInput(skillObject.optString("name")));
	            skillEntity.setLevel(HTMLSanitizer.sanitizeInput(skillObject.optString("level")));

	            skillList.add(skillEntity);
	        }
	        
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
			int idUV = Integer.parseInt(idUVStr);
			
			/* CV cv = new CV(idUV, position, careerGoals); */
			CV cv = new CV(idUV,
		               HTMLSanitizer.sanitizeInput(position),
		               HTMLSanitizer.sanitizeInput(careerGoals));
	        cv.setIdCV(Integer.parseInt(IdCV));
	        // Tiến hành lưu các đối tượng này vào cơ sở dữ liệu hoặc xử lý theo yêu cầu
	        if (mode.equals("create")) {
	        	CVDAO.addCV(cv, educationList, experienceList, certificateList, skillList);
	        }
	        else if (mode.equals("edit"))
	        {
	        	CVDAO.updateCV(cv, educationList, experienceList, certificateList, skillList);
	        }
	        //CSRFTokenManager.generateToken(request);
	        // Trả về phản hồi thành công
	        out.write("{\"status\":\"success\"}");
	        request.getRequestDispatcher("QuanLyCVServlet").forward(request, response);
	    } catch (Exception e) {
	        // Nếu có lỗi trong việc phân tích cú pháp JSON
	        out.write("{\"status\":\"error\",\"message\":\"Invalid JSON data\"}");
	    }
	}
}
