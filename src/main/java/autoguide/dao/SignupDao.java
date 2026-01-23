package autoguide.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SignupDao {
	
	public static boolean createUser(String fullname,String email,String password,String city) {
		try(Connection connection=CreateConnection.getConnection();PreparedStatement preparedStatement=connection.prepareStatement("insert into users(full_name,email,password,city,created_at) values(?,?,?,?,?)")){
			preparedStatement.setString(1,fullname);
			preparedStatement.setString(2, email);
			preparedStatement.setString(3, password);
			
			preparedStatement.setString(4,city);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			
			preparedStatement.setString(5, sdf.format(new Date()));
			int update=preparedStatement.executeUpdate();
			if(update>=1) {
				return true;
			}
			return false;
		}catch(Exception e) {
			e.printStackTrace();
			
			return false;
		}
	}
}
