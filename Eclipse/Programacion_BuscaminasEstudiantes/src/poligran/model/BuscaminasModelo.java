package poligran.model;

public class BuscaminasModelo
{
	public static int MAX_TABLERO = 10;
	public static int MINA = -1;
	public static int VACIO = 0;
	public static int MAX_MINAS = 5;
	public static int MAX_DIRECCIONES = 8;

	public int[][] tablero = new int[MAX_TABLERO][MAX_TABLERO];
	public boolean[][] casillas_abiertas = new boolean[MAX_TABLERO][MAX_TABLERO];
	public boolean[][] casillas_visitadas = new boolean[MAX_TABLERO][MAX_TABLERO];

	// arreglos direccionales

	public static int[] DESPLAZAMIENTOS_FILAS = { -1, -1, 0, 1, 1, 1, 0, -1 };
	public static int[] DESPLAZAMIENTOS_COLUMNAS = { 0, 1, 1, 1, 0, -1, -1, -1 };

	/**
	 * 
	 */
	public BuscaminasModelo()
	{
		inicializarMinas();
		limpiarVisitados();
	}

	/**
	 * limpiarVisitados
	 * El metodo tiene la responsabilidad de limpiar las casillas visitadas
	 */
	private void limpiarVisitados()
	{
		casillas_visitadas = new boolean[MAX_TABLERO][MAX_TABLERO];
	}

	/**
	 * inicializarMinas
	 * El metodo tiene la responsabilidad de inicializar el tablero poniendo MAX_MINAS via llamado al metodo instalarMina sobre posiciones aleatorias
	 */
	private void inicializarMinas()
	{
		// Seleccione aleatoriamente lugares donde poner minas
		for (int i = 0; i < MAX_MINAS; i++)
		{
			int filamina = darPosicionAleatoria();
			int columnamina = darPosicionAleatoria();
			instalarMina(filamina, columnamina);

		}
	}

	/**
	 * instalarMina
	 * @param filamina
	 * @param columnamina
	 * El metodo tiene como responsabilidad instalar una mina en una posicion del tablero si en esta posicion NO HAY PREVIAMENTE UNA MINA. 
	 * Para ello debe ponerla en la posicion indicada, y aumentar en 1 los valores de las casillas adyacentes en las 8 direcciones definidas 
	 * si estas NO son minas y si estan DENTRO del tablero
	 * 
	 */
	private void instalarMina(int filamina, int columnamina)
	{
		if (tablero[filamina][columnamina] != MINA)
		{
			//TODO: INICIO PUNTO 1
			tablero[filamina][columnamina] = MINA;
			
			//Verifica que 
			//no sea 0 para evitar IndexOutOfBoundsException, también verifica que
			//la de arriba no sea una mina, de no verificar la quitaría.
			//El orden de estas expresiones lógicas en el if es también importante
			//para evitar las excepciones
			
			//Arriba
			int filArriba = filamina + DESPLAZAMIENTOS_FILAS[0];
			int colArriba = columnamina + DESPLAZAMIENTOS_COLUMNAS[0];
			if(filamina>0 && tablero[filArriba][colArriba]!=MINA)
				tablero[filArriba][colArriba]++;
			
			//Arriba derecha
			int filArribaDerecha = filamina + DESPLAZAMIENTOS_FILAS[1];
			int colArribaDerecha = columnamina + DESPLAZAMIENTOS_COLUMNAS[1];
			if(filamina>0 && columnamina<MAX_TABLERO-1 && tablero[filArribaDerecha][colArribaDerecha]!=MINA)
				tablero[filArribaDerecha][colArribaDerecha]++;
			
			//Derecha
			int filDerecha = filamina + DESPLAZAMIENTOS_FILAS[2];
			int colDerecha = columnamina + DESPLAZAMIENTOS_COLUMNAS[2];
			if(columnamina<MAX_TABLERO-1 && tablero[filDerecha][colDerecha]!=MINA)
				tablero[filDerecha][colDerecha]++;
			
			//Derecha abajo
			int filAbajoDerecha = filamina + DESPLAZAMIENTOS_FILAS[3];
			int colAbajoDerecha = columnamina + DESPLAZAMIENTOS_COLUMNAS[3];
			if(filamina<MAX_TABLERO-1 && columnamina<MAX_TABLERO-1 && tablero[filAbajoDerecha][colAbajoDerecha]!=MINA)
				tablero[filAbajoDerecha][colAbajoDerecha]++;
			
			//Abajo
			int filAbajo = filamina + DESPLAZAMIENTOS_FILAS[4];
			int colAbajo = columnamina + DESPLAZAMIENTOS_COLUMNAS[4];
			if(filamina<MAX_TABLERO-1 && tablero[filAbajo][colAbajo]!=MINA)
				tablero[filAbajo][colAbajo]++;
			
			//IzquierdaAbajo
			int filAbajoIzquierda = filamina + DESPLAZAMIENTOS_FILAS[5];
			int colAbajoIzquierda = columnamina + DESPLAZAMIENTOS_COLUMNAS[5];
			if(filamina<MAX_TABLERO-1 && columnamina>0 && tablero[filAbajoIzquierda][colAbajoIzquierda]!=MINA)
				tablero[filAbajoIzquierda][colAbajoIzquierda]++;
			
			//Izquierda
			int filIzquierda = filamina + DESPLAZAMIENTOS_FILAS[6];
			int colIzquierda = columnamina + DESPLAZAMIENTOS_COLUMNAS[6];
			if(columnamina>0 && tablero[filIzquierda][colIzquierda]!=MINA)
				tablero[filIzquierda][colIzquierda]++;
			
			//Izquierda arriba
			int filArribaIzquierda = filamina + DESPLAZAMIENTOS_FILAS[7];
			int colArribaIzquierda = columnamina + DESPLAZAMIENTOS_COLUMNAS[7];
			if(columnamina>0 && filamina>0 && tablero[filArribaIzquierda][colArribaIzquierda]!=MINA)
				tablero[filArribaIzquierda][colArribaIzquierda]++;
			
			//FIN PUNTO 1
		}
	}

	/**
	 * jugar
	 * @param fila
	 * @param columna
	 * @return true si el juego continua, false si exploto una mina
	 * Este metodo tiene la responsabilidad de jugar una casilla enviada
	 * como parametro
	 */
	public boolean jugar(int fila, int columna)
	{
		if (tablero[fila][columna] == MINA)
		{
			casillas_abiertas[fila][columna] = true;
			return false;
		}
		else
		{
			limpiarVisitados();
			descubrirCasillasAdyacentes(fila, columna);
			return true;
		}
	}

	/**
	 * descubrirCasillasAdyacentes
	 * @param fila
	 * @param columna
	 * El metodo tiene la responsabilidad de destapar las casillas adyacentes a la casilla enviada por parametro en caso de ser una casilla vacia.
	 * Este metodo RECURSIVO tiene los siguientes casos:
	 * 		Si la casilla esta previamente visitada, retorna
	 * 		Si la casilla no habia sido visitada, muestra la casilla usando la matriz de casillas_abiertas y marca la casilla como visitada
	 * 		Si la posicion actual es mayor a vacio, muestra la casilla  
	 * 		Si la casilla actual es vacia, expande el llamado recursivamente en todas direcciones (mediante el uso de los arreglos DESPLAZAMIENTOS_COLUMNAS y DESPLAZAMIENTOS_FILAS)
	 * 			solamente si las casillas destino se encuentran DENTRO del tablero.
	 */
	private void descubrirCasillasAdyacentes(int fila, int columna)
	{

		
		//TODO: INICIO PUNTO 2: CASOS BASE
		if(!casillas_visitadas[fila][columna]){

			casillas_abiertas[fila][columna] = true;
			casillas_visitadas[fila][columna] = true;
			
		//FIN PUNTO 2
		
		// Caso inductivo, si es una casilla vacia expandir hacia todas las direcciones
		
		//TODO: INICIO PUNTO 3: CASO INDUCTIVO
			
			if(tablero[fila][columna]==VACIO){
				
				//Arriba
				int filArriba = fila + DESPLAZAMIENTOS_FILAS[0];
				int colArriba = columna + DESPLAZAMIENTOS_COLUMNAS[0];
				if(fila>0)
					descubrirCasillasAdyacentes(filArriba, colArriba);
				
				//Arriba derecha
				int filArribaDerecha = fila + DESPLAZAMIENTOS_FILAS[1];
				int colArribaDerecha = columna + DESPLAZAMIENTOS_COLUMNAS[1];
				if(fila>0 && columna<MAX_TABLERO-1)
					descubrirCasillasAdyacentes(filArribaDerecha, colArribaDerecha);
				
				//Derecha
				int filDerecha = fila + DESPLAZAMIENTOS_FILAS[2];
				int colDerecha = columna + DESPLAZAMIENTOS_COLUMNAS[2];
				if(columna<MAX_TABLERO-1)
					descubrirCasillasAdyacentes(filDerecha, colDerecha);
				
				//Derecha abajo
				int filAbajoDerecha = fila + DESPLAZAMIENTOS_FILAS[3];
				int colAbajoDerecha = columna + DESPLAZAMIENTOS_COLUMNAS[3];
				if(fila<MAX_TABLERO-1 && columna<MAX_TABLERO-1)
					descubrirCasillasAdyacentes(filAbajoDerecha, colAbajoDerecha);
				
				//Abajo
				int filAbajo = fila + DESPLAZAMIENTOS_FILAS[4];
				int colAbajo = columna + DESPLAZAMIENTOS_COLUMNAS[4];
				if(fila<MAX_TABLERO-1)
					descubrirCasillasAdyacentes(filAbajo, colAbajo);
				
				//IzquierdaAbajo
				int filAbajoIzquierda = fila + DESPLAZAMIENTOS_FILAS[5];
				int colAbajoIzquierda = columna + DESPLAZAMIENTOS_COLUMNAS[5];
				if(fila<MAX_TABLERO-1 && columna>0)
					descubrirCasillasAdyacentes(filAbajoIzquierda, colAbajoIzquierda);
				
				//Izquierda
				int filIzquierda = fila + DESPLAZAMIENTOS_FILAS[6];
				int colIzquierda = columna + DESPLAZAMIENTOS_COLUMNAS[6];
				if(columna>0)
					descubrirCasillasAdyacentes(filIzquierda, colIzquierda);
				
				//Izquierda arriba
				int filArribaIzquierda = fila + DESPLAZAMIENTOS_FILAS[7];
				int colArribaIzquierda = columna + DESPLAZAMIENTOS_COLUMNAS[7];
				if(columna>0 && fila>0)
					descubrirCasillasAdyacentes(filArribaIzquierda, colArribaIzquierda);
				
			}
			
		}
		//FIN PUNTO 3
	}

	/**
	 * darPosicionAleatoria
	 * @return
	 * Este metodo tiene la responsabilidad de retornar un valor aleatorio entre 0 y el tama�o del tablero
	 */
	public static int darPosicionAleatoria()
	{
		double millis = Math.random() * 100000;
		millis = Math.round(millis);
		millis = millis % (MAX_TABLERO - 1);
		// millis++;
		return (int) millis;
	}

	/**
	 * validarGanador
	 * @return
	 * El metodo tiene la resposabilidad de validar si ya existe un ganador en el juego. Retorna true si no hay ninguna casilla abierta que NO sea una mina
	 * retorna falso en el caso contrario
	 */
	public boolean validarGanador()
	{
		// recorrer todo el tablero, si hay alguna casilla escondida que no sea
		// mina retorno false

		//TODO: INICIO PUNTO 4 : BONO
		
		for(int i=0;i<MAX_TABLERO;i++)
			for(int j=0;j<MAX_TABLERO;j++)
				if(tablero[i][j]!=MINA && !casillas_abiertas[i][j])
					return false;
		
		//FIN PUNTO 4
		return true;
	}


	/**
	 * imprimirTablero
	 * Este metodo tiene la responsabilidad de imprimir por la consola el tablero.
	 */
	private void imprimirTablero()
	{
		for (int i = 0; i < MAX_TABLERO; i++)
		{
			for (int j = 0; j < MAX_TABLERO; j++)
			{
				int valor = tablero[i][j];
				if (valor == MINA)
				{
					System.out.print(valor + "|");
				}
				else
				{
					System.out.print(valor + " |");
				}
			}
			System.out.println();
			for (int j = 0; j < MAX_TABLERO; j++)
			{
				System.out.print("___");
			}
			System.out.println();
		}
	}

	/**
	 * getTablero
	 * @return
	 * El metodo tiene la responsabilidad de retornar el tablero
	 */
	public int[][] getTablero()
	{
		return tablero;
	}

	/**
	 * setTablero
	 * @param tablero
	 */
	public void setTablero(int[][] tablero)
	{
		this.tablero = tablero;
	}

	/**
	 * getCasillas_abiertas
	 * @return
	 */
	public boolean[][] getCasillas_abiertas()
	{
		return casillas_abiertas;
	}

	/**
	 * setCasillas_abiertas
	 * @param casillas_abiertas
	 */
	public void setCasillas_abiertas(boolean[][] casillas_abiertas)
	{
		this.casillas_abiertas = casillas_abiertas;
	}

	/**
	 * getCasillas_visitadas
	 * @return
	 */
	public boolean[][] getCasillas_visitadas()
	{
		return casillas_visitadas;
	}

	/**
	 * setCasillas_visitadas
	 * @param casillas_visitadas
	 */
	public void setCasillas_visitadas(boolean[][] casillas_visitadas)
	{
		this.casillas_visitadas = casillas_visitadas;
	}
}
