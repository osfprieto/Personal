package topCoder;

import java.util.Scanner;

public class CookiePiles {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int N, D, A, count, temp;
		for(int i=0;i<n;i++){
			count = 0;
			N = sc.nextInt();
			A = sc.nextInt();
			D = sc.nextInt();
			temp = A;
			for(int j=1;j<=N;j++){
				count+=temp;
				temp+=D;
			}
			System.out.println(count);
		}
	}
}
