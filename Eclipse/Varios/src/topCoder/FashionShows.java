package topCoder;

import java.util.Scanner;

public class FashionShows {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		int n, i, j, hm, hw, cont, total;
		boolean canDate;
		for(i=0;i<t;i++){
			n = sc.nextInt();
			
			int men[] = new int[n];
			int wom[] = new int[n];
			boolean bmen[] = new boolean[n];
			boolean bwom[] = new boolean[n];
			
			for(j=0;j<n;j++){
				men[j] = sc.nextInt();
				bmen[j] = true;
			}
			for(j=0;j<n;j++){
				wom[j] = sc.nextInt();
				bwom[j] = true;
			}
			total = 0;
			canDate = true;
			while(canDate){
				hm = 0;
				hw = 0;
				
				for(j=0;j<n;j++){
					if(men[j]>men[hm] && bmen[j])
						hm = j;
					if(wom[j]>wom[hm] && bwom[j])
						hw = j;
				}
				
				total+=(men[hm]*wom[hw]);
				bmen[hm] = false;
				bwom[hw] = false;
				
				cont=0;
				for(j=0;j<n;j++){
					if(!bmen[j])
						cont++;
				}
				if(cont==n)
					canDate = false;
			}
			
			System.out.println(total);
			
		}
		
	}

}
