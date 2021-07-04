package varios;

import java.util.regex.*;

public class RegEx {
	public static void main(String[] args) {
		testPattern();
	}
	
	public static void testPattern(){
		Pattern patron = Pattern.compile("a*b");
		Matcher encaja = patron.matcher("aabmanoloaabmanoloabmanolob");
		String resultado = encaja.replaceAll("\t");
		System.out.println(resultado);
	}
	
}
