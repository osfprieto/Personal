package topCoder;

import java.math.BigInteger;
import java.util.Scanner;

public class BigFactorials {
	
	private static final BigInteger UNO = new BigInteger("1");
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		int times=1;
		BigInteger fact;
		Scanner sc = new Scanner(System.in);
		while(times>0){
			fact = UNO;
			times = sc.nextInt();
			for(int i=1;i<=times;i++){
				fact = fact.multiply(new BigInteger(""+i));
			}
			if (times > 0){
				System.out.println(""+times+"!");
				System.out.println(fact.toString());
			}
		}
	}

}