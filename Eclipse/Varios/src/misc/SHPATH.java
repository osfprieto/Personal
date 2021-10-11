package misc;

import java.util.Scanner;

public class SHPATH {
	
	private static long mat[][];
	private static long grafo[][];
	private static String names[];
	private static boolean q[];
	private static final long INF = Long.MAX_VALUE;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int k, i, j, a, b, r, m, indexMin;
		long alt;
		String from, to;
		int indexFrom, indexTo;
		for(k=0;k<T;k++){
			int n = sc.nextInt();
			mat = new long[n][n];
			grafo = new long[n][n];
			names = new String[n];
			q = new boolean[n];
			for(i=0;i<n;i++)
				for(j=0;j<n;j++)
					if(i==j)
						mat[i][j] = 0;
					else
						mat[i][j] = INF;
			
			for(i=0;i<n;i++){
				names[i] = sc.next();
				r = sc.nextInt();
				for(j=0;j<r;j++){
					a = sc.nextInt()-1;
					b = sc.nextInt();
					mat[i][a] = b;
					mat[a][i] = b;
				}
			}
			
			for(i=0;i<n;i++)
				for(j=0;j<n;j++)
					grafo[i][j] = mat[i][j];
			
			m = sc.nextInt();
			for(i=0;i<m;i++){
				from = sc.next();
				to = sc.next();
				indexFrom = -1;
				indexTo = -1;
				for(j=0;j<n && indexFrom==-1;j++)
					if(names[j].equals(from))
						indexFrom = j;
				for(j=0;j<n && indexTo==-1;j++)
					if(names[j].equals(to))
						indexTo = j;
				
				for(j=0;j<n;j++)
					q[j] = true;
				while(!isEmpty()){
					indexMin = getMin(i);
					q[indexMin] = false;
					for(j=0;j<n;j++)
						if(grafo[indexMin][j]<INF && grafo[indexMin][j]>0){
							alt = mat[indexFrom][indexMin]+grafo[indexMin][j];
							if(alt<mat[indexFrom][j])
								mat[indexFrom][j] = alt;
						}
					//System.out.println(isEmpty()?"true":"false");
				}
				
				System.out.println(mat[indexFrom][indexTo]);
			}
		}
		
	}
	
	private static boolean isEmpty(){
		boolean ret = false;
		for(int i=0;i<q.length;i++)
			ret = ret || q[i];
		return !ret;
	}
	
	private static int getMin(int i){
		long min = Long.MAX_VALUE;
		int ret = 0;
		int j;
		for(j=0;j<q.length;j++)
			if(q[j] && mat[i][j]<min){
				ret = j;
				min = mat[i][j];
			}
		return ret;
	}
	
}

// La de arriba pasa por tiempo en el problema
// La de abajo resuelve para todos los casos, no los que se piden en el problema no m�s

/*package varios;

import java.util.Scanner;

public class SHPATH {
	
	private static long mat[][];
	private static long grafo[][];
	private static String names[];
	private static boolean q[];
	private static final long INF = Long.MAX_VALUE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int k, i, j, a, b, r, m;
		long alt;
		String from, to;
		int indexFrom, indexTo;
		for(k=0;k<T;k++){
			int n = sc.nextInt();
			mat = new long[n][n];
			grafo = new long[n][n];
			names = new String[n];
			q = new boolean[n];
			for(i=0;i<n;i++)
				for(j=0;j<n;j++)
					if(i==j)
						mat[i][j] = 0;
					else
						mat[i][j] = INF;
			
			for(i=0;i<n;i++){
				names[i] = sc.next();
				r = sc.nextInt();
				for(j=0;j<r;j++){
					a = sc.nextInt()-1;
					b = sc.nextInt();
					mat[i][a] = b;
					mat[a][i] = b;
				}
			}
			
			for(i=0;i<n;i++)
				for(j=0;j<n;j++)
					grafo[i][j] = mat[i][j];
			
			int indexMin;
			for(i=0;i<n;i++){
				for(j=0;j<n;j++)
					q[j] = true;
				while(!isEmpty()){
					indexMin = getMin(i);
					q[indexMin] = false;
					for(j=0;j<n;j++)
						if(grafo[indexMin][j]<INF && grafo[indexMin][j]>0){
							alt = mat[i][indexMin]+grafo[indexMin][j];
							if(alt<mat[i][j])
								mat[i][j] = alt;
						}
					//System.out.println(isEmpty()?"true":"false");
				}
			}
			m = sc.nextInt();
			for(i=0;i<m;i++){
				from = sc.next();
				to = sc.next();
				indexFrom = -1;
				indexTo = -1;
				for(j=0;j<n && indexFrom==-1;j++)
					if(names[j].equals(from))
						indexFrom = j;
				for(j=0;j<n && indexTo==-1;j++)
					if(names[j].equals(to))
						indexTo = j;
				System.out.println(mat[indexFrom][indexTo]);
			}
		}
		
	}
	
	private static boolean isEmpty(){
		boolean ret = false;
		for(int i=0;i<q.length;i++)
			ret = ret || q[i];
		return !ret;
	}
	
	private static int getMin(int i){
		long min = Long.MAX_VALUE;
		int ret = 0;
		int j;
		for(j=0;j<q.length;j++)
			if(q[j] && mat[i][j]<min){
				ret = j;
				min = mat[i][j];
			}
		return ret;
	}
	
}
*/
