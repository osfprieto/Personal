//recibir por líneas :S pasamos a java

#include<iostream>
using namespace std;

int prime[10];

int main(){
    /*
    int in, temp=1;
    
    cin>>in;
    
    while(in>0){
        temp*=in;
        
        
        
    }*/
    
    bool flag;
    int cont=0;
    
    for(int i=2;i<1000;i++){
        flag=false;
        for(int j=2;j<i && !flag;j++){
            if(i%j==0)
                flag=true;
        }
        if(!flag){
            cont++;
            cout<<i<<endl;
        }
    }
    cout<<"Conteo: "<<cont<<endl;
    system("pause");
    return 0;
}
