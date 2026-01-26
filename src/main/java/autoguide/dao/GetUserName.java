package autoguide.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * this class is used to get the user-name from the data to how in the home page
 */

public class GetUserName {
	/**
	 * from the request we get the email, depends on the email we get the user name 
	 * @param email
	 * @return the user name
	 */
	public static String getUserName(String email) {
		try (Connection con = CreateConnection.getConnection();
				PreparedStatement ps = con.prepareStatement("select full_name from users where email=?")) {
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String name = rs.getString("full_name");
			return name;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
}
