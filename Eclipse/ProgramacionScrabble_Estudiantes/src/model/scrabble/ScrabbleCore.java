package model.scrabble;

import java.io.File;
import java.util.ArrayList;

import model.dictionary.DictionaryQuerySolver;

public class ScrabbleCore
{
	public static final char DOBLE_LETRA = '2';
	public static final char TRIPLE_LETRA = '3';
	
	public static final char DOBLE_PALABRA = '4';
	public static final char TRIPLE_PALABRA = '5';
	
	public static final char VACIO = '_';
	public static final char CENTRO = '*';
	
	
	public static final char COMODIN = '!';
	
	public static final int TAM_TABLERO = 15;
	public static final int TAM_LETRAS = 27;
	
	public static final int TOTAL_FICHAS = 100;
	public static final int FICHAS_JUGADOR = 25;
	
	public static final int TAM_BANDEJA = 7;
	public static final int NUM_JUGADORES = 4;
	
	public static final int DIRECCION_VERTICAL = 0;
	public static final int DIRECCION_HORIZONTAL = 1;
	
	
	//matriz de 15X15 de casillas
	
	/**
	 * tablero
	 * char[][]
	 * tablero de juego
	 * Posibles valores: 
	 * VACIO si no hay letras o comportamientos, 
	 * CENTRO si es la casilla del centro,
	 * DOBLE_LETRA si la casilla es de doble letra y no ha sido usada
	 * TRIPLE_LETRA si la casilla es de triple letra y no ha sido usada
	 * DOBLE_PALABRA si la casilla es de doble palabra y no ha sido usada
	 * TRIPLE_PALABRA si la casilla es de triple palabra y no ha sido usada
	 * en caso contrario tiene la letra que ha sido jugada en esa posicion
	 * 
	 */
	private char[][] tablero = new char[TAM_TABLERO][TAM_TABLERO];
	
	private char[][] tableroRespaldo = new char[TAM_TABLERO][TAM_TABLERO];
	
	/**
	 * fichas
	 * char[]
	 * arreglo de fichas (incluye comodin '!')
	 */
	private char[] fichas =  {'!','a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	//arreglo de puntajes
	private int[] puntajesFichas = {0, 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
	//
	/**
	 * cantidades
	 * int[]
	 * cantidades de cada letra (ficha)
	 */
	
	//XXX: quito comodines para pruebas
	//private int[] cantidades = {2, 9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};
	private int[] cantidades = {0, 9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};
	
	
	//fichas que tienen los jugadores, la posicion i,j representa las fichas que tiene el jugador i de la letra j
	//las fichas de un jugador deben sumar 25, las fichas en una letra deben sumar segun la distrubucion
	//distribucion inicial (en el arreglo cantidades)
		/*
		 * 	2 cuadros en blanco (0 puntos)
			1 punto: E ×12, A ×9, I ×9, O ×8, N ×6, R ×6, T ×6, L ×4, S ×4, U ×4
			2 puntos: D ×4, G ×3
			3 puntos: B ×2, C ×2, M ×2, P ×2
			4 puntos: F ×2, H ×2, V ×2, W ×2, Y ×2
			5 puntos: K ×1
			8 puntos: J ×1, X ×1
			10 puntos: Q ×1, Z ×1
		*/
	
	/**
	 * fichasJugadores
	 * int[][]
	 * Representa las fichas que tiene el jugador i y pueden ser maximo 7
	 */
	private int[][] fichasJugadores = new int[NUM_JUGADORES][TAM_LETRAS];
	
	private int turnoActual = 1; 
	
	private ArrayList<int[]> posicionesNoConfirmadas = new ArrayList<int[]>();
	
	private String letrasEnJuego = "";
	
	private DictionaryQuerySolver dictionary = new DictionaryQuerySolver();
	
	private int[] puntajesJugadores = new int[NUM_JUGADORES];
	
	public ScrabbleCore ()
	{
		
		reiniciar();
		imprimirTableroConsola();
		repartir();
		for (int i=0; i<NUM_JUGADORES; i++)
		{
			imprimirLetrasJugador(i);
			System.out.println();
		}
		respaldarTablero();
		dictionary.loadDictionary(new File("./dic/en-Us.dic"));
	}
	/*
	 * Realizado por Eric Julián Rodriguez
	 * */
	/**
	 * repartirJugador
	 * @param jugador
	 * @param cuantas
	 * El metodo tiene la responsabilidad de repartir un cierto numero de fichas al jugador enviado como parametro
	 * El metodo debe 
	 * 1. pedir el numero de fichas de un jugador mediante llamado al metodo darCantidadLetrasActuales
	 * 2. pedir el numero de fichas disponibles para repartir mediante llamado al metodo darCantidadFichasDisponibles
	 * 3. mediante un ciclo completar las fichas que le faltan pidiendo una letra aleatoria, 
	 * si la cantidad de esa ficha (en el arreglo cantidad en esa posicion) es mayor que cero se puede restar en uno 
	 * esa cantidad y sumar en la matriz de fichasJugadores de ese jugador y de esa ficha.
	 */
	public void repartirJugador(int jugador , int cuantas)
	{
		//TODO: INICIO DEL PUNTO 1: Implemente el metodo segun la documentacion
		int letrasActuales = darCantidadLetrasActuales(jugador);
		int fichasDisponibles = darCantidadFichasDisponibles();
		int k;
		for(k=0;k<cuantas && fichasDisponibles>0;k++){
			int letraAleatoria = darPosicionLetraAleatoria();
			while(cantidades[letraAleatoria]==0){
				letraAleatoria = darPosicionLetraAleatoria();
			}
			fichasJugadores[jugador][letraAleatoria]++;
			cantidades[letraAleatoria]--;
			fichasDisponibles--;
			letrasActuales++;
		}
		//FIN DEL PUNTO 1
	}
	/**
	 * darPalabra
	 * @param fila
	 * @param columna
	 * @param direccion
	 * @return
	 * El metodo tiene la responsabilidad de retornar la palabra que se encuentra en el tablero en la posicion fila, columna en la orientacion
	 * enviada como parametro. el parametro direccion puede tener valores DIRECCION_VERTICAL o DIRECCION_HORIZONTAL
	 */
	private String darPalabra(int fila, int columna, int direccion)
	{
		//TODO: INICIO PUNTO 2: Implemente el contenido del metodo segun la documentacion
		String palabra = "";
		int pareja[] = new int[2];
		pareja[0] = fila;
		pareja[1] = columna;
		if(direccion==DIRECCION_HORIZONTAL){
			int coordenada[] = darCoordenadaInicialHorizontal(pareja);
			int i=coordenada[1];
			while(i<TAM_TABLERO  && tablero[coordenada[0]][i]!=DOBLE_LETRA
					&& tablero[coordenada[0]][i]!=TRIPLE_LETRA
					&& tablero[coordenada[0]][i]!=DOBLE_PALABRA
					&& tablero[coordenada[0]][i]!=TRIPLE_PALABRA
					&& tablero[coordenada[0]][i]!=VACIO){
				palabra = palabra + tablero[coordenada[0]][i];
				i++;
			}
		}
		else if(direccion==DIRECCION_VERTICAL){
			int coordenada[] = darCoordenadaInicialVertical(pareja);
			int i=coordenada[0];
			while(i<TAM_TABLERO  && tablero[i][coordenada[1]]!=DOBLE_LETRA
					&& tablero[i][coordenada[1]]!=TRIPLE_LETRA
					&& tablero[i][coordenada[1]]!=DOBLE_PALABRA
					&& tablero[i][coordenada[1]]!=TRIPLE_PALABRA
					&& tablero[i][coordenada[1]]!=VACIO){
				palabra = palabra + tablero[i][coordenada[1]];
				i++;
			}
		}
		return palabra;
		//FIN PUNTO 2
	}
	/**
	 * darCoordenadaInicialHorizontal
	 * @param pareja
	 * @return
	 * El metodo tiene la responsabilidad de retornar un arreglo con un par de coordenadas en el tablero donde este la primera
	 * letra horizontal desde esa posicion. Recibe como parametro un arreglo de tamano 2 entero que tiene la coordenada de donde parte la busqueda
	 * Asi mismo debe ser el valor de retorno
	 * 
	 * Ejemplo: Si el parametro es la pareja de coordenadas <2,3> el metodo debe retornar <2,1> porque es el lugar donde esta
	 * La primera letra a la izquierda.
	 * 
			 
		         0       1      2       3       4
		
		     +------++------++------++------++------+
		  0  |  ' ' ||  ' ' ||  ' ' ||  ' ' ||  ' ' |
		     |      ||      ||      ||      ||      |
		     +------++------++------++------++------+
		     +------++------++------++------++------+
		  1  |  ' ' ||  ' ' ||  ' ' ||  ' ' ||  ' ' |
		     |      ||      ||      ||      ||      |
		     +------++------++------++------++------+
		     +------++------++------++------++------+
		  2  |  ' ' ||  'O' ||  'S' ||  'O' ||  ' ' |
		     |      ||      ||      ||      ||      |
		     +------++------++------++------++------+
		     +------++------++------++------++------+
		  3  |  ' ' ||  ' ' ||  ' ' ||  ' ' ||  ' ' |
		     |      ||      ||      ||      ||      |
		     +------++------++------++------++------+


	 */
	private int[] darCoordenadaInicialHorizontal(int[] pareja) 
	{
		//TODO: INICIO PUNTO 3: Implemente el metodo segun la documentacion
		int coordenada[] = new int[2];
		coordenada[0] = pareja[0];
		int i=pareja[1];
		while(i>0 && tablero[pareja[0]][i]!=DOBLE_LETRA
				&& tablero[pareja[0]][i]!=TRIPLE_LETRA
				&& tablero[pareja[0]][i]!=DOBLE_PALABRA
				&& tablero[pareja[0]][i]!=TRIPLE_PALABRA
				&& tablero[pareja[0]][i]!=VACIO){
			i--;
		}
		if(i==0 && tablero[pareja[0]][i]!=DOBLE_LETRA
				&& tablero[pareja[0]][i]!=TRIPLE_LETRA
				&& tablero[pareja[0]][i]!=DOBLE_PALABRA
				&& tablero[pareja[0]][i]!=TRIPLE_PALABRA
				&& tablero[pareja[0]][i]!=VACIO){
			coordenada[1] = 0;
		}
		else{
			coordenada[1] = i+1; 
		}
			
		return coordenada;
		//FIN PUNTO 3
	}
	/**
	 * darCoordenadaInicialVertical
	 * @param pareja
	 * @return
	 * El metodo tiene la responsabilidad de retornar un arreglo con un par de coordenadas en el tablero donde este la primera
	 * letra vertical desde esa posicion. Recibe como parametro un arreglo de tamano 2 entero que tiene la coordenada de donde parte la busqueda
	 * Asi mismo debe ser el valor de retorno
	 * 
	 * Ejemplo: Si el parametro es la pareja de coordenadas <3,2> el metodo debe retornar <1,2> porque es el lugar donde esta
	 * La primera letra hacia arriba.
	 * 
			 
		         0       1      2       3       4
		
		     +------++------++------++------++------+
		  0  |  ' ' ||  ' ' ||  ' ' ||  ' ' ||  ' ' |
		     |      ||      ||      ||      ||      |
		     +------++------++------++------++------+
		     +------++------++------++------++------+
		  1  |  ' ' ||  ' ' ||  'O' ||  ' ' ||  ' ' |
		     |      ||      ||      ||      ||      |
		     +------++------++------++------++------+
		     +------++------++------++------++------+
		  2  |  ' ' ||  ' ' ||  'S' ||  ' ' ||  ' ' |
		     |      ||      ||      ||      ||      |
		     +------++------++------++------++------+
		     +------++------++------++------++------+
		  3  |  ' ' ||  ' ' ||  'O' ||  ' ' ||  ' ' |
		     |      ||      ||      ||      ||      |
		     +------++------++------++------++------+


	 */
	private int[] darCoordenadaInicialVertical(int[] pareja) 
	{
		/*DOBLE_LETRA = '2';
	public static final char TRIPLE_LETRA = '3';
	
	public static final char DOBLE_PALABRA = '4';
	public static final char TRIPLE_PALABRA = '5';
	
	public static final char VACIO = '_';
	public static final char CENTRO = '*';*/
		//TODO: INICIO PUNTO 4: Implemente el metodo segun la documentacion
		int coordenada[] = new int[2];
		coordenada[1] = pareja[1];
		int i=pareja[0];
		while(i>0 && tablero[i][pareja[1]]!=DOBLE_LETRA
				&& tablero[i][pareja[1]]!=TRIPLE_LETRA
				&& tablero[i][pareja[1]]!=DOBLE_PALABRA
				&& tablero[i][pareja[1]]!=TRIPLE_PALABRA
				&& tablero[i][pareja[1]]!=VACIO){
			i--;
		}
		if(i==0 && tablero[i][pareja[1]]!=DOBLE_LETRA
				&& tablero[i][pareja[1]]!=TRIPLE_LETRA
				&& tablero[i][pareja[1]]!=DOBLE_PALABRA
				&& tablero[i][pareja[1]]!=TRIPLE_PALABRA
				&& tablero[i][pareja[1]]!=VACIO){
			coordenada[0] = 0;
		}
		else{
			coordenada[0] = i+1; 
		}
			
		return coordenada;
		//FIN PUNTO 4
	}
	
	//retorna la cantidad de letras que tiene un jugador
	/**
	 * darCantidadLetrasActuales
	 * @param jugador
	 * @return
	 * El metodo tiene la responsabilidad de contar cuantas fichas tiene un jugador en particular.
	 * El parametro jugador es el numero del jugador que se requiere
	 * Debe retornar el numero de fichas que dicho jugador tiene
	 */
	private int darCantidadLetrasActuales(int jugador)
	{
		//TODO: INICIO PUNTO 5: Implemente el metodo segun la documentacion
		int conteo = 0;
		int i;
		for(i=0;i<TAM_LETRAS;i++){
			conteo = conteo + fichasJugadores[jugador][i];
		}
		return conteo;
		//FIN PUNTO 5
	}
	/**
	 * respaldarTablero
	 * El metodo tiene la responsabilidad de copiar el contenido de la matriz tablero en la matriz tableroRespaldo
	 */
	private void respaldarTablero() 
	{
		//TODO: INICIO PUNTO 6: Implemente el metodo segun la documentacion
		int i, j;
		for(i=0;i<TAM_TABLERO;i++){
			for(j=0;j<TAM_TABLERO;j++){
				tableroRespaldo[i][j] = tablero[i][j];
			}
		}
				
		//FIN PUNTO 6
	}
	public char[] darLetrasJugador(int jugador)
	{
		int totalFichasJugador = darCantidadLetrasActuales(jugador);
		char[] respuesta = new char[totalFichasJugador];
		int contador = 0;
		for (int i = 0; i < TAM_LETRAS; i++)
		{
			int numFichas = fichasJugadores[jugador][i];
			if(numFichas>0)
			{
				for(int j=0; j<numFichas;j++)
				{
					respuesta[contador]=fichas[i];
					contador++;
				}
			}
		}
		return respuesta;
	}
	private int darCantidadFichasDisponibles()
	{
		int cuantas =0;
		for (int i = 0; i < TAM_LETRAS; i++)
		{
			cuantas+=cantidades[i];
		}
		return cuantas;
	}
	public void reiniciar()
	{
		//tablero con valores iniciales , vacios y casillas especiales (DW, TW, DL, TL) y centro
		for (int i = 0; i < TAM_TABLERO; i++) 
		{
			for (int j = 0; j < TAM_TABLERO; j++) 
			{
				tablero[i][j]=VACIO;
			}
		}
		//CASILLAS ESPECIALES: 
		
		//CENTRO: posicion 7,7
		tablero[7][7]= CENTRO;
		//TRIPLE_PALABRA: 
		tablero[0][0]= TRIPLE_PALABRA;
		tablero[0][7]= TRIPLE_PALABRA;
		tablero[0][14]= TRIPLE_PALABRA;
		tablero[7][0]= TRIPLE_PALABRA;
		tablero[7][14]= TRIPLE_PALABRA;
		tablero[14][0]= TRIPLE_PALABRA;
		tablero[14][7]= TRIPLE_PALABRA;
		tablero[14][14]= TRIPLE_PALABRA;
		//DOBLE PALABRA: 
		tablero[1][1]= DOBLE_PALABRA;
		tablero[2][2]= DOBLE_PALABRA;
		tablero[3][3]= DOBLE_PALABRA;
		tablero[4][4]= DOBLE_PALABRA;
		tablero[10][10]= DOBLE_PALABRA;
		tablero[11][11]= DOBLE_PALABRA;
		tablero[12][12]= DOBLE_PALABRA;
		tablero[13][13]= DOBLE_PALABRA;
		tablero[13][1]= DOBLE_PALABRA;
		tablero[12][2]= DOBLE_PALABRA;
		tablero[11][3]= DOBLE_PALABRA;
		tablero[10][4]= DOBLE_PALABRA;
		tablero[4][10]= DOBLE_PALABRA;
		tablero[3][11]= DOBLE_PALABRA;
		tablero[2][12]= DOBLE_PALABRA;
		tablero[1][13]= DOBLE_PALABRA;
		//TRIPLE LETRA
		tablero[1][5]= TRIPLE_LETRA;
		tablero[1][9]= TRIPLE_LETRA;
		tablero[5][1]= TRIPLE_LETRA;
		tablero[5][5]= TRIPLE_LETRA;
		tablero[5][9]= TRIPLE_LETRA;
		tablero[5][13]= TRIPLE_LETRA;
		tablero[9][1]= TRIPLE_LETRA;
		tablero[9][5]= TRIPLE_LETRA;
		tablero[9][9]= TRIPLE_LETRA;
		tablero[9][13]= TRIPLE_LETRA;
		tablero[13][5]= TRIPLE_LETRA;
		tablero[13][9]= TRIPLE_LETRA;
		//DOBLE LETRA
		tablero[0][3]= DOBLE_LETRA;
		tablero[0][11]= DOBLE_LETRA;
		tablero[2][6]= DOBLE_LETRA;
		tablero[2][8]= DOBLE_LETRA;
		tablero[3][0]= DOBLE_LETRA;
		tablero[3][7]= DOBLE_LETRA;
		tablero[3][14]= DOBLE_LETRA;
		tablero[6][2]= DOBLE_LETRA;
		tablero[6][6]= DOBLE_LETRA;
		tablero[6][8]= DOBLE_LETRA;
		tablero[6][12]= DOBLE_LETRA;
		tablero[7][3]= DOBLE_LETRA;
		tablero[7][11]= DOBLE_LETRA;
		tablero[8][2]= DOBLE_LETRA;
		tablero[8][6]= DOBLE_LETRA;
		tablero[8][8]= DOBLE_LETRA;
		tablero[8][12]= DOBLE_LETRA;
		tablero[11][0]= DOBLE_LETRA;
		tablero[11][7]= DOBLE_LETRA;
		tablero[11][14]= DOBLE_LETRA;
		tablero[12][6]= DOBLE_LETRA;
		tablero[12][8]= DOBLE_LETRA;
		tablero[14][3]= DOBLE_LETRA;
		tablero[14][11]= DOBLE_LETRA;
		
		//cantidades iniciales		
		cantidades[0]=2;
		cantidades[1]=9;
		cantidades[2]=2;
		cantidades[3]=2;
		cantidades[4]=4;
		cantidades[5]=12;
		cantidades[6]=2;
		cantidades[7]=3;
		cantidades[8]=2;
		cantidades[9]=9;
		cantidades[10]=1;
		cantidades[11]=1;
		cantidades[12]=4;
		cantidades[13]=2;
		cantidades[14]=6;
		cantidades[15]=8;
		cantidades[16]=2;
		cantidades[17]=1;
		cantidades[18]=6;
		cantidades[19]=4;
		cantidades[20]=6;
		cantidades[21]=4;
		cantidades[22]=2;
		cantidades[23]=2;
		cantidades[24]=1;
		cantidades[25]=2;
		cantidades[26]=1;
		
		//bandeja por jugador
		for (int i = 0; i < NUM_JUGADORES; i++) 
		{
			for (int j = 0; j < TAM_LETRAS; j++) 
			{
				fichasJugadores[i][j]=0;
			}
		}
	}
	public void repartir()
	{
		for(int jugadori = 0 ; jugadori< NUM_JUGADORES; jugadori++)
		{
			repartirJugador(jugadori, TAM_BANDEJA);
		}
	}
	public void iniciarJugada()
	{
		respaldarTablero();
		posicionesNoConfirmadas = new ArrayList<int[]>();
		letrasEnJuego ="";
	}
	public boolean entrarLetra(char letra, int i, int j, int jugador)
	{
		//inserta al tablero, validando no sobreescribir otra letra
		if(esLetra(tablero[i][j]))
		{
			return false;
		}
		//almacena posiciones en arreglo de posiciones no confirmadas
		int[] pareja = {i,j};
		posicionesNoConfirmadas.add(pareja);
		letrasEnJuego+=letra;
		tablero[i][j]=letra;
		//retorna true si fue posible, false si no
		return true;
	}
	public void cancelarJugada()
	{
		posicionesNoConfirmadas = new ArrayList<int[]>();
		restaurarTablero();
	}
	public int confirmarJugada(int jugador)
	{
		int puntaje = revisarSecuencia();
		if(puntaje == -1)
		{
			cancelarJugada();
			return -1;
		}
		else
		{
			//quitar piezas de la bandeja al jugador actual
			for (int i = 0; i < this.letrasEnJuego.length(); i++)
			{
				char c = this.letrasEnJuego.charAt(i);
				this.eliminarFichaBandejaJugador(jugador,c);
			}
			//repartir nuevas fichas al jugador
			this.repartirJugador(jugador, TAM_BANDEJA);
			//aumentar puntaje
			puntajesJugadores[jugador]+=puntaje;
			//cambiar turno
			setTurnoActual(darSiguienteTurno(jugador));
			return puntaje;
		}
	}
	
	private void eliminarFichaBandejaJugador(int jugador, char letra)
	{
		for (int i = 0; i < TAM_LETRAS; i++)
		{
			if(letra == this.fichas[i])
			{
				this.fichasJugadores[jugador][i]--;
			}
		}
	}
	public int revisarSecuencia()
	{
		//lista de triplas filaXcolumnaXdireccion
		ArrayList<int[]> triplasXYDVerticales = new ArrayList<int[]>();
		ArrayList<int[]> triplasXYDHorizontales = new ArrayList<int[]>();
		//recorrer cada letra nueva y hallar sus dos palabras (vertical y horizontal)
		for(int[] pareja: posicionesNoConfirmadas)
		{
			int[] coordenadaInicialVertical = darCoordenadaInicialVertical(pareja);
			int[] triplaXYDVertical = {coordenadaInicialVertical[0], coordenadaInicialVertical[1], DIRECCION_VERTICAL};
			triplasXYDVerticales.add(triplaXYDVertical);
			int[] coordenadaInicialHorizontal = darCoordenadaInicialHorizontal(pareja);
			int[] triplaXYDHorizontal = {coordenadaInicialHorizontal[0], coordenadaInicialHorizontal[1], DIRECCION_HORIZONTAL};
			triplasXYDHorizontales.add(triplaXYDHorizontal);
		}
		//eliminar triplas repetidas
		triplasXYDHorizontales= eliminarTriplasXYDRepetidas(triplasXYDHorizontales);
		triplasXYDVerticales = eliminarTriplasXYDRepetidas(triplasXYDVerticales);
		
		//ahora todas las triplas estan completas. falta validar sus contenidos y si son validos TODOS darles puntaje
		//adecuar llamado a metodo para que pueda ser implementado por los estudiantes
		boolean todasValidas= true;
		for(int[]tripla :triplasXYDHorizontales)
		{
			int xini=tripla[0];
			int yini=tripla[1];
			int dir=tripla[2];
			if(!validarPalabra(darPalabra(xini, yini, dir)))
			{
				todasValidas= false;
			}
		}
		for(int[] tripla: triplasXYDVerticales)
		{
			int xini=tripla[0];
			int yini=tripla[1];
			int dir=tripla[2];
			if(!validarPalabra(darPalabra(xini, yini, dir)))
			{
				todasValidas= false;
			}
		}
		
		if(!todasValidas)
		{
			return -1;//significa que no se vale esta entrada
		}
		//continuando con el asunto, se debe retornar el puntaje de las que quedaron.
		int puntaje = 0;
		for(int[]tripla :triplasXYDHorizontales)
		{
			int xini=tripla[0];
			int yini=tripla[1];
			int dir=tripla[2];
			puntaje+=darPuntajePalabra(xini, yini, dir);
		}
		for(int[] tripla: triplasXYDVerticales)
		{
			int xini=tripla[0];
			int yini=tripla[1];
			int dir=tripla[2];
			puntaje+=darPuntajePalabra(xini, yini, dir);
		}
		return puntaje;
	}
	private int darPuntajePalabra(int fila, int columna, int direccion)
	{
		//validacion inicial, se puntua cero una palabra de tamaño 1
		if(darPalabra(fila, columna, direccion).length()==1)
		{
			return 0;
		}
		
		int puntajeTotal=0;
		int multiplicadores = 1;//variable para almacenar los DW y TW encontrados
		
		if(direccion == DIRECCION_VERTICAL)
		{
			for(int i = fila; i<TAM_TABLERO && esLetra(tablero[i][columna]);i++)
			{
				//comparar con el tablero viejo para saber si era una letra nueva o vieja
				char letra = tablero[i][columna];
				char letraVieja = tableroRespaldo[i][columna];
				if(letra == letraVieja)//era una letra vieja, solo puntua lo que vale la letra
				{
					int posicion = darPosicionLetra(letraVieja);
					int puntajeLetra = puntajesFichas[posicion];
					puntajeTotal+=puntajeLetra;
				}
				else
				{
					int posicion = darPosicionLetra(letra);
					int puntajeLetra = puntajesFichas[posicion];
					int puntajeParcialLetra = puntajeLetra;
					
					//en este caso podría ser un DW, TW, *, DL, TL o vacio
					if(letraVieja== DOBLE_LETRA)
					{
						puntajeParcialLetra*=2;
					}
					else if(letraVieja==TRIPLE_LETRA)
					{
						puntajeParcialLetra*=2;
					}
					else if(letraVieja==DOBLE_PALABRA)
					{
						multiplicadores*=2;
					}
					else if(letraVieja==TRIPLE_PALABRA)
					{
						multiplicadores*=3;
					}
					puntajeTotal+=puntajeParcialLetra;
				}
			}
			puntajeTotal*=multiplicadores;
		}
		else if(direccion == DIRECCION_HORIZONTAL)
		{
			for(int j = columna; j<TAM_TABLERO && esLetra(tablero[fila][j]);j++)
			{
				//comparar con el tablero viejo para saber si era una letra nueva o vieja
				char letra = tablero[fila][j];
				char letraVieja = tableroRespaldo[fila][j];
				if(letra == letraVieja)//era una letra vieja, solo puntua lo que vale la letra
				{
					int posicion = darPosicionLetra(letraVieja);
					int puntajeLetra = puntajesFichas[posicion];
					puntajeTotal+=puntajeLetra;
				}
				else
				{
					int posicion = darPosicionLetra(letra);
					int puntajeLetra = puntajesFichas[posicion];
					int puntajeParcialLetra = puntajeLetra;
					
					//en este caso podría ser un DW, TW, *, DL, TL o vacio
					if(letraVieja== DOBLE_LETRA)
					{
						puntajeParcialLetra*=2;
					}
					else if(letraVieja==TRIPLE_LETRA)
					{
						puntajeParcialLetra*=2;
					}
					else if(letraVieja==DOBLE_PALABRA)
					{
						multiplicadores*=2;
					}
					else if(letraVieja==TRIPLE_PALABRA)
					{
						multiplicadores*=3;
					}
					puntajeTotal+=puntajeParcialLetra;
				}
			}
			puntajeTotal*=multiplicadores;
			
		}
		System.out.println("La palabra puntuo: "+puntajeTotal);
		return puntajeTotal;
	}
	private boolean validarPalabra(String palabra)
	{
		if(palabra.length()== 1)
		{
			return true;
		}
		//acceso al diccionario
		if(dictionary.searchWord(palabra))
		{
			return true;
		}
		return false;
	}
	private ArrayList<int[]> eliminarTriplasXYDRepetidas(ArrayList<int[]> triplasXYD) 
	{
		ArrayList<int[]> respuesta = new ArrayList<int[]>();
		for(int[] tripla: triplasXYD)
		{
			if(!buscarTripla(respuesta, tripla))
			{
				respuesta.add(tripla);
			}
		}
		return respuesta;
	}
	private boolean buscarTripla(ArrayList<int[]> triplasXYD, int[] triplaBuscada)
	{
		for(int[] tripla: triplasXYD)
		{
			if(tripla[0] == triplaBuscada[0] && tripla[1] == triplaBuscada[1] && tripla[2]== triplaBuscada[2])
			{
				return true;
			}
		}
		return false;
	}
	private void restaurarTablero()
	{
		for (int i = 0; i < TAM_TABLERO; i++) 
		{
			for (int j = 0; j < TAM_TABLERO; j++) 
			{
				tablero[i][j]=tableroRespaldo[i][j];
			}
		}
	}
	//utilitarios
	public static int darPosicionLetraAleatoria()
	{
		double millis = Math.random() * 100000;
		millis = Math.round(millis);
		millis = millis%TAM_LETRAS;
		int res = (int)millis;
		return res;
	}
	public char[][] getTablero()
	{
		return tablero;
	}
	public void setTablero(char[][] tablero)
	{
		this.tablero = tablero;
	}
	public char[] getFichas()
	{
		return fichas;
	}
	public void setFichas(char[] fichas)
	{
		this.fichas = fichas;
	}
	public int[] getPuntajes()
	{
		return puntajesFichas;
	}
	public void setPuntajes(int[] puntajes)
	{
		this.puntajesFichas = puntajes;
	}
	public int[] getCantidades()
	{
		return cantidades;
	}
	public void setCantidades(int[] cantidades)
	{
		this.cantidades = cantidades;
	}
	public int[][] getFichasJugadores()
	{
		return fichasJugadores;
	}
	public void setFichasJugadores(int[][] fichasJugadores)
	{
		this.fichasJugadores = fichasJugadores;
	}
	public int darPosicionLetra(char c)
	{
		for(int i = 0 ; i<TAM_LETRAS; i++)
		{
			if(c==fichas[i])
			{
				return i;
			}
		}
		return 0;
	}
	public int getTurnoActual() {
		return turnoActual;
	}
	public void setTurnoActual(int turnoActual) {
		this.turnoActual = turnoActual;
	}
	public int darSiguienteTurno(int jugador)
	{
		if(jugador == 0)
		{
			return 1;
		}
		else if(jugador == 1)
		{
			return 2;
		}
		else if ( jugador == 2)
		{
			return 3;
		}
		else
		{
			return 0;
		}
	}
	public char[][] getTableroRespaldo() {
		return tableroRespaldo;
	}
	public void setTableroRespaldo(char[][] tableroRespaldo) {
		this.tableroRespaldo = tableroRespaldo;
	}
	public boolean esLetra (char valor)
	{
		boolean esletra = false;
		for (int i = 0; i < TAM_LETRAS; i++) 
		{
			if(fichas[i]== valor)
			{
				esletra=true;
			}
		}
		return esletra;
	}
	public void imprimirLetrasJugador(int jugador)
	{
		System.out.println("Fichas del jugador: "+jugador);
		for (int i = 0; i < TAM_LETRAS; i++)
		{
			if(fichasJugadores[jugador][i]>0)
			{
				System.out.print(fichas[i]+":"+puntajesFichas[i]+ " ");
			}
		}
	}
	public static void main(String[] args)
	{
		ScrabbleCore sc = new ScrabbleCore();
		sc.reiniciar();
		sc.imprimirTableroConsola();
		sc.repartir();
		for (int i=0; i<NUM_JUGADORES; i++)
		{
			sc.imprimirLetrasJugador(i);
			System.out.println();
		}
	}
	public void imprimirTableroConsola ()
	{
		for (int i = 0; i < TAM_TABLERO; i++)
		{
			for (int j = 0; j < TAM_TABLERO; j++)
			{
				System.out.print(tablero[i][j]+" ");
			}
			System.out.println();
		}
	}
	public int getPuntajeJugador(int t)
	{
		return this.puntajesJugadores[t];
	}
	
}
