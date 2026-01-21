package autoguide.service;

import autoguide.dao.GetColumnsDAO;

public class GetColumnService {
	public static String getVehicleType() {
		return GetColumnsDAO.getVehicleType();
	}
	
	
	public static String getVehicleManufacturer() {
		return GetColumnsDAO.getVehicleManufacturer();
	}
	
	
	public static String getVehicleModel() {
		return GetColumnsDAO.getVehicleModel();
	}
}
