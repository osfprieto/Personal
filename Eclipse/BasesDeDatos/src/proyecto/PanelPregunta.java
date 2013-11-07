package proyecto;

import java.awt.GridLayout;

import javax.swing.*;

@SuppressWarnings("serial")
public class PanelPregunta extends JPanel {

	public static final int TIPO_ENTRADA_TEXTO = 0;
	public static final int TIPO_ENTRADA_DATE = 1;
	public static final int TIPO_SELECCION_LISTA = 2;
	public static final int TIPO_ENTRADA_PARRAFO = 3;
	public static final int TIPO_ENTRADA_NUMERO = 4;
	public static final String[] DIAS = {"", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
										"11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
										"21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
	public static final String[] MESES = {"", "01", "02", "03", "04", "05", "06",
										"07", "08", "09", "10", "11", "12"};
	public static final String[] MESES_LETRAS = {"", "Enero", "Febrero", "Marzo", "Abril",
												"Mayo", "Junio", "Julio", "Agosto",
												"Septiembre", "Octubre", "Noviembre", "Diciembre"};
	
	private JLabel label;
	private int tipo;
	private boolean allowsNull;
	// tipo entrada de texto o número
	private JTextField text;
	// tipo date
	private JComboBox comboDia;
	private JComboBox comboMes;
	private JTextField textAnio;
	// tipo selección de una lista
	private JComboBox combo;
	private String[] args;
	// tipo entrada párrafo
	private JTextArea textArea;

	public PanelPregunta(String labelValue, int tipoPregunta, String[] args, boolean allowsNull) {
		super();
		this.tipo = tipoPregunta;
		this.label = new JLabel(((allowsNull)?"":"* ")+labelValue);
		this.args = args;
		this.allowsNull = allowsNull;
		this.inicializar();
	}

	private void inicializar(){
		if(this.tipo == PanelPregunta.TIPO_ENTRADA_TEXTO || this.tipo==PanelPregunta.TIPO_ENTRADA_NUMERO){
			this.text = new JTextField();
			this.removeAll();
			this.setLayout(new GridLayout(1, 2));
			this.add(this.label);
			this.add(text);
			if(args!=null){
				text.setText(args[0]);
				text.setEditable(false);
			}
		}
		else if(this.tipo == PanelPregunta.TIPO_ENTRADA_DATE){
			this.comboDia = new JComboBox(PanelPregunta.DIAS);
			this.comboMes = new JComboBox(PanelPregunta.MESES_LETRAS);
			this.textAnio = new JTextField();
			this.removeAll();
			this.setLayout(new GridLayout(1, 7));
			this.add(this.label);
			this.add(new JLabel("Día:"));
			this.add(this.comboDia);
			this.add(new JLabel("Mes:"));
			this.add(this.comboMes);
			this.add(new JLabel("Año:"));
			this.add(this.textAnio);
		}
		else if(this.tipo == PanelPregunta.TIPO_SELECCION_LISTA){
			this.combo = new JComboBox(this.args);
			this.removeAll();
			this.setLayout(new GridLayout(1, 2));
			this.add(this.label);
			this.add(this.combo);
		}
		else if(this.tipo == PanelPregunta.TIPO_ENTRADA_PARRAFO){
			this.textArea = new JTextArea();
			this.removeAll();
			JScrollPane scroll = new JScrollPane(this.textArea);
			this.setLayout(new GridLayout(1, 2));
			this.add(this.label);
			this.add(scroll);
		}
	}
	
	public String toString(){
		if(this.tipo == PanelPregunta.TIPO_ENTRADA_TEXTO  || this.tipo==PanelPregunta.TIPO_ENTRADA_NUMERO)
			return this.text.getText();
		else if(this.tipo == PanelPregunta.TIPO_ENTRADA_DATE && !textAnio.getText().equals(""))
			return this.textAnio.getText()+"-"+PanelPregunta.MESES[this.comboMes.getSelectedIndex()]+"-"+PanelPregunta.DIAS[this.comboDia.getSelectedIndex()];
		else if(this.tipo == PanelPregunta.TIPO_SELECCION_LISTA)
			return this.args[this.combo.getSelectedIndex()];
		else if(this.tipo == PanelPregunta.TIPO_ENTRADA_PARRAFO)
			return this.textArea.getText().replace('\n', ' ');
		else
			return "";
	}
	
	public boolean esValida(){
		if(!allowsNull() && toString().equals(""))
			return false;
		else if(allowsNull() && toString().equals(""))
			return true;
		else{
			try{
				if(this.tipo == PanelPregunta.TIPO_ENTRADA_TEXTO && (toString().toLowerCase().contains("select") || toString().toLowerCase().contains("insert") || toString().toLowerCase().contains("drop")))
					return false;
				else if (this.tipo==PanelPregunta.TIPO_ENTRADA_NUMERO && Double.parseDouble(toString())<=0)
					return false;
				else if(this.tipo == PanelPregunta.TIPO_ENTRADA_DATE && ((Double.parseDouble(textAnio.getText())<0 || Double.parseDouble(textAnio.getText())>2012) || comboDia.getSelectedIndex()==0 || comboMes.getSelectedIndex()==0))
					return false;
				else if(this.tipo == PanelPregunta.TIPO_SELECCION_LISTA)
					return true;
				else if(this.tipo == PanelPregunta.TIPO_ENTRADA_PARRAFO && (toString().toLowerCase().contains("select") || toString().toLowerCase().contains("insert") || toString().toLowerCase().contains("drop")))
					return false;
				else
					return true;
			}
			catch(Exception e){
				return false;
			}
		}
	}
	
	public String getName(){
		return this.label.getText();
	}
	
	public boolean allowsNull(){
		return this.allowsNull;
	}
	
	public int getTipo(){
		return this.tipo;
	}
}
