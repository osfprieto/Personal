package lightedPanels;

import java.util.Scanner;

public class LightedPanels {
	
	@SuppressWarnings("resource")
	public static void main(String[] args){
		System.out.println("hola");
		
		Scanner sc = new Scanner(System.in);
		int i, n;
		String[] board = new String[8];
		for(i=0;i<8;i++){
			board[i] = sc.nextLine();
		}
		n = LightedPanels.minTouch(board);
		System.out.println(n);
	}
	
	public static int minTouch(String[] board){
		@SuppressWarnings("unused")
		boolean[][] lBoard = new boolean[8][8];
		return 0;
	}
	
}
