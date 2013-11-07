#include<iostream>
using namespace std;

int main(){
    
    int at, def, i, j;
    bool off;
    
    cin>>at>>def;
    
    while(at!=0 || def!=0){
        off = false;
        int AT[at], DEF[def], CONT[at];
        
        for(i=0;i<at;i++){
            cin>>AT[i];
            CONT[i]=0;
        }
        
        for(i=0;i<def;i++)
            cin>>DEF[i];
        
        for(i=0;i<at;i++){
            for(j=0;j<def;j++){
                if(DEF[j]<=AT[i])
                    CONT[i]++;
            }
        }
        
        for(i=0;i<at;i++){
            if(CONT[i]<2)
                off = true;
        }
        
        cout<<((off)?'Y':'N')<<endl;
        
        cin>>at>>def;
    }
    
    return 0;
}
