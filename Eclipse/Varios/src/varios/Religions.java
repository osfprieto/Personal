package varios;

import java.util.Scanner;

public class Religions {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		int n, m, bel1, bel2, last, i, j, temp, relCount, caso=1;
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		while(n!=0 && m!=0){
			int religiones[] = new int[n];
			
			for(i=0;i<n;i++)
				religiones[i]=0;
			
			last = 1;
			
			for(i=0;i<m;i++){
				bel1 = sc.nextInt()-1;
				bel2 = sc.nextInt()-1;
				
				if(religiones[bel1]==0 && religiones[bel2]==0){
					religiones[bel1]=last;
					religiones[bel2]=last;
					last++;
				}
				else if(religiones[bel1]>0 && religiones[bel2]>0){
					temp = religiones[bel2];
					for(j=0;j<n;j++){
						if(religiones[j]==temp){
							religiones[j]=religiones[bel1];
						}
					}
				}
				else if(religiones[bel1]>0){
					religiones[bel2]=religiones[bel1];
				}
				else if(religiones[bel2]>0){
					religiones[bel1]=religiones[bel2];
				}
			}
			
			relCount=0;
			
			for(i=0;i<n;i++){
				if(religiones[i]==0){
					relCount++;
				}
				else if(religiones[i]>=0){
					relCount++;
					temp = religiones[i];
					for(j=i;j<n;j++){
						if(religiones[j]==temp){
							religiones[j]=-1;
						}
					}
				}
			}
			
			System.out.println("Case "+(caso++)+": "+relCount);
			
			n = sc.nextInt();
			m = sc.nextInt();
		}
	}
	
	public class DisjointSet{//quedé perdido armando esto
		int[] parent;
		int[] rank;

		public DisjointSet(int n){
			parent = new int[n];
			rank = new int[n];
	
			for (int i = 0; i < n; i++){
				parent[i] = i;
				rank[i] = 0;
			}
		}
	
		void Union(int x, int y){
			int xRoot = Find(x);
			int yRoot = Find(y);
			
			if (xRoot == yRoot)
				return;
	
			// x and y are not already in same set. Merge them.
			if (rank[xRoot] < rank[yRoot]){
				parent[xRoot] = yRoot;
			}
			else if(rank[xRoot] > rank[yRoot]){
				parent[yRoot] = xRoot;
			}
			else{
				parent[yRoot] = xRoot;
				rank[xRoot]++;
			}
		}

		int Find(int x){
			if (parent[x] != x)
				parent[x] = Find(parent[x]);
			return parent[x];
		}
	}
	
}