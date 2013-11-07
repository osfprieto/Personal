#include<iostream>
using namespace std;
int main(){
    int i, t, k, n, p;
    cin>>t;
    for(i=0;i<t;i++){
        cin>>n>>k>>p;
        cout<<"Case "<<(i+1)<<": "<<(((k-1+p)%n))+1<<endl;
    }
    return 0;
}
