package misc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class FrameCirculos extends JFrame implements MouseListener{
	
	private JLabel label;
	private Container cont;
	private String coords;
	
	public static void main(String[] args) {
		FrameCirculos frame = new FrameCirculos();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public FrameCirculos(){
		super("");
		label = new JLabel("");
		label.setBackground(Color.PINK);
		label.setForeground(Color.WHITE);
		coords = "";
		cont = getContentPane();
		cont.removeAll();
		cont.setLayout(new BorderLayout());
		cont.add(label, BorderLayout.SOUTH);
		setBounds(300, 300, 300, 300);
		setResizable(false);
		addMouseListener(this);
	}
	
	public void paint(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 300, 300);
		g.setColor(Color.RED);
		g.fillOval(20, 100, 40, 40);
		g.setColor(Color.BLUE);
		g.fillOval(200, 200, 40, 40);
		g.setColor(Color.PINK);
		g.fillRect(0, 260, 300, 40);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		g.drawString(coords, 10, 285);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(distancia(20, 100, e.getX(), e.getY())<=40.0 || distancia(200, 200, e.getX(), e.getY())<=40.0){
			coords = e.getX()+", "+e.getY();
			paint(getGraphics());
		}
	}

	private double distancia(int x1, int y1, int x2, int y2) {
		return Math.sqrt(((double)((x1-x2)*(x1-x2)))+((double)((y1-y2)*(y1-y2))));
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		setExtendedState(JFrame.ICONIFIED);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
	
}
