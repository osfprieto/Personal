#include<iostream>
using namespace std;

int main(){
    int caso, ttt, tth, tht, thh, htt, hth, hht, hhh, T, k, i;
    char cad[45];
    cin>>T;
    
    for(k=0;k<T;k++){
        cin>>caso;
        cin>>cad;
        ttt=0;
        tth=0;
        tht=0;
        thh=0;
        htt=0;
        hth=0;
        hht=0;
        hhh=0;
        for(i=0;i<=37;i++){
            if(cad[i]=='T' && cad[i+1]=='T' && cad[i+2]=='T')
                ttt++;
            else if(cad[i]=='T' && cad[i+1]=='T' && cad[i+2]=='H')
                tth++;
            else if(cad[i]=='T' && cad[i+1]=='H' && cad[i+2]=='T')
                tht++;
            else if(cad[i]=='T' && cad[i+1]=='H' && cad[i+2]=='H')
                thh++;
            else if(cad[i]=='H' && cad[i+1]=='T' && cad[i+2]=='T')
                htt++;
            else if(cad[i]=='H' && cad[i+1]=='T' && cad[i+2]=='H')
                hth++;
            else if(cad[i]=='H' && cad[i+1]=='H' && cad[i+2]=='T')
                hht++;
            else if(cad[i]=='H' && cad[i+1]=='H' && cad[i+2]=='H')
                hhh++;
        }
        
        cout<<caso<<' '<<ttt<<' '<<tth<<' '<<tht<<' '<<thh<<' '<<htt<<' '<<hth<<' '<<hht<<' '<<hhh<<endl;
    }
    
    return 0;
}
