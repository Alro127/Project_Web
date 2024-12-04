package dao;

import beans.CV;
import beans.HocVan;
import beans.KinhNghiem;
import beans.KyNang;
import conn.DBConnection;
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
    	try {
			this.connection = DBConnection.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    // Thêm mới CV
    public static void addCV(CV cv, List<HocVan> hocVans, List<KinhNghiem> kinhNghiems, List<ChungChi> chungChis, List<KyNang> kyNangs) throws SQLException {
        String sql = "INSERT INTO CV (idUV, position, careerGoals) VALUES (?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) { // Sửa chỗ này
             
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
                HocVanDAO.saveEducationList(cv, hocVans);
                // Lưu danh sách kinh nghiệm
                KinhNghiemDAO.saveExperienceList(cv, kinhNghiems);
                // Lưu danh sách chứng chỉ
                ChungChiDAO.saveCertificateList(cv, chungChis);
                // Lưu danh sách kỹ năng
                KyNangDAO.saveSkillList(cv, kyNangs);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
                cv.setUngvien(UngVienDAO.getUngVienById(cv.getIdUV()));
                cv.setPosition(rs.getString("position"));
                cv.setCareerGoals(rs.getString("careerGoals"));
                cv.setHocVan(HocVanDAO.getEducationListByCV(cv));
                cv.setKinhNghiem(KinhNghiemDAO.getExperienceListByCV(cv));
                cv.setChungChi(ChungChiDAO.getCertificateListByCV(cv));
                cv.setKyNang(KyNangDAO.getSkillListByCV(cv));
                cvList.add(cv);
            }
        }
        return cvList;
    }
    // Lấy tất cả CV
    public List<CV> getAllCVbyIdUV(int idUV) throws SQLException {
        String sql = "SELECT * FROM CV WHERE IdUV = " + idUV;
        List<CV> cvList = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                CV cv = new CV();
                cv.setIdCV(rs.getInt("idCV"));
                cv.setIdUV(rs.getInt("idUV"));
                cv.setUngvien(UngVienDAO.getUngVienById(cv.getIdUV()));
                cv.setPosition(rs.getString("position"));
                cv.setCareerGoals(rs.getString("careerGoals"));
                cv.setHocVan(HocVanDAO.getEducationListByCV(cv));
                cv.setKinhNghiem(KinhNghiemDAO.getExperienceListByCV(cv));
                cv.setChungChi(ChungChiDAO.getCertificateListByCV(cv));
                cv.setKyNang(KyNangDAO.getSkillListByCV(cv));
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
                    cv.setUngvien(UngVienDAO.getUngVienById(cv.getIdUV()));
                    cv.setPosition(rs.getString("position"));
                    cv.setCareerGoals(rs.getString("careerGoals"));
                    cv.setHocVan(HocVanDAO.getEducationListByCV(cv));
                    cv.setKinhNghiem(KinhNghiemDAO.getExperienceListByCV(cv));
                    cv.setChungChi(ChungChiDAO.getCertificateListByCV(cv));
                    cv.setKyNang(KyNangDAO.getSkillListByCV(cv));
                }
            }
        }
        return cv;
    }
    // Xóa CV theo ID
    public boolean deleteCVbyId(int IdCV) throws SQLException {
        // Sửa lại câu lệnh SQL cho đúng cú pháp
        String sql = "DELETE FROM CV WHERE IdCV = ?";
        
        // Tạo kết nối, chuẩn bị câu lệnh PreparedStatement
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            // Thiết lập tham số cho câu lệnh
            ps.setInt(1, IdCV);
            
            // Thực thi câu lệnh xóa
            int affectedRows = ps.executeUpdate();
            
            // Kiểm tra xem có dòng nào bị xóa không
            if (affectedRows > 0) {
                // Trả về true nếu có ít nhất 1 dòng bị xóa
                return true;
            } else {
                // Trả về false nếu không có dòng nào bị xóa
                return false;
            }
        } catch (SQLException e) {
            // Xử lý lỗi nếu có
            e.printStackTrace();
            throw new SQLException("Error deleting CV with Id: " + IdCV);
        }
    }

}
