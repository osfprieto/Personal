package topCoder;

import java.util.*;

public class RPN {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n, i, j;
		char cIn;
		String cadIn, cadOut;
		n = sc.nextInt();
		Stack<Character> stackOpera = new Stack<Character>();
		for(i=0;i<n;i++){
			cadOut = "";
			cadIn = sc.next();
			for(j=0;j<cadIn.length();j++){
				cIn = cadIn.charAt(j);
				if(cIn == ')'){
					cadOut+=stackOpera.pop();
				}
				else if(cIn=='+' || cIn=='-' || cIn=='*' || cIn=='/' || cIn=='^')
					stackOpera.push(cIn);
				else if((cIn>='a' && cIn<='z')||(cIn>='A' && cIn<='Z'))
					cadOut+=cIn;
			}
			System.out.println(cadOut);
		}
	}

}
