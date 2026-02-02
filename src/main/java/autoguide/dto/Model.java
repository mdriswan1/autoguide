package autoguide.dto;

public class Model implements GetColumn{
  private String model_name;
  private int model_id;
  public int getModel_id() {
	return model_id;
}
  public void setModel_id(int model_id) {
	this.model_id = model_id;
  }
  public Model(int model_id, String model_name) {
	super();
	this.model_name = model_name;
	this.model_id = model_id;
  }
}
