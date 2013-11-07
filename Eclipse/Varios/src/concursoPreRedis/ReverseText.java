package concursoPreRedis;

import java.util.ArrayList;
import java.util.Scanner;

public class ReverseText {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		int n, i, j;
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		
		ArrayList<String> arr = new ArrayList<String>();
		
		for(i=0;i<=n;i++){
			arr.add(sc.nextLine());
		}
		
		for(i=0;i<=n;i++){
			for(j=arr.get(i).length()-1;j>=0;j--){
				System.out.print(arr.get(i).charAt(j));
			}
			System.out.println("");
		}
		
	}

}
