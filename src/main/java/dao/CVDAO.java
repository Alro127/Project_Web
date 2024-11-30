package dao;

import beans.CV;
import conn.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CVDAO {

    // Lấy danh sách CV từ cơ sở dữ liệu
    public static List<CV> getListCV() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM CV;";
        List<CV> cvList = new ArrayList<>();
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                CV cv = new CV(
                    rs.getString("fullName"),
                    rs.getString("position"),
                    rs.getString("personalInfo"),
                    rs.getString("careerGoals"),
                    rs.getDate("educationStart"),
                    rs.getDate("educationEnd"),
                    rs.getString("educationSchool"),
                    rs.getString("educationMajor"),
                    rs.getString("educationDescription"),
                    rs.getDate("experienceStart"),
                    rs.getDate("experienceEnd"),
                    rs.getString("experienceCompany"),
                    rs.getString("experiencePosition"),
                    rs.getString("experienceDescription"),
                    rs.getString("certificates"),
                    rs.getString("skills"),
                    rs.getString("hobbies")
                );
                cvList.add(cv);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
        return cvList;
    }
    public static CV getCVbyId(int IdCV) throws SQLException, ClassNotFoundException {
    	String sql = "SELECT * FROM CV WHERE IdCV = " + IdCV + ";";
        CV cv = new CV();
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                cv = new CV(
                    rs.getString("fullName"),
                    rs.getString("position"),
                    rs.getString("personalInfo"),
                    rs.getString("careerGoals"),
                    rs.getDate("educationStart"),
                    rs.getDate("educationEnd"),
                    rs.getString("educationSchool"),
                    rs.getString("educationMajor"),
                    rs.getString("educationDescription"),
                    rs.getDate("experienceStart"),
                    rs.getDate("experienceEnd"),
                    rs.getString("experienceCompany"),
                    rs.getString("experiencePosition"),
                    rs.getString("experienceDescription"),
                    rs.getString("certificates"),
                    rs.getString("skills"),
                    rs.getString("hobbies")
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
        return cv;
    }

    // Thêm một CV mới vào cơ sở dữ liệu
    public static boolean addCV(CV cv) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO CV (fullName, position, personalInfo, careerGoals, educationStart, educationEnd, "
                + "educationSchool, educationMajor, educationDescription, experienceStart, experienceEnd, "
                + "experienceCompany, experiencePosition, experienceDescription, certificates, skills, hobbies) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cv.getFullName());
            stmt.setString(2, cv.getPosition());
            stmt.setString(3, cv.getPersonalInfo());
            stmt.setString(4, cv.getCareerGoals());
            stmt.setDate(5, new java.sql.Date(cv.getEducationStart().getTime()));
            stmt.setDate(6, new java.sql.Date(cv.getEducationEnd().getTime()));
            stmt.setString(7, cv.getEducationSchool());
            stmt.setString(8, cv.getEducationMajor());
            stmt.setString(9, cv.getEducationDescription());
            stmt.setDate(10, new java.sql.Date(cv.getExperienceStart().getTime()));
            stmt.setDate(11, new java.sql.Date(cv.getExperienceEnd().getTime()));
            stmt.setString(12, cv.getExperienceCompany());
            stmt.setString(13, cv.getExperiencePosition());
            stmt.setString(14, cv.getExperienceDescription());
            stmt.setString(15, cv.getCertificates());
            stmt.setString(16, cv.getSkills());
            stmt.setString(17, cv.getHobbies());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
