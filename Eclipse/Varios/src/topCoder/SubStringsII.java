package topCoder;

import java.util.Scanner;

public class SubStringsII {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		String in, inTemp;
		Scanner sc = new Scanner(System.in);
		int q, a, b, i, j, k, ans;
		in = sc.next();
		q = sc.nextInt();
		a = sc.nextInt();
		b = sc.nextInt();
		for(i=0;i<q;i++){
			inTemp = sc.next();
			ans = 0;
			for(j=0;j<in.length();j++){
				if(in.charAt(j)==inTemp.charAt(0)){
					boolean its = true;
					try{
						for(k=0;k<inTemp.length();k++){
							if(in.charAt(j+k)!=inTemp.charAt(k))
								its = false;
						}
					}
					catch(IndexOutOfBoundsException e){
						its = false;
					}
					if(its){
						ans++;
					}
				}
			}
			System.out.println(ans);
			in+=((char)((a*ans+b)%26+'a'));
		}
	}
}
