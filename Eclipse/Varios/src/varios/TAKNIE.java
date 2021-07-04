package varios;

import java.util.HashMap;
import java.util.Scanner;

public class TAKNIE {
	@SuppressWarnings("resource")
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNext())
		{
			Integer n = sc.nextInt();
			
			HashMap<Integer, Boolean> hash = new HashMap<Integer, Boolean>();
			boolean cycle = false;
			hash.put(n, true);
			while(n>1 && !cycle){
				if(n%2==0)
					n/=2;
				else
					n = 3*n+3;
				if(hash.get(n)!=null)
					cycle=true;
				else
					hash.put(n, true);
				System.out.println(n);
			}
			
			if(!cycle)
				System.out.println("TAK");
			else
				System.out.println("NIE");
		}
	}
}
