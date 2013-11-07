#include<iostream>
using namespace std;
int main(){
    int i, cont, limit;
    cin>>limit;
    while(limit>0){
        i=1;
        cont=1;
        while(cont<limit){
            if(i%2==0 || i%3==0 || i%5==0)
                cont++;
            i++;
        }
        cout<<"The "<<limit<<"'th ugly number is "<<i<<'.'<<endl;
        cin>>limit;
    }
    return 0;
}
