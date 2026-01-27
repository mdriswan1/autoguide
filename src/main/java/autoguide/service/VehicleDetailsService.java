package autoguide.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import autoguide.dao.VehicleDetailsDao;

/**
 *  This service is used to call VehicleDetailsDao and get the vehicle details  
 *  based on the method it will the return the data
 */


public class VehicleDetailsService {
	  private static final Logger logger=LogManager.getLogger(VehicleDetailsService.class);
/**
 * this method return all vehicle 
 * @return all Vehicle details
 */
	public static String vehicleDetails() {
		logger.debug("vehicleDetails method is called in service module");
		return VehicleDetailsDao.allVehicles();
	}
/**
 * this method return all vehicle based on the manufacturer amd model
 * @param manu
 * @param model
 * @return vehicle details
 */
	public static String vehicleDetails(String manu, String model) {
		logger.debug("vehicleDetails  method is called with two inputs in service module");
		return VehicleDetailsDao.getVehicle(manu, model);
	}
	/**
	 *  this method return first 3 vehicle details (used for before login)
	 * @return 3 vehicle details
	 */
	public static String getWelcomeDetails() {
		logger.debug("getWelcomeDetails method is called in service module");
		return VehicleDetailsDao.getsomeDetails();
	}
}
