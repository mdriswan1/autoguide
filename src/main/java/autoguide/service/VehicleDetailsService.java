package autoguide.service;

import autoguide.dao.VehicleDetailsDao;

public class VehicleDetailsService {
	
	public static String vehicleDetails() {
		return VehicleDetailsDao.allVehicles();
	}
}
