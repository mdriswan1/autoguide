package autoguide.util;

public class VehicleNews {
	private int news_id;
	private int model_id;
	private String link;
	public VehicleNews(int news_id, int model_id, String link) {
		super();
		this.news_id = news_id;
		this.model_id = model_id;
		this.link = link;
	}
	public int getNews_id() {
		return news_id;
	}
	public void setNews_id(int news_id) {
		this.news_id = news_id;
	}
	public int getModel_id() {
		return model_id;
	}
	public void setModel_id(int model_id) {
		this.model_id = model_id;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	

}
