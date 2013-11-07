package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import classes.Empleados;

public class IngresarFrame extends JFrame{
	
private static final long serialVersionUID = 1L;//no entiendo qué hace esto:osf
	private JTextField usuario; // campo para ingresar el usuario
	private JPasswordField password; // campo para ingresar la contraseña
	private JLabel etiquetaUsuario; // etiqueta para el campo de usuario
	private JLabel etiquetaPassword; // etiqueta para el campo de contraseña
	private JButton confirmar; // boton de confirmar
	private JButton cancelar; // boton de cancelar 
	/*private File imgPath; // dirección de la imágen de fondo*/
	/*private Icon img; // imágen de fondo*/
	private JLabel empresa; // etiqueta con el nombre de la empresa
	private ButtonHandler handler; 
	
	
	public IngresarFrame(){
		
		super("Ingreso de Usuario");
		super.setLayout(new FlowLayout());
		/*imgPath=new File("img"+File.separator+"ingresoImg.png");*/
		/*img=new ImageIcon(imgPath.getPath());*/
		handler = new ButtonHandler();
		usuario= new JTextField();
		password= new JPasswordField();
		usuario.setColumns(12);
		password.setColumns(12);
		empresa=new JLabel("                 Chocolates Glue S.A.                    ");
		empresa.setFont(Font.decode(Font.MONOSPACED));
		confirmar= new JButton("CONFIRMAR");
		confirmar.addActionListener(handler);
		cancelar= new JButton("CANCELAR");
		cancelar.addActionListener(handler);
		confirmar.setBackground(Color.green);
		cancelar.setBackground(Color.red);
		etiquetaUsuario=new JLabel("Ingrese su usuario: ");
		etiquetaPassword= new JLabel("Ingrese su contraseña: ");
		add(empresa);
		add(etiquetaUsuario);
		add(usuario);
		add(etiquetaPassword);
		add(password);
		add(confirmar);
		add(cancelar);
	}
	/*
	 * El método abrir, se encarga de abrir una ventana con 
	 * el IngresoFrame
	 */
	public void abrir(){
		this.setBounds(350, 200, 350, 150);
		this.setVisible(true);
	}
	/*
	 * El método cerrar, se encarga de cerrar la ventana de 
	 * ingreso 
	 */
	public void cerrar(){
		this.setVisible(false);
	}
	public void limpiar(){
		this.usuario.setText("");
		this.password.setText("");
	}
	private class ButtonHandler implements ActionListener{
		boolean flag=false;
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(cancelar)){
				JOptionPane.showMessageDialog(null, "Gracias por utilizar nuestro sistema.", "Hasta luego", JOptionPane.PLAIN_MESSAGE);
				try{
							IngresarTest.frameIngreso.setVisible(false);
							IngresarTest.guardar();
				} catch (IOException exception) {
					JOptionPane.showMessageDialog(null, "No se ha podido guardar el archivo de la empresa",
							"Error al guardar", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			else if(e.getSource().equals(confirmar)){
				
				if(!usuario.getText().isEmpty() && !password.getText().isEmpty()){
				if(Double.parseDouble(usuario.getText()) == ((Double)IngresarTest.empresa.getAdministrador().getCedulaCiudadania())
						&& password.getText().equals(IngresarTest.empresa.getAdministrador().getContrasenia())){
					IngresarTest.frameAdministrador.abrir();
					IngresarTest.frameIngreso.cerrar();					
					flag=true;
				}
				for(Empleados emp: IngresarTest.empresa.getEmpleados().values()){
					
					if((Double)emp.getCedulaCiudadania()==Double.parseDouble(usuario.getText())
							&& password.getText().equals(emp.getContrasenia())){
						flag=true;
						
						if(emp.getClass().getName().equals("classes.Comprador")){
							IngresarTest.frameIngreso.cerrar();
							IngresarTest.frameComprador.abrir();
						}
						if(emp.getClass().getName().equals("classes.Vendedor")){
							IngresarTest.frameIngreso.cerrar();
							IngresarTest.frameVendedor.abrir();
						}
					}
				}
				if(!flag){
					JOptionPane.showMessageDialog(null, "Sus datos no son válidos, por favor vuelva a intentar");
				}
			}
			
			else{
				JOptionPane.showMessageDialog(null, "Por favor llene todos los campos");
			}
			}
		}
		
	}
}
