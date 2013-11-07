package gui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import classes.Comprador;
import classes.FacturaCompra;
import classes.Productos;
import classes.Proveedor;

public class FacturaCompraGUI extends JFrame implements ActionListener{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -521645782628906735L;
	private ArrayList<Productos> productos = new ArrayList<Productos>();
	private FacturaCompra fac;
	
	private JLabel labelNombreProveedor = new JLabel("Nombre Proveedor");
	private JTextField nombreProveedor = new JTextField(15);
	private JLabel labelNitProveedor = new JLabel("NIT Proveedor");
	private JTextField nitProveedor = new JTextField(15);
	private JLabel labelCantidad = new JLabel("Cantidad");
	private JTextField cantidad = new JTextField(5);
	
	private JComboBox listaProductos = new JComboBox();
	private JButton cancelar = new JButton("Cancelar");
	private JButton agregarProductos = new JButton("Agregar Producto");
	private JButton generarFactura = new JButton("Generar Factura");
	private Set<String> referencias =  IngresarTest.empresa.getProductos().keySet();
	private String[] setReferencias;
	private Container cont;
	
	public FacturaCompraGUI(){
		cont = this.getContentPane();
		cont.removeAll();
		cont.setLayout(new FlowLayout());
		cont.add(labelNombreProveedor);
		cont.add(nombreProveedor);
		cont.add(labelNitProveedor);
		cont.add(nitProveedor);
		listaProductos.setMaximumRowCount(5);
		Object[] ref = new Object[referencias.size()];
		setReferencias = new String[referencias.size()];
		ref = referencias.toArray();
		for(int i=0; i<referencias.size();i++){
			setReferencias[i] = ref[i].toString();
		}
		listaProductos = new JComboBox(setReferencias);
		cont.add(listaProductos);
		cont.add(labelCantidad);
		cont.add(cantidad);
		cont.add(cancelar);
		cont.add(agregarProductos);
		cont.add(generarFactura);
		
		this.setContentPane(cont);
		this.setBounds(400, 400, 270, 215);
		
		cancelar.addActionListener(this);
		agregarProductos.addActionListener(this);
		generarFactura.addActionListener(this);
	}
	public void actualizar(){
		cont = this.getContentPane();
		cont.removeAll();
		cont.setLayout(new FlowLayout());
		cont.add(labelNombreProveedor);
		cont.add(nombreProveedor);
		cont.add(labelNitProveedor);
		cont.add(nitProveedor);
		listaProductos.setMaximumRowCount(5);
		Object[] ref = new Object[referencias.size()];
		setReferencias = new String[referencias.size()];
		ref = referencias.toArray();
		for(int i=0; i<referencias.size();i++){
			setReferencias[i] = ref[i].toString();
		}
		listaProductos = new JComboBox(setReferencias);
		cont.add(listaProductos);
		cont.add(labelCantidad);
		cont.add(cantidad);
		cont.add(cancelar);
		cont.add(agregarProductos);
		cont.add(generarFactura);
		this.setContentPane(cont);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		
		if(e.getSource().equals(cancelar)){
			this.setVisible(false);
		}
		if(e.getSource().equals(generarFactura)){
			try{
				Proveedor proveedor = new Proveedor(nombreProveedor.getText(), null, Double.parseDouble(nitProveedor.getText()), 0);
				fac = (FacturaCompra) ((Comprador)(IngresarTest.empleadoAtual)).trabajar(IngresarTest.empresa, productos, proveedor);
				if(fac != null){
					JOptionPane.showMessageDialog(null, "Su factura ha sido generada"+fac.toString(), "Venta completa", JOptionPane.INFORMATION_MESSAGE);
				}
				cancelar.doClick();
			}
			catch(NumberFormatException exception){
				JOptionPane.showMessageDialog(null, "El número del Nit del Proveedor es erroneo", "Nit erroneo", JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource().equals(agregarProductos)){
			try{
				Productos prodTemp = IngresarTest.empresa.getProducto(setReferencias[listaProductos.getSelectedIndex()]);
				prodTemp.setCantidadStock(Integer.parseInt(cantidad.getText()));
				productos.add(prodTemp);
			}
			catch(NumberFormatException exception){
				JOptionPane.showMessageDialog(null, "Ha ingresado un número de cantidad no válido", "Entrada no válida", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		
	}

}
