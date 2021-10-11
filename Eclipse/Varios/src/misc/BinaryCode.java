package misc;

import java.util.Arrays;
import java.util.Scanner;

public class BinaryCode {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String arr[];
		BinaryCode obj = new BinaryCode();
		while(sc.hasNext()){
			arr = obj.decode(sc.next());
			System.out.println(Arrays.toString(arr));
		}
	}
	
	public String[] decode(String input){
		char arr[] = input.toCharArray();
		int len = arr.length;
		char arrDecode[] = new char[len];
		String ret[] = new String[2];
		boolean error;
		int i;
		String output;
		if(len>=2){
			for(i=0;i<len;i++)
				arr[i]-='0';
			///////////////
			arrDecode[0]=0;
			error = false;
			
			if(arr[0]==1)
				arrDecode[1]=1;
			else if(arr[0]==0)
				arrDecode[1]=0;
			else
				error=true;
			
			for(i=2;i<len;i++)
				arrDecode[i] = (char)(arr[i-1]-arrDecode[i-1]-arrDecode[i-2]);
			
			if(len>=3){
				if(arr[len-1]-arrDecode[len-1]-arrDecode[len-2]!=0)
					error=true;
			}
			else if(len==2){
				if(arr[len-1]-arrDecode[len-1]!=0)
					error=true;
			}
			
			output="";
			for(i=0;i<len && !error;i++)
				if(arrDecode[i]==0 || arrDecode[i]==1)
					output+=""+((char)(arrDecode[i]+'0'));
				else
					error = true;
			
			if(error)
				ret[0]="NONE";
			else
				ret[0]=output;
			
			arrDecode[0]=1;
			error = false;
			
			if(arr[0]==2)
				arrDecode[1]=1;
			else if(arr[0]==1)
				arrDecode[1]=0;
			else
				error=true;
			
			for(i=2;i<len;i++)
				arrDecode[i] = (char)(arr[i-1]-arrDecode[i-1]-arrDecode[i-2]);
			
			if(len>=3){
				if(arr[len-1]-arrDecode[len-1]-arrDecode[len-2]!=0)
					error=true;
			}
			else if(len==2){
				if(arr[len-1]-arrDecode[len-1]!=0)
					error=true;
			}
			
			output="";
			for(i=0;i<len && !error;i++)
				if(arrDecode[i]==0 || arrDecode[i]==1)
					output+=""+((char)(arrDecode[i]+'0'));
				else
					error = true;
			
			if(error)
				ret[1]="NONE";
			else
				ret[1]=output;
		}
		else{
			if(arr[0]==0){
				ret[0]="0";
				ret[1]="NONE";
			}
			else if(arr[0]==1){
				ret[0]="NONE";
				ret[1]="1";
			}
			else{
				ret[0]="NONE";
				ret[1]="NONE";
			}
		}
		
		return ret;
	}
	
}
