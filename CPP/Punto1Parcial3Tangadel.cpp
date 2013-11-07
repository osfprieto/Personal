#include<iostream>
using namespace std;

struct alo{
    int a;
    int b;
};

void total(int &a, int b){
    int c;
    a+=2;
    b++;
    c = (a+b)*2;
    cout<<c<<" - ";
}

int main(){
    alo x;
    cin>>x.a;
    cin>>x.b;
    for(int i=0;i<3;i++){
        total(x.a, x.b);
        cout<<x.a<<" - ";
        cout<<x.b<<endl;
    }
    system("pause");
    return 0;
}
