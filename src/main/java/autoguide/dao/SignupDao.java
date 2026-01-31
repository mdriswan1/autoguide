package autoguide.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import autoguide.db.CreateConnection;

public class SignupDao {
	private static final Logger logger = LogManager.getLogger(SignupDao.class);

	/**
	 * This class is used to insert the new user data into the data base(Users table)
	 * 
	 * @param fullname
	 * @param email
	 *            is unique value
	 * @param password
	 *            is converted in hash and stored it in the data base
	 * @param city
	 *            created_at is to store the data when the sign up the data
	 * @return true or false if the user give the valid data else false(not insert data in the data base)
	 */

	public static boolean createUser(String fullname, String email, String password, String city) {
		try (Connection connection = CreateConnection.getConnection();
						PreparedStatement preparedStatement = connection.prepareStatement(
										"insert into users(full_name,email,password,city,created_at) values(?,?,?,?,?)")) {
			preparedStatement.setString(1, fullname);
			preparedStatement.setString(2, email);
			preparedStatement.setString(3, password);

			preparedStatement.setString(4, city);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			preparedStatement.setString(5, sdf.format(new Date()));
			int update = preparedStatement.executeUpdate();
			if (update >= 1) {
				logger.debug("inserted sigup details successfully");
				return true;
			}
			return false;
		} catch (SQLException e) {
			logger.error("error while sigunup ");
			return false;
		}
	}
}
