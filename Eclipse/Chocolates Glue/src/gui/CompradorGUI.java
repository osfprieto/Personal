package gui;



import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CompradorGUI extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JLabel empresa; // etiqueta con el nombre de la empresa
	private JComboBox opciones; // Combo box con las opciones de Comprador
	private String[] listaOpciones={"Seleccione...",
			"Factura Compra","Ver Stock"}; // Lista con las opciones de Comprador
	private JButton volver= new JButton("Cerrar Sesion");
	public CompradorGUI(){
		super(" Comprador ");
		setLayout(new FlowLayout());
		empresa=new JLabel("        Chocolates Glue S.A.        ");
		empresa.setFont(Font.decode(Font.MONOSPACED));
		opciones=new JComboBox(listaOpciones);
		add(empresa);
		add(opciones);
		add(volver);
		ActionHandler clickHandler=new ActionHandler();
		opciones.addItemListener(clickHandler);
		volver.addActionListener(clickHandler);
		
	
	}
	public class ActionHandler implements ItemListener,ActionListener{

		public void itemStateChanged(ItemEvent e) {
			if(((String)(opciones.getSelectedItem())).equals(listaOpciones[1])){
				IngresarTest.facturaCompraGUI.actualizar();
				IngresarTest.facturaCompraGUI.setVisible(true);
			}
			if(((String)(opciones.getSelectedItem())).equals(listaOpciones[2])){
				IngresarTest.verProductosGUI.setVisible(true);
			}
		}
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(volver)){
				IngresarTest.frameComprador.setVisible(false);
				IngresarTest.frameIngreso.limpiar();
				IngresarTest.frameIngreso.setVisible(true);
			}
		}	
		
	}
	/*
	 * El método abrir, se encarga de abrir una ventana con la
	 * compradorGUI
	 */
	public void abrir(){
		this.setBounds(250, 200, 350, 100);
		this.setVisible(true);
	}
	/*
	 * El método cerrar, se encargar de cerrar la venta con la
	 * compradorGUI
	 */
	public void cerrar(){
		this.setVisible(false);
	}
}
