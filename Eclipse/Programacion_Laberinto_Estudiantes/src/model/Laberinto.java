package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class Laberinto {
	// tablero
	public static int MAX = 15;
	private String[][] tablero = new String[MAX][MAX];
	private boolean[][] visitadas = new boolean[MAX][MAX];
	private int salidax = 0;
	private int saliday = 0;
	private int llegadax = 0;
	private int llegaday = 0;
	// convenciones
	public static String VACIO = "1";
	public static String MURO = "0";
	public static String SALIDA = "2";
	public static String LLEGADA = "3";

	public static String CONVENCION_ARRIBA = "^";
	public static String CONVENCION_DERECHA = ">";
	public static String CONVENCION_ABAJO = "V";
	public static String CONVENCION_IZQUIERDA = "<";
	public static String[] DIRECCIONES = { CONVENCION_ARRIBA,
			CONVENCION_DERECHA, CONVENCION_ABAJO, CONVENCION_IZQUIERDA };

	// direcciones
	public static int DIRECCION_ARRIBA = 0;
	public static int DIRECCION_DERECHA = 1;
	public static int DIRECCION_ABAJO = 2;
	public static int DIRECCION_IZQUIERDA = 3;
	public static int NUM_DIRECCIONES = 4;

	// arreglo direccional
	public static int[] DESPLAZAMIENTOS_X = { -1, 0, 1, 0 };
	public static int[] DESPLAZAMIENTOS_Y = { 0, 1, 0, -1 };

	/**
	 * resolver Este metodo tiene la responsabilidad de llamar al metodo
	 * resolverRecursivo inicializando los parametros en las coordenadas de
	 * salida y llegada e inicializar la matriz de casillas visitadas.
	 */
	public void resolver() {
		//INICIO CODIGO ESTUDIANTES PUNTO 1
		
		resolverRecursivo(salidax, saliday, llegadax,
				llegaday, "("+salidax+", "+saliday+")");
		
		// FIN CODIGO ESTUDIANTES PUNTO 1
	}

	/**
	 * resolverRecursivo Este metodo tiene la responsabilidad de hallar el
	 * camino correcto del laberinto desde la posicion xcoord, ycoord retornando
	 * X si no es posible (si ya fue visitada o es un muro) o alguna de las
	 * convenciones de direccion si es posible.
	 * 
	 * @param xcoord
	 *            coordenada x de salida
	 * @param ycoord
	 *            coordenada y de salida
	 * @param llegadax
	 *            coordenada x de llegada
	 * @param llegaday
	 *            coordenada y de llegada
	 * @param camino
	 *            camino previo (lo que lleva) hasta el momento de la invocacion
	 * @return retorna "X" si el camino es incorrecto , retorna "O" si llegó al
	 *         destino.
	 * 
	 */
	private String resolverRecursivo(int xcoord, int ycoord, int llegadax,
			int llegaday, String camino) {
		String lab = "";
		for(int i=0;i<MAX;i++){
			for(int j=0;j<MAX;j++){
				if(visitadas[i][j])
					lab+=("*");
				else if(tablero[i][j].equals(MURO))
					lab+=("m");
				else if(i==llegadax && j==llegaday)
					lab+=("F");
				else
					lab+=("_");
			}
			lab+=("|\n");
		}
		//JOptionPane.showMessageDialog(null, lab, "Lab", JOptionPane.PLAIN_MESSAGE);
		
		// caso base: por fuera de la matriz
		//INICIO CODIGO ESTUDIANTES PUNTO 2
		if(xcoord<0 || xcoord>=MAX || ycoord<0 || ycoord>=MAX){
			return "X";
		}
		// FIN CODIGO ESTUDIANTES PUNTO 2

		// caso base : si esta casilla esta llena
		// INICIO CODIGO ESTUDIANTES PUNTO 4
		if(tablero[xcoord][ycoord].equals(MURO)){
			return "X";
		}
		// FIN CODIGO ESTUDIANTES PUNTO 4
		
		// caso base: YA VISITADA O MARCAR VISITADA
		//INICIO CODIGO ESTUDIANTES PUNTO 3
		if(visitadas[xcoord][ycoord]){
			return "X";
		}
		else{
			visitadas[xcoord][ycoord] = true;
		}
		// FIN CODIGO ESTUDIANTES PUNTO 3


		// caso base 2: llegué
		// TODO: INICIO CODIGO ESTUDIANTES PUNTO 5
		if(llegadax==xcoord && llegaday==ycoord){
			JOptionPane.showMessageDialog(null, lab, "Lab", JOptionPane.PLAIN_MESSAGE);
			System.out.println("Camino: "+camino+", ("+xcoord+", "+ycoord+")");
			return "O";
		}
		// FIN CODIGO ESTUDIANTES PUNTO 5

		// caso recursivo : PARA TODAS LAS DIRECCIONES
		// TODO: INICIO CODIGO ESTUDIANTES PUNTO 6
		
		if    (resolverRecursivo(xcoord-1, ycoord, llegadax, llegaday, camino+", ("+xcoord+", "+ycoord+")").equals("O")
			|| resolverRecursivo(xcoord+1, ycoord, llegadax, llegaday, camino+", ("+xcoord+", "+ycoord+")").equals("O")
			|| resolverRecursivo(xcoord, ycoord-1, llegadax, llegaday, camino+", ("+xcoord+", "+ycoord+")").equals("O")
			|| resolverRecursivo(xcoord, ycoord+1, llegadax, llegaday, camino+", ("+xcoord+", "+ycoord+")").equals("O")){
			return "O";
		}
		
		// FIN CODIGO ESTUDIANTES PUNTO 6
		return "X";

	}

	/**
	 * salvarArchivo Este metodo tiene la responsabilidad de guardar un
	 * laberinto dado un nombre de archivo
	 * 
	 * @param nombre
	 */
	public void salvarArchivo(String nombre) {
		try {
			FileWriter outFile = new FileWriter(nombre);
			PrintWriter out = new PrintWriter(outFile);

			for (int i = 0; i < MAX; i++) {
				for (int j = 0; j < MAX; j++) {
					out.print(tablero[i][j]);
				}
				out.println("");
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * cargarArchivo: Este metodo tiene la responsabilidad de cargar un tablero
	 * del laberinto dada una referencia a un archivo de texto
	 * 
	 * @param archivo
	 * @return
	 */
	public boolean cargarArchivo(File archivo) {
		try {
			BufferedReader lector = new BufferedReader(new FileReader(archivo));
			String linea = "";
			int i = 0;
			while ((linea = lector.readLine()) != null) {
				for (int j = 0; j < linea.length(); j++) {
					char c = linea.charAt(j);
					if (c == '2') {
						salidax = i;
						saliday = j;
					}
					if (c == '3') {
						llegadax = i;
						llegaday = j;
					}
					tablero[i][j] = (c + "");
					visitadas[i][j] = false;
				}
				i++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("Archivo no encontrado");
			return false;
		} catch (IOException e) {
			System.out.println("Error de lectura");
			return false;
		} catch (NullPointerException e) {
			System.out.println("Tamaño del laberinto descrito "
					+ "en el archivo incorrecto");
		}
		System.out.println("Carga del archivo exitosa!");
		return true;
	}

	/**
	 * getTablero
	 * 
	 * @return
	 */
	public String[][] getTablero() {
		return tablero;
	}

	/**
	 * setTablero
	 * 
	 * @param tablero
	 */
	public void setTablero(String[][] tablero) {
		this.tablero = tablero;
	}

}
