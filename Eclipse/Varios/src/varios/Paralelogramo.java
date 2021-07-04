package varios;

import java.util.Scanner;

public class Paralelogramo {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		
		int n = sc.nextInt(), i;
		double x1, y1, x2, y2, x3, y3, x4, y4;
		double l1, l2, l3, l4;
		
		for(i=0;i<n;i++){
			x1 = sc.nextDouble();
			y1 = sc.nextDouble();
			x2 = sc.nextDouble();
			y2 = sc.nextDouble();
			x3 = sc.nextDouble();
			y3 = sc.nextDouble();
			x4 = sc.nextDouble();
			y4 = sc.nextDouble();
			
			l1 = Math.sqrt((x1-x3)*(x1-x3)+(y1-y3)*(y1-y3));//entre 1 y 3
			l2 = Math.sqrt((x2-x4)*(x2-x4)+(y2-y4)*(y2-y4));//entre 2 y 4
			
			l3 = Math.sqrt((x1-x4)*(x1-x4)+(y1-y4)*(y1-y4));//entre 1 y 4
			l4 = Math.sqrt((x2-x3)*(x2-x3)+(y2-y3)*(y2-y3));//entre 2 y 3
			
			if(iguales(l1, l2) && iguales (l3, l4))
				System.out.println("Yes");
			else
				System.out.println("No");
			
		}
	}
	
	public static boolean iguales(double l1, double l2){
		double error = 0.000001;
		if(abs(l1-l2)<error)
			return true;
		else
			return false;
	}
	public static double abs(double val){
		if(val<0)
			return -val;
		else
			return val;
	}
}
