package gui;

import gui.util.ScrabbleBoardLabel;
import gui.util.ScrabbleTrayLabel;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.scrabble.ScrabbleCore;
import net.miginfocom.swing.MigLayout;

public class ScrabbleWindow extends JFrame implements MouseListener, ActionListener{

	private JPanel contentPane;
	private HashMap<String, ImageIcon> imageIcons = new HashMap<String, ImageIcon>();
	private ScrabbleBoardLabel[][] boardLabels = new ScrabbleBoardLabel[ScrabbleCore.TAM_TABLERO][ScrabbleCore.TAM_TABLERO];
	private ScrabbleCore scrabbleCore = new ScrabbleCore();
	private ScrabbleTrayLabel[] trayLabels = new ScrabbleTrayLabel[ScrabbleCore.TAM_BANDEJA];
	public static final int TRAY_ROW=16;
	public static final int TRAY_START_COLUMN=4;
	
	private char actualChar = ' ';
	private boolean activeChar = false;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScrabbleWindow frame = new ScrabbleWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ScrabbleWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 620, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][]", "[][][][][][][][][][][][][][][][][][]"));
		
		JButton btnConfirmar = new JButton("Confirmar");
		contentPane.add(btnConfirmar, "cell 1 17 4 1");
		btnConfirmar.addActionListener(this);
		
		
		JButton btnLimpiar = new JButton("Limpiar");
		contentPane.add(btnLimpiar, "cell 11 17 3 1");
		btnLimpiar.addActionListener(this);
		
		
		
		
		initTray();
		initBoardLabels();
		initIcons();
		refreshBoard();
		refreshTray();
		refreshHeader();
	}

	
	private void initTray() 
	{
		for (int i = 0; i < ScrabbleCore.TAM_BANDEJA; i++) 
		{
			int column = TRAY_START_COLUMN +i;
			this.trayLabels[i] = new ScrabbleTrayLabel(' ');
			this.trayLabels[i].addMouseListener(this);
			contentPane.add(this.trayLabels[i], "cell "+column+" "+ TRAY_ROW);
		}
		
	}
	private void refreshTray()
	{
		char[] pieces = this.scrabbleCore.darLetrasJugador(this.scrabbleCore.getTurnoActual());
		for (int i = 0; i < pieces.length; i++)
		{
			this.trayLabels[i].setValue(pieces[i]);
			this.trayLabels[i].setIcon(this.imageIcons.get(pieces[i]+""));
			this.trayLabels[i].setEnabled(true);
		}
	}

	private void refreshBoard() 
	{
		char[][] board = this.scrabbleCore.getTablero();
		for (int i = 0; i < board.length; i++) 
		{
			for (int j = 0; j < board[i].length; j++) 
			{
				char element = board[i][j] ;
				ImageIcon icon = this.imageIcons.get(element+"");
				boardLabels[i][j].setIcon(icon);
			}
		}
		
	}

	private void initBoardLabels() 
	{
		for (int i = 0; i < ScrabbleCore.TAM_TABLERO; i++) 
		{
			for (int j = 0; j < ScrabbleCore.TAM_TABLERO; j++) 
			{
				this.boardLabels[i][j] = new ScrabbleBoardLabel (i,j);
				this.boardLabels[i][j].addMouseListener(this);
				contentPane.add(this.boardLabels[i][j], "cell "+j+" "+i);
			}
		}
		
	}

	private void initIcons() 
	{
		//first pieces
		char[] pieces = this.scrabbleCore.getFichas();
		for (int i = 0; i < pieces.length; i++) 
		{
			this.imageIcons.put(pieces[i]+"", new ImageIcon("./img/big/"+pieces[i]+".jpg"));
		}
		//second special and blank squares
		
		this.imageIcons.put("_", new ImageIcon("./img/big/_.jpg"));
		this.imageIcons.put("*", new ImageIcon("./img/big/centro.jpg"));
		this.imageIcons.put("2", new ImageIcon("./img/big/dobleletra.jpg"));
		this.imageIcons.put("3", new ImageIcon("./img/big/tripleletra.jpg"));
		this.imageIcons.put("4", new ImageIcon("./img/big/doblepalabra.jpg"));
		this.imageIcons.put("5", new ImageIcon("./img/big/triplepalabra.jpg"));
	}
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if(e.getSource().getClass().getSimpleName().equals("ScrabbleTrayLabel"))
		{
			
			ScrabbleTrayLabel label = (ScrabbleTrayLabel)e.getSource();
			char valor = label.getValue();
			this.actualChar=valor;
			this.activeChar=true;
			label.setEnabled(false);
		}
		else
		{
			ScrabbleBoardLabel label = (ScrabbleBoardLabel)e.getSource();
			int fila = label.getRow();
			int col = label.getColumn();
			
			this.scrabbleCore.entrarLetra(this.actualChar, fila, col, this.scrabbleCore.getTurnoActual());
			this.activeChar=false;
		}
		refreshBoard();
		refreshTray();
		
	}
	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		if(ae.getActionCommand().equals("Limpiar"))
		{
			this.scrabbleCore.cancelarJugada();
		}
		else if(ae.getActionCommand().equals("Confirmar"))
		{
			int resultado = this.scrabbleCore.confirmarJugada(this.scrabbleCore.getTurnoActual());
			if(resultado!=-1)
			{
				//iniciar jugada para el proximo turno
				this.scrabbleCore.iniciarJugada();
				javax.swing.JOptionPane.showMessageDialog(this,"Palabra aceptada,  ("+resultado+") puntos!");
				
			}
			else
			{
				this.scrabbleCore.cancelarJugada();
				javax.swing.JOptionPane.showMessageDialog(this,"Palabra denegada ");
			}
		}
		refreshBoard();
		refreshTray();
		refreshHeader();
		
	}
	private void refreshHeader()
	{
		int t = this.scrabbleCore.getTurnoActual();
		int p = this.scrabbleCore.getPuntajeJugador(t);
		int t2=t;
		if(t==0)t2=4;
		this.setTitle(" Jugador "+(t2)+ " : "+p+" puntos");
		if(t==0)
		{
			this.getContentPane().setBackground(new Color(0, 127, 127));
		}
		else if( t == 1)
		{
			this.getContentPane().setBackground(new Color(255, 90, 53));
		}
		else if( t == 2)
		{
			this.getContentPane().setBackground(new Color(255, 242, 68));
		}
		else if( t == 3)
		{
			this.getContentPane().setBackground(new Color(92, 255, 71));
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
}
