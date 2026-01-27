package autoguide.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import autoguide.util.AllVehicleDetails;
import autoguide.util.Welcome;

/**
 * this class is used to get the vehicle details from the data base and send it
 * as json as string
 * 
 * @method 1:allVehicles() is used to load all vehicle
 * @method 2:getVehicle(String manu, String name) is use to load the data based
 *         on the manufacturer and model
 * @method 3:getsomeDetails() is used to load the Vehicle data only 3 or 4 for
 *         the front page
 */
public class VehicleDetailsDao {
	private static final Logger logger = LogManager.getLogger(VehicleDetailsDao.class);

	/**
	 * This method is used to return the all vehicle details from data base(From
	 * more then one table)
	 * 
	 * @return all details as json format(String)
	 */
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
			logger.debug("all vehicle details are get from the database");
			return json;

		} catch (SQLException e) {

			e.printStackTrace();
			logger.error("error while fetching all vehicle details");
			return null;
			// TODO: handle exception
		}
	}

	/**
	 * This method is used to return the all vehicle details from data base(From
	 * more then one table) based on the manufacturer and the model filter the
	 * details of the vehicle
	 * 
	 * @return all details as json format(String)
	 */

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
			logger.debug("get all the vehicle details using manufacturer and model_name ");
			return json;

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("error while fetching details for manufacturer and model_name ");
			return null;
		}
	}

	/**
	 * This method is used to return the only 3 vehicle details from data base(From
	 * more then one table) for welcome page
	 * 
	 * @return all details as json format(String)
	 */

	public static String getsomeDetails() {
		try (Connection con = CreateConnection.getConnection(); Statement s = con.createStatement()) {
			String sql = "select model_name,fuel_type,description,image_data from vehicle_model limit 4";
			ResultSet rs = s.executeQuery(sql);
			List<Welcome> vehicles = new ArrayList<Welcome>();

			while (rs.next()) {

				Welcome v1 = new Welcome(Base64.getEncoder().encodeToString(rs.getBytes("image_data")),
						rs.getString("model_name"), rs.getString("fuel_type"), rs.getString("description"));
				vehicles.add(v1);

			}

			Gson gson = new Gson();
			String json = gson.toJson(vehicles);
			logger.debug("get vehicle details from database based for annoyms users");
			return json;

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("error while fetching data for annoynms users");
			return null;
		}
	}

	public static String vehicleTypeDetails(String type) {

		try (Connection con = CreateConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(
						"select vehicle_model.image_data,vehicle_type.type_name,vehicle_model.model_name,vehicle_model.fuel_type,vehicle_model.year_of_manuf,vehicle_model.seating_capacity,vehicle_model.engine_capacity,vehicle_model.length_mm,vehicle_model.width_mm,vehicle_model.height_mm,vehicle_model.description from vehicle_model inner join vehicle_type on vehicle_model.type_id=vehicle_type.type_id and vehicle_model.type_id in (select type_id from vehicle_type where type_name=?);")) {
			ps.setString(1, type);

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
			logger.debug("get all the vehicle details using manufacturer and model_name ");
			return json;

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("error while fetching details for manufacturer and model_name ");
			return null;
		}
	}
	
	
	public static String manufacturerDetails(String type) {

		try (Connection con = CreateConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(
						"select vehicle_model.image_data,vehicle_type.type_name,vehicle_model.model_name,vehicle_model.fuel_type,vehicle_model.year_of_manuf,vehicle_model.seating_capacity,vehicle_model.engine_capacity,vehicle_model.length_mm,vehicle_model.width_mm,vehicle_model.height_mm,vehicle_model.description from vehicle_model inner join vehicle_type on vehicle_model.type_id=vehicle_type.type_id and vehicle_model.manufacturer_id in (select manufacturer_id from manufacturer where name=?);")) {
			ps.setString(1, type);

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
			logger.debug("get all the vehicle details using manufacturer and model_name ");
			return json;

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("error while fetching details for manufacturer and model_name ");
			return null;
		}
	}

	
	
	
}
