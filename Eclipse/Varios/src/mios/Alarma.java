package mios;


import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
//import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Alarma extends JApplet implements ActionListener{
	
	private static String fileName;
	//private static String subFileName;
	private static String alertMessage;
	private static int horaNum;
	private static int minutoNum;
	private static int horaActual;
	private static int minutoActual;
	private static long actualTime;
	private static AudioClip audio;
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
	
	public void init(){
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
		labelRingTime.setText("Ring Time      "+((horaNum/10==0)?"0":"")+horaNum+" : "+((minutoNum/10==0)?"0":"")+minutoNum);
		labelActualTime = new JLabel();
		labelActualTime.setText("Acutal Time  "+((horaActual/10==0)?"0":"")+horaActual+" : "+((minutoActual/10==0)?"0":"")+minutoActual);
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
		
		buttonsPanel = new JPanel();
		buttonsPanel.removeAll();
		buttonsPanel.setLayout(new BorderLayout());
		buttonsPanel.add(ajustar, BorderLayout.WEST);
		buttonsPanel.add(pausa, BorderLayout.CENTER);
		buttonsPanel.add(desactivar, BorderLayout.EAST);
		
		containerPanel.add(labelsPanel);
		containerPanel.add(buttonsPanel);
		
		contentPane.add(containerPanel);
		
		this.setBounds(500, 300, 250, 100);
		
		this.setSize(250, 100);
		
		onWait = true;
		tt = new Thread(new RunTime(), "Tiempo");
		tt.start();
		labelsPanel.updateUI();
	}
	
	public void setup(){
		//primero pide con un JFileChooser un sonido para reproducir
		//prepara el sonido
		//acto seguido pide el tiempo en el que desea que suene con un OptionPane con
		//campos de horas y minutos
		//llama a comenzar()
		
		/*fileChooser = new JFileChooser();
		openingResult = 1;
		subFileName = "";
		while(subFileName.compareTo(".wav")!=0){
			JOptionPane.showMessageDialog(null, "Selecciona el archivo bb", "meto la sala", JOptionPane.INFORMATION_MESSAGE);
			openingResult = fileChooser.showOpenDialog(this);
			if(openingResult != JFileChooser.CANCEL_OPTION){
				fileName = fileChooser.getSelectedFile().getPath();
				subFileName = fileName.subSequence(fileName.length()-4, fileName.length()).toString();
			}
		}*/
		audio = getAudioClip(getDocumentBase(), "..\\"+"sound.wav");
		//audio.loop();
		
		labelHora = new JLabel("Hora:");
		labelMinuto = new JLabel("Minutos:");
		labelUtc = new JLabel("UTC:");
		labelAlertMessage = new JLabel("Message:");
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
			alertMessageTextField.setText("meto la sala");
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
				JOptionPane.showMessageDialog(null, "Error tomando datos", "Error", JOptionPane.ERROR_MESSAGE);
				horaNum = minutoNum = -1;
				utcNum = 13;
			}
		}
		comenzar();
	}
	
	public void pausar(){
		//aumenta el ringTime en 10 minutos
		minutoNum += 10;
		horaNum += (minutoNum/60);
		minutoNum %= 60;
		horaNum %= 24;
		comenzar();
	}
	
	public void alertar(){
		//destruye el thread, modifica la GUI dando la opción de pausa adicionalmente
		//reproduce el sonido
		//muestra un mensaje en pantalla
		//sigue reproduciendo el sonido hasta que se cierre el mensaje y se
		//seleccione una opción de los botones
		onWait = false;
		audio.loop();
		buttonsPanel.add(pausa);
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
		audio.stop();
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
				horaActual =(int) (actualTime%(1000*60*60*24))/(1000*60*60);
				horaActual -= utcNum;
				minutoActual = (int) (actualTime%(1000*60*60))/(1000*60);
				labelActualTime.setText("Acutal Time  "+((horaActual/10==0)?"0":"")+horaActual+" : "+((minutoActual/10==0)?"0":"")+minutoActual);
				labelActualTime.updateUI();
				if(horaActual==horaNum && minutoActual==minutoNum)
					alertar();
			}
		}
	}
	
}
