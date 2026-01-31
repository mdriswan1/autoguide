package autoguide.dto;

public class Users {
	private int user_id;
	private String fullName;
	private String email;
	private String password;
	private String city;
	private String created_at;
	public Users(int user_id, String fullName, String email, String password, String city, String created_at) {
		super();
		this.user_id = user_id;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.city = city;
		this.created_at = created_at;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	
	
}
