package misc;

import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JOptionPane;

public class StringFormulas {
	public static void main(String[] args) {
		String input = JOptionPane.showInputDialog(null, "Entre la f�rmula");
		JOptionPane.showMessageDialog(null,  input+" = "+analizarFormula(input), "Resultado", JOptionPane.PLAIN_MESSAGE);
	}
	public static String analizarFormula(String input){
		try{
			ArrayList<String> tokens = new ArrayList<String>();
			int i;
			String temp="";
			char c;
			
			//separo los tokens
			
			for(i=0;i<input.length();i++){
				c = input.charAt(i);
				if(c=='+' || c=='-' || c=='*' || c=='/' || c=='(' || c==')'){
					if(!temp.equals("")){
						tokens.add(temp);
						temp="";
					}
					tokens.add(""+c);
				}
				else{
					temp = temp+c;
				}
			}
			
			//paso a notaci�n postfija
			
			ArrayList<String> postfijo = new ArrayList<String>();
			Stack<String> pila = new Stack<String>();
			
			for(i=0;i<tokens.size();i++){
				temp = tokens.get(i);
				if(temp.equals("("))
					pila.add(temp);
				else if(temp.equals("+") || temp.equals("-") || temp.equals("*") || temp.equals("/")){
					while(!(pila.isEmpty() || prioridad(temp)>prioridad(pila.peek())))
						postfijo.add(pila.pop());
					pila.add(temp);
				}
				else if(temp.equals(")")){
					while(!pila.peek().equals("("))
						postfijo.add(pila.pop());
					pila.pop();
				}
				else
					postfijo.add(temp);
			}
			
			while(!pila.isEmpty())
				postfijo.add(pila.pop());
			
			//analizo la notaci�n postfija
			
			Double temp1, temp2;
			
			for(i=0;i<postfijo.size();i++){
				temp = postfijo.get(i);
				if(prioridad(temp)==0)
					pila.add(temp);
				else{
					temp2 = Double.parseDouble(pila.pop());
					temp1 = Double.parseDouble(pila.pop());
					
					if(temp.equals("+"))
						temp = ""+(temp1+temp2);
					else if(temp.equals("-"))
						temp = ""+(temp1-temp2);
					else if(temp.equals("*"))
						temp = ""+(temp1*temp2);
					else if(temp.equals("/"))
						temp = ""+(temp1/temp2);
					
					pila.add(temp);
				}
			}
			
			System.out.println(input);
			System.out.println(postfijo);
			System.out.println(pila.peek());
			
			return pila.pop();
		}
		catch(Exception e){
			return input;
		}
	}
	private static int prioridad(String temp) {
		if(temp.equals("+") || temp.equals("-"))
			return 1;
		if(temp.equals("*") || temp.equals("/"))
			return 2;
		return 0;
	}
}
