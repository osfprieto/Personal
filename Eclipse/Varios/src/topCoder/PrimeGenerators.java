package topCoder;

public class PrimeGenerators {
	public static void main(String[] args) {
		
		int n = 1000000000;
		int nums[] = new int[n];
		
		for(int i=1;i<=n;i++){
			nums[i-1] = i;
		}
		
		for(int i=2;i<32000;i++){
			for(int j=0;j<n;j++){
				if(nums[j]!=0){
					if(primo(j)){
						
					}
				}
			}
		}
		
	}
	public static boolean primo(int x){
		//for(int i=0)
		return true;
	}
}
