package uno.model;


public class UNOFachada 
{
	
	//referencia al modelo
	private UNOModelo uno = new UNOModelo();
	
	public UNOFachada()
	{
		this.uno = new UNOModelo();
		this.uno.reiniciarJuego();
	}
	/**
	 * jugar
	 * El metodo jugar toma una carta (color, valor), valida si es de un jugador y ejecuta la jugada. Retorna el turno
	 * @param jugador
	 * @param color
	 * @param valor
	 * @param comodin
	 * @param nuevoColor
	 * @return
	 * El metodo tiene la responsabilida de evaluar la jugada actual dada la carta que el jugador ha decidido jugar.
	 * Debe aplicar las reglas de juego basado en el tipo de la carta y ejecutar las relas especiales asociadas a las cartas de accion.
	 * Retorna el turno que quedara despues de la jugada.
	 * Recibe como parametro el numero del jugador actual, el color de la carta a jugar, el valor de la carta a jugar, un booleano 
	 * que dice si va a jugar un comodin y el color seleccionado por el comodin
	 */
	public int jugar(int jugador, int color, int valor, boolean comodin, int nuevoColor)
	{
		
		//si la carta es un comodin, se debe cambiar el color por el nuevo
		if(UNOModelo.esComodin(valor))
		{
			this.uno.setComodinActivo(2); 
			this.uno.setColorComodinActivo(nuevoColor);
			this.uno.descartar(jugador, color, valor);
			this.uno.setTurnoActual(this.uno.darProximoTurno(false));
			return this.uno.getTurnoActual();
		}
		if(UNOModelo.esMasCuatro(valor) )
		{
			this.uno.setComodinActivo(2);
			this.uno.setColorComodinActivo(nuevoColor);
			this.uno.darCartasAlJugador(this.uno.darProximoTurno(false), 4);
			this.uno.descartar(jugador, color, valor);
			this.uno.setTurnoActual(this.uno.darProximoTurno(true));
			return this.uno.getTurnoActual();
		}
		//DESDE AQUI SE DEBE REVISAR COLOR O VALOR 
		//si la carta en juego es una carta  +2 o +4 simplemente se debe robar y pasar
		if(UNOModelo.esMasDos(valor))
		{
			//validar si coincide el color
			boolean coincideColor = (color == uno.darColorActual());
			//19 o 20
			boolean coincideValor = (UNOModelo.esMasDos(valor) && UNOModelo.esMasDos(this.uno.darValorActual()));
			if(coincideColor || coincideValor)
			{
				this.uno.setComodinActivo(0);
				this.uno.darCartasAlJugador(this.uno.darProximoTurno(false), 2);
				this.uno.descartar(jugador, color, valor);
				this.uno.setTurnoActual(this.uno.darProximoTurno(true));
				return this.uno.getTurnoActual();
			}
			else
			{
				//castigo
				this.uno.darCartasAlJugador(jugador, 1);
				this.uno.setTurnoActual(this.uno.darProximoTurno(false));
				return this.uno.getTurnoActual();
			}
			
		}
		
		//si la carta es un cambio de sentido se valida igual, pero se debe hacer la modificacion de sentido
		if(UNOModelo.esInvierte(valor))
		{
			boolean coincideColor =  (color == uno.darColorActual());
			boolean coincideValor =  (uno.darValorActual() == UNOModelo.POSICION_CARTA_INVIERTE)||  (uno.darValorActual() == UNOModelo.POSICION_CARTA_INVIERTE+1);
			if(coincideColor || coincideValor )
			{
				this.uno.setComodinActivo(0);
				//descartar
				this.uno.descartar(jugador, color, valor);
				this.uno.cambiarSentido();
				this.uno.setTurnoActual(this.uno.darProximoTurno(false));
				return this.uno.getTurnoActual();
			}
			else
			{
				//castigo
				this.uno.darCartasAlJugador(jugador, 1);
				this.uno.setTurnoActual(this.uno.darProximoTurno(false));
				return this.uno.getTurnoActual();
			}
		}
		//si la carta es un salto de turno se valida igual, pero se debe hacer la modificacion de turno
		if(UNOModelo.esSalto(valor))
		{
			boolean coincideColor =  (color == uno.darColorActual());
			boolean coincideValor =  (uno.darValorActual() == UNOModelo.POSICION_CARTA_SALTO)||  (uno.darValorActual() == UNOModelo.POSICION_CARTA_SALTO+1);
			if(coincideColor || coincideValor)
			{
				this.uno.setComodinActivo(0);
				this.uno.descartar(jugador, color, valor);
				this.uno.setTurnoActual(this.uno.darProximoTurno(true));
				return this.uno.getTurnoActual();
			}
			else
			{
				//castigo
				this.uno.darCartasAlJugador(jugador, 1);
				this.uno.setTurnoActual(this.uno.darProximoTurno(false));
				return this.uno.getTurnoActual();
			}
		}
		
		//se puede asumir que esa carta la tiene el jugador, se requiere revisar si la carta sirve
		if(UNOModelo.esNumero(valor))
		{
			boolean coincideColor =  (color == uno.darColorActual());
			boolean coincideValor = UNOModelo.translateCardNumberCode(uno.darValorActual()) == UNOModelo.translateCardNumberCode(valor);
			if(coincideColor || coincideValor)
			{
				this.uno.setComodinActivo(0);
				this.uno.descartar(jugador, color, valor);
				this.uno.setTurnoActual(this.uno.darProximoTurno(false));
				return this.uno.getTurnoActual();
			}
			else
			{
				//castigo
				this.uno.darCartasAlJugador(jugador, 1);
				this.uno.setTurnoActual(this.uno.darProximoTurno(false));
				return this.uno.getTurnoActual();
			}
		}
		return -1;
		
	}
	
	

	/**
	 * Devuelve una matriz con las cartas de cada jugador.
	 * Cada fila representa una carta, donde la primera columna representa
	 * el valor de la carta y la segunda representa el color
	 * @param jugador
	 * @return
	 */
	public int[][] darCartasJugador(int jugador)
	{
		if(jugador <1 || jugador >4)
		{
			return new int[7][2];
		}
		return uno.darListadoCartasJugador(jugador);
	}
	
	/**
	 * Devuelve un arreglo de dos posiciones, que representa la carta que esta
	 * en el tope de la pila.
	 * La primera componente corresponde al valor de la carta, la segunda
	 * representa el color.
	 * @return
	 */
	public int[] darCartaEnJuego(){
		return this.uno.darCartaEnJuego();
	}

	/**
	 * Metodo invocado en el momento en que el jugador actual decide robar
	 */
	public void robar() 
	{
		this.uno.darCartasAlJugador(this.uno.getTurnoActual(), 1);		
	}

	/**
	 * Metodo invocado en el momento en que el jugador actual decide pasar
	 */
	public void pasar() 
	{
		this.uno.setTurnoActual(this.uno.darProximoTurno(false));
	}

	/**
	 * Metodo invocado en el momento en que el jugador actual dice UNO
	 */
	public void uno() 
	{
		int turnoactual = this.uno.getTurnoActual();
		this.uno.avisarUNO(turnoactual, true);
		
		for(int ijugador =1; ijugador<=UNOModelo.NUMERO_JUGADORES;ijugador++)
		{
			int conteo = this.uno.contarCartasJugador(ijugador);
			if(conteo == 1 && !this.uno.hayAvisoUNO(ijugador))
			{
				this.uno.darCartasAlJugador(ijugador, 2);
			}
		}
		
	}
	/**
	 * darTurno
	 * @return
	 * Retorna el turno actual
	 */
	public int darTurno()
	{
		return this.uno.getTurnoActual();
	}
	/**
	 * darTextoColorActual
	 * @return
	 * retorna el texto que representa el color actual que se encuentra en juego
	 */
	public String darTextoColorActual() 
	{
		int color = this.uno.darColorActual();
		if(color == UNOModelo.COLOR_AMARILLO)
		{
			return "Amarillo";
		}
		else if ( color == UNOModelo.COLOR_AZUL)
		{
			return "Azul";
		}
		else if(color == UNOModelo.COLOR_VERDE)
		{
			return "Verde";
		}
		else if (color == UNOModelo.COLOR_ROJO)
		{
			return "Rojo";
		}
		return "";
	}
	public int darColorActual()
	{
		return this.uno.darColorActual();
	}
	/**
	 * hayAvisoUNO
	 * @param jugador
	 * @return
	 * Retorna true si el jugador enviado como parametro ha avisado un UNO
	 */
	public boolean hayAvisoUNO(int jugador)
	{
		return this.uno.hayAvisoUNO(jugador);
	}
	/**
	 * contarCartas
	 * @param jugador
	 * @return
	 * Retorna el numero de cartas que tiene el jugador enviado como parametro
	 */
	public int contarCartas(int jugador)
	{
		return this.uno.contarCartasJugador(jugador);
	}
	/**
	 * hayGanador
	 * @return
	 * Retorna el numero del jugador si hay un ganador, -1 si nadie ha ganado
	 */
	public int hayGanador()
	{
		return this.uno.hayGanador();
	}
	
	public boolean validarGanador()
	{
		if(this.uno.hayGanador()==-1)
		{
			return false;
		}
		return true;
	}
	
	
}
