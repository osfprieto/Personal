package topCoder;

import java.util.Scanner;

public class StoneGame {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int t, i, j, n;
		int ld;//lowest difference
		t = sc.nextInt();
		int turn;
		int cantMove;
		for(i=0;i<t;i++){
			n = sc.nextInt();
			cantMove = 0;
			int cols[] = new int[n];
			int dif[] = new int[n];
			
			for(j=0;j<n;j++){
				cols[j] = sc.nextInt();
			}
			for(j=0;j<n;j++){
				dif[j] = j+1-cols[j];
			}
			turn=1;
			while(cantMove<n){
				ld=0;
				
				for(j=0;j<n;j++){
					if(dif[j]<dif[ld]){
						ld = j;
					}
				}
				
				cols[ld]-=(ld+1);
				
				cantMove=0;
				for(j=0;j<n;j++){
					if(cols[j]<j+1){
						cantMove++;
					}
				}
				for(j=0;j<n;j++){
					dif[j] = j+1-cols[j];
				}
				turn++;
			}
			if(turn%2==0){
				System.out.println("ALICE");
			}
			else{
				System.out.println("BOB");
			}
		}
		
	}

}
