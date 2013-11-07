#include<iostream>
using namespace std;

char substring(char A[10], char B[5]);

int main(){
    char A[10];
    char B[5];
    bool salir, encontrada;
    for(int i=0;i<24;i++){
        cin>>A>>B;
        cout<<substring(A, B)<<endl;
    }
    return 0;
}

char substring(char A[10], char B[5]){
    int cont, i, j;
    i=0;
    while(i<=5){
        j=0;
        while(B[j]==A[i+j] && B[j]!=0)
            j++;
        if(B[j]==0)
            return '1';
        i++;
    }
    return '0';
}
