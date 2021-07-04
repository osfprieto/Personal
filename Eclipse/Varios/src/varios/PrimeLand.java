package varios;

import java.util.Scanner;

public class PrimeLand {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Integer primes[] = bufferPrimes();
		Integer power[] = new Integer[3518];
		Scanner sc = new Scanner(System.in);
		String linea, nums[];
		
		linea = sc.nextLine();
		int temp, i;
		
		while(linea.charAt(0)!='0'){
			nums = linea.split(" ");
			temp=1;
			for(i=0;i<nums.length;i+=2)
				temp *= (Math.pow(Double.parseDouble(nums[i]), Double.parseDouble(nums[i+1])));;
			temp--;
			for(i=0;i<3518;i++)
				power[i]=0;
			while(temp>1){
				i=0;
				while(temp%primes[i]!=0)
					i++;
				power[i]++;
				temp/=primes[i];
			}
			boolean iniciado=false;
			for(i=3517;i>=0;i--){
				if(power[i]>0){
					System.out.print(((iniciado)?" ":"")+primes[i]+" "+power[i]);
					iniciado=true;
				}
			}
			System.out.println("");
			linea = sc.nextLine();
		}
	}
	
	public static Integer[] bufferPrimes(){
		Integer primes[] = new Integer[3518];
		int cont=0, i, j;
		boolean primo;
		for(i=2;i<32800;i++){
			primo=true;
			for(j=2;j<i && primo;j++)
				if(i%j==0)
					primo=false;
			if(primo)
				primes[cont++]=i;
		}
		return primes;
		
	}
	
}
