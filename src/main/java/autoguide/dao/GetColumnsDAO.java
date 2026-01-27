package autoguide.dao;

import java.sql.Connection;
/**
 * this class is used for the filter option (Vehicle_type,manufacturer,model)
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import autoguide.servlet.FrontController;

public class GetColumnsDAO {
	private static final Logger logger = LogManager.getLogger(GetColumnsDAO.class);

	/**
	 * this method is used to get the vehicle type from the vehicle type table from
	 * the data base
	 * 
	 * @return vehicle type
	 */
	public static String getVehicleType() {
		List<String> types = new ArrayList<String>();
		try (Connection con = CreateConnection.getConnection(); Statement s = con.createStatement()) {
			ResultSet rs = s.executeQuery("select distinct(type_name) from vehicle_type");
			while (rs.next()) {
				types.add(rs.getString("type_name"));
			}
			Gson gson = new Gson();
			String json = gson.toJson(types);
			logger.debug("vehicle types return json format");
			return json;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("details not get from the database");
			return null;
		}
	}

	/**
	 * this method is used to get the manufacturer from the manufacturer table from
	 * the data base based on the vehicle type
	 * 
	 * @return manufacturer name
	 */
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
			logger.debug("manufacturer details based on vehicle_type return json format");
			return json;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("details not get from the database");
			return null;
		}
	}

	/**
	 * this method is used to get the model name from the vehicle_model table from
	 * the data base based on the manufacturer
	 * 
	 * @return model
	 */
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
			logger.debug("vehicle_details return based on manufactrer and model_name return in json format");
			return json;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("details not get from the database");
			
			return null;
		}
	}
}
