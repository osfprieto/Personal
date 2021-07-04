package varios;

import java.util.Scanner;

public class Exams {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n, marks, i, suma, cont=0;
		
		n = sc.nextInt();
		marks = sc.nextInt();
		
		int exams[] = new int[n];
		
		for(i=0;i<n;i++)
			exams[i] = 5;
		
		i=0;
		suma = sum(exams);
		
		while(suma!=marks){
			exams[i++]--;
			i%=n;
			suma = sum(exams);
		}
		
		for(i=0;i<n;i++)
			if(exams[i]==2)
				cont++;
		System.out.println(cont);
		
	}
	public static int sum(int[] exams){
		int sum=0;
		for(int i=0;i<exams.length;i++)
			sum+=exams[i];
		return sum;
	}
}
