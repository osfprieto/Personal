package gui;

import java.awt.*;
import java.awt.event.*;
import classes.*;
import javax.swing.*;

public class VerClientesGUI extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2506649933989834966L;
	private static Container cont;
	private static ButtonHandler handler;
	
	private static JButton confirmarCedula;
	private static JButton volver;
	private static JTextField cedulaIn;
	private static JTextArea infoCliente;
	
	private static boolean inCliente;//esta variable booleana muestra en cuál de los estados de la GUI
									//se encuentra el frame: si es true, está en la opción de recibir
									//la cédula del cliente; si es false, está mostrando los datos de
									//Cliente determinado
	
	public VerClientesGUI(){
		super("Ver Clientes");
		
		handler = new ButtonHandler();
		cedulaIn = new JTextField("");
		
		confirmarCedula = new JButton("Confirmar");
		confirmarCedula.setToolTipText("Usa la Cédula del cuadro de texto para mostrar esta información.");
		confirmarCedula.addActionListener(handler);
		
		volver = new JButton("Volver");
		volver.addActionListener(handler);
		
		this.init();
	}
	
	private void init(){
		
		inCliente = true;
		
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
		panel0.add(new JLabel("Ingrese el número de la cédula del cliente que desea ver:"), BorderLayout.NORTH);
		panel0.add(new JLabel("|"), BorderLayout.SOUTH);
		panel0.add(cedulaIn, BorderLayout.CENTER);
		cont.add(panel0, BorderLayout.CENTER);
		this.setSize(500, 100);
	}
	
	public void mostrar(Cliente cliente){
		this.setVisible(false);
		cont = this.getContentPane();
		cont.removeAll();
		cont.setLayout(new FlowLayout());
		infoCliente = new JTextArea();
		infoCliente.append(cliente.toString());
		infoCliente.setEditable(false);
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(infoCliente);
		cont.add(panel);
		cont.add(volver);
		this.setContentPane(cont);
		this.setSize(300, 200);
		this.setVisible(true);
	}
	
	private class ButtonHandler implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			// si es confimar, busca al cliente en la base de datos y lo muestra en la
			// JTextArea, si no lo encuentra, manda un mensaje de error y sigue en espera
			// Si es volver y está en la segunda, se devuelve a la primera, si es la primera se
			// devuelve a la primera, por lo que usa al temporal de empleado que instancia a todas
			// GUI's por aparte; para saber esto se usa a inCLiente.
			if(e.getSource() == VerClientesGUI.confirmarCedula){
				try{
					Cliente clienteTemp = IngresarTest.empresa.getObjetoCliente(Double.parseDouble(VerClientesGUI.cedulaIn.getText()));
					if(clienteTemp == null){
						JOptionPane.showMessageDialog(null, "El cliente no ha sido encontrado. Verifique la cédula ingresada", "Cliente no encontrado", JOptionPane.ERROR_MESSAGE);
					}
					else{
						inCliente = false;
						mostrar(clienteTemp);
					}
				}
				catch(NumberFormatException numberFormatException){
					JOptionPane.showMessageDialog(null, "Usted no ha ingresado un número de cédula válido", "Dato no válido", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(e.getSource() == volver){
				if(!inCliente){
					inCliente = true;
					init();
				}
				else if(inCliente){
					//aquí vuelve al Frame determinado del Empleado actual, por ahora sale.
					//System.exit(1);
					/*if(IngresarTest.empleadoAtual.getClass().getName().equals("classes.Administrador")){
						IngresarTest.verClientesGUI.setVisible(false);
						IngresarTest.frameAdministrador.setVisible(true);
					}
					else if(IngresarTest.empleadoAtual.getClass().getName().equals("classes.Comprador")){
						IngresarTest.verClientesGUI.setVisible(false);
						IngresarTest.frameComprador.setVisible(true);
					}
					else if(IngresarTest.empleadoAtual.getClass().getName().equals("classes.Vendedor")){
						IngresarTest.verClientesGUI.setVisible(false);
						IngresarTest.frameVendedor.setVisible(true);
					}*/
					VerClientesGUI.cedulaIn.setText("");
					IngresarTest.verClientesGUI.setVisible(false);
				}
			}
		}
	}
}

