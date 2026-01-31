package autoguide.dto;

public class Manufacturer {
	private int manufacturer_id;
	private String name;
	private String description;
	public Manufacturer(int manufacturer_id, String name, String description) {
		super();
		this.manufacturer_id = manufacturer_id;
		this.name = name;
		this.description = description;
	}
	public int getManufacturer_id() {
		return manufacturer_id;
	}
	public void setManufacturer_id(int manufacturer_id) {
		this.manufacturer_id = manufacturer_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
