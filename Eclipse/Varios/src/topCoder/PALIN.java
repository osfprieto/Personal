package topCoder;

import java.math.BigInteger;
import java.util.Scanner;

public class PALIN {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for(int i=0;i<n;i++){
			BigInteger bi = new BigInteger(sc.next());
			bi = bi.add(new BigInteger("1"));
			boolean found = false;
			String biString = bi.toString();
			String biStringReverse = "";
			for(int j=biString.length();j>0;j--){
				biStringReverse+=biString.charAt(j-1);
			}
			if(biString.equals(biStringReverse)){
				found = true;
			}
			while(!found){
				bi = bi.add(new BigInteger("1"));
				biString = bi.toString();
				biStringReverse = "";
				for(int j=biString.length();j>0;j--){
					biStringReverse+=biString.charAt(j-1);
				}
				if(biString.equals(biStringReverse)){
					found = true;
				}
			}
			System.out.println(bi.toString());
		}
	}
}
