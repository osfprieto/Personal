package gui.util;

import javax.swing.JLabel;

public class ScrabbleTrayLabel extends JLabel{
	private char value;
	public ScrabbleTrayLabel(char nValue)
	{
		super();
		this.value= nValue;
	}
	public char getValue() {
		return value;
	}
	public void setValue(char value) {
		this.value = value;
	}
	
	
}
