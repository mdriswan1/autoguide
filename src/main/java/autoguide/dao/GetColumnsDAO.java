package autoguide.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class GetColumnsDAO {
	// gives two or four wheeler
	public static String getVehicleType() {
		List<String> types = new ArrayList<String>();
		try (Connection con = CreateConnection.getConnection(); Statement s = con.createStatement()) {
			ResultSet rs = s.executeQuery("select distinct(type_name) from vehicle_type");
			while (rs.next()) {
				types.add(rs.getString("type_name"));
			}
			Gson gson = new Gson();
			String json = gson.toJson(types);
			return json;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	// it gives maufcature name
	public static String getVehicleManufacturer(String vehicle_type) {
		List<String> types = new ArrayList<String>();
		try (Connection con = CreateConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(
						"select name from  manufacturer where type_id in(select type_id from vehicle_type where type_name=?)")) {
			ps.setString(1, vehicle_type);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				types.add(rs.getString("name"));
			}
			Gson gson = new Gson();
			String json = gson.toJson(types);
			return json;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public static String getVehicleModel(String manufaturer) {
		List<String> types = new ArrayList<String>();
		try (Connection con = CreateConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(
						"select model_name from vehicle_model where manufacturer_id in (select manufacturer_id from manufacturer where name =?)")) {
			ps.setString(1, manufaturer);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				types.add(rs.getString("model_name"));
			}
			Gson gson = new Gson();
			String json = gson.toJson(types);
			return json;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
}
