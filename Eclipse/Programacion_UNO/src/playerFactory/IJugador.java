package playerFactory;

import uno.model.UNOFachada;

/**
 * Class: IJugador
 *
 */
public interface IJugador
{
	/**
	 * jugar
	 * @param jugador
	 * @param fachada
	 * El método jugar tiene la responsabilidad de ejecutar una sola jugada del juego, teniendo que evaluar entre las diferentes 
	 * opciones de juego que tiene. Recibe como parametro solamente el numero del turno actual y una variable de tipo UNOFachada
	 * que permite acceder a los servicios de consulta sobre el tablero. Para mas información, puede abrir el archivo UNOFachada.java 
	 * para revisar la documentacion de los metodos que puede usar. Entre otras, usted debera llamar los metodos jugar, pasar y robar para
	 * ejecutar la jugada.
	 */
	public void jugar( int jugador, UNOFachada fachada);
}
