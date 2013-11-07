package mios;

import java.util.Scanner;

public class TanksALot {
	
	@SuppressWarnings("resource")
	public static void main(String[] args){
	    int c, s, i, j, temp, run=0;
	    boolean cw, cc;
	    
	    Scanner sc = new Scanner(System.in);
	    
	    c = sc.nextInt();
	    s = sc.nextInt();
	    
	    while(c>0 && s>0){
	        int posiciones[] = new int[s];
	        int combustible[] = new int[s];
	        
	        for(i=0;i<s;i++){
	            posiciones[i] = sc.nextInt();
	            combustible[i] = sc.nextInt();
	        }
	        
	        for(i=0;i<s;i++)
	            for(j=0;j<s;j++)
	                if(posiciones[j]>posiciones[i]){
	                    temp = posiciones[i];
	                    posiciones[i] = posiciones[j];
	                    posiciones[j] = temp;
	                    temp = combustible[i];
	                    combustible[i] = combustible[j];
	                    combustible[j] = temp;
	                }
	        
	        System.out.print("Case "+(++run)+":");
	        
	        for(i=0;i<s;i++){
	            cw = false;
	            cc = false;
	            cw = canDo(posiciones, combustible, i, s, 1);
	            cc = canDo(posiciones, combustible, i, s, -1);
	            if(cw && cc)
	                System.out.print(" "+posiciones[i]+"CCC");
	            else if(cc)
	            	System.out.print(" "+posiciones[i]+"CC");
	            else if(cw)
	            	System.out.print(" "+posiciones[i]+"C");
	        }
	        
	        System.out.println();
	                        
	        c = sc.nextInt();
		    s = sc.nextInt();
	    }
	}

	static boolean canDo(int[] posiciones, int[] combustible, int i, int s, int opcion){//opcion = 1 -> clockwise, opcion = -1 -> counterClockwise
	    
	    int tank=0, cont=0, index=i;
	    boolean iniciado = false;
	    
	    while((tank>0 && cont<s) || !iniciado){
	        iniciado = true;
	        tank += combustible[index];
	        tank -= posiciones[(index+opcion+s)%s]-posiciones[index];
	        index+=opcion;
	        index+=s;
	        index%=s;
	        cont++;
	    }
	    
	    if (cont==s)
	        return true;
	    else
	        return false;
	}
}
