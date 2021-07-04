package varios;

import java.util.Scanner;

public class PhoneList {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		StringBuilder sb = new StringBuilder();
		int T = sc.nextInt();
		int i, k, j, n, len;
		boolean prefix;
		for(k=0;k<T;k++){
			n = sc.nextInt();
			String numeros[] = new String[n];
			for(i=0;i<n;i++)
				numeros[i]=sc.next();
			sort(numeros);
			prefix = false;
			for(i=0;i<n && !prefix;i++){
				len = numeros.length;
				for(j=i+1;j<n && !prefix;j++)
					if(numeros[i].equals(numeros[j].substring(0, len)))
						prefix = true;
			}
			if(!prefix)
				sb.append("YES\n");
			else
				sb.append("NO\n");
			for(i=0;i<n;i++)
				System.out.print(numeros[i]+", ");
			System.out.println(",     "+numeros.length);
		}
		System.out.println(sb.toString());
	}
	private static void sort(String[] numeros){
		int i, j, n=numeros.length;
		String temp;
		for(i=0;i<n;i++)
			for(j=i+1;j<n;j++)
				if(numeros[i].compareTo(numeros[j])>0){
					temp = numeros[i];
					numeros[i] = numeros[j];
					numeros[j] = temp;
				}
	}
}
