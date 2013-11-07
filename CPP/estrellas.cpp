#include<iostream>
#include<stdlib.h>
#include<time.h>
using namespace std;

bool isStar(int mat[40][20],int i,int j);

int main(){
    
    srand(time(NULL));
    int n, mat[40][20], i, j;
    bool img[40][20];
    
    for(i=0;i<40;i++)
        for(j=0;j<20;j++){
            mat[i][j] = rand()%100;
            img[i][j] = false;
        }
    
    for(i=1;i<39;i++)
        for(j=1;j<19;j++)
            img[i][j] = isStar(mat, i, j);
    
    for(i=0;i<40;i++){
        for(j=0;j<20;j++)
            cout<<((img[i][j])?'*':' ');
        cout<<endl;
    }
    system("pause");
    return 0;
}

bool isStar(int mat[40][20],int i,int j){
    int sum=0;
    
    sum+=mat[i+1][j-1];
    sum+=mat[i+1][j];
    sum+=mat[i+1][j+1];
    sum+=mat[i][j+1];
    sum+=mat[i-1][j+1];
    sum+=mat[i-1][j];
    sum+=mat[i-1][j-1];
    sum+=mat[i][j-1];
    
    sum/=8;
    
    return (mat[i][j]+sum)>=60;
}
