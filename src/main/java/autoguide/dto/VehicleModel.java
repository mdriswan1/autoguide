package autoguide.dto;

public class VehicleModel {
	private  int model_id;
	private int manufacturer_id;
	private int type_id;
	private String model_name;
	private int year_of_manufacture;
	private String engine_capacity;
	private String fuel_type;
	private int length_mm;
	private int width_mm;
	private int height_mm;
	private String description;
	public VehicleModel(int model_id, int manufacturer_id, int type_id, String model_name, int year_of_manufacture, String engine_capacity,
					String fuel_type, int length_mm, int width_mm, int height_mm, String description) {
		super();
		this.model_id = model_id;
		this.manufacturer_id = manufacturer_id;
		this.type_id = type_id;
		this.model_name = model_name;
		this.year_of_manufacture = year_of_manufacture;
		this.engine_capacity = engine_capacity;
		this.fuel_type = fuel_type;
		this.length_mm = length_mm;
		this.width_mm = width_mm;
		this.height_mm = height_mm;
		this.description = description;
	}
	public int getModel_id() {
		return model_id;
	}
	public void setModel_id(int model_id) {
		this.model_id = model_id;
	}
	public int getManufacturer_id() {
		return manufacturer_id;
	}
	public void setManufacturer_id(int manufacturer_id) {
		this.manufacturer_id = manufacturer_id;
	}
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public String getModel_name() {
		return model_name;
	}
	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}
	public int getYear_of_manufacture() {
		return year_of_manufacture;
	}
	public void setYear_of_manufacture(int year_of_manufacture) {
		this.year_of_manufacture = year_of_manufacture;
	}
	public String getEngine_capacity() {
		return engine_capacity;
	}
	public void setEngine_capacity(String engine_capacity) {
		this.engine_capacity = engine_capacity;
	}
	public String getFuel_type() {
		return fuel_type;
	}
	public void setFuel_type(String fuel_type) {
		this.fuel_type = fuel_type;
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
