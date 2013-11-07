package entity;

public class Stop {
	
	private String name;
	private String address;
	private Double latitude;
	private Double longitude;
	
	public Stop(String name, String address, Double latitude, Double longitude) {
		this.name = name;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	public Double getLongitude() {
		return longitude;
	}

	public void setlongitude(Double longitude) {
		this.latitude = longitude;
	}
	
	public String toString(){
		String ret = name+", "+address+", "+latitude+", "+longitude;
		return ret;
	}
	
}
