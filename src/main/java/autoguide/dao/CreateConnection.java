package autoguide.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CreateConnection {
	public static Connection getConnection() {
		InitialContext context;
		Connection connection=null;
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			context = new InitialContext();
			DataSource dataSource=(DataSource) context.lookup("java:comp/env/jdbc/autoguide");
				connection = dataSource.getConnection();
			
		} catch (NamingException |SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;				
	}
}
