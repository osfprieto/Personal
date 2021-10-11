package misc;

import java.util.Scanner;

public class StirlongModTwo {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int i, j;
		
		//for(i=0;i<n;i++)
			//System.out.println(stirling(sc.nextLong()%2, sc.nextLong()%2));
		StringBuilder out = new StringBuilder();
		for(i=0;i<n;i++){
			for(j=0;j<n;j++)
				out = out.append(stirling(i, j)+""+stirlingCompleto(i, j)%2+" ");
			out = out.append("\n");
		}
		System.out.println(out);
	}
	
	private static long stirling(long n, long m) {
		if (m>n)
			return 0;
		if(n==0 && m==0)
			return 1;
		if(n==0 || m==0)
			return 0;
		if(n==m)
			return 1;
		if(m%2==1)
			return (stirling(n-1, m)+stirling(n-1, m-1))%2;
		else
			return stirling(n-1, m-1)%2;
	}
	private static long stirlingCompleto(long n, long m) {
		if (m>n)
			return 0;
		if(n==0 && m==0)
			return 1;
		if(n==0 || m==0)
			return 0;
		/*if(n==m)
			return 1;*/
		return m*stirling(n-1, m)+stirling(n-1, m-1);
	}
}
