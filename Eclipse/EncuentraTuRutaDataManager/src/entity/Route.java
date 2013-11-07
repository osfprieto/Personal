package entity;

import java.util.LinkedList;

public class Route {

	private String name;
	private String description;
	private LinkedList<String> stops;
	private LinkedList<Double> latitudes;
	private LinkedList<Double> longitudes;
	
	public Route(String name, String description) {
		super();
		this.name = name;
		this.description = description;
		stops = new LinkedList<String>();
		latitudes = new LinkedList<Double>();
		longitudes = new LinkedList<Double>();
	}
	
	public void addStop(String address){
		stops.add(address);
	}
	
	public void addCoordinate(Double latitude, Double longitud){
		latitudes.add(latitude);
		longitudes.add(longitud);
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

	public LinkedList<String> getStops() {
		return stops;
	}

	public void setStops(LinkedList<String> stops) {
		this.stops = stops;
	}

	public LinkedList<Double> getLatitudes() {
		return latitudes;
	}

	public void setLatitudes(LinkedList<Double> latitudes) {
		this.latitudes = latitudes;
	}
	
	public LinkedList<Double> getLongitudes() {
		return longitudes;
	}

	public void setLongitudes(LinkedList<Double> longitudes) {
		this.longitudes = longitudes;
	}
	
	public String toString(){
		String ret = name;
		ret += "\n"+description+"\n";
		int i;
		for(i=0;i<stops.size();i++){
			ret+=stops.get(i);
			if(i<stops.size()-1)
				ret+=", ";
		}
		ret+="\n";
		for(i=0;i<latitudes.size();i++){
			ret+=latitudes.get(i)+", "+longitudes.get(i);
			if(i<latitudes.size()-1)
				ret+="; ";
		}
		
		return ret;
	}
}
