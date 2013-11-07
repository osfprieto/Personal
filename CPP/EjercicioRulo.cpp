#include<iostream>
using namespace std;

bool identificarEntrada();

int mat[8][8];

/*
En este análisis se va a analizar si las fichas blancas tienen al rey negro sin
amenazas, en jaque o en "jaque mate" (este jaque mate se da si el rey está
rodeado de casillas amenazadas)

0  - casilla vacía sin amenaza
1  - casilla vacía con amenaza

2  - rey negro
3  - reina negra
4  - caballo negro
5  - alfil negro
6  - torre negra
7  - peón negro

8  - rey blanco
9  - reina blanca
10 - caballo blanco
11 - alfil blanco
12 - torre blanca
13 - peón blanco
*/

int main(){
    
    int i, j;
    
    for(i=0;i<8;i++)
        for(j=0;j<8;j++)
            mat[i][j] = 0;
    
    while(identificarEntrada()){}
    
    
    
    for(i=0;i<8;i++){
        for(j=0;j<8;j++){
            if(mat[i][j]>=10)
                cout<<mat[i][j];
            else
                cout<<' '<<mat[i][j];
            cout<<' ';
        }
        cout<<endl;
    }
    
    system("pause");
    return 0;
}

bool identificarEntrada(){
    system("cls");
    cout<<"Entre la ficha que va a poner.\nEntre -1 para indicar que ha terminado"<<endl<<endl;
    cout<<"2  - Rey Negro"<<endl;
    cout<<"3  - Reina Negra"<<endl;
    cout<<"4  - Caballo Negro"<<endl;
    cout<<"5  - Alfil Negro"<<endl;
    cout<<"6  - Torre Negra"<<endl;
    cout<<"7  - Peon Negro"<<endl;
    cout<<endl;
    cout<<"8  - Rey Blanco"<<endl;
    cout<<"9  - Reina Blanca"<<endl;
    cout<<"10 - Caballo Blanco"<<endl;
    cout<<"11 - Alfil Blanco"<<endl;
    cout<<"12 - Torre Blanca"<<endl;
    cout<<"13 - Peon Blanco"<<endl<<endl;
    int ficha, fila, columna;
    cin>>ficha;
    while((ficha<2 || ficha>13) && ficha!=-1){
        cout<<"Valor incorrecto, ingrese de nuevo:"<<endl;
        cin>>ficha;
    }
    if (ficha == -1)
        return false;
    cout<<"Ahora entre la fila y la columna de su ficha:"<<endl;
    cin>>fila>>columna;
    while(--fila<0 || fila>7 || --columna<0 || columna>7){
        cout<<"Casillas ingresadas erroneas, ingresar de nuevo:"<<endl;
        cin>>fila>>columna;
    }
    mat[fila][columna] = ficha;
    return true;
}
