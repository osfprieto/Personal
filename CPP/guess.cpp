#include<iostream>
using namespace std;

int mat[100][100];

int guess(int n, int s);
void inicio();

int main(){
    int n, s;
    inicio();
    cin>>n>>s;
    while(n!=0){
        cout<<guess(n, s)<<endl;
        cin>>n>>s;
    }
    
}

//programación dinámica

int guess(int n, int s){
    if(mat[n][s]!=-1)
        return mat[n][s];
    if(s==1)
        return n;
    if(n<s)
        return guess(n, n);
    else
        mat[n][s] = 1+guess(n/2, s-1);
    return mat[n][s];
}

void inicio(){
    for(int i=0;i<100;i++)
        for(int j=0;j<100;j++)
            mat[i][j] = -1;
}
