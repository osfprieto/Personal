#include<iostream>
using namespace std;

int main(){
    int t, i, j, m, k, temp;
    cin>>t;
    
    for(i=0;i<t;i++){
        cin>>m;
        int candy[m], candyA[m], candyB[m];
        
        for(j=0;j<m;j++)
            cin>>candy[m];
            
        for(j=0;j<m;j++){
            for(k=j;k<m;k++){
                if(candy[j]<candy[k]){
                    temp = candy[k];
                    candy[k] = candy[j];
                    candy[j] = temp;
                }
            }
        }
        
        for(j=0;j<m-1;j++)
            candyA[j] = candy[j];
        
        //////////////////////////////
        
    }
    return 0;
}
