#include<iostream>
#include<fstream>
#include<math.h>
using namespace std;

int mat[10][10];

int deter(int n, int k);
int cofac(int n, int j);

int main(){
    
    fstream leer, escribir;
    char in[20], out[20];
    int n, i, j;
    
    cout<<"Entre el nombre del archivo de la matriz de entrada:"<<endl;
    cin>>in;
    cout<<"Entre el nombre del archivo del dato salida:"<<endl;
    cin>>out;
    
    leer.open(in, ios::in);
    escribir.open(out, ios::out);
    
    leer>>n;
    
    for(i=0;i<n;i++){
        for(j=0;j<n;j++){
            leer>>mat[i][j];
        }
    }
    
    for(i=0;i<n;i++){
        escribir<<"|\t";
        for(j=0;j<n;j++){
            escribir<<mat[i][j]<<"\t";
        }
        escribir<<"|"<<endl;
    }
    
    escribir<<"\n\nDeterminante = "<<deter(n-1, n-1);
}

int deter(int n, int k){
    
    if(n==1){
        return mat[0][0]*mat[1][1]-mat[0][1]*mat[1][0];
    }
    else{
        int det=0, j;
        for(j=0;j<n;j++){
            if(j!=k){
                det+=mat[n][j]*cofac(n, j);
            }
        }
        return det;
    }
    
}
int cofac(int n, int j){
    
    double cof = pow(-1.0, n+j)*deter(n-1, j);
    int ret = (int)cof;
    return ret;
    
}
