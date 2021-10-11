package misc;

import java.util.HashMap;
import java.util.Scanner;

public class PasswordSearch {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n, i, j;
		String cadena, subcadena="";
		HashMap<String, Integer> cadenas;
		Integer times;
		Object[] setCadenas;
		while(sc.hasNext()){
			cadenas = new HashMap<String, Integer>();
			n = sc.nextInt();
			cadena = sc.next();
			for(i=0;i<=cadena.length()-n;i++){
				subcadena = cadena.substring(i, i+n);
				times = cadenas.get(subcadena);
				if(times==null)
					cadenas.put(subcadena, 1);
				else
					cadenas.put(subcadena, times+1);
			}
			j=0;
			setCadenas = cadenas.keySet().toArray();
			for(i=0;i<setCadenas.length;i++){
				if(j<cadenas.get((String)setCadenas[i])){
					j=cadenas.get((String)setCadenas[i]);
					subcadena = (String)setCadenas[i];
				}
					
			}
			System.out.println(subcadena);
		}
		
	}
}
