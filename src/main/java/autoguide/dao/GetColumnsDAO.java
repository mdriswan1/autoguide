package autoguide.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class GetColumnsDAO {
	public static String getVehicleType() {
		List<String> types=new ArrayList<String>();
		try(Connection con=CreateConnection.getConnection();Statement s=con.createStatement()){
			ResultSet rs=s.executeQuery("select distinct(type_name) from vehicle_type");
			while(rs.next()) {
				types.add(rs.getString("type_name"));
			}
			Gson gson=new Gson();
			String json=gson.toJson(types);
			return json;
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static String getVehicleManufacturer() {
		List<String> types=new ArrayList<String>();
		try(Connection con=CreateConnection.getConnection();Statement s=con.createStatement()){
			ResultSet rs=s.executeQuery("select distinct(name) from manufacturer");
			while(rs.next()) {
				types.add(rs.getString("name"));
			}
			Gson gson=new Gson();
			String json=gson.toJson(types);
			return json;
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static String getVehicleModel() {
		List<String> types=new ArrayList<String>();
		try(Connection con=CreateConnection.getConnection();Statement s=con.createStatement()){
			ResultSet rs=s.executeQuery("select distinct(model_name) from vehicle_model");
			while(rs.next()) {
				types.add(rs.getString("model_name"));
			}
			Gson gson=new Gson();
			String json=gson.toJson(types);
			return json;
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
}
