package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Set;

import entity.*;

import javax.swing.*;

public class AdminRoutesFrame extends JFrame implements ActionListener{

	private HashMap<String, Route> routes;
	private HashMap<String, Stop> stops;
	
	private PrincipalFrame pf;
	
	private JPanel panelButton;
	
	private JButton buttonCreateRoute;
	private JButton buttonEditRoute;
	private JButton buttonDeleteRoute;
	private JButton buttonExit;
	
	private Container cont;
	
	private String[] routeKeys;
	
	private JComboBox<String> combo;
	
	public AdminRoutesFrame(HashMap<String, Stop> stops, HashMap<String, Route> routes, PrincipalFrame pf){
		this.routes = routes;
		this.stops = stops;
		this.pf = pf;
		this.setUndecorated(true);
		routeKeys = routes.keySet().toArray(new String[0]);
		
		cont = getContentPane();
		cont.removeAll();
		cont.setLayout(new BorderLayout());
		
		panelButton = new JPanel();
		panelButton.setLayout(new GridLayout(4, 1));
		
		buttonCreateRoute = new JButton("Create route");
		buttonEditRoute = new JButton("Edit route");
		buttonDeleteRoute = new JButton("Delete route");
		buttonExit = new JButton("Exit");
		
		buttonCreateRoute.addActionListener(this);
		buttonEditRoute.addActionListener(this);
		buttonDeleteRoute.addActionListener(this);
		buttonExit.addActionListener(this);
		
		panelButton.add(buttonCreateRoute);
		panelButton.add(buttonEditRoute);
		panelButton.add(buttonDeleteRoute);
		panelButton.add(buttonExit);
		
		combo = new JComboBox<String>(routeKeys);
		
		cont.add(new JLabel("Route key:"), BorderLayout.NORTH);
		cont.add(combo, BorderLayout.CENTER);
		cont.add(panelButton, BorderLayout.EAST);
		pack();
		setBounds(100, 100, getWidth(), getHeight());
	}
	
	public void createRoute(){
		AdminRouteFrame f = new AdminRouteFrame(stops, routes,
				this, null);
		f.setVisible(true);
		buttonCreateRoute.setEnabled(false);
		buttonEditRoute.setEnabled(false);
		buttonDeleteRoute.setEnabled(false);
	}
	
	public void editRoute(){
		AdminRouteFrame f = new AdminRouteFrame(stops, routes,
				this, routeKeys[combo.getSelectedIndex()]);
		f.setVisible(true);
	}
	
	public void deleteRoute(){
		if(JOptionPane.showConfirmDialog(null,
				"Are you sure to delete this route?",
				"Hold on", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE)==JOptionPane.OK_OPTION){
			String routeKeyToDelete = routeKeys[combo.getSelectedIndex()];
			routes.remove(routeKeyToDelete);
			buttonCreateRoute.setEnabled(false);
			buttonEditRoute.setEnabled(false);
			buttonDeleteRoute.setEnabled(false);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object s = e.getSource();
		if(s.equals(buttonCreateRoute)){
			createRoute();
		}
		else if(s.equals(buttonEditRoute)){
			editRoute();
		}
		else if(s.equals(buttonDeleteRoute)){
			deleteRoute();
		}
		else if(s.equals(buttonExit)){
			pf.liberateRoutes();
			setVisible(false);
		}
	}

}
