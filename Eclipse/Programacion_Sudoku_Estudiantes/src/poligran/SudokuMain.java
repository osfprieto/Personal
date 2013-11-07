package poligran;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SudokuMain 
{
	public static int TAMANOTABLERO = 9;
	public static int [][] tablero= new int [TAMANOTABLERO][TAMANOTABLERO];
	public static int NUMTABLEROSINICIALES = 10000;
	public static String[] tablerosIniciales = new String[NUMTABLEROSINICIALES];
	public static int MAXOPCIONES = 3;
	public static int TAMANOZONA = 3;
	private static void jugarCasilla() 
	{
		//Primera parte: Solicitud de datos al usuario
		System.out.println("Ingrese fila (entre 1 y 9)");
		int fila = new Scanner(System.in).nextInt() -1;
		System.out.println("Ingrese columna (entre 1 y 9)");
		int columna = new Scanner(System.in).nextInt() -1;
		System.out.println("Ingrese valor (entre 1 y 9) o cero para borrar");
		int valor = new Scanner(System.in).nextInt();
		
		//Segunda parte: Asignar el valor al tablero
		tablero[fila][columna] = valor;
		
		//Tercera parte: Revision de errores
		boolean hayErrores = hayErroresFilaColumnaZona(fila, columna);
		if(hayErrores)
		{
			System.out.println("Hay errores en su juego, revise que no haya repetido digitos en alguna fila, columna o zona");
		}
		//Cuarta parte: Revision de ganador
		boolean hayGanador = validarGanador();
		if(hayGanador)
		{
			System.out.println("Ha ganado!");
		}
	}
	
	
	public static void seleccionarTablero()
	{
		//INICIO CODIGO ESTUDIANTE PUNTO 1
		int n=SudokuMain.NUMTABLEROSINICIALES, i, j, cont=0;
		Scanner sc = new Scanner(System.in);
		while(n>SudokuMain.NUMTABLEROSINICIALES-1 || n<0){
			System.out.println("Ingrese el número del tablero");
			n = sc.nextInt()-1;
		}
		for(i=0;i<SudokuMain.TAMANOTABLERO;i++)
			for(j=0;j<SudokuMain.TAMANOTABLERO;j++)
				tablero[i][j] = (int)(tablerosIniciales[n].charAt(cont++)-'0');
		//FIN CODIGO ESTUDIANTE PUNTO 1
	}
	
	
	private static boolean validarGanador() 
	{
		//INICIO CODIGO ESTUDIANTE PUNTO 2
	
		if(contarCasillasLlenas()==81){
			boolean hayErrores = false;
			for(int i=0;i<SudokuMain.TAMANOTABLERO && !hayErrores;i++){
				for(int j=0;j<SudokuMain.TAMANOTABLERO && !hayErrores;j++){
					hayErrores = hayErroresFilaColumnaZona(i, j);
				}
			}
			boolean gana = !hayErrores;
			return gana;
		}
		return false;
		//FIN CODIGO ESTUDIANTE PUNTO 2
	}
	
	private static boolean hayErroresFilaColumnaZona(int fila, int columna) 
	{
		//revisar fila
		boolean erroresfila = hayErrorFila( fila);
		//revisar columna
		boolean errorescolumna = hayErrorColumna( columna);
		//revisar zona
		boolean erroreszona = hayErrorZona(fila, columna);
		if(erroresfila || errorescolumna || erroreszona)
		{
			return true;// esto implica que hay algun error
		}
		return false;// esto implica que no hay ningun error
	}
	
	private static boolean hayErrorZona(int fila, int columna) 
	{
		//inicio de zona: 0, 3, 6
		//tamano de zona 3
		
		//declaracion de los indices que recorreran la submatriz de la zona
		int filainicial = 3*fila;
		int columnainicial =3*columna;
		
		//ubicar los indices en los puntos iniciales de la zona a evaluar
		//INICIO CODIGO ESTUDIANTE PUNTO 3
		
		boolean []ocurrencias = new boolean[10];
		for(int i =0 ; i< TAMANOZONA ; i++)
		{
			for(int j=0;j<TAMANOZONA;j++){
				int valorActual = tablero[filainicial+i][columnainicial+j];
				if(valorActual !=0)
				{
					if(ocurrencias[valorActual] == false)
					{
						ocurrencias[valorActual]=true;
					}
					else
					{
						System.out.println("Error revisando zonas: Valor repetido en la coordenada [fila:"+(filainicial+i)+",columna:"+(columnainicial+j)+"]");
						return true;
					}
				}
			}
			
		}
		return false;
		//FIN INICIO CODIGO ESTUDIANTE PUNTO 3
	}
	
	private static boolean hayErrorColumna(int columna) 
	{
		//INICIO CODIGO ESTUDIANTE PUNTO 4
		boolean []ocurrencias = new boolean[10];
		for(int filaActual =0 ; filaActual< TAMANOTABLERO ; filaActual++)
		{
			int valorActual = tablero[filaActual][columna];
			if(valorActual !=0)
			{
				if(ocurrencias[valorActual] == false)
				{
					ocurrencias[valorActual]=true;
				}
				else
				{
					System.out.println("Error revisando columnas: Valor repetido en la coordenada [fila:"+filaActual+",columna:"+columna+"]");
					return true;
				}
			}
			
		}
		return false;
		//FIN CODIGO ESTUDIANTE PUNTO 4
	}
	
	private static boolean hayErrorFila(int fila) 
	{
		boolean []ocurrencias = new boolean[10];
		for(int columnaActual =0 ; columnaActual< TAMANOTABLERO ; columnaActual++)
		{
			int valorActual = tablero[fila][columnaActual];
			if(valorActual !=0)
			{
				if(ocurrencias[valorActual] == false)
				{
					ocurrencias[valorActual]=true;
				}
				else
				{
					System.out.println("Error revisando filas: Valor repetido en la coordenada [fila:"+fila+",columna:"+columnaActual+"]");
					return true;
				}
			}
			
		}
		return false;
		
	}
	
	public static void imprimirTablero ()
	{
		System.out.println("Impresion del tablero: Hay "+contarCasillasLlenas()+" casillas llenas");
		System.out.println("    "+" C1  "+" C2  "+" C3  "+" C4  "+" C5  "+" C6  "+" C7  "+" C8  "+" C9  ");
		for (int i = 0; i < TAMANOTABLERO; i++) 
		{
			System.out.print("F"+(i+1)+"  ");
			for (int j = 0; j < TAMANOTABLERO; j++) 
			{
				if(tablero[i][j]!=0)
				{
					System.out.print("| " +tablero[i][j]+" |");
				}
				else
				{
					System.out.print("| " +" "+" |");
				}
				
			}
			System.out.println();
			System.out.println("    ---------------------------------------------");
		}
	}
	
	public static void main(String[] args) 
	{
		reiniciar();
		menu();
		
	}
	
	public static void reiniciar()
	{
		cargarNivel();
		seleccionarTablero();
	}
	
	public static void cargarNivel()
	{
		
		try 
		{
			BufferedReader lector =  new BufferedReader(new FileReader("./data/"+"boards.sdk"));
			String linea = "";
			int contadorLineas = 0;
			while (( linea = lector.readLine()) != null && contadorLineas < NUMTABLEROSINICIALES)
			{
				tablerosIniciales[contadorLineas]= linea;
				contadorLineas++;
			}
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("Archivo no encontrado");
		} 
		catch (IOException e) 
		{
			System.out.println("Error de lectura");
		}
		System.out.println("Carga del archivo exitosa!");
	}
	
	public static void menu()
	{
		System.out.println("Sudoku!");
		int opcion  = -1;
		while(opcion < MAXOPCIONES)
		{
			imprimirTablero();
			System.out.println("Ingrese opcion");
			
			System.out.println("1. Jugar una casilla");
			System.out.println("2. Reiniciar Juego - Seleccionar Tablero");
			System.out.println("3. Salir");
			
			opcion =  new Scanner(System.in).nextInt();
			if(opcion == 2)
			{
				seleccionarTablero();
			}
			else if (opcion == 1)
			{
				jugarCasilla();
			}
		}
	}
	
	public static int contarCasillasLlenas()
	{
		//INICIO CODIGO ESTUDIANTE PUNTO 5 (BONO)
		int casillasLlenas=0;
		
		for(int i=0;i<TAMANOTABLERO;i++)
			for(int j=0;j<TAMANOTABLERO;j++)
				if(tablero[i][j]>0)
					casillasLlenas++;
		
		return casillasLlenas;
		//FIN CODIGO ESTUDIANTE PUNTO 5 (BONO)
	}
}
