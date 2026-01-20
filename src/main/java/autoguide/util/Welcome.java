package autoguide.util;

public class Welcome {
	String vehicleImage ;
	public void setVehicleImage(String vehicleImage) {
		this.vehicleImage = vehicleImage;
	}
	String vehicleName;
	
	String fuelType;
	String description;
	
	public Welcome(String vehicleImage, String vehcileName, String fuelType, String description) {
		super();
		this.vehicleImage = vehicleImage;
		this.vehicleName = vehcileName;
		this.fuelType = fuelType;
		this.description = description;
	}
	
	public String getVehicleImage() {
		return vehicleImage;
	}
	public String getVehcileName() {
		return vehicleName;
	}
	public void setVehcileName(String vehcileName) {
		this.vehicleName = vehcileName;
	}
	
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
