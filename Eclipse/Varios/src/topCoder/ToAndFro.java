package topCoder;

import java.util.Scanner;

public class ToAndFro {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n, m, i, j, k;
		String cad, cout;
		m = sc.nextInt();
		while(m>0){
			cad = sc.next();
			
			n = cad.length()/m;
			
			char mat[][] = new char[n][m];
			k=0;
			for(i=0;i<n;i++){
				if(i%2 == 0){
					for(j=0;j<m;j++){
						mat[i][j] = cad.charAt(k++);
					}
				}
				else if (i%2 == 1){
					for(j=m-1;j>=0;j--){
						mat[i][j] = cad.charAt(k++);
					}
				}
			}
			
			cout = "";
			
			for(j=0;j<m;j++){
				for(i=0;i<n;i++){
					cout+=mat[i][j];
				}
			}
			System.out.println(cout);
			m = sc.nextInt();
		}
	}

}
