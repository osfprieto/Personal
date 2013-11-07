package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import classes.Administrador;

public class NewEmpresaGUI extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5135941869543028303L;
	private JLabel labelNombre = new JLabel("nombre");
	private JTextField nombre = new JTextField(15);
	private JLabel labelCedula = new JLabel("Cédula de Ciudadanía");
	private JTextField cedula = new JTextField(15);
	private JLabel labelSalario = new JLabel("Salario");
	private JTextField salario = new JTextField(10);
	private JLabel labelTelefono = new JLabel("Teléfono");
	private JTextField telefono = new JTextField(7);
	private JLabel labelCorreo = new JLabel("Correo Electrónico");
	private JTextField correo = new JTextField(15);
	private JLabel labelFecha = new JLabel("Fecha Nacimiento");
	private JTextField fecha = new JTextField(10);
	private JLabel labelContrasenia = new JLabel("Contraseña");
	private JTextField contrasenia = new JTextField(10);
	
	private JButton confirmar = new JButton("Confirmar");
	private JPanel panelLabels = new JPanel();
	private JPanel panelTextFields = new JPanel();
	private Container cont;
	
	public NewEmpresaGUI(){
		cont = this.getContentPane();
		cont.removeAll();
		cont.setLayout(new BorderLayout());
		
		panelLabels.setLayout(new GridLayout(7, 1));
		panelTextFields.setLayout(new GridLayout(7, 1));
		
		panelLabels.add(labelNombre);
		panelLabels.add(labelCedula);
		panelLabels.add(labelSalario);
		panelLabels.add(labelTelefono);
		panelLabels.add(labelCorreo);
		panelLabels.add(labelFecha);
		panelLabels.add(labelContrasenia);
		
		panelTextFields.add(nombre);
		panelTextFields.add(cedula);
		panelTextFields.add(salario);
		panelTextFields.add(telefono);
		panelTextFields.add(correo);
		panelTextFields.add(fecha);
		panelTextFields.add(contrasenia);
		
		confirmar.setBackground(Color.GREEN);
		confirmar.addActionListener(this);
		
		cont.add(panelLabels, BorderLayout.WEST);
		cont.add(panelTextFields, BorderLayout.EAST);
		cont.add(confirmar, BorderLayout.SOUTH);
		
		this.setBounds(400, 100, 340, 410);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(confirmar)){
			try{
				Administrador administrador = new Administrador(nombre.getText(),
						Double.parseDouble(cedula.getText()), Double.parseDouble(salario.getText()),
						Double.parseDouble(telefono.getText()), correo.getText(), fecha.getText(),
						contrasenia.getText());
				IngresarTest.empresa.setAdministrador(administrador);
				JOptionPane.showMessageDialog(null, "La empresa ha sido creada correctamente."
						+"\nAhora puede manejar la base de datos como Administrador"+
						"\ndesde esta plataforma.\nAhora reinicie la aplicación.", "Exito",
						JOptionPane.INFORMATION_MESSAGE);
						this.setVisible(false);
						IngresarTest.guardar();
			}
			catch(NumberFormatException exception){
				JOptionPane.showMessageDialog(null, "Usted ha ingresado algún número erróneo",
						"Entreada no válida", JOptionPane.ERROR_MESSAGE);
			} catch (IOException exception) {
				JOptionPane.showMessageDialog(null, "No se ha podido guardar el archivo de la empresa",
						"Error al guardar", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}
