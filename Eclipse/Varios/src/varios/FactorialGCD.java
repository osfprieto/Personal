package varios;

import java.util.Scanner;

public class FactorialGCD {
	/*
	 * recibe a y b, retorna gcd(a!, b)*/
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		long a, b;
		
		while(sc.hasNext()){
			a = Long.parseLong(sc.next());
			b = Long.parseLong(sc.next());
			
			if(a>13)
				System.out.println(b);
			else 
				System.out.println(gcd(fact(a), b));
			
		}
		
	}

	public static long gcd(long a, long b){
		if (b==0)
			return a;
		else
			return gcd(b, a%b);
	}
	
	public static long fact(long a){
		if(a==0)
			return 1;
		else
			return a*fact(a-1);
	}
	
}
