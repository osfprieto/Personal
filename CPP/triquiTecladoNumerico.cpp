#include<iostream>
#include<conio.h>
#define TAM 3 // Este debe ser constante = 3 cuando se use el modo especial de juego que se encarga del uso del teclado numérico para mejor accesibilidad
using namespace std;

bool gana(int mat[TAM][TAM]);
void imprimir(int mat[TAM][TAM]);

int main(){
    int selec=0;
    while((selec>2 || selec<1) && TAM==3){
        system("cls");
        cout<<endl<<"\t\t\t\tosfprieto ::D"<<endl;
        cout<<endl<<endl;
        cout<<"\t\t1. Usar modo especial de juego. (teclado numerico)"<<endl;
        cout<<"\t\t2. Usar modo normal de juego"<<endl;
        selec = getch();
        selec-='0';
    }
    if(TAM!=3)
        selec=2;
    int i, j, turno=1, filaIn, colIn, input;
    int mat[TAM][TAM];
    
    for(i=0;i<TAM;i++)
        for(j=0;j<TAM;j++)
            mat[i][j]=0;
    
    while(!gana(mat)){
        imprimir(mat);
        cout<<"\t\t\t\tTurno de "<<((turno>0)?'X':'O')<<endl;
        if(selec==1){
            input=getch();
            input-='0';
            input--;
            filaIn = (TAM-1)-input/TAM;
            colIn = input%TAM;
        }
        else if(selec==2){
            cout<<"\t\t\t\tIngrese fila: ";
            cin>>filaIn;
            filaIn--;
            cout<<"\t\t\t\tIngrese columna: ";
            cin>>colIn;
            colIn--;
        }
        if(mat[filaIn][colIn]==0){
            mat[filaIn][colIn]=turno;
            turno*=-1;
        }
    }
    imprimir(mat);
    turno*=-1;
    cout<<"\t\t\t\tGano "<<((turno>0)?'X':'O')<<endl<<"\t\t";
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
    cout<<endl<<"\t\t\t\tosfprieto ::D"<<endl;
    cout<<endl<<endl;
    for(int i=0;i<TAM;i++){
        cout<<"\t\t\t\t";
        for(int j=0;j<TAM;j++){
            cout<<"   ";
            if(j<TAM-1)
                cout<<'|';
        }
        cout<<endl;
        cout<<"\t\t\t\t";
        for(int j=0;j<TAM;j++){
            cout<<' '<<((mat[i][j]==0)?' ':(mat[i][j]>0)?'X':'O')<<' ';
            if(j<TAM-1)
                cout<<'|';
        }
        cout<<endl;
        cout<<"\t\t\t\t";
        for(int j=0;j<TAM;j++){
            cout<<"   ";
            if(j<TAM-1)
                cout<<'|';
        }
        cout<<endl;
        cout<<"\t\t\t\t";
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
