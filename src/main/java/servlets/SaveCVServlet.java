import java.io.*;
import java.sql.*;
import org.json.*;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import conn.SQLServerConnection;
public class SaveCVServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Thiết lập kiểu trả về là JSON
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        // Lấy dữ liệu từ request
        String careerGoals = request.getParameter("careerGoals");
        String educationData = request.getParameter("educationData");
        String experienceData = request.getParameter("experienceData");
        String certificateData = request.getParameter("certificateData");
        String skillData = request.getParameter("skillData");

        // Parse JSON để lấy thông tin chi tiết
        try {
            // Kết nối cơ sở dữ liệu
            Connection conn = SQLServerConnection;
            conn.setAutoCommit(false); // Tắt chế độ tự động commit

            // Tạo câu lệnh SQL để lưu dữ liệu
            String insertCVSQL = "INSERT INTO CV (career_goals) VALUES (?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertCVSQL, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, careerGoals);
                stmt.executeUpdate();

                // Lấy ID của CV vừa được tạo
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int cvId = rs.getInt(1);

                    // Lưu dữ liệu học vấn
                    if (educationData != null && !educationData.isEmpty()) {
                        JSONArray educationArray = new JSONArray(educationData);
                        String insertEducationSQL = "INSERT INTO Education (IdCV, start_date, end_date, school, major, description) VALUES (?, ?, ?, ?, ?, ?)";
                        try (PreparedStatement eduStmt = conn.prepareStatement(insertEducationSQL)) {
                            for (int i = 0; i < educationArray.length(); i++) {
                                JSONObject education = educationArray.getJSONObject(i);
                                eduStmt.setInt(1, cvId);
                                eduStmt.setString(2, education.getString("start"));
                                eduStmt.setString(3, education.getString("end"));
                                eduStmt.setString(4, education.getString("school"));
                                eduStmt.setString(5, education.getString("major"));
                                eduStmt.setString(6, education.getString("description"));
                                eduStmt.addBatch(); // Thực thi batch để tiết kiệm tài nguyên
                            }
                            eduStmt.executeBatch(); // Thực thi tất cả các lệnh insert cho học vấn
                        }
                    }

                    // Lưu dữ liệu kinh nghiệm làm việc
                    if (experienceData != null && !experienceData.isEmpty()) {
                        JSONArray experienceArray = new JSONArray(experienceData);
                        String insertExperienceSQL = "INSERT INTO WorkExperience (cv_id, start_date, end_date, company, position, description) VALUES (?, ?, ?, ?, ?, ?)";
                        try (PreparedStatement expStmt = conn.prepareStatement(insertExperienceSQL)) {
                            for (int i = 0; i < experienceArray.length(); i++) {
                                JSONObject experience = experienceArray.getJSONObject(i);
                                expStmt.setInt(1, cvId);
                                expStmt.setString(2, experience.getString("start"));
                                expStmt.setString(3, experience.getString("end"));
                                expStmt.setString(4, experience.getString("company"));
                                expStmt.setString(5, experience.getString("position"));
                                expStmt.setString(6, experience.getString("description"));
                                expStmt.addBatch(); // Thực thi batch
                            }
                            expStmt.executeBatch(); // Thực thi batch cho kinh nghiệm
                        }
                    }

                    // Lưu dữ liệu chứng chỉ
                    if (certificateData != null && !certificateData.isEmpty()) {
                        JSONArray certificateArray = new JSONArray(certificateData);
                        String insertCertificateSQL = "INSERT INTO Certificates (cv_id, certificate_name) VALUES (?, ?)";
                        try (PreparedStatement certStmt = conn.prepareStatement(insertCertificateSQL)) {
                            for (int i = 0; i < certificateArray.length(); i++) {
                                certStmt.setInt(1, cvId);
                                certStmt.setString(2, certificateArray.getString(i));
                                certStmt.addBatch();
                            }
                            certStmt.executeBatch();
                        }
                    }

                    // Lưu dữ liệu kỹ năng
                    if (skillData != null && !skillData.isEmpty()) {
                        JSONArray skillArray = new JSONArray(skillData);
                        String insertSkillSQL = "INSERT INTO Skills (cv_id, skill_name, skill_level) VALUES (?, ?, ?)";
                        try (PreparedStatement skillStmt = conn.prepareStatement(insertSkillSQL)) {
                            for (int i = 0; i < skillArray.length(); i++) {
                                JSONObject skill = skillArray.getJSONObject(i);
                                skillStmt.setInt(1, cvId);
                                skillStmt.setString(2, skill.getString("name"));
                                skillStmt.setString(3, skill.getString("level"));
                                skillStmt.addBatch();
                            }
                            skillStmt.executeBatch();
                        }
                    }

                    // Commit transaction nếu tất cả thành công
                    conn.commit();
                    out.write("{\"status\":\"success\",\"message\":\"CV đã được lưu thành công!\"}");
                }
            } catch (SQLException e) {
                conn.rollback(); // Rollback nếu có lỗi xảy ra
                e.printStackTrace();
                out.write("{\"status\":\"error\",\"message\":\"Lỗi khi lưu CV.\"}");
            } finally {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.write("{\"status\":\"error\",\"message\":\"Không thể kết nối với cơ sở dữ liệu.\"}");
        } catch (JSONException e) {
            e.printStackTrace();
            out.write("{\"status\":\"error\",\"message\":\"Lỗi xử lý dữ liệu JSON.\"}");
        }
    }
}
