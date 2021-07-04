package varios;

import java.util.Scanner;

public class Kruskal {
	
	private static long mat[][];
	private static String names[];
	private static int q[];//El conjunto al que pertenecen
	private static final long INF = Long.MAX_VALUE;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int k, i, j, a, b, r;
		for(k=0;k<T;k++){
			int n = sc.nextInt();
			mat = new long[n][n];
			names = new String[n];
			q = new int[n];
			for(i=0;i<n;i++)
				for(j=0;j<n;j++)
					mat[i][j] = INF;
			
			for(i=0;i<n;i++){
				q[i] = -1;
				names[i] = sc.next();
				r = sc.nextInt();
				for(j=0;j<r;j++){
					a = sc.nextInt();
					b = sc.nextInt();
					mat[i][a] = b;
					mat[a][i] = b;
				}
			}
			
			System.out.println(treeWeight(MST(mat)));
			
		}
		
	}
	//TODO: Completar los métodos para hallar el arbol de expanción mínima y el peso del mismo
	public static int treeWeight(long matriz[][]){
		
		return 0;
	}
	
	public static long[][] MST(long matriz[][]){
		mat = matriz;
		
		
		
		return matriz;
	}
	
}