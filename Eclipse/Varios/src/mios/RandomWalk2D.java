package mios;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RandomWalk2D extends JFrame implements ActionListener{

	public static final int SIZE = 200;
	public static final int CELL_LENGTH = 5;
	public static final long DELAY_MILLIS = 10;
	
	public static final Color COLOR_WALKED = Color.YELLOW;
	public static final Color COLOR_ORIGIN = Color.BLACK;
	public static final Color COLOR_CURRENT_CELL = Color.BLUE;
	public static final Color COLOR_EMPTY = Color.RED;
	
	private int originI;
	private int originJ;
	private int currentI;
	private int currentJ;
	
	private JLabel[][] labels;
	private Container cont;
	private JButton buttonStart;
	private JButton buttonReset;
	private Random random;
	
	private boolean walking;
	
	public static void main(String[] args) {
		
		RandomWalk2D caminata = new RandomWalk2D();
		caminata.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		caminata.startWalking();
		
	}
	
	public RandomWalk2D(){
		super("Random walk 2D - osfprieto");
		
		originI = SIZE/2;
		originJ = SIZE/2;
		
		currentI = originI;
		currentJ = originJ;
		
		random = new Random();
		walking = false;
		
		labels = new JLabel[SIZE][SIZE];
		
		cont = getContentPane();
		cont.removeAll();
		
		JPanel panelLabels = new JPanel();
		JPanel panelButtons = new JPanel();
		
		cont.setLayout(new GridLayout(1, 1));
		
		panelLabels.setLayout(new GridLayout(SIZE, SIZE));
		for(int i=0;i<SIZE;i++){
			//System.out.println(i);
			for(int j=0;j<SIZE;j++){
				labels[i][j] = new JLabel();
				labels[i][j].setOpaque(true);
				labels[i][j].setBackground(COLOR_EMPTY);
				//labels[i][j].setSize(CELL_LENGTH, CELL_LENGTH);
				panelLabels.add(labels[i][j]);
			}
		}
		
		//panelLabels.setSize(CELL_LENGTH*SIZE, CELL_LENGTH*SIZE);
		
		panelButtons.setLayout(new GridLayout(1, 2));
		
		buttonReset = new JButton("Reset");
		buttonStart = new JButton("Start");
		
		buttonReset.addActionListener(this);
		buttonStart.addActionListener(this);
		
		panelButtons.add(buttonReset);
		panelButtons.add(buttonStart);
		
		cont.add(panelLabels);
		//cont.add(panelButtons, BorderLayout.SOUTH);
		
		setVisible(true);
		setBounds(10, 10, 750, 750);
		//pack();
	}
	
	public void move(){
		int dir = random.nextInt(4);
		
		if(currentI>=0 && currentI<SIZE && currentJ>=0 && currentJ<SIZE)
			labels[currentI][currentJ].setBackground(COLOR_WALKED);
		
		if(dir==0)
			currentI++;
		else if(dir==1)
			currentI--;
		else if(dir==2)
			currentJ++;
		else if(dir==3)
			currentJ--;
		
		labels[originI][originJ].setBackground(COLOR_ORIGIN);
		
		if(currentI>=0 && currentI<SIZE && currentJ>=0 && currentJ<SIZE)
			labels[currentI][currentJ].setBackground(COLOR_CURRENT_CELL);
		else
			System.out.println("("+currentI+", "+currentJ+")");
		
	}
	
	public void startWalking(){
		
		buttonStart.setEnabled(false);
		walking = true;
		
		Thread thread = new Thread(new Runnable(){

			public void run() {
				while(walking){
					move();
					try {
						Thread.sleep(DELAY_MILLIS);
					} catch (InterruptedException e) {
						System.err.println("Error waiting");
					}
				}
			}
		});
		
		thread.start();	
	}
	
	public void reset(){
		
		walking = false;
		currentI = originI;
		currentJ = originJ;
		
		for(int i=0;i<SIZE;i++)
			for(int j=0;j<SIZE;j++)
				labels[i][j].setBackground(COLOR_EMPTY);
		
		buttonStart.setEnabled(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton)e.getSource();
		if(source.equals(buttonReset))
			reset();
		else if(source.equals(buttonStart))
			startWalking();
	}
	

}
