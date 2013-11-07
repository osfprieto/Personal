package gui;
/**Al ejecutar este archivo como un Applet se puede jugar Ohtello entre dos personas ::D*/
import kernel.Kernel;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class GUI extends JApplet implements ActionListener{
	public static final int SIZE = 8;
	public static Container cont;
	public static JPanel menu, pieces, info, alert;
	public static JButton newGame, loadGame, credits, handOver, giveUp, save, backMenu;
	public static JButton[][] button = new JButton[SIZE][SIZE];
	public static Icon b = new ImageIcon(Kernel.path+ "black.png"), br = new ImageIcon(Kernel.path+ "blackOver.png");
	public static Icon w = new ImageIcon(Kernel.path+ "white.png"), wr = new ImageIcon(Kernel.path+ "whiteOver.png");
	public static Icon g = new ImageIcon(Kernel.path+ "blank.png");
	public static Icon t, tr;
	public static JLabel time, pnts, alrt;
	public static JList plays;
	public static DefaultListModel playsList;
	public static int whtPnts=2, blckPnts=2, x, y, moves;
	public static long whtTm, blckTm, actTm, rgstrTm, tmTPlWht, tmTPlBlck, tmRstntWht, tmRstntBlck;
	public static boolean noLmts[]=new boolean[2], canPlay, sameSong;
	public static Thread tmApp;
	public static AudioClip sng[] = new AudioClip[4];
	
	public void init(){
		sameSong = true;
		sng[0] = getAudioClip(getDocumentBase(), Kernel.path+ "inMenu.wav");
		sng[1] = getAudioClip(getDocumentBase(), Kernel.path+ "inGameTime.wav");
		sng[2] = getAudioClip(getDocumentBase(), Kernel.path+ "inGameNoTime.wav");
		sng[3] = getAudioClip(getDocumentBase(), Kernel.path+ "gameOver.wav");

		//Cambiar el tamaño de la ventana del programa
		setSize(560, 450);
		iniciarMenu();
	}
	
	//Inicia la interface del menú principal del juego
	public void iniciarMenu(){
		cambiarCancion(0);
		//System.out.println(System.getProperty("user.dir"));
		cont = getContentPane();
		cont.removeAll();
		cont.setLayout(new FlowLayout(FlowLayout.CENTER));
		menu = new JPanel();
		menu.setLayout(new GridLayout(7,1));
		menu.add(new JLabel("Othello MegaGus"));
		menu.add(new JLabel(" "));
		newGame = new JButton("Juego Nuevo");
		newGame.addActionListener(this);
		menu.add(newGame);
		menu.add(new JLabel(" "));
		loadGame = new JButton("Cargar Juego");
		loadGame.addActionListener(this);
		menu.add(loadGame);
		menu.add(new JLabel(" "));
		credits = new JButton("Creditos");
		credits.addActionListener(this);
		menu.add(credits);
		cont.add(menu);
	}
	
	//Inicia un nuevo juego con el tiempo límite provisto
	public void iniciarJuego(long timeWhite, long timeBlack){
		noLmts[0] = timeWhite==-1 ? true : false;
		noLmts[1] = timeBlack==-1 ? true : false;
		if(!sameSong){
			cambiarCancion(noLmts[0] ? 2 : 1);
		}else{
			cambiarCancion(2);
		}
		whtTm = timeWhite;
		blckTm = timeBlack;
		rgstrTm = System.currentTimeMillis();
		t = w;
		tr = wr;
		whtPnts = blckPnts = 2;
		canPlay = true;
		moves = 1;
		cont = getContentPane();
		cont.removeAll();
		cont.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//Botones de acción adicional
		handOver = new JButton("Ceder Turno");
		handOver.addActionListener(this);
		cont.add(handOver);
		giveUp = new JButton("Rendirse");
		giveUp.addActionListener(this);
		cont.add(giveUp);
		save = new JButton("Guardar");
		save.addActionListener(this);
		cont.add(save);
		backMenu = new JButton("Volver al Menú");
		backMenu.addActionListener(this);
		cont.add(backMenu);
		
		//Tabla para el tablero de juego
		pieces = new JPanel();
		pieces.setLayout(new GridLayout(SIZE, SIZE));
		pieces.setPreferredSize(new Dimension(400,400));
		for(int i=0; i<SIZE; i++){
			for(int j=0; j<SIZE; j++){
				if((i==SIZE/2-1 && j==SIZE/2-1) || (i==SIZE/2 && j==SIZE/2)){
					button[i][j] = new JButton(b);
					button[i][j].setRolloverIcon(br);
				}else if((i==SIZE/2-1 && j==SIZE/2) || (i==SIZE/2 && j==SIZE/2-1)){
					button[i][j] = new JButton(w);
					button[i][j].setRolloverIcon(wr);
				}else{
					button[i][j] = new JButton(g);
					button[i][j].setRolloverIcon(w);
				}
				button[i][j].setBackground(new Color(0,125,15));
				button[i][j].addActionListener(this);
				pieces.add(button[i][j]);
			}
		}
		cont.add(pieces);
		
		//Lugar para guardar los datos adicionales
		info = new JPanel();
		info.setLayout(new GridLayout(4,1));
		pnts = new JLabel("<html>Puntos:<br>- Blancas:  " +whtPnts+ "<br>- Negras:\t" +blckPnts+ "</html>");
		pnts.setToolTipText("Puntos actuales de cada uno de los jugadores!");
		info.add(pnts);
		String tms = "<html>Tiempo Restante:";
		tms += "<br>- Blancas:\t";
		tms += noLmts[0] ? "N/A" : (tmTPlWht/60000)+ " mins";
		tms += "<br>- Negras:\t";
		tms += noLmts[1] ? "N/A" : (tmTPlBlck/60000)+ " mins";
		tms += "</html>";
		time = new JLabel(tms);
		time.setToolTipText("Tiempo restante disponible para cada jugador!");
		info.add(time);
		alrt = new JLabel("<html><br>Juegan Blancas<br>...</html>");
		alrt.setPreferredSize(new Dimension(1,1));
		info.add(alrt);
		playsList = new DefaultListModel();
		playsList.addElement("Equipo [posX, posY]");
		plays = new JList(playsList);
		plays.setVisibleRowCount(4);
		JScrollPane jsp = new JScrollPane(plays);
		jsp.setPreferredSize(new Dimension(140, 100));
		info.add(jsp);
		cont.add(info);
		tmApp = new Thread(new RunTime(), "Tiempo");
		tmApp.start();
		info.updateUI();
	}
	
	//Muestra los créditos
	public void mostrarCreditos(){
		String msg = "<html><a style='font-family:courier new;'>";
		msg += "|---------------Créditos xD---------------|<br>";
		msg += "|. No hay ................................|<br>";
		msg += "|.. Pero los habría ......................|<br>";
		msg += "|... Si sobrara tiempo ...................|<br>";
		msg += "|.... Pero cómo no sobra .................|<br>";
		msg += "|..... Mejor lo gastamos bien ............|<br>";
		msg += "|...... xD xD xD .........................|<br>";
		msg += "|....... Disfrute del Othello MegaGus ....|<br>";
		msg += "|-----------------Fin xD------------------|<br>";
		msg += "</a></html>";
		alertarGrande(msg);
	}
	
	//Pone play a la canción especificada y para las otras xD
	public static void cambiarCancion(int id){
		for(int i=0; i<sng.length; i++){
			if(i != id){
				sng[i].stop();
			}else{
				if(id != 3){
					sng[i].loop();
				}else{
					sng[i].play();
				}
			}
		}
	}
	
	//Cambia el turno
	public static void cambiarTurno(){
		plays.updateUI();
		if(moves != 0){
			playsList.addElement((moves<10 ? "0" : "")+moves+ ". " +(GUI.t.equals(GUI.b)?"Negras ":"Blancas")+ " [" +x+ ", " +y+ "]");
		}
		moves++;
		if(t.equals(b)){
			if(!sameSong){
				cambiarCancion(noLmts[0] ? 2 : 1);
			}
			t = w;
			tr = wr;
			alrt.setText("<html><br>Juegan Blancas<br>Alertas...</html>");
		}else if(t.equals(w)){
			if(!sameSong){
				cambiarCancion(noLmts[1] ? 2 : 1);
			}
			t = b;
			tr = br;
			alrt.setText("<html><br>Juegan Negras<br>Alertas...</html>");
		}
		for(int i=0; i<SIZE; i++){
			for(int j=0; j<SIZE; j++){
				if(button[i][j].getIcon().equals(g)){
					button[i][j].setRolloverIcon(t);
				}
			}
		}
		alrt.updateUI();
	}
	
	//Actualiza la cantidad de fichas de cada jugador
	public static void cambiarPuntos(int x, Icon who){
		if(who.equals(w)){//El enemigo era el blanco
			whtPnts -= x;
			blckPnts += x+1;
		}else if(who.equals(b)){//El enemigo era el negro
			whtPnts += x+1;
			blckPnts -= x;
		}
		pnts.setText("<html>Puntos:<br>- Blancas:\t" +whtPnts+ "<br>- Negras:\t" +blckPnts+ "<html>");
		pnts.updateUI();
		if(whtPnts+blckPnts==64 || whtPnts==0 || blckPnts==0){
			acabaJuego(' ');
		}
	}
	
	//Cambia el tiempo después de cada vez que se activa un botón
	public static void cambiarTiempo(Icon turno){
		actTm = System.currentTimeMillis();
		if(turno.equals(b) && !noLmts[1]){
			blckTm += (actTm - rgstrTm);
			tmRstntBlck = tmTPlBlck - blckTm;
			if(tmRstntBlck<=0){
				acabaJuego('t');
				return;
			}
		}else if(turno.equals(w) && !noLmts[0]){
			whtTm += (actTm - rgstrTm);
			tmRstntWht = tmTPlWht - whtTm;
			if(tmRstntWht<=0){
				acabaJuego('t');
				return;
			}
		}else if(turno.equals(g)){
			tmRstntWht = tmTPlWht - whtTm;
			tmRstntBlck = tmTPlBlck - blckTm;
		}
		rgstrTm = actTm;
		int minWht = (int)(((tmRstntWht-(tmRstntWht%(60000)))/60000)%60);
		int secWht = (int)(((tmRstntWht-(tmRstntWht%1000))/1000)%60);
		int milWht = (int)(tmRstntWht%1000);
		int minBlck = (int)(((tmRstntBlck-(tmRstntBlck%(60000)))/60000)%60);
		int secBlck = (int)(((tmRstntBlck-(tmRstntBlck%1000))/1000)%60);
		int milBlck = (int)(tmRstntBlck%1000);
		String tms = "<html>Tiempo Restante:";
		tms += "<br>- Blancas:\t";
		tms += noLmts[0] ? "N/A" : minWht+ " : " +(secWht < 10 ? "0" : "") + secWht+ " . " +(milWht<10 ? "00" : (milWht<100 ? "0" : ""))+ milWht;
		tms += "<br>- Negras:\t";
		tms += noLmts[1] ? "N/A" : minBlck+ " : " +(secBlck < 10 ? "0" : "") + secBlck+ " . " +(milBlck<10 ? "00" : (milBlck<100 ? "0" : ""))+ milBlck;
		tms += "</html>";
		time.setText(tms);
		time.repaint();
	}
	
	//Juego Finalizado
	public static void acabaJuego(char id){
		cambiarCancion(3);
		String msg = "El Juego está Over jajaja\n";
		boolean whtWns = id=='w'? false : true;
		boolean blckWns = id=='b'? false : true;
		if(whtWns && blckWns){
			if(whtPnts > blckPnts){
				blckWns = false;
			}else if(blckPnts > whtPnts){
				whtWns = false;
			}
		}else{
			msg += "El juego fue terminado por que las " +(whtWns ? "negras" : "blancas");
			msg += " se dieron porvencidas!";
		}
		if(id == 't'){
			msg += "El juego se acabó porque las ";
			if(tmRstntWht < 0){
				msg += "blancas";
				whtWns = false;
				blckWns = true;
			}else{
				msg += "negras";
				whtWns = true;
				blckWns = false;
			}
			msg += " se quedaron sin tiempo";
		}
		msg += "\n";
		if(whtWns && blckWns){//Ganan ambas
			msg += "Fue un empate, ambos colores con " +whtPnts+ " fichas";
		}else if(blckWns){
			msg += "Ganaron las negras! " +blckPnts+ " a " +whtPnts;
		}else{
			msg += "Ganaron las blancas! " +whtPnts+ " a " +blckPnts;
		}
		for(int i=0; i<SIZE; i++){
			for(int j=0; j<SIZE; j++){
				if(button[i][j].getIcon().equals(g)){
					button[i][j].setRolloverIcon(null);
				}
			}
		}
		canPlay = false;
		handOver.setEnabled(false);
		giveUp.setEnabled(false);
		save.setEnabled(false);
		alertar("¡¡¡Juego Finalizado!!!");
		alertarGrande(msg);
		//Con ésta línea se manda al menú principal luego de mostrar el aviso de gameover
		//Pero es mejor dejar que el usuario vea el tablero y que luego decida volver al menú
		//backMenu.doClick();
	}
	
	//Alerta de eventos en el juego
	public static void alertar(String txt){
		String turn = t.equals(w)?"Blancas":"Negras";
		if(txt == ""){txt += "...";}
		alrt.setText("<html><br>Juegan " +turn+ "<br>" +txt+ "</html>");
		alrt.updateUI();
	}
	
	//Alerta de eventos GRANDES en el juego
	public static void alertarGrande(String txt){
		JOptionPane.showMessageDialog(null, txt);
	}
	
	//Pendiente de los clicks en los botones
	public void actionPerformed(ActionEvent evnt){
		if(evnt.getSource() == newGame){//Comenzar nuevo juego
			long timeLongW = -1;
			long timeLongB = -1;
			
			boolean seRepite = true;
			//Pedir tiempo para las blancas
			while(seRepite){
				String timeW = JOptionPane.showInputDialog("Minutos máximos de juego para las blancas\n[Vacío = Sin límite]");
				if(timeW != null){
					if(timeW.length() == 0){
						timeLongW = -1;
						seRepite = false;
					}else{
						try{
							timeLongW = Long.parseLong(timeW);
							tmTPlWht = tmRstntWht = (long)timeLongW*60000;
							if(timeLongW > 0){
								seRepite = false;
							}else{
								throw new Exception();
							}
						}catch(Exception e){
							alertarGrande("Usted no indicó un número entero mayor que 0");
						}
					}
				}
			}
			
			seRepite = true;
			//Pedir tiempo para las negras
			while(seRepite){
				String timeB = JOptionPane.showInputDialog("Minutos máximos de juego para las negras\n[Vacío = Sin límite]");
				if(timeB != null){
					if(timeB.length() == 0){
						timeLongB = -1;
						seRepite = false;
					}else{
						try{
							timeLongB = Long.parseLong(timeB);
							tmTPlBlck = tmRstntBlck = (long)timeLongB*60000;
							if(timeLongB > 0){
								seRepite = false;
							}else{
								throw new Exception();
							}
						}catch(Exception e){
							alertarGrande("Usted no indicó un número entero mayor que 0");
						}
					}
				}
			}
			
			iniciarJuego(timeLongW, timeLongB);
		}else if(evnt.getSource() == loadGame){//Carga el último juego guardado
			iniciarJuego(-1,-1);
			Kernel.loadGame();
		}else if(evnt.getSource() == credits){//Muestra los créditos
			mostrarCreditos();
		}else if(evnt.getSource() == handOver){//Cede turno el jugador actual
			cambiarTurno();
		}else if(evnt.getSource() == giveUp){//Alguien se rindió
			acabaJuego(t.equals(b) ? 'b' : 'w');
		}else if(evnt.getSource() == save){//Salvar juego
			Kernel.saveGame();
			iniciarMenu();
			cont.repaint();
			menu.updateUI();
		}else if(evnt.getSource() == backMenu){//Vuelve al menu principal
			iniciarMenu();
			cont.repaint();
			menu.updateUI();
		}else{//Se clickeó uno de los botones del juego
			//System.out.println("\n----------\n\nwhtTm = " +whtTm+ "\nblckTm = " +blckTm+ "\nactTm = " +actTm+ "\nrgstrTm = " +rgstrTm+ "\ntmTPlWht = " +tmTPlWht+ "\ntmTPlBlck = " +tmTPlBlck+ "\ntmRstntWht = " +tmRstntWht+ "\ntmRstntBlck = " +tmRstntBlck);
			if(canPlay){//Si se puede jugar
				for(int i=0; i<SIZE; i++){
					for(int j=0; j<SIZE; j++){
						if(evnt.getSource() == button[i][j]){
							x = j+1;
							y = i+1;
							Kernel.play(i, j);
						}
					}
				}
			}
		}
	}
	
	public class RunTime implements Runnable{
		public void run(){
			while(canPlay){
				int id = (t.equals(b) ? 1 : 2);
				if(id == 1){
					cambiarTiempo(b);
				}else{
					cambiarTiempo(w);
				}
			}
		}
	}
}
