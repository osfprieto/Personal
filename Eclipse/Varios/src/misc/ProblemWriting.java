package misc;

import java.util.Scanner;

public class ProblemWriting {
	
	private static final String ERROR_LENGTH = "dotForm must contain between 1 and 25 characters, inclusive.";
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ProblemWriting obj = new ProblemWriting();
		while(sc.hasNext()){
			System.out.println(obj.myCheckData(sc.nextLine()));
		}
		
	}
	
	public String myCheckData(String input){
		int len = input.length(), i;
		if(len<1 || len>25)
			return ERROR_LENGTH;
		
		char ini = input.charAt(0);
		char fin = input.charAt(len-1);
		if(ini<'0' || ini>'9')
			return "dotForm is not in dot notation, check character 0.";
		if(fin<'0' || fin>'9')
			return "dotForm is not in dot notation, check character "+len+".";
		
		//remover puntos y pasar de n�mero a operador y visceversa
		
		char arr[] = input.toCharArray();
		int caso=0;
		for(i=0;i<len;i++){
			while(arr[i]=='.')
				i++;
			if(caso==0){//leyendo n�mero
				if(arr[i]>='0' && arr[i]<='9')
					caso=1;
				else
					return "dotForm is not in dot notation, check character "+i+".";
			}
			else if(caso==1){//leyendo operadores
				if(arr[i]=='*' || arr[i]=='+' || arr[i]=='-' || arr[i]=='/')
					caso=0;
				else
					return "dotForm is not in dot notation, check character "+i+".";
			}
		}
		
		return "";
	}
	
}
