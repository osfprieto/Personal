package misc;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;

public class HappyNumbers {
	private static final BigInteger UNO = new BigInteger("1");
	private static final BigInteger CERO = new BigInteger("0");
	private static final BigInteger DIEZ = new BigInteger("10");
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//while(true)
		{
		BigInteger n = new BigInteger(sc.next());
		int i=0;
		boolean cycle=false;
		HashMap<BigInteger, Boolean> hash = new HashMap<BigInteger, Boolean>();
		while(!n.equals(UNO) && !cycle){
			if(hash.get(n)!=null)
				cycle = true;
			else{
				hash.put(n, true);
				BigInteger sum= new BigInteger("0");
				while(n.compareTo(CERO)>0){
					sum = sum.add(n.mod(DIEZ).multiply(n.mod(DIEZ)));
					n = n.divide(DIEZ);
					//System.out.println(sum+", "+n);
				}
				n=sum;
				//System.out.println("\t"+n);
			}
			i++;
		}
		
		if(cycle)
			System.out.println(-1);
		else
			System.out.println(i);
		}
	}
}
