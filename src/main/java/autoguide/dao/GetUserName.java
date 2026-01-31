package autoguide.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import autoguide.db.CreateConnection;

/**
 * this class is used to get the user-name from the data to how in the home page
 */

public class GetUserName {
	  private static final Logger logger=LogManager.getLogger(GetUserName.class);
	/**
	 * from the request we get the email, depends on the email we get the user name 
	 * @param email
	 * @return the user name
	 */
	public static String getUserName(String email) {
		String name = null;
		try (Connection con = CreateConnection.getConnection();
				PreparedStatement ps = con.prepareStatement("select full_name from users where email=?")) {
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				name = rs.getString("full_name");
			}
			logger.debug("return user name based on logging email:"+email);

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("user name is  not get from the database ");

		}
		return name;
	}
}
