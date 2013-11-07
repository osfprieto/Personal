#include<iostream>
using namespace std;

int main(){
    int m, i, j, k;
    cin>>m;
    double mat[m][m+1];
    
    //recibir
    for(i=0;i<m;i++){
        for(j=0;j<m+1;j++){
            cin>>mat[i][j];
        }
    }
    
    //imprimir
    system("cls");
    for(i=0;i<m;i++){
        for(j=0;j<m+1;j++){
            cout<<mat[i][j]<<"\t";
        }
        cout<<endl;
    }
    
    system("pause");
    
    //reducir a 1's y 0's
    for(i=0;i<m;i++){
        cout<<endl<<"Paso "<<i<<endl<<endl;
        double aux=mat[i][i];
        for(j=0;j<m+1;j++){
            mat[i][j]/=aux;
        }
        for(k=0;k<m;k++){
            if(k!=i){
                aux=mat[k][i];
                for(j=0;j<m+1;j++){
                    mat[k][j] -= mat[i][j]*aux;
                }
            }
        }
        
        for(k=0;k<m;k++){
            for(j=0;j<m+1;j++){
                cout<<mat[k][j]<<"\t";
            }
            cout<<endl;
        }
        cout<<endl<<endl;
        system("pause");
    }
    
    //imprimir
    system("cls");
    cout<<"Resultado!"<<endl<<endl;
    for(i=0;i<m;i++){
        for(j=0;j<m+1;j++){
            cout<<mat[i][j]<<"\t";
        }
        cout<<endl;
    }
    
    system("pause");
    
    return 0;
}
