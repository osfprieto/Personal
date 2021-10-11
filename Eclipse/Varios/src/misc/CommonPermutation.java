package misc;

import java.util.Scanner;

public class CommonPermutation {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String a, b, output="";
		int i, lenA, lenB, cont;
		char arrA[];
		char arrB[];
		boolean presenteA[] = new boolean[26];
		boolean presenteB[] = new boolean[26];
		while(sc.hasNext()){
			
			a = sc.nextLine();
			b = sc.nextLine();
			
			lenA = a.length();
			lenB = b.length();
			
			arrA = a.toCharArray();
			arrB = b.toCharArray();
			
			for(i=0;i<26;i++){
				presenteA[i] = false;
				presenteB[i] = false;
			}
			for(i=0, cont=0;i<lenA && cont<26;i++){
				int index = arrA[i]-'a';
				if(!presenteA[index]){
					presenteA[index]=true;
					cont++;
				}
			}
			for(i=0, cont=0;i<lenB && cont<26;i++){
				int index = arrB[i]-'a';
				if(!presenteB[index]){
					presenteB[index]=true;
					cont++;
				}
			}
			for(i=0;i<26;i++)
				if(presenteA[i] && presenteB[i])
					output+=(char)('a'+i);
			output+="\n";
		}
		System.out.println(output);
	}
}
