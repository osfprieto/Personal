#include<iostream>
using namespace std;

int main(){
    
    int m, n, i, sum, j;
    
    cin>>m>>n;
    
    while(m>=0 && m<n){
        sum = 0;
        for(i=m;i<n+1;i++){
            j=i;
            while(j>0){
                if(j%10 == 0){
                    sum++;
                }
                j/=10;
            }
        }
        cout<<sum<<endl;
        cin>>m>>n;
    }
    
    return 0;
}
