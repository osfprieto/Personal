#include<stdio.h>

int main(){
	
	int horas, mins, segundos, centesimas, time, input;
	while(scanf("%d", &input)){
		
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
		
		time = ((int)(((double)time)*(1000.0/864.0)));
		
		printf("%d\n", time);
	}
	return 0;
}
