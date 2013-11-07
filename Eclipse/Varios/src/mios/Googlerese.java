package mios;

public class Googlerese {
	public static void main(String[] args) {
		String g = "ejp mysljylc kd kxveddknmc re jsicpdrysi rbcpc ypc rtcsra dkh wyfrepkym veddknkmkrkcd de kr kd eoya kw aej tysr re ujdr lkgc jv";
		String s = "our language is impossible to understand there are twenty six factorial possibilities so it is okay if you want to just give up";
		
		char[][] arr = new char[26][2];
		int index;
		char c;
		
		for(int i=0;i<26;i++)
			arr[i][0]='.';
		
		for(int i=0;i<g.length();i++){
			c = s.charAt(i);
			if(c!=' '){
				index = (int)c-'a';
				if(arr[index][0]=='.'){
					arr[index][0] = s.charAt(i);
					arr[index][1] = g.charAt(i);
				}
			}
		}
		
		arr[25][1] = 'q';
		arr[25][0] = 'z';
		c = 'q';
		index = (int)c-'a';
		arr[index][1] = 'z';
		arr[index][0] = c;
		
		for(int i=0;i<26;i++){
			//System.out.println("arr["+i+"] = '"+arr[i][0]+"';");
			System.out.println(arr[i][0]+"\t"+arr[i][1]);
		}
		
	}

}
