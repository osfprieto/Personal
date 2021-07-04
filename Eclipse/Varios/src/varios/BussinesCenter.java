package varios;

import java.util.Scanner;

public class BussinesCenter {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n, m, i, j, menor, up, down, actual;
		while(sc.hasNext()){
			n = sc.nextInt();
			m = sc.nextInt();
			menor = Integer.MAX_VALUE;
			for(i=0;i<m;i++){
				up = sc.nextInt();
				down = sc.nextInt();
				actual = 0;
				for(j=0;j<n;j++)
					if(actual>down)
						actual-=down;
					else
						actual+=up;
				if(actual<menor)
					menor=actual;
			}
			System.out.println(menor);
		}
		
	}

}
