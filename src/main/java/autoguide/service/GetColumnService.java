package autoguide.service;

import autoguide.dao.GetColumnsDAO;
/**
 * this is the service class to call the getcolumnDao class (Service to DAO)
 */
public class GetColumnService {
	
	/**
	 * this method is used to call the getVehicleType method and get the data
	 * @return vehicle type
	 */
	
	public static String getVehicleType() {
		return GetColumnsDAO.getVehicleType();
	}
	
	/**
	 * this method is used to call the getVehicleManufacturer method and get the data
	 * @return manufacturer_name
	 */
	public static String getVehicleManufacturer(String vehicle_type) {
		return GetColumnsDAO.getVehicleManufacturer(vehicle_type);
	}
	/**
	 * this method is used to call the getVehicleModel method and get the data
	 * @return model_name
	 */
	
	public static String getVehicleModel(String manufacturer) {
		return GetColumnsDAO.getVehicleModel(manufacturer);
	}
}
