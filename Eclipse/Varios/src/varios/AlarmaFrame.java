package varios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
//import javax.swing.JFileChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AlarmaFrame extends JFrame implements ActionListener{
	
	private static boolean isEstefany;
	private static String fileName;
	//private static String subFileName;
	private static String alertMessage;
	private static int horaNum;
	private static int minutoNum;
	private static int horaActual;
	private static int minutoActual;
	private static int segundoActual;
	private static long actualTime;
	private static int utcNum;
	private static boolean onWait;
	
	//private static int tempNum;
	
	//private static JFileChooser fileChooser;
	//private static int openingResult;
	
	private static JButton pausa;
	private static JButton desactivar;
	private static JButton ajustar;
	
	private static Container contentPane;
	
	private static JLabel labelActualTime;
	private static JLabel labelRingTime;
	private static JLabel labelFileName;
	
	private static JPanel containerPanel;
	private static JPanel buttonsPanel;
	private static JPanel labelsPanel;
	
	private static JPanel setupPanel;
	private static JTextField horaTextField;
	private static JTextField minutoTextField;
	private static JTextField utcTextField;
	private static JTextField alertMessageTextField;
	private static JLabel labelHora;
	private static JLabel labelMinuto;
	private static JLabel labelUtc;
	private static JLabel labelAlertMessage;
	
	private static Thread tt;
	private URL soundLocation;
	private Clip clip;
	private AudioInputStream inputStream;
	private JFileChooser fileChooser;
	private int openingResult;
	private String subFileName;
	private String fileURL;
	
	public static void main(String[] args){
		isEstefany = false;
		String nombre = JOptionPane.showInputDialog("Cúal es tu nombre?");
		if(nombre.equals("Estefany")){
			nombre = "Princesa Chamoltrafuia de Prieto";
			isEstefany = true;
		}
		AlarmaFrame af = new AlarmaFrame("Alarma "+nombre);
		af.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	AlarmaFrame(String frameName){
		super(frameName);
		onWait = false;
		setup();
	}
	
	public void comenzar(){
		//crea el thread que revisa el actualiza la UI en la zona de hora programada
		//GUI con hora acutal, hora programada, nombre del archivo a sonar, botones
		//de salida y ajustes
		//cuadra tamaños, GUI
		
		contentPane = getContentPane();
		contentPane.removeAll();
		contentPane.setLayout(new FlowLayout());
		
		containerPanel = new JPanel();
		containerPanel.setLayout(new GridLayout(2, 1));
		
		labelRingTime = new JLabel();
		labelRingTime.setText("Hora Alarma  "+((horaNum/10==0)?"0":"")+horaNum+" : "+((minutoNum/10==0)?"0":"")+minutoNum+" : 00");
		labelActualTime = new JLabel();
		labelActualTime.setText("Hora Actual  "+((horaActual/10==0)?"0":"")+horaActual+" : "+((minutoActual/10==0)?"0":"")+minutoActual);
		labelFileName = new JLabel();
		labelFileName.setText(fileName);
		
		labelsPanel = new JPanel();
		labelsPanel.removeAll();
		labelsPanel.setLayout(new GridLayout(3, 1));
		labelsPanel.add(labelRingTime);
		labelsPanel.add(labelActualTime);
		labelsPanel.add(labelFileName);
		
		pausa = new JButton("Pausar");
		pausa.addActionListener(this);
		desactivar = new JButton("Desactivar");
		desactivar.addActionListener(this);
		ajustar = new JButton("Ajustes");
		ajustar.addActionListener(this);
		
		if(isEstefany){
			pausa.setForeground(Color.RED);
			desactivar.setForeground(Color.RED);
			ajustar.setForeground(Color.RED);
			labelActualTime.setForeground(Color.RED);
			labelRingTime.setForeground(Color.RED);
			labelFileName.setForeground(Color.RED);
			
			pausa.setBackground(Color.WHITE);
			desactivar.setBackground(Color.WHITE);
			ajustar.setBackground(Color.WHITE);
			labelActualTime.setBackground(Color.WHITE);
			labelRingTime.setBackground(Color.WHITE);
			labelFileName.setBackground(Color.WHITE);
		}
		
		buttonsPanel = new JPanel();
		buttonsPanel.removeAll();
		buttonsPanel.setLayout(new BorderLayout());
		buttonsPanel.add(ajustar, BorderLayout.WEST);
		//buttonsPanel.add(pausa, BorderLayout.CENTER);
		buttonsPanel.add(desactivar, BorderLayout.EAST);
		if(isEstefany){
			JLabel estefanyLabel = new JLabel("TE AMO ESTEFANY! TE ADORO PRINCESA HERMOSA! METO LA SALA!");
			estefanyLabel.setForeground(Color.RED);
			estefanyLabel.setBackground(Color.RED);
			buttonsPanel.setBackground(Color.WHITE);
			labelsPanel.setBackground(Color.WHITE);
			containerPanel.setBackground(Color.WHITE);
			buttonsPanel.add(estefanyLabel, BorderLayout.SOUTH);
			this.setBackground(Color.WHITE);
		}
		containerPanel.add(labelsPanel);
		containerPanel.add(buttonsPanel);
		
		contentPane.add(containerPanel);
		
		this.setBounds(500, 300, 400, 140);
		this.setResizable(false);
		this.setVisible(true);
		
		onWait = true;
		tt = new Thread(new RunTime(), "Tiempo");
		tt.start();
		labelsPanel.updateUI();
		buttonsPanel.updateUI();
	}
	
	public void setup(){
		//primero pide con un JFileChooser un sonido para reproducir
		//prepara el sonido
		//acto seguido pide el tiempo en el que desea que suene con un OptionPane con
		//campos de horas y minutos
		//llama a comenzar()
		
		fileChooser = new JFileChooser();
		openingResult = 1;
		subFileName = "";
		while(subFileName.compareTo(".wav")!=0){
			if(isEstefany)
				JOptionPane.showMessageDialog(null, "Selecciona un archivo bb", "Meto la sala", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "Selecciona un archivo .wav", "Audio", JOptionPane.INFORMATION_MESSAGE);
			openingResult = fileChooser.showOpenDialog(this);
			if(openingResult != JFileChooser.CANCEL_OPTION){
				fileName = fileChooser.getSelectedFile().getPath();
				subFileName = fileName.subSequence(fileName.length()-4, fileName.length()).toString();
			}
			else{
				System.exit(1);
			}
		}
		
		//"file:C:/Users/TOSHIBA/Documents/Eclipse%20workspace/varios/sound.wav"
		fileURL = "file:";
		for(int i=0;i<fileName.length();i++){
			char c = fileName.charAt(i);
			if(c==47||c==92)
				fileURL += "/";
			else if(c==32)
				fileURL += "%20";
			else
				fileURL += fileName.charAt(i);
		}
		
		labelHora = new JLabel("Hora:");
		labelMinuto = new JLabel("Minutos:");
		labelUtc = new JLabel("UTC:");
		labelAlertMessage = new JLabel("Mensaje:");
		horaTextField = new JTextField();
		minutoTextField = new JTextField();
		utcTextField = new JTextField();
		alertMessageTextField = new JTextField();
		setupPanel = new JPanel();
		setupPanel.removeAll();
		setupPanel.setLayout(new GridLayout(4, 2));
		setupPanel.add(labelHora);
		setupPanel.add(horaTextField);
		setupPanel.add(labelMinuto);
		setupPanel.add(minutoTextField);
		setupPanel.add(labelUtc);
		setupPanel.add(utcTextField);
		setupPanel.add(labelAlertMessage);
		setupPanel.add(alertMessageTextField);
		horaNum = minutoNum = -1;
		utcNum = 13;
		while(horaNum<0 || horaNum>23 || minutoNum<0 || minutoNum>59 || utcNum<-12 || utcNum>12){
			horaTextField.setText("");
			minutoTextField.setText("");
			utcTextField.setText("");
			if(isEstefany)
				alertMessageTextField.setText("meto la sala");
			else
				alertMessageTextField.setText("");
			if(isEstefany)
				JOptionPane.showMessageDialog(null, setupPanel, "Entra la hora de la alarma mi princesa", JOptionPane.QUESTION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, setupPanel, "Entra la hora de la alarma", JOptionPane.QUESTION_MESSAGE);
			try{
				horaNum = Integer.parseInt(horaTextField.getText());
				minutoNum = Integer.parseInt(minutoTextField.getText());
				utcNum = Integer.parseInt(utcTextField.getText());
				alertMessage = alertMessageTextField.getText();
				if(horaNum<0 || horaNum>23 || minutoNum<0 || minutoNum>59 || utcNum<-12 || utcNum>12){
					throw new NumberFormatException();
				}
			}
			catch(NumberFormatException e){
				if(isEstefany)
					JOptionPane.showMessageDialog(null, "Bb, algo pasó princesa :S", "Subsú", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "Error tomando datos", "Error", JOptionPane.ERROR_MESSAGE);
				horaNum = minutoNum = -1;
				utcNum = 13;
			}
		}
		comenzar();
	}
	
	public void pausar(){
		//aumenta el ringTime en 10 minutos
		clip.loop(0);
		minutoNum += 10;
		horaNum += (minutoNum/60);
		minutoNum %= 60;
		horaNum %= 24;
		comenzar();
	}
	
	public void alertar() throws Exception{
		//destruye el thread, modifica la GUI dando la opción de pausa adicionalmente
		//reproduce el sonido
		//muestra un mensaje en pantalla
		//sigue reproduciendo el sonido hasta que se cierre el mensaje y se
		//seleccione una opción de los botones
		soundLocation = new URL(fileURL); 
	    // can use one clip many times 
	    clip = AudioSystem.getClip(); 
	    inputStream = AudioSystem.getAudioInputStream(soundLocation); 
	    buttonsPanel.add(pausa, BorderLayout.WEST);
	    buttonsPanel.remove(ajustar);
	    clip.open(inputStream); 
	    clip.loop(Clip.LOOP_CONTINUOUSLY); 
	    clip.start(); 
		onWait = false;
		buttonsPanel.add(pausa, BorderLayout.WEST);
		buttonsPanel.updateUI();
		JOptionPane.showMessageDialog(null, alertMessage, "Ya es hora!", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void actionPerformed(ActionEvent e) {
		//destruye el Thread
		//mira lo de los botones de ajustar y desactivar
		//ajsutes: setup()
		//desactivar: System.exit(1);
		//pausa: pausar();
		onWait = false;
		if(e.getSource().equals(pausa))
			pausar();
		else if (e.getSource().equals(ajustar))
			setup();
		else if (e.getSource().equals(desactivar))
			System.exit(1);
	}
	
	public class RunTime implements Runnable{
		public void run(){
			//actualiza la GUI del tiempo Actual cada segundo, de pronto se puede usar sleep(1000)
			//revisa que no se haya acabado el tiempo llamando a alertar() al llegar a la meta
			while(onWait){
				actualTime = System.currentTimeMillis();
				horaActual =(int) ((actualTime+utcNum*1000*60*60)%(1000*60*60*24))/(1000*60*60);
				minutoActual = (int) (actualTime%(1000*60*60))/(1000*60);
				segundoActual = (int) ((actualTime%(1000*60))/(1000));
				labelActualTime.setText("Hora Actual  "+((horaActual/10==0)?"0":"")+horaActual+" : "+((minutoActual/10==0)?"0":"")+minutoActual+" : "+((segundoActual/10==0)?"0":"")+segundoActual);
				labelActualTime.updateUI();
				if(horaActual==horaNum && minutoActual==minutoNum)
					try {
						alertar();
					} catch (Exception e) {
						if(isEstefany)
							JOptionPane.showMessageDialog(null, "hmm algo pasó bb, toca reiniciar", "Ups", JOptionPane.INFORMATION_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, "Una Excepción se ha presentado", "Error", JOptionPane.ERROR_MESSAGE);
						desactivar.doClick();
					}
			}
		}
	}
}
