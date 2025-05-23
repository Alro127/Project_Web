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
import conn.DBConnection;

public class CongViecDAO {
	public static List<CongViec> GetListCongViec() throws SQLException, ClassNotFoundException {
		String sql = "select * from CongViec;";
		List<CongViec> congViecs = new ArrayList<CongViec>();
		try {
			Connection conn = DBConnection.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

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
				    congViec.setLogo(congTy.getLogo());
				} else {
				    congViec.setTenCongTy("Chưa cập nhật");
				    congViec.setLogo("Chưa cập nhật");
				}

				// Thêm đối tượng vào danh sách
				congViecs.add(congViec);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return congViecs;
	}

	public static boolean AddCongViecMoi(CongViec congViec) throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO CongViec (IdCT, Ten, DiaDiem, Luong, NamKinhNghiem, LinhVuc, ThoiGianHetHan, MoTa, YeuCau, QuyenLoi) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		boolean rs = false;
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, congViec.getIdCT());
			statement.setString(2, congViec.getTen());
			statement.setString(3, congViec.getDiaDiem());
			statement.setDouble(4, congViec.getLuong());
			statement.setInt(5, congViec.getNamKinhNghiem());
			statement.setString(6, congViec.getLinhVuc());
			statement.setTimestamp(7, congViec.getThoiGianHetHan());
			statement.setString(8, congViec.getMoTa());
			statement.setString(9, congViec.getYeuCau());
			statement.setString(10, congViec.getQuyenLoi());

			rs = statement.executeUpdate() > 0;

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	public static CongViec getCongViecById(int id) {
		String sql = "select * from CongViec where IdCongViec = ?;";
        
		try 
		{
			Connection conn = DBConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			
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
				    congViec.setLogo(congTy.getLogo());
				} else {
				    congViec.setTenCongTy("Chưa cập nhật");
				    congViec.setLogo("Chưa cập nhật");
				}
				
				return congViec;
			}
		}
        catch (Exception e)
		{
        	e.printStackTrace();
		}
        return null; 
    }
	public static List<CongViec> GetListCongViecByIdCT(int id) throws SQLException, ClassNotFoundException {
		String sql = "select * from CongViec where IdCT = ?;";
		List<CongViec> congViecs = new ArrayList<CongViec>();
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();

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
				    congViec.setLogo(congTy.getLogo());
				} else {
				    congViec.setTenCongTy("Chưa cập nhật");
				    congViec.setLogo("Chưa cập nhật");
				}
				// Thêm đối tượng vào danh sách
				congViecs.add(congViec);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return congViecs;
	}

	public static List<String> getListLinhVuc() {
	    List<String> linhVucs = new ArrayList<>();
	    String sqlcmd = "SELECT DISTINCT LinhVuc FROM CongViec";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sqlcmd);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            linhVucs.add(rs.getString("LinhVuc"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return linhVucs;
	}
	
	public static List<String> getListTinhThanh()
	{
		List<String> tinhThanhs = new ArrayList<>();
	    String sqlcmd = "SELECT DISTINCT DiaDiem FROM CongViec";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sqlcmd);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	        	tinhThanhs.add(rs.getString("DiaDiem"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return tinhThanhs;
	}
	
	public static void updateLuotXem(int id)
	{
		String sqlcmd = "update CongViec set LuotXem = LuotXem + 1 where IdCongViec = ?";
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sqlcmd);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void updateLuotNop(int id)
	{
		String sqlcmd = "update CongViec set LuotNop = LuotNop + 1 where IdCongViec = ?";
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sqlcmd);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static CongTy getCongTyByID(int id)
	{
		 String cmd = "select * from CongTy where IdCT = ?";
		 CongTy congTy = null;
	     try {
	    	 Connection conn = DBConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(cmd);
            // Gán giá trị tham số cho câu lệnh SQL
            statement.setInt(1, id);

            // Thực thi truy vấn
            ResultSet resultSet = statement.executeQuery();
            
            // Lấy dữ liệu từ resultSet
            if (resultSet.next()) {
                int idCT = resultSet.getInt("IdCT");
                String tenCongTy = resultSet.getString("TenCongTy");
                String sdt = resultSet.getString("SDT");
                String email = resultSet.getString("Email");
                String maSoThue = resultSet.getString("MaSoThue");
                String linhVuc = resultSet.getString("LinhVuc");
                String quyMoNhanSu = resultSet.getString("QuyMoNhanSu");
                String tinhThanh = resultSet.getString("TinhThanh");
                String diaChi = resultSet.getString("DiaChi");
                String url = resultSet.getString("Url");
                String gioiThieu = resultSet.getString("GioiThieu");
                String logo = resultSet.getString("Logo");
                String background = resultSet.getString("Background");

                // Tạo đối tượng CongTy
                congTy = new CongTy(idCT, tenCongTy, sdt, email, maSoThue, linhVuc, quyMoNhanSu,
                                    tinhThanh, diaChi, url, gioiThieu, logo, background);
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
	     		
	   return congTy;
	}
	public static boolean DeleteCongViecById(int idCongViec) {
	    String sqlcmd = "DELETE FROM CongViec WHERE IdCongViec = ?";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement preparedStatement = conn.prepareStatement(sqlcmd)) {
	        
	        preparedStatement.setInt(1, idCongViec);
	        int rowsAffected = preparedStatement.executeUpdate();
	        
	        // Nếu xóa thành công, rowsAffected sẽ > 0
	        return rowsAffected > 0;
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public static boolean isCongViecBelongsToCompany(int idCongViec, int idCongTy) {
	    String sql = "SELECT 1 FROM CongViec WHERE IdCongViec = ? AND IdCT = ?";
	    try (
	        Connection conn = DBConnection.getConnection();
	        PreparedStatement stmt = conn.prepareStatement(sql)
	    ) {
	        stmt.setInt(1, idCongViec);
	        stmt.setInt(2, idCongTy);
	        ResultSet rs = stmt.executeQuery();
	        return rs.next();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}


}
