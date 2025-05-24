package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.TaiKhoan;
import conn.DBConnection;
import utils.PasswordUtil;

public class TaiKhoanDAO {
	public static Boolean AuthenticationAccount(TaiKhoan tk) {
	    try {
	        Connection conn = DBConnection.getConnection();
	        String sql = "SELECT password FROM TaiKhoan WHERE username = ?";
	        PreparedStatement preparedStatement = conn.prepareStatement(sql);
	        preparedStatement.setString(1, tk.getUsername());

	        ResultSet rs = preparedStatement.executeQuery();

	        if (rs.next()) {
	            String hashedPassword = rs.getString("password");

	            // So sánh mật khẩu người dùng nhập với mật khẩu mã hóa trong DB
	            if (PasswordUtil.checkPassword(tk.getPassword(), hashedPassword)) {
	                return true;
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
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
	public static boolean AddAccount(String username, String password, String email, String role)
	{
		try {
			Connection conn = DBConnection.getConnection();
			String sqlcmd = "insert into TaiKhoan(username, password, email, role) values (?, ?, ?, ?)";
			PreparedStatement preparedStatement = conn.prepareStatement(sqlcmd);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, role);
			int rowsAffected = preparedStatement.executeUpdate();
			preparedStatement.close();
			conn.close();
	        return rowsAffected > 0; 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
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
	public static TaiKhoan getTaiKhoanById(int id) {
		TaiKhoan tk = new TaiKhoan();
	    try {
	        Connection conn = DBConnection.getConnection();

	        // Sử dụng columnName trong câu SQL
	        String sqlcmd = "SELECT username, password, role FROM TaiKhoan WHERE id = ?";
	        
	        PreparedStatement preparedStatement = conn.prepareStatement(sqlcmd);
	        preparedStatement.setInt(1, id);
	        
	        ResultSet rs = preparedStatement.executeQuery();
	        if (rs.next()) {
	            tk.setId(id);
	            tk.setUsername(rs.getString("username"));
	            tk.setPassword(rs.getString("password"));
	            tk.setRole(rs.getString("role"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); // In lỗi ra console để debug
	    }
	    return tk;
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
    	
    	if (oldPassword.isEmpty() || oldPassword == null || newPassword.isEmpty() || newPassword == null
			|| username.isEmpty() || username == null)
    	{
    		return false;
		}
    	// đã hash bằng BCrypt
        String currentPassword = getPasswordByUsername(username);
        
        // Kiểm tra mật khẩu cũ
        if (currentPassword == null || !PasswordUtil.checkPassword(oldPassword, currentPassword)) {
            return false;  // Mật khẩu cũ không đúng
        }
        String newPasswordHashed = PasswordUtil.encryptPassword(newPassword);
        // Tiến hành cập nhật mật khẩu mới vào cơ sở dữ liệu
        // Giả sử bạn có phương thức `updatePassword`
        return updatePassword(username, newPasswordHashed);
    }

    private String getPasswordByUsername(String username) {
		String password = "";
	    try {
	        Connection conn = DBConnection.getConnection();

	        // Sử dụng columnName trong câu SQL
	        String sqlcmd = "SELECT password  FROM TaiKhoan WHERE username = ?";
	        
	        PreparedStatement preparedStatement = conn.prepareStatement(sqlcmd);
	        preparedStatement.setString(1, username);
	        
	        ResultSet rs = preparedStatement.executeQuery();
	        if (rs.next()) {
	        	password = rs.getString("password");
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); // In lỗi ra console để debug
	    }
        return password; // Ví dụ, trả về mật khẩu cũ từ DB
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
    public static void StoreTokens(String access_token, String refresh_token, int id , String email, String id_google)
    {
        try {
            Connection conn = DBConnection.getConnection();
            if (refresh_token != null) {
                String sqlcmd = "UPDATE TaiKhoan SET email = ?, access_token = ?, refresh_token = ?, id_google = ? WHERE id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(sqlcmd);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, access_token);
                preparedStatement.setString(3, refresh_token);
                preparedStatement.setString(4, id_google); // Thêm id_google
                preparedStatement.setInt(5, id);
                preparedStatement.execute();
            }
            else {
                String sqlcmd = "UPDATE TaiKhoan SET email = ?, access_token = ?, id_google = ? WHERE id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(sqlcmd);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, access_token);
                preparedStatement.setString(3, id_google); // Thêm id_google
                preparedStatement.setInt(4, id);
                preparedStatement.execute();
            }
        } catch (Exception e) {
            // Xử lý lỗi nếu có
            e.printStackTrace();
        }
    }
    public static String getRefreshToken(int id)
    {
    	try {
			Connection connection = DBConnection.getConnection();
			String sqlcmd = "select refresh_token from TaiKhoan where id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlcmd);
			preparedStatement.setInt(1, id);
			ResultSet rSet = preparedStatement.executeQuery();
			if (rSet.next()) {
				return rSet.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
    public static void SetRoleByIDGoogle(String id_goole, String role)
    {
    	String sql = "update TaiKhoan set role = ? where id_google = ?";
    	try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, role);
			preparedStatement.setString(2, id_goole);
			preparedStatement.execute();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
}
