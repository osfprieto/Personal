package varios;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class KmlParser {

	private static HashMap<String, Route> routes;
	private static HashMap<String, Stop> stops;

	public static void main(String[] args) {
		JFileChooser fileChooser = new JFileChooser();
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			readKml(fileChooser.getSelectedFile());
			outputSQLiteSentences();
		}
	}

	private static class Route {

		private int id;
		private String name;
		private String description;
		private LinkedList<GeoPoint> coordinatesOSM;
		private LinkedList<String> stops;
		
		public Route(String name, String description){
			this.name = name;
			this.description = description;
			this.id = -1;
		}
		
		public void addStop(String stop){
			stops.add(stop);
		}
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
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
		public LinkedList<GeoPoint> getCoordinatesOSM() {
			return coordinatesOSM;
		}
		public void setCoordinatesOSM(LinkedList<GeoPoint> coordinatesOSM) {
			this.coordinatesOSM = coordinatesOSM;
		}
		public LinkedList<String> getStops() {
			return stops;
		}
		public void setStops(LinkedList<String> stops) {
			this.stops = stops;
		}
				

	}

	private static class Stop {

		private int id;
		private String name;
		private String address;
		private GeoPoint coordinates;
	
		public Stop(String name, String address, GeoPoint coordinates){
			this.name = name;
			this.address = address;
			this.coordinates = coordinates;
			this.id = -1;
		}
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
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
		public GeoPoint getCoordinates() {
			return coordinates;
		}
		public void setCoordinates(GeoPoint coordinates) {
			this.coordinates = coordinates;
		}
		
		
		
	}

	private static class GeoPoint {
		
		private Double lat;
		private Double lon;

		public GeoPoint(Double lat, Double lon){
			this.lat = lat;
			this.lon = lon;
		}
		
		public GeoPoint(GeoPoint geo){
			this.lat = geo.lat;
			this.lon = geo.lon;
		}

		public Double getLat() {
			return lat;
		}

		public void setLat(Double lat) {
			this.lat = lat;
		}

		public Double getLon() {
			return lon;
		}

		public void setLon(Double lon) {
			this.lon = lon;
		}
		
		
		
	}

	public static HashMap<String, Route> getRoutes() {
		return routes;
	}

	public static HashMap<String, Stop> getStops() {
		return stops;
	}

	private static void outputSQLiteSentences() {
		
		int indexRoutes = 0;
		int indexStops = 0;
		
		StringBuilder sbTableCreate = new StringBuilder();
		StringBuilder sbRoutes = new StringBuilder();
		StringBuilder sbStops = new StringBuilder();
		StringBuilder sbRouteDrawing = new StringBuilder();
		StringBuilder sbRouteStops = new StringBuilder();
		
		
		sbTableCreate.append("create table routes("+
			"routeid integer primary key not null,"+
			"name text not null,"+
			"description text not null);"+
			
			" create table stops("+
			"stopid integer primary key not null,"+
			"name text not null,"+
			"address text not null,"+
			"latt real not null,"+
			"long real not null);"+
			
			" create table route_drawing("+
			"routeid integer not null,"+
			"sortingindex integer not null,"+
			"latt real not null,"+
			"long real not null,"+
			"foreign key(routeid) references routes(routeid));"+
			"create table route_stops("+
			"routeid integer not null,"+
			"stopid integer not null,"+
			"sortingindex integer not null,"+
			"foreign key(routeid) references routes(routeid),"+
			"foreign key(stopid) references stops(stopid));").append("\n");
		
		Iterator<String> routeIterator = routes.keySet().iterator();
		System.out.println(routes.size());
		System.out.println(stops.size());
		
		while(routeIterator.hasNext()){
			String routeKey = routeIterator.next();
			Route route = routes.get(routeKey);
			if(route.getId()==-1){
				route.setId(indexRoutes++);
				sbRoutes.append(sqlSentence(route)).append("\n");
			}
			
			Iterator<String> routeStopKeyIter = route.getStops().iterator();
			int sortingIndexRouteStops = 0;
			while(routeStopKeyIter.hasNext()){
				String routeStopKey = routeStopKeyIter.next();
				Stop stop = stops.get(routeStopKey);
				if(stop.getId()==-1){
					stop.setId(indexStops++);
					sbStops.append(sqlSentence(stop)).append("\n");
				}
				sbRouteStops.append("insert into route_stops values ("
				+route.getId()+", "+stop.getId()+", "+
						(sortingIndexRouteStops++)+");").append("\n");
			}
			
			Iterator<GeoPoint> routeDrawingIter = route.coordinatesOSM.iterator();
			int sortingIndexRouteDrawing = 0;
			while(routeDrawingIter.hasNext()){
				GeoPoint coordinate = routeDrawingIter.next();
				sbRouteDrawing.append("insert into route_drawing values ("+
				route.getId()+", "+(sortingIndexRouteDrawing++)+", "+
						coordinate.getLat()+", "+coordinate.getLon()
						
						+");").append("\n");
			}
			
		}
		
		/*StringBuilder sbTableCreate = new StringBuilder();
		StringBuilder sbRoutes = new StringBuilder();
		StringBuilder sbStops = new StringBuilder();
		StringBuilder sbRouteDrawing = new StringBuilder();
		StringBuilder sbRouteStops = new StringBuilder();*/
		
		JTextArea area = new JTextArea();
		area.append(sbTableCreate.toString());
		area.append(sbRoutes.toString());
		area.append(sbStops.toString());
		area.append(sbRouteDrawing.toString());
		area.append(sbRouteStops.toString());
		
		JScrollPane scroll = new JScrollPane(area);
		JFrame frame = new JFrame("Data");
		frame.getContentPane().removeAll();
		frame.getContentPane().add(scroll);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 400, 500);
		frame.setVisible(true);
		
	}
	
	public static String sqlSentence(Route route){
		return "insert into routes values ("+route.getId()+
				", "+route.getName()+", "+route.getDescription()+");";
	}
	
	public static String sqlSentence(Stop stop){
		return "insert into stops values ("+stop.getId()+
				", "+stop.getName()+", "+stop.getAddress()+
				", "+stop.getCoordinates().getLat()+
				", "+stop.getCoordinates().getLon()+");";
	}

	public static boolean readKml(File kmlFile) {

		try {

			routes = new HashMap<String, Route>();
			stops = new HashMap<String, Stop>();

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(kmlFile);

			Element rootElement = doc.getDocumentElement();
			rootElement.normalize();

			NodeList routesList = rootElement.getChildNodes().item(1)
					.getChildNodes();
			routesList = routesList.item(routesList.getLength() - 2)
					.getChildNodes();

			// Urbano
			NodeList urbanList = routesList.item(routesList.getLength() - 6)
					.getChildNodes();

			for (int i = 5; i < urbanList.getLength(); i = i + 2) {
				Route route = processRoute(urbanList.item(i));

				if (route != null && !routes.containsKey(route.getName())) {
					routes.put(route.getName(), route);
				}
			}

			return true;
		} catch (Exception e) {
			// Log.v("EXCEPTION READING KML: "+e, "OSFPRIETO");
			return false;
		}

	}

	private static Route processRoute(Node node) {
		if (node != null) {
			// Basic info
			NodeList routeList = node.getChildNodes();
			String name = routeList.item(1).getTextContent();
			String description = routeList.item(5).getTextContent();

			// Omitmos en caso de que la descripciÛn tenga la palabra probando
			if (description.trim().toLowerCase().equals("probando")) {
				description = name;
			}

			// Coordinates
			LinkedList<GeoPoint> partA = processCoordinates(routeList.item(7));
			LinkedList<GeoPoint> partB = processCoordinates(routeList.item(9));
			LinkedList<GeoPoint> coordinates = new LinkedList<GeoPoint>(partA);
			coordinates.addAll(partB);
			coordinates.add(new GeoPoint(partA.get(0)));

			Route route = new Route(name, description);
			route.setCoordinatesOSM(coordinates);

			// Stops
			for (int i = 11; i < routeList.getLength(); i = i + 2) {
				Stop stop = processStop(routeList.item(i));

				if (stop != null) {
					route.addStop(stop.getAddress());

					if (!stops.containsKey(stop.getAddress())) {
						stops.put(stop.getAddress(), stop);
					}
				}
			}

			return route;
		} else {
			return null;
		}
	}

	/**
	 * Splits the parts of the addresses in order to obtain the address itself.
	 */
	private static Pattern addressExtractor = Pattern.compile("(("
			+ Pattern.quote("<![CDATA[") + ")|("
			+ Pattern.quote("<div dir=\"ltr\">") + ")|("
			+ Pattern.quote("<span style=\"font-size:10pt\">") + ")|("
			+ Pattern.quote("<span style=\"font-size:small\">") + ")|("
			+ Pattern.quote("<font color=\"#cc0000\">") + "))*(["
			+ Pattern.quote("-*/.\\[]{}_#@,()")
			+ "\\sA-Za-z0-9·ÈÌÛ˙¡…Õ”⁄ƒÀœ÷‹‰ÎÔˆ¸\n]+)(("
			+ Pattern.quote("</div>") + ")|(" + Pattern.quote("]]>") + ")|("
			+ Pattern.quote("</span>") + ")|(" + Pattern.quote("&nbsp;")
			+ ")|(" + Pattern.quote("</font>") + "))*");

	private static Stop processStop(Node node) {
		if (node != null) {
			NodeList stopList = node.getChildNodes();

			String name = stopList.item(1).getTextContent().trim();
			Node pointNode = stopList.item(stopList.getLength() - 2);

			String address = stopList.item(5).getTextContent().trim();
			Matcher addressMatcher = addressExtractor.matcher(address);

			if (addressMatcher.matches()) {
				address = addressMatcher.group(7);

				// System.out.print(name + ": {");
				// for (int i = 0; i < addressMatcher.groupCount(); i++) {
				// System.out.print(" " + i + "=" + addressMatcher.group(i));
				// }
				// System.out.println("}");
			}

			NodeList pointList = pointNode.getChildNodes();
			pointNode = pointList.item(1);
			String point = pointNode.getChildNodes().item(0).getTextContent()
					.trim();

			String[] points = point.split("\\s*,\\s*");
			GeoPoint geoPoint = new GeoPoint(Double.parseDouble(points[1]),
					Double.parseDouble(points[0]));

			return new Stop(name, address, geoPoint);
		} else {
			return null;
		}
	}

	private static LinkedList<GeoPoint> processCoordinates(Node node) {
		LinkedList<GeoPoint> coordinates = new LinkedList<GeoPoint>();

		if (node != null) {
			NodeList coordinatesList = node.getChildNodes();
			node = coordinatesList.item(coordinatesList.getLength() - 2)
					.getChildNodes().item(1);

			String lineCoordinates = node.getTextContent().replaceAll("\n", "")
					.trim();
			String[] coordinatesPairs = lineCoordinates.split("\\s+");

			for (String coordinateLine : coordinatesPairs) {
				String[] pair = coordinateLine.split("\\s*,\\s*");
				coordinates.add(new GeoPoint(Double.parseDouble(pair[1]),
						Double.parseDouble(pair[0])));
			}

			return coordinates;
		} else {
			return coordinates;
		}
	}
}