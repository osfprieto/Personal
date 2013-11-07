package topCoder;

import java.math.BigInteger;
import java.util.Scanner;

public class Module {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BigInteger bi;
		int n, cont=0, i;
		n = sc.nextInt();
		BigInteger k = new BigInteger(sc.next());
		for(i=0;i<n;i++){
			bi = new BigInteger(sc.next());
			if(bi.mod(k).intValue()==0)
				cont++;
		}
		System.out.println(cont);
	}
}
