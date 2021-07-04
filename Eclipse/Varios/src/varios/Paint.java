package varios;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class Paint extends JFrame implements MouseListener, Runnable{
	
	private static final int DOT_SIZE = 10;
	private static final Color COLOR_FONDO = Color.RED;
	private static final Color COLOR_MOUSE = Color.YELLOW;
	private static final int X_BOUND = 200;
	private static final int Y_BOUND = 100;
	private static final int ANCHO = 1000;
	private static final int ALTO = 500;
	
	
	private Container container;
	private JPanel panelPrincipal;
	private Graphics graphics;
	private Point point;
	private Point lastPoint;
	private boolean seguirPintando;
	private Thread hiloPintor;
	
	
	public static void main(String[] args) {
		Paint paint = new Paint();
		paint.setVisible(true);
		paint.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public Paint(){
		super("Paint - osfprieto");
		container = this.getContentPane();
		container.removeAll();
		panelPrincipal = new JPanel();
		panelPrincipal.addMouseListener(this);
		container.add(panelPrincipal);
		setBounds(X_BOUND, Y_BOUND, ANCHO, ALTO);
		setResizable(false);
		point = new Point();
		lastPoint = point;
		seguirPintando = false;
		hiloPintor = new Thread(this, "Hilo pintor");
		hiloPintor.start();
	}

	public void paint(Graphics g){
		if(!lastPoint.equals(point)){
			g.setColor(COLOR_FONDO);
			//g.fillOval(lastPoint.x-DOT_SIZE/2, lastPoint.y-DOT_SIZE/2, DOT_SIZE, DOT_SIZE);
			g.setColor(COLOR_MOUSE);
			g.fillOval(point.x-DOT_SIZE/2, point.y-DOT_SIZE/2, DOT_SIZE, DOT_SIZE);
		}
	}
	
	public void setVisible(boolean flag){
		super.setVisible(flag);
		if(flag){
			graphics = panelPrincipal.getGraphics();
			graphics.setColor(COLOR_FONDO);
			graphics.fillRect(0, 0, getWidth(), getHeight());
		}
	}
	
	private void flood(MouseEvent e){
		
	}
	
	public void mouseClicked(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON3)
			flood(e);
	}

	public void mouseEntered(MouseEvent e) {
		//actualizar(e);
	}

	public void mouseExited(MouseEvent e) {
		//actualizar(e);
	}

	public void mousePressed(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1)
			seguirPintando = true;
	}

	public void mouseReleased(MouseEvent e) {
		seguirPintando = false;
	}
	
	public void actualizarPuntos(){
		Point coordenada = panelPrincipal.getMousePosition();
		lastPoint = point;
		point = new Point(coordenada.x, coordenada.y);
		//System.out.println(e.getX()+", "+e.getY());
	}
	
	public void actualizar(){
		try{	
			actualizarPuntos();
			paint(graphics);
		}
		catch(NullPointerException e){
			//System.out.println("Mouse fuera del panel");
		}
	}
	
	public void run() {
		while(true)
			if(seguirPintando)
				actualizar();
	}
}
