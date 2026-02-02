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

import autoguide.db.CreateConnection;
import autoguide.dto.AllVehicleDetails;
import autoguide.dto.Welcome;

/**
 * this class is used to get the vehicle details from the data base and send it as json as string
 */
public class VehicleDetailsDao {
	private static final Logger logger = LogManager.getLogger(VehicleDetailsDao.class);

	/**
	 * this method is used to get the vehicle details based on the user input
	 * 
	 * @param type
	 * @param manufacturer
	 * @param model_name
	 * @return vehicle details
	 */

	public static String vehicleDetails(Integer type, Integer manufacturer, Integer model_name) {

		List<AllVehicleDetails> all = new ArrayList<AllVehicleDetails>();
		ResultSet rs = null;
		PreparedStatement ps = null;

		try (Connection con = CreateConnection.getConnection()) {
			if (type == null && manufacturer == null && model_name == null) {
				/**
				 * this load the all vehicle details
				 */
				ps = con.prepareStatement(
								"select * from vehicle_model inner join vehicle_type on vehicle_model.type_id= vehicle_type.type_id left join vehicle_news on vehicle_news.model_id=vehicle_model.model_id");

			} else if (type != null && manufacturer == null && model_name == null) {
				/**
				 * this load the all vehicle details based on the vehicle type
				 */
				ps = con.prepareStatement(
								"select * from vehicle_model inner join vehicle_type on vehicle_model.type_id=vehicle_type.type_id and vehicle_model.type_id =? left join vehicle_news on vehicle_news.model_id=vehicle_model.model_id;");
				ps.setInt(1, type);
			} else if (type == null && manufacturer != null && model_name == null) {
				/**
				 * this load the all vehicle details based on the manufacturer
				 */
				ps = con.prepareStatement(
								"select * from vehicle_model inner join vehicle_type on vehicle_model.type_id=vehicle_type.type_id and vehicle_model.manufacturer_id=? left join vehicle_news on vehicle_news.model_id=vehicle_model.model_id;");
				ps.setInt(1, manufacturer);

			} else if (type == null && manufacturer != null && model_name != null) {
				/**
				 * this load the all vehicle details based on manufacturer and model
				 */
				ps = con.prepareStatement(
								"select * from vehicle_model inner join manufacturer on manufacturer.manufacturer_id=? and vehicle_model.model_id=? inner join vehicle_type on vehicle_model.type_id=vehicle_type.type_id left join vehicle_news on vehicle_news.model_id=vehicle_model.model_id  ;");
				ps.setInt(1, manufacturer);
				ps.setInt(2, model_name);
			}
			if (ps != null) {
				rs = ps.executeQuery();
				String image = "";
				while (rs.next()) {
					if (rs.getBytes("image_data") != null) {
						image = Base64.getEncoder().encodeToString(rs.getBytes("image_data"));
					}

					AllVehicleDetails v = new AllVehicleDetails(image, rs.getString("type_name"), rs.getString("model_name"),
									rs.getString("fuel_type"), rs.getInt("year_of_manuf"), rs.getInt("seating_capacity"),
									rs.getString("engine_capacity"), rs.getInt("length_mm"), rs.getInt("width_mm"), rs.getInt("height_mm"),
									rs.getString("description"), rs.getString("link"));

					all.add(v);

				}
				Gson gson = new Gson();
				String json = gson.toJson(all);
				System.out.println(json);
				logger.debug("get all the vehicle details using manufacturer and model_name ");
				return json;
			}
			return null;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("error while fetching details for manufacturer and model_name ");
			return null;
		}
	}

	/**
	 * this method get the vehicle details for the welcome page(guest users)
	 * 
	 * @return vehicle_details for guest user
	 */
	public static String getsomeDetails() {
		try (Connection con = CreateConnection.getConnection(); Statement s = con.createStatement()) {
			String sql = "select model_name,fuel_type,description,image_data from vehicle_model limit 3";
			ResultSet rs = s.executeQuery(sql);
			List<Welcome> vehicles = new ArrayList<Welcome>();

			while (rs.next()) {

				Welcome v1 = new Welcome(Base64.getEncoder().encodeToString(rs.getBytes("image_data")), rs.getString("model_name"),
								rs.getString("fuel_type"), rs.getString("description"));
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
}
