#include<iostream>
using namespace std;

int funcion(char arr[15], int n);

int main(){
    
    char arr[15], n=0;
    
    cin>>arr;
    
    while(arr[n]!=NULL){
        cout<<arr[n]<<endl;
        n++;
    }
    
    int ret = funcion(arr, n);
    
    if(ret==0){
        cout<<"La Palabra "<<arr<<" NO ES palindrome."<<endl;
    }
    else if(ret == 1){
        cout<<"La palabra "<<arr<<" ES palindrome."<<endl;
    }
    
    system("pause");
    
    return 0;
    
}

//0 para no palíndromo, 1 para palíndromo

int funcion(char arr[15], int n){
    
    int i;
    
    char arr2[15];
    
    for(i=0;i<n;i++){
        arr2[n-i]=arr[i];
    }
    
    for(i=0;i<n;i++){
        if(arr2[i]!=arr[i]){
            return 0;
        }
    }
    
    return 1;
}
