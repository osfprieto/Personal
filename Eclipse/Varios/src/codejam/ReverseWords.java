package codejam;

import java.util.*;

public class ReverseWords {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n, i, j, k;
		n = sc.nextInt();
		String in;
		
		for(i=-1;i<n;i++){
			in = sc.nextLine();
			StringTokenizer st = new StringTokenizer(in);
			ArrayList<String> arr = new ArrayList<String>();
			j=0;
			while(st.hasMoreTokens()){
				arr.add(st.nextToken());
				j++;
			}
			if(i>=0){
				System.out.print("Case #"+(i+1)+": ");
				for(k=j-1;k>=0;k--){
					System.out.print(arr.get(k)+' ');
				}
				System.out.println("");
			}
		}
	}
}
