package topCoder;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ReverseTheInput {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(), i, j;
		String in, out;
		
		in = sc.nextLine();
		
		while(in!=null || in!=""){
			
			StringTokenizer st = new StringTokenizer(in);
			ArrayList<String> arr = new ArrayList<String>();
			i=0;
			while(st.hasMoreTokens()){
				arr.add(st.nextToken());
				i++;
			}
			i--;
			while(i>=0){
				out = "";
				for(j=arr.get(i).length()-1;j>=0;j--){
					out+=arr.get(i).charAt(j);
				}
				for(j=0;j<n;j++){
					System.out.print(out+' ');
				}
				i--;
			}
			
			in = sc.nextLine();
		}
		
		
	}
}
