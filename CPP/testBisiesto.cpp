#include<iostream>
using namespace std;
int main(){
    int n;
    cin>>n;
    while(n>0){
        if((n % 4 == 0 && n % 100 != 0) || n % 400 == 0)
            cout<<"Lo es!"<<endl;
        else
            cout<<"No lo es!"<<endl;
        cin>>n;
    }
}
