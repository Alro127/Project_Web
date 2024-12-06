package beans;

public class TaiKhoan {
	private int id;
	private String username;
	private String password;
	private String id_google;
	private String id_facebook;
	private String email;
	private String role;
	
	public TaiKhoan() {
		
	}
	public TaiKhoan(int id, String username, String password, String id_google,
			String id_facebook, String email, String role)
	{
		setId(id);
		setUsername(username);
		setPassword(password);
		setId_google(id_google);
		setId_facebook(id_facebook);
		setEmail(email);
		setRole(role);
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getId_facebook() {
		return id_facebook;
	}
	public void setId_facebook(String id_facebook) {
		this.id_facebook = id_facebook;
	}
	public String getId_google() {
		return id_google;
	}
	public void setId_google(String id_google) {
		this.id_google = id_google;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
}
