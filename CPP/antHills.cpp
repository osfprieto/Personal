#include<iostream>
using namespace std;

#define INF 2000000000

int main(){
    int n;
    cin>>n;
    while(n>0){
        int i, j, k, temp;
        long long dist[n][n], buff[n][n];
        
        for(i=0;i<n;i++)
            for(j=0;j<n;j++)
                if(i==j)
                    dist[i][j] = 0;
                else
                    dist[i][j] = INF;
        
        for(i=0;i<n-1;i++){
            cin>>j>>temp;
            dist[i][j] = temp;
            dist[j][i] = temp;
        }
        
        for(k=0;k<n;k++){
            for(i=0;i<n;i++)
                for(j=0;j<n;j++)
                    buff[i][j] = dist[i][j];
            
            for(i=0;i<n;i++)
                for(j=0;j<n;j++)
                    dist[i][j] = (buff[i][j]<buff[i][k]+buff[k][j])?buff[i][j]:buff[i][k]+buff[k][j];
            
            for(i=0;i<n;i++)
                for(j=0;j<n;j++)
                    dist[i][j] = buff[i][j];
        }
        
        cin>>n;
        
        for(k=0;k<n;k++){
            cin>>i>>j;
            cout<<dist[i][j]<<(k<n-1)?" ":"";
        }
        cout<<endl;
        cin>>n;
    }
    return 0;
}
