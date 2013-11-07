package gui;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import classes.Cliente;
import classes.Empleados;
import classes.Productos;
import classes.Proveedor;

public class AdministradorGUI extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private static final String HANDLER_EJECUCION="handlerEjecucion";
	private static final String HANDLER_SELECCION="handlerSeleccion";

	private JComboBox seleccion; // ComboBox de selección	
	private String[] listaSeleccion={"Seleccione...","Empleados","Clientes",
			"Proveedores","Productos","Registro de Ventas"};// Listado de Seleccion 
	private JComboBox ejecucion; // ComboBox de acción según la primera elección
	private String[] listaEjecucion={"Seleccione...","Adicionar","Modifica","Eliminar","Ver"};//Listade de Ejecuciom
	private AdicionarGUI adicionar;
	private JLabel labelEmpresa; // etiqueta con el nombre de la empresa
	
	private JButton cerrarSesion;

	public AdministradorGUI(){
		super(" Administrador ");
		setLayout(new FlowLayout());
		labelEmpresa=new JLabel("        Chocolates Glue S.A.        ");
		labelEmpresa.setFont(Font.decode(Font.MONOSPACED));
		seleccion=new JComboBox(listaSeleccion);

		ejecucion=new JComboBox(listaEjecucion);
		ejecucion.setVisible(true);
		add(labelEmpresa);
		add(seleccion);
		add(ejecucion);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		cerrarSesion = new JButton("Cerrar Sesión");
		cerrarSesion.addActionListener(this);
		
		add(cerrarSesion);
		
		seleccion.addActionListener(this);
		seleccion.setActionCommand(HANDLER_SELECCION);
		ejecucion.addActionListener(this);
		ejecucion.setActionCommand(HANDLER_EJECUCION);
	}

	public JComboBox getSeleccion() {
		return seleccion;
	}
	public void abrir(){
		this.ejecucion.setSelectedIndex(0);
		this.seleccion.setSelectedIndex(0);
		this.setBounds(100, 200, 300, 150);
		this.setVisible(true);
	}
	
	public void agregarEmpleado(Empleados empleado){
		IngresarTest.empresa.getEmpleados().put(empleado.getCedulaCiudadania(), empleado);
		JOptionPane.showMessageDialog(null,
				IngresarTest.empresa.getObjetoEmpleado(empleado.getCedulaCiudadania()).toString()
				+"\n Ha sido agregado exitosamente.","Exito",JOptionPane.INFORMATION_MESSAGE);
				adicionar.limpiar();
	}
	public void eliminarEmpleado(Double cedula){
		JTextArea infoEmp = new JTextArea();
		Empleados emp = IngresarTest.empresa.getObjetoEmpleado(cedula);
		if(emp==null){
			JOptionPane.showMessageDialog(null, "El empleado no existe", "Empleado no encontrado", 
					JOptionPane.ERROR_MESSAGE);
		}
		else{
			infoEmp.append("Desea elinar a este Empleado?\n");
			infoEmp.append(emp.toString());
			infoEmp.setEditable(false);
			int eliminar = JOptionPane.showConfirmDialog(null, infoEmp);
			if(eliminar == JOptionPane.YES_OPTION){
				IngresarTest.empresa.getEmpleados().remove(cedula);
			}
		}
	}
	public void agregarCliente(Cliente cliente){
		IngresarTest.empresa.getClientes().put(cliente.getCédula(), cliente);
		JOptionPane.showMessageDialog(null,
				IngresarTest.empresa.getObjetoCliente(cliente.getCédula()).toString()+
				"\n Ha sido agregado exitosamente.", "Exito",JOptionPane.INFORMATION_MESSAGE);
		adicionar.limpiar();
	}
	public void eliminarCliente(Double cedula){
		try{
			JTextArea infoCliente = new JTextArea();
			Cliente cliente = IngresarTest.empresa.getObjetoCliente(cedula);
			if(cliente == null){
				JOptionPane.showMessageDialog(null, "El Cliente no existe", "Cliente no encontrado", JOptionPane.ERROR_MESSAGE);
			}
			else{
				infoCliente.append("Desea elinar a este Cliente?\n");
				infoCliente.append(cliente.toString());
				infoCliente.setEditable(false);
				int eliminar = JOptionPane.showConfirmDialog(null, infoCliente);
				if(eliminar == JOptionPane.YES_OPTION){
					IngresarTest.empresa.getClientes().remove(cedula);
				}
			}
		}
		catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Usted no ha ingresado una cédula válida", 
					"Entrada No válida", JOptionPane.ERROR_MESSAGE);
		}
	}
	public void agregarProducto(Productos producto){
		IngresarTest.empresa.agregarProducto(producto);
		JOptionPane.showMessageDialog(null, IngresarTest.empresa.getProducto(producto.getReferencia()).toString()+"\n Ha sido agregado exitosamente.",
				"Exito",JOptionPane.INFORMATION_MESSAGE);
		adicionar.limpiar();
		IngresarTest.verProductosGUI.actualizar();
		IngresarTest.facturaCompraGUI.actualizar();
		IngresarTest.facturaVentaGUI.actualizar();
	}
	public void eliminarProducto (String referencia){
		JTextArea infoProd = new JTextArea();
		Productos prod = IngresarTest.empresa.getProducto(referencia);
		if(prod == null){
			JOptionPane.showMessageDialog(null, "El Producto no existe", "Producto no encontrado",
					JOptionPane.ERROR_MESSAGE);
		}
		else{
			infoProd.append("Desea elinar a este Producto?\n");
			infoProd.append(prod.toString());
			infoProd.setEditable(false);
			int eliminar = JOptionPane.showConfirmDialog(null, infoProd);
			if(eliminar == JOptionPane.YES_OPTION){
				IngresarTest.empresa.getProductos().remove(referencia);
			}
		}
	}
	public void agregarProveedor(Proveedor proveedor){
		IngresarTest.empresa.getProveedores().put(proveedor.getNit(), proveedor);
		JOptionPane.showMessageDialog(null, IngresarTest.empresa.getObjetoProveedor(proveedor.getNit()).toString()+"\n Ha sido agregado exitosamente.",
				"Exito",JOptionPane.INFORMATION_MESSAGE);
		adicionar.limpiar();
		
	}
	public void eliminarProveedor(double nit){
		try{
			JTextArea infoProv = new JTextArea();
			Proveedor prov = IngresarTest.empresa.getObjetoProveedor(nit);
			if(prov == null){
				JOptionPane.showMessageDialog(null, "El Proveedor no existe", "Proveedor no encontrado", JOptionPane.ERROR_MESSAGE);
			}
			else{
				infoProv.append("Desea elinar a este Proveedor?\n");
				infoProv.append(prov.toString());
				infoProv.setEditable(false);
				int eliminar = JOptionPane.showConfirmDialog(null, infoProv);
				if(eliminar == JOptionPane.YES_OPTION){
					IngresarTest.empresa.getProveedores().remove(nit);
				}
			}
		}
		catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Usted no ha ingresado un NIT válido", "Entrada No válida", JOptionPane.ERROR_MESSAGE);
		}
	}
	public void actionPerformed(ActionEvent e) {
		String persona = (String)seleccion.getSelectedItem();
		String ejec = (String)ejecucion.getSelectedItem();
		String msg=e.getActionCommand();

		if(e.getSource().equals(cerrarSesion)){
			this.setVisible(false);
			IngresarTest.frameIngreso.limpiar();
			IngresarTest.frameIngreso.setVisible(true);
		}
		
		if(msg.equals(HANDLER_EJECUCION)){
			if(persona.equals(listaSeleccion[1])){
				Empleados empleado = null;
				if(ejec.equals(listaEjecucion[1])){
					adicionar = new AdicionarGUI(empleado,this);
				}
				if(ejec.equals(listaEjecucion[2])){
					IngresarTest.frameModificar.setVisible(true);
					IngresarTest.frameModificar.setMode(0);
				}
				if(ejec.equals(listaEjecucion[3])){
					new EliminarGUI(empleado, this);
				}
				if(ejec.equals(listaEjecucion[4])){
					IngresarTest.verEmpleadosGUI.setVisible(true);
				}
			}

			if(persona.equals(listaSeleccion[2])){
				Cliente cliente = null;
				if(ejec.equals(listaEjecucion[1])){
					adicionar = new AdicionarGUI(cliente,this);
				}
				if(ejec.equals(listaEjecucion[2])){
					IngresarTest.frameModificar.setVisible(true);
					IngresarTest.frameModificar.setMode(1);
				}
				if(ejec.equals(listaEjecucion[3])){
					new EliminarGUI(cliente,this);
				}
				if(ejec.equals(listaEjecucion[4])){
					IngresarTest.verClientesGUI.setVisible(true);
				}
			}

			if(persona.equals(listaSeleccion[3])){
				Proveedor proveedor = null;
				if(ejec.equals(listaEjecucion[1])){
					adicionar = new AdicionarGUI(proveedor,this);
				}
				if(ejec.equals(listaEjecucion[2])){
					IngresarTest.frameModificar.setVisible(true);
					IngresarTest.frameModificar.setMode(2);
				}
				if(ejec.equals(listaEjecucion[3])){
					new EliminarGUI(proveedor,this);
				}
				if(ejec.equals(listaEjecucion[4])){
					IngresarTest.verProveedorGUI.setVisible(true);
				}
			}

			if(persona.equals(listaSeleccion[4])){
				Productos producto = null;
				if(ejec.equals(listaEjecucion[1])){
					adicionar = new AdicionarGUI(producto,this);
				}
				if(ejec.equals(listaEjecucion[2])){
					IngresarTest.frameModificar.setVisible(true);
					IngresarTest.frameModificar.setMode(3);
				}
				if(ejec.equals(listaEjecucion[3])){
					new EliminarGUI(producto,this);
				}
				if(ejec.equals(listaEjecucion[4])){
					IngresarTest.verProductosGUI.setVisible(true);
				}
			}
			if(persona.equals(listaSeleccion[5])){
				
				if(ejec.equals(listaEjecucion[1])){
					JOptionPane.showMessageDialog(null, "Productos solo puede ser visto",
							"Acceso denegado", JOptionPane.INFORMATION_MESSAGE);
				}
				if(ejec.equals(listaEjecucion[2])){
					JOptionPane.showMessageDialog(null, "Productos solo puede ser visto",
							"Acceso denegado", JOptionPane.INFORMATION_MESSAGE);
				}
				if(ejec.equals(listaEjecucion[3])){
					JOptionPane.showMessageDialog(null, "Productos solo puede ser visto",
							"Acceso denegado", JOptionPane.INFORMATION_MESSAGE);
				}
				if(ejec.equals(listaEjecucion[4])){
					IngresarTest.frameGenerarRegistro.setVisible(true);
				}
			}
		}
	}
}



