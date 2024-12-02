package dao;

import beans.CV;
import beans.HocVan;
import beans.KinhNghiem;
import beans.KyNang;
import beans.ChungChi;

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
        // Constructor mặc định
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
                // Lưu danh sách chứng chỉ
                saveCertificateList(cv);
                // Lưu danh sách kỹ năng
                saveSkillList(cv);
                return true;
            }
        }
        return false;
    }

    // Lưu danh sách học vấn
    private void saveEducationList(CV cv) throws SQLException {
        String sql = "INSERT INTO Education (idCV, educationStart, educationEnd, educationSchool, educationMajor, educationDescription) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (HocVan hocVan : cv.getHocVan()) {
                ps.setInt(1, cv.getIdCV());
                ps.setDate(2, new java.sql.Date(hocVan.getStart().getTime()));
                ps.setDate(3, hocVan.getEnd() != null ? new java.sql.Date(hocVan.getEnd().getTime()) : null);
                ps.setString(4, hocVan.getSchool());
                ps.setString(5, hocVan.getMajor());
                ps.setString(6, hocVan.getDescription());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    // Lưu danh sách kinh nghiệm
    private void saveExperienceList(CV cv) throws SQLException {
        String sql = "INSERT INTO Experience (idCV, experienceStart, experienceEnd, experienceCompany, experiencePosition, experienceDescription) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (KinhNghiem kinhNghiem : cv.getKinhNghiem()) {
                ps.setInt(1, cv.getIdCV());
                ps.setDate(2, new java.sql.Date(kinhNghiem.getStart().getTime()));
                ps.setDate(3, kinhNghiem.getEnd() != null ? new java.sql.Date(kinhNghiem.getEnd().getTime()) : null);
                ps.setString(4, kinhNghiem.getCompany());
                ps.setString(5, kinhNghiem.getPosition());
                ps.setString(6, kinhNghiem.getDescription());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    // Lưu danh sách chứng chỉ
    private void saveCertificateList(CV cv) throws SQLException {
        String sql = "INSERT INTO Certificate (idCV, certificateName) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (ChungChi chungChi : cv.getChungChi()) {
                ps.setInt(1, cv.getIdCV());
                ps.setString(2, chungChi.getName());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    // Lưu danh sách kỹ năng
    private void saveSkillList(CV cv) throws SQLException {
        String sql = "INSERT INTO Skill (idCV, skillName, skillLevel) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (KyNang kyNang : cv.getKyNang()) {
                ps.setInt(1, cv.getIdCV());
                ps.setString(2, kyNang.getName());
                ps.setString(3, kyNang.getLevel());
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
                // Lấy danh sách chứng chỉ
                getCertificateList(cv);
                // Lấy danh sách kỹ năng
                getSkillList(cv);
                cvList.add(cv);
            }
        }
        return cvList;
    }

    // Lấy CV theo ID
    public CV getCVbyId(int IdCV) throws SQLException {
        String sql = "SELECT * FROM CV WHERE IdCV = ?";
        CV cv = new CV();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, IdCV);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cv.setIdCV(rs.getInt("idCV"));
                    cv.setIdUV(rs.getInt("idUV"));
                    cv.setPosition(rs.getString("position"));
                    cv.setCareerGoals(rs.getString("careerGoals"));

                    // Lấy danh sách học vấn
                    getEducationList(cv);
                    // Lấy danh sách kinh nghiệm
                    getExperienceList(cv);
                    // Lấy danh sách chứng chỉ
                    getCertificateList(cv);
                    // Lấy danh sách kỹ năng
                    getSkillList(cv);
                }
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
                List<HocVan> hocVans = new ArrayList<>();
                while (rs.next()) {
                    Date start = rs.getDate("educationStart");
                    Date end = rs.getDate("educationEnd");
                    String school = rs.getString("educationSchool");
                    String major = rs.getString("educationMajor");
                    String description = rs.getString("educationDescription");

                    HocVan hocVan = new HocVan(start, end, school, major, description);
                    hocVans.add(hocVan);
                }
                cv.setHocVan(hocVans);
            }
        }
    }

    // Lấy danh sách kinh nghiệm theo CV ID
    private void getExperienceList(CV cv) throws SQLException {
        String sql = "SELECT * FROM Experience WHERE idCV = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, cv.getIdCV());
            try (ResultSet rs = ps.executeQuery()) {
                List<KinhNghiem> kinhNghiems = new ArrayList<>();
                while (rs.next()) {
                    Date start = rs.getDate("experienceStart");
                    Date end = rs.getDate("experienceEnd");
                    String company = rs.getString("experienceCompany");
                    String position = rs.getString("experiencePosition");
                    String description = rs.getString("experienceDescription");

                    KinhNghiem kinhNghiem = new KinhNghiem(start, end, company, position, description);
                    kinhNghiems.add(kinhNghiem);
                }
                cv.setKinhNghiem(kinhNghiems);
            }
        }
    }

    // Lấy danh sách chứng chỉ theo CV ID
    private void getCertificateList(CV cv) throws SQLException {
        String sql = "SELECT * FROM Certificate WHERE idCV = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, cv.getIdCV());
            try (ResultSet rs = ps.executeQuery()) {
                List<ChungChi> chungChis = new ArrayList<>();
                while (rs.next()) {
                    String name = rs.getString("certificateName");
                    ChungChi chungChi = new ChungChi(name);
                    chungChis.add(chungChi);
                }
                cv.setChungChi(chungChis);
            }
        }
    }

    // Lấy danh sách kỹ năng theo CV ID
    private void getSkillList(CV cv) throws SQLException {
        String sql = "SELECT * FROM Skill WHERE idCV = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, cv.getIdCV());
            try (ResultSet rs = ps.executeQuery()) {
                List<KyNang> kyNangs = new ArrayList<>();
                while (rs.next()) {
                    String name = rs.getString("skillName");
                    String level = rs.getString("skillLevel");
                    KyNang kyNang = new KyNang(name, level);
                    kyNangs.add(kyNang);
                }
                cv.setKyNang(kyNangs);
            }
        }
    }
}
