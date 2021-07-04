package varios;

import java.awt.AWTException;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

public class Clicker extends JFrame implements ActionListener{

	private JLabel labelX;
	private JLabel labelY;
	private JLabel delay;
	private JLabel labelCount;
	private JLabel labelStatus;
	
	private JSpinner xCoordinate;
	private JSpinner yCoordinate;
	private JSpinner delaySpinner;
	private JSpinner spinnerCount;
	private JButton button;
		
	private JPanel panel;
	
	private boolean clicking;
	private Robot robot;
	
	public static void main(String[] args) {
		
		Clicker clicker = new Clicker();
		clicker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		clicker.setVisible(true);
		
	}
	
	
	public Clicker(){
		super("Clicker");
	
		clicking = false;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			System.exit(0);
		}
		
		setAlwaysOnTop(true);
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(5, 4));
		
		labelX = new JLabel("X=");
		xCoordinate = new JSpinner();
		labelY = new JLabel("Y=");
		yCoordinate = new JSpinner();
		delay = new JLabel("Delay(millis)=");
		delaySpinner = new JSpinner();
		labelCount = new JLabel("Cliks=");
		spinnerCount = new JSpinner();
		labelStatus = new JLabel("");
		button = new JButton("Clickear");
		
		panel.add(labelX);
		panel.add(xCoordinate);
		panel.add(labelY);
		panel.add(yCoordinate);
		panel.add(delay);
		panel.add(delaySpinner);
		panel.add(labelCount);
		panel.add(spinnerCount);
		panel.add(labelStatus);
		panel.add(button);
		
		button.addActionListener(this);
		getContentPane().removeAll();
		getContentPane().add(panel);
		setBounds(100, 100, 300, 200);
		
		startMyMouseListener();
		
	}
	
	private void startMyMouseListener(){
		
		Thread thread = new Thread(new Runnable(){

			public void run() {
				while(true){
					Point p = MouseInfo.getPointerInfo().getLocation();
					labelStatus.setText("("+p.x+", "+p.y+"):"+
					MyRunnable.actualClicks);
					labelStatus.updateUI();
				}
			}
			
		});
		
		thread.start();
		
	}
	
	private void startClicking(){
		
		int x = ((Integer)xCoordinate.getValue()).intValue();
		int y = ((Integer)yCoordinate.getValue()).intValue();
		int delayValue = ((Integer)delaySpinner.getValue()).intValue();
		int count = ((Integer)spinnerCount.getValue()).intValue();
		
		MyRunnable.delay = delayValue<10?10:delayValue;
		MyRunnable.actualClicks = 0;
		MyRunnable.maxClicks = count;
		
		robot.mouseMove(x, y);
		
		Thread thread = new Thread(new MyRunnable(robot));
		thread.start();
	}
	
	private void stopClicking(){
		MyRunnable.actualClicks = MyRunnable.maxClicks;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(clicking){
			clicking = false;
			button.setText("Clikear");
			stopClicking();
		}
		else{
			clicking = true;
			button.setText("Stop");
			startClicking();
		}
	}
	
	private static class MyRunnable implements Runnable{

		public static int maxClicks = 0;
		public static int actualClicks = 0;
		public static int delay = 50;
		
		private Robot robot;
		
		public MyRunnable(Robot robot){
			this.robot = robot;
		}
		
		public void run() {
			
			while(actualClicks<maxClicks){
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
				actualClicks++;
				if(delay>0)
					try {
						Thread.sleep(delay);
					} catch (InterruptedException e) {
						System.out.println("Can't sleep");
					}
				//System.out.println(actualClicks+"\t"+maxClicks);
			}
			
		}
		
	}

}
