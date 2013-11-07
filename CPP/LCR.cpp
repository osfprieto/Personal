#include<iostream>//#include<fstream>
#include<string>
using namespace std;

int main(){
    int n, player, center, i, j, cont, game=0;
    string rolls;
    bool exitWin, exitRolls;
    
    /*fstream cin, cout;
    
    cin.open("LCRIN.txt", ios::in);
    cout.open("LCROUT.txt", ios::out);*/
    
    cin>>n;
    
    while(n!=0){
        int chips[n];
        player = 0;
        center = 0;
        
        
        cin>>rolls;
        
        exitWin = false;
        exitRolls = false;
        
        for(i=0;i<n;i++)
            chips[i] = 3;
        
        i=0;
        
        while(rolls[i]!=0 && !exitWin && !exitRolls){
            if(chips[player]==1){
                if(rolls[i]=='L'){
                    chips[player]--;
                    chips[(player+1)%n]++;
                }
                else if(rolls[i]=='R'){
                    chips[player]--;
                    chips[(player-1+n)%n]++;
                }
                else if(rolls[i]=='C'){
                    chips[player]--;
                    center++;
                }
                i++;
            }
            else if(chips[player]==2){
                if(rolls[i+1]!=0){
                    if(rolls[i]=='L'){
                        chips[player]--;
                        chips[(player+1)%n]++;
                    }
                    else if(rolls[i]=='R'){
                        chips[player]--;
                        chips[(player-1+n)%n]++;
                    }
                    else if(rolls[i]=='C'){
                        chips[player]--;
                        center++;
                    }
                    i++;
                    if(rolls[i]=='L'){
                        chips[player]--;
                        chips[(player+1)%n]++;
                    }
                    else if(rolls[i]=='R'){
                        chips[player]--;
                        chips[(player-1+n)%n]++;
                    }
                    else if(rolls[i]=='C'){
                        chips[player]--;
                        center++;
                    }
                    i++;
                }
                else
                    exitRolls = true;
            }
            else if(chips[player]>=3){
                if(rolls[i+1]!=0 && rolls[i+2]!=0){
                    if(rolls[i]=='L'){
                        chips[player]--;
                        chips[(player+1)%n]++;
                    }
                    else if(rolls[i]=='R'){
                        chips[player]--;
                        chips[(player-1+n)%n]++;
                    }
                    else if(rolls[i]=='C'){
                        chips[player]--;
                        center++;
                    }
                    i++;
                    if(rolls[i]=='L'){
                        chips[player]--;
                        chips[(player+1)%n]++;
                    }
                    else if(rolls[i]=='R'){
                        chips[player]--;
                        chips[(player-1+n)%n]++;
                    }
                    else if(rolls[i]=='C'){
                        chips[player]--;
                        center++;
                    }
                    i++;
                    if(rolls[i]=='L'){
                        chips[player]--;
                        chips[(player+1)%n]++;
                    }
                    else if(rolls[i]=='R'){
                        chips[player]--;
                        chips[(player-1+n)%n]++;
                    }
                    else if(rolls[i]=='C'){
                        chips[player]--;
                        center++;
                    }
                    i++;
                }
                else
                    exitRolls = true;
            }
            
            cont=0;
            for(j=0;j<n;j++)
                if(chips[j]>0)
                    cont++;
            
            if(cont==1)
                exitWin = true;
            
            if(exitRolls)
                player--;
            
            player++;
            player%=n;
            
        }
        
        if(game>0)
            cout<<endl;
        
        if(exitWin){
            
            cout<<"Game "<<++game<<":"<<endl;
            for(i=0;i<n;i++)
                cout<<"Player "<<i+1<<":"<<chips[i]<<((chips[i]>0)?"(W)":"")<<endl;
        }
        else{
            while(chips[player]==0){
                player++;
                player%=n;
            }
            cout<<endl<<"Game "<<++game<<":"<<endl;
            for(i=0;i<n;i++)
                cout<<"Player "<<i+1<<":"<<chips[i]<<((i==player)?"(*)":"")<<endl;
        }
        cout<<"Center:"<<center;
        
        //out end
        
        cin>>n;
    }
    return 0;
}
