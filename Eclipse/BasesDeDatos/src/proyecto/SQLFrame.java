package proyecto;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class SQLFrame extends JFrame implements ActionListener{
	
	private PanelPregunta[] preguntas;
	private int conteoPreguntas;
	private int maxPreguntas;
	private JPanel panelPrincipal;
	private JScrollPane scroll;
	private JButton botonAceptar;
	private Container cont;
	private boolean enEspera;
	
	public SQLFrame(String name, int maxPreguntas){
		super(name);
		conteoPreguntas = 0;
		this.maxPreguntas = maxPreguntas;
		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new GridLayout(this.maxPreguntas, 1));
		preguntas = new PanelPregunta[maxPreguntas];
		scroll = new JScrollPane(panelPrincipal);
		JPanel panelPrimario = new JPanel();
		botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener(this);
		cont = this.getContentPane();
		cont.removeAll();
		//Codigo de prueba de preguntas únicas
		/*preguntas = new PanelPregunta[1];
		preguntas[0] = new PanelPregunta("Test", 0, null, false);
		panelPrincipal.add(preguntas[0]);*/
		panelPrimario.setLayout(new BorderLayout());
		panelPrimario.add(scroll, BorderLayout.CENTER);
		panelPrimario.add(botonAceptar, BorderLayout.SOUTH);
		cont.add(panelPrimario);
		this.setBounds(100, 100, 0, 0);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setVisible(boolean flag){
		super.setVisible(flag);
		enEspera = flag;
	}
	
	public void actionPerformed(ActionEvent e) {
		int i=0;
		boolean error=false;
		for(i=0;i<this.conteoPreguntas && !error;i++){
			if(!preguntas[i].esValida())
				error=true;
		}
		if(error)
			JOptionPane.showMessageDialog(null, "Error de datos en:\n"+preguntas[--i].getName(), "Error", JOptionPane.ERROR_MESSAGE);
		else
			setVisible(false);
	}
	
	public String getInsertSQLCommand(String tableName){
		String sql = "insert into "+tableName+" values (";
		int i;
		boolean comilla;
		for(i=0;i<this.conteoPreguntas;i++){
			if(!preguntas[i].toString().equals("")){
				if(preguntas[i].getTipo()==PanelPregunta.TIPO_ENTRADA_DATE || preguntas[i].getTipo()==PanelPregunta.TIPO_ENTRADA_PARRAFO || preguntas[i].getTipo()==PanelPregunta.TIPO_ENTRADA_TEXTO || preguntas[i].getTipo()==PanelPregunta.TIPO_SELECCION_LISTA){
					sql+="'";
					comilla = true;
				}
				else
					comilla = false;
				sql+=preguntas[i];
				if(comilla)
					sql+="'";
			}
			else
				sql+="null";
			if(i<preguntas.length-1)
				sql+=", ";
		}
		sql+=");";
		return sql;
	}
	
	public void addPregunta(String labelValue, int tipoPregunta, String[] args, boolean allowsNull){
		if(maxPreguntas>conteoPreguntas){
			preguntas[conteoPreguntas] = new PanelPregunta(labelValue, tipoPregunta, args, allowsNull);
			panelPrincipal.add(preguntas[conteoPreguntas++]);
			if(conteoPreguntas>=10)
				this.setSize(800, 400);
			else
				this.pack();
		}
		else{
			JOptionPane.showConfirmDialog(null, "Se ha intentado agregar más preguntas de\nlas ingresadas como parámetro máximo con:\n"+labelValue, "Overflow", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public boolean enEspera(){
		return enEspera;
	}
	public PanelPregunta getPregunta(int index){
		return preguntas[index];
	}
}
