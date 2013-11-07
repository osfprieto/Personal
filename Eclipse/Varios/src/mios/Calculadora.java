package mios;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Calculadora extends JFrame implements ActionListener{

	private JPanel Calculadora;
	private JPanel JBotonNumeros;
	private JPanel JBotonSignos;
	private JLabel muestra;
	//private JPanel Operaciones;
	private int resultado;
	private int memoria;
	private String bufferOperacion;
	
	public static void main(String args[]){
		Calculadora cd = new Calculadora();
		cd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cd.pack();
		cd.setBounds(400, 200, cd.getWidth(), cd.getHeight());
		cd.setVisible(true);
	}
	
	public Calculadora(){
		super("Calculadora");
		init();
	}
	
	private void init(){
		
		this.Calculadora= (JPanel)this.getContentPane();
		this.muestra = new JLabel("0");
		this.JBotonNumeros = new JPanel(new GridLayout(3,3));
		this.JBotonSignos = new JPanel(new GridLayout(3,2));
		this.bufferOperacion="";
		
		MyButton b1, s1;
		
		for (int i=1; i<=9; i++){
			b1 =new MyButton(""+i);
			b1.addActionListener(this);
		    this.JBotonNumeros.add(b1);
		}
		String signo;
		for(int j=0;j<6;j++){
			signo="";
			if(j==0)
				signo="+";
			else if (j==1)
				signo="-";
			else if (j==2)
				signo="*";
			else if(j==3)
				signo="%";
			else if (j==4)
				signo="=";
			else if(j==5)
				signo="CE";
			s1=new MyButton(signo);
			s1.addActionListener(this);
			this.JBotonSignos.add(s1);
			if(j==5)
				s1.doClick();
		}
		
		Calculadora.setLayout(new BorderLayout());
		Calculadora.add(muestra,BorderLayout.NORTH);
		Calculadora.add(JBotonNumeros,BorderLayout.CENTER);
		Calculadora.add(JBotonSignos,BorderLayout.EAST);
	}
	
	public void numeroOprimido(int digito){
		this.resultado*=10;
		this.resultado+=digito;
		this.muestra.setText(""+resultado);
		
		//System.out.println("botón "+digito+", bufferOperacion "+bufferOperacion);
		//System.out.println(memoria+" "+resultado);
		
	}
	private void operacionPulsado(String tecla) {
		
		if(bufferOperacion.equals("")){
			this.memoria=this.resultado;
		}
		else if(bufferOperacion.equals("+")){
			this.memoria += this.resultado;
		}
		else if(bufferOperacion.equals("*")){
			this.memoria *= this.resultado;
		}
		else if(bufferOperacion.equals("-")){
			this.memoria -= this.resultado;
		}
		else if(bufferOperacion.equals("%")){
			this.memoria /= this.resultado;
		}
		else if(bufferOperacion.equals("=")){
			this.memoria=this.resultado;
		}
		
		if(tecla.equals("+")){
			bufferOperacion="+";
		}
		else if(tecla.equals("*")){
			bufferOperacion="*";
		}
		else if(tecla.equals("-")){
			bufferOperacion="-";
		}
		else if(tecla.equals("%")){
			bufferOperacion="%";
		}
		else if(tecla.equals("=")){
			bufferOperacion="=";
		}
		else if(tecla.equals("CE")){
			this.bufferOperacion="";
			this.memoria=0;
		}
		this.resultado=0;
		this.muestra.setText(""+this.memoria);
		
		//System.out.println("botón "+tecla+", bufferOperacion "+bufferOperacion);
		//System.out.println(memoria+" "+resultado);
		
    }

	public void actionPerformed(ActionEvent e) {
		//System.out.println("Presión");
		try{
			int num = Integer.parseInt(((MyButton)e.getSource()).value);
			numeroOprimido(num);
		}
		catch(NumberFormatException exc){
			operacionPulsado(((MyButton)e.getSource()).value);
		}
	}
	
	private class MyButton extends JButton{
		public String value;
		private MyButton(String value){
			super(value);
			this.value = value;
		}
	}
	
}