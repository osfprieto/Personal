package topCoder;

import java.util.Scanner;

public class TheExplicitFormula {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int data[] = new int[10];
		boolean bd[] = new boolean[10];
		for(int i=0;i<n;i++){
			for(int j=0;j<10;j++){
				data[j] = sc.nextInt();
			}
			for(int j=0;j<10;j++){
				if(data[j]==1)
					bd[j] = true;
				else
					bd[j] = false;
			}
			boolean res = f(bd[0], bd[1], bd[2], bd[3], bd[4], bd[5], bd[6], bd[7], bd[8], bd[9]);
			if (res)
				System.out.println(1);
			else
				System.out.println(0);
		}

	}

	
	static boolean f(boolean x1,boolean x2,boolean x3,boolean x4,boolean x5,boolean x6,boolean x7,boolean x8,boolean x9,boolean x10){
		if((x1 || x2)^(x1 || x3)^(x1 || x4)^(x1 || x5)^(x1 || x6)^
				(x1 || x7)^(x1 || x8)^(x1 || x9)^(x1 || x10)^(x2 || x3)^
				(x2 || x4)^(x2 || x5)^(x2 || x6)^(x2 || x7)^(x2 || x8)^(x2 || x9)^
				(x2 || x10)^(x3 || x4)^(x3 || x5)^(x3 || x6)^(x3 || x7)^(x3 || x8)^
				(x3 || x9)^(x3 || x10)^(x4 || x5)^(x4 || x6)^(x4 || x7)^(x4 || x8)^
				(x4 || x9)^(x4 || x10)^(x5 || x6)^(x5 || x7)^(x5 || x8)^(x5 || x9)^
				(x5 || x10)^(x6 || x7)^(x6 || x8)^(x6 || x9)^(x6 || x10)^(x7 || x8)^
				(x7 || x9)^(x7 || x10)^(x8 || x9) ^ (x8 || x10) ^ (x9 || x10) ^ 
				(x1 || x2 || x3) ^ (x1 || x2 || x4) ^ (x1 || x2 || x5) ^ 
				(x1 || x2 || x6) ^(x1 || x2 || x7) ^ (x1 || x2 || x8) ^ 
				(x1 || x2 || x9) ^ (x1 || x2 || x10) ^ (x1 || x3 || x4) ^ 
				(x1 || x3 || x5) ^(x1 || x3 || x6) ^ (x1 || x3 || x7) ^ 
				(x1 || x3 || x8) ^ (x1 || x3 || x9) ^ (x1 || x3 || x10) ^ 
				(x1 || x4 || x5) ^(x1 || x4 || x6) ^ (x1 || x4 || x7) ^ 
				(x1 || x4 || x8) ^ (x1 || x4 || x9) ^ (x1 || x4 || x10) ^ 
				(x1 || x5 || x6) ^(x1 || x5 || x7) ^ (x1 || x5 || x8) ^ 
				(x1 || x5 || x9) ^ (x1 || x5 || x10) ^ (x1 || x6 || x7) ^ 
				(x1 || x6 || x8) ^(x1 || x6 || x9) ^ (x1 || x6 || x10) ^ 
				(x1 || x7 || x8) ^ (x1 || x7 || x9) ^ (x1 || x7 || x10) ^ 
				(x1 || x8 || x9) ^(x1 || x8 || x10) ^ (x1 || x9 || x10) ^ 
				(x2 || x3 || x4) ^ (x2 || x3 || x5) ^ (x2 || x3 || x6) ^ 
				(x2 || x3 || x7) ^(x2 || x3 || x8) ^ (x2 || x3 || x9) ^ 
				(x2 || x3 || x10) ^ (x2 || x4 || x5) ^ (x2 || x4 || x6) ^ 
				(x2 || x4 || x7) ^(x2 || x4 || x8) ^ (x2 || x4 || x9) ^ 
				(x2 || x4 || x10) ^ (x2 || x5 || x6) ^ (x2 || x5 || x7) ^ 
				(x2 || x5 || x8) ^(x2 || x5 || x9) ^ (x2 || x5 || x10) ^ 
				(x2 || x6 || x7) ^ (x2 || x6 || x8) ^ (x2 || x6 || x9) ^ 
				(x2 || x6 || x10) ^(x2 || x7 || x8) ^ (x2 || x7 || x9) ^ 
				(x2 || x7 || x10) ^ (x2 || x8 || x9) ^ (x2 || x8 || x10) ^ 
				(x2 || x9 || x10) ^(x3 || x4 || x5) ^ (x3 || x4 || x6) ^ 
				(x3 || x4 || x7) ^ (x3 || x4 || x8) ^ (x3 || x4 || x9) ^ 
				(x3 || x4 || x10) ^(x3 || x5 || x6) ^ (x3 || x5 || x7) ^ 
				(x3 || x5 || x8) ^ (x3 || x5 || x9) ^ (x3 || x5 || x10) ^ 
				(x3 || x6 || x7) ^(x3 || x6 || x8) ^ (x3 || x6 || x9) ^ 
				(x3 || x6 || x10) ^ (x3 || x7 || x8) ^ (x3 || x7 || x9) ^ 
				(x3 || x7 || x10) ^(x3 || x8 || x9) ^ (x3 || x8 || x10) ^ 
				(x3 || x9 || x10) ^ (x4 || x5 || x6) ^ (x4 || x5 || x7) ^ 
				(x4 || x5 || x8) ^(x4 || x5 || x9) ^ (x4 || x5 || x10) ^ 
				(x4 || x6 || x7) ^ (x4 || x6 || x8) ^ (x4 || x6 || x9) ^ 
				(x4 || x6 || x10) ^(x4 || x7 || x8) ^ (x4 || x7 || x9) ^ 
				(x4 || x7 || x10) ^ (x4 || x8 || x9) ^ (x4 || x8 || x10) ^ 
				(x4 || x9 || x10) ^(x5 || x6 || x7) ^ (x5 || x6 || x8) ^ 
				(x5 || x6 || x9) ^ (x5 || x6 || x10) ^ (x5 || x7 || x8) ^ 
				(x5 || x7 || x9) ^(x5 || x7 || x10) ^ (x5 || x8 || x9) ^ 
				(x5 || x8 || x10) ^ (x5 || x9 || x10) ^ (x6 || x7 || x8) ^ 
				(x6 || x7 || x9) ^(x6 || x7 || x10) ^ (x6 || x8 || x9) ^ 
				(x6 || x8 || x10) ^ (x6 || x9 || x10) ^ (x7 || x8 || x9) ^ 
				(x7 || x8 || x10) ^(x7 || x9 || x10) ^ (x8 || x9 || x10))
			return true;
		return false;
	}
}
