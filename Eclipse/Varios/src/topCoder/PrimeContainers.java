package topCoder;

public class PrimeContainers {
	
	public int containerSize(int n){
		int s=0, i=0, m;
		m=(int)(Math.pow(2, i));
		while(m<n){
			if(primo((int)(n/m))){
				s++;
			}
			i++;
			//m=(int)(Math.pow(2, i));
		}
		return s;
	}
	
	private boolean primo(int n){
		int i;
		for(i=2;i<n;i++){
			if(n%i==0){
				return false;
			}
		}
		return true;
	} 
}
