#include<iostream>
using namespace std;
int funcion(long p);
int main(){
    int i, sum;
    long p, q;
    cin>>p>>q;
    while(p>=0 && q>0){
        sum = 0;
        for(i=p;i<=q;i++)
            sum+=funcion(i);
        cout<<sum<<endl;
        cin>>p>>q;
    }
    return 0;    
}
int funcion(long p){
    /*if(p == 0)
        return 0;
    while(p%10==0){
        p/=10;
    }
    return p%10;*/
    int n = p%10;
    if(n>0)
        return n;
    if(p == 0)
        return 0;
    return funcion(p/10);
}
