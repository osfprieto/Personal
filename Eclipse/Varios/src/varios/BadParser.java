package varios;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class BadParser {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNext()){
			String input = sc.nextLine().trim();
			if(!input.equals("")){
				String arr[] = input.split(",");
				int i;
				for(i=0;i<arr.length;i++)
					arr[i] = arr[i].trim();
				String original = getOriginal(arr);
				System.out.println(analizar(original));
			}
		}
		
	}

	public static String getOriginal(String[] arr){
		return scan(0, arr);
	}
	
	public static String scan(int position, String[] cadenas){
		String arr[] = cadenas[position].split(" ");
		if(arr.length==1)
			return arr[0].trim();
		else
			return scan(Integer.parseInt(arr[1].trim()), cadenas)
			+arr[0].trim()+scan(Integer.parseInt(arr[2].trim()), cadenas);
	}
	
	public static String analizar(String input){
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
		
		//paso a notación postfija
		
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
		
		//analizo la notación postfija
		
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
	
	private static int prioridad(String temp) {
		if(temp.equals("+") || temp.equals("-"))
			return 1;
		if(temp.equals("*") || temp.equals("/"))
			return 2;
		return 0;
	}
	
}
