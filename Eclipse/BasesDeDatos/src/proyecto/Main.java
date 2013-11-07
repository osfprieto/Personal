package proyecto;

import java.sql.*;
import javax.swing.*;


public class Main {
	
	private static Statement st;
	private static ResultSet rs;
	private static Connection con;
	private static String connectionUrl;
	private static String query;
	
	public static void main(String[] args) {
		crearConexion();
		
		String[] lista = {"Registrar", "Buscar", "Salir"};
		JComboBox combo = new JComboBox(lista);
		
		JOptionPane.showMessageDialog(null, combo, "Acción", JOptionPane.PLAIN_MESSAGE);
		//JOptionPane.showConfirmDialog(null, combo, "Selección", JOptionPane.OK_OPTION);
		
		while(combo.getSelectedIndex()!=2){
			if(combo.getSelectedIndex()==0)
				ingresarRegistro();
			else if(combo.getSelectedIndex()==1)
				buscarRegistros();
			combo.setSelectedIndex(2);
			JOptionPane.showMessageDialog(null, combo, "Acción", JOptionPane.PLAIN_MESSAGE);
			//JOptionPane.showConfirmDialog(null, combo, "Selección", JOptionPane.OK_OPTION);
		}
		
		System.exit(0);
	}
	
	private static void buscarRegistros(){
		String entrada = JOptionPane.showInputDialog("Ingrese el NUIP del menor:");
		query = "call mostrarDatosMenor("+entrada+")";
		try {
			rs = st.executeQuery(query);
			if(rs.next()){
				JLabel label = new JLabel("<html>Nombre del menor: <b>"+rs.getString("estudiante")+"</b><br>" +
										"Nombre de la madre: <b>"+rs.getString("madre")+"</b></html>");
				JOptionPane.showMessageDialog(null, label, "Datos", JOptionPane.PLAIN_MESSAGE);
			}
			else{
				JOptionPane.showMessageDialog(null, "Datos del menor no registrados", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error buscando datos", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private static void ingresarRegistro(){
		MatriculaFrame matricula = new MatriculaFrame();
		MenorFrame menor;
		MadreFrame madre = new MadreFrame();
		DatosMadre datosMadre;
		
		matricula.setVisible(true);
		while(matricula.enEspera()){
			//esperar a que se llenen los datos bn en matricula
			//posibilidad de miltihilos de sencilla implementación
			//verificación en tiempo real de datos ingresados
		}
		//System.out.println(matricula.getInsertSQLCommand("matricula"));
		String idMatricula = matricula.getPregunta(0).toString();
		
		madre.setVisible(true);
		while(madre.enEspera()){
			//esperar a que se llenen los datos bn en madre
			//posibilidad de miltihilos de sencilla implementación
			//verificación en tiempo real de datos ingresados
		}
		//System.out.println(madre.getInsertSQLCommand("pariente"));
		String numeroDocumento = madre.getPregunta(0).toString();
		String tipoVinculacion = madre.getPregunta(5).toString();
		
		if(tipoVinculacion.equals("estudiante_UN"))
			datosMadre = new estudiante_UN(numeroDocumento);
		else if(tipoVinculacion.equals("empleado_oficial"))
			datosMadre = new empleado_oficial(numeroDocumento);
		else if(tipoVinculacion.equals("docente_UN"))
			datosMadre = new docente_UN(numeroDocumento);
		else if(tipoVinculacion.equals("estudiante"))
			datosMadre = new estudiante(numeroDocumento);
		else if(tipoVinculacion.equals("empleado"))
			datosMadre = new empleado(numeroDocumento);
		else// if(tipoVinculacion.equals("independiente"))
			datosMadre = new independiente(numeroDocumento);
		
		datosMadre.setVisible(true);
		while(datosMadre.enEspera()){
			//esperar a que se llenen los datos bn en datos madre
			//posibilidad de miltihilos de sencilla implementación
			//verificación en tiempo real de datos ingresados
		}
		//System.out.println(datosMadre.getInsertSQLCommand(tipoVinculacion));
		
		menor = new MenorFrame(numeroDocumento, numeroDocumento, idMatricula);
		
		menor.setVisible(true);
		while(menor.enEspera()){
			//esperar a que se llenen los datos bn en menor
			//posibilidad de miltihilos de sencilla implementación
			//verificación en tiempo real de datos ingresados
		}
		//System.out.println(menor.getInsertSQLCommand("estudiante_jardin"));
		
		//obtener los comandos de insert y ejecutarlos, crear la conexión
		
		
		try{
			query = matricula.getInsertSQLCommand("matricula");
			st.executeUpdate(query);
			query = madre.getInsertSQLCommand("pariente");
			st.executeUpdate(query);
			query = datosMadre.getInsertSQLCommand(tipoVinculacion);
			st.executeUpdate(query);
			query = menor.getInsertSQLCommand("estudiante_jardin");
			st.executeUpdate(query);
			
			JOptionPane.showMessageDialog(null, "Datos registrados exitosamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
			
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error creando registros", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	private static void crearConexion(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connectionUrl = "jdbc:mysql://192.168.80.1/proyecto?user=root&password=92031416126";
	        con = DriverManager.getConnection(connectionUrl);
	        st = con.createStatement();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error creando conexión", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}
	
}
