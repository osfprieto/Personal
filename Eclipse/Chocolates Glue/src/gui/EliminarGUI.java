package gui;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import classes.*;

public class EliminarGUI extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4671254269002033621L;
	private JLabel labelCedula;
	private JTextField cedula;
	private JLabel labelNit;
	private JTextField nit;
	private JLabel labelReferencia;
	private JTextField referencia;
	private JPanel panelEmpleado;
	private JPanel panelCliente;
	private JPanel panelProveedor;
	private JPanel panelProductos;
	
	private JButton actionButtonEmp;
	private JButton actionButtonProv;
	private JButton actionButtonClien;
	private JButton actionButtonProd;
	private JButton cancelarEliminarButton;
	
	private AdministradorGUI administradorGui;

	public EliminarGUI(Empleados empleado,AdministradorGUI administradorGui){

		super("Eliminar Empleado");
		labelCedula = new JLabel("Ingrese Cedula del empleado que desea eliminar");
		cedula = new JTextField(15);
		panelEmpleado = new JPanel();
		actionButtonEmp = new JButton("Eliminar!");
		cancelarEliminarButton = new JButton("Cancelar");

		panelEmpleado.add(labelCedula);
		panelEmpleado.add(cedula);
		panelEmpleado.add(actionButtonEmp);
		panelEmpleado.add(cancelarEliminarButton);

		actionButtonEmp.addActionListener(this);
		cancelarEliminarButton.addActionListener(this);
		
		add(panelEmpleado);
		setBounds(150, 150, 350, 350);
		setVisible(true);
		this.administradorGui=administradorGui;
	}

	public EliminarGUI(Cliente cliente,AdministradorGUI administradorGui){
		super("Eliminar Cliente");

		labelCedula = new JLabel("Ingrese Cedula del cliente que desea eliminar");
		cedula = new JTextField(15);
		panelCliente = new JPanel();
		actionButtonClien = new JButton("Eliminar!");
		cancelarEliminarButton = new JButton("Cancelar");

		panelCliente.add(labelCedula);
		panelCliente.add(cedula);
		panelCliente.add(actionButtonClien);
		panelCliente.add(cancelarEliminarButton);

		actionButtonClien.addActionListener(this);
		cancelarEliminarButton.addActionListener(this);
		
		add(panelCliente);
		setBounds(150, 150, 350, 250);
		setVisible(true);
		this.administradorGui=administradorGui;
	}

	public EliminarGUI(Proveedor proveedor,AdministradorGUI administradorGui){
		super("Eliminar Proveedor");

		labelNit = new JLabel("Nit de la Empresa de desea eliminar");
		nit = new JTextField(15);
		panelProveedor = new JPanel();
		actionButtonProv = new JButton("Eliminar!");
		cancelarEliminarButton = new JButton("Cancelar");

		panelProveedor.add(labelNit);
		panelProveedor.add(nit);
		panelProveedor.add(actionButtonProv);
		panelProveedor.add(cancelarEliminarButton);

		actionButtonProv.addActionListener(this);
		cancelarEliminarButton.addActionListener(this);
		
		add(panelProveedor);
		setBounds(150, 150, 350, 350);
		setVisible(true);
		this.administradorGui=administradorGui;
	}

	public EliminarGUI(Productos producto,AdministradorGUI administradorGui){
		super("Eliminar Producto");

		labelReferencia = new JLabel("Referencia");
		referencia = new JTextField(15);
		panelProductos = new JPanel();
		actionButtonProd = new JButton("Eliminar!");
		cancelarEliminarButton = new JButton("Cancelar");

		panelProductos.add(labelReferencia);
		panelProductos.add(referencia);
		panelProductos.add(actionButtonProd);
		panelProductos.add(cancelarEliminarButton);

		actionButtonProd.addActionListener(this);
		cancelarEliminarButton.addActionListener(this);
		
		add(panelProductos);
		setBounds(150, 150, 350, 250);
		setVisible(true);
		this.administradorGui=administradorGui;
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(actionButtonEmp)){
			//Hacer algo para determinar el empleado
			//System.out.println(cedula.getText());
			try{
				Double ced = Double.parseDouble(cedula.getText());
				administradorGui.eliminarEmpleado(ced);
			}
			catch(NumberFormatException exception){
				JOptionPane.showMessageDialog(null, "Usted no ha ingresado una cédula válida", "Entrada No válida", JOptionPane.ERROR_MESSAGE);
			}

		}
		if(e.getSource().equals(actionButtonClien)){
			//Hacer algo para determinar el cleinte
			try{
				double ced = Double.parseDouble(cedula.getText());
				administradorGui.eliminarCliente(ced);
			}
			catch(NumberFormatException exception){
				JOptionPane.showMessageDialog(null, "Usted no ha ingresado una cédula válida", "Entrada No válida", JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource().equals(actionButtonProd)){
			//Hacer algo para determinar el producto
			administradorGui.eliminarProducto(referencia.getText());
		}
		if(e.getSource().equals(actionButtonProv)){
			//Hacer algo para determinar el proveedor
			try{
				Double nitNum = Double.parseDouble(nit.getText());
				administradorGui.eliminarProveedor(nitNum);
			}
			catch(NumberFormatException exception){
				JOptionPane.showMessageDialog(null, "Usted no ha ingresado un NIT válido", "Entrada No válida", JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource().equals(cancelarEliminarButton)){
			this.setVisible(false);
		}
	}

}
