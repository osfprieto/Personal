package uno.gui;

import javax.swing.JLabel;

/**
 * Representa el JLabel de una carta
 * @author jbadillo
 *
 */
public class CardLabel extends JLabel{

	private static final long serialVersionUID = 1L;
		
	private int numero;
	private int color;
	
	public int getNumero() {
		return numero;
	}
	
	public int getColor() {
		return color;
	}
	
	public void setNumero(int num)
	{
		this.numero= num;
	}
	public void setColor (int col)
	{
		this.color = col;
	}
}
