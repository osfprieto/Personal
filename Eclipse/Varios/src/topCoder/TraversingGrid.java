package topCoder;

import java.util.Scanner;

public class TraversingGrid {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int canMove;
		int x, y, dir, cont;
		int t = sc.nextInt();
		int m, n;
		int i, j, k;
		//dir = 1 => derecha
		//dir = 2 => abajo
		//dir = 3 => izquierda
		//dir = 4 => arriba
		for(k=0;k<t;k++){
			canMove = 1;
			x = 0;
			y = 0;
			dir = 1;
			n = sc.nextInt();
			m = sc.nextInt();
			
			boolean grid[][] = new boolean[n][m];
			
			for(i=0;i<n;i++){
				for(j=0;j<m;j++){
					grid[i][j] = true;
				}
			}
			cont = 0;
			while(canMove==1){
				grid[x][y] = false;
				if (dir==1){
					if(y<m-1 && grid[x][y+1]){
						y++;
						cont=0;
					}
					else{
						dir%=4;
						dir++;
						cont++;
					}
				}
				else if(dir==2){
					if(x<n-1 && grid[x+1][y]){
						x++;
						cont=0;
					}
					else{
						dir%=4;
						dir++;
						cont++;
					}
				}
				else if(dir==3){
					if(y>0 && grid[x][y-1]){
						y--;
						cont=0;
					}
					else{
						dir%=4;
						dir++;
						cont++;
					}
				}
				else if(dir==4){
					if(x>0 && grid[x-1][y]){
						x--;
						cont=0;
					}
					else{
						dir%=4;
						dir++;
						cont++;
					}
				}
				if(cont==4){
					canMove = 0;
				}
			}
			if(dir==4)
				System.out.println("U");
			else if(dir==3)
				System.out.println("L");
			else if(dir==2)
				System.out.println("D");
			else if(dir==1)
				System.out.println("R");
		}	
	}
}