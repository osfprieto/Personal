#include<iostream>
using namespace std;

int main(){
    
    int n, c;
    
    cin>>n;
    while(n!=0){
        c = 0;
        while(n!=72){
            if(n<72)
                n+=32;
            else
                n-=33;
            c++;
            if(c%1000==0)
                system("pause");
            cout<<'\t'<<n<<endl;
        }
        cout<<c<<endl;
        cin>>n;
    }
    return 0;
}
