#include<iostream>
#include<fstream>
#include<conio.h>
using namespace std;

void iniciar();
void procesar();

void sumar();
void restar();
void multiplica();
void solucion();
void inversa();
void trazaDeter();
void salir();

char s = 0;
bool repite = true;

int main(){
    while(repite){
        iniciar();
        procesar();
    }
    return 0;
}

void iniciar(){
    system("color c");
    system("cls");
    cout<<"Desarrollado por osfprieto@hotmail.com\n"<<endl;
    cout<<"\t\tMatrices\n\n"<<endl;
    cout<<"1. Suma de Matrices."<<endl;
    cout<<"2. Diferencia de Matrices."<<endl;    
    cout<<"3. Multiplicacion de Matrices."<<endl;
    cout<<"4. Solcucion de Sistema de Ecuaciones."<<endl;
    cout<<"5. Hallar la Inversa."<<endl;
    cout<<"6. Hallar Traza y Determinante"<<endl;
    cout<<"7. Salir"<<endl;
    s=getch();
}
void procesar(){
    if(s=='1'){
        sumar();
    }
    else if(s=='2'){
        restar();
    }
    else if(s=='3'){
        multiplica();
    }
    else if(s=='4'){
        solucion();
    }
    else if(s=='5'){
        inversa();
    }
    else if(s=='6'){
        trazaDeter();
    }
    else if(s=='7'){
        salir();
    }
}

void sumar(){
    system("cls");
    cout<<"Desarrollado por osfprieto@hotmail.com\n"<<endl;
    cout<<"\t\tSuma de Matrices.\n\n"<<endl;
    fstream leer, escribir;
    char sale[20], entra[20];
    cout<<"Entre el nombre del archivo resultado:"<<endl;
    cin>>sale;
    cout<<"Entre el nombre del archivo de los operandos:"<<endl;
    cin>>entra;
    leer.open(entra, ios::in);
    escribir.open(sale, ios::out);
    int m, n, i, j;
    leer>>m>>n;
    double matSuma1[m][n], matSuma2[m][n];
    for(i=0;i<m;i++){
        for(j=0;j<n;j++){
            leer>>matSuma1[i][j];
        }
        cout<<endl;
    }
    for(i=0;i<m;i++){
        for(j=0;j<n;j++){
            leer>>matSuma2[i][j];
        }
    }
    for(i=0;i<m;i++){
        for(j=0;j<n;j++){
            escribir<<matSuma1[i][j]+matSuma2[i][j]<<"\t";
        }
        escribir<<"\n";
    }
    leer.close();
    escribir.close();
}

void restar(){
    system("cls");
    cout<<"Desarrollado por osfprieto@hotmail.com\n"<<endl;
    cout<<"\t\tSuma de Matrices.\n\n"<<endl;
    fstream leer, escribir;
    char sale[20], entra[20];
    cout<<"Entre el nombre del archivo resultado:"<<endl;
    cin>>sale;
    cout<<"Entre el nombre del archivo de los operandos:"<<endl;
    cin>>entra;
    leer.open(entra, ios::in);
    escribir.open(sale, ios::out);
    int m, n, i, j;
    leer>>m>>n;
    double matResta1[m][n], matResta2[m][n];
    for(i=0;i<m;i++){
        for(j=0;j<n;j++){
            leer>>matResta1[i][j];
        }
    }
    for(i=0;i<m;i++){
        for(j=0;j<n;j++){
            leer>>matResta2[i][j];
        }
    }
    for(i=0;i<m;i++){
        for(j=0;j<n;j++){
            escribir<<matResta1[i][j]-matResta2[i][j]<<"\t";
        }
        escribir<<"\n";
    }
    leer.close();
    escribir.close();
}
void multiplica(){
    system("cls");
    cout<<"Desarrollado por osfprieto@hotmail.com\n"<<endl;
    cout<<"\t\tMultiplicacion de Matrices.\n\n"<<endl;
    fstream leer, escribir;
    char sale[20], entra[20];
    cout<<"Entre el nombre del archivo resultado:"<<endl;
    cin>>sale;
    cout<<"Entre el nombre del archivo de los operandos:"<<endl;
    cin>>entra;
    leer.open(entra, ios::in);
    escribir.open(sale, ios::out);
    int m, l, n, i, j, k;
    leer>>m>>l>>n;
    double matMul1[m][l], matMul2[l][n], aux;
    for(i=0;i<m;i++){
        for(j=0;j<l;j++){
            leer>>matMul1[i][j];
        }
    }
    for(i=0;i<l;i++){
        for(j=0;j<n;j++){
            leer>>matMul2[i][j];
        }
    }
    for(i=0;i<m;i++){
        for(j=0;j<n;j++){
            aux=0;
            for(k=0;k<l;k++){
                aux+=matMul1[i][k]*matMul2[k][j];
            }
            escribir<<aux<<"\t";
        }
        escribir<<endl;
    }
    leer.close();
    escribir.close();
}
void solucion(){
    system("cls");
    cout<<"Desarrollado por osfprieto@hotmail.com\n"<<endl;
    cout<<"\t\tSolucion de Sistemas de Ecuaciones.\n\n"<<endl;
    fstream leer, escribir;
    char sale[20], entra[20];
    cout<<"Entre el nombre del archivo resultado:"<<endl;
    cin>>sale;
    cout<<"Entre el nombre del archivo de los operandos:"<<endl;
    cin>>entra;
    leer.open(entra, ios::in);
    escribir.open(sale, ios::out);
    int m, i, j, k;
    leer>>m;
    double matSolve[m][m+1], aux;
    
    for(i=0;i<m;i++){
        for(j=0;j<m+1;j++){
            leer>>matSolve[i][j];
        }
    }
    
    for(i=0;i<m;i++){
        double aux=matSolve[i][i];
        for(j=0;j<m+1;j++){
            matSolve[i][j]/=aux;
        }
        for(k=0;k<m;k++){
            if(k!=i){
                aux=matSolve[k][i];
                for(j=0;j<m+1;j++){
                    matSolve[k][j] -= matSolve[i][j]*aux;
                }
            }
        }
    }
    for(i=0;i<m;i++){
        escribir<<"X"<<i+1<<"\t"<<matSolve[i][m]<<endl;
    }
}
void inversa(){
    system("cls");
    cout<<"Desarrollado por osfprieto@hotmail.com\n"<<endl;
    cout<<"\t\tInversa de una Matriz.\n\n"<<endl;
    fstream leer, escribir;
    char sale[20], entra[20];
    cout<<"Entre el nombre del archivo resultado:"<<endl;
    cin>>sale;
    cout<<"Entre el nombre del archivo de los operandos:"<<endl;
    cin>>entra;
    leer.open(entra, ios::in);
    escribir.open(sale, ios::out);
    int m, i, j, k;
    leer>>m;
    double matInv[m][2*m], aux;
    
    for(i=0;i<m;i++){
        for(j=0;j<m;j++){
            leer>>matInv[i][j];
        }
    }
    
    for(i=0;i<m;i++){
        for(j=m;j<2*m;j++){
            if(j-m==i){
                matInv[i][j]=1;
            }
            else{
                matInv[i][j]=0;
            }
        }
    }
    
    for(i=0;i<m;i++){
        double aux=matInv[i][i];
        for(j=0;j<2*m;j++){
            matInv[i][j]/=aux;
        }
        for(k=0;k<m;k++){
            if(k!=i){
                aux=matInv[k][i];
                for(j=0;j<2*m;j++){
                    matInv[k][j] -= matInv[i][j]*aux;
                }
            }
        }
    }
    for(i=0;i<m;i++){
        for(j=m;j<2*m;j++){
            escribir<<matInv[i][j]<<"\t";
        }
        escribir<<endl;
    }
}
void trazaDeter(){
    system("cls");
    cout<<"Desarrollado por osfprieto@hotmail.com\n"<<endl;
    cout<<"\t\tTraza y Determinante de una Matriz\n\n"<<endl;
    fstream leer, escribir;
}
void salir(){
    repite = false;
}
