package mios;

import java.util.Scanner;

public class ForestTrees{
	@SuppressWarnings("resource")
	public static void main(String[] args){
		int n, k, i, j, trees, acorns, ini, fin, grupo[], last=1, temp;
		grupo = new int[26];
		Scanner sc = new Scanner(System.in);
		String token, tokens[];
		
		n = sc.nextInt();
		
		for(k=0;k<n;k++){
			acorns=0;
			trees=0;
			
			for(i=0;i<26;i++)
				grupo[i]=-1;
			
			token = sc.next();
			
			while(token.charAt(0)!='*'){
				ini = token.charAt(1)-'A';
				fin = token.charAt(3)-'A';
				
				if(grupo[ini]==-1 && grupo[fin]==-1){
					grupo[ini]=last;
					grupo[fin]=last;
					last++;
				}
				else if(grupo[ini]>0 && grupo[fin]>0){
					temp = grupo[fin];
					for(i=0;i<26;i++){
						if(grupo[i]==temp){
							grupo[i]=grupo[ini];
						}
					}
				}
				else if(grupo[ini]>0){
					grupo[fin]=grupo[ini];
				}
				else if(grupo[fin]>0){
					grupo[ini] = grupo[fin];
				}
				token = sc.next();
			}
			
			token = sc.next();
			tokens = token.split(",");
			
			for(i=0;i<tokens.length;i++){
				temp = tokens[i].charAt(0)-'A';
				grupo[temp]++;
			}
			
			for(i=0;i<26;i++){
				if(grupo[i]==0)
					acorns++;
				else if(grupo[i]>0){
					temp=grupo[i];
					trees++;
					for(j=0;j<26;j++){
						if(grupo[j]==temp){
							grupo[j]=-1;
						}
					}
				}
			}
			
			System.out.println("There are "+trees+" tree(s) and "+acorns+" acorn(s).");
			
		}
	}
}