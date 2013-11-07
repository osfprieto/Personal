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

import classes.Proveedor;

public class VerProveedorGUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 541506042045618000L;
	private static Container cont;
	private static ButtonHandler handler;
	
	private static JButton confirmarNIT;
	private static JButton volver;
	private static JTextField NITIn;
	private static JTextArea infoProveedor;
	
	private static boolean inProveedor;//esta variable booleana muestra en cuál de los estados de la GUI
									//se encuentra el frame: si es true, está en la opción de recibir
									//la cédula del Empleado; si es false, está mostrando los datos de
									//Empleado determinado
	
	public VerProveedorGUI(){
		super("Ver Proveedor");
		
		handler = new ButtonHandler();
		NITIn = new JTextField("");
		
		confirmarNIT = new JButton("Confirmar");
		confirmarNIT.setToolTipText("Usa el NIT del cuadro de texto para mostrar esta información.");
		confirmarNIT.addActionListener(handler);
		
		volver = new JButton("Volver");
		volver.addActionListener(handler);
		
		this.init();
	}
	
	private void init(){
		
		inProveedor = true;
		
		cont = this.getContentPane();
		
		cont.removeAll();
		cont.setLayout(new BorderLayout());
		JPanel panel0 = new JPanel();
		panel0.setLayout(new BorderLayout());
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		panel1.add(confirmarNIT, BorderLayout.NORTH);
		panel1.add(volver, BorderLayout.SOUTH);
		cont.add(panel1, BorderLayout.EAST);
		panel0.add(new JLabel("Ingrese el número de la NIT del Proveedor que desea ver:"), BorderLayout.NORTH);
		panel0.add(new JLabel("|"), BorderLayout.SOUTH);
		panel0.add(NITIn, BorderLayout.CENTER);
		cont.add(panel0, BorderLayout.CENTER);
		this.setSize(500, 100);
	}
	
	public void mostrar(Proveedor proveedor){
		this.setVisible(false);
		cont = this.getContentPane();
		cont.removeAll();
		cont.setLayout(new FlowLayout());
		infoProveedor = new JTextArea();
		infoProveedor.append((proveedor).toString());
		infoProveedor.setEditable(false);
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(infoProveedor);
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
			if(e.getSource() == confirmarNIT){
				try{
					Proveedor proveedorTemp = IngresarTest.empresa.getObjetoProveedor(Double.parseDouble(NITIn.getText()));
					if(proveedorTemp == null){
						JOptionPane.showMessageDialog(null, "El proveedor no ha sido encontrado. Verifique el NIT ingresado", "Proveedor no encontrado", JOptionPane.ERROR_MESSAGE);
					}
					else{
						inProveedor = false;
						mostrar(proveedorTemp);
					}
				}
				catch(NumberFormatException numberFormatException){
					JOptionPane.showMessageDialog(null, "Usted no ha ingresado un número de cédula válido", "Dato no válido", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(e.getSource() == volver){
				if(!inProveedor){
					inProveedor = true;
					init();
				}
				else if(inProveedor){
					//aquí vuelve al Frame determinado del Empleado actual, por ahora sale.
					//System.exit(1);
					/*if(IngresarTest.empleadoAtual.getClass().getName().equals("classes.Administrador")){
						IngresarTest.verProveedorGUI.setVisible(false);
						IngresarTest.frameAdministrador.setVisible(true);
					}
					else if(IngresarTest.empleadoAtual.getClass().getName().equals("classes.Comprador")){
						IngresarTest.verProveedorGUI.setVisible(false);
						IngresarTest.frameComprador.setVisible(true);
					}
					else if(IngresarTest.empleadoAtual.getClass().getName().equals("classes.Vendedor")){
						IngresarTest.verProveedorGUI.setVisible(false);
						IngresarTest.frameVendedor.setVisible(true);
					
					}*/
					VerProveedorGUI.NITIn.setText("");
					IngresarTest.verProveedorGUI.setVisible(false);
				}
			}
		}
	}
}
