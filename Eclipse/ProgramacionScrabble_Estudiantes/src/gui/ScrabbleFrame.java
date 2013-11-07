package gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;

import model.scrabble.ScrabbleCore;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

public class ScrabbleFrame {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScrabbleFrame window = new ScrabbleFrame();
					window.frame.setVisible(true);
					ChipTrayFrame controls = new ChipTrayFrame();
					
					controls.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ScrabbleFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 627, 525);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[pref!,grow][][][][][][][][][][][][][][]", "[][][][][][][][][][][][][][][]"));
		
		for (int i = 0; i < ScrabbleCore.TAM_TABLERO; i++) 
		{
			for (int j = 0; j < ScrabbleCore.TAM_TABLERO; j++) 
			{
				JButton button = new JButton("");
				String pos = i+" "+j;
				button.setPreferredSize(new Dimension(20,20));
				frame.getContentPane().add(button, "cell "+pos);
			}
		}
		
		
		
		
	}

}
