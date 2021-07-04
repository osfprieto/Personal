package varios;

import java.util.Scanner;

public class Results {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int k, n, i, j, time, solved, count=1;
		
		n = sc.nextInt();
		k = sc.nextInt();
		
		Contestant conts[] = new Contestant[n];
		Contestant temp;
		
		for(i=0;i<n;i++){
			solved = sc.nextInt();
			time = sc.nextInt();
			conts[i] = new Contestant(solved, time);
		}
		
		for(i=0;i<n;i++)
			for(j=0;j<n;j++)
				if(conts[j].isGreaterThan(conts[i])){
					temp = conts[i];
					conts[i] = conts[j];
					conts[j] = temp;
				}
		
		temp = conts[k-1];
		
		i=k;
		while(i<n && conts[i++].equals(temp))
			count++;
		
		i=k-2;
		while(i>=0 && conts[i--].equals(temp))
			count++;
		
		System.out.println(count);
	}
	private static class Contestant{
		private int solved;
		private int time;
		public Contestant(int solved, int time){
			this.solved = solved;
			this.time = time;
		}
		public boolean isGreaterThan(Contestant obj){
			if (solved<obj.solved || (solved==obj.solved && time>obj.time))
				return true;
			return false;
		}
		public boolean equals(Contestant obj){
			return solved==obj.solved && time==obj.time;
		}
		public String toString(){
			return "("+solved+", "+time+")";
		}
	}
}
