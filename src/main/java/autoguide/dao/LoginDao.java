package autoguide.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

public class LoginDao{
	  private static final Logger logger=LogManager.getLogger(LoginDao.class);
	
	/**
	 * this class is used to validate the user
	 * @param email from user come from request
	 * @param password from user come from request
	 * @return true means valid user or else invalid user
	 */
	
	public static boolean userValidate(String email,String password) {
		
		
		try(Connection con=CreateConnection.getConnection();PreparedStatement ps=con.prepareStatement("select email,password from users where email=?")){
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				String pw=rs.getString("password");
				boolean flag=BCrypt.checkpw(password, pw);
				logger.debug("validate the email and password");
				return flag;
			}
		}catch (SQLException  e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("error while login validate");
			return false;
		}
		return false;
	}
	
}
