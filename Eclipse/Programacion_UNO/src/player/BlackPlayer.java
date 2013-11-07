package player;

import playerFactory.IJugador;
import uno.model.UNOFachada;
import uno.model.UNOModelo;

public class BlackPlayer implements IJugador {
	
	public void jugar(int jugador, UNOFachada fachada) {
		//comodin y nuevoColor debe ser una decision que toma el sistema
		boolean jugarComodin = false;
		int seleccionNuevoColor = UNOModelo.COLOR_NINGUNO;
		int cartaAJugarColor = -1;
		int cartaAJugarValor = -1;
		
		//averiguar qué carta esta en juego
		int[] cartaArriba = fachada.darCartaEnJuego();
		int cartaArribaValor = cartaArriba[0];
		int cartaArribaColor = cartaArriba[1];
		
		//Si el siguiente jugador tiene o no una sola carta
		int[][] cartasSiguienteJugador = fachada.darCartasJugador((jugador+1)%UNOModelo.NUMERO_JUGADORES);
		boolean unaCartaSiguienteJugador = cartasSiguienteJugador.length==1;
		int valorCartaSiguienteJugador;
		int colorCartaSiguienteJugador;
		
		if(unaCartaSiguienteJugador){
			valorCartaSiguienteJugador = cartasSiguienteJugador[0][0];
			colorCartaSiguienteJugador = cartasSiguienteJugador[0][1];
		}
		else{
			colorCartaSiguienteJugador = 100;
			valorCartaSiguienteJugador = 100;
		}
		
		//averiguar que carta jugar, de lo contrario robar o pasar
		int[][]cartasDisponibles = fachada.darCartasJugador(jugador);
		
		// Este jugador de prueba solamente intentara jugar la primera carta que tiene, si no puede roba y pasa.
		int i;
		for(i=0;i<cartasDisponibles.length;i++){
			cartaAJugarValor = cartasDisponibles[i][0];
			cartaAJugarColor = cartasDisponibles[i][1];
			if((cartaAJugarColor == cartaArribaColor || 
					cartaAJugarValor == cartaArribaValor) && 
					!(cartaAJugarColor == colorCartaSiguienteJugador || 
							cartaAJugarValor == valorCartaSiguienteJugador)) { 
				fachada.jugar(jugador, cartaAJugarColor, cartaAJugarValor, jugarComodin, seleccionNuevoColor);
				return;
			}
		}
		fachada.robar();
		cartasDisponibles = fachada.darCartasJugador(jugador);
		System.out.println(jugador);
		for(i=0;i<cartasDisponibles.length;i++){
			
			cartaAJugarValor = cartasDisponibles[i][0];
			cartaAJugarColor = cartasDisponibles[i][1];
			if((cartaAJugarColor == cartaArribaColor || 
					cartaAJugarValor == cartaArribaValor) && 
					!(cartaAJugarColor == colorCartaSiguienteJugador || 
							cartaAJugarValor == valorCartaSiguienteJugador)) { 
				fachada.jugar(jugador, cartaAJugarColor, cartaAJugarValor, jugarComodin, seleccionNuevoColor);
				return;
			}
		}
		
		/*for(i=0;i<cartasDisponibles.length;i++){
			
			cartaAJugarValor = cartasDisponibles[i][0];
			cartaAJugarColor = cartasDisponibles[i][1];
			if((cartaAJugarColor == cartaArribaColor || 
					cartaAJugarValor == cartaArribaValor)){ 
				fachada.jugar(jugador, cartaAJugarColor, cartaAJugarValor, jugarComodin, seleccionNuevoColor);
				return;
			}
		}*/
		
		fachada.pasar();
	}

}
