//"grafico.h"
#include<iostream>
#include<conio.h>
#include<iostream>

#define CREAR 1
#define ABRIR 2
#define SALIR 3
#define ERROR 0

using namespace std;
int menu(){
    system("cls");
    cout<<"              Editor de Texto Julián"<<endl;
    cout<<"              ----------------------"<<endl<<endl<<endl;
    
    cout<<"1. Crear nuevo archivo."<<endl;
    cout<<"2. Modificar archivo."<<endl;
    cout<<"3. Salir"<<endl;
    
    char c = getch();
    
    if(c == '1')
        return CREAR;
    else if (c == '2')
        return ABRIR;
    else if (c == '3')
        return SALIR;
    return ERROR;
}

void print(char **texto){
    int i, j;
    for(i=0;i<20;i++){
        for(j=0;j<80;j++){
            cout<<texto[i][j];
        }
        cout<<endl;
    }
}
