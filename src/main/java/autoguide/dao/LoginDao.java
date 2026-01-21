package autoguide.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

public class LoginDao{
	
	public static boolean userValidate(String email,String password) {
		
		
		try(Connection con=CreateConnection.getConnection();PreparedStatement ps=con.prepareStatement("select email,password from users where email=?")){
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				String pw=rs.getString("password");
				boolean flag=BCrypt.checkpw(password, pw);
				return flag;
			}
		}catch (SQLException  e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
}
