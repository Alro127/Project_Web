package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.CongTy;
import beans.CongViec;
import beans.CongViecYeuThich;
import conn.DBConnection;

public class CongViecYeuThichDAO {
	
	public static List<CongViec> GetListCongViecYeuThich(int idUV) throws SQLException, ClassNotFoundException {
		String sql = "SELECT * FROM CongViec INNER JOIN CongViecYeuThich ON CongViecYeuThich.IdCongViec = CongViec.IdCongViec AND CongViecYeuThich.IdUV = ?;";
		List<CongViec> congViecs = new ArrayList<CongViec>();
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idUV);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				CongViec congViec = new CongViec();

				// Lấy dữ liệu từ ResultSet và gán vào đối tượng CongViec
				congViec.setIdCongViec(rs.getInt("IdCongViec"));
				congViec.setIdCT(rs.getInt("IdCT"));
				congViec.setTen(rs.getString("Ten"));
				congViec.setDiaDiem(rs.getString("DiaDiem"));
				congViec.setLuong(rs.getDouble("Luong"));
				congViec.setNamKinhNghiem(rs.getInt("NamKinhNghiem"));
				congViec.setLinhVuc(rs.getString("LinhVuc"));
				congViec.setThoiGianDang(rs.getTimestamp("ThoiGianDang"));
				congViec.setThoiGianHetHan(rs.getTimestamp("ThoiGianHetHan"));
				congViec.setMoTa(rs.getString("MoTa"));
				congViec.setYeuCau(rs.getString("YeuCau"));
				congViec.setQuyenLoi(rs.getString("QuyenLoi"));
				congViec.setLuotXem(rs.getInt("LuotXem"));
				congViec.setLuotNop(rs.getInt("LuotNop"));
				
				CongTy congTy = CongTyDAO.GetCongTyById(congViec.getIdCT());
				if (congTy != null) {
				    congViec.setTenCongTy(congTy.getTenCongTy());
				} else {
				    congViec.setTenCongTy("Chưa cập nhật");
				}

				// Thêm đối tượng vào danh sách
				congViecs.add(congViec);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return congViecs;
	}
	
	public static CongViecYeuThich GetCongViecYeuThich(int idUV, int idCongViec) {
		String sql = "SELECT * FROM CongViecYeuThich WHERE IdUV = ? AND IdCongViec = ? ";

		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, idUV);
			statement.setInt(2, idCongViec);
			ResultSet rs = statement.executeQuery();
			if (rs.next())
			{
				CongViecYeuThich congViecYeuThich = new CongViecYeuThich(rs.getInt("IdUV"), rs.getInt("IdCongViec"));
				return congViecYeuThich;
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void AddCongViecYeuThich(int idUV, int idCongViec) throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO CongViecYeuThich (IdUV, IdCongViec ) "
				+ "VALUES (?, ?)";
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, idUV);
			statement.setInt(2, idCongViec);

			statement.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	public static void DeleteCongViecYeuThich(int idUV, int idCongViec) throws SQLException, ClassNotFoundException {
		String sql = "DELETE CongViecYeuThich WHERE IdUV = ? AND IdCongViec = ? ";
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, idUV);
			statement.setInt(2, idCongViec);

			statement.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
