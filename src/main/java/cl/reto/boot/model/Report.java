package cl.reto.boot.model;

public class Report {

	private String cityName;
	private String lowest;
	private String highest;
	private String message;
	
	public Report() {}

	public Report(String cityName, String lowest, String highest, String message) {
		super();
		this.cityName = cityName;
		this.lowest = lowest;
		this.highest = highest;
		this.message = message;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getLowest() {
		return lowest;
	}

	public void setLowest(String lowest) {
		this.lowest = lowest;
	}

	public String getHighest() {
		return highest;
	}

	public void setHighest(String highest) {
		this.highest = highest;
	}

	@Override
	public String toString() {
		return "Report [cityName=" + cityName + ", lowest=" + lowest + ", highest=" + highest + ", message=" + message
				+ "]";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	};

}
