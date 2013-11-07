package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import classes.Empleados;

public class VerEmpleadosGUI extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5229413777873499176L;

	private static Container cont;
	
	private static JButton confirmarCedula;
	private static JButton volver;
	private static JTextField cedulaIn;
	private static JTextArea infoEmpleado;
	
	private static boolean inEmpleado;//esta variable booleana muestra en cuál de los estados de la GUI
									//se encuentra el frame: si es true, está en la opción de recibir
									//la cédula del Empleado; si es false, está mostrando los datos de
									//Empleado determinado
	
	public VerEmpleadosGUI(){
		super("Ver Empleados");
		
		cedulaIn = new JTextField("");
		
		confirmarCedula = new JButton("Confirmar");
		confirmarCedula.setToolTipText("Usa la Cédula del cuadro de texto para mostrar esta información.");
		confirmarCedula.addActionListener(this);
		
		volver = new JButton("Volver");
		volver.addActionListener(this);
		
		this.init();
	}
	
	private void init(){
		
		inEmpleado = true;
		
		cont = this.getContentPane();
		
		cont.removeAll();
		cont.setLayout(new BorderLayout());
		JPanel panel0 = new JPanel();
		panel0.setLayout(new BorderLayout());
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		panel1.add(confirmarCedula, BorderLayout.NORTH);
		panel1.add(volver, BorderLayout.SOUTH);
		cont.add(panel1, BorderLayout.EAST);
		panel0.add(new JLabel("Ingrese el número de la cédula del Empleado que desea ver:"), BorderLayout.NORTH);
		panel0.add(new JLabel("|"), BorderLayout.SOUTH);
		panel0.add(cedulaIn, BorderLayout.CENTER);
		cont.add(panel0, BorderLayout.CENTER);
		this.setSize(500, 100);
	}
	
	public void mostrar(Empleados empleado){
		this.setVisible(false);
		cont = this.getContentPane();
		cont.removeAll();
		cont.setLayout(new FlowLayout());
		infoEmpleado = new JTextArea();
		infoEmpleado.append((empleado).toString());
		infoEmpleado.setEditable(false);
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(infoEmpleado);
		cont.add(panel);
		cont.add(volver);
		this.setContentPane(cont);
		this.setSize(300, 200);
		this.setVisible(true);
	}
		
	public void actionPerformed(ActionEvent e) {
		// si es confimar, busca al cliente en la base de datos y lo muestra en la
		// JTextArea, si no lo encuentra, manda un mensaje de error y sigue en espera
		// Si es volver y está en la segunda, se devuelve a la primera, si es la primera se
		// devuelve a la primera, por lo que usa al temporal de empleado que instancia a todas
		// GUI's por aparte; para saber esto se usa a inCLiente.
		if(e.getSource() == confirmarCedula){
			try{
				Empleados empleadoTemp = IngresarTest.empresa.getObjetoEmpleado(Double.parseDouble(cedulaIn.getText()));
				if(empleadoTemp == null){
					JOptionPane.showMessageDialog(null, "El empleado no ha sido encontrado. Verifique la cédula ingresada", "Empleado no encontrado", JOptionPane.ERROR_MESSAGE);
				}
				else{
					inEmpleado = false;
					mostrar(empleadoTemp);
				}
			}
			catch(NumberFormatException numberFormatException){
				JOptionPane.showMessageDialog(null, "Usted no ha ingresado un número de cédula válido", "Dato no válido", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(e.getSource() == volver){
			if(!inEmpleado){
				inEmpleado = true;
				init();
			}
			else if(inEmpleado){
				//aquí vuelve al Frame determinado del Empleado actual, por ahora sale.
				//System.exit(1);
				/*if(IngresarTest.empleadoAtual.getClass().getName().equals("classes.Administrador")){
					IngresarTest.verEmpleadosGUI.setVisible(false);
					//IngresarTest.frameAdministrador.setVisible(true);
				}
				else if(IngresarTest.empleadoAtual.getClass().getName().equals("classes.Comprador")){
					IngresarTest.verEmpleadosGUI.setVisible(false);
					//IngresarTest.frameComprador.setVisible(true);
				}
				else if(IngresarTest.empleadoAtual.getClass().getName().equals("classes.Vendedor")){
					IngresarTest.verEmpleadosGUI.setVisible(false);
					//IngresarTest.frameVendedor.setVisible(true);*/
				VerEmpleadosGUI.cedulaIn.setText("");
				IngresarTest.verEmpleadosGUI.setVisible(false);
			}
		}
	}
}
