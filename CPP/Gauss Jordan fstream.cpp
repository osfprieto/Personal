#include<fstream>
using namespace std;

int main(){
    fstream leer, escribir;
    leer.open("inMatriz.txt", ios::in);
    escribir.open("outMatriz.txt", ios::out);
    int n, i, j, k;
    
    leer>>n;
    
    double pibote, factor;
    double mat[n][n+1];
    
    for(i=0;i<n;i++)
        for(j=0;j<n+1;j++)
            leer>>mat[i][j];
    
    for(i=0;i<n;i++){
        pibote = mat[i][i];
        for(j=0;j<n+1;j++)
            mat[i][j]/=pibote;
        for(k=0;k<n;k++)
            if(k!=i){
                factor = mat[k][i];
                for(j=i;j<n+1;j++)
                    mat[k][j]-=factor*mat[i][j];
            }
        
        for(k=0;k<n;k++){
            for(j=0;j<n+1;j++)
                escribir<<mat[k][j]<<'\t';
            escribir<<endl;
        }
        escribir<<endl<<endl<<i<<endl;
    }
    leer.close();
    escribir.close();
    return 0;
}
