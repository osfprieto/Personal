package varios;

import java.util.Scanner;

public class JailTrain {
	
	private static int INF = 2000;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		int n, grafo[][], i, j, k, m, a, b, time, padre[], dist[], min, time1, time2, camino, caminoPadre;
		boolean queue[];//is on queue
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		
		while(n>0){
			grafo = new int[n][n];
			padre = new int[n];
			dist = new int[n];
			queue = new boolean[n];
			
			for(i=0;i<n;i++){
				padre[i] = -1;
				dist[i] = INF;
				queue[i] = false;
				for(j=0;j<n;j++)
					grafo[i][j]=INF;
			}
			
			m = sc.nextInt();
			
			for(i=0;i<m;i++){
				a = sc.nextInt()-1;
				b = sc.nextInt()-1;
				time = sc.nextInt();
				grafo[a][b] = time;
				grafo[b][a] = time;
			}
			
			
			dist[0] = 0;
			queue[0] = true;
			while(!queueEmpty(queue)){
				min = hallarMin(queue, dist);
				queue[min] = false;
				for(i=0;i<n;i++){
					if(grafo[min][i]<INF){//existe el arco entre los nodos
						if(dist[i]>dist[min] + grafo[min][i]){
							dist[i] = dist[min] + grafo[min][i];
							padre[i] = min;
							queue[i] = true;
						}
					}
				}
			}
			
			time1 = dist[n-1];
			
			camino = n-1;
			caminoPadre = padre[camino];
			
			while(caminoPadre!=-1){
				grafo[caminoPadre][camino] = INF;
				grafo[camino][caminoPadre] = -grafo[camino][caminoPadre];//
				camino = caminoPadre;
				caminoPadre = padre[camino];
			}
			
			for(i=0;i<n;i++){
				padre[i] = -1;
				dist[i] = INF;
				queue[i] = false;
			}
			
			dist[0] = 0;
			
			for(k=0;k<n;k++){
				for(i=0;i<n;i++){
					for(j=0;j<n;j++){
						if(grafo[i][j]<INF){
							if(dist[j]>dist[i]+grafo[i][j]){
								dist[j] = dist[i]+grafo[i][j];
								padre[j] = dist[i];
							}
						}
					}
				}
			}
			
			time2 = dist[n-1];
			
			if(padre[n-1]==-1)
				System.out.println("Back to jail");
			else
				System.out.println(time1+time2);
			
			n = sc.nextInt();
		}
	}
	
	public static boolean queueEmpty(boolean[] queue){
		int i;
		boolean retorno = false;
		for(i=0;i<queue.length;i++)
			retorno = retorno || queue[i];
		return !retorno;
	}
	
	public static int hallarMin(boolean[] queue, int[] dist){
		int n = dist.length, i;
		int menor=0, distMenor=INF;
		for(i=0;i<n;i++)
			if(queue[i])
				if(dist[i]<distMenor){
					distMenor=dist[i];
					menor = i;
				}
		
		return menor;
	}
	
}
