package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import beans.CV;
import beans.CongTy;
import beans.CongViec;
import beans.HoSo;
import conn.DBConnection;

public class HoSoDAO {
	public static List<HoSo> GetListHoSoByIdCT(int idCT) throws SQLException, ClassNotFoundException {
		String sql = "select * from HoSo inner join CongViec on CongViec.IdCongViec = HoSo.IdCongViec Where CongViec.IdCT = ?;";
		List<HoSo> hoSos = new ArrayList<HoSo>();
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, idCT);
			ResultSet rs = statement.executeQuery();


			while (rs.next()) {
				HoSo hoSo = new HoSo();

				// Lấy dữ liệu từ ResultSet và gán vào đối tượng CongViec
				hoSo.setIdCongViec(rs.getInt("IdCongViec"));
				hoSo.setIdCV(rs.getInt("IdCV"));
				hoSo.setThoiGianGui(rs.getTimestamp("ThoiGianGui"));
				hoSo.setTrangThai(rs.getString("TrangThai"));
				
				CV cv = CVDAO.getCVbyId(hoSo.getIdCV());
				CongViec congViec = CongViecDAO.getCongViecById(hoSo.getIdCongViec());
				hoSo.setCv(cv);
				hoSo.setCongViec(congViec);
				// Thêm đối tượng vào danh sách
				hoSos.add(hoSo);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return hoSos;
	}
	public static List<HoSo> GetListHoSoByIdUV(int idUV) throws SQLException, ClassNotFoundException {
		String sql = "select * from HoSo inner join CV on CV.IdCV = HoSo.IdCV Where CV.IdUV = ?;";
		List<HoSo> hoSos = new ArrayList<HoSo>();
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, idUV);
			ResultSet rs = statement.executeQuery();


			while (rs.next()) {
				HoSo hoSo = new HoSo();

				// Lấy dữ liệu từ ResultSet và gán vào đối tượng CongViec
				hoSo.setIdCongViec(rs.getInt("IdCongViec"));
				hoSo.setIdCV(rs.getInt("IdCV"));
				hoSo.setThoiGianGui(rs.getTimestamp("ThoiGianGui"));
				hoSo.setTrangThai(rs.getString("TrangThai"));
				
				CV cv = CVDAO.getCVbyId(hoSo.getIdCV());
				CongViec congViec = CongViecDAO.getCongViecById(hoSo.getIdCongViec());
				hoSo.setCv(cv);
				hoSo.setCongViec(congViec);
				// Thêm đối tượng vào danh sách
				hoSos.add(hoSo);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return hoSos;
	}
	public static boolean AddHoSo(int idCV, int idCongViec) throws SQLException, ClassNotFoundException {
	    String sql = "INSERT INTO HoSo (IdCV, IdCongViec, ThoiGianGui, TrangThai) VALUES (?, ?, ?, ?)";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement statement = conn.prepareStatement(sql)) {
	         
	        statement.setInt(1, idCV);
	        statement.setInt(2, idCongViec);
	        Timestamp currentTimestamp = Timestamp.from(Instant.now());
	        statement.setTimestamp(3, currentTimestamp);
	        statement.setString(4, "Chờ");

	        // Thực hiện câu lệnh và kiểm tra số dòng bị ảnh hưởng
	        int rowsAffected = statement.executeUpdate();
	        return rowsAffected > 0; // Trả về true nếu có ít nhất một dòng được thêm
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	        return false; 
	    }
	}
	

}
