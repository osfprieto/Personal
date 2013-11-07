package topCoder;

import java.util.Scanner;

public class JavaVSCPP {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String in = sc.next();
		String out;
		int i;
		char temp;
		boolean underScore, upperCase;
		while(!in.equals(null) || !in.equals("")){
			underScore = false;
			upperCase = false;
			out = "";
			for(i=0;i<in.length();i++){
				temp = in.charAt(i);
				if(temp=='_')
					underScore = true;
				if(temp>='A' && temp<='Z')
					upperCase = true;
			}
			if(upperCase && underScore){
				System.out.println("Error!");
			}
			else if(upperCase){
				//from java to C++
				for(i=0;i<in.length();i++){
					temp = in.charAt(i);
					if(temp>='a' && temp<='z')
						out+=temp;
					else if(temp>='A' && temp<='Z'){
						out+='_';
						out+=(char)(temp-'A'+1);
					}
				}
				System.out.println(out);
			}
			else if(underScore){
				//from C++ to java
				boolean flag=false;
				for(i=0;i<in.length();i++){
					temp = in.charAt(i);
					if(flag)
						out+=(char)(temp+'A'-1);
					else if(temp>='a' && temp<='z')
						out+=temp;
					else if(temp=='_')
						flag = true;
				}
				System.out.println(out);
			}
			else{
				System.out.println(in);
			}
			in = sc.next();
		}
	}
}
