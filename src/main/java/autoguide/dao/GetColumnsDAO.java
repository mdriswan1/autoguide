package autoguide.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import autoguide.db.CreateConnection;
import autoguide.dto.GetColumn;
import autoguide.dto.Manufacturer;
import autoguide.dto.Model;
import autoguide.dto.VehicleType;

/**
 * this class is used for the filter option (Vehicle_type,manufacturer,model)
 */
public class GetColumnsDAO {
	private static final Logger logger = LogManager.getLogger(GetColumnsDAO.class);

	/**
	 * this method is used to get the drop down option for the user
	 * 
	 * @param vehicle_type
	 * @param manufacturer
	 * @return
	 */

	public static String vehicleColumn(Integer vehicle_type, Integer manufacturer) {
		List<GetColumn> types = new ArrayList<GetColumn>();
		PreparedStatement ps = null;
		try (Connection con = CreateConnection.getConnection()) {

			if (vehicle_type == null && manufacturer == null) {
				/**
				 * this load the vehicle type to shown in the drop down option
				 */
				ps = con.prepareStatement("select * from vehicle_type");
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					types.add(new VehicleType(rs.getInt("type_id"),rs.getString("type_name")));
				}
				logger.debug("get the vehicle type");
			} else if (vehicle_type != null && manufacturer == null) {
				/**
				 * this will execute when the user enter the vehicle type based on the vehicle type it will return the manufacturer
				 * (eg:two_wheeler->tvs,bajaj)
				 */

				ps = con.prepareStatement(
								"select name,manufacturer_id from  manufacturer where type_id=?");
				ps.setInt(1, vehicle_type);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					types.add(new Manufacturer(rs.getInt("manufacturer_id"),rs.getString("name")));
				}
				logger.debug("get the manufacturer");
			} else if (vehicle_type == null && manufacturer != null) {
				/**
				 * this will execute when the user enter the manufacturer based on the manufacturer it will return the model
				 * (eg:model(maruti Suzuki)->swift VXI)
				 */
				ps = con.prepareStatement(
								"select model_name,model_id from vehicle_model where manufacturer_id =?");
				ps.setInt(1, manufacturer);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					types.add(new Model(rs.getInt("model_id"),rs.getString("model_name")));
				}
				logger.debug("get the model");
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

}
