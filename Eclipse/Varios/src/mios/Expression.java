package mios;

//http://www.spoj.pl/problems/GUESSNM2/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Expression {
	
	private static final int ORDENES_5[] = {1234,1243,1324,1342,1423,1432,2134,2143,2314,2341,2413,2431,3124,3142,3214,3241,3412,3421,4123,4132,4213,4231,4312,4321,10234,10243,10324,10342,10423,10432,12034,12043,12304,12340,12403,12430,13024,13042,13204,13240,13402,13420,14023,14032,14203,14230,14302,14320,20134,20143,20314,20341,20413,20431,21034,21043,21304,21340,21403,21430,23014,23041,23104,23140,23401,23410,24013,24031,24103,24130,24301,24310,30124,30142,30214,30241,30412,30421,31024,31042,31204,31240,31402,31420,32014,32041,32104,32140,32401,32410,34012,34021,34102,34120,34201,34210,40123,40132,40213,40231,40312,40321,41023,41032,41203,41230,41302,41320,42013,42031,42103,42130,42301,42310,43012,43021,43102,43120,43201,43210};
	private static final int ORDENES_4[] = {123,132,213,231,312,321,1023,1032,1203,1230,1302,1320,2013,2031,2103,2130,2301,2310,3012,3021,3102,3120,3201,3210};
	private static final int ORDENES_3[] = {12,21,102,120,201,210};
	private static final int ORDENES_2[] = {1, 10};
	private static final int ORDENES_1[] = {0};
	
	public static void main(String[] args) {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		int i, limite=0, cont, temp, ORDENES[] = new int[1];
		try{
			String expresion;
			ArrayList<String> notacionPosfija;
			StringTokenizer values = new StringTokenizer(sc.readLine());
			int n = Integer.parseInt(values.nextToken());
			String value[] = new String[n];
			for(i=0;i<n;i++)
				value[i] = values.nextToken();
			int m = Integer.parseInt(values.nextToken());
			
			while(n!=0 || m!=0){
				
				expresion = sc.readLine(); 
				
				notacionPosfija = pasarAPostfijo(expresion);
				
				if(n==5){
					ORDENES=ORDENES_5;
					limite = 120;
				}
				else if(n==4){
					ORDENES=ORDENES_4;
					limite = 24;
				}
				else if(n==3){
					ORDENES=ORDENES_3;
					limite = 6;
				}
				else if(n==2){
					ORDENES=ORDENES_2;
					limite = 2;
				}
				else if(n==1){
					ORDENES=ORDENES_1;
					limite = 1;
				}
				
				cont = 0;
				int indices[] = new int[n];
				for(i=0;i<n;i++)
					indices[i] = notacionPosfija.indexOf(""+((char)(i+'a')));
				
				temp = ORDENES[0];
				
				for(i=0;i<n;i++){
					notacionPosfija.set(indices[temp%10], value[i]);
					temp/=10;
				}
				while(analizarFormula(notacionPosfija)!=m && cont<limite){
					
					temp = ORDENES[cont++];
					
					for(i=0;i<n;i++){
						notacionPosfija.set(indices[temp%10], value[i]);
						temp/=10;
					}
				}
				
				if(cont==limite)
					System.out.println("NO");
				else
					System.out.println("YES");
				
				values = new StringTokenizer(sc.readLine());
				n = Integer.parseInt(values.nextToken());
				value = new String[n];
				for(i=0;i<n;i++)
					value[i] = values.nextToken();
				m = Integer.parseInt(values.nextToken());
			}
		}
		catch(Exception e){
			
		}
	}
	
	public static int analizarFormula(ArrayList<String> postfijo){
		
		if(postfijo.size()==1)
			return Integer.parseInt(postfijo.get(0));
		
		int temp1, temp2;
		String temp;
		int i;
		Stack<String> pila = new Stack<String>();
		
		for(i=0;i<postfijo.size();i++){
			temp = postfijo.get(i);
			if(prioridad(temp)==0)
				pila.add(temp);
			else{
				temp2 = Integer.parseInt(pila.pop());
				temp1 = Integer.parseInt(pila.pop());
				
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
		
		return Integer.parseInt(pila.pop());
	}
	
	public static ArrayList<String> pasarAPostfijo(String input){
		
		input = "("+input+")";
		ArrayList<String> tokens = new ArrayList<String>();
		int i;
		String temp="";
		char c;
		int cont=0;
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
				if(c>='a'&&c<='z'){
					c = (char)('a' + cont++);
				}
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
		
		return postfijo;
	}
	
	private static int prioridad(String temp){
		if(temp.equals("+") || temp.equals("-"))
			return 1;
		if(temp.equals("*") || temp.equals("/"))
			return 2;
		return 0;
	}
	
}
