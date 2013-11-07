package codejam;

import java.util.Scanner;

public class Magicka {
	
	public static void main(String[] args) {
		/////////////////////////////////////////////////////////
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		int t, c, d, s, i, j, k, l, cont;
	    //char base[8] = {Q, W, E, R, A, S, D, F};
	    String conjugation[];
	    String destruction[];
	    String data;
	    
	    t = scanner.nextInt();
	    
	    for(i=0;i<t;i++){

	        c = scanner.nextInt();
	        conjugation = new String[c];
	        for(j=0;j<c;j++){
	            conjugation[j] = new String("");
	            conjugation[j] = scanner.next();
	        }
	        
	        d = scanner.nextInt();
	        destruction = new String[d];
	        for(j=0;j<d;j++){
	            destruction[j] = new String("");
	            destruction[j] = scanner.next();
	        }
	        
	        s = scanner.nextInt();
	        d = scanner.nextInt();
	        data = new String("");
	        data = scanner.next();
	        
	        cont=s;
	        
	        while(cont>0){
	            for(j=0;j<s;j++){
	                
	                for(k=0;k<c;k++){
	                    if(data.charAt(j)==conjugation[k].charAt(0)){
	                        if(j==0){
	                            if(data.charAt(j+1)==conjugation[k].charAt(1)){
	                                data=data.substring(0, j)+conjugation[k].charAt(2)+data.substring(j+2, s-1);
	                                cont--;
	                                s--;
	                            }
	                        }
	                        else if(j==s-1){
	                            if(data.charAt(j-1)==conjugation[k].charAt(1)){
	                                cont--;
	                                s--;
	                                data = data.substring(0, s-1)+conjugation[k].charAt(2);
	                            }
	                        }
	                        else{
	                            if(data.charAt(j+1)==conjugation[k].charAt(1)){
	                            	data=data.substring(0, j)+conjugation[k].charAt(2)+data.substring(j+2, s-1);;
	                                cont--;
	                                s--;
	                            }
	                            else if(data.charAt(j-1)==conjugation[k].charAt(1)){
	                                cont--;
	                                s--;
	                                data=data.substring(0, j-1)+conjugation[k].charAt(2)+data.substring(j+1, s);
	                            }
	                        }
	                    }
	                }
	                
	                for(k=0;k<d;k++){
	                    if(data.charAt(j)==destruction[k].charAt(0)){
	                        for(l=0;l<s;l++){
	                            if(data.charAt(l)==destruction[k].charAt(1)){
	                                cont-=j;
	                                s-=j;
	                                data = data.substring(j, j+s);
	                            }
	                        }
	                    }
	                }
	               
	            }
	            cont--;
	        }
	        
	        System.out.print("Case #"+(i+1)+": "+'[');
	        for(j=0;j<s-1;j++)
	            System.out.print(+data.charAt(j)+", ");
	        System.out.println(data.charAt(s-1)+']');
	    }
		/////////////////////////////////////////////////////////
	}

}
