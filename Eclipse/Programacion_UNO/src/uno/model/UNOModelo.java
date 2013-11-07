package uno.model;

/**
 * Class: UNOModelo
 *
 */
public class UNOModelo 
{
	//DECLARACION DE CONSTANTES
	public static final int NUMERO_JUGADORES = 4;
	public static final int NUMERO_COLORES = 4;
	public static final int NUMERO_CARTAS = 27;
	
	public static final int CARTA_NO_USADA = 0;
	public static final int CARTA_USADA = -2;
	public static final int CARTA_ACTUAL = -1;
	
	public static final int POSICION_CARTA_CERO = 0;
	public static final int POSICION_CARTA_ROBA2 = 19;
	public static final int POSICION_CARTA_INVIERTE = 21;
	public static final int POSICION_CARTA_SALTO = 23;
	public static final int POSICION_CARTA_COMODIN = 25;
	public static final int POSICION_CARTA_ROBA4 = 26;
	
	
	public static final int JUGADOR_VERDE =1;
	public static final int JUGADOR_AZUL = 2;
	public static final int JUGADOR_ROJO = 3;
	public static final int JUGADOR_AMARILLO = 4;
	
	public static final int COLOR_VERDE =0 ;
	public static final int COLOR_AZUL = 1;
	public static final int COLOR_ROJO = 2;
	public static final int COLOR_AMARILLO = 3;
	public static final int COLOR_NINGUNO = 4;
	
	public static final int CARTAS_INICIALES = 7;
	public static final int CARTAS_ACTIVAS = 1;
	public static final int SENTIDO_HORARIO = 1;
	public static final int SENTIDO_ANTIHORARIO = -1;
	
	//DECLARACION DE ATRIBUTOS DE LA CLASE
	
	
	/**
	 * conjuntoCartas
	 * int[][]
	 * Representacion del tablero de juego, matríz entera de 4X27 donde cada valor representa el lugar donde está la carta representada
	 * su valor i,j es -1 si es la carta en juego
	 * su valor es -2 si ya fue usada
	 * su valor es 0 si la carta se encuentra en las cartas para robar
	 * su valor es 1,2,3,4 si algun jugador la tiene
	 * 
	 * Las filas 0 a 18 son las cartas numericas, excepto el cero las demas se repiten
	 * Las filas 19 y 20 son "+2" (hay 8 en total)
	 * Las filas 21 y 22 son "-><-" o cambio de sentido (hay 8 en total)
	 * Las filas 23 y 24 son "X" o salto de turno (hay 8 en total)
	 * La fila 25 es el comodin "C" de cambio de color (hay 4 en total)
	 * La fila 26 es "+4" (hay 4 en total)
	 * 
	 */
	private int[][] conjuntoCartas = new int[NUMERO_CARTAS][NUMERO_COLORES];
	
	/**
	 * comodinActivo
	 * int
	 * Representa la entrada en juego de un comodin, si es verdadera significa que la carta actual es un comodin y solo se permitira jugar 
	 * con cartas del color "colorComodinActivo" sin restriccion de numero
	 */
	private int comodinActivo = 0;
	/**
	 * colorComodinActivo
	 * int
	 * Representa el color seleccionado por un jugador que ha usado un comodin, la siguiente jugada debera realizarse con este color
	 */
	private int colorComodinActivo = COLOR_NINGUNO;
	/**
	 * turnoActual
	 * int
	 * Representa el turno actual
	 */
	private  int turnoActual = 1;
	/**
	 * sentido
	 * int
	 * Representa el sentido del cambio de turno, puede ser horario o antihorario
	 */
	private  int sentido = SENTIDO_HORARIO;
	
	/**
	 * filaValorCartaActiva
	 * int
	 * Representa la fila donde se encuentra la carta que esta en juego
	 */
	private int filaValorCartaActiva = 0 ;
	/**
	 * columnaColorCartaActiva
	 * int
	 * Representa la columna donde se encuentra la carta que esta en juego
	 */
	private int columnaColorCartaActiva = 0;
	
	/**
	 * avisoUNO
	 * boolean[]
	 * Representa los avisos de UNO
	 */
	private boolean[] avisoUNO = new boolean[NUMERO_JUGADORES];
	
	//Metodos de inicializacion
	
	/**
	 * reiniciarJuego
	 * El metodo tiene la responsabilidad de iniciar un juego nuevo limpiando el tablero (via llamado al correspondiente
	 * metodo), repartir siete cartas a cada jugador y poniendo una carta en el maso de descarte.
	 */
	public void reiniciarJuego()
	{
		this.limpiarTablero();
		for(int i = 0 ; i<NUMERO_JUGADORES; i++)
		{
			this.darCartasAlJugador(i+1, 7);
		}
		this.fijarCartaInicial();
	}

	/**
	 * limpiarTablero
	 * El metodo tiene la responsabilidad de limpiar el tablero poniendo todas las cartas en estado CARTA_NO_USADA
	 */
	private void limpiarTablero()
	{
		for(int i = 0 ; i<NUMERO_CARTAS; i++)
		{
			for(int j=0; j<NUMERO_COLORES; j++)
			{
				this.conjuntoCartas[i][j]= CARTA_NO_USADA;
			}
		}
	}
	
	/**
	 * darPosicionAleatoria
	 * @param limite
	 * @return
	 * El metodo tiene la responsabilidad de devolver como respuesta un valor entre cero y el limite definido
	 */
	public static int darPosicionAleatoria(int limite) 
	{
		double numeroAleatorio = Math.random() * 100000;
		numeroAleatorio = Math.round(numeroAleatorio);
		numeroAleatorio = numeroAleatorio%limite;
		int res = (int)numeroAleatorio;
		
		return res;
	}

	/**
	 * fijarCartaInicial
	 * El metodo tiene la responsabilidad de tomar una carta no usada de manera aleatoria y fijarla como carta actual
	 * de juego. La primera carta no debe ser una carta de accion (comodin, +2, +4, salto, invierte).
	 */
	private void fijarCartaInicial() 
	{
		int cartasAsignadas = 0;
		while(cartasAsignadas < CARTAS_ACTIVAS)
		{
			int i = darPosicionAleatoria(NUMERO_CARTAS);
			//revisar que no sea una carta especial
			boolean esCartaAccion = esCartaAccion(i);
			int j = darPosicionAleatoria(NUMERO_COLORES);
			if(this.conjuntoCartas[i][j]== CARTA_NO_USADA  && !esCartaAccion)
			{
				this.conjuntoCartas[i][j]= CARTA_ACTUAL;
				this.filaValorCartaActiva =i;
				this.columnaColorCartaActiva = j;
				cartasAsignadas++;
			}
		}
	}

	/**
	 * darCartasAlJugador
	 * @param jugador
	 * @param cuantas
	 * El metodo tiene la responsabilidad de dar a un jugador un numero de cartas definido.
	 * Puede usarse para repartir al principio o para castigar al jugador o para robar
	 */
	public void darCartasAlJugador(int jugador, int cuantas)
	{
		int cartasDisponibles = contarCartasDisponibles();
		int cartasAsignadas = 0;
		while(cartasAsignadas < cuantas && cartasDisponibles > 0)
		{
			int i = darPosicionAleatoria(NUMERO_CARTAS);
			int j = darPosicionAleatoria(NUMERO_COLORES);
			if(this.conjuntoCartas[i][j]== CARTA_NO_USADA || this.conjuntoCartas[i][j]== CARTA_USADA)
			{
				this.conjuntoCartas[i][j]= jugador;
				cartasAsignadas++;
			}
			cartasDisponibles = contarCartasDisponibles();
		}
	}
	/**
	 * cambiarSentido
	 * El metodo tiene la responsabilidad de cambiar el sentido de juego entre los valores SENTIDO_HORARIO y SENTIDO_ANITHORARIO
	 */
	public void cambiarSentido()
	{
		if(this.sentido == SENTIDO_ANTIHORARIO)
		{
			this.sentido = SENTIDO_HORARIO;
		}
		else
		{
			this.sentido = SENTIDO_ANTIHORARIO;
		}
	}
	/**
	 * darProximoTurno
	 * @param salto
	 * @return
	 * El metodo tiene la responsabilidad de retornar el proximo turno teniendo en cuenta el sentido del juego.
	 * En caso que el parametro salto sea verdadero, debera saltar uno de los turnos.
	 * El metodo NO modifica el estado del juego, solo retorna el turno.
	 */
	public int darProximoTurno(boolean salto)
	{
		if(this.sentido == SENTIDO_HORARIO)
		{
			if(salto)
			{
				if(this.turnoActual==1)
				{
					return 3;
				}
				else if (this.turnoActual == 2)
				{
					return 4;
				}
				else if (this.turnoActual  == 3)
				{
					return 1;
				}
				else
				{
					return 2;
				}
			}
			else
			{
				if(this.turnoActual==1)
				{
					return 2;
				}
				else if (this.turnoActual == 2)
				{
					return 3;
				}
				else if (this.turnoActual  == 3)
				{
					return 4;
				}
				else
				{
					return 1;
				}
			}
			
		}
		else 
		{
			if(salto)
			{
				if(this.turnoActual==1)
				{
					return 3;
				}
				else if (this.turnoActual == 2)
				{
					return 4;
				}
				else if (this.turnoActual  == 3)
				{
					return 1;
				}
				else
				{
					return 2;
				}
			}
			else
			{
				if(this.turnoActual==1)
				{
					return 4;
				}
				else if (this.turnoActual == 2)
				{
					return 1;
				}
				else if (this.turnoActual  == 3)
				{
					return 2;
				}
				else
				{
					return 3;
				}
			}
			
		}
	}
	/**
	 * darListadoCartasJugador
	 * @param jugador
	 * @return
	 * El metodo tiene la responsabilidad de retornar una matriz entera de tantas filas como cartas tenga el jugador 
	 * y de dos columnas para almacenar las posiciones de ubicacion de cada carta.
	 *  con el listado de cartas que tiene el jugador que 
	 * es enviado como parametro. la matriz debe almacenar las posiciones i,j de la ubicacion de cada una de las cartas.
	 * 
	 */
	public int[][] darListadoCartasJugador(int jugador)
	{
		int cantidadCartas = contarCartasJugador(jugador);
		
		//una matriz de tantas filas como cartas tenga el jugador
		int[][] respuesta = new int[cantidadCartas][2];
		int contadorCantidad =0;
		
		for(int i = 0; i<NUMERO_CARTAS; i++)
		{
			for (int j = 0; j < NUMERO_COLORES; j++)
			{
				int carta = this.conjuntoCartas[i][j];
				if(carta==jugador)
				{
					int[] coordenada = new int[2];
					coordenada[0]=i;
					coordenada[1]=j;
					respuesta[contadorCantidad]= coordenada;
					contadorCantidad++;
				}
			}
		}
		return respuesta;
	}
	/**
	 * contarCartasJugador
	 * @param jugador
	 * @return
	 * El metodo tiene la responsabilidad de contar las cartas del jugador que viene como parametro. 
	 * Retorna el numero de cartas
	 */
	public int contarCartasJugador(int jugador)
	{
		int respuesta =0;
		
		for(int i = 0; i<NUMERO_CARTAS; i++)
		{
			for (int j = 0; j < NUMERO_COLORES; j++)
			{
				int carta = this.conjuntoCartas[i][j];
				if(carta==jugador)
				{
					respuesta++;
				}
			}
		}
		return respuesta;
	}
	/**
	 * contarCartasDisponibles
	 * @return
	 * El metodo tiene la responsabilidad de contar la cantidad de cartas que estan disponibles para repartir
	 * Incluyendo las cartas que se encuentran ya jugadas y las que no han sido repartidas.
	 */
	public int contarCartasDisponibles()
	{
		int respuesta =0;
		
		for(int i = 0; i<NUMERO_CARTAS; i++)
		{
			for (int j = 0; j < NUMERO_COLORES; j++)
			{
				int carta = this.conjuntoCartas[i][j];
				if(carta==CARTA_NO_USADA || carta == CARTA_USADA)
				{
					respuesta++;
				}
			}
		}
		return respuesta;
	}
	
	/**
	 * darCartaEnJuego
	 * @return
	 * El metodo tiene la responsabilidad de dar la carta que se encuentra activa (en juego).
	 * Retorna un arreglo de dos posiciones con las coordenadas i , j de la posicion de la carta en juego
	 */
	public int[] darCartaEnJuego()
	{
		for (int i = 0; i < NUMERO_CARTAS; i++)
		{
			for (int j = 0; j < NUMERO_COLORES; j++)
			{
				if(conjuntoCartas[i][j]== CARTA_ACTUAL)
				{
					return new int[] {i,j};
				}
					
			}
		}
		return new int[] {0,0};
	}
	/**
	 * descartar
	 * @param jugador
	 * @param color
	 * @param valor
	 * El metodo tiene la responsabilidad de descartar una carta de la mano del jugador que viene como parametro.
	 * Para ello debe localizar dicha carta en la matriz y cambiar su valor a CARTA_ACTUAL indicando que ahora es la carta en 
	 * juego, tambien debe modificar el valor de la anterior carta activa a CARTA_USADA indicando que la carta ya fue jugada
	 * 
	 */
	public void descartar(int jugador, int color, int valor) 
	{
		//la carta que viene como parametro debe ser convertida en la nueva carta activa
		conjuntoCartas[valor][color]=CARTA_ACTUAL;
		//la anterior carta activa debe ser convertida en carta usada
		conjuntoCartas[filaValorCartaActiva][columnaColorCartaActiva]=CARTA_USADA;
		//modificar las coordenadas de la nueva carta actual
		filaValorCartaActiva = valor;
		columnaColorCartaActiva = color;
	}
	/**
	 * darColorActual
	 * @return
	 * Retorna el color actual que se esta jugando. En caso de que haya actividad del comodin retornara el color 
	 * seleccionado por el comodin, en caso contrario retornara el color de la carta en juego
	 */
	public int darColorActual() 
	{
		if(comodinActivo>0)
		{
			//System.out.println(colorComodinActivo);
			return colorComodinActivo;
		}
		else
		{
			//System.out.println(columnaColorCartaActiva);
			return columnaColorCartaActiva;
		}
	}
	/**
	 * darValorActual
	 * @return
	 * Retorna el valor de la carta en juego
	 */
	public int darValorActual() 
	{
		return filaValorCartaActiva;
	}
	
	
	/**
	 * avisarUNO
	 * @param jugador
	 * @param valor
	 * Modifica el aviso de UNO para un jugador que viene como parametro. 
	 */
	public void avisarUNO(int jugador, boolean valor) 
	{
		this.avisoUNO[jugador-1]=valor;
		
	}
	/**
	 * hayAvisoUNO
	 * @param jugador
	 * @return
	 * Retorna true si el jugador actual ha avisado un UNO, false de lo contrario
	 */
	public boolean hayAvisoUNO(int jugador)
	{
		return this.avisoUNO[jugador-1];
	}
	/**
	 * translateCardNumberCode
	 * @param code
	 * @return
	 * Retorna el valor numerico de una carta dada su posicion (fila) en la matriz.
	 */
	public static  int translateCardNumberCode(int code)
	{
		int number = 0;
		if (code == 1 || code == 2)
		{
			number = 1;
		}
		else if (code == 3 || code == 4)
		{
			number = 2;
		}
		else if (code == 5 || code == 6)
		{
			number = 3;
		}
		else if (code == 7 || code == 8)
		{
			number = 4;
		}
		else if (code == 9 || code == 10)
		{
			number = 5;
		}
		else if (code == 11 || code == 12)
		{
			number = 6;
		}
		else if (code == 13 || code == 14)
		{
			number = 7;
		}
		else if (code == 15 || code == 16)
		{
			number = 8;
		}
		else if (code == 17 || code == 18)
		{
			number = 9;
		}
		//System.out.println("Traduccion codigo: "+code+", numero="+number);
		return number;
	}

	/**
	 * esMasDos
	 * @param valor
	 * @return
	 * Retorna true si la posicion enviada corresponde a una carta +2
	 */
	public static boolean esMasDos(int valor)
	{
		if(valor == POSICION_CARTA_ROBA2 || valor == POSICION_CARTA_ROBA2+1)
		{
			return true;
		}
		return false;
	}
	/**
	 * esMasCuatro
	 * @param valor
	 * @return
	 * Retorna true si la posicion enviada corresponde a una carta +4
	 */
	public static boolean esMasCuatro(int valor)
	{
		if(valor == POSICION_CARTA_ROBA4 )
		{
			return true;
		}
		return false;
	}
	/**
	 * esComodin
	 * @param valor
	 * @return
	 * Retorna true si la posicion enviada corresponde a una carta comodin
	 */
	public static boolean esComodin(int valor)
	{
		if(valor == POSICION_CARTA_COMODIN )
		{
			return true;
		}
		return false;
	}
	/**
	 * esSalto
	 * @param valor
	 * @return
	 * Retorna true si la posicion enviada corresponde a una carta de salto de turno
	 */
	public static boolean esSalto(int valor)
	{
		if(valor == POSICION_CARTA_SALTO || valor == POSICION_CARTA_SALTO+1 )
		{
			return true;
		}
		return false;
	}
	/**
	 * esInvierte
	 * @param valor
	 * @return
	 * Retorna true si la posicion enviada corresponde a una carta de invertir sentido
	 */
	public static boolean esInvierte(int valor)
	{
		if(valor == POSICION_CARTA_INVIERTE || valor == POSICION_CARTA_INVIERTE+1 )
		{
			return true;
		}
		return false;
	}
	/**
	 * esNumero
	 * @param valor
	 * @return
	 * Retorna true si la posicion enviada corresponde a una carta numerica
	 */
	public static boolean esNumero(int valor)
	{
		if(valor >=0 && valor < POSICION_CARTA_ROBA2 )
		{
			return true;
		}
		return false;
	}
	/**
	 * esCartaAccion
	 * @param valor
	 * @return
	 * Retorna true si la posicion enviada corresponde a una carta de accion
	 */
	public boolean esCartaAccion(int valor) 
	{
		if(valor >= POSICION_CARTA_ROBA2)
		{
			return true;
		}
		return false;
	}
	/**
	 * hayGanador
	 * @return
	 * Cuenta las cartas de cada jugador, retorna el numero del jugador si hay un ganador, -1 si nadie ha ganado
	 */
	public int hayGanador() 
	{
		for(int i = 1; i<= UNOModelo.NUMERO_JUGADORES;i++)
		{
			int conteo = contarCartasJugador(i);
			if(conteo == 0)
			{
				return i;
			}
		}
		return -1;
	}

	/**
	 * getConjuntoCartas
	 * @return
	 * Retorna la matriz con el conjunto de cartas
	 */
	public int[][] getConjuntoCartas()
	{
		return conjuntoCartas;
	}

	/**
	 * setConjuntoCartas
	 * @param conjuntoCartas
	 * cambia la matriz del conjunto de cartas
	 */
	public void setConjuntoCartas(int[][] conjuntoCartas)
	{
		this.conjuntoCartas = conjuntoCartas;
	}

	/**
	 * getComodinActivo
	 * @return
	 * Retorna el numero de turnos de actividad del comodin.
	 */
	public int getComodinActivo()
	{
		return comodinActivo;
	}

	/**
	 * setComodinActivo
	 * @param comodinActivo
	 * Cambia el numero de turnos de actividad de un comodin
	 */
	public void setComodinActivo(int comodinActivo)
	{
		this.comodinActivo = comodinActivo;
	}

	/**
	 * getColorComodinActivo
	 * @return
	 * Retorna el color que ha sido definido por un comodin
	 */
	public int getColorComodinActivo()
	{
		return colorComodinActivo;
	}

	/**
	 * setColorComodinActivo
	 * @param colorComodinActivo
	 * Cambia el color que ha sido definido por un comodin
	 */
	public void setColorComodinActivo(int colorComodinActivo)
	{
		this.colorComodinActivo = colorComodinActivo;
	}

	/**
	 * getTurnoActual
	 * @return
	 * Retorna el numero de turno actual
	 */
	public int getTurnoActual()
	{
		return turnoActual;
	}

	/**
	 * setTurnoActual
	 * @param nuevoTurno
	 * Modifica el turno actual y desactiva el aviso de UNO para el jugador nuevo
	 */
	public void setTurnoActual(int nuevoTurno)
	{
		this.turnoActual = nuevoTurno;
		//cancelar aviso de UNO 
		avisarUNO(nuevoTurno, false);
		
	}

	/**
	 * getSentido
	 * @return
	 * Retorna el sentido de juego
	 */
	public int getSentido()
	{
		return sentido;
	}

	/**
	 * setSentido
	 * @param sentido
	 * Cambia el sentido de juego
	 */
	public void setSentido(int sentido)
	{
		this.sentido = sentido;
	}
}
