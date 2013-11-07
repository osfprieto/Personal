#include<iostream>
using namespace std;

int main(){
    int n, i, k, j, X, O;
    char mat[3][3];
    cin>>n;
    for(k=0;k<n;k++){
        X=0;
        O=0;
        for(i=0;i<3;i++)
            cin>>mat[i];
        for(i=0;i<3;i++){
            for(j=0;j<3;j++){
                if(mat[i][j]=='X')
                    X++;
                if(mat[i][j]=='O')
                    O++;
            }
        }
        if(X-1==O || X==O)
            cout<<"yes"<<endl;
        else
            cout<<"no"<<endl;
    }
    return 0;
}
