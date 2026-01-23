package autoguide.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetUserName {
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
