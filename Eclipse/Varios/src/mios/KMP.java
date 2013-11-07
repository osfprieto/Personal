package mios;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class KMP {

	private static HashMap<String, int[]> tablas = new HashMap<String, int[]>();
	
	public static void main(String[] args) {
		
		/*System.out.println(search("hola mundo", "mun"));
		System.out.println(search("hola mundo", "rico"));*/
		
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		
		/*while(sc.hasNext())
			System.out.println(search(sc.next(), sc.next()));*/
		try{
			int T = Integer.parseInt(sc.readLine().trim());
			int k, q, i;
			String t, p;
			StringBuilder sb = new StringBuilder();
			for(k=0;k<T;k++){
				t = sc.readLine().trim();
				q = Integer.parseInt(sc.readLine().trim());
				for(i=0;i<q;i++){
					p = sc.readLine().trim();
					if(search(t, p)==-1)
						sb.append("n\n");
					else
						sb.append("y\n");
				}
			}
			System.out.println(sb.toString());
		}
		catch(Exception e){
			System.out.println("Exception");
		}
	}
	
	public static int search(String T, String P){
		int i=0;
		int q=0;
		int tabla[] = tablas.get(P);
		if(tabla==null){
			tabla = table(P);
			tablas.put(P, tabla);
			//System.out.println("agregó tabla");
		}
		char t[] = T.toCharArray();
		char p[] = P.toCharArray();
		
		int lenT = t.length;
		int lenP = p.length;
		
		while(i<lenT)
			if(p[q]==t[i]){
				q++;
				i++;
				if(q==lenP)
					return i-q;
			}
			else if(q==0)
				i++;
			else
				q = tabla[q];
		
		return -1;
	}
	
	private static int[] table(String palabra){
		char p[] = palabra.toCharArray();
		int len = p.length;
		
		int pos = 2;
		int cnd = 0;
		int tabla[] = new int[len];
		
		tabla[0] = -1;
		tabla[1] = 0;
		while(pos<len)
			if(p[pos-1]==p[cnd])
				tabla[pos++] = cnd++;
			else if(cnd>0)
				cnd = tabla[cnd];
			else
				tabla[pos++] = 0;
				
		return tabla;
	}

}