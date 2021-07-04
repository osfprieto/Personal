package varios;

import java.util.HashMap;
import java.util.Scanner;

public class SUDOIME {
	private static int mat[][] = new int[9][9];
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int k, i, j;
		
		boolean valido;
		for(k=1;k<=T;k++){
			for(i=0;i<9;i++)
				for(j=0;j<9;j++)
					mat[i][j] = sc.nextInt();
			valido = true;
			for(i=0;i<9 && valido;i++)
				valido = valido && validarHorizontal(i);
			
			for(j=0;j<9 && valido;j++)
				valido = valido && validarVertical(j);
			
			for(i=0;i<3 && valido;i++)
				for(j=0;j<3 && valido;j++)
					valido = valido && validarCuadro(i, j);
			
			System.out.println("Instancia "+k);
			if(valido)
				System.out.println("SI");
			else
				System.out.println("NAO");
			System.out.println();
		}
	}
	
	private static boolean validarHorizontal(int i){
		int j;
		HashMap<Integer, Boolean> h = new HashMap<Integer, Boolean>();
		for(j=0;j<9;j++)
			h.put(mat[i][j], true);
		return h.keySet().size()==9;
	}
	private static boolean validarVertical(int j){
		int i;
		HashMap<Integer, Boolean> h = new HashMap<Integer, Boolean>();
		for(i=0;i<9;i++)
			h.put(mat[i][j], true);
		return h.keySet().size()==9;
	}
	private static boolean validarCuadro(int i, int j){
		int ii, jj;
		HashMap<Integer, Boolean> h = new HashMap<Integer, Boolean>();
		for(ii=3*i;ii<3*i+3;ii++)
			for(jj=3*j;jj<3*j+3;jj++)
				h.put(mat[ii][jj], true);
		return h.keySet().size()==9;
	}
	
}
