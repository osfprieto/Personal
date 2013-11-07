package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

import entity.*;

@SuppressWarnings("serial")
public class PrincipalFrame extends JFrame implements ActionListener{

	public static final String STOPS_FILENAME = "stops.txt";
	public static final String ROUTES_FILENAME = "routes.txt";
	
	private String route;
	
	private HashMap<String, Stop> stops;
	private HashMap<String, Route> routes;
	
	private JButton buttonSaveData;
	private JButton buttonAdminRoutes;
	private JButton buttonAdminStops;
	
	public static void main(String[] args) {
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		JOptionPane.showMessageDialog(null, "Elige la ubicación de\nlos archivos de rutas", "Ubicación", JOptionPane.QUESTION_MESSAGE);
		
		if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
			String route = fileChooser.getSelectedFile().getAbsolutePath()+File.separator;
			PrincipalFrame frame = new PrincipalFrame(route);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		else{
			System.exit(0);
		}
	}
	
	public PrincipalFrame(String route){
		super("Encuentra tu ruta data manager");
		this.route = route;
		routes = new HashMap<String, Route>();
		stops = new HashMap<String, Stop>();
		loadData();
		Container cont = this.getContentPane();
		cont.removeAll();
		cont.setLayout(new GridLayout(4, 1));
		cont.add(new JLabel("Select your action. Don't close this until you're saved and done."));
		buttonAdminRoutes = new JButton("Admin routes");
		buttonAdminStops = new JButton("Admin stops");
		buttonSaveData = new JButton("Commit");
		buttonAdminRoutes.addActionListener(this);
		buttonAdminStops.addActionListener(this);
		buttonSaveData.addActionListener(this);
		cont.add(buttonAdminRoutes);
		cont.add(buttonAdminStops);
		cont.add(buttonSaveData);
		pack();
		setResizable(false);
		setBounds(100, 100, getWidth(), getHeight());
	}
	
	private void loadData(){
		try{
			File file;
			Scanner sc;
			String fileRoute;
			
			fileRoute = route+STOPS_FILENAME;
			file = new File(fileRoute);
			
			if(file.canRead()){//Read stops
				
				sc = new Scanner(file);
				while(sc.hasNext()){
					String markerStrings[] = sc.nextLine().trim().split(",");
					
					String name = markerStrings[0].trim();
					String address = markerStrings[1].trim();
					Double lat = Double.parseDouble(markerStrings[2].trim());
					Double lng = Double.parseDouble(markerStrings[3].trim());
					
					Stop stop = new Stop(name, address, lat, lng);
					
					stops.put(address, stop);
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "File not found: "+
						STOPS_FILENAME+". Will not read", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			
			fileRoute = route+ROUTES_FILENAME;
			file = new File(fileRoute);
			
			if(file.canRead()){//Read routes
				sc = new Scanner(file);
				while(sc.hasNext()){
					readRoute(sc.nextLine().trim());
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "File not found: "+
						ROUTES_FILENAME+". Will not read", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		catch(Exception e){
			
		}
	}
	
	private void readRoute(String fileName){
		try{
			File file = new File(route+fileName);
			
			Scanner sc = new Scanner(file);
			
			String name = sc.nextLine().trim();
			String description = sc.nextLine().trim();
			String stopsStrings[] = sc.nextLine().split(",");
			String coordinateStrings[] = sc.nextLine().split(";");
			
			Route route = new Route(name, description);
			
			int i;
			
			for(i=0;i<stopsStrings.length;i++)
				route.addStop(stopsStrings[i].trim());
			
			for(i=0;i<coordinateStrings.length;i++){
				String latLngStrings[] = coordinateStrings[i].trim().split(",");
				Double lat = Double.parseDouble(latLngStrings[0].trim());
				Double lng = Double.parseDouble(latLngStrings[1].trim());
				route.addCoordinate(lat, lng);	
			}
			routes.put(name, route);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error reading route: "+fileName, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void saveData(){
		try{
			File file;
			BufferedWriter bw;
			FileWriter fw;
			
			file = new File(route+STOPS_FILENAME);
			
			if (!file.exists()) {
				file.createNewFile();
			}
			fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			
			//Guardar datos de los stops
			
			Set<String> stopKeys = stops.keySet();
			
			for(String s : stopKeys){
				bw.write(stops.get(s).toString()+"\n");
			}
			
			bw.close();
			
			//Abrir archivo de las routes y guardar eso llamando al mÃ©todo saveRoute para cada ruta
			
			file = new File(route+ROUTES_FILENAME);
			
			if (!file.exists()) {
				file.createNewFile();
			}
			fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			
			//Guardar datos de los stops
			
			Set<String> routeKeys = routes.keySet();
			
			for(String s : routeKeys){
				bw.write(s+"\n");
				saveRoute(s);
			}
			
			bw.close();
			
			JOptionPane.showMessageDialog(null, "Data saved", "Success", JOptionPane.INFORMATION_MESSAGE);
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error saving data, try again", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void saveRoute(String routeKey){
		try{
			File file;
			BufferedWriter bw;
			FileWriter fw;
			file = new File(route+routeKey);
			
			if (!file.exists()) {
				file.createNewFile();
			}
			
			fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			
			//Guardar datos de los stops
			
			bw.write(routes.get(routeKey).toString());
			
			bw.close();
		}catch(Exception e){
			
		}
	}
	
	private void adminRoutes(){
		AdminRoutesFrame frame = new AdminRoutesFrame(stops, routes, this);
		frame.setVisible(true);
		buttonAdminStops.setEnabled(false);
		buttonAdminRoutes.setEnabled(false);
		buttonSaveData.setEnabled(false);
	}
	
	private void adminStops(){
		AdminStopsFrame frame = new AdminStopsFrame(stops, routes, this);
		frame.setVisible(true);
		buttonAdminStops.setEnabled(false);
		buttonAdminRoutes.setEnabled(false);
		buttonSaveData.setEnabled(false);
	}
	
	public void liberateRoutes(){
		enableButtons();
	}
	
	public void liberateStops(){
		enableButtons();
	}
	
	private void enableButtons(){
		buttonAdminStops.setEnabled(true);
		buttonAdminRoutes.setEnabled(true);
		buttonSaveData.setEnabled(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(buttonSaveData)){
			saveData();
		}
		else if(e.getSource().equals(buttonAdminRoutes)){
			adminRoutes();
		}
		else if(e.getSource().equals(buttonAdminStops)){
			adminStops();
		}
	}
	
}
