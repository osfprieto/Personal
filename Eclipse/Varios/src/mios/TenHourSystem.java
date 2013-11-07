package mios;

import java.util.Scanner;

public class TenHourSystem {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		long horas, mins, segundos, centesimas, time, input;
		String output;
		while(sc.hasNext()){
			try{
				input = sc.nextLong();
				
				centesimas = input%100;
				input/=100;
				segundos = input%100;
				input/=100;
				mins = input%100;
				input/=100;
				horas = input%100;
				
				horas*=60*60*100;
				mins*=60*100;
				segundos*=100;
				
				time = horas+mins+segundos+centesimas;
				
				time = ((long)(((double)time)*(1000.0/864.0)));
				
				centesimas = time%100;
				time/=100;
				segundos = time%100;
				time/=100;
				mins = time%100;
				time/=100;
				horas = time%10;
				
				output=""+horas+((mins<10)?"0"+mins:""+mins)+((segundos<10)?"0"+segundos:""+segundos)+((centesimas<10)?"0"+centesimas:""+centesimas);
				
				System.out.println(output);
				
			}
			catch(Exception e){
				
			}
		}
	}
}
