package varios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Lanmower {

	private static int obj[][];
	private static int mat[][];
	private static int buff[][];
	private static int n, m;
	
	private static int actualHeight;
	
	private static Set<Integer> wantedHeights;
	
	@SuppressWarnings("resource")
	public static void main(String[] args){
	    try{
	    	
		    int T, i, j, k;
		    
		    BufferedReader br = new BufferedReader(new FileReader("B-large.in"));
		    
		    StringBuffer sb = new StringBuffer();
		    String line;
		    
		    T = Integer.parseInt(br.readLine().trim());
		    
		    for(k=1;k<=T;k++){
		    	
		    	line = br.readLine().trim();
		    	
		    	String nm[] = line.split(" ");
		    	
		    	n = Integer.parseInt(nm[0].trim());
		    	m = Integer.parseInt(nm[1].trim());
		        
		        obj = new int[n][m];
		        
		        for(i=0;i<n;i++){
		            line = br.readLine().trim();
		            String nums[] = line.split(" ");
		            for(j=0;j<m;j++)
		                obj[i][j] = Integer.parseInt(nums[j].trim());
		        }
		        
		        
		        
		        sb.append("Case #");
		        sb.append(k);
		        sb.append(": ");
		        sb.append(isPossible()?"YES":"NO");
		        sb.append("\n");
		        
		        
		    }
		    
		    BufferedWriter bw = new BufferedWriter(new FileWriter("B-large.out"));
		    bw.write(sb.toString());
		    bw.close();
		    
	    }catch(Exception e){
	    	
	    }
	}

	private static boolean isPossible(){
	    
	    int i, j;
	    
	    buff = new int[n][m];
	    mat = new int[n][m];
	        
	    for(i=0;i<n;i++){
	        for(j=0;j<m;j++){
	            buff[i][j] = 100;
	            mat[i][j] = 100;
	        }
	    }
	    
	    wantedHeights = new TreeSet<Integer>();
	    
	    for(i=0;i<n;i++)
	    	for(j=0;j<m;j++)
	    		wantedHeights.add(obj[i][j]);
	   
	    Iterator<Integer> it = wantedHeights.iterator();
	    
	    while(it.hasNext()){
	    	actualHeight = it.next();
	    	
	    	for(i=0;i<n;i++){
	    		cutH(i);
	    		int check = check();
	    		if(check==0)//Llegamos al objetivo
	    			return true;
	    		else if(check==1)//Hay que seguir
	    			backUp();
	    		else//La embarramos en el corte
	    			goBack();
	    			
	    	}
	    	
	    	for(j=0;j<m;j++){
	    		cutV(j);
	    		int check = check();
	    		if(check==0)//Llegamos al objetivo
	    			return true;
	    		else if(check==1)//Hay que seguir
	    			backUp();
	    		else//La embarramos en el corte
	    			goBack();
	    			
	    	}
	    	
	    }
	    
	    return false;
	}
	
	private static int check(){
		
		/*System.err.println("ActualHeight: "+actualHeight);
    	for(int ii=0;ii<n;ii++){
        	for(int jj=0;jj<m;jj++)
        		System.out.print(mat[ii][jj]+"\t");
        	System.out.println();
        }
    	System.err.println("___");*/
		
		
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
				if(mat[i][j]<obj[i][j])
					return -1;
		
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
				if(mat[i][j]>obj[i][j])
					return 1;
					
		return 0;
	}
	
	private static void cutH(int i){
		for(int j=0;j<m;j++)
			if(mat[i][j]>actualHeight)
				mat[i][j] = actualHeight;
	}
	
	private static void cutV(int j){
		for(int i=0;i<n;i++)
			if(mat[i][j]>actualHeight)
				mat[i][j] = actualHeight;
	}
	
	private static void backUp(){
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
				buff[i][j] = mat[i][j];
	}
	
	private static void goBack(){
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
				mat[i][j] = buff[i][j];
	}
	
}
