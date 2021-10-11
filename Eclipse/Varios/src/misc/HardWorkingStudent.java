package misc;

import java.util.Scanner;

public class HardWorkingStudent {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int lim = 100000, i;
		int arr[] = new int[lim];
		int index0, index1, indexActual;
		String order;
		char orders[];
		char temp;
		StringBuilder out;
		while(sc.hasNext()){
			for(i=0;i<lim;i++)
				arr[i] = i;
			
			index0 = sc.nextInt();
			order = sc.next();
			index1 = sc.nextInt();
			
			orders = order.toCharArray();
			
			indexActual = index1;
			out = new StringBuilder();
			for(i=orders.length-1;i>=0;i--){
				temp = orders[i];
				if(temp=='f')
					indexActual = f(indexActual);
				else if(temp=='b')
					indexActual = b(indexActual);
				else if(temp=='k')
					out.append(arr[indexActual]);
				else if(temp=='<')
					arr[index0] = arr[indexActual];
				else if(temp=='=')
					out.append(((arr[index0]==arr[indexActual])?'=':'#'));
				
			}
			System.out.println(out.toString());
		}
		
	}
	
	public static int f(int n){
		if(n%2==0)
			return n+3;
		return n+1;
	}
	public static int b(int n){
		return n-2;
	}
	
}
