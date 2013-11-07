#include<iostream>
using namespace std;

int main(){
    int t, c, d, s, i, j, k, l, m, cont;
    //char base[8] = {Q, W, E, R, A, S, D, F};
    char **conjugation;
    char **destruction;
    char *data;
    
    cin>>t;
    
    for(i=0;i<t;i++){

        cin>>c;
        conjugation = new char*[c];
        for(j=0;j<c;j++){
            conjugation[j] = new char[3];
            cin>>conjugation[j];
        }
        
        cin>>d;
        destruction = new char*[d];
        for(j=0;j<d;j++){
            destruction[j] = new char[2];
            cin>>destruction[j];
        }
        
        cin>>s;
        cin>>d;
        data = new char[s];
        cin>>data;
        
        cont=s;
        
        while(cont>0){
            for(j=0;j<s;j++){
                
                for(k=0;k<c;k++){
                    if(data[j]==conjugation[k][0]){
                        if(j==0){
                            if(data[j+1]==conjugation[k][1]){
                                data[j]=conjugation[k][2];
                                cont--;
                                s--;
                                for(l=j+1;l<cont;l++){
                                    data[l] = data[l+1];
                                }
                            }
                        }
                        else if(j==s-1){
                            if(data[j-1]==conjugation[k][1]){
                                cont--;
                                s--;
                                data[j-1]=conjugation[k][2];
                            }
                        }
                        else{
                            if(data[j+1]==conjugation[k][1]){
                                data[j]=conjugation[k][2];
                                cont--;
                                s--;
                                for(l=j+1;l<cont;l++){
                                    data[l] = data[l+1];
                                }
                            }
                            else if(data[j-1]==conjugation[k][1]){
                                cont--;
                                s--;
                                data[j-1]=conjugation[k][2];
                                for(l=j+1;l<cont;l++){
                                    data[l] = data[l+1];
                                }
                            }
                        }
                    }
                }
                
                for(k=0;k<d;k++){
                    if(data[j]==destruction[k][0]){
                        for(l=0;l<s;l++){
                            if(data[l]==destruction[k][1]){
                                cont-=j;
                                s-=j;
                                for(m=0;m<cont;m++){
                                    data[m] = data[m+j];
                                }
                            }
                        }
                    }
                }
                
            }
            cont--;
        }
        
        cout<<"Case #"<<(i+1)<<": "<<'[';
        for(j=0;j<s-1;j++)
            cout<<data[j]<<", ";
        cout<<data[s-1]<<']'<<endl;
        
        system("pause");
        
        for(j=0;j<c;j++){
            delete[] conjugation[j];
        }
        delete[] conjugation;
        
        for(j=0;j<d;j++){
            delete[] destruction[j];
        }
        delete[] destruction;
        
        delete[] data;
    }
}
