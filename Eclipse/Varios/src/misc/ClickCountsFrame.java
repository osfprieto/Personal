package misc;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class ClickCountsFrame extends JFrame implements ActionListener{
	
	private long timeLimit;
	private long timeIni;
	private long timeActual;
	private long timeDif;
	private JButton startButton;
	private JButton clickButton;
	private JLabel countLabel;
	private int clicks;
	private Container contentPane;
	private JPanel panel;
	private Thread tt;
	private boolean canClic;
	
	public static void main(String[] args) {
		long time = Integer.parseInt(JOptionPane.showInputDialog(null, "Entre la cantidad de segundos en los que quiere contar la cantidad de clics:", 10));
		ClickCountsFrame frame = new ClickCountsFrame(time);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public ClickCountsFrame(long time){
		this.setBounds(500, 300, 160, 110);
		this.startButton = new JButton("Empezar");
		this.clickButton = new JButton("Click");
		this.timeLimit = time * 1000;
		this.countLabel = new JLabel("Clicks: "+this.clicks+" Tiempo: 0");
		contentPane = this.getContentPane();
		contentPane.removeAll();
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		this.startButton.addActionListener(this);
		this.clickButton.addActionListener(this);
		panel.add(startButton, BorderLayout.CENTER);
		contentPane.add(panel);
		canClic = false;
	}
	
	public void observar(){
		this.timeActual = System.currentTimeMillis();
		this.timeDif = this.timeActual - this.timeIni;
		if(this.timeDif<=this.timeLimit){
			this.countLabel.setText("Click: "+this.clicks+" Tiempo: "+this.timeDif/1000);
			this.countLabel.updateUI();
		}
		else{
			canClic = false;
			JOptionPane.showMessageDialog(null, "Felicitaciones! hiciste "+this.clicks+" clicks \nen "+this.timeLimit/1000+" segundos!", "Clicks", JOptionPane.INFORMATION_MESSAGE);
			this.panel.removeAll();
			this.panel.add(this.startButton, BorderLayout.CENTER);
			this.panel.updateUI();
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(this.startButton)){
			this.clicks = 0;
			this.contentPane.removeAll();
			this.panel.removeAll();
			this.panel.add(this.countLabel, BorderLayout.NORTH);
			this.panel.add(this.clickButton, BorderLayout.CENTER);
			this.contentPane.add(this.panel);
			this.timeIni = System.currentTimeMillis();
			this.timeActual = this.timeIni;
			this.timeDif = this.timeLimit;
			this.panel.updateUI();
			canClic = true;
			tt = new Thread(new RunTime(), "Tiempo");
			tt.start();
		}
		else if(e.getSource().equals(this.clickButton)){
			this.clicks++;
		}
	}
	public class RunTime implements Runnable{
		public void run() {
			while(canClic)
				observar();
		}
	}
}
