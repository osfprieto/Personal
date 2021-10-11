package misc;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class Tests extends JFrame implements ActionListener{
	private int tamanioMat;
	private final static int WIDTH = 60;
	private final static int HEIGHT = 40;
	
	//private JButton button;
	private JCheckBox cb;
	private static JPanel panel;
	
	public static void main(String[] args){
		GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    Font[] fonts = e.getAllFonts(); // Get the fonts
	    for (Font f : fonts) {
	      System.out.println(f.getFontName());
	    }
	}
	
	/*public static void main(String[] args){
		FrameCarga frameCarga = new FrameCarga();
		frameCarga.setVisible(true);
		frameCarga.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	*/
	/*
	public static void main(String[] args){
		String lista[] = {"asdf", "asdfasdf", "asdfasdfasdf"};
		JComboBox<String> cb = new JComboBox<String>(lista);
		panel = new JPanel();
		panel.add(cb);
		JOptionPane.showMessageDialog(null, panel, "aasdf", JOptionPane.PLAIN_MESSAGE);
		JOptionPane.showMessageDialog(null, cb.getSelectedItem().toString(), "asdf", JOptionPane.PLAIN_MESSAGE);
	}*/
	
	@SuppressWarnings("unused")
	private static class FrameCarga extends JFrame{
        public FrameCarga(){
            super("Guardando");
            getContentPane().removeAll();
            //add(new PanelImagen(PanelImagen.leerImagen("cargando.gif")));
            //ImageIcon imageIcon = new ImageIcon(FrameCarga.this.getClass().getResource("cargando.gif")/*"recursos/cargando.gif"*/);
            panel = new JPanel();
            panel.setLayout(new FlowLayout());
            panel.add(new JLabel("Espere mientras se guarda el reporte"));
            setResizable(false);
            getContentPane().add(panel);
            pack();
            setPreferredSize(getSize());
            setUndecorated(true);
            setLocation(new Point(100, 100));
        }
    }
	
	/*public static void main(String[] args){
		JFrame ventana = new Tests("Test toggle button");
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public Tests(String name){
		super(name);
		button = new JButton("Mirar");
		button.addActionListener(this);
		cb = new JCheckBox("Toggle");
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(button);
		panel.add(cb);
		Container cont = this.getContentPane();
		cont.removeAll();
		cont.add(panel);
		this.setBounds(100, 100, 200, 100);
	}*/
	/*public static void main(String[] args){
		JOptionPane.showMessageDialog(null, new JLabel(new ImageIcon("test.png")), "Test", JOptionPane.PLAIN_MESSAGE);
	}*/
	
	/*public static void main(String[] args){
		int cont=0;
		for(int i=2;i<1000000;i++)
			if(primo(i))
				//System.out.println(i+", "+(cont++));
				cont++;
		System.out.println(cont);
	}*/
	
	@SuppressWarnings("unused")
	private static boolean primo(int n){
		for(int i=2;i<n;i++)
			if(n%i==0)
				return false;
		return true;
	}
	
	/*public static void main(String[] args){
		long ini;
		int limite = 10, delta=10, i, temp;
		
		while(limite<10000){
			int nums[] = new int[limite];
			
			for(i=0;i<limite;i++)
				nums[i] = (int) Math.random()*limite;
			//System.out.println("Start");
			ini = System.currentTimeMillis();
			
			////////////////////////////////////////////
			
			//bubble sort
			//bubblesort(nums, limit);
			
			//quicksort
			quicksort(nums, 0, limite-1);
			
			////////////////////////////////////////////
			
			long time = (System.currentTimeMillis()-ini);
			
			temp = nums[0];
			for(i=0;i<limite;i++)
				if(temp>nums[i])
					System.exit(0);
				else
					temp = nums[i];
			
			System.out.println(limite+"\t"+time);
			
			limite+=delta;
		}
	}*/
	
	public static void bubblesort(int[] arreglo, int limite){
		int i, j, temp;
		for(i=0;i<limite;i++){
			//System.out.println(i);
			for(j=0;j<limite;j++)
				if(arreglo[j]>arreglo[i]){
					temp = arreglo[i];
					arreglo[i] = arreglo[j];
					arreglo[j] = temp;
				}
		}
	}
	
	public static void quicksort(int[] arreglo, int p, int r){
		if(p<r){
			int q = partition(arreglo, p, r);
			quicksort(arreglo, p, q-1);
			quicksort(arreglo, q+1, r);
		}
	}
	
	//used by quicksort
	public static int partition(int[] arreglo, int p, int r){
		
		int x = arreglo[r];
		int i = p-1, j, temp;
		for(j=p;j<r;j++)
			if(arreglo[j]<=x){
				i++;
				temp = arreglo[i];
				arreglo[i] = arreglo[j];
				arreglo[j] = temp;
			}
		temp = arreglo[i+1];
		arreglo[i+1] = arreglo[r];
		arreglo[r] = temp;
		
		return i+1;
	}
	
	/*public static void main(String[] args){
		try{
			String input = JOptionPane.showInputDialog("Ingrese la cadena a convertir");
		
			byte[] bytes = input.getBytes("UTF-8");
		
			MessageDigest md = MessageDigest.getInstance("MD5");
		
			byte[] md5 = md.digest(bytes);
			
			JOptionPane.showMessageDialog(null, new String(md5, "UTF-8"));
			
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e.toString(), "Excepci�n", JOptionPane.ERROR_MESSAGE);
		}
	}*/
	
	/*public static void main(String[] args){
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		
		q.add(4);
		q.add(2);
		q.add(3);
		q.add(5);
		q.add(1);
		q.add(6);
		
		while(!q.isEmpty())
			System.out.println(q.poll());
	}*/
	
	/*public static void main(String[] args){
		
		Tests test = new Tests(4);
		test.setVisible(true);
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}*/
	public static int abs(int i){
		if(i<0)
			return -i;
		else
			return i;
	}
	
	/*private static int cont;
	public static void main(String[] args){
		
		cont=0;
		
		DefaultTableModel model = new DefaultTableModel();
		JTable table = new JTable(model);
		JScrollPane scrollpane = new JScrollPane(table);
		
		JOptionPane.showMessageDialog(null, scrollpane, "Datos "+(cont++), JOptionPane.PLAIN_MESSAGE);
		
		// Create a couple of columns
		model.addColumn("Col1");
		model.addColumn("Col2");
		
		JOptionPane.showMessageDialog(null, scrollpane, "Datos "+(cont++), JOptionPane.PLAIN_MESSAGE);
		
		// Append a row
		model.addRow(new Object[]{"v1", "v2"});
		// there are now 2 rows with 2 columns
		
		JOptionPane.showMessageDialog(null, scrollpane, "Datos "+(cont++), JOptionPane.PLAIN_MESSAGE);
		
		// Append a row with fewer values than columns.
		// The left-most fields in the new row are populated
		// with the supplied values (left-to-right) and fields
		// without values are set to null.
		model.addRow(new Object[]{"v1"});
		// there are now 3 rows with 2 columns
		
		JOptionPane.showMessageDialog(null, scrollpane, "Datos "+(cont++), JOptionPane.PLAIN_MESSAGE);
		
		// Append a row with more values than columns.
		// The extra values are ignored.
		model.addRow(new Object[]{"v1", "v2", "v3"});
		// there are now 4 rows with 2 columns
		
		JOptionPane.showMessageDialog(null, scrollpane, "Datos "+(cont++), JOptionPane.PLAIN_MESSAGE);
		
		 /*DefaultTableModel dataModel = new DefaultTableModel() {
	          public int getColumnCount() { return 10; }
	          public int getRowCount() { return 10;}
	          public Object getValueAt(int row, int col) { return new Integer(row*col); }
	      };
	      JTable table = new JTable(dataModel);
	      JScrollPane scrollpane = new JScrollPane(table);
	      scrollpane.setHorizontalScrollBar(new JScrollBar());
	      for(int i=0;i<5;i++){
	    	  JOptionPane.showMessageDialog(null, scrollpane, "Datos", JOptionPane.PLAIN_MESSAGE);
	    	  table.addColumn(new TableColumn());
	      }
	      table.addRowSelectionInterval(5, dataModel.getRowCount()-1);
	      JOptionPane.showMessageDialog(null, scrollpane, "Datos", JOptionPane.PLAIN_MESSAGE);
	      dataModel.addRow(new Object[]{"khgfd"});* /
	}*/
	
	/*public static void main(String[] args) {
		TestFrame testFrame = new TestFrame();
		testFrame.setVisible(true);
		testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}*/
	
	public Tests(int tamanioMat){
		super("Tests");
		this.tamanioMat =tamanioMat; 
		this.setBounds(100, 100, 300, 400);
	}
	public void paint(Graphics g) {
		int n = this.tamanioMat;
		g.setColor(Color.RED);
		int w = Tests.WIDTH;
		int h = Tests.HEIGHT;
		int maxW = n*w+(n-1)*(w/3);
		int maxH = n*h;
		for(int i = -(n-1);i<=n-1;i++){
			for(int j = -(n-1-abs(i));j<=n-1-abs(i);j+=2){
				int x = maxW/2 - w/2 + (2*j*w)/3;
				int y = maxH/2 + (i*h)/2;
				Polygon poly = new Polygon();
				poly.addPoint(x, y);//a
				poly.addPoint(x+w/3, y+h/2);//b
				poly.addPoint(x+(2*w)/3, y+h/2);//c
				poly.addPoint(x+w, y);//d
				poly.addPoint(x+(2*w)/3, y-h/2);//e
				poly.addPoint(x+w/3, y-h/2);//f
				g.drawPolygon(poly);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JOptionPane.showMessageDialog(null, ((cb.isSelected())?"Enabled":"Disabled"), "output", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/*public static void main(String[] args) {
		Tests testFrame = new Tests();
		testFrame.setVisible(true);
		testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}*/
	
}