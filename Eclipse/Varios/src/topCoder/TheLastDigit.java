package topCoder;

import java.math.BigInteger;
import java.util.Scanner;

public class TheLastDigit {
	public static final BigInteger bg10= new BigInteger("10");
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for(int i=0;i<n;i++){
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c;
			if (b==0)
				c = 1;
			else{
				a%=10;
				b%=10;
				if(b==0 || a==1){
					c=1;
				}
				else{
					c = (int)Math.pow((double) a,(double) b);
					c%=10;
				}
			}
			System.out.println(c);
		}
	}
}