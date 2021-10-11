package misc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CountingSort {
	public static void main(String[] args) {
		try{
			int n, money, i;
			BufferedReader sc = new BufferedReader(new InputStreamReader(System.in)); 
			n = Integer.parseInt(sc.readLine().trim());
			StringTokenizer linea;
			while(n!=0){
				money = 0;
				
				int count[] = new int[n];
				int cost[] = new int[n];
				
				linea = new StringTokenizer(sc.readLine());
				for(i=0;i<n;i++)
					count[i] = Integer.parseInt(linea.nextToken());
				
				linea = new StringTokenizer(sc.readLine());
				for(i=0;i<n;i++)
					cost[i] = Integer.parseInt(linea.nextToken());
				
				sort(count);
				sort(cost);
				
				for(i=0;i<n;i++){
					money+=cost[i]*count[n-1-i];
					//System.out.println(count[i]+", "+cost[i]);
				}
				
				System.out.println(money);
				n = Integer.parseInt(sc.readLine().trim());
			}
		}
		catch(Exception e){
			
		}
	}

	public static void sort(int[] a){
		if(a.length==0)
			return;
		int max = a[0], min = a[0];
		for(int i=0;i<a.length;i++){
			if(a[i]>max)
				max = a[i];
			else if(a[i]<min)
				min = a[i];
		}
		int numValues = max-min+1;
		
		int counts[] = new int[numValues];
		for(int i=0;i<a.length;i++)
			counts[a[i]-min]++;
		int outPos = 0;
		for(int i=0;i<numValues;i++)
			for(int j=0;j<counts[i];j++)
				a[outPos++] = i+min;
	}
} 
