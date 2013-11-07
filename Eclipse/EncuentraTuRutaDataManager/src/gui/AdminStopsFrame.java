package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.*;

import entity.*;

public class AdminStopsFrame extends JFrame implements ActionListener{

	private HashMap<String, Route> routes;
	private HashMap<String, Stop> stops;
	
	private PrincipalFrame pf;
	
	private String[] stopKeys;
	private JComboBox<String> combo;
	private JPanel panelButtons;
	
	private JButton buttonCreateStop;
	private JButton buttonDeleteStop;
	private JButton buttonEditStop;
	private JButton buttonExit;
	
	private JPanel panelEdit;
	
	private JTextField stopName;
	private JTextField stopAddress;
	private JTextField stopLat;
	private JTextField stopLng;
	
	private Container cont;
	
	public AdminStopsFrame(HashMap<String, Stop> stops, HashMap<String, Route> routes, PrincipalFrame pf){
		super("Admin stops");
		this.routes = routes;
		this.stops = stops;
		this.pf = pf;
		this.setUndecorated(true);
		
		stopKeys = stops.keySet().toArray(new String[0]);
		
		String stopsKeysStringsForComboBox[] = new String[stopKeys.length];
		
		int i;
		for(i=0;i<stopKeys.length;i++){
			stopsKeysStringsForComboBox[i] = stops.get(stopKeys[i]).getName()+", "+stopKeys[i];
		}
		
		combo = new JComboBox<String>(stopsKeysStringsForComboBox);
		
		cont = getContentPane();
		cont.removeAll();
		cont.setLayout(new BorderLayout());
		
		panelButtons = new JPanel();
		panelButtons.setLayout(new GridLayout(4, 1));
		
		buttonCreateStop = new JButton("Create Stop");
		buttonDeleteStop = new JButton("Delete Stop");
		buttonEditStop = new JButton("Edit Stop");
		buttonExit = new JButton("Exit");
		
		buttonCreateStop.addActionListener(this);
		buttonDeleteStop.addActionListener(this);
		buttonEditStop.addActionListener(this);
		buttonExit.addActionListener(this);
		
		panelButtons.add(buttonCreateStop);
		panelButtons.add(buttonEditStop);
		panelButtons.add(buttonDeleteStop);
		panelButtons.add(buttonExit);
		
		cont.add(new JLabel("Stop key:"), BorderLayout.NORTH);
		cont.add(combo, BorderLayout.CENTER);
		cont.add(panelButtons, BorderLayout.EAST);
		
		panelEdit = new JPanel();
		panelEdit.setLayout(new GridLayout(4, 2));
		
		stopName = new JTextField();
		stopAddress = new JTextField();
		stopLat = new JTextField();
		stopLng = new JTextField();
		
		panelEdit.add(new JLabel("Name: "));
		panelEdit.add(stopName);
		panelEdit.add(new JLabel("Address (Key): "));
		panelEdit.add(stopAddress);
		panelEdit.add(new JLabel("Lat: "));
		panelEdit.add(stopLat);
		panelEdit.add(new JLabel("Lng: "));
		panelEdit.add(stopLng);
		
		pack();
		setBounds(100, 100, getWidth(), getHeight());
		
	}
	
	private void createStop(){
		stopName.setText("");
		stopAddress.setText("");
		stopAddress.setEnabled(true);
		stopLat.setText("");
		stopLng.setText("");
		//JOptionPane.showInputDialog(null, panelEdit, "New Stop", JOptionPane.QUESTION_MESSAGE);
		int answer = JOptionPane.showConfirmDialog(null
				, panelEdit, "New Stop", JOptionPane.YES_NO_OPTION);
		if(answer==JOptionPane.OK_OPTION){
			try{
				Stop stop = new Stop(stopName.getText().trim(),
						stopAddress.getText().trim(),
						Double.parseDouble(stopLat.getText().trim()),
						Double.parseDouble(stopLng.getText().trim()));
				stops.put(stopAddress.getText().trim(), stop);
				
				buttonCreateStop.setEnabled(false);
				buttonDeleteStop.setEnabled(false);
				buttonEditStop.setEnabled(false);
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Error while creating stop, check your information", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void editStop(){
		Stop stop = stops.get(stopKeys[combo.getSelectedIndex()]);
		
		stopName.setText(stop.getName());
		stopAddress.setText(stop.getAddress());
		stopAddress.setEnabled(false);
		stopLat.setText(""+stop.getLatitude());
		stopLng.setText(""+stop.getLongitude());
		//JOptionPane.showInputDialog(null, panelEdit, "New Stop", JOptionPane.QUESTION_MESSAGE);
		int answer = JOptionPane.showConfirmDialog(null
				, panelEdit, "New Stop", JOptionPane.YES_NO_OPTION);
		if(answer==JOptionPane.OK_OPTION){
			try{
				stop.setName(stopName.getText().trim());
				stop.setLatitude(Double.parseDouble(stopLat.getText().trim()));
				stop.setlongitude(Double.parseDouble(stopLng.getText().trim()));
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Error while creating stop, check your information", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void deleteStop(){
		if(JOptionPane.showConfirmDialog(null, "Are you sure to delete this stop?",
				"Hold on", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
				==JOptionPane.OK_OPTION){
			String stopKeyToDelete = stopKeys[combo.getSelectedIndex()];
			stops.remove(stopKeyToDelete);
			
			for(String sr:routes.keySet()){
				Route r = routes.get(sr);
				LinkedList<String> stops = r.getStops();
				int i;
				for(i=stops.size()-1;i>=0;i--){
					if(stops.get(i).equals(stopKeyToDelete)){
						stops.remove(i);
					}
				}
			}
			
			buttonCreateStop.setEnabled(false);
			buttonDeleteStop.setEnabled(false);
			buttonEditStop.setEnabled(false);
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object s = e.getSource();
		if(s.equals(buttonCreateStop)){
			createStop();
		}
		else if(s.equals(buttonEditStop)){
			editStop();
		}
		else if(s.equals(buttonDeleteStop)){
			deleteStop();
		}
		else if(s.equals(buttonExit)){
			this.setVisible(false);
			pf.liberateStops();
		}
	}

}
