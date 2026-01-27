package autoguide.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import autoguide.servlet.FrontController;

/*
 *this class is used to get the connection from the data source
 * 
 */

public class CreateConnection {
	private static final Logger logger = LogManager.getLogger(CreateConnection.class);

	public static Connection getConnection() {
//		InitialContext context;
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
//			context = new InitialContext();
//			DataSource dataSource=(DataSource) context.lookup("java:comp/env/jdbc/autoguide");

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
