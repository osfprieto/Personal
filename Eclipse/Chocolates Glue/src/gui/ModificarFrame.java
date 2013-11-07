package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ModificarFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private JLabel etiquetaNombre; // Etiqueta acompañante del campo de texto nombre
	private JLabel etiquetaTelefono; // Etiqueta acompañante del campo de texto teléfono
	private JLabel etiquetaCorreo; // Etiqueta acompañanante del campo de texto correo
	private JLabel etiquetaSalario; // Etiqueta acompañante de campo de texto salario
	private JLabel etiquetaCantidadStock; // Etiqueta acompañante del campo de texto cantidadStock
	private JLabel etiquetaValorVenta; //Etiqueta acompañante del campo de texto valorVenta
	private JLabel etiquetaValorCompra; //Etiqueta acompañante del campo de texto valorCompra
	private JLabel etiquetaReferencia; //Etiqueta acompañante del campo de texto Referencia
	private JLabel etiquetaCedulaEmpleado; // Etiqueta para la cédula del empleado
	private JLabel etiquetaCedula; // etiqueta que acompañana el campo de texto para ingresar la cédula
	private JLabel etiquetaNIT; // etiqueta que acompaña el campo de texto para ingresar el NIT
	private JTextField nombre; // campo de texto con el nombre
	private JTextField telefono; // campo de texto para el telefono
	private JTextField correo; // campo de texto para el correo
	private JTextField salario; // campo de texto para el salario
	private JTextField cantidadStock; // campo de texto para la cantidad en Stock
	private JTextField valorVenta; // campo de texto para el valor de venta
	private JTextField valorCompra; // campo de texto para el valor de compra
	private JTextField referencia; // campo de texto para la referencia del producto
	private JTextField cedulaEmpleado; // campo de texto para la cedula del empleado
	private JTextField cedula; // campo de texto para ingresar la cédula
	private JTextField nit; // campo de texto para ingresar el NIT
	private JButton[] confirmar; // Arreglo de botonespara confirmar el ingreso de la llave para buscar
	private JButton[] cancelar; // Arreglo de botones para salir de la venta y devolverse al menu del administrador
	private JButton[] guardar; // Arreglo de botones para guardar los datos
	private JPanel panelClientes; // Panel con los datos necesarios para un Cliente
	private JPanel panelProveedores; // Panel con los datos necesarios para un Proveedor
	private JPanel panelEmpleados; // Panel con los datos necesarios para un Empleado
	private JPanel panelProductos; // Panel con los datos necesarios para un Producto
	
	
	public ModificarFrame(){
		super("Modificar un Registro");
		setLayout(new FlowLayout());
		// Instanciación de los componentes de la GUI
		this.etiquetaCantidadStock=new JLabel("Cantidad en Stock: ");
		this.etiquetaCorreo=new JLabel("Correo: ");
		this.etiquetaCedula=new JLabel("Cédula: ");
		this.etiquetaNIT=new JLabel("NIT: ");
		this.etiquetaNombre=new JLabel("Nombre: ");
		this.etiquetaSalario=new JLabel("Salario: ");
		this.etiquetaTelefono=new JLabel("Telefono: ");
		this.etiquetaValorCompra=new JLabel("Valor Compra: ");
		this.etiquetaValorVenta=new JLabel("Valor Venta: ");
		this.etiquetaReferencia=new JLabel("Referencia: ");
		this.etiquetaCedulaEmpleado=new JLabel("Cédula: ");
		this.cedulaEmpleado=new JTextField();
		this.referencia=new JTextField();
		this.cantidadStock=new JTextField();
		this.correo=new JTextField();
		this.cedula=new JTextField();
		this.nit=new JTextField();
		this.nombre=new JTextField();
		this.salario=new JTextField();
		this.telefono=new JTextField();
		this.valorCompra=new JTextField();
		this.valorVenta=new JTextField();
		this.panelClientes=new JPanel();
		this.panelEmpleados=new JPanel();
		this.panelProveedores=new JPanel();
		this.panelProductos=new JPanel();
		this.cancelar=new JButton[4];
		this.confirmar=new JButton[4];
		this.guardar= new JButton[4];
		ButtonHandler handler=new ButtonHandler();
		confirmar[0]=new JButton("Modificar Proveedor");
		confirmar[1]=new JButton("Modificar Empleado");
		confirmar[2]=new JButton("Modificar Cliente");
		confirmar[3]=new JButton("Modificar Producto");
		for(int i=0;i<4;i++){
			confirmar[i].setBackground(Color.green);
			confirmar[i].addActionListener(handler);
		}
		
		for(int i=0;i<4;i++){
			cancelar[i]=new JButton("Cancelar");
			cancelar[i].setBackground(Color.red);
			cancelar[i].addActionListener(handler);		
		}
		for(int i=0;i<4;i++){
			guardar[i]=new JButton("Guardar");
			guardar[i].setBackground(Color.gray);
			guardar[i].addActionListener(handler);
		}
		referencia.setColumns(12);
		cedula.setColumns(12);
		nit.setColumns(12);
		correo.setColumns(15);
		nombre.setColumns(12);
		salario.setColumns(10);
		telefono.setColumns(10);
		valorCompra.setColumns(12);
		valorVenta.setColumns(12);	
		cedulaEmpleado.setColumns(12);
		cantidadStock.setColumns(8);
		// Primera definición de las JPanels
		
		this.panelProveedores.add(this.etiquetaNIT);
		this.panelProveedores.add(this.nit);		
		this.panelProveedores.add(this.confirmar[0]);
		this.panelProveedores.add(this.cancelar[0]);
		this.panelEmpleados.add(this.etiquetaCedulaEmpleado);
		this.panelEmpleados.add(this.cedulaEmpleado);		
		this.panelEmpleados.add(this.confirmar[1]);
		this.panelEmpleados.add(this.cancelar[1]);
		this.panelProductos.add(this.etiquetaReferencia);		
		this.panelProductos.add(this.referencia);
		this.panelProductos.add(this.confirmar[3]);
		this.panelProductos.add(this.cancelar[2]);
	
		this.panelClientes.add(this.etiquetaCedula);
		this.panelClientes.add(this.cedula);
		this.panelClientes.add(this.confirmar[2]);
		this.panelClientes.add(this.cancelar[3]);
	}
	
	public void setMode(int i){
		if(i==0){
			this.getContentPane().removeAll();
			this.getContentPane().add(panelEmpleados);
		}
		else if(i==1){
			this.getContentPane().removeAll();
			this.getContentPane().add(panelClientes);
		}
		else if(i==2){
			this.getContentPane().removeAll();
			this.getContentPane().add(panelProveedores);
		}
		else if (i==3){
			this.getContentPane().removeAll();
			this.getContentPane().add(panelProductos);
		}
		this.setBounds(300, 300, 500, 100);
	}
	
	public void abrir(){
		this.setVisible(true);
		this.setBounds(100, 150, 500, 100);		
		add(panelClientes);
		add(panelEmpleados);
		add(panelProductos);
		add(panelProveedores);
		this.panelEmpleados.setVisible(false);
		this.panelProductos.setVisible(false);
		this.panelClientes.setVisible(false);
		this.panelProveedores.setVisible(false);
		if(IngresarTest.frameAdministrador.getSeleccion().getSelectedItem()
				.equals("Empleados"))		
			panelEmpleados.setVisible(true);
		if(IngresarTest.frameAdministrador.getSeleccion().getSelectedItem()
				.equals("Proveedores"))
			panelProveedores.setVisible(true);		
		if(IngresarTest.frameAdministrador.getSeleccion().getSelectedItem()
				.equals("Productos"))
			panelProductos.setVisible(true);
		if(IngresarTest.frameAdministrador.getSeleccion().getSelectedItem()
				.equals("Clientes"))			
			panelClientes.setVisible(true);		
	}
	public void cerrar(){
		this.setVisible(false);
	}
	
	private class ButtonHandler implements ActionListener{

		private int opcionUsuario; // Denota si el usuario quiere o no modificar otro resitro
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(confirmar[0])){
				
				if(IngresarTest.empresa.getProveedores().
						containsKey(Double.parseDouble(nit.getText()))){
					JOptionPane.showMessageDialog(null, 
							"Por favor modifique los datos necesarios y luego guarde");
					ModificarFrame.super.setBounds(100, 150, 250, 200);
					ModificarFrame.super.remove(panelProveedores);					
					nombre.setText(IngresarTest.empresa.getProveedores()
							.get(Double.parseDouble(nit.getText())).getNombre());
					
					ModificarFrame.super.add(etiquetaNombre);
					ModificarFrame.super.add(nombre);					
					telefono.setText(Integer.toString(IngresarTest.empresa.getProveedores()
							.get(Double.parseDouble(nit.getText())).getTelefono()));					
					ModificarFrame.super.add(etiquetaTelefono);
					ModificarFrame.super.add(telefono);
					ModificarFrame.super.add(guardar[0]);
				}
				else{
					JOptionPane.showMessageDialog(null, "Sus datos no son válidos, por favor vuelva a intenar","Datos inválidos", JFrame.EXIT_ON_CLOSE);
					
				}
			}
			if(e.getSource().equals(confirmar[1])){
				try{
					if(IngresarTest.empresa.getEmpleados()
							.containsKey(Double.parseDouble(cedulaEmpleado.getText()))){
						JOptionPane.showMessageDialog(null, 
								"Por favor modifique los datos necesarios y luego guarde");
						ModificarFrame.super.setBounds(100,150,230,200);
						ModificarFrame.super.remove(panelEmpleados);
						nombre.setText(IngresarTest.empresa.getEmpleados()
								.get(Double.parseDouble(cedulaEmpleado.getText())).getNombre());
						salario.setText(Double.toString(IngresarTest.empresa.getEmpleados()
								.get(Double.parseDouble(cedulaEmpleado.getText())).getSalario()));			
						telefono.setText(Double.toString(IngresarTest.empresa.getEmpleados()
								.get(Double.parseDouble(cedulaEmpleado.getText())).getTelefono()));
						correo.setText(IngresarTest.empresa.getEmpleados().get(Double.parseDouble(cedulaEmpleado.getText()))
								.getCorreoElectronico());
						ModificarFrame.super.add(etiquetaNombre);
						ModificarFrame.super.add(nombre);
						ModificarFrame.super.add(etiquetaSalario);
						ModificarFrame.super.add(salario);
						ModificarFrame.super.add(etiquetaTelefono);
						ModificarFrame.super.add(telefono);
						ModificarFrame.super.add(etiquetaCorreo);
						ModificarFrame.super.add(correo);		
						ModificarFrame.super.add(guardar[1]);
					}
					else{
						JOptionPane.showMessageDialog(null, "Sus datos no son válidos, por favor vuelva a intenar","Datos inválidos", JFrame.EXIT_ON_CLOSE);
						
					}
				}
				catch(NumberFormatException exception){
					JOptionPane.showMessageDialog(null, "Número no válido", "Entradda no válida", JOptionPane.ERROR_MESSAGE);
				}
			}
			if(e.getSource().equals(confirmar[2])){
				try{	
					if(IngresarTest.empresa.getClientes().
							containsKey(Double.parseDouble(cedula.getText()))){
						JOptionPane.showMessageDialog(null, 
						"Por favor modifique los datos necesarios y luego guarde");
						ModificarFrame.super.setBounds(100,150,250,200);
						ModificarFrame.super.remove(panelClientes);
						nombre.setText(IngresarTest.empresa.getClientes()
								.get(Double.parseDouble(cedula.getText())).getNombre());
						telefono.setText(Integer.toString(IngresarTest.empresa.getClientes()
								.get(Double.parseDouble(cedula.getText())).getTelefono()));
						correo.setText(IngresarTest.empresa.getClientes().get(Double.parseDouble(cedula.getText()))
								.getCorreo());
						ModificarFrame.super.add(etiquetaNombre);
						ModificarFrame.super.add(nombre);
						ModificarFrame.super.add(etiquetaTelefono);
						ModificarFrame.super.add(telefono);
						ModificarFrame.super.add(etiquetaCorreo);
						ModificarFrame.super.add(correo);
						ModificarFrame.super.add(guardar[2]);
					}
					else{
						JOptionPane.showMessageDialog(null, "Sus datos no son válidos, por favor vuelva a intenar","Datos inválidos", JFrame.EXIT_ON_CLOSE);
						
					}
				}
				catch(NumberFormatException exception){
					JOptionPane.showMessageDialog(null, "Número no válido", "Entradda no válida", JOptionPane.ERROR_MESSAGE);
				}
			}
			if(e.getSource().equals(confirmar[3])){
				try{
					if(IngresarTest.empresa.getProductos().containsKey(referencia.getText())){
						JOptionPane.showMessageDialog(null, 
						"Por favor modifique los datos necesarios y luego guarde");
						ModificarFrame.super.setBounds(100,150,250,200);
						ModificarFrame.super.remove(panelProductos);
						cantidadStock.setText(Integer.toString(IngresarTest.empresa.getProductos()
								.get(referencia.getText()).getCantidadStock()));
						valorVenta.setText(Double.toString(IngresarTest.empresa.getProductos()
								.get(referencia.getText()).getValorVenta()));
						valorCompra.setText(Double.toString(IngresarTest.empresa.getProductos()
								.get(referencia.getText()).getValorCompra()));
						ModificarFrame.super.add(etiquetaCantidadStock);
						ModificarFrame.super.add(cantidadStock);
						ModificarFrame.super.add(etiquetaValorVenta);
						ModificarFrame.super.add(valorVenta);
						ModificarFrame.super.add(etiquetaValorCompra);
						ModificarFrame.super.add(valorCompra);
						ModificarFrame.super.add(guardar[3]);
					}
					else{
						JOptionPane.showMessageDialog(null, "Sus datos no son válidos, por favor vuelva a intenar","Datos inválidos", JFrame.EXIT_ON_CLOSE);
						
					}
				}
				catch(NumberFormatException exception){
					JOptionPane.showMessageDialog(null, "Número no válido", "Entradda no válida", JOptionPane.ERROR_MESSAGE);
				}
			}
			if(e.getSource().equals(cancelar[0])||e.getSource().equals(cancelar[1])
					||e.getSource().equals(cancelar[2])||e.getSource().equals(cancelar[3])){
				IngresarTest.frameModificar.cerrar();
				IngresarTest.frameAdministrador.abrir();
			}
			if(e.getSource().equals(guardar[0])){
				
				IngresarTest.empresa.getProveedores().get(Double.parseDouble(nit.getText()))
						.modificar(nombre.getText(), Integer.parseInt(telefono.getText()));
				opcionUsuario=JOptionPane.showConfirmDialog(null, "Sus datos han sido modificados correctamente. Desea modificar otro registro? ", "Exito al guardar",0);
				if(opcionUsuario==0){
					ModificarFrame.super.getContentPane().removeAll();
					ModificarFrame.super.setBounds(100, 150, 500, 100);
					nit.setText("");
					ModificarFrame.super.getContentPane().add(panelProveedores);					
				}
				else{
					IngresarTest.frameModificar.cerrar();
					ModificarFrame.super.getContentPane().removeAll();
					IngresarTest.frameAdministrador.abrir();
				}
			}
			if(e.getSource().equals(guardar[1])){
				IngresarTest.empresa.getEmpleados().get(Double.parseDouble(cedulaEmpleado.getText())).
					modificar(nombre.getText(), Double.parseDouble(salario.getText()), Double.parseDouble(telefono.getText()), correo.getText());
				opcionUsuario=JOptionPane.showConfirmDialog(null, "Sus datos han sido modificados correctamente. Desea modificar otro registro? ", "Exito al guardar",0);
				if(opcionUsuario==0){
					ModificarFrame.super.getContentPane().removeAll();
					ModificarFrame.super.setBounds(100, 150, 500, 100);
					cedulaEmpleado.setText("");
					ModificarFrame.super.getContentPane().add(panelEmpleados);
					
				}
				else{
					IngresarTest.frameModificar.cerrar();
					ModificarFrame.super.getContentPane().removeAll();
					IngresarTest.frameAdministrador.abrir();
					
				}
			}
			if(e.getSource().equals(guardar[2])){
				IngresarTest.empresa.getClientes().get(Double.parseDouble(cedula.getText()))
					.modificar(nombre.getText(), Integer.parseInt(telefono.getText()), correo.getText());
				opcionUsuario=JOptionPane.showConfirmDialog(null, "Sus datos han sido modificados correctamente. Desea modificar otro registro? ", "Exito al guardar",0);
				if(opcionUsuario==0){
					ModificarFrame.super.getContentPane().removeAll();
					ModificarFrame.super.setBounds(100, 150, 500, 100);
					cedula.setText("");
					ModificarFrame.super.getContentPane().add(panelClientes);					
				}
				else{
					IngresarTest.frameModificar.cerrar();
					ModificarFrame.super.getContentPane().removeAll();
					IngresarTest.frameAdministrador.abrir();
				}
			}
			if(e.getSource().equals(guardar[3])){
							
				IngresarTest.empresa.getProductos().get(referencia.getText())
					.modificar(Integer.parseInt(cantidadStock.getText()), Double.parseDouble(valorVenta.getText()),Double.parseDouble(valorCompra.getText()));
				opcionUsuario=JOptionPane.showConfirmDialog(null, "Sus datos han sido modificados correctamente. Desea modificar otro registro? ", "Exito al guardar",0);
				if(opcionUsuario==0){
					ModificarFrame.super.getContentPane().removeAll();
					ModificarFrame.super.setBounds(100, 150, 500, 100);
					referencia.setText("");
					ModificarFrame.super.getContentPane().add(panelProductos);					
				}
				else{
					IngresarTest.frameModificar.cerrar();
					ModificarFrame.super.getContentPane().removeAll();
					IngresarTest.frameAdministrador.abrir();
				}
			}		
		}
		
	}
}
