package topCoder;

import java.util.Scanner;

public class PrimePath {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int cont, from, to;
		boolean impo;
		for(int i=0;i<n;i++){
			from = sc.nextInt();
			to = sc.nextInt();
			cont = 0;
			impo = false;
			System.out.println(1);
			while(from<to && !impo){
				System.out.println(2);
				impo = true;
				for(int j=1;j<10;j++){
					if(primo(from+i)){
						cont++;
						from = from+i;
						impo = false;
						System.out.println(3);
						System.out.println(from);
						System.out.println(to);
					}
				}
				for(int j=1;j<10;j++){
					if(primo(from+10*i)){
						cont++;
						from= from+10*i;
						impo = false;
						System.out.println(4);
						System.out.println(from);
						System.out.println(to);
					}
				}
				for(int j=1;j<10;j++){
					if(primo(from+100*i)){
						cont++;
						from=from+100*i;
						impo = false;
						System.out.println(5);
						System.out.println(from);
						System.out.println(to);
					}
				}
				for(int j=1;j<10;j++){
					if(primo(from+1000*i)){
						cont++;
						from = from+1000*i;
						impo = false;
						System.out.println(6);
						System.out.println(from);
						System.out.println(to);
					}
				}
			}
			
			if(impo || from>to)
				System.out.println("Impossible");
			else
				System.out.println(cont);
			
		}
		
	}
	public static boolean primo(int n){
		for(int i=2;i<n;i++){
			if(n%i==0)
				return false;
		}
		return true;
	}

}
