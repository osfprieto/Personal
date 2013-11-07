package mios;

import java.lang.Thread;
import java.applet.AudioClip;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JApplet;
/* La clase TimeCounter hereda de la Clase JApplet e implementa a la clase ActionListener.
 * Esta clase corre un JApplet en el que se pide un número determinado por el usario de
 * minutos y segundos para que el programa alerte al momento que haya pasado dicho tiempo.*/
@SuppressWarnings("serial")
public class TimeCounter extends JApplet implements ActionListener{
		public static int i, m, s;
		public static StringTokenizer ent;
		public static long time, time1, limit, limit1, begin, begin1, tic;
	    public static AudioClip audio;
	    public static Container cont;
	    public static JLabel outAct, outLim;
	    public static JPanel info;
	    public static JButton starter;
	    public static boolean canRun;
	    public static Dimension dim;
		public static Thread tmApp;
	    
	    public void init(){
	    	audio = getAudioClip(getDocumentBase(), "..\\"+"beep.wav");
	    	dim = new Dimension(200, 200);
	    	tic = 1;
	    	setSize(dim);
	    	setMaximumSize(dim);
	    	setMinimumSize(dim);
	    	inicio();
	    }
	    
	    public void pide(){
	    	
		    ent = new StringTokenizer(JOptionPane.showInputDialog("Entre el tiempo en minutos y segundos separados por un espacio:"));
		    m = Integer.parseInt(ent.nextToken());
		    s = Integer.parseInt(ent.nextToken());
		    limit =(long) ((m*60+s)*1000);    
	        time = 0;
	        begin = System.currentTimeMillis();
	        while (time<limit){
	            time = System.currentTimeMillis()-begin;
	            if(time%(tic*1000) == 0){
	            	audio.play();
	            }
	        }
	        
	        time1=0;
	        begin1 = System.currentTimeMillis();
	        limit1=5000;
	        audio.loop();
	        while (time1<limit1){
	            time1 = System.currentTimeMillis()-begin1;
		        audio.loop();
	        }
	        audio.stop();
	        canRun = false;
	    }
	    public void inicio(){
	    	cont = getContentPane();
			cont.removeAll();
			cont.setLayout(new GridLayout(3, 1));
			cont.add(new JLabel(""));
			starter = new JButton("Empezar");
			starter.addActionListener(this);
			cont.add(starter);
			this.setContentPane(cont);
	    }
	    
	    public void updateUI(){
	    		cont = getContentPane();
				cont.removeAll();
				cont.setLayout(new GridLayout(4, 1));
				outAct = new JLabel("Tiempo actual: 0");
				outLim = new JLabel("Tiempo límite: 0");
				cont.add(new JLabel(""));
				cont.add(outAct);
				cont.add(outLim);
				this.setContentPane(cont);
				canRun=true;
				tmApp = new Thread(new RunTime(), "Tiempo");
				tmApp.start();
				TimeCheck timeCheck = new TimeCheck();
		    	timeCheck.start();
				pide();
	    }

	    public void cambiarTiempo(){
	    	outAct.setText("Tiempo actual: "+time);
			outLim.setText("Tiempo límite: "+limit);
			outAct.repaint();
			outLim.repaint();
	    }
	    
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == starter){
				updateUI();
			}
		}
		
		public class RunTime implements Runnable{
			public void run(){
				while(canRun){
					cambiarTiempo();
				}
			}
		}
		
		public class TimeCheck extends Thread{
			public void run(){
				while (canRun){
					cambiarTiempo();
				}
			}
		}
		
}
