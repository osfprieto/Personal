package topCoder;

import java.util.ArrayList;
import java.util.Scanner;

public class AVION {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ArrayList<String> arr = new ArrayList<String>();
		
		Scanner sc = new Scanner(System.in);
		int i, j=0;
		
		for(i=0;i<5;i++){
			arr.add(sc.nextLine());
		}
		
		for(i=0;i<5;i++){
			if(arr.get(i).contains("FBI"))
				j=i+1;
		}
		if(j==0)
			System.out.println("HE GOT AWAY!");
		else
			System.out.println(j);
	}

}
