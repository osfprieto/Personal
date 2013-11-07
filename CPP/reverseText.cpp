#include<iostream>
#include<fstream>
using namespace std;

int main(){
    
    int n, s, i, j;
    cin>>n;
    
    char str[71];
    
    for(i=0;i<=n;i++){
        cin.getline(str, 70, '\n');
        s=0;
        while(str[s]!=0){
            s++;
        }
        for(j=s-1;j>=0;j--){
            cout<<str[j];
        }
        cout<<endl;
    }
    return 0;
}
