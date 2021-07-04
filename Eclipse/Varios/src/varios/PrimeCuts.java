package varios;

import java.util.Scanner;

public class PrimeCuts {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Integer primes[] = bufferPrimes();
		int n, c, cont, i, ini;
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		c = sc.nextInt();
		
		while(n>=1 && n<=1000){
			cont=0;
			while(primes[cont]<=n){
				//System.out.println(primes[cont]);
				cont++;
			}
			//cont++;
			//System.out.println(cont);
			System.out.print(n+" "+c+":");
			//c--;
			if(cont%2==0){
				ini=cont/2-c;
				for(i=0;i<2*c;i++)
					if(i+ini>=0 && i<cont+ini)
						System.out.print(" "+primes[i+ini]);
			}
			else{
				ini=cont/2-c;
				for(i=0;i<2*c-1;i++)
					if(i+ini>=0 && i<cont+ini)
						System.out.print(" "+primes[i+ini]);
			}
			System.out.println("");
			System.out.println("");
			
			n = sc.nextInt();
			c = sc.nextInt();
		}
	}
	
	public static Integer[] bufferPrimes(){
		Integer primes[] = new Integer[169];
		int cont=0, i, j;
		boolean primo;
		for(i=1;i<1000;i++){
			primo=true;
			for(j=2;j<i && primo;j++)
				if(i%j==0)
					primo=false;
			if(primo)
				primes[cont++]=i;
		}
		//System.out.println(primes[3]);
		return primes;
	}
}
