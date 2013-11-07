package mios;

import java.util.Scanner;

public class Julka{
	@SuppressWarnings("resource")
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		int i, sum, extra, nat, kat, temp;
		
		for(i=0;i<10;i++){
			sum = sc.nextInt();
			extra = sc.nextInt();
			//Nat + Kat = sum
			//Kat = Nat + extra
			//sum = nat + nat + extra
			//sum - extra = 2nat
			//nat = (sum-extra)/2
			//kat = nat+extra
			temp = sum-extra;
			nat = temp/2;
			kat = nat+extra;
			System.out.println(kat);
			System.out.println(nat);
		}
	}
}
