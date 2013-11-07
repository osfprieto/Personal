#include<iostream>
using namespace std;

bool distinct(int n);

int main(){
    int n=1;
    while(n!=0)
    {
        cin>>n;
        n++;
        while(!distinct(n))
            n++;
        cout<<n<<endl;
    }
    
    return 0;
}

bool distinct(int n){
    int aparece[10];
    int i;
    for(i=0;i<10;i++)
        aparece[i] = 0;
    while(n>0){
        aparece[n%10]++;
        n/=10;
    }
    for(i=0;i<10;i++){
        if(aparece[i]>=2)
            return false;
    }
    return true;
}
