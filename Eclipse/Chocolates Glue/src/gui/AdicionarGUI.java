package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import classes.*;

public class AdicionarGUI extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5229226648658174195L;
	private JButton actionButtonEmp;
	private JButton actionButtonProv;
	private JButton actionButtonClien;
	private JButton actionButtonProd;
	private JButton cancelarAdicionButton;
	private JPanel panelEmpleado = new JPanel();

	private JLabel labelNombre;
	private JTextField nombre = new JTextField("");
	private JLabel labelApellidos;
	private JTextField apellidos = new JTextField("");
	private JLabel labelCedula;
	private JTextField cedula = new JTextField("");
	private JLabel labelSalario;
	private JTextField salario = new JTextField("");
	private JLabel labelCargo;
	private JRadioButton radioVendedor = new JRadioButton();
	private JRadioButton radioComprador = new JRadioButton();
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel labelTelefono;
	private JTextField telefono = new JTextField("");
	private JLabel labelMail;
	private JTextField mail = new JTextField("");
	private JLabel labelNacimiento;
	private JTextField nacimiento = new JTextField("");
	private JLabel labelContrasenia;
	private JTextField contrasenia = new JTextField("");

	private JPanel panelCliente = new JPanel();

	private JPanel panelProveedor = new JPanel();

	private JLabel labelNombreEmpresa;
	private JTextField nombreEmpresa = new JTextField("");
	private JLabel labelNit;
	private JTextField nit = new JTextField("");

	private JPanel panelProductos = new JPanel();

	private JLabel labelReferencia;
	private JTextField referencia = new JTextField("");
	private JLabel labelNombreProducto;
	private JTextField nombreProducto = new JTextField("");
	private JLabel labelValorCompra;
	private JTextField valorCompra = new JTextField("");
	private JLabel labelValorVenta;
	private JTextField valorVenta = new JTextField("");
	private JLabel labelCantidadStock;
	private JTextField cantidadStock = new JTextField("");

	private AdministradorGUI administradorGui;

	public AdicionarGUI(Empleados empleado,AdministradorGUI administradorGui) {
		super("Adicionar Empleado");
		labelNombre = new JLabel("Nombre");
		nombre = new JTextField(15);
		labelApellidos = new JLabel("Apellidos");
		apellidos = new JTextField(15);
		labelCedula = new JLabel("Cedula");
		cedula = new JTextField(15);
		labelSalario = new JLabel("Salario");
		salario = new JTextField(15);
		labelCargo = new JLabel("Cargo:");
		radioVendedor = new JRadioButton("Vendedor");
		radioComprador = new JRadioButton("Comprador");
		buttonGroup.add(radioComprador);
		buttonGroup.add(radioVendedor);
		labelTelefono = new JLabel("Telefono");
		telefono = new JTextField(15);
		labelMail = new JLabel("Correo Electronico");
		mail = new JTextField(15);
		labelNacimiento = new JLabel("Fecha de Nacimiento");
		nacimiento = new JTextField(15);
		labelContrasenia = new JLabel("Contrasenia");
		contrasenia = new JTextField(15);
		panelEmpleado = new JPanel();
		actionButtonEmp = new JButton("Adicionar!");
		cancelarAdicionButton = new JButton("Cancelar");

		panelEmpleado.add(labelNombre);
		panelEmpleado.add(nombre);
		panelEmpleado.add(labelApellidos);
		panelEmpleado.add(apellidos);
		panelEmpleado.add(labelCedula);
		panelEmpleado.add(cedula);
		panelEmpleado.add(labelSalario);
		panelEmpleado.add(salario);
		panelEmpleado.add(labelCargo);
		panelEmpleado.add(radioComprador);
		panelEmpleado.add(radioVendedor);
		panelEmpleado.add(labelTelefono);
		panelEmpleado.add(telefono);
		panelEmpleado.add(labelMail);
		panelEmpleado.add(mail);
		panelEmpleado.add(labelNacimiento);
		panelEmpleado.add(nacimiento);
		panelEmpleado.add(labelContrasenia);
		panelEmpleado.add(contrasenia);
		panelEmpleado.add(actionButtonEmp);
		panelEmpleado.add(cancelarAdicionButton);

		add(panelEmpleado);
		setBounds(150, 150, 350, 350);
		setVisible(true);

		cancelarAdicionButton.addActionListener(this);
		actionButtonEmp.addActionListener(this);
		//actionButtonEmp.setActionCommand(CREAR);
		this.setAdministradorGui(administradorGui);

	}

	public AdicionarGUI(Proveedor proveedor,AdministradorGUI administradorGui){
		super("Adicionar Proveedor");

		labelNombreEmpresa = new JLabel("Nombre de la Empresa");
		nombreEmpresa = new JTextField(15);
		labelNit = new JLabel("NIT de la Empresa");
		nit = new JTextField(15);
		labelTelefono = new JLabel("Numero de Telefono");
		telefono = new JTextField(15);
		panelProveedor = new JPanel();
		actionButtonProv = new JButton("Adicionar");
		cancelarAdicionButton = new JButton("Cancelar");

		panelProveedor.add(labelNombreEmpresa);
		panelProveedor.add(nombreEmpresa);
		panelProveedor.add(labelNit);
		panelProveedor.add(nit);
		panelProveedor.add(labelTelefono);
		panelProveedor.add(telefono);
		panelProveedor.add(actionButtonProv);
		panelProveedor.add(cancelarAdicionButton);

		actionButtonProv.addActionListener(this);
		cancelarAdicionButton.addActionListener(this);
		
		add(panelProveedor);
		setBounds(150, 150, 350, 350);
		setVisible(true);
		this.setAdministradorGui(administradorGui);
	}

	public AdicionarGUI(Cliente cliente,AdministradorGUI administradorGui){
		super("Adicionar Cliente");

		labelNombre = new JLabel("Nombre");
		nombre = new JTextField(15);
		labelApellidos = new JLabel("Apellidos");
		apellidos = new JTextField(15);
		labelCedula = new JLabel("Cedula");
		cedula = new JTextField(15);
		labelTelefono = new JLabel("Telefono");
		telefono = new JTextField(15);
		labelMail = new JLabel("Correo Electronico");
		mail = new JTextField(15);
		panelCliente = new JPanel();
		actionButtonClien = new JButton("Adicionar!");
		cancelarAdicionButton = new JButton("Cancelar");

		panelCliente.add(labelNombre);
		panelCliente.add(nombre);
		panelCliente.add(labelApellidos);
		panelCliente.add(apellidos);
		panelCliente.add(labelCedula);
		panelCliente.add(cedula);
		panelCliente.add(labelTelefono);
		panelCliente.add(telefono);
		panelCliente.add(labelMail);
		panelCliente.add(mail);
		panelCliente.add(actionButtonClien);
		panelCliente.add(cancelarAdicionButton);

		actionButtonClien.addActionListener(this);
		cancelarAdicionButton.addActionListener(this);
		add(panelCliente);
		setBounds(150, 150, 350, 250);
		setVisible(true);
		this.setAdministradorGui(administradorGui);
	}

	public AdicionarGUI(Productos producto,AdministradorGUI administradorGui){
		super("Adicionar Producto");

		labelReferencia = new JLabel("Referencia");
		referencia = new JTextField(15);
		labelNombreProducto = new JLabel("Nombre del producto");
		nombreProducto = new JTextField(15);
		labelValorCompra = new JLabel("Valor Compra");
		valorCompra = new JTextField(15);
		labelValorVenta= new JLabel("Valor Venta");
		valorVenta = new JTextField(15);
		labelCantidadStock = new JLabel("Cantidad en Stock");
		cantidadStock = new JTextField(15);
		panelProductos = new JPanel();
		actionButtonProd = new JButton("Adicionar");
		cancelarAdicionButton = new JButton("Cancelar");
		
		panelProductos.setLayout(new FlowLayout());//orderna con lo layouts para que se vea bonito

		panelProductos.add(labelReferencia);
		panelProductos.add(referencia);
		panelProductos.add(labelNombreProducto);
		panelProductos.add(nombreProducto);
		panelProductos.add(labelValorCompra);
		panelProductos.add(valorCompra);
		panelProductos.add(labelValorVenta);
		panelProductos.add(valorVenta);
		panelProductos.add(labelCantidadStock);
		panelProductos.add(cantidadStock);
		panelProductos.add(actionButtonProd);
		panelProductos.add(cancelarAdicionButton);

		actionButtonProd.addActionListener(this);
		cancelarAdicionButton.addActionListener(this);
		add(panelProductos);
		setBounds(150, 150, 350, 250);
		setVisible(true);

		actionButtonProd.addActionListener(this);
		//actionButtonProd.setActionCommand(CREAR);
		this.setAdministradorGui(administradorGui);
	}

	public void limpiar(){
		nombre.setText("");
		apellidos.setText("");
		cedula.setText("");
		salario.setText("");
		radioComprador.setSelected(false);
		radioVendedor.setSelected(false);
		telefono.setText("");
		mail.setText("");
		nacimiento.setText("");
		contrasenia.setText("");
		nombreEmpresa.setText("");
		nit.setText("");
		referencia.setText("");
		nombreProducto.setText("");
		valorCompra.setText("");
		valorVenta.setText("");
		cantidadStock.setText("");
		
		panelEmpleado.updateUI();
		panelProductos.updateUI();
		panelCliente.updateUI();
		panelProveedor.updateUI();
		
		cancelarAdicionButton.doClick();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(cancelarAdicionButton)){
			this.setVisible(false);
		}
		if(e.getSource().equals(actionButtonEmp)){
			if(radioComprador.isSelected()){
				try{
						Comprador c1 = new Comprador(nombre.getText()+" "+apellidos.getText()
						,Double.parseDouble(cedula.getText()),Double.parseDouble(salario.getText()),
						Double.parseDouble(telefono.getText()),
						mail.getText(),nacimiento.getText(), contrasenia.getText());
						IngresarTest.frameAdministrador.agregarEmpleado(c1);
			}
				catch(NumberFormatException exeption){
					JOptionPane.showMessageDialog(null, "Usted ha ingresado algún campo no válido",
							"Entrada No válida", JOptionPane.ERROR_MESSAGE);
					}
			}
			else if(radioVendedor.isSelected()){
				try{
						Vendedor v1 = new Vendedor(nombre.getText()+" "+apellidos.getText(), Double.parseDouble(cedula.getText()),
						Double.parseDouble(salario.getText()), Double.parseDouble(telefono.getText()),
						mail.getText(), nacimiento.getText(),contrasenia.getText());
						IngresarTest.frameAdministrador.agregarEmpleado(v1);
				}
				catch(NumberFormatException exeption){
					JOptionPane.showMessageDialog(null, "Usted ha ingresado algún campo no válido",
							"Entrada No válida", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		if(e.getSource().equals(actionButtonClien)){
			try{
			Cliente c1 = new Cliente(nombre.getText(), Integer.parseInt(telefono.getText()),
					mail.getText(), Integer.parseInt(cedula.getText()));
			IngresarTest.frameAdministrador.agregarCliente(c1);
			}
			catch(NumberFormatException exeption){
				JOptionPane.showMessageDialog(null, "Usted ha ingresado algún campo no válido",
						"Entrada No válida", JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource().equals(actionButtonProd)){
			try{
			Productos p1 = new Productos(referencia.getText(), nombreProducto.getText(),Integer.parseInt(cantidadStock.getText()),
				Double.parseDouble(valorVenta.getText()), Double.parseDouble(valorCompra.getText()));
				IngresarTest.frameAdministrador.agregarProducto(p1);
			//limpiar();
			}
			catch(NumberFormatException exeption){
				JOptionPane.showMessageDialog(null, "Usted ha ingresado algún campo no válido",
						"Entrada No válida", JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource().equals(actionButtonProv)){
			try {
			Proveedor p1 = new Proveedor(nombreEmpresa.getText(), Double.parseDouble(nit.getText()),
					Integer.parseInt(telefono.getText()));
			IngresarTest.frameAdministrador.agregarProveedor(p1);
			}
			catch(NumberFormatException exeption){
				JOptionPane.showMessageDialog(null, "Usted ha ingresado algún campo no válido",
						"Entrada No válida", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void setAdministradorGui(AdministradorGUI administradorGui) {
		this.administradorGui = administradorGui;
	}

	public AdministradorGUI getAdministradorGui() {
		return administradorGui;
	}
}
