package topCoder;

import java.util.Scanner;

public class Tron {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int i, j, ring=1, core=1;
		
		ring = sc.nextInt();
		core = sc.nextInt();
		
		while(ring > 0){
			
			for(i=0;i<ring;i++){
				for(j=ring;j>i+1;j--){
					System.out.print(' ');
				}
				for(j=0;j<=i;j++){
					System.out.print('/');
				}
				for(j=0;j<core;j++){
					System.out.print('-');
				}
				for(j=0;j<=i;j++){
					System.out.print('\\');
				}
				System.out.println();
			}
			
			for(i=0;i<core;i++){
				for(j=0;j<ring;j++){
					System.out.print('|');
				}
				for(j=0;j<core;j++){
					System.out.print(' ');
				}
				for(j=0;j<ring;j++){
					System.out.print('|');
				}
				System.out.println();
			}
			
			for(i=0;i<ring;i++){
				for(j=0;j<i;j++){
					System.out.print(' ');
				}
				for(j=ring;j>=i+1;j--){
					System.out.print('\\');
				}
				for(j=0;j<core;j++){
					System.out.print('-');
				}
				for(j=ring;j>=i+1;j--){
					System.out.print('/');
				}
				System.out.println();
			}
			
			System.out.println();
			
			ring = sc.nextInt();
			core = sc.nextInt();
		}
	}
}
