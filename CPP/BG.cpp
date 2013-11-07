#include<iostream>
using namespace std;

int main(){
    
    int n, m, temp;
    cin>>n>>m;
    
    while(!(n==-1 && m==-1)){
        if(m>n){
            temp = n;
            n = m;
            m = temp;
        }   
        cout<<((n+m)/(m+1))<<endl;
        cin>>n>>m;
    }
    return 0;
}
