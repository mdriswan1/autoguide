package autoguide.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import autoguide.dao.GetColumnsDAO;

/**
 * this is the service class to call the getcolumnDao class (Service to DAO)
 */
public class GetColumnService {
	private static final Logger logger = LogManager.getLogger(GetColumnService.class);

	/**
	 * this method is used to call the getVehicleType method and get the data
	 * 
	 * @return vehicle type
	 */

	public static String getVehicleType() {
		logger.debug("getVehicleType method is called");
		return GetColumnsDAO.vehicleColumn(null, null);
	}

	/**
	 * this method is used to call the getVehicleManufacturer method and get the data
	 * 
	 * @return manufacturer_name
	 */
	public static String getVehicleManufacturer(String vehicle_type) {
		logger.debug("getVehicleManufacturer  method is called");
		return GetColumnsDAO.vehicleColumn(vehicle_type, null);
	}

	/**
	 * this method is used to call the getVehicleModel method and get the data
	 * 
	 * @return model_name
	 */

	public static String getVehicleModel(String manufacturer) {
		logger.debug("getVehicleModel method is called");
		return GetColumnsDAO.vehicleColumn(null, manufacturer);
	}
}
