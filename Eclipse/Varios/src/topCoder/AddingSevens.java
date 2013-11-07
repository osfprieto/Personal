package topCoder;

import java.util.ArrayList;
import java.util.Scanner;

public class AddingSevens {

	//https://www.spoj.pl/problems/ANARC08B/
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String in = sc.next();
		String num7_1, num7_2, num7_3;
		int i, posP, posE, num1, num2, num3;
		try{
			while(!in.equals("BYE")){
				
				i=0;
				
				while(in.charAt(i)!='+')
					i++;
				
				posP = i;
				
				while(in.charAt(i)!='=')
					i++;
				
				posE = i;
				
				num7_1 = in.substring(0, posP);
				num7_2 = in.substring(posP+1, posE);
				
				num1=0;
				num2=0;
				
				for(i=0;i<num7_1.length()/3;i++){
					num1+=value(num7_1.charAt(3*i)+""+num7_1.charAt(3*i+1)+""+num7_1.charAt(3*i+2))*Math.pow(10.0, num7_1.length()/3-1-i);
				}
				
				for(i=0;i<num7_2.length()/3;i++){
					num2+=value(num7_2.charAt(3*i)+""+num7_2.charAt(3*i+1)+""+num7_2.charAt(3*i+2))*Math.pow(10.0, num7_2.length()/3-1-i);
				}
				
				num3 = num1+num2;
				
				num7_3 = "";
				ArrayList<String> arr = new ArrayList<String>();
				while(num3>0){
					arr.add(value(num3%10));
					num3/=10;
				}
				
				for(i=arr.size()-1;i>=0;i--)
					num7_3+=arr.get(i);
				
				System.out.println(num7_1+"+"+num7_2+"="+num7_3);
				
				in = sc.next();
			}
		}
		catch(IndexOutOfBoundsException e){
			//nada, solo es para que el programa se cierre si se entre algo mal
			//0 => 0111111 => 063
			//1 => 0001010 => 010
			//2 => 1011101 => 093
			//3 => 1001111 => 079
			//4 => 1101010 => 106
			//5 => 1100111 => 103
			//6 => 1110111 => 119
			//7 => 0001011 => 011
			//8 => 1111111 => 127
			//9 => 1101111 => 111
		}
	}
	public static int value(String x){
		if(x.equals("063"))
			return 0;
		else if(x.equals("010"))
			return 1;
		else if(x.equals("093"))
			return 2;
		else if(x.equals("079"))
			return 3;
		else if(x.equals("106"))
			return 4;
		else if(x.equals("103"))
			return 5;
		else if(x.equals("119"))
			return 6;
		else if(x.equals("011"))
			return 7;
		else if(x.equals("127"))
			return 8;
		else if(x.equals("111"))
			return 9;
		else
			return -1;
	}
	
	public static String value(int n){
		if(n==0)
			return "063";
		else if(n==1)
			return "010";
		else if(n==2)
			return "093";
		else if(n==3)
			return "079";
		else if(n==4)
			return "106";
		else if(n==5)
			return "103";
		else if(n==6)
			return "119";
		else if(n==7)
			return "011";
		else if(n==8)
			return "127";
		else if(n==9)
			return "111";
		else
			return "";
	}
}
