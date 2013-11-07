package topCoder;

import java.util.Scanner;

public class FamilyProblems {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		long n = sc.nextInt();
		char term;
		
		while(n>0){
			
			term = (char)((-1/2)+Math.sqrt(((double)(1/4))+((double)(2*n))));
			System.out.println("TERM "+n+" is "+(char)(term%26+'A'-1));
			
			n = sc.nextInt();
		}
		
	}

}
