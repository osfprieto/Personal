package varios;
import java.util.Scanner;


/**
 * @author danielsantamaria
 *
 */
public class TriquiEstaticoVersionEstudiantes 
{
	//maximo tama�o del tablero
	public static int MAX = 3;
	//maximo numero de opciones del menu
	public static int MAXOPCIONES=3;
	
	//variables que representan el tablero: 
	//convencion: el primer digito del nombre de la variable tablero indica la fila de la casilla, el segundo nombre indica la columna
	
	/*
		    0   1   2
		    
		0   X | X | X
		   -----------
		1   O | O | O    De esta manera, la variable tablero00 es la casilla ubicada en la fila 0 y columna 0 del tablero 
		   -----------   En el ejemplo, tablero00 tiene valor 'X'
		2   X | O | X
		 
		* */
	public static char tablero00=' ';
	public static char tablero01=' ';
	public static char tablero02=' ';
	public static char tablero10=' ';
	public static char tablero11=' ';
	public static char tablero12=' ';
	public static char tablero20=' ';
	public static char tablero21=' ';
	public static char tablero22=' ';
	
	//variable que guarda el turno actual
	public static char turno='X';
	//variable que guarda el ganador de la partida
	public static char ganador=' ';
	
	
	/**
	 * El metodo reiniciar tiene como resposabilidad: Iniciar un nuevo juego
	 * 
	 * retorna: void
	 * Precondiciones: Ninguna
	 * Postcondiciones: Que el tablero quede inicializado con todas las casillas en ' ', el turno para 'X' y el ganador en ' '
	 */
	//Punto 1 (Asingacion y metodos): declare un metodo "reiniciar" que inicialice los valores del tablero, las casillas vacias deben tener valor ' ', 
	// el turno inicial debe ser 'X' y el ganador debe ser ' ' 
	// tenga en cuenta que las variables turno y ganador ya existen
	// tenga en cuenta que las variables que represenan el tablero ya existen y son: 
	// tablero01, tablero02, tablero10, tablero11, tablero12, tablero20, tablero21 y tablero22
	
	//Inicio codigo estudiante punto 1
	public static void reiniciar ()
	{
		tablero00=' ';
		tablero01=' ';
		tablero02=' ';
		tablero10=' ';
		tablero11=' ';
		tablero12=' ';
		tablero20=' ';
		tablero21=' ';
		tablero22=' ';
		turno = 'X';
		ganador = ' ';
	}
	//Fin codigo estudiante punto 1
	
	
	/**
	 * El metodo main tiene como resposabilidad: iniciar el programa
	 * Recibe los siguientes parametros
	 * @param args
	 * retorna:
	 * void
	 * Precondiciones: No hay precodiciones particulares
	 * Postcondiciones: Inicia el juego y invoca el menu
	 */
	public static void main(String[] args) 
	{
		reiniciar();
		menu();
	}


	/**
	 * El método jugarTurno tiene como resposabilidad: pedir al usuario las coordenadas de juego
	 * , validar si son correctas, asignar la casilla seleccionada al turno activo y revisar si hay un ganador.
	 * Recibe los siguientes parametros
	 * retorna:
	 * void
	 * Precondiciones: Ninguna
	 * Postcondiciones: si las coordenadas enviadas son validas, se debe asignar el valor del turno actual en la 
	 * casilla correspondiente del tablero, si hay un ganador, se debe asignar el turno ganador.
	 */
	@SuppressWarnings("resource")
	private static void jugarTurno() 
	{
		Scanner teclado = new Scanner (System.in);
		int fila=-1;
		int columna=-1;
		
		// Punto 2 (Entrada/Salida): solicite al usuario la fila y la columna donde va a jugar.
		// El usuario podrá insertar valores entre 1 y 3, pero usted debe reducir en 1 el valor que reciba del usuario
		
		//Inicio codigo estudiante punto 2
		System.out.println("Ingrese la fila");
		fila = teclado.nextInt();
		System.out.println("Ingrese la columna");
		columna = teclado.nextInt();
		fila = fila -1;
		columna = columna -1;
		//Fin codigo estudiante punto 2
		
		
		if(fila < 0 || fila > MAX-1 || columna < 0 || columna > MAX-1 )
		{
			System.out.println("Coordenadas invalidas");
			return;
		}
		if(pedirValorTablero(fila, columna)==' ')
		{
			asignarValorTablero(fila, columna, turno);
		}
		else
		{
			System.out.println("Jugada invalida");
			return;
		}
		if (turno=='X')
		{
			turno='O';
		}
		else if(turno=='O')
		{
			turno ='X';
		}
		ganador = validarGanador();
		if(ganador == 'E')
		{
			System.out.println("Empate!");
			return;
		}
		if(ganador!=' ')
		{
			System.out.println("Gana: " + ganador);
		}
	}


	/**
	 * El metodo pedirValorTablero tiene como resposabilidad: retornar el valor de una casilla dada su fila y columna
	 * Recibe los siguientes parametros
	 * @param fila la fila del tablero donde se ubica la casilla
	 * @param columna la columna del tablero donde se ubica la casilla
	 * @return char el valor del tablero en la casilla seleccionada
	 * Precondiciones: fila y columna son enteros positivos
	 * Postcondiciones: si fila y columna se encuentran dentro de los rangos (0 a 2) se retorna el valor, de lo contrario se retorna 'I'
	 */
	private static char pedirValorTablero(int fila, int columna) 
	{
		//Punto 3 (Condicionales): retornar un valor del tablero segun la fila y columna especficadas por el usuario. tenga en cuenta que 
		//las variables que represenan el tablero ya existen y son: 
		//tablero01, tablero02, tablero10, tablero11, tablero12, tablero20, tablero21 y tablero22
		
		
		//Inicio codigo estudiante punto 3
		if(fila==0)
		{
			if(columna == 0)
			{
				return tablero00;
			}
			else if (columna == 1)
			{
				return tablero01;
			}
			else if (columna == 2)
			{
				return tablero02;
			}
		}
		else if (fila == 1)
			{
				if(columna == 0)
				{
					return tablero10;
				}
				else if (columna == 1)
				{
					return tablero11;
				}
				else if (columna == 2)
				{
					return tablero12;
				}
			}
			else if (fila == 2)
			{
				if(columna == 0)
				{
					return tablero20;
				}
				else if (columna == 1)
			{
					return tablero21;
				}
				else if (columna == 2)
				{
					return tablero22;
				}
			}
		//Fin codigo estudiante punto 3
		
		return 'I'; // retorna invalido si no se trata de ninguna de las casillas. 
	}


	/**
	 * El metodo asignarValorTablero tiene como resposabilidad: cambiar el valor de una casilla del tablero dadas sus coordenadas y el nuevo valor
	 * Recibe los siguientes parametros
	 * @param fila la fila donde se ubica la casilla a cambiar
	 * @param columna la columna donde se ubica la casilla a cambiar
	 * @param valor el nuevo valor que tendra la casilla 
	 * retorna:
	 * void
	 * Precondiciones: fila y columna son enteros positivos, valor es 'X' o 'O'
	 * Postcondiciones:
	 */
	private static void asignarValorTablero(int fila, int columna, char valor) 
	{
		//Punto 4 (Condicionales y asignacion): Cambiar el valor de una casilla segun la fila y columna seleccionadas.
		//las variables que represenan el tablero ya existen y son: 
		//tablero01, tablero02, tablero10, tablero11, tablero12, tablero20, tablero21 y tablero22
		
		//Inicio codigo estudiante punto 4
		if(fila==0)
		{
			if(columna == 0)
			{
				tablero00=valor;
			}
			else if (columna == 1)
			{
				tablero01=valor;
			}
			else if (columna == 2)
			{
				tablero02=valor;
			}
		}
		else if (fila == 1)
			{
				if(columna == 0)
				{
					tablero10=valor;
				}
				else if (columna == 1)
				{
					tablero11=valor;
				}
				else if (columna == 2)
				{
					tablero12=valor;
				}
			}
			else if (fila == 2)
			{
				if(columna == 0)
				{
					tablero20=valor;
				}
				else if (columna == 1)
			{
					tablero21=valor;
				}
				else if (columna == 2)
				{
					tablero22=valor;
				}
			}
		//Fin codigo estudiante punto 4
		
	}


	/**
	 * El metodo validarGanador tiene como resposabilidad: revisar si hay algun ganador en el juego
	 * Recibe los siguientes parametros
	 * @return char: el turno ganador 'X' , 'O', ' ' , 'E'
	 * Precondiciones: Ninguna
	 * Postcondiciones: si x gana, retorna 'X'. si y gana, retorna 'Y', si hay empate, retorna 'E' y si nadie ha ganado retorna ' '
	 */
	private static char validarGanador() 
	{
		// caso 1 si alguna fila gana
		
		// primera fila 
		if(tablero00 == tablero01 && tablero01 == tablero02 && tablero00 == 'X')
		{
			return 'X';//gana x
		}
		else if(tablero00 == tablero01 && tablero01 == tablero02 && tablero00 == 'O')
		{
			return 'O';//gana o
		}
		//segunda fila
		else if(tablero10 == tablero11 && tablero11 == tablero12 && tablero10 == 'X')
		{
			return 'X';//gana o
		}
		else if(tablero10 == tablero11 && tablero11 == tablero12 && tablero10 == 'O')
		{
			return 'O';//gana o
		}
		//tercera fila
		else if(tablero20 == tablero21 && tablero21 == tablero22 && tablero20 == 'X')
		{
			return 'X';//gana o
		}
		else if(tablero20 == tablero21 && tablero21 == tablero22 && tablero20 == 'O')
		{
			return 'O';//gana o
		}
		
		// caso 2 si alguna columna gana
		//primera columna
		if(tablero00 == tablero10 && tablero10 == tablero20 && tablero00 == 'X')
		{
			return 'X';//gana x
		}
		else if(tablero00 == tablero10 && tablero10 == tablero20 && tablero00 == 'O')
		{
			return 'O';//gana o
		}
		//segunda fila
		else if(tablero01 == tablero11 && tablero11 == tablero21 && tablero01 == 'X')
		{
			return 'X';//gana o
		}
		else if(tablero01 == tablero11 && tablero11 == tablero21 && tablero01 == 'O')
		{
			return 'O';//gana o
		}
		//tercera fila
		else if(tablero02 == tablero12 && tablero12 == tablero22 && tablero02 == 'X')
		{
			return 'X';//gana o
		}
		else if(tablero02 == tablero12 && tablero12 == tablero22 && tablero02 == 'O')
		{
			return 'O';//gana o
		}
		
		//Punto 5 (condicionales): verificar si ha ganado un jugador revisando las diagonales del tablero
		// Tome como ejemplo el codigo anterior de este metodo que revisa si ha ganado un jugador revisando las filas y columnas del tablero. 
		// tenga en cuenta que son dos diagonales diferentes y dos jugadores diferentes los que pueden ganar
		// tenga en cuenta que el metodo retorna el valor del jugador que gana, revise la especificacion del metodo.
		
		
		// caso 3 si una de las diagonales gana
		// caso 3.1 /
		
		//Inicio codigo estudiante punto 5
		if(tablero00 == tablero11 && tablero11 == tablero22 && tablero22 == 'X')
		{
			return 'X';
		}
		else if(tablero00 == tablero11 && tablero11 == tablero22 && tablero22 == 'O')
		{
			return 'O';
		}
		if(tablero02 == tablero11 && tablero11 == tablero20 && tablero20 == 'X')
		{
			return 'X';
		}
		else if(tablero02 == tablero11 && tablero11 == tablero20 && tablero20 == 'O')
		{
			return 'O';
		}
		//Fin codigo estudiante punto 5
		
		// caso 4 empate
		boolean empate = true;
		if(tablero00== ' ' || tablero01== ' ' || tablero02== ' ' || tablero10== ' ' || tablero11== ' ' || tablero12== ' ' || tablero20== ' ' || tablero21== ' ' || tablero22== ' ' )
		{
			empate=false;
		}
		if(empate)
		{
			return 'E';
		}
		// caso 5 nadie gana aún
		return ' ';
	}

	/**
	 * El método menu tiene como resposabilidad: desplegar el menu de opciones e invocar las opciones seleccionadas
	 * Recibe los siguientes parametros
	 * retorna:
	 * void
	 * Precondiciones: Ninguna
	 * Postcondiciones: Se muestra el menu y se invocan las acciones seleccionadas hasta que se invoque la opcion de salir
	 */
	@SuppressWarnings("resource")
	private static void menu() 
	{
		int opcion = 0;
		Scanner teclado = new Scanner (System.in);
		System.out.println("     Triqui!");
		System.out.println("      ()~()");
		System.out.println("     (-___-)");
		System.out.println("     ==`-'==");
		
		
		while(opcion < MAXOPCIONES)
		{
			imprimirTablero();
			System.out.println(" Seleccione opcion");
			if(ganador==' ') System.out.println(" 1. Juega "+turno);
			System.out.println(" 2. Iniciar juego");
			System.out.println(" 3. Salir");
			opcion = teclado.nextInt();
			if(opcion == 2)
			{
				reiniciar();
			}
			else if(opcion == 1)
			{
				if(ganador==' ')jugarTurno();
			}
			else if(opcion == 3)
			{
				System.out.println("Ha seleccionado salir del juego!");
			}
			else
			{
				System.out.println("Opcion Invalida");
			}
		}
		
	}
	/**
	 * El método imprimirTablero tiene como resposabilidad: imprimir en pantalla el tablero de juego segun los valores de las casillas
	 * Recibe los siguientes parametros
	 * retorna:
	 * void
	 * Precondiciones:Ninguna
	 * Postcondiciones: imprime el tablero, el turno actual y si hay ganador imprime el turno ganador
	 */
	public static void imprimirTablero()
	{
		
		if(ganador==' ')
		{
			System.out.println("\nEl turno es de "+turno);
		}
		else if(ganador == 'E')
		{
			System.out.println("\nFin del Juego: empate!");
		}
		else
		{
			System.out.println("\nFin del juego: El ganador fue: "+ganador+"!");
		}
		
		System.out.print("     ");
		for(int i=0;i<MAX;i++)
		{
			System.out.print((i+1)+"   ");
		}
		System.out.println("\n");
		for (int i = 0; i < MAX; i++) 
		{
			System.out.print((i+1)+"   ");
			for (int j = 0; j < MAX; j++) 
			{
				System.out.print(" " + pedirValorTablero(i, j) + " ");
				if( j<MAX-1)System.out.print("|");
			}
			System.out.println();
			if(i<MAX-1)
			{
				System.out.print("    ");
				for(int j=0;j<MAX;j++)
				{
					System.out.print("----");
				}
				System.out.println();
				//System.out.println("-----------");
			}
		}
		System.out.println();
		System.out.println();
	}
}

