package misc;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class GramaticasI extends JFrame implements ActionListener{

	private static int state;
	private static int cantidadSimbolos;
	private static int cantidadReglas;
	
	private static char simbolos[];
	private static char reglasImplicantes[];
	private static String reglasImplicadas[];
	
	private static JTextField textSimbolos[];
	private static JTextField textReglasImplicante[];
	private static JTextField textReglasImplicadas[];
	
	private static Container cont;
	
	private static JPanel panelButtons;
	private static JPanel panelInputs;
	
	private static JButton aceptarButton;
	private static JButton cancelarButton;
	
	public static void main(String[] args) {
		boolean exito = false;
		while(!exito){
			try{
				cantidadSimbolos = Integer.parseInt(JOptionPane.showInputDialog("Ingresa la cantidad de s�mbolos a usar:"));
				cantidadReglas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de reglas a definir:"));
				exito = true;
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(null, "Ha ingresado un dato err�neo", "Error", JOptionPane.ERROR_MESSAGE);
				exito = false;
			}
		}
		
		GramaticasI frame = new GramaticasI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		//JOptionPane.showMessageDialog(null, cantidadSimbolos+", "+cantidadReglas, "Entrada", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public GramaticasI(){
		super("Gram�ticas");
		inicializar();
		setUI();
	}

	private void inicializar(){
		//inicio datos, variables, botones y p�neles
		state = 0;
		
		cont = this.getContentPane();
		
		panelButtons = new JPanel();
		panelInputs = new JPanel();
		
		aceptarButton = new JButton("Aceptar");
		aceptarButton.addActionListener(this);
		cancelarButton = new JButton("Cancelar");
		cancelarButton.addActionListener(this);
		
		cont.removeAll();
		cont.setLayout(new BorderLayout());
		cont.add(panelInputs, BorderLayout.CENTER);
		cont.add(panelButtons, BorderLayout.SOUTH);
		
		simbolos = new char[cantidadSimbolos];
		reglasImplicantes = new char[cantidadReglas];
		reglasImplicadas = new String[cantidadReglas];
		
		textSimbolos = new JTextField[cantidadSimbolos];
		textReglasImplicante = new JTextField[cantidadReglas];
		textReglasImplicadas = new JTextField[cantidadReglas];
		
		for (int i=0;i<cantidadSimbolos;i++){
			textSimbolos[i] = new JTextField(5);
		}
		for(int i=0;i<cantidadReglas;i++){
			textReglasImplicante[i] = new JTextField(5);
			textReglasImplicadas[i] = new JTextField(15);
		}
	}
	
	private void setUI(){
		if (state == 0){//cuadro la interfaz del frame para mostrar los input de los s�mbolos
			
			panelButtons.removeAll();
			panelButtons.setLayout(new BorderLayout());

			panelButtons.add(aceptarButton, BorderLayout.WEST);
			panelButtons.add(cancelarButton, BorderLayout.EAST);
			
			panelInputs.removeAll();
			panelInputs.setLayout(new GridLayout(cantidadSimbolos, 2));
			
			for(int i=0; i<cantidadSimbolos;i++){
				panelInputs.add(new JLabel("S�mbolo "+(i+1)));
				panelInputs.add(textSimbolos[i]);
			}
			
			this.setBounds(400, 200, 400, 20+cantidadSimbolos*30);
			
			panelButtons.updateUI();
			panelInputs.updateUI();
			
		}
		else if (state == 1){//cuadro la intergaz del frame para mostrar los input de los s�mbolos
			
			panelButtons.removeAll();
			panelButtons.setLayout(new BorderLayout());

			panelButtons.add(aceptarButton, BorderLayout.WEST);
			panelButtons.add(cancelarButton, BorderLayout.EAST);
			
			panelInputs.removeAll();
			panelInputs.setLayout(new GridLayout(cantidadReglas, 4));
			
			for(int i=0; i<cantidadReglas;i++){
				panelInputs.add(new JLabel("Regla "+(i+1)));
				panelInputs.add(textReglasImplicante[i]);
				panelInputs.add(new JLabel(" = "));
				panelInputs.add(textReglasImplicadas[i]);
			}
			
			this.setBounds(400, 200, 400, 20+cantidadReglas*30);
			
			panelButtons.updateUI();
			panelInputs.updateUI();
			
		}
		else if(state == 2){
			mostrarDatosGuardados();
		}
		else if(state == 3){
			System.exit(0);
		}
		
		panelButtons.updateUI();
		panelInputs.updateUI();
	}
	
	private boolean guardarDatos(){
		if(state == 0){
			for(int i=0; i<cantidadSimbolos;i++){
				if(textSimbolos[i].getText().length()!=1){
					JOptionPane.showMessageDialog(null, "El s�molo "+(i+1)+" no tiene un d�gito", "Error", JOptionPane.ERROR_MESSAGE);
					return false;
				}
				if(esOperador(textSimbolos[i].getText().charAt(0))){
					JOptionPane.showMessageDialog(null, "El s�molo "+(i+1)+" es un operador o un n�mero", "Error", JOptionPane.ERROR_MESSAGE);
					return false;
				}
				simbolos[i] = textSimbolos[i].getText().charAt(0);
			}
		}
		else if(state == 1){
			for(int i=0; i<cantidadReglas;i++){
				if(textReglasImplicante[i].getText().length()!=1){
					JOptionPane.showMessageDialog(null, "El simbolo representante de la regla "+(i+1)+" tiene m�s de un d�gito", "Error", JOptionPane.ERROR_MESSAGE);
					return false;
				}
				if(esOperador(textReglasImplicante[i].getText().charAt(0))){
					JOptionPane.showMessageDialog(null, "El s�molo representante de la regla "+(i+1)+" es un operador o un n�mero", "Error", JOptionPane.ERROR_MESSAGE);
					return false;
				}
				reglasImplicantes[i] =  new Character(textReglasImplicante[i].getText().charAt(0));
			}
			for(int i=0;i<cantidadReglas;i++){
				String implicada = textReglasImplicadas[i].getText();
				if(implicada.length()==0){
					JOptionPane.showMessageDialog(null, "La regla "+(i+1)+" presenta su parte derecha de la igualdad vac�a", "Error", JOptionPane.ERROR_MESSAGE);
					return false;
				}
				for(int j=0;j<implicada.length();j++){
					char caracter = implicada.charAt(j);
					if(!esOperador(caracter)){
						boolean esta = false;
						for(int k=0;k<simbolos.length && !esta;k++){
							esta = simbolos[k]==caracter;
						}
						for(int k=0;k<reglasImplicantes.length && !esta;k++){
							esta = reglasImplicantes[k]==caracter;
						}
						if(!esta){
							JOptionPane.showMessageDialog(null, "El el simbolo '"+caracter+"' en la regla "+(i+1)+" no es un s�mbolo del\nlenguaje ni es representante de una regla", "Error", JOptionPane.ERROR_MESSAGE);
							return false;
						}
					}
				}
				reglasImplicadas[i] = textReglasImplicadas[i].getText();
			}
		}
		else if(state == 2){
			JOptionPane.showMessageDialog(null, "No hay nada por guardar en el estado 2", "Alerta", JOptionPane.INFORMATION_MESSAGE);
		}
		else if(state == 3){
			JOptionPane.showMessageDialog(null, "No hay nada por guardar en el estado 3", "Alerta", JOptionPane.INFORMATION_MESSAGE);
		}
		state++;
		return true;
	}
	
	public boolean esOperador(char c){
		if (c == '+' || c == '*' || c == '^' || c == '0' || c == '1' || c == '2' || c == '3'
			|| c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c == '|')
			return true;
		return false;
	}
	
	public void mostrarDatosGuardados(){
		JTextArea area = new JTextArea();
		area.append("Lenguaje:\n\n");
		for(int i=0;i<cantidadSimbolos;i++)
			area.append((i+1)+". "+simbolos[i]+"\n");
		area.append("\nReglas de transci�n:\n\n");
		for(int i=0;i<cantidadReglas;i++){
			area.append((i+1)+". "+reglasImplicantes[i]+" = "+reglasImplicadas[i]+"\n");
		}
		area.setEditable(false);
		JOptionPane.showMessageDialog(null, area, "Datos Guardados", JOptionPane.PLAIN_MESSAGE);
		System.exit(0);
	}
	
	@Override
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
	}
	
}
