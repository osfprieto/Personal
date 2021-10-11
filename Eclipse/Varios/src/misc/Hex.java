package misc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class Hex extends JFrame implements MouseListener{
		
	private int tamanioMat;
	private static final int WIDTH = 60;
	private static final int HEIGHT = 40;
	private static final int CONSTANTE_HORIZONTAL = 50;
	private static final int CONSTANTE_VERTICAL = 50;
	private static final Color COLOR_VACIA = Color.WHITE;
	private static final Color COLOR_PARED = Color.BLACK;
	private static final Color COLOR_BLANCAS = Color.RED;
	private static final Color COLOR_NEGRAS = Color.BLUE;
	public MyPolygon[][] mat;
	public boolean iniciado;
	public int turno;//-1 blancas, 1 negras;
	public boolean ableToPlay;
	public static MyPolygon paredUpLeft;
	public static MyPolygon paredDownRight;
	public static MyPolygon paredUpRight;
	public static MyPolygon paredDownLeft;
	public static boolean primerTurno;
	public static boolean contraPC;
	/*Encargado de inicializar el objeto de tipo Hex que hereda de JFrame para la interfaz Gr�fica*/
	public static void main(String[] args){
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2));
		String[] data = {"Jugador vs Jugador", "Jugador vs Computador", "Salir"};
		JComboBox lista = new JComboBox(data);
		JSpinner spinner = new JSpinner();
		panel.add(new JLabel("Seleccionar"));
		panel.add(lista);
		panel.add(new JLabel("Tamaño"));
		panel.add(spinner);
		
		JOptionPane.showMessageDialog(null, panel, "Inicio de Hex", JOptionPane.PLAIN_MESSAGE);
		
		try{
			if(lista.getSelectedIndex()==0){
				int tamanio = Integer.parseInt(spinner.getValue().toString());
				if(tamanio>0 && tamanio<16){
					Hex hex = new Hex(tamanio);
					Hex.contraPC = false;
					hex.setVisible(true);
					hex.setBounds(100, 100, hex.tamanioMat*Hex.WIDTH+(hex.tamanioMat-1)*(Hex.WIDTH/3)+2*CONSTANTE_HORIZONTAL, hex.tamanioMat*Hex.HEIGHT+2*CONSTANTE_VERTICAL);
					hex.setResizable(false);
					hex.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				else{
					throw new Exception();
				}
			}
			else if(lista.getSelectedIndex()==1){
				int tamanio = Integer.parseInt(spinner.getValue().toString());
				if(tamanio>0 && tamanio<16){
					Hex hex = new Hex(tamanio);
					Hex.contraPC = true;
					hex.setVisible(true);
					hex.setBounds(100, 100, hex.tamanioMat*Hex.WIDTH+(hex.tamanioMat-1)*(Hex.WIDTH/3)+2*CONSTANTE_HORIZONTAL, hex.tamanioMat*Hex.HEIGHT+2*CONSTANTE_VERTICAL);
					hex.setResizable(false);
					hex.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				else{
					throw new Exception();
				}
			}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Tama�o ingresado erroneo.\nReiniciar juego", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	/*Funci�n valor absoluto*/
	public static int abs(int i){
		if(i<0)
			return -i;
		else
			return i;
	}
	/*Constructro de la clase Hex*/
	public Hex(int tamanioMat){
		super("HEX");
		this.tamanioMat = tamanioMat;
		mat = new MyPolygon[this.tamanioMat][this.tamanioMat];
		this.iniciado = false;
		this.ableToPlay = true;
		this.turno = -1;
		this.addMouseListener(this);
		
		Hex.paredUpLeft = new MyPolygon(64, 64, 64, 64, 2);
		Hex.paredDownRight = new MyPolygon(64, 64, 64, 64, -3);
		Hex.paredUpRight = new MyPolygon(64, 64, 64, 64, 3);
		Hex.paredDownLeft = new MyPolygon(64, 64, 64, 64, -2);
		Hex.paredUpLeft.evaluado=true;
		Hex.paredDownRight.evaluado=true;
		Hex.paredUpRight.evaluado=true;
		Hex.paredDownLeft.evaluado=true;
		if(this.tamanioMat%2==1)
			Hex.primerTurno = true;
		else
			Hex.primerTurno = false;
	}
	/*Sobreescritura del m�todo paint de la clase Hex para el dibujado de los hex�gonos y el
	 * tablero en la interfaz gr�fica*/
	public void paint(Graphics g) {
		int n = this.tamanioMat;
		int w = Hex.WIDTH;
		int h = Hex.HEIGHT;
		int maxW = n*w+(n-1)*(w/3);
		int maxH = n*h;
		g.setColor(COLOR_BLANCAS);
		g.fillRect(0, maxH/2+Hex.CONSTANTE_VERTICAL, maxW/2+Hex.CONSTANTE_HORIZONTAL, maxH/2+Hex.CONSTANTE_VERTICAL);
		g.fillRect(maxW/2+Hex.CONSTANTE_HORIZONTAL, 0, maxW/2+Hex.CONSTANTE_HORIZONTAL, maxH/2+Hex.CONSTANTE_VERTICAL);
		g.setColor(COLOR_NEGRAS);
		g.fillRect(maxW/2+Hex.CONSTANTE_HORIZONTAL, maxH/2+Hex.CONSTANTE_VERTICAL, maxW/2+Hex.CONSTANTE_HORIZONTAL, maxH/2+Hex.CONSTANTE_VERTICAL);
		g.fillRect(0, 0, maxW/2+Hex.CONSTANTE_HORIZONTAL, maxH/2+Hex.CONSTANTE_VERTICAL);
		int coordY=0, coordX=0;
		if(!iniciado){
			iniciado = true;
			for(int i = -(n-1);i<=n-1;i++){
				for(int j = -(n-1-abs(i));j<=n-1-abs(i);j+=2){
					int x = maxW/2 - w/2 + (2*j*w)/3 + CONSTANTE_HORIZONTAL;
					int y = maxH/2 + (i*h)/2 + CONSTANTE_VERTICAL;
					MyPolygon poly = new MyPolygon(i, j, coordX, coordY, 0);
					poly.addPoint(x, y);//a
					poly.addPoint(x+w/3, y+h/2);//b
					poly.addPoint(x+(2*w)/3, y+h/2);//c
					poly.addPoint(x+w, y);//d
					poly.addPoint(x+(2*w)/3, y-h/2);//e
					poly.addPoint(x+w/3, y-h/2);//f
					mat[coordY][coordX] = poly;
					g.setColor(Hex.COLOR_VACIA);
					g.fillPolygon(poly);
					g.setColor(Hex.COLOR_PARED);
					g.drawPolygon(poly);
					coordX++;
					coordX%=this.tamanioMat;
					if(coordX==0){
						coordY++;
						coordY%=this.tamanioMat;
					}
					//String cadena = poly.toString();
					//g.drawChars(cadena.toCharArray(), 0, cadena.length(), x, y);
				}
			}
			iniciarVecinos();
		}
		else{
			for(int i=0;i<this.tamanioMat;i++)
				for(int j=0;j<this.tamanioMat;j++){
					MyPolygon poly = mat[i][j];
					if(poly.color==-1)
						g.setColor(Hex.COLOR_BLANCAS);
					else if(poly.color==0)
						g.setColor(Hex.COLOR_VACIA);
					else if(poly.color==1)
						g.setColor(Hex.COLOR_NEGRAS);
					g.fillPolygon(poly);
					g.setColor(Hex.COLOR_PARED);
					g.drawPolygon(poly);
				}
		}
		
	}
	/*M�todo que se ejecuta al comienzo del juego y hace que cada objeto de tipo MyPolygon asocie
	 * a los objetos que tendr� como vecinos, esto para hacer un recorrido de tiempo lineal sobre
	 * los caminos del tablero y las fichas ocupadas*/
	private void iniciarVecinos(){
		iniciarVecinosInternos(mat[0][0]);
		MyPolygon poly = mat[0][0];
		while(poly.downLeft!=Hex.paredDownLeft){
			poly.upLeft = Hex.paredUpLeft;
			poly.up = Hex.paredUpLeft;
			poly = poly.downLeft;
		}
		poly.upLeft = Hex.paredUpLeft;
		poly.up = Hex.paredUpLeft;
		poly = mat[this.tamanioMat-1][this.tamanioMat-1];
		while(poly.upRight!=Hex.paredUpRight){
			poly.downRight = Hex.paredDownRight;
			poly.down = Hex.paredDownRight;
			poly = poly.upRight;
		}
		poly.downRight = Hex.paredDownRight;
		poly.down = Hex.paredDownRight;
	}
	/*M�todo extra utilizado para complementar de manera recursiva una parte del m�todo iniciarVecinos*/
	private void iniciarVecinosInternos(MyPolygon poly) {
		if(!poly.evaluado){
			poly.evaluado = true;
			int cont = 0;
			int I = poly.y;
			int J = poly.x;
			for(int i=0;i<this.tamanioMat && cont<6;i++)
				for(int j=0;j<this.tamanioMat && cont<6;j++)
					if(J!=j || I!=i){
						MyPolygon polyBuscado = mat[i][j]; 
						if(polyBuscado.i==poly.i+1 && polyBuscado.j==poly.j+1){
							poly.downRight = polyBuscado;
							cont++;
						}
						else if(polyBuscado.i==poly.i+1 && polyBuscado.j==poly.j-1){
							poly.downLeft = polyBuscado;
							cont++;
						}
						else if(polyBuscado.i==poly.i-1 && polyBuscado.j==poly.j+1){
							poly.upRight = polyBuscado;
							cont++;
						}
						else if(polyBuscado.i==poly.i-1 && polyBuscado.j==poly.j-1){
							poly.upLeft = polyBuscado;
							cont++;
						}
						else if(polyBuscado.i==poly.i+2 && polyBuscado.j==poly.j){
							poly.down = polyBuscado;
							cont++;
						}
						else if(polyBuscado.i==poly.i-2 && polyBuscado.j==poly.j){
							poly.up = polyBuscado;
							cont++;
						}
						
						if(poly.upLeft==null){
							poly.upLeft = Hex.paredUpLeft;
							poly.up = Hex.paredUpLeft;
						}
						else
							iniciarVecinosInternos(poly.upLeft);
						if(poly.upRight==null){
							poly.upRight = Hex.paredUpRight;
							poly.up = Hex.paredUpRight;
						}
						else
							iniciarVecinosInternos(poly.upRight);
						if(poly.downRight==null){
							poly.downRight = Hex.paredDownRight;
							poly.down = Hex.paredDownRight;
						}
						else
							iniciarVecinosInternos(poly.downRight);
						if(poly.downLeft==null){
							poly.downLeft = Hex.paredDownLeft;
							poly.down = Hex.paredDownLeft;
						}
						else
							iniciarVecinosInternos(poly.downLeft);
						if(poly.up!=null)
							iniciarVecinosInternos(poly.up);
						if(poly.down!=null)
							iniciarVecinosInternos(poly.down);
					}
		}
	}
	/*M�todo que recorre todos los caminos de un color de ficha, si se han tocados las dos paredes
	 * correspondientes a cada color avisa que hay un ganador*/
	private void revisarGanar(MyPolygon poly) {
		
		for(int i=0;i<this.tamanioMat;i++)
			for(int j=0;j<this.tamanioMat;j++)
				this.mat[i][j].evaluado = false;
		
		Hex.paredUpLeft.evaluado=false;
		Hex.paredDownRight.evaluado=false;
		Hex.paredUpRight.evaluado=false;
		Hex.paredDownLeft.evaluado=false;
		
		recorrerVecinos(poly);
		
		if((Hex.paredDownLeft.evaluado&&Hex.paredUpRight.evaluado&&this.turno==-1) || (Hex.paredDownRight.evaluado&&Hex.paredUpLeft.evaluado&&this.turno==1))
			ganar();
		
	}
	/*M�todo que avisa que hay un ganador y finaliza el juego*/
	private void ganar(){
		this.ableToPlay=false;
		JOptionPane.showMessageDialog(null, "Ganaron las fichas "+((this.turno<0)?"blancas":"negras")+"!", "Hubo Ganador", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}
	/*M�todo usado por el m�todo revisarGanar para recorrer de manera recursiva y sin repetir
	 * los caminos de las fichas*/
	private void recorrerVecinos(MyPolygon poly){
		if(!poly.evaluado){
			poly.evaluado = true;
			int color = poly.color;
			if(!isWall(poly.up)){
				if(poly.up.color==color)
					recorrerVecinos(poly.up);
			}
			else{
				poly.up.evaluado=true;
			}
			if(!isWall(poly.upRight)){
				if(poly.upRight.color==color)
					recorrerVecinos(poly.upRight);
			}
			else{
				poly.upRight.evaluado=true;
			}
			if(!isWall(poly.downRight)){
				if(poly.downRight.color==color)
					recorrerVecinos(poly.downRight);
			}
			else{
				poly.downRight.evaluado=true;
			}
			if(!isWall(poly.down)){
				if(poly.down.color==color)
					recorrerVecinos(poly.down);
			}
			else{
				poly.down.evaluado=true;
			}
			if(!isWall(poly.downLeft)){
				if(poly.downLeft.color==color)
					recorrerVecinos(poly.downLeft);
			}
			else{
				poly.downLeft.evaluado=true;
			}
			if(!isWall(poly.upLeft)){
				if(poly.upLeft.color==color)
					recorrerVecinos(poly.upLeft);
			}
			else{
				poly.upLeft.evaluado=true;
			}
		}
	}
	/*M�todo que indica si un hex�gono es correspondiente a una pared o no*/
	private boolean isWall(MyPolygon poly){
		if(abs(poly.color)>1)
			return true;
		return false;
	}
	/*M�todo usado en momentos de desarrollo, ahora no es utilizado*/
	public void changeColor(Graphics g, int num){
		if(num==0)
			g.setColor(Color.RED);
		else if(num==1)
			g.setColor(Color.WHITE);
		else if(num==2)
			g.setColor(Color.BLUE);
		else if(num==4)
			g.setColor(Color.GREEN);
		else
			g.setColor(Color.GRAY);
	}
	/*Clase interna utilizada para representar a los hex�gonos (cada casilla del tablero) como
	 * un objeto cada una, al igual que almacenar las propiedades correspondientes como el color
	 * actual y un apuntamiento a cada uno de sus seis vecinos*/
	private class MyPolygon extends Polygon{
		
		public int i;
		public int j;
		public int color;
		public int x;
		public int y;//coordenadas x e y de la casilla en la matriz que las guarda
		public boolean evaluado;//usado para saber evaluar si alguien ha ganado o no y no evaluar el camino que pase por la misma casilla varias veces
		public MyPolygon up;
		public MyPolygon down;
		public MyPolygon upLeft;
		public MyPolygon upRight;
		public MyPolygon downLeft;
		public MyPolygon downRight;
		
		public MyPolygon(int i, int j, int x, int y, int color){
			super();
			this.i = i;
			this.j = j;
			this.color = color;//-1 blancas, 1 negras, 0 desocupado
			this.x = x;
			this.y = y;
			this.up = null;
			this.down = null;
			this.upLeft = null;
			this.upRight = null;
			this.downLeft = null;
			this.downRight = null;
			this.evaluado = false;
		}
		
		@SuppressWarnings("unused")
		public ArrayList<MyPolygon> getVecinos(){
			ArrayList<MyPolygon> array = new ArrayList<MyPolygon>();
			array.add(this.downLeft);
			array.add(this.down);
			array.add(this.downRight);
			array.add(this.upRight);
			array.add(this.up);
			array.add(this.upLeft);
			return array;
		}
		
		/*public String toString2(){
			return y+", "+x+": ("+i+", "+j+")"+" c = "+color;
		}*/
		public String toString(){
			return "("+i+", "+j+")"+" c = "+color;
		}
	}
	/*M�todo que maneja cada uno de los eventos en los que se hace clic sobre la pantalla*/
	public void mouseClicked(MouseEvent e) {
		
		int x = e.getX();
		int y = e.getY();
		MyPolygon poly=null;
		boolean found = false;
		for(int i=0;i<this.tamanioMat && !found;i++)
			for(int j=0;j<this.tamanioMat && !found;j++)
				if(this.mat[i][j].contains(new Point(x, y))){
					poly = mat[i][j];
					found = true;
				}
		
		if(found){
			if(!Hex.primerTurno || !(poly.i==0 && poly.j==0)){
				Hex.primerTurno = false;
				//System.out.println("Clicked: "+poly.toString());
				//System.out.println("Vecinos: "+poly.getVecinos().toString());
				if(poly.color==0){//si la casilla est� vac�a
					
					poly.color = this.turno;
					Graphics g = this.getGraphics();
					if(poly.color==-1)
						g.setColor(Hex.COLOR_BLANCAS);
					else if(poly.color==1)
						g.setColor(Hex.COLOR_NEGRAS);
					g.fillPolygon(poly);
					g.setColor(Hex.COLOR_PARED);
					g.drawPolygon(poly);
					this.revisarGanar(poly);
					this.turno*=(-1);
					if(Hex.contraPC && this.turno==1){
						this.jugarComputadora(poly);
						this.turno*=(-1);
					}
				}
			}
		}
	}
	
	/*M�todol encargado de indicar qu� casilla es llenada por la computadora*/
	public void jugarComputadora(MyPolygon poly){
		
		int selec=0;
		int cont=0;
		boolean exc=false;
		try{
			while(cont<this.tamanioMat && poly.color!=0){
				if(poly.upRight.color==0)
					poly = poly.upRight;
				else if(poly.downLeft.color==0)
					poly = poly.downLeft;
				else if(poly.up.color==0)
					poly = poly.up;
				else if(poly.upLeft.color==0)
					poly = poly.upLeft;
				else if(poly.downRight.color==0)
					poly = poly.downRight;
				else
					poly = poly.down;
				cont++;
			}
		}catch(Exception e){
			exc=true;
		}
		
		if(poly.color!=0 || exc){
			poly=mat[0][0];
		
			while(poly.color!=0){
				selec = (int)(Math.random()*10000);
				selec%=this.tamanioMat*this.tamanioMat;
				poly = this.mat[selec/this.tamanioMat][selec%this.tamanioMat];
			}
		}
		
		//hasta ac� es el algoritmo de selecci�n del hex�gono a cambiar
		//hacia adelante es proceso de redibujo del hex�gono
		
		poly.color = this.turno;
		Graphics g = this.getGraphics();
		if(poly.color==-1)
			g.setColor(Hex.COLOR_BLANCAS);
		else if(poly.color==1)
			g.setColor(Hex.COLOR_NEGRAS);
		g.fillPolygon(poly);
		g.setColor(Hex.COLOR_PARED);
		g.drawPolygon(poly);
		this.revisarGanar(poly);
	}
	/*M�todos no utilizados que se encuentran ah� al implementar la interfaz MouseListener*/
	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
	
}