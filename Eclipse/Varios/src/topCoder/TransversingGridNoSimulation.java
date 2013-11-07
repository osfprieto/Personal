package topCoder;

import java.util.Scanner;

public class TransversingGridNoSimulation {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		int t, i, n, m;
		Scanner sc = new Scanner(System.in);
		
		t = sc.nextInt();
		
		for(i=0;i<t;i++){
			n = sc.nextInt();
			m = sc.nextInt();
			
			if(n==m){
				if (n%2==1)
					System.out.println('R');
				else
					System.out.println('L');
			}
			else if(n>m){
				if(n%2==1)
					System.out.println('D');
				else
					System.out.println('U');
			}
			else if(m>n){
				if(m%2==1)
					System.out.println('R');
				else
					System.out.println('L');
			}
					
		}
		
	}

}
