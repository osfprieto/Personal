package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class GUI extends JApplet implements ActionListener{
	public static Container cont;
	public static JPanel tablero;
	
	public void init(){
		setSize(400, 300);
		iniciar();
	}
	
	public void iniciar(){
		cont = getContentPane();
		cont.removeAll();
		cont.setLayout(new FlowLayout(FlowLayout.CENTER));
		
	}
	
	public void actionPerformed(ActionEvent arg0){
		
	}
}
