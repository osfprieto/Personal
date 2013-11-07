#include<iostream>
using namespace std;

bool isPossible(int** obj, int n, int m);

int main(){
    
    int T, n, m, i, j, k;
    cin>>T;
    
    int** mat;
    
    for(k=1;k<=T;k++){
        cin>>n>>m;
        
        mat = new int*[n];
        
        for(i=0;i<n;i++){
            mat[i] = new int[m];
            for(j=0;j<m;j++)
                cin>>mat[i][j];
        }
        
        cout<<"Case #"<<k<<": "<<((isPossible(mat, n, m))?"YES":"NO")<<endl;
        
    }
    
}

bool isPossible(int** obj, int n, int m){
    int** buff;
    int** mat;
    
    int i, j;
    
    buff = new int*[n];
    mat = new int*[n];
        
    for(i=0;i<n;i++){
        buff[i] = new int[m];
        mat[i] = new int[m];
        for(j=0;j<m;j++){
            buff[i][j] = 100;
            mat[i][j] = 100;
        }
    }
    
    
    
    for(i=0;i<n;i++){
        delete[] buff;
        delete[] mat;
        delete[] obj;
    }
    
    return true;
}
