package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
import classes.*;


public class VerProductosGUI extends JFrame{
/**
	 * 
	 */
	private static final long serialVersionUID = -3044285192201977870L;
	/*
 * La clase en la que va a estar el main, como todo ya estará instanciado, se debe tener también una
 * variable estática para saber quién es el empleado que acutalmente está usando el panel para poder
 * ver a qué frame se es enviado cuando se presiona al botón volver*/
	private JScrollPane scrollPane;
	private JPanel panel;
	private Container cont;
	private JButton volver;
	private ButtonHandler handler;

	public VerProductosGUI(){
		super( "Ver Productos en Stock" );
		
		handler = new ButtonHandler();
		
		cont = this.getContentPane();
		cont.removeAll();
		cont.setLayout(new BorderLayout());
		
		HashMap<String, Productos> productos = IngresarTest.empresa.getProductos();
		Collection<Productos> produc = productos.values();
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(produc.size()+1, 5));
		panel.add(new JLabel("REFERENCIA"));
		panel.add(new JLabel("NOMBRE"));
		panel.add(new JLabel("PECIO COMPRA"));
		panel.add(new JLabel("PRECIO VENTA"));
		panel.add(new JLabel("CANTIDAD"));
		for(Iterator<Productos> iterador = produc.iterator(); iterador.hasNext();){
			Productos temp = iterador.next();
			panel.add(new JLabel(temp.getReferencia()));
			panel.add(new JLabel(temp.getNombre()));
			panel.add(new JLabel(""+temp.getValorCompra()));
			panel.add(new JLabel(""+temp.getValorVenta()));
			panel.add(new JLabel(""+temp.getCantidadStock()));
		}
		scrollPane = new JScrollPane( panel );
		this.add( scrollPane, BorderLayout.CENTER );
		cont.add(panel, BorderLayout.CENTER);
		
		volver = new JButton("Volver");
		volver.addActionListener(handler);
		cont.add(volver, BorderLayout.SOUTH);
		
		this.setSize(500, 100*produc.size()+20);
	}
	public void actualizar(){
		cont = this.getContentPane();
		cont.removeAll();
		cont.setLayout(new BorderLayout());
		
		HashMap<String, Productos> productos = IngresarTest.empresa.getProductos();
		Collection<Productos> produc = productos.values();
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(produc.size()+1, 5));
		panel.add(new JLabel("REFERENCIA"));
		panel.add(new JLabel("NOMBRE"));
		panel.add(new JLabel("PECIO COMPRA"));
		panel.add(new JLabel("PRECIO VENTA"));
		panel.add(new JLabel("CANTIDAD"));
		for(Iterator<Productos> iterador = produc.iterator(); iterador.hasNext();){
			Productos temp = iterador.next();
			panel.add(new JLabel(temp.getReferencia()));
			panel.add(new JLabel(temp.getNombre()));
			panel.add(new JLabel(""+temp.getValorCompra()));
			panel.add(new JLabel(""+temp.getValorVenta()));
			panel.add(new JLabel(""+temp.getCantidadStock()));
		}
		scrollPane = new JScrollPane( panel );
		this.add( scrollPane, BorderLayout.CENTER );
		cont.add(panel, BorderLayout.CENTER);
		
		volver = new JButton("Volver");
		volver.addActionListener(handler);
		cont.add(volver, BorderLayout.SOUTH);
		
		this.setSize(500, 100*produc.size()+20);
	}
	private class ButtonHandler implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == volver){
				/*if(IngresarTest.empleadoAtual.getClass().getName().equals("classes.Administrador")){
					IngresarTest.verProductosGUI.setVisible(false);
					IngresarTest.frameAdministrador.setVisible(true);
				}
				else if(IngresarTest.empleadoAtual.getClass().getName().equals("classes.Comprador")){
					IngresarTest.verProductosGUI.setVisible(false);
					IngresarTest.frameComprador.setVisible(true);
				}
				else if(IngresarTest.empleadoAtual.getClass().getName().equals("classes.Vendedor")){
					IngresarTest.verProductosGUI.setVisible(false);
					IngresarTest.frameVendedor.setVisible(true);
				}*/
				IngresarTest.verProductosGUI.setVisible(false);
			}
		}
		
	}
}