package topCoder;
//https://www.spoj.pl/problems/FCTRL2/
import java.math.BigInteger;
import java.util.Scanner;

public class SmallFactorials {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		int n, i, j, f;
		BigInteger fact = new BigInteger("1");
		BigInteger prod;
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		
		for(i=0;i<n;i++){
			fact = BigInteger.ONE;
			f = sc.nextInt();
			for(j=0;j<f;j++){
				prod = new BigInteger(""+(j+1));
				fact = fact.multiply(prod);
			}
			System.out.println(fact.toString());
		}
		
	}

}
