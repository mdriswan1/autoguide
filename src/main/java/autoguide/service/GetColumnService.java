package autoguide.service;

import autoguide.dao.GetColumnsDAO;

public class GetColumnService {
	public static String getVehicleType() {
		return GetColumnsDAO.getVehicleType();
	}
	
	
	public static String getVehicleManufacturer(String vehicle_type) {
		return GetColumnsDAO.getVehicleManufacturer(vehicle_type);
	}
	
	
	public static String getVehicleModel(String manufacturer) {
		return GetColumnsDAO.getVehicleModel(manufacturer);
	}
}
