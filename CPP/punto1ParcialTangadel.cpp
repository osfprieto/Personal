#include<iostream>
#include<string.h>
using namespace std;

int main(int arg, char*argv[]){
    int i, j;
    char cadena[30], aux[2];
    cout<<"Digite cadena";
    cin>>cadena;
    for(i=0;i<strlen(cadena)-1;i++){
        for(j=i+1;j<strlen(cadena);j++){
            if(cadena[i]>cadena[j]){
                aux[0] = cadena[j];
                cadena[j] = cadena[i];
                cadena[i] = aux[0];
            }
        }
    }
    cout<<cadena;
    system("pause");
    return 0;
}
//decir que se imprime si se entra comportamiento como entrada
