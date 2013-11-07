#include<iostream>
using namespace std;

bool expresable(int n);

int main(){
    
    int n = 1000000;
    int raiz = 1000;
    
    int i, j;
    bool primo[n];
    for(i=0;i<n;i++)
        primo[i] = true;
    primo[0] = false;
    primo[1] = false;
    
    for(i=2;i<=raiz;i++)
        if(primo[i])
            for(j=2*i;j<n;j+=i)
                primo[j] = false;
            
    int l, u, x, y;
    
    cin>>l>>u;
    
    while(l!=-1 || u!=-1){
        
        if(l<0)
            l=0;
        if(u<0)
            u=0;
        
        x=0;
        y=0;
        
        for(i=l;i<=u;i++)
            if(primo[i]){
                x++;
                if(expresable(i))
                    y++;
            }
        cout<<l<<' '<<u<<' '<<x<<' '<<y<<endl;
        cin>>l>>u;
    }

    return 0;
}

bool expresable(int n){
    return n%4==1;
}
