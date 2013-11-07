#include<iostream>
#define TAM 5
using namespace std;

bool gana(int mat[TAM][TAM]);
void imprimir(int mat[TAM][TAM]);

int main(){
    
    int i, j, turno=1, filaIn, colIn;
    int mat[TAM][TAM];
    
    for(i=0;i<TAM;i++)
        for(j=0;j<TAM;j++)
            mat[i][j]=0;
    
    while(!gana(mat)){
        imprimir(mat);
        cout<<"Turno de "<<((turno>0)?'X':'O')<<endl;
        cout<<"Ingrese fila: ";
        cin>>filaIn;
        cout<<"Ingrese columna: ";
        cin>>colIn;
        if(mat[filaIn-1][colIn-1]==0){
            mat[filaIn-1][colIn-1]=turno;
            turno*=-1;
        }
    }
    imprimir(mat);
    turno*=-1;
    cout<<"Gano "<<((turno>0)?'X':'O')<<endl;
    system("pause");
    return 0;
}

bool gana(int mat[TAM][TAM]){
    int i, j, inicio;
    //revisar filas
    for(i=0;i<TAM;i++){
        inicio = mat[i][0];
        j=0;
        if(inicio!=0){
            while(TAM>j && inicio==mat[i][j])
                j++;
            if(TAM==j)
                return true;
        }
    }
    
    //revisar columnas
    for(j=0;j<TAM;j++){
        inicio = mat[0][j];
        i=0;
        if(inicio!=0){
            while(TAM>i && inicio==mat[i][j])
                i++;
            if(TAM==i)
                return true;
        }
    }
    
    //revisar diagonal primaria
    inicio = mat[0][0];
    i=0;
    if(inicio!=0){
        while(TAM>i && inicio==mat[i][i])
            i++;
        if(TAM==i)
            return true;
    }
    
    //revisar diagonal secundaria
    inicio = mat[0][TAM-1];
    i=0;
    if(inicio!=0){
        while(TAM>i && inicio==mat[i][TAM-1-i])
            i++;
        if(TAM==i)
            return true;
    }
    
    return false;
}

void imprimir(int mat[TAM][TAM]){
    system("cls");
    for(int i=0;i<TAM;i++){
        for(int j=0;j<TAM;j++){
            cout<<"   ";
            if(j<TAM-1)
                cout<<'|';
        }
        cout<<endl;
        for(int j=0;j<TAM;j++){
            cout<<' '<<((mat[i][j]==0)?' ':(mat[i][j]>0)?'X':'O')<<' ';
            if(j<TAM-1)
                cout<<'|';
        }
        cout<<endl;
        for(int j=0;j<TAM;j++){
            cout<<"   ";
            if(j<TAM-1)
                cout<<'|';
        }
        cout<<endl;
        if(i<TAM-1){
            for(int j=0;j<TAM;j++){
            cout<<"---";
            if(j<TAM-1)
                cout<<'-';
            }
        }
        cout<<endl;
    }
}
