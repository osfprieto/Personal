package misc;

import java.awt.BorderLayout;

import java.util.LinkedList;

import java.util.Queue;
import java.util.Stack;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class FromInToPostOrPre<E> extends LinkedList<E> implements Queue<E>{
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		int repeat=1;
		while(repeat>0){
			
			String ss[] = new String[2];
			ss[0] = "Pre Orden";
			ss[1] = "Post Orden";
			JComboBox cb = new JComboBox(ss);
			cb.setVisible(true);
			
			JPanel jp = new JPanel();
			jp.setLayout(new BorderLayout());
			
			jp.add(cb, BorderLayout.SOUTH);
			
			JTextField tf = new JTextField(15);
			tf.setVisible(true);
			
			jp.add(tf, BorderLayout.NORTH);
			
			jp.add(new JLabel("Pasar Expresión a:"));
			
			JOptionPane.showMessageDialog(null, jp, "Entrada", JOptionPane.QUESTION_MESSAGE);
			
			String input = tf.getText();
			
			repeat = operar(input, cb.getSelectedIndex());
			if(repeat==2)
				JOptionPane.showMessageDialog(null, "Hay muchos ')'", "Error", JOptionPane.ERROR_MESSAGE);
			else if(repeat==3)
				JOptionPane.showMessageDialog(null, "Hay muchos '('", "Error", JOptionPane.ERROR_MESSAGE);
			else if(repeat==4)
				JOptionPane.showMessageDialog(null, "Hay operadores mal organizados", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private static int operar(String input, int opcion){
		
		if(!operadoresValidos(input))
			return 4;
		else if (opcion == 0){//Prefija
			//http://ocw.udem.edu.mx/cursos-de-profesional/estructura-de-datos/contenido/modulo6_estructras_dinamicas/notacion-polaca/
			
			String output = "";
			
			Stack<Character> pila = new Stack<Character>(), aux = new Stack<Character>();
			for(int i=0;i<input.length();i++){
				char e = input.charAt(i);
				if (e >= '0' && e<='9')
					pila.add(e);
				else if((e>='a' && e<='z') || (e>='A' && e<='Z'))
					pila.add(e);
				else if(e=='+'||e=='-'||e=='*'||e=='/'){
					if(!aux.isEmpty())
						while(!aux.isEmpty() && precedencia(aux.peek())>=precedencia(e))
							pila.add(aux.pop());
					aux.add(e);
				}
				else if(e == '(')
					aux.add(e);
				else if(e == ')')
					if(!aux.isEmpty()){
						while(!aux.isEmpty() && aux.peek()!='(')
							pila.add(aux.pop());
						if(!aux.isEmpty())
							aux.pop();
						else
							return 2;
					}
					else
						return 2;
			}
			
			while(!aux.isEmpty())
				if(aux.peek()=='(')
					return 3;
				else
					pila.add(aux.pop());
			
			while(!pila.isEmpty())
				output+=""+pila.pop();
			
			JOptionPane.showMessageDialog(null, "Su expresión cambiada es:\n"+input+'\n'+output, "Salida", JOptionPane.PLAIN_MESSAGE);
				
			return 0;
		}
		else{//Postfija
			//http://es.wikipedia.org/wiki/Algoritmo_shunting_yard
			
			Stack<Character> stack = new Stack<Character>();
			Queue<Character> queue = new LinkedList<Character>();
			for(int i=0;i<input.length();i++)
				queue.add(input.charAt(i));
			String output = "";
			while(!queue.isEmpty()){
				
				Character e = queue.remove();
				
				if (e >= '0' && e<='9')
					output += ""+e;
				else if((e>='a' && e<='z') || (e>='A' && e<='Z'))
					output += ""+e;
				else if(e == '(')
					stack.push(e);
				else if(e == ')'){
					while(!stack.isEmpty() && stack.peek()!='(')
						output += ""+stack.pop();
					if(stack.isEmpty())
						return 2;
					else
						stack.pop();
				}
				
				else if(e=='+'||e=='-'||e=='*'||e=='/'){
			
					while(!stack.isEmpty() && precedencia(stack.peek())>=precedencia(e)){
						output += ""+stack.pop();
					}
					stack.push(e);
				}
				
			}
			
			while(!stack.isEmpty())
				output += ""+stack.pop();
			
			if(output.contains("("))
				return 3;
			
			JOptionPane.showMessageDialog(null, "Su expresión cambiada es:\n"+input+'\n'+output, "Salida", JOptionPane.PLAIN_MESSAGE);
			
			return 0;
		}
	}

	private static int precedencia(char peek) {
		if (peek == '*' || peek == '/' || peek == '^')
			return 2;
		else if(peek == '+' || peek == '-')
			return 1;
		else
			return 0;
	}
	
	private static boolean operadoresValidos(String input){
		char e, d;
		e = input.charAt(0);
		d = input.charAt(input.length()-1);
		if (e=='+'||e=='-'||e=='*'||e=='/'||d=='+'||d=='-'||d=='*'||d=='/')
			return false;
		for(int i=0;i<input.length()-1;i++){
			e = input.charAt(i);
			d = input.charAt(i+1);
			if (e=='+'||e=='-'||e=='*'||e=='/')
				if (d=='+'||d=='-'||d=='*'||d=='/')
					return false;
		}
		return true;
	}
	
}