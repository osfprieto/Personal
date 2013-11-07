package uno.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import uno.model.UNOFachada;
import uno.model.UNOModelo;

@SuppressWarnings("serial")
public class UNOWindow extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	
	CardLabelFactory cardFactory = new CardLabelFactory();
	
	JPanel panelPlayer1 = new JPanel();
	JPanel panelPlayer2 = new JPanel();
	JPanel panelPlayer3 = new JPanel();
	JPanel panelPlayer4 = new JPanel();
	
	JScrollPane scroll1 = new JScrollPane();
	JScrollPane scroll2 = new JScrollPane();
	JScrollPane scroll3 = new JScrollPane();
	JScrollPane scroll4 = new JScrollPane();
	
	JPanel container1 = new JPanel();
	JPanel container2 = new JPanel();
	JPanel container3 = new JPanel();
	JPanel container4 = new JPanel();
	
	
	UNOFachada fachada = new UNOFachada();
	JPanel panelGame = new JPanel();
	JLabel lblBackground = new JLabel("");
	JPanel panelControls = new JPanel();
	private JToggleButton jbRobar = new JToggleButton();
	private JToggleButton jbPasar = new JToggleButton();
	private JToggleButton jbUNO = new JToggleButton();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UNOWindow frame = new UNOWindow();
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
	public UNOWindow() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		container1.setOpaque(false);
		container2.setOpaque(false);
		container3.setOpaque(false);
		container4.setOpaque(false);
		
		container1.setBounds(12, 12, 570, 180);
		container2.setBounds(696, 12, 570, 180);
		container3.setBounds(716, 479, 550, 180);
		container4.setBounds(12, 479, 570, 180);
		
		contentPane.add(container1);
		contentPane.add(container2);
		contentPane.add(container3);
		contentPane.add(container4);
		
		scroll1 = new JScrollPane(panelPlayer1, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll2 = new JScrollPane(panelPlayer2, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll3 = new JScrollPane(panelPlayer3, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll4 = new JScrollPane(panelPlayer4, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		container1.setLayout(new BorderLayout(0, 0));
		
		container1.add(scroll1);
		container2.setLayout(new BorderLayout(0, 0));
		container2.add(scroll2);
		container3.setLayout(new BorderLayout(0, 0));
		container3.add(scroll3);
		container4.setLayout(new BorderLayout(0, 0));
		container4.add(scroll4);
		
		
		panelGame.setBounds(548, 238, 205, 172);
		
		contentPane.add(panelGame);
		
		setBackgroundLabel();
		createPlayersPanel();
		createGamePanel();
		createCurrentPlayerPanel();
		createButtonsPanel();
	}
	public void setBackgroundLabel()
	{
		panelControls.setBounds(610, 422, 80, 172);
		panelControls.setOpaque(false);
		
		contentPane.add(panelControls);
		lblBackground.setBounds(0, 0, 1280, 720);
		contentPane.add(lblBackground);
		lblBackground.setHorizontalAlignment(SwingConstants.TRAILING);
		lblBackground.setIcon(new ImageIcon("./data/bg/background-Negro.jpg"));
	}
	
	//PLAYER CARDS PANEL
	private void createPlayersPanel() 
	{
		int turno = fachada.darTurno();
		
		if(turno != 1)	createPlayerPanel(panelPlayer1, 1);
		if(turno != 2)	createPlayerPanel(panelPlayer2, 2);
		if(turno != 3)	createPlayerPanel(panelPlayer3, 3);
		if(turno != 4)	createPlayerPanel(panelPlayer4, 4);
	}


	private void createPlayerPanel(JPanel playerPanel, int jugador) {
		
		playerPanel.setBorder(new TitledBorder(new LineBorder(Color.WHITE), "Jugador "+jugador));
		playerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
		playerPanel.setOpaque(false);
		updateOtherPlayerPanel(playerPanel, jugador);
	}
	

	private void updateOtherPlayerPanel(JPanel playerPanel, int jugador) 
	{
		String texto = "Jugador "+(jugador)+" ";
		int conteoCartas = fachada.contarCartas(jugador);
		if(fachada.hayAvisoUNO(jugador))
		{
			texto+="UNO Avisado!";
		}
		else
		{
			if (conteoCartas == 0)
			{
				texto+="GANADOR!!!";
			}
			else if(conteoCartas == 1)
			{
				texto+="Queda una carta, el jugador no ha avisado UNO!";
			}
			else if (conteoCartas >1)
			{
				texto+="Quedan "+conteoCartas+" cartas";
			}
		}
		playerPanel.setBorder(new TitledBorder(new LineBorder(Color.black), texto));
		playerPanel.setBackground(new Color(3,112,3));
		playerPanel.removeAll();
		
		int[][] cards = fachada.darCartasJugador(jugador);
		
		for (int j = 0; j < cards.length-1; j++) {
			JLabel card = cardFactory.getCardLabel(CardLabelFactory.CARTA_BOCA_ABAJO_CUB, 0);
			playerPanel.add(card);
		}
		
		JLabel card = cardFactory.getCardLabel(CardLabelFactory.CARTA_BOCA_ABAJO, 0);
		playerPanel.add(card);
	}
	
	//GAME PANEL
	private void createGamePanel() {
		
		panelGame.setLayout(new FlowLayout());
		panelGame.setOpaque(false);
		
		updateGameCard();
	}


	private void updateGameCard() {
		panelGame.removeAll();
		int [] game = fachada.darCartaEnJuego();
				
		JLabel gameCard = cardFactory.getCardLabel(game[0], game[1]);
		panelGame.add(gameCard);
		
		JLabel stackCard = cardFactory.getCardLabel(CardLabelFactory.CARTA_BOCA_ABAJO, 0);
		panelGame.add(stackCard);
	}
	
	//CURRENT PANEL
	private void createCurrentPlayerPanel() {
		
		
		JPanel currentPanel = getCurrentPlayerPanel(fachada.darTurno());
		currentPanel.setOpaque(false);
		
		currentPanel.setBorder(new TitledBorder(new LineBorder(Color.black), "Jugador "+fachada.darTurno()));
		currentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
		
		updateCurrentPlayerPanel();
		
	}
	private void createButtonsPanel()
	{
		panelControls.setLayout(new GridLayout(3,1));
		
		jbRobar.setActionCommand("Robar");
		jbRobar.setIcon(new ImageIcon("./data/buttons/steal-button.png"));
		jbRobar.setBackground(Color.WHITE);
		jbRobar.setOpaque(false);
		panelControls.add(jbRobar);
		jbRobar.setActionCommand("Robar");
		jbRobar.addActionListener(this);
		
		
		jbPasar.setActionCommand("Pasar");
		jbPasar.setIcon(new ImageIcon("./data/buttons/skip-button.png"));
		jbPasar.setBackground(Color.WHITE);
		jbPasar.setOpaque(false);
		panelControls.add(jbPasar);
		jbPasar.setActionCommand("Pasar");
		jbPasar.addActionListener(this);
		jbPasar.setEnabled(false);
		
		
		jbUNO.setActionCommand("UNO");
		jbUNO.setIcon(new ImageIcon("./data/buttons/uno-button.png"));
		jbUNO.setBackground(Color.WHITE);
		jbUNO.setOpaque(false);
		panelControls.add(jbUNO);
		jbUNO.setActionCommand("UNO");
		jbUNO.addActionListener(this);
	}

	private JPanel getCurrentPlayerPanel(int jugador) 
	{
		if(jugador == 1)
		{
			return this.panelPlayer1;
		}
		
		else if(jugador == 2)
		{
			return this.panelPlayer2;
		}
		else if(jugador == 3)
		{
			return this.panelPlayer3;
		}
		else 
		{
			return this.panelPlayer4;
		}
	}
	private void updateCurrentPlayerPanel()
	{
		JPanel currentPanel = getCurrentPlayerPanel(fachada.darTurno());
		updateCurrentPlayerPanel(currentPanel);
	}
	private void updateCurrentPlayerPanel(JPanel currentPanel) 
	{
		currentPanel.setBorder(new TitledBorder(new LineBorder(Color.black), "Jugador "+fachada.darTurno()));
		currentPanel.removeAll();
		int[][] cards = fachada.darCartasJugador(fachada.darTurno());
		for (int j = 0; j < cards.length; j++) 
		{
			int code = cards[j][0];
			int color = cards[j][1];
			JLabel card = cardFactory.getCardLabel(code,color);
			currentPanel.add(card);
			card.addMouseListener(this);
		}
	}
	
	public void actionPerformed(ActionEvent arg0) {
		String comm = arg0.getActionCommand();
		if(comm.equals("Robar")){
			fachada.robar();
			jbPasar.setEnabled(true);
			jbRobar.setEnabled(false);
		}
		else if (comm.equals("Pasar"))
		{
			fachada.pasar();
			jbRobar.setEnabled(true);
			jbPasar.setEnabled(false);
		}
		else if(comm.equals("UNO"))
		{
			fachada.uno();
		}
		this.updateCurrentPlayerPanel();
		this.updateAllOtherPlayers();
		
		
		//Se actualiza la fachada en un thread aparte
		new Thread(){
			public void run() {
				updateAllOtherPlayers();
				updateGameCard();
				updateCurrentPlayerPanel();
				setVisible(true);
			}
		}.start();
		
	}
	private void validateWinner() 
	{
		int ganador = fachada.hayGanador();
		if(ganador != -1)
		{
			javax.swing.JOptionPane.showMessageDialog(this,"El jugador ("+ganador+") ha ganado!");
		}
	}
	public void updateAllOtherPlayers()
	{
		int turno = fachada.darTurno();
		
		if(turno != 1)	updateOtherPlayerPanel(panelPlayer1, 1);
		if(turno != 2)	updateOtherPlayerPanel(panelPlayer2, 2);
		if(turno != 3)	updateOtherPlayerPanel(panelPlayer3, 3);
		if(turno != 4)	updateOtherPlayerPanel(panelPlayer4, 4);
	}
	public void mouseClicked(MouseEvent e) 
	{
		if( e.getSource() instanceof CardLabel)
		{
			CardLabel card = (CardLabel)e.getSource();
			int numero = card.getNumero();
			int color = card.getColor();
			
			if(numero == UNOModelo.POSICION_CARTA_COMODIN || numero == UNOModelo.POSICION_CARTA_ROBA4)
			{
				CustomDialog getNewColorDialog = new CustomDialog(this, true, "Seleccione un nuevo color");
		        int nuevoColor = getNewColorDialog.getAnswer();
		        System.out.println("jugar: numero:"+numero+" color: "+color);
				fachada.jugar(fachada.darTurno() , color, numero, true, nuevoColor);
				jbPasar.setEnabled(false);
				jbRobar.setEnabled(true);
				String colorTexto = fachada.darTextoColorActual();
				setTitle("UNO - Juega "+colorTexto);
				this.lblBackground.setIcon(new ImageIcon("./data/bg/background-"+colorTexto+".jpg"));
			}
			else
			{
				System.out.println("jugar: numero:"+numero+" color: "+color);
				fachada.jugar(fachada.darTurno() , color, numero, false, -1);
				jbPasar.setEnabled(false);
				jbRobar.setEnabled(true);
				String colorTexto = fachada.darTextoColorActual();
				setTitle("UNO - Juega "+colorTexto);
				this.lblBackground.setIcon(new ImageIcon("./data/bg/background-"+colorTexto+".jpg"));
			}
			validateWinner();
			updateAllOtherPlayers();
			updateGameCard();
			updateCurrentPlayerPanel();
			setVisible(true);
		}
	}

	

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}
}
