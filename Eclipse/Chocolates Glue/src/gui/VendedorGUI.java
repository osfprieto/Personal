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

public class VendedorGUI extends JFrame{

	private static final long serialVersionUID = 1L;
	private JLabel empresa; // etiqueta con el nombre de la empresa
	private JComboBox opciones; // Combo box con las opciones de Vendedor
	private String[] listaOpciones={"Seleccione...",
			"Factura Venta","Ver Stock"}; // Lista con las opciones de Vendedor
	private JButton volver = new JButton("Cerrar Sesión");
	public VendedorGUI(){
		super(" Vendedor ");
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
	/*
	 * El método abrir, se encarga de abrir una ventana 
	 * con la vendedorGUI
	 */
	public void abrir(){
		this.setBounds(250, 200, 350, 100);
		this.setVisible(true);
	}
	/*
	 * El método cerrar, se encarga de cerrar la ventana con 
	 * la vendedorGUI
	 */
	public void cerrar(){
		this.setVisible(false);
	}
	public class ActionHandler implements ItemListener, ActionListener{

		public void itemStateChanged(ItemEvent e) {
			if(((String)(opciones.getSelectedItem())).equals(listaOpciones[1])){
				IngresarTest.facturaVentaGUI.actualizar();
				IngresarTest.facturaVentaGUI.setVisible(true);

			}
			if(((String)(opciones.getSelectedItem())).equals(listaOpciones[2])){
				IngresarTest.verProductosGUI.setVisible(true);
			}
		}
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(volver)){
				IngresarTest.frameVendedor.setVisible(false);
				IngresarTest.frameIngreso.limpiar();
				IngresarTest.frameIngreso.setVisible(true);
			}				
		}	
	}
}

