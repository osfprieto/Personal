#include<iostream>
#include<math.h>
using namespace std;

bool primoRaw(int n);
void iniciarArreglo(int primos[3540]);
bool primo(int n, int primos[3540]);

int main(){
    int primos[3540];
    iniciarArreglo(primos);
    
    int t, i, n, m, j;
    cin>>t;
    for(i=0;i<t;i++){
        cin>>m>>n;
        
        if(m<2)
            m=2;
        if(n<2)
            n=2;
        for(j=m;j<=n;j++){
            if(primo(j, primos))
                cout<<j<<endl;
        }
        cout<<endl;
    }
    
}

bool primo(int n, int primos[3540]){
    int j=0;
    while(n%primos[j]!=0 && primos[j]<=sqrt((double)(n)))
        j++;
    if(n%primos[j]==0 && n!=primos[j])
        return false;
    return true;
}

bool primoRaw(int n){
    for(int i=2; i<n;i++)
        if(n%i==0)
            return false;
    return true;
}

void iniciarArreglo(int primos[3540]){
    int j=0;
    for(int i=2;i<32000;i++){
        if(primoRaw(i)){
            primos[j]=i;
            j++;
        }
    }
}
