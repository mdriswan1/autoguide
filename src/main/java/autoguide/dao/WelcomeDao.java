/*
 * package autoguide.dao;
 * 
 * import java.sql.Statement; import java.sql.Connection; import
 * java.sql.ResultSet; import java.sql.SQLException; import java.util.ArrayList;
 * import java.util.Base64; import java.util.List;
 * 
 * import com.google.gson.Gson;
 * 
 * import autoguide.util.Welcome;
 *//**
	 * this class is used to load the vehicle data from the database
	 *//*
		 * public class WelcomeDao { public static String getsomeDetails() {
		 * try(Connection con=CreateConnection.getConnection(); Statement
		 * s=con.createStatement()){ String
		 * sql="select model_name,fuel_type,description,image_data from vehicle_model limit 4"
		 * ; ResultSet rs=s.executeQuery(sql); List<Welcome> vehicles=new
		 * ArrayList<Welcome>();
		 * 
		 * while(rs.next()) {
		 * 
		 * Welcome v1=new
		 * Welcome(Base64.getEncoder().encodeToString(rs.getBytes("image_data")),
		 * rs.getString("model_name"),
		 * rs.getString("fuel_type"),rs.getString("description") ); vehicles.add(v1);
		 * 
		 * 
		 * }
		 * 
		 * Gson gson=new Gson(); String json =gson.toJson(vehicles); return json;
		 * 
		 * }catch (SQLException e) { // TODO: handle exception e.printStackTrace();
		 * return null; } } }
		 */