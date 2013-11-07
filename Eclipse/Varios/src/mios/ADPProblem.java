package mios;

import java.util.ArrayList;
import java.util.Scanner;

public class ADPProblem {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		int k, i, res;
		
		for(k=0;k<T;k++){
			
			String input[] = sc.next().split("=");
			if(input[0].charAt(0)!='-')
				input[0] = '+'+input[0];
			if(input[1].charAt(0)!='-')
				input[1] = '+'+input[1];
			
			String temp;
			//lo que tenga x se va a la izquierda, lo que no, a la derecha, es cambiar el símbolo cuando sea necesario y quitamos las x de los ints
			temp = "";
			char arr0[] = input[0].toCharArray();
			ArrayList<String> tokens0 = new ArrayList<String>();
			
			for(i=0;i<arr0.length;i++){
				if(temp=="")
					temp+=""+arr0[i];
				else if(arr0[i]=='+' || arr0[i]=='-'){
					tokens0.add(temp);
					temp = ""+arr0[i];
				}
				else if(arr0[i] == '='){
					tokens0.add(temp);
					temp = "";
					tokens0.add("=");
				}
				else
					temp += ""+arr0[i];
			}
			tokens0.add(temp);
			
			temp="";
			char arr1[] = input[1].toCharArray();
			ArrayList<String> tokens1 = new ArrayList<String>();
			
			for(i=0;i<arr1.length;i++){
				if(temp=="")
					temp+=""+arr1[i];
				else if(arr1[i]=='+' || arr1[i]=='-'){
					tokens1.add(temp);
					temp = ""+arr1[i];
				}
				else if(arr1[i] == '='){
					tokens1.add(temp);
					temp = "";
					tokens1.add("=");
				}
				else
					temp += ""+arr1[i];
			}
			tokens1.add(temp);
			
			int cx=0, c=0;
			
			for(i=0;i<tokens0.size();i++){
				temp = tokens0.get(i);
				
				if(temp.charAt(0)=='+')
					temp = temp.substring(1);
				if(temp.equals("x"))
					temp = "1x";
				else if(temp.equals("-x"))
					temp = "-1x";
				if(temp.charAt(temp.length()-1)=='x')
					cx += Integer.parseInt(temp.substring(0, temp.length()-1));
				else
					c -= Integer.parseInt(temp);
			}
			for(i=0;i<tokens1.size();i++){
				temp = tokens1.get(i);
				if(temp.charAt(0)=='+')
					temp = temp.substring(1);
				if(temp.equals("x"))
					temp = "1x";
				else if(temp.equals("-x"))
					temp = "-1x";
				if(temp.charAt(temp.length()-1)=='x')
					cx -= Integer.parseInt(temp.substring(0, temp.length()-1));
				else
					c += Integer.parseInt(temp);
			}
			try{
				res = c/cx;
				System.out.println(res);
			}
			catch(Exception e){
				if(c==0 && cx==0)
					System.out.println("IDENTITY");
				else
					System.out.println("IMPOSSIBLE");
			}
			finally{
				System.out.println(c+" "+cx);
			}
		}
		
	}
	
}
