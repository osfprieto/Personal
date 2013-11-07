package button;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ButtonFrame extends JFrame
{
	private JButton plainJButton; // button with just text
	private JButton fancyJButton; // button with icons    

	// ButtonFrame adds JButtons to JFrame
	public ButtonFrame()
	{
		super( "Testing Buttons" );
		setLayout( new FlowLayout() ); // set frame layout

		plainJButton = new JButton( "Plain Button" ); // button with text
		add( plainJButton ); // add plainJButton to JFrame

		Icon btn1 = new ImageIcon( "img\\btn1.gif" );
		Icon btn2 = new ImageIcon( "img\\btn2.gif" );
		fancyJButton = new JButton("Fancy Button", btn1 ); // set image  
		fancyJButton.setRolloverIcon( btn2 ); // set rollover image       
		add( fancyJButton ); // add fancyJButton to JFrame

		// create new ButtonHandler for button event handling
		ButtonHandler handler = new ButtonHandler();         
		fancyJButton.addActionListener( handler );           
		plainJButton.addActionListener( handler );           
	} // end ButtonFrame constructor

	// inner class for button event handling
	private class ButtonHandler implements ActionListener
	{
		// handle button event
		public void actionPerformed( ActionEvent event )
		{
			JOptionPane.showMessageDialog( ButtonFrame.this, String.format(
					"You pressed: %s", event.getActionCommand() ) );
		}
	} 
}

