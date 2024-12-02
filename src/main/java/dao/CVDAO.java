package dao;

import beans.CV;
import beans.UngVien;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CVDAO {
    private Connection connection;

    // Constructor
    public CVDAO(Connection connection) {
        this.connection = connection;
    }

    public CVDAO() {
		// TODO Auto-generated constructor stub
	}

	// Thêm mới CV
    public boolean addCV(CV cv) throws SQLException {
        String sql = "INSERT INTO CV (idUV, position, careerGoals) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, cv.getIdUV());
            ps.setString(2, cv.getPosition());
            ps.setString(3, cv.getCareerGoals());

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        int generatedId = rs.getInt(1);
                        cv.setIdCV(generatedId);
                    }
                }

                // Lưu danh sách học vấn
                saveEducationList(cv);
                // Lưu danh sách kinh nghiệm
                saveExperienceList(cv);
                saveCertificateList(cv);
                saveCertificateAndSkillList(cv);
                return true;
            }
        }
        return false;
    }


    // Lưu danh sách học vấn
    private void saveEducationList(CV educationList) throws SQLException {
        String sql = "INSERT INTO Education (idCV, educationStart, educationEnd, educationSchool, educationMajor, educationDescription) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
        	 for (int i = 0; i < educationList.getEducationStart().size(); i++) {
                 ps.setInt(1, educationList.getIdCV());
                 ps.setDate(2, (Date) educationList.getEducationStart().get(i));
                 ps.setDate(3, (Date) educationList.getEducationEnd().get(i));
                 ps.setString(4, educationList.getEducationSchool().get(i));
                 ps.setString(5, educationList.getEducationMajor().get(i));
                 ps.setString(6,  educationList.getExperienceDescription().get(i));
                 ps.addBatch();
                 ps.executeBatch();
             }
        }
    }

    // Lưu danh sách kinh nghiệm
    private void saveExperienceList(CV experienceList) throws SQLException {
        String sql = "INSERT INTO Experience (idCV, experienceStart, experienceEnd, experienceCompany, experiencePosition, experienceDescription) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (int i = 0; i < experienceList.getExperienceStart().size(); i++) {
                ps.setInt(1, experienceList.getIdCV());
                ps.setDate(2, new java.sql.Date(experienceList.getExperienceStart().get(i).getTime()));
                ps.setDate(3, new java.sql.Date(experienceList.getExperienceEnd().get(i).getTime()));
                ps.setString(4, experienceList.getExperienceCompany().get(i));
                ps.setString(5, experienceList.getExperiencePosition().get(i));
                ps.setString(6, experienceList.getExperienceDescription().get(i));
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }
    // Lưu danh sách chứng chỉ
    private void saveCertificateList(CV cv) throws SQLException {
        String sql = "INSERT INTO Experience (idCV, certificates) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (int i = 0; i < cv.getExperienceStart().size(); i++) {
                ps.setInt(1, cv.getIdCV());
                ps.setString(2, cv.getCertificates().get(i));
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }
    // Lưu danh sách kỹ năng
    private void saveCertificateAndSkillList(CV cv) throws SQLException {
        String sql = "INSERT INTO Experience (idCV, skills, skillsValue) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (int i = 0; i < cv.getExperienceStart().size(); i++) {
                ps.setInt(1, cv.getIdCV());
                ps.setString(2, cv.getSkills().get(i));
                ps.setString(3, cv.getSkillsValue().get(i));
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }


    // Lấy tất cả CV
    public List<CV> getAllCV() throws SQLException {
        String sql = "SELECT * FROM CV";
        List<CV> cvList = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                CV cv = new CV();
                cv.setIdCV(rs.getInt("idCV"));
                cv.setIdUV(rs.getInt("idUV"));
                cv.setPosition(rs.getString("position"));
                cv.setCareerGoals(rs.getString("careerGoals"));

                // Lấy danh sách học vấn
                getEducationList(cv);
                // Lấy danh sách kinh nghiệm
                getExperienceList(cv);
                getCertificate(cv);
                getSkillList(cv);
                cvList.add(cv);
            }
        }
        return cvList;
    }
    
    // Lấy 1 CV theo IdCV
    public CV getCVbyId(int IdCV) throws SQLException {
        String sql = "SELECT * FROM CV WHERE IdCV = " + IdCV;
        CV cv = new CV();

        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                cv.setIdCV(rs.getInt("idCV"));
                cv.setIdUV(rs.getInt("idUV"));
                cv.setPosition(rs.getString("position"));
                cv.setCareerGoals(rs.getString("careerGoals"));

                // Lấy danh sách học vấn
                getEducationList(cv);
                // Lấy danh sách kinh nghiệm
                getExperienceList(cv);
                
                getCertificate(cv);
                getSkillList(cv);
            }
        }
        return cv;
    }


    // Lấy danh sách học vấn theo CV ID
    private void getEducationList(CV cv) throws SQLException {
        String sql = "SELECT * FROM Education WHERE idCV = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, cv.getIdCV());
            try (ResultSet rs = ps.executeQuery()) {
                List<Date> educationStart = new ArrayList<>();
                List<Date> educationEnd = new ArrayList<>();
                List<String> educationSchool = new ArrayList<>();
                List<String> educationMajor = new ArrayList<>();
                List<String> educationDescription = new ArrayList<>();

                while (rs.next()) {
                    educationStart.add(rs.getDate("educationStart"));
                    educationEnd.add(rs.getDate("educationEnd"));
                    educationSchool.add(rs.getString("educationSchool"));
                    educationMajor.add(rs.getString("educationMajor"));
                    educationDescription.add(rs.getString("educationDescription"));
                }

                cv.setEducationStart(educationStart);
                cv.setEducationEnd(educationEnd);
                cv.setEducationSchool(educationSchool);
                cv.setEducationMajor(educationMajor);
                cv.setEducationDescription(educationDescription);
            }
        }
    }


    // Lấy danh sách kinh nghiệm theo CV ID
    private void getExperienceList(CV cv) throws SQLException {
        String sql = "SELECT * FROM Experience WHERE idCV = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, cv.getIdCV());
            try (ResultSet rs = ps.executeQuery()) {
                List<Date> experienceStart = new ArrayList<>();
                List<Date> experienceEnd = new ArrayList<>();
                List<String> experienceCompany = new ArrayList<>();
                List<String> experiencePosition = new ArrayList<>();
                List<String> experienceDescription = new ArrayList<>();

                while (rs.next()) {
                    // Chuyển đổi java.sql.Date -> java.util.Date
                    experienceStart.add((Date) new java.util.Date(rs.getDate("experienceStart").getTime()));
                    experienceEnd.add((Date) new java.util.Date(rs.getDate("experienceEnd").getTime()));
                    experienceCompany.add(rs.getString("experienceCompany"));
                    experiencePosition.add(rs.getString("experiencePosition"));
                    experienceDescription.add(rs.getString("experienceDescription"));
                }

                // Gọi setter với danh sách đã chuyển đổi
                cv.setExperienceStart(experienceStart);
                cv.setExperienceEnd(experienceEnd);
                cv.setExperienceCompany(experienceCompany);
                cv.setExperiencePosition(experiencePosition);
                cv.setExperienceDescription(experienceDescription);
            }
        }
    }
    private void getCertificate(CV cv) throws SQLException {
        String sql = "SELECT * FROM Certificate WHERE IdCV = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, cv.getIdCV());
            try (ResultSet rs = ps.executeQuery()) {
            	List<String> certificates 	= new ArrayList<>();// Chứng chỉ
                while (rs.next()) {
                	certificates.add(rs.getString("certificates"));
                }

                // Gọi setter với danh sách đã chuyển đổi;
                cv.setCertificates(certificates);
            }
        }
    }
    private void getSkillList(CV cv) throws SQLException {
        String sql = "SELECT * FROM Skill WHERE IdCV = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, cv.getIdCV());
            try (ResultSet rs = ps.executeQuery()) {
                List<String> skills     	= new ArrayList<>();  // Kỹ năng
                List<String> skillsValue 	= new ArrayList<>();// Mức độ kỹ năng
                while (rs.next()) {
                	skills.add(rs.getString("skills"));
                	skillsValue.add(rs.getString("skillsValue"));
                }
                cv.setSkills(skills);
                cv.setSkillsValue(skillsValue);
            }
        }
    }
}
