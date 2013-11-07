package mios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class N_Gram {
	
	static ArrayList<String> llaves;
	static HashMap<String, Integer> dicc;

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNext()){
			String linea = sc.nextLine();
			int T = Integer.parseInt(sc.nextLine().trim());
			int k, i;
			
			for(k=0;k<T;k++){
				int subLen = Integer.parseInt(sc.nextLine().trim());
				int len = linea.length();
				
				dicc = new HashMap<String, Integer>();
				llaves = new ArrayList<String>();
				
				for(i=0;i<=len-subLen;i++)
					put(linea.substring(i, i+subLen));
				
				//hallar moda
				
				String key = getLlaveModa();
				int count = dicc.get(key);
				
				System.out.println(count+"\t"+key);
			}	
		}
	}
		
	public static Integer put(String key){
		if(!llaves.contains(key)){
			llaves.add(key);
			return dicc.put(key, 1);
		}
		else{
			return dicc.put(key, dicc.get(key)+1);
		}
	}
	
	public static String getLlaveModa(){
		Integer mayor=0;
		String indexMayor="";
		for(int i=0;i<llaves.size();i++){
			String llave = llaves.get(i);
			int last = dicc.get(llave);
			if(last>mayor){
				indexMayor = llave;
				mayor = last;
			}
			else if(last==mayor){
				if(llave.compareTo(indexMayor)<0){
					indexMayor = llave;
					mayor = last;
				}
			}
		}
		
		return indexMayor;
	}
}
