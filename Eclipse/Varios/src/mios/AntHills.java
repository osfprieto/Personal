package mios;

import java.math.BigInteger;
import java.util.Scanner;
//time limit
//http://livearchive.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=2806
public class AntHills {
	
	public static final BigInteger INF = new BigInteger("1000000000000000");
	@SuppressWarnings("resource")
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
	    int n = sc.nextInt();
	    while(n>0){
	        int i, j, k;
	        String temp;
	        BigInteger dist[][] = new BigInteger[n][n];
	        BigInteger buff[][] = new BigInteger[n][n];
	        
	        for(i=0;i<n;i++)
	            for(j=0;j<n;j++)
	                if(i==j)
	                    dist[i][j] = BigInteger.ZERO;
	                else
	                    dist[i][j] = INF;
	        
	        for(i=1;i<n;i++){
	            j = sc.nextInt();
	            temp = sc.next();
	            dist[i][j] = new BigInteger(temp);
	            dist[j][i] = new BigInteger(temp);
	        }
	        
	        for(k=0;k<n;k++){
	            for(i=0;i<n;i++)
	                for(j=0;j<n;j++)
	                    buff[i][j] = dist[i][j];
	            
	            for(i=0;i<n;i++)
	                for(j=0;j<n;j++)
	                    dist[i][j] = (buff[i][j].compareTo(buff[i][k].add(buff[k][j]))<0)?buff[i][j]:buff[i][k].add(buff[k][j]);
	            
	        }
	        
	        n = sc.nextInt();
	        
	        for(k=0;k<n;k++){
	            i = sc.nextInt();
	            j = sc.nextInt();
	            System.out.print(dist[i][j]+((k<n-1)?" ":""));
	        }
	        System.out.println();
	        n = sc.nextInt();
	    }
	}
}
