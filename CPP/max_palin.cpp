#include<iostream>
using namespace std;

int max_palin(string cadena,int size, int size_menos_n);

int main(){
    string cadena;
    cin>>cadena;
    
    int size;
    while(cadena[0]!='.'){
        size = 0;
        while(cadena[size]!=0)
            size++;
        cout<<max_palin(cadena, size, 0)<<endl;
        cin>>cadena;
    }
}

int max_palin(string cadena,int size, int size_menos_n){
    if(size-size_menos_n==1)
        return 1;
}
