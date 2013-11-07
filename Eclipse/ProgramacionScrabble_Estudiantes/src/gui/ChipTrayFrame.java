package gui;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.scrabble.ScrabbleCore;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

public class ChipTrayFrame {

	public JFrame frame;
	private JButton[] trayButtons= new JButton[ScrabbleCore.TAM_BANDEJA];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChipTrayFrame window = new ChipTrayFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChipTrayFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(800, 100, 113, 463);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[50.00][317.00,grow][-40.00]", "[10.00][265.00][47.00,grow]"));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, "cell 1 1,grow");
		panel.setLayout(new GridLayout(7, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, "cell 1 2,grow");
		panel_1.setLayout(new GridLayout(3, 1, 0, 0));
		
		JLabel lblTurnoJugador = new JLabel("Turno: Jugador 1");
		panel_1.add(lblTurnoJugador);
		
		JButton btnAgregar = new JButton("Agregar");
		panel_1.add(btnAgregar);
		
		for(int i=0;i<ScrabbleCore.TAM_BANDEJA;i++)
		{
			JButton button = new JButton();
			this.trayButtons[i]=button;
			panel.add(button);
		}
		
	}

}
