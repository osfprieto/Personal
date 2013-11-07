package player;


import playerFactory.IJugador;
import uno.model.UNOFachada;
import uno.model.UNOModelo;

public class JugadorAutomaticoPrueba implements IJugador
{

	//El método jugar tiene la responsabilidad de ejecutar una sola jugada del juego, teniendo que evaluar entre las diferentes 
	//opciones de juego que tiene. Recibe como parametro solamente el numero del turno actual y una variable de tipo UNOFachada
	//que permite acceder a los servicios de consulta sobre el tablero. Para mas información, puede abrir el archivo UNOFachada.java 
	//para revisar la documentacion de los metodos que puede usar. Entre otras, usted debera llamar los metodos jugar, pasar y robar para
	//ejecutar la jugada.
	//En el archivo actual solamente encontrara la signatura del metodo y un ejemplo de llamado el metodo jugar, pasar y robar.
	/* (non-Javadoc)
	 * @see playerFactory.IJugador#jugar(int, uno.model.UNOFachada)
	 */
	public void jugar( int jugador, UNOFachada fachada)
	{
		System.out.println("Llamado el jugador automatico: "+this.getClass().getSimpleName()+" Turno: "+jugador);
		
		//comodin y nuevoColor debe ser una decision que toma el sistema
		boolean jugarComodin = false;
		int seleccionNuevoColor = UNOModelo.COLOR_NINGUNO;
		int cartaAJugarColor = -1;
		int cartaAJugarValor = -1;
		
		//averiguar que carta esta en juego
		int[] cartaArriba = fachada.darCartaEnJuego();
		int cartaArribaValor = cartaArriba[0];
		int cartaArribaColor = cartaArriba[1];
		
		//averiguar que carta jugar, de lo contrario robar o pasar
		int[][]cartasDisponibles = fachada.darCartasJugador(jugador);
		
		// Este jugador de prueba solamente intentara jugar la primera carta que tiene, si no puede roba y pasa.
		cartaAJugarValor = cartasDisponibles[0][0];
		cartaAJugarColor = cartasDisponibles[0][1];
		
		if(cartaAJugarColor == cartaArribaColor && cartaAJugarValor == cartaArribaValor)
		{
			fachada.jugar(  jugador, cartaAJugarColor, cartaAJugarValor, jugarComodin, seleccionNuevoColor);
		}
		else
		{
			fachada.robar();
			fachada.pasar();
		}
	}
	
	

}
