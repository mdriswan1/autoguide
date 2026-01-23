package autoguide.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.google.gson.Gson;

import autoguide.util.AllVehicleDetails;

public class VehicleDetailsDao {
	public static String allVehicles() {
		try (Connection con = CreateConnection.getConnection(); Statement s = con.createStatement()) {
			List<AllVehicleDetails> all = new ArrayList<AllVehicleDetails>();
			ResultSet rs = s.executeQuery(
					"select * from vehicle_model inner join vehicle_type on vehicle_model.type_id= vehicle_type.type_id");
			String image = "";
			while (rs.next()) {
				if (rs.getBytes("image_data") != null) {
					image = Base64.getEncoder().encodeToString(rs.getBytes("image_data"));
				}
				AllVehicleDetails v = new AllVehicleDetails(image, rs.getString("type_name"),
						rs.getString("model_name"), rs.getString("fuel_type"), rs.getInt("year_of_manuf"),
						rs.getInt("seating_capacity"), rs.getString("engine_capacity"), rs.getInt("length_mm"),
						rs.getInt("width_mm"), rs.getInt("height_mm"), rs.getString("description"));
				all.add(v);

			}
			Gson gson = new Gson();
			String json = gson.toJson(all);
			System.out.println(json);
			return json;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
			// TODO: handle exception
		}
	}

	public static String getVehicle(String manu, String name) {
		try (Connection con = CreateConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(
						"select vehicle_model.image_data,vehicle_type.type_name,vehicle_model.model_name,vehicle_model.fuel_type,vehicle_model.year_of_manuf,vehicle_model.seating_capacity,vehicle_model.engine_capacity,vehicle_model.length_mm,vehicle_model.width_mm,vehicle_model.height_mm,vehicle_model.description from vehicle_model inner join manufacturer on manufacturer.manufacturer_id=(select m1.manufacturer_id from manufacturer m1 where m1.name=?) and vehicle_model.model_name=? inner join vehicle_type on vehicle_model.type_id=vehicle_type.type_id ;")) {
			ps.setString(1, manu);
			ps.setString(2, name);
			List<AllVehicleDetails> all = new ArrayList<AllVehicleDetails>();
			ResultSet rs = ps.executeQuery();
			String image = "";
			while (rs.next()) {
				if (rs.getBytes("image_data") != null) {
					image = Base64.getEncoder().encodeToString(rs.getBytes("image_data"));
				}
				AllVehicleDetails v = new AllVehicleDetails(image, rs.getString("type_name"),
						rs.getString("model_name"), rs.getString("fuel_type"), rs.getInt("year_of_manuf"),
						rs.getInt("seating_capacity"), rs.getString("engine_capacity"), rs.getInt("length_mm"),
						rs.getInt("width_mm"), rs.getInt("height_mm"), rs.getString("description"));
				all.add(v);

			}
			Gson gson = new Gson();
			String json = gson.toJson(all);
			System.out.println(json);
			return json;

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
}
