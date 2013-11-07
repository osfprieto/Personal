#include<iostream>
using namespace std;

int powerMod(int a, int b, int n);

int main(){
    int a, b, n;
    
    cout<<"Ingrese A:\n";
    cin>>a;
    cout<<"Ingrese B:\n";
    cin>>b;
    cout<<"Ingrese N:\n";
    cin>>n;
    
    while(a!=0){
        system("cls");
        
        cout<<"A^B mod N = "<<powerMod(a, b, n)<<endl;
        
        system("pause");
        
        system("cls");
        cout<<"Ingrese A:\n";
        cin>>a;
        cout<<"Ingrese B:\n";
        cin>>b;
        cout<<"Ingrese N:\n";
    }
    
    return 0;
}

int powerMod(int a, int b, int n){
    int result=1;
    while(b>0){
        if(b%2==1){
            result*=a;
            result%=n;
        }
        b = b>>1;
        a*=a;
    }
    return result;
}
