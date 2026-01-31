package autoguide.db;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
 *this class is used to get the connection from the data source
 * 
 */

public class CreateConnection {
	private static final Logger logger = LogManager.getLogger(CreateConnection.class);

	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		// InitialContext context;
		Connection connection = null;

		try {
			// context = new InitialContext();
			// DataSource dataSource=(DataSource) context.lookup("java:comp/env/jdbc/autoguide");

			connection = HikariConnection.getDataSource().getConnection();
			logger.debug("database connection is created ");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("database connection not created");
		}
		return connection;
	}
}
