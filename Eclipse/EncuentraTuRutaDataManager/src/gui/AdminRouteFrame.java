package gui;

import entity.Route;
import entity.Stop;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.*;

public class AdminRouteFrame extends JFrame implements ActionListener{

	
	private HashMap<String, Route> routes;
	private HashMap<String, Stop> stops;
	
	private String routeKey;
	
	private Container cont;
	
	private JPanel panelTextFields;
	
	private JTextField textRouteName;
	private JTextField textDesc;
	
	private JPanel panelButtons;
	
	private JButton buttonAddStop;
	private JButton buttonDeleteStop;
	private JButton buttonAddLocation;
	private JButton buttonDeleteLocation;
	
	private JButton buttonUpdateAndExit;
	
	private AdminRoutesFrame superior;
	
	public AdminRouteFrame(HashMap<String, Stop> stops, HashMap<String, Route> routes,
			AdminRoutesFrame superior, String routeKey){
		super(routeKey+" route manager");
		this.routes = routes;
		this.stops = stops;
		this.superior = superior;
		this.routeKey = routeKey;
		this.setUndecorated(true);
		
		textDesc = new JTextField();
		textRouteName = new JTextField();
		
		panelTextFields = new JPanel();
		panelTextFields.setLayout(new GridLayout(2, 2));
		
		panelTextFields.add(new JLabel("Route name:"));
		panelTextFields.add(textRouteName);
		panelTextFields.add(new JLabel("Route desc:"));
		panelTextFields.add(textDesc);
		
		panelButtons = new JPanel();
		panelButtons.setLayout(new GridLayout(2, 2));
		
		buttonAddLocation = new JButton("Add location");
		buttonAddStop = new JButton("Add stop");
		buttonDeleteLocation = new JButton("Delete location");
		buttonDeleteStop = new JButton("Delete stop");
		
		buttonAddLocation.addActionListener(this);
		buttonAddStop.addActionListener(this);
		buttonDeleteLocation.addActionListener(this);
		buttonDeleteStop.addActionListener(this);
		
		panelButtons.add(buttonAddLocation);
		panelButtons.add(buttonAddStop);
		panelButtons.add(buttonDeleteLocation);
		panelButtons.add(buttonDeleteStop);
		
		buttonUpdateAndExit = new JButton("Update and exit");
		buttonUpdateAndExit.addActionListener(this);
		
		cont = getContentPane();
		cont.removeAll();
		
		cont.setLayout(new GridLayout(3, 1));
		cont.add(panelTextFields);
		if(routeKey!=null){
			cont.add(panelButtons);
			Route r = routes.get(routeKey);
			textRouteName.setEditable(false);
			textRouteName.setText(r.getName());
			textDesc.setText(r.getDescription());
		}
		cont.add(buttonUpdateAndExit);
		
		pack();
		setBounds(100, 100, getWidth(), getHeight());
	}
	
	public void addLocation(){
		//dos text con lat y lng y spinner para escoger index de ubicación en la lista
		
		Route r = routes.get(routeKey);
		
		JPanel panel = new JPanel();
		JTextField textLat = new JTextField();
		JTextField textLng = new JTextField();
		JSpinner spinner = new JSpinner();
		
		try{
			spinner.setValue(r.getLatitudes().size());
		}catch(Exception e){
			spinner.setValue(0);
		}
		
		panel.setLayout(new GridLayout(3, 2));
		
		panel.add(new JLabel("Lat:"));
		panel.add(textLat);
		panel.add(new JLabel("Lng:"));
		panel.add(textLng);
		panel.add(new JLabel("Location index:"));
		panel.add(spinner);
		
		int result = JOptionPane.showConfirmDialog(null, panel,
				"Location input", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		
		if(result==JOptionPane.OK_OPTION){
			try{
				int index = Integer.parseInt(""+spinner.getValue());
				Double lat = Double.parseDouble(textLat.getText());
				Double lng = Double.parseDouble(textLng.getText());
				
				r.getLatitudes().add(index, lat);
				r.getLongitudes().add(index, lng);
			}catch(Exception e){
				JOptionPane.showMessageDialog(null,
						"Error adding location, check your input", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	
	public void deleteLocation(){
		//combo con las disponibles
		Route r = routes.get(routeKey);
		LinkedList<Double> lats = r.getLatitudes();
		LinkedList<Double> lngs = r.getLongitudes();
		
		String strings[] = new String[lats.size()];
		int i;
		for(i=0;i<lats.size();i++){
			strings[i] = lats.get(i)+", "+lngs.get(i);
		}
		
		JComboBox<String> combo = new JComboBox<String>(strings);
		
		int result = JOptionPane.showConfirmDialog(null, combo,
				"Delete location", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		
		if(result==JOptionPane.OK_OPTION){
			lats.remove(combo.getSelectedIndex());
			lngs.remove(combo.getSelectedIndex());
		}
		
	}
	
	public void addStop(){
		//combo con las paradas de stops y un spinner para elegir index de ubicación en la lista
		JPanel panel = new JPanel();
		
		Route r = routes.get(routeKey);
		
		String stopKeys[] = stops.keySet().toArray(new String[0]);
		
		String stopStrings[] = new String[stopKeys.length];
		int i;
		for(i=0;i<stopKeys.length;i++){
			stopStrings[i] = stops.get(stopKeys[i]).getName()+", "+stopKeys[i];
		}
		
		JComboBox<String> combo = new JComboBox<String>(stopStrings);
		
		JSpinner spinner = new JSpinner();
		
		try{
			spinner.setValue(r.getStops().size());
		}catch(Exception e){
			spinner.setValue(0);
		}
		
		panel.setLayout(new GridLayout(2, 2));
		
		panel.add(new JLabel("Stop:"));
		panel.add(combo);
		panel.add(new JLabel("Location index:"));
		panel.add(spinner);
		
		int result = JOptionPane.showConfirmDialog(null, panel,
				"Location input", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		
		if(result==JOptionPane.OK_OPTION){
			try{
				int index = Integer.parseInt(""+spinner.getValue());
				
				r.getStops().add(index, stopKeys[combo.getSelectedIndex()]);
			}catch(Exception e){
				JOptionPane.showMessageDialog(null,
						"Error adding stop, check your input", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public void deleteStop(){
		//combo para elegir
		Route r = routes.get(routeKey);
		LinkedList<String> routeStops = r.getStops();
		
		String strings[] = new String[routeStops.size()];
		int i;
		for(i=0;i<routeStops.size();i++){
			strings[i] = stops.get(routeStops.get(i)).getName()+", "+routeStops.get(i);
		}
		
		JComboBox<String> combo = new JComboBox<String>(strings);
		
		int result = JOptionPane.showConfirmDialog(null, combo,
				"Remove stop", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		
		if(result==JOptionPane.OK_OPTION){
			routeStops.remove(combo.getSelectedIndex());
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		Object s = e.getSource();
		if(s.equals(buttonAddLocation)){
			addLocation();
		}
		else if(s.equals(buttonDeleteLocation)){
			deleteLocation();
		}
		else if(s.equals(buttonAddStop)){
			addStop();
		}
		else if(s.equals(buttonDeleteStop)){
			deleteStop();
		}
		else if(s.equals(buttonUpdateAndExit)){
			if(routeKey==null){
				Route r = new Route(textRouteName.getText(), textDesc.getText());
				routes.put(r.getName(), r);
			}
			else{
				Route r = routes.get(routeKey);
				r.setDescription(textDesc.getText());
			}
			setVisible(false);
		}
	}

}
