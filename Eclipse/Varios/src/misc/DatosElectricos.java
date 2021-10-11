package misc;

import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class DatosElectricos extends JFrame implements ListSelectionListener, CaretListener{
	//contenedores
	private static Container cont;
	private static JPanel inputPanel;
	private static JPanel outputPanel;
	//labels para indicar
	private static JLabel listaHp;//input
	private static JLabel listaET;//input
	private static JLabel longitud;//input
	private static JLabel listMaterial;//input
	private static JLabel listAplicacion;//input
	private static JLabel amp;//output
	private static JLabel fc;//output
	private static JLabel res;//output
	private static JLabel awg;//output
	//cuadros de texto y labels para mostrar resultados
	@SuppressWarnings({ "rawtypes" })
	private static JList listaHpDato;
	@SuppressWarnings("rawtypes")
	private static JList listaETDato;
	private static JTextField longitudDato;
	@SuppressWarnings("rawtypes")
	private static JList listaMaterialDato;
	@SuppressWarnings("rawtypes")
	private static JList listAplicacionDato;
	private static JLabel ampDato;
	private static JLabel fcDato;
	private static JLabel resDato;
	private static JLabel awgDato;
	//tabla de datos
	private static final int inf = -32000;
	private static final int valoresEt[] = {115, 230};
	private static final double matHp[][] = {{0.5,4,2},
											{0.75,5.6,2.8},
											{1,7.2,3.6},
											{1.5,10.4,5.2},
											{2,13.6,6.8},
											{3,0,9.6},
											{5,0,15.2},
											{7.5,0,22},
											{10,0,28},
											{15,0,42},
											{20,0,54},
											{25,0,68}};
	
	private static final int matAwg[][][] = {{{inf,inf,inf,25},
											{inf,inf,27,27},
											{20,20,30,30},
											{25,25,40,40},
											{40,40,55,55},
											{55,65,70,70},
											{80,95,100,100},
											{105,125,135,135},
											{120,145,155,155},
											{140,170,180,180},
											{165,195,210,210},
											{195,230,245,245},
											{225,265,285,285},
											{260,310,330,330},
											{300,360,385,385},
											{340,405,425,425},
											{375,445,480,480},
											{420,505,530,530},
											{455,545,575,575},
											{515,620,660,660}},
											
											{{inf,inf,inf,inf},
											{inf,inf,inf,inf},
											{inf,inf,inf,inf},
											{20,20,30,30},
											{30,30,45,45},
											{45,55,55,55},
											{60,75,80,80},
											{80,100,105,105},
											{95,115,120,120},
											{110,135,140,140},
											{130,155,160,165},
											{150,180,190,190},
											{175,210,220,220},
											{200,240,255,255},
											{230,280,300,300},
											{265,315,330,330},
											{290,350,375,375},
											{330,395,415,415},
											{355,425,450,450},
											{405,485,515,515}}};
	
	private static final String nombreAwg[] = {"18",
												"16",
												"14",
												"12",
												"10",
												"8",
												"6",
												"4",
												"3",
												"2",
												"1",
												"0.1",
												"0.01",
												"0.001",
												"0.0001",
												"250",
												"300",
												"350",
												"400",
												"500"};
	
	private static final double matResistencia[][] = {{0.0001,0.161},
													{0.001,0.202},
													{0.01,0.256},
													{0.1,0.322},
													{1,0.406},
													{2,0.513},
													{4,0.815},
													{6,1.3},
													{8,2.06},
													{10,3.28},
													{12,5.21},
													{14,8.28},
													{16,13.18}};
	
	public static void main(String args[]){
		DatosElectricos de = new DatosElectricos();
		de.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		de.setBounds(100, 100, 400, 400);
		de.setVisible(true);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DatosElectricos(){
		super("Datos");
		cont = this.getContentPane();
		cont.removeAll();
		cont.setLayout(new GridLayout(2, 1));
		inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(2,5));
		outputPanel = new JPanel();
		outputPanel.setLayout(new GridLayout(2,4));
		
		listaHp = new JLabel("HP");
		String[] datosHp = {"1/2", "3/4", "1", "1 1/2", "2", "3", "5", "7 1/2", "10", "15", "20", "25"};
		listaHpDato = new JList(datosHp);
		listaHpDato.addListSelectionListener(this);
		JScrollPane scrollHp = new JScrollPane(listaHpDato);
		
		listaET = new JLabel("ET");
		String datosET[] = {"Monof�sico", "Bif�sico o trif�sico"};
		listaETDato = new JList(datosET);
		listaETDato.addListSelectionListener(this);
		
		longitud = new JLabel("Longitud");
		longitudDato = new JTextField();
		longitudDato.addCaretListener(this);
		
		listMaterial = new JLabel("Material");
		String[] datosMaterial = {"Cobre", "Aluminio"};
		listaMaterialDato = new JList(datosMaterial);
		listaMaterialDato.addListSelectionListener(this);
		
		listAplicacion = new JLabel("Aplicaci�n");
		String[] datosAplicacion = {"Seco o h�medo", "Seco", "Muy h�medo", "H�medo"};
		listAplicacionDato = new JList(datosAplicacion);
		
		inputPanel.add(listaET);
		inputPanel.add(listaHp);
		inputPanel.add(longitud);
		inputPanel.add(listMaterial);
		inputPanel.add(listAplicacion);
		
		inputPanel.add(listaETDato);
		inputPanel.add(scrollHp);
		inputPanel.add(longitudDato);
		inputPanel.add(listaMaterialDato);
		inputPanel.add(listAplicacionDato);
		
		fc = new JLabel("FC");
		res = new JLabel("Res");
		amp = new JLabel("Amps");
		awg = new JLabel("AWG");
		fcDato = new JLabel();
		resDato = new JLabel();
		ampDato = new JLabel();
		awgDato = new JLabel();
		
		outputPanel.add(fc);
		outputPanel.add(res);
		outputPanel.add(awg);
		outputPanel.add(amp);
		outputPanel.add(fcDato);
		outputPanel.add(resDato);
		outputPanel.add(awgDato);
		outputPanel.add(ampDato);
		
		cont.add(inputPanel);
		cont.add(outputPanel);
		
	}

	public void caretUpdate(CaretEvent arg0) {
		actualizar();
	}
	public void valueChanged(ListSelectionEvent arg0) {
		actualizar();
	}
	private void actualizar(){
		//JOptionPane.showMessageDialog(null, "Algo cambi�!", "Alerta", JOptionPane.INFORMATION_MESSAGE);
		//C�digo que hace las operaciones y asigna los valores
		
		try{
			int selectEt = listaETDato.getSelectedIndex();
			int selectHp = listaHpDato.getSelectedIndex();
			double amps = matHp[selectHp][selectEt];
			ampDato.setText(amps+" amp");
			
			int selectAplicacion = listAplicacionDato.getSelectedIndex();
			int selectMaterial = listaMaterialDato.getSelectedIndex();
			
			int indexAwg=0;
			while(amps>matAwg[selectMaterial][indexAwg][selectAplicacion])
				indexAwg++;
			awgDato.setText(nombreAwg[indexAwg]);
			
			double awgValue = Double.parseDouble(nombreAwg[indexAwg]);
			double lInput = Double.parseDouble(longitudDato.getText());
			int indexRes=0;
			double res;
			while(indexRes<matResistencia.length-1 || awgValue>matResistencia[indexRes][0])
				indexRes++;
			if(indexRes==matResistencia.length-1){
				resDato.setText("Datos no tabulado");
				res = 0;
			}
			else{
				double res0 = matResistencia[indexRes][1];
				res = (lInput*res0)/1000;
				resDato.setText(res+" Ohm");
			}
			
			double fcValue = (res*amps)/valoresEt[selectEt];
			fcDato.setText(fcValue+"");
			
		}
		catch(Exception e){
			ampDato.setText("Esperando");
			awgDato.setText("Esperando");
			resDato.setText("Esperando");
			fcDato.setText("Esperando");
		}
	}
}
