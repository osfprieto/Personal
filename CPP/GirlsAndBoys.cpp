#include<iostream>
using namespace std;

int funcion(int a, int b);

int main(){
	int g, b;
	cin>>g>>b;
	while(g!=-1 || b!=-1){
		if(g==b)
			cout<<1<<endl;
		else if(g>b)
			cout<<funcion(g, b)<<endl;
		else
			cout<<funcion(b, g)<<endl;
		cin>>g>>b;
	}
	return 0;
}

int funcion(int a, int b){
    int retorno = a/(b+1);
    if(retorno*b == a)
        return retorno;
    else
        return retorno+1;
}
