#include<iostream>
#include<math.h>
#define PI 3,14159
using namespace std;

double an(int n);

int main(){
    
    int i;
    double c;
    
    for(i=1;i<21;i++){
        c = an(i);
        cout<<i<<"------------"<<c<<endl;
    }
    system("pause");
    return 0;
}

double an(int n){
    
    if(n <= 1){
        return 1;
    }
    else{
        return an(n-1)*(2*n-1);
    }
    
}
