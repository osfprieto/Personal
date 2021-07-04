package varios;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AlternateChain {
	public static void main(String[] args) {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		
		String line;
		int i, caso, len;
		int cont[] = new int[2];
		char arr[];
		try{
			for(int k=0;k<300;k++){
				
				line = sc.readLine();
				len = line.length();
				arr = line.toCharArray();
				
				caso=0;
				cont[caso] = 0;
				for(i=0;i<len;i++){//minúsculas en los pares
					if(i%2==caso){
						if(arr[i]>='A' && arr[i]<='Z')
							cont[caso]++;
					}
					else{
						if(arr[i]>='a' && arr[i]<='z')
							cont[caso]++;
					}
				}
				
				caso=1;
				cont[caso] = 0;
				for(i=0;i<len;i++){//minúsculas en los pares
					if(i%2==caso){
						if(arr[i]>='A' && arr[i]<='Z')
							cont[caso]++;
					}
					else{
						if(arr[i]>='a' && arr[i]<='z')
							cont[caso]++;
					}
				}
				
				System.out.println((cont[0]<cont[1])?cont[0]:cont[1]);
			}
		}
		catch(Exception e){
			
		}
	}
}
