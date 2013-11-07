package uno.gui;

//Fri Oct 25 18:07:43 EST 2004
//
//Written by Sean R. Owens, sean at guild dot net, released to the
//public domain.  Share and enjoy.  Since some people argue that it is
//impossible to release software to the public domain, you are also free
//to use this code under any version of the GPL, LPGL, or BSD licenses,
//or contact me for use of another license.
//http://darksleep.com/player

//A very simple custom dialog that takes a string as a parameter,
//displays it in a JLabel, along with two Jbuttons, one labeled Yes,
//and one labeled No, and waits for the user to click one of them.

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import uno.model.UNOModelo;

public class CustomDialog extends JDialog implements ActionListener {
 private JPanel myPanel = null;
 private JToggleButton blueButton = null;
 private JToggleButton redButton = null;
 private JToggleButton greenButton = null;
 private JToggleButton yellowButton = null;
 private int answer = -1;
 public int getAnswer() { return answer; }

 public CustomDialog(JFrame frame, boolean modal, String myMessage) {
     
	 super(frame, modal);
	 this.setTitle(myMessage);
     myPanel = new JPanel();
     getContentPane().add(myPanel);
     
     yellowButton = new JToggleButton("");
     yellowButton.setIcon(new ImageIcon("./data/buttons/yellow-button.png"));
     yellowButton.addActionListener(this);
     myPanel.add(yellowButton); 
     
     redButton = new JToggleButton("");
     redButton.setIcon(new ImageIcon("./data/buttons/red-button.png"));
     redButton.addActionListener(this);
     myPanel.add(redButton); 
     
     greenButton = new JToggleButton("");
     greenButton.setIcon(new ImageIcon("./data/buttons/green-button.png"));
     greenButton.addActionListener(this);
     myPanel.add(greenButton); 
     
     blueButton = new JToggleButton("");
     blueButton.setIcon(new ImageIcon("./data/buttons/blue-button.png"));
     blueButton.addActionListener(this);
     myPanel.add(blueButton); 
     
       
     pack();
     setLocationRelativeTo(frame);
     setVisible(true);
 }

 public void actionPerformed(ActionEvent e) {
     if(blueButton == e.getSource()) {
         answer = UNOModelo.COLOR_AZUL;
         setVisible(false);
     }
     else if(redButton == e.getSource()) {
         answer = UNOModelo.COLOR_ROJO;
         setVisible(false);
     }
     else if(greenButton == e.getSource()) {
         answer = UNOModelo.COLOR_VERDE;
         setVisible(false);
     }
     else if(yellowButton == e.getSource()) {
         answer = UNOModelo.COLOR_AMARILLO;
         setVisible(false);
     }
 }
 
}
