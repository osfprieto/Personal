package tests;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

@SuppressWarnings("serial")
public class CompleteGUI extends JFrame implements ActionListener{
	
	private static final String MENSAJE_DEMO = "Para la demostración de la conexión, permitiremos al usuario conectarse a la base\n" +
												"de datos Bookstore y hacer operaciones de actualización, inserción y borrado de\n" +
												"tuplas sobre la relación stores: el acceso a las tuplas se hará uno a uno con el\n" +
												"atributo store_id, a través de este se podrá ver store_id, store_name y store_adress\n" +
												"existe la posibilidad de modifiar la dirección e insertar ingresando el store_id y\n" +
												"store_name, los otros atributos serán nulos y se permiten borrar registros por stor_id";
	private static final String MENSAJE_ADMON = "Esta sección no está plenamente desarrollada, pueden presentarese inconvenientes";
	private static final String[] OPCIONES_DEMO = {"Buscar", "Modificar", "Insertar", "Borrar"};
	
	private static JComboBox comboModo;
	private static JComboBox comboBox;
	private static JButton runButton;
	private static JTextField keyText;
	private static JTextField extraText;
	private static JPanel demoPanel;
	private static JPanel demoSelectPanel;
	private static JPanel demoValuesPanel;
	private static JPanel demoKeyPanel;
	private static JPanel demoExtraPanel;
	
	private static JTextField ipText;
	private static JTextField bdNameText;
	private static JTextField userName;
	private static JPasswordField userPassword;
	
	private static JTextArea inputAdminTextArea;
	private static JTextArea outputAdminTextArea;
	private static JPanel panelConfiguracionConexion;
	private static JPanel panelInformacionConexion;
	private static JPanel panelButtons;
	private static JPanel panelAdministrador;
	private static java.sql.Connection conAdmin;
	
	
	private static Container cont;
	
	public static void main(String[] args) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		String[] opciones = {"Demo","Administrador"};
		comboModo = new JComboBox(opciones);
		panel.add(new JLabel("Seleccione modo:"));
		panel.add(comboModo);
		JOptionPane.showMessageDialog(null, panel, "Inicio", JOptionPane.PLAIN_MESSAGE);
		CompleteGUI gui = new CompleteGUI(comboModo.getSelectedIndex());
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setVisible(true);
	}
	
	@SuppressWarnings("deprecation")
	public CompleteGUI(int modo){
		super("Interfaz BD");
		cont = this.getContentPane();
		cont.removeAll();
		
		if(modo==0){//demo
			JOptionPane.showMessageDialog(null, CompleteGUI.MENSAJE_DEMO, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
			
			comboBox = new JComboBox(CompleteGUI.OPCIONES_DEMO);
			comboBox.addActionListener(this);
			runButton = new JButton("Correr");
			runButton.addActionListener(this);
			demoSelectPanel = new JPanel();
			demoSelectPanel.setLayout(new GridLayout(2, 1));
			demoSelectPanel.add(comboBox);
			demoSelectPanel.add(runButton);
			
			keyText = new JTextField();
			extraText = new JTextField();
			demoKeyPanel = new JPanel();
			demoKeyPanel.setLayout(new BorderLayout());
			demoKeyPanel.add(keyText, BorderLayout.SOUTH);
			demoKeyPanel.add(new JLabel("store_id"), BorderLayout.NORTH);
			demoExtraPanel = new JPanel();
			demoExtraPanel.setLayout(new BorderLayout());
			demoExtraPanel.add(extraText, BorderLayout.SOUTH);
			demoExtraPanel.add(new JLabel("Valor"), BorderLayout.NORTH);
			demoExtraPanel.setVisible(false);
			demoValuesPanel = new JPanel();
			demoValuesPanel.setLayout(new GridLayout(1, 3));
			demoValuesPanel.add(demoKeyPanel);
			demoValuesPanel.add(new JLabel());
			demoValuesPanel.add(demoExtraPanel);
			
			demoPanel = new JPanel();
			demoPanel.setLayout(new BorderLayout());
			demoPanel.add(demoSelectPanel, BorderLayout.WEST);
			demoPanel.add(demoValuesPanel, BorderLayout.CENTER);
			
			cont.add(demoPanel);
			this.setBounds(400, 200, 0, 0);
			this.pack();
		}
		else if(modo==1){//admon
			JOptionPane.showMessageDialog(null, CompleteGUI.MENSAJE_ADMON, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
			ipText = new JTextField();
			bdNameText = new JTextField();
			userName = new JTextField();
			userPassword = new JPasswordField();
			panelConfiguracionConexion = new JPanel();
			
			/*bdNameText.setText("test");
			userName.setText("root");
			userPassword.setText("92031416126");*/
			
			panelConfiguracionConexion.setLayout(new GridLayout(4, 2));
			panelConfiguracionConexion.add(new JLabel("IP"));
			panelConfiguracionConexion.add(ipText);
			panelConfiguracionConexion.add(new JLabel("Nombre BD"));
			panelConfiguracionConexion.add(bdNameText);
			panelConfiguracionConexion.add(new JLabel("Usuario"));
			panelConfiguracionConexion.add(userName);
			panelConfiguracionConexion.add(new JLabel("Contraseña"));
			panelConfiguracionConexion.add(userPassword);
			JOptionPane.showMessageDialog(null, panelConfiguracionConexion, "Configuración de conexión", JOptionPane.PLAIN_MESSAGE);
			
			try{
				Class.forName("com.mysql.jdbc.Driver");
				String connectionUrl = "jdbc:mysql://"+ipText.getText()+"/"+bdNameText.getText()+"?user="+userName.getText()+"&password="+userPassword.getText();
	            conAdmin = DriverManager.getConnection(connectionUrl);
	            panelInformacionConexion = new JPanel();
	            panelInformacionConexion.setLayout(new GridLayout(3, 1));
	            panelInformacionConexion.add(new JLabel("IP: "+ipText.getText()));
	            panelInformacionConexion.add(new JLabel("BD: "+bdNameText.getText()));
	            panelInformacionConexion.add(new JLabel("User: "+userName.getText()));
	            runButton = new JButton("Correr");
	            runButton.addActionListener(this);
	            String[] opcionesComando = {"Query", "Update"};
	            comboBox = new JComboBox(opcionesComando);
	            panelButtons = new JPanel();
	            panelButtons.setLayout(new GridLayout(2, 1));
	            panelButtons.add(runButton);
	            panelButtons.add(comboBox);
	            inputAdminTextArea = new JTextArea();
	            outputAdminTextArea = new JTextArea();
	            outputAdminTextArea.setEditable(false);
	            JScrollPane scrollInput = new JScrollPane(inputAdminTextArea);
	            JScrollPane scrollOutput = new JScrollPane(outputAdminTextArea);
	            scrollInput.setAutoscrolls(true);
	            scrollOutput.setAutoscrolls(true);
	            panelAdministrador = new JPanel();
	            panelAdministrador.setLayout(new BorderLayout());
	            JPanel panelLeft = new JPanel();
	            panelLeft.setLayout(new BorderLayout());
	            panelLeft.add(panelButtons, BorderLayout.NORTH);
	            panelLeft.add(panelInformacionConexion, BorderLayout.SOUTH);
	            JPanel panelCenter = new JPanel();
	            panelCenter.setLayout(new GridLayout(2, 1));
	            panelCenter.add(scrollInput);
	            panelCenter.add(scrollOutput);
	            
	            panelAdministrador.add(panelLeft, BorderLayout.WEST);
	            panelAdministrador.add(panelCenter, BorderLayout.CENTER);
	            
	            cont.add(panelAdministrador);
	            this.setBounds(400, 200, 400, 400);
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(null, "Error al crear la conexión", "Error", JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(comboModo.getSelectedIndex()==0){
			if(e.getSource().equals(comboBox)){//se cambia el modo de la vista
				int selec = comboBox.getSelectedIndex();
				if(selec==0)
					demoExtraPanel.setVisible(false);
				else if(selec==1)
					demoExtraPanel.setVisible(true);
				else if(selec==2)
					demoExtraPanel.setVisible(true);
				else if(selec==3)
					demoExtraPanel.setVisible(false);
			}
			else if(e.getSource().equals(runButton)){//se corre el statement con respecto al texto ingresado
				try{
					Class.forName("com.mysql.jdbc.Driver");
					String connectionUrl = "jdbc:mysql://localhost/practica2?user=root&password=92031416126";
		            java.sql.Connection con = DriverManager.getConnection(connectionUrl);
		            
		            Statement st = con.createStatement();		            
		            String comandoSQL = "";
		            
		            if(comboBox.getSelectedIndex()==0){//buscar
		            	comandoSQL = "select * from stores where stor_id="+keyText.getText();
		            	ResultSet rs = st.executeQuery(comandoSQL);
		            	rs.next();
		            	String stor_id = rs.getString("stor_id");
		            	String stor_name = rs.getString("stor_name");
		            	String stor_address = rs.getString("stor_address");
		            	JPanel panelOut = new JPanel();
		            	panelOut.setLayout(new GridLayout(2, 3));
		            	panelOut.add(new JLabel("stor_id"));
		            	panelOut.add(new JLabel("stor_name"));
		            	panelOut.add(new JLabel("stor_address"));
		            	panelOut.add(new JLabel(stor_id));
		            	panelOut.add(new JLabel(stor_name));
		            	panelOut.add(new JLabel(stor_address));
		            	JOptionPane.showMessageDialog(null, panelOut, "Resultado búsqueda", JOptionPane.PLAIN_MESSAGE);
		            }
		            else if(comboBox.getSelectedIndex()==1){//modificar
		            	comandoSQL = "update stores set stor_address='"+extraText.getText()+"' where stor_id="+keyText.getText();
		            	int modificadas = st.executeUpdate(comandoSQL);
		            	JOptionPane.showMessageDialog(null, "Se han modificado "+modificadas+" filas", "Resultado modificación", JOptionPane.INFORMATION_MESSAGE);
		            }
		            else if(comboBox.getSelectedIndex()==2){//Insertar
		            	comandoSQL = "insert into stores(stor_id, stor_name) values ("+keyText.getText()+", '"+extraText.getText()+"')";
		            	int modificadas = st.executeUpdate(comandoSQL);
		            	JOptionPane.showMessageDialog(null, "Se han insertado "+modificadas+" filas", "Resultado inserción", JOptionPane.INFORMATION_MESSAGE);
		            }
		            else if(comboBox.getSelectedIndex()==3){//Borrar
		            	comandoSQL = "delete from stores where stor_id="+keyText.getText();
		            	int modificadas = st.executeUpdate(comandoSQL);
		            	JOptionPane.showMessageDialog(null, "Se han borrado "+modificadas+" filas", "Resultado borrado", JOptionPane.INFORMATION_MESSAGE);
		            }
				}
				catch(SQLException exc){
					JOptionPane.showMessageDialog(null, exc.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
				}
				catch(ClassNotFoundException exc){
					JOptionPane.showMessageDialog(null, exc.getMessage(), "Error Class not found", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		else if(comboModo.getSelectedIndex()==1){
			try{
				Statement st = conAdmin.createStatement();
				String[] comandos;
				int k;
				if(comboBox.getSelectedIndex()==0){//query
					comandos = inputAdminTextArea.getText().split(";");
					for(k=0;k<comandos.length;k++){
						ResultSet rs = st.executeQuery(comandos[k]+';');
						int j, i=0;
						String cadena="";
						
						while(rs.next()){
							try{
								j=0;
								while(true){
									cadena+=rs.getString(++j)+"\t\t";
								}
							}
							catch(Exception exc){
								cadena+="\n";
							}
							i++;
						}
						cadena+=(i+" filas retornadas");
						outputAdminTextArea.append("\n"+cadena);
					}
				}
				else if(comboBox.getSelectedIndex()==1){//update
					comandos = inputAdminTextArea.getText().split(";");
					for(k=0;k<comandos.length;k++){
						int rows = st.executeUpdate(comandos[k]+';');
						outputAdminTextArea.append("\n"+rows+" filas modificadas");
					}
				}
			}
			catch(Exception exc){
				outputAdminTextArea.append("\nEXCEPCIÓN: "+exc.getMessage());
			}
		}
	}
}
