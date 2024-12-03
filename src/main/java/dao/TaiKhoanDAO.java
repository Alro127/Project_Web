package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.TaiKhoan;
import conn.DBConnection;

public class TaiKhoanDAO {
	public static Boolean AuthenticationAccount(TaiKhoan tk)
	{
		try {
			Connection conn = DBConnection.getConnection();
			String sql = "select * from TaiKhoan where username = ? and password = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, tk.getUsername());
			preparedStatement.setString(2, tk.getPassword());
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public static boolean isValidUserNamePassword(String username, String password, String repassword) {
		String uregex = "^[a-zA-Z0-9]{3,20}$";
		String pregex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
		if (password == null || password.isEmpty() || username == null || username.isEmpty()) {
            return false;
        }
		boolean isValidUsername = username.matches(uregex);
		boolean isValidPassword = password.matches(pregex);
		boolean isValidConfirmPassword = password.equals(repassword);
		return isValidUsername  && isValidPassword && isValidConfirmPassword;
	}
	
	public static boolean isExistedAccount(String username, String password)
	{
		try {
			Connection conn = DBConnection.getConnection();
			String sqlcmd = "select * from TaiKhoan where username = ? and password = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(sqlcmd);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public static void AddAccount(String username, String password)
	{
		try {
			Connection conn = DBConnection.getConnection();
			String sqlcmd = "insert into TaiKhoan(username, password) values (?, ?)";
			PreparedStatement preparedStatement = conn.prepareStatement(sqlcmd);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			preparedStatement.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static List<String> getInformationForSession(int id)
	{
		try {
			Connection conn = DBConnection.getConnection();
			String sqlcmd = "select id, id_google, id_facebook, access_token, refresh_token, role, email from TaiKhoan"
					+ " where id = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(sqlcmd);
			preparedStatement.setInt(1, id);
			List<String> information = new ArrayList<>();
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				information.add(rs.getString("id"));
	            information.add(rs.getString("id_google"));
	            information.add(rs.getString("id_facebook"));
	            information.add(rs.getString("access_token"));
	            information.add(rs.getString("refresh_token"));
	            information.add(rs.getString("role"));
	            information.add(rs.getString("email"));
			}
			return information;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static int getID(String columnName, String value) {
	    try {
	        Connection conn = DBConnection.getConnection();

	        // Sử dụng columnName trong câu SQL
	        String sqlcmd = "SELECT id FROM TaiKhoan WHERE " + columnName + " = ?";
	        
	        PreparedStatement preparedStatement = conn.prepareStatement(sqlcmd);
	        preparedStatement.setString(1, value);
	        
	        ResultSet rs = preparedStatement.executeQuery();
	        if (rs.next()) {
	            return rs.getInt(1);
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); // In lỗi ra console để debug
	    }
	    return -1; // Trả về -1 nếu không tìm thấy hoặc có lỗi
	}

	public static boolean isIDExisted(String id, String columnName)
	{
	    try {
	        Connection conn = DBConnection.getConnection();
	        String sqlcmd = "SELECT * FROM TaiKhoan WHERE " + columnName + " = ?";
	        PreparedStatement preparedStatement = conn.prepareStatement(sqlcmd);    
	        preparedStatement.setString(1, id);
	        ResultSet rs = preparedStatement.executeQuery();
	        if (rs.next()) {
	            return true;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	public static void AddAccountByID(String id, String columnName)
	{
		try {
			Connection conn = DBConnection.getConnection();
			String sqlcmd = "INSERT INTO TaiKhoan (" + columnName + ") VALUES (?)";
			PreparedStatement preparedStatement = conn.prepareStatement(sqlcmd);
			preparedStatement.setString(1, id);
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    public boolean updateTaiKhoan(String username, String oldPassword, String newPassword) {
        // Kiểm tra mật khẩu cũ có đúng không (có thể cần gọi database)
        // Giả sử bạn có một phương thức `getPasswordByUsername` để lấy mật khẩu hiện tại
        String currentPassword = getPasswordByUsername(username);

        // Kiểm tra mật khẩu cũ
        if (currentPassword == null || !currentPassword.equals(oldPassword)) {
            return false;  // Mật khẩu cũ không đúng
        }

        // Tiến hành cập nhật mật khẩu mới vào cơ sở dữ liệu
        // Giả sử bạn có phương thức `updatePassword`
        return updatePassword(username, newPassword);
    }

    private String getPasswordByUsername(String username) {
        // Giả sử bạn truy vấn cơ sở dữ liệu và lấy mật khẩu hiện tại
        // Đây chỉ là ví dụ, bạn cần thay thế bằng truy vấn thực tế
        return "dat123"; // Ví dụ, trả về mật khẩu cũ từ DB
    }

    private boolean updatePassword(String username, String newPassword) {
        // Cập nhật mật khẩu mới vào cơ sở dữ liệu
		try {
			Connection conn = DBConnection.getConnection();
			String sqlcmd = "UPDATE TaiKhoan SET password = ? WHERE username = ?;";
			PreparedStatement preparedStatement = conn.prepareStatement(sqlcmd);
			preparedStatement.setString(1, newPassword);
			preparedStatement.setString(2, username);
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return true;  // Giả sử cập nhật thành công
    }
}
