package misc;

import java.util.Scanner;

public class Maradona {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		int t, k, i, j, cont;
		Scanner sc = new Scanner(System.in);
		String names[] = new String[10], tempName;
		int attack[] = new int[10], tempAttack;
		int defense[] = new int[10], tempDefense;
		//String defenders[] = new String[10];
		//String attackers[] = new String[10];
		
		t = sc.nextInt();
		
		for(k=0;k<t;k++){
			for(i=0;i<10;i++){
				names[i] = sc.next();
				attack[i] = sc.nextInt();
				defense[i] = sc.nextInt();
			}
			
			for(i=0;i<10;i++){
				for(j=i;j<10;j++){
					if(attack[j]<attack[i]){
						tempName = names[i];
						names[i] = names[j];
						names[j] = tempName;
						
						tempAttack = attack[i];
						attack[i] = attack[j];
						attack[j] = tempAttack;
						
						tempDefense = defense[i];
						defense[i] = defense[j];
						defense[j] = tempDefense;
					}
				}
			}
			
			tempAttack=attack[4];
			cont=5;
			
			while(tempAttack==attack[cont])
				cont++;
			
			
			for(i=0;i<10;i++){
				for(j=i;j<10;j++){
					if(defense[j]>defense[i]){
						tempName = names[i];
						names[i] = names[j];
						names[j] = tempName;
						
						tempAttack = attack[i];
						attack[i] = attack[j];
						attack[j] = tempAttack;
						
						tempDefense = defense[i];
						defense[i] = defense[j];
						defense[j] = tempDefense;
					}
				}
			}

			
			
		}
	}
}
