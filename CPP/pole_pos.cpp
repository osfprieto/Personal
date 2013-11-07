#include<iostream>
using namespace std;

int main(){
    
    int n;
    cin>>n;
    while(n>0){
        
        int i, j;
        int cnum[n], npos[n], original[n];
        bool error=false;
        for(i=0;i<n;i++){
            cin>>cnum[i]>>npos[i];
            original[i] = -1;
        }
        for(i=0;i<n;i++)
            if(i+npos[i]<0 || i+npos[i]>=n)
                error = true;
            else if(original[i+npos[i]]!=-1)
                error = true;
            else
                original[i+npos[i]] = cnum[i];
            
        if(error)
            cout<<-1;
        else
            for(i=0;i<n;i++)
                cout<<original[i]<<((i<n-1)?" ":"");
        cout<<endl;
        cin>>n;
    }
    return 0;
}
