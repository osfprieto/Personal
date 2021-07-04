package varios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigInteger;
import java.util.HashMap;

public class FairAndSquare {
	
	private static final BigInteger UNO = new BigInteger("1");
	private static HashMap<BigInteger, Boolean> hash;
	
	public static void main(String[] args) {
		try{
			BufferedReader sc = new BufferedReader(new FileReader("C-large-1.in"));
			
			StringBuffer sb = new StringBuffer();
			
			int T = Integer.parseInt(sc.readLine().trim());
			
			int k;
			BigInteger a, b;
			
			hash = new HashMap<BigInteger, Boolean>();
			
			for(k=1;k<=T;k++){
				
				String line = sc.readLine();
				
				String ab[] = line.trim().split(" ");
				a = new BigInteger(ab[0].trim());
				b = new BigInteger(ab[1].trim());
				
				sb.append("Case #");
				sb.append(""+k);
				sb.append(": ");
				sb.append(""+countFairAndSquare(a, b));
				sb.append("\n");
				
			}
			
			sc.close();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter("C-large-1.out"));
			bw.write(sb.toString());
			bw.close();
		}catch(Exception e){
			
		}
	}
	
	private static int countFairAndSquare(BigInteger a, BigInteger b){
		int count = 0;

		
		for(BigInteger i = new BigInteger("1");i.multiply(i).compareTo(b)<=0;i = i.add(UNO))
			if(isTheSquareFairAndSquareAndValid(i, a, b))
				count++;
		
		return count;
	}
	
	private static boolean isTheSquareFairAndSquare(BigInteger x, BigInteger square){
		
		Boolean bool = hash.get(square);
		
		if(bool!=null)
			return bool;

		if(isPalindrome(x))
			if(isPalindrome(square)){
				hash.put(square, true);
				return true;
			}
		
		hash.put(square, false);
		return false;
		
	}
	
	private static boolean isTheSquareFairAndSquareAndValid(BigInteger x, BigInteger a, BigInteger b){
		
		BigInteger square = x.multiply(x);
		
		if(isTheSquareFairAndSquare(x, square) && square.compareTo(b)<=0 && square.compareTo(a)>=0)
			return true;
		else
			return false;
	}
	
	private static boolean isPalindrome(BigInteger x){
		String s = x.toString();
		int i;
		int l = s.length();
		char ss[] = s.toCharArray();
		for(i=0;i<l/2;i++)
			if(ss[i]!=ss[l-i-1])
				return false;
		
		return true;
	}
	
}
