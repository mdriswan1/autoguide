package autoguide.util;

public class AllVehicleDetails {
	String vehicle_image;
	String vehicle_type;
	String vehicle_name;
	int year;
	int seat_capacity;
	String engine_capacity;
	int length_mm;
	int width_mm;
	int height_mm;
	String description;
	public AllVehicleDetails(String vehicle_image, String vehicle_type, String vehicle_name,
			int year, int seat_capacity, String engine_capacity, int length_mm, int width_mm, int height_mm,
			String description) {
		super();
		this.vehicle_image = vehicle_image;
		this.vehicle_type = vehicle_type;
		this.vehicle_name = vehicle_name;
		this.year = year;
		this.seat_capacity = seat_capacity;
		this.engine_capacity = engine_capacity;
		this.length_mm = length_mm;
		this.width_mm = width_mm;
		this.height_mm = height_mm;
		this.description = description;
	}
	public String getVehicle_image() {
		return vehicle_image;
	}
	public void setVehicle_image(String vehicle_image) {
		this.vehicle_image = vehicle_image;
	}
	public String getVehicle_type() {
		return vehicle_type;
	}
	public void setVehicle_type(String vehicle_type) {
		this.vehicle_type = vehicle_type;
	}
	public String getVehicle_name() {
		return vehicle_name;
	}
	public void setVehicle_name(String vehicle_name) {
		this.vehicle_name = vehicle_name;
	}

	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getSeat_capacity() {
		return seat_capacity;
	}
	public void setSeat_capacity(int seat_capacity) {
		this.seat_capacity = seat_capacity;
	}
	public String getEngine_capacity() {
		return engine_capacity;
	}
	public void setEngine_capacity(String engine_capacity) {
		this.engine_capacity = engine_capacity;
	}
	public int getLength_mm() {
		return length_mm;
	}
	public void setLength_mm(int length_mm) {
		this.length_mm = length_mm;
	}
	public int getWidth_mm() {
		return width_mm;
	}
	public void setWidth_mm(int width_mm) {
		this.width_mm = width_mm;
	}
	public int getHeight_mm() {
		return height_mm;
	}
	public void setHeight_mm(int height_mm) {
		this.height_mm = height_mm;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
