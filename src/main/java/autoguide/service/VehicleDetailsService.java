package autoguide.service;

import autoguide.dao.VehicleDetailsDao;

public class VehicleDetailsService {
	
	public static String vehicleDetails() {
		return VehicleDetailsDao.allVehicles();
	}
	public static String vehicleDetails(String manu,String model) {
		return VehicleDetailsDao.getVehicle(manu,model);
	}
}
