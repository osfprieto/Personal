package varios;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class GramaticasII extends JFrame implements ActionListener{

	private static int estado;//Nos dice en qué parte del programa nos encontramos
	private static int cantidadSimbolos;//cantidad de símbolos que tiene el lenguaje
	private static int cantidadReglas;//cantidad de reglas que tiene la Gramática
	
	private static LinkedList<Character> simbolos;//Lista encadenada donde se almacenan los símbolos del lenguaje
	private static LinkedList<String> reglasImplicantes;//Lista encadenada donde se almacena la parte izquierda de cada una de las reglas de la gramática
	private static LinkedList<String> reglasImplicadas;//Lista encadenada donde se almacena la parte derecha de cada una de las reglas de la gramática
	
	private static LinkedList<JTextField> textSimbolos;//Lista encadenaeda que almacena los campos de texto mostrados en la interfaz gráfica al momento de solicitar los símbolos del lenguaje
	private static LinkedList<JTextField> textReglasImplicante;//Lista encadenada que almacena los campos de texto mostrados en la intergaz gráfica al momento de solicitar la parte izquierda de las reglase de la gramática
	private static LinkedList<JTextField> textReglasImplicadas;//Lista encadenada que almacena los campos de texto mostrados en la intergaz gráfica al momento de solicitar la parte derecha de las reglase de la gramática
	
	private static Container cont;//Contenedor usado para darle formato a la ventana de la interfaz gráfica
	
	private static JPanel panelButtons;//Panel en el que se ubicarán los botones de comandos
	private static JPanel panelInputs;//Panel en el que se ubicarán los campos de texto
	
	private static JButton aceptarButton;//Botón usado para dar la señal de aceptar y continuar al siguiente estado del programa
	private static JButton cancelarButton;//Botón usado para terminar el programa en cualquier momento
	private static JButton agregarDatosButton;//Botón usado para agregar aumentar la cantidad de datos (símbolos o reglas) al momento del ingreso de estas según sea el estado
	
	public static JScrollPane scrollPane;//
	
	public static void main(String[] args) {
		boolean exito = false;
		
		/*inicialización de las variables y muestra de la pimera ventana donde
		se solicitan la cantidad de símbolos y reglas iniciales*/
		JTextField inputSimbolos = new JTextField(5);
		JTextField inputReglas = new JTextField(5);
		JPanel input = new JPanel();
		input.setLayout(new GridLayout(3, 2));
		input.add(new JLabel("Entre la cantidad que desea"));
		input.add(new JLabel("ingresar de símbolos y reglas:"));
		input.add(new JLabel("Símbolos"));
		input.add(inputSimbolos);
		input.add(new JLabel("Reglas"));
		input.add(inputReglas);
		
		while(!exito){
			inputSimbolos.setText("");
			inputReglas.setText("");
			JOptionPane.showMessageDialog(null, input, "Inicio", JOptionPane.PLAIN_MESSAGE);
			try{
				cantidadSimbolos = Integer.parseInt(inputSimbolos.getText());
				cantidadReglas = Integer.parseInt(inputReglas.getText());
				exito = true;
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(null, "Ha ingresado un dato erróneo", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		GramaticasII frame = new GramaticasII();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	public GramaticasII(){
		super("Gramáticas");
		inicializar();
		setUI();
	}

	/* Instancia todos los objetos referenciados en la clase como estáticos
	 * para evitar apuntar a objetos nulos y no tener errores*/
	private void inicializar(){
		estado = 0;
		
		cont = this.getContentPane();
		
		panelButtons = new JPanel();
		panelInputs = new JPanel();
		
		scrollPane = new JScrollPane(panelInputs);
		scrollPane.setAutoscrolls(true);
		scrollPane.setSize(300, 380);
		
		aceptarButton = new JButton("Aceptar");
		aceptarButton.addActionListener(this);
		cancelarButton = new JButton("Cancelar");
		cancelarButton.addActionListener(this);
		agregarDatosButton = new JButton("Agregar dato");
		agregarDatosButton.addActionListener(this);
		
		cont.removeAll();
		cont.setLayout(new BorderLayout());
		cont.add(scrollPane, BorderLayout.CENTER);
		cont.add(panelButtons, BorderLayout.EAST);
		
		simbolos = new LinkedList<Character>();
		reglasImplicantes = new LinkedList<String>();
		reglasImplicadas = new LinkedList<String>();
		
		textSimbolos = new LinkedList<JTextField>();
		textReglasImplicante = new LinkedList<JTextField>();
		textReglasImplicadas = new LinkedList<JTextField>();
		
		for (int i=0;i<cantidadSimbolos;i++){
			textSimbolos.add(new JTextField(5));
			simbolos.add(new Character('a'));
		}
		for(int i=0;i<cantidadReglas;i++){
			textReglasImplicante.add(new JTextField(5));
			textReglasImplicadas.add(new JTextField(15));
			reglasImplicantes.add("");
			reglasImplicadas.add("");
		}
	}
	
	/* Dependiendo del estado del programa ubica los componentes de la interfaz
	 * en los páneles de la ventana y la actualiza para poder mostrar los cuadros
	 * de texto correspondientes al estado*/
	private void setUI(){
		if (estado == 0){//cuadro la interfaz del frame para mostrar los input de los símbolos
			
			panelButtons.removeAll();
			panelButtons.setLayout(new BorderLayout());

			panelButtons.add(aceptarButton, BorderLayout.NORTH);
			panelButtons.add(agregarDatosButton, BorderLayout.CENTER);
			panelButtons.add(cancelarButton, BorderLayout.SOUTH);
			
			panelInputs.removeAll();
			panelInputs.setLayout(new GridLayout(cantidadSimbolos, 2));
			
			for(int i=0; i<cantidadSimbolos;i++){
				panelInputs.add(new JLabel("Símbolo "+(i+1)));
				panelInputs.add(textSimbolos.get(i));
			}
			
			this.setBounds(400, 200, 400, (cantidadSimbolos*40>400)?400:cantidadSimbolos*40);
			
			panelButtons.updateUI();
			panelInputs.updateUI();
			
		}
		else if (estado == 1){//cuadro la interfaz del frame para mostrar los input de las reglas
			
			panelButtons.removeAll();
			panelButtons.setLayout(new BorderLayout());

			panelButtons.add(aceptarButton, BorderLayout.NORTH);
			panelButtons.add(agregarDatosButton, BorderLayout.CENTER);
			panelButtons.add(cancelarButton, BorderLayout.SOUTH);
			
			panelInputs.removeAll();
			panelInputs.setLayout(new GridLayout(cantidadReglas+1, 4));
			
			panelInputs.add(new JLabel(""));
			panelInputs.add(new JLabel("Estados"));
			panelInputs.add(new JLabel(""));
			panelInputs.add(new JLabel("Implicaciones"));
			
			for(int i=0; i<cantidadReglas;i++){
				panelInputs.add(new JLabel("Regla "+(i+1)));
				panelInputs.add(textReglasImplicante.get(i));
				panelInputs.add(new JLabel(" => "));
				panelInputs.add(textReglasImplicadas.get(i));
			}
			
			this.setBounds(400, 200, 400, (cantidadReglas*40>400)?400:cantidadReglas*40);
			
			panelButtons.updateUI();
			panelInputs.updateUI();
			
		}
		else if(estado == 2){
			mostrarDatosGuardados();
		}
		else if(estado == 3){
			System.exit(0);
		}
		
		panelButtons.updateUI();
		panelInputs.updateUI();
	}
	
	/* Dependiendo del estado agrega un objeto más a las listas de los símbolos o
	 * las reglas y sus correspondientes cuadros de texto (JTextField)*/
	private void agregarDatos(){
		if(estado == 0){
			simbolos.add(new Character('a'));
			textSimbolos.add(new JTextField(5));
			cantidadSimbolos++;
		}
		else if(estado == 1){
			reglasImplicantes.add("");
			reglasImplicadas.add("");
			textReglasImplicante.add(new JTextField(5));
			textReglasImplicadas.add(new JTextField(15));
			cantidadReglas++;
		}
		else if(estado == 2){
			JOptionPane.showMessageDialog(null, "No hay nada por agregar en el estado "+estado, "Alerta", JOptionPane.INFORMATION_MESSAGE);
		}
		else if(estado == 3){
			JOptionPane.showMessageDialog(null, "No hay nada por agregar en el estado "+estado, "Alerta", JOptionPane.INFORMATION_MESSAGE);
		}
		setUI();
	}
	
	/* Dependiendo del estado del programa pasa los datos de los cuadros de texto a
	 * sus correspondientes listas encadenadas de información y cambia el estado*/
	private boolean guardarDatos(){
		if(estado == 0){
			for(int i=0; i<cantidadSimbolos;i++){
				if(textSimbolos.get(i).getText().length()!=1){
					JOptionPane.showMessageDialog(null, "El símolo "+(i+1)+" tiene más de un caracter", "Error", JOptionPane.ERROR_MESSAGE);
					return false;
				}
				
				simbolos.set(i, new Character(textSimbolos.get(i).getText().charAt(0)));
			}
		}
		else if(estado == 1){
			for(int i=0; i<cantidadReglas;i++){
				if(textReglasImplicante.get(i).getText().length()!=1){
					JOptionPane.showMessageDialog(null, "El simbolo representante de la regla "+(i+1)+" tiene más de un dígito", "Error", JOptionPane.ERROR_MESSAGE);
					return false;
				}
				if(esOperador(textReglasImplicante.get(i).getText().charAt(0))){
					JOptionPane.showMessageDialog(null, "El símolo representante de la regla "+(i+1)+" es un operador o un número", "Error", JOptionPane.ERROR_MESSAGE);
					return false;
				}
				reglasImplicantes.set(i, ""+textReglasImplicante.get(i).getText().charAt(0));
			}
			for(int i=0;i<cantidadReglas;i++){
				String implicada = textReglasImplicadas.get(i).getText();
				if(implicada.length()==0){
					JOptionPane.showMessageDialog(null, "La regla "+(i+1)+" presenta su parte derecha de la igualdad vacía", "Error", JOptionPane.ERROR_MESSAGE);
					return false;
				}
				for(int j=0;j<implicada.length();j++){
					char caracter = implicada.charAt(j);
						boolean esta = false;
						for(int k=0;k<simbolos.size() && !esta;k++){
							esta = simbolos.get(k).charValue()==caracter;
						}
						for(int k=0;k<reglasImplicantes.size() && !esta;k++){
							esta = reglasImplicantes.get(k).charAt(0)==caracter;
						}
						if(!esta){
							JOptionPane.showMessageDialog(null, "El el simbolo '"+caracter+"' en la regla "+(i+1)+" no es un símbolo del\nlenguaje ni es representante de una regla", "Error", JOptionPane.ERROR_MESSAGE);
							return false;
						}
				}
				reglasImplicadas.set(i, textReglasImplicadas.get(i).getText());
			}
		}
		else if(estado == 2){
			JOptionPane.showMessageDialog(null, "No hay nada por guardar en el estado 2", "Alerta", JOptionPane.INFORMATION_MESSAGE);
		}
		else if(estado == 3){
			JOptionPane.showMessageDialog(null, "No hay nada por guardar en el estado 3", "Alerta", JOptionPane.INFORMATION_MESSAGE);
		}
		estado++;
		estado%=3;
		return true;
	}
	
	/* Revisa si un caracter es operador o número para evitar que un estado
	 * se llame con estos símbolos reservados*/
	public boolean esOperador(char c){
		if (c == '+' || c == '*' || c == '^' || c == '0' || c == '1' || c == '2' || c == '3'
			|| c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c == '|')
			return true;
		return false;
	}
	
	/* Muestra los datos guardados en las listas encadenadas en una tabla (JTable)*/
	public void mostrarDatosGuardados(){
		HashSet<String> set = new HashSet<String>();
		for(int i=0;i<cantidadReglas;i++){
			set.add(reglasImplicantes.get(i));
		}
		
		DefaultTableModel model = new DefaultTableModel();
		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		
		Object reglasImplicadasMinimizadas[] = new Object[set.size()];
		reglasImplicadasMinimizadas = set.toArray();
		
		String datos[] = new String[1+cantidadSimbolos+1+set.size()];
		
		int j=0;
		
		datos[j++] = "Identificador";
		for(int i=0;i<cantidadSimbolos;i++)
			datos[j++] = ""+simbolos.get(i);
		
		datos[j++] = "FDC";
		
		for(int i=0;i<set.size();i++)
			datos[j++] = reglasImplicadasMinimizadas[i].toString();
		
		String cabezotes[] = new String[datos.length];
		cabezotes = datos.clone();
		
		for(int i=0;i<datos.length;i++){
			model.addColumn(cabezotes[i]);
			datos[i]="";
		}
		
		for(int i=0;i<cantidadReglas;i++){
			for(int k=0;k<datos.length;k++)
				datos[k]="";
			datos[0] = ""+(i+1);
			for(int k=1;k<cabezotes.length;k++){
				for(int l=0;l<reglasImplicadas.get(i).length();l++){
					if((""+reglasImplicadas.get(i).charAt(l)).equals(cabezotes[k]))
						datos[k] = ""+(l+1);
				}
			}
			model.addRow(datos);
		}
		
		this.setVisible(false);
		
		JOptionPane.showMessageDialog(null, scrollPane, "Datos Guardados", JOptionPane.PLAIN_MESSAGE);
		
		System.exit(0);
	}
	
	@Override
	/*Revisa las acciones de los botones para el llamado a los métodos*/
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(cancelarButton)){
			JOptionPane.showMessageDialog(null, "Ha seleecionado salir", "Salida", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
		else if(e.getSource().equals(aceptarButton)){
			boolean exito = guardarDatos();
			if(exito)
				setUI();
		}
		else if(e.getSource().equals(agregarDatosButton)){
			agregarDatos();
		}
	}
	
}
