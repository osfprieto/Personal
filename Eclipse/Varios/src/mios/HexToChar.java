package mios;

import java.util.Scanner;

public class HexToChar {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext("[A-F0-9a-f][A-F0-9a-f]"))
			sb.append((char)caracter(sc.next().toUpperCase()));
		System.out.println(sb.toString());
	}
	
	private static int caracter(String hex){
		int n = 0;
		char arr[] = hex.toCharArray();
		for(int i=0;i<arr.length;i++){
			n*=16;
			n+=valor(arr[i]);
		}
		return n;
	}
	
	private static int valor(char car){
		if(car-'A'<0)
			return car-'0';
		else
			return car-'A';
	}
	
}
