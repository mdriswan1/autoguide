package autoguide.service;

import autoguide.dao.VehicleDetailsDao;

/**
 *  This service is used to call VehicleDetailsDao and get the vehicle details  
 *  based on the method it will the return the data
 */


public class VehicleDetailsService {
/**
 * this method return all vehicle 
 * @return all Vehicle details
 */
	public static String vehicleDetails() {
		return VehicleDetailsDao.allVehicles();
	}
/**
 * this method return all vehicle based on the manufacturer amd model
 * @param manu
 * @param model
 * @return vehicle details
 */
	public static String vehicleDetails(String manu, String model) {
		return VehicleDetailsDao.getVehicle(manu, model);
	}
	/**
	 *  this method return first 3 vehicle details (used for before login)
	 * @return 3 vehicle details
	 */
	public static String getWelcomeDetails() {
		return VehicleDetailsDao.getsomeDetails();
	}
}
