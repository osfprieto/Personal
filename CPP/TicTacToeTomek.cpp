#include<fstream>
using namespace std;

bool xWon(char mat[4][4]);
bool oWon(char mat[4][4]);
bool hasNoPoints(char mat[4][4]);

int main(){
    int T;
    char mat[4][4];
    
    fstream cin, cout;
    cin.open("TicTacToeTomek.in", ios::in);
    cout.open("TicTacToeTomek.out", ios::out);
    
    cin>>T;
    int i, k;
    
    for(k=1;k<=T;k++){
        for(i=0;i<4;i++)
            cin>>mat[i];
        
        cout<<"Case #"<<k<<": ";
        
        if(xWon(mat))
            cout<<"X won";
        else if(oWon(mat))
            cout<<"O won";
        else if(hasNoPoints(mat))
            cout<<"Draw";
        else
            cout<<"Game has  not completed";
        
        if(k<T)
            cout<<endl;
    }
    cin.close();
    cout.close();
}

bool xWon(char mat[4][4]){
    int i, j, count;
    char P='X', T='T';
    for(i=0;i<4;i++){
        count=0;
        for(j=0;j<4;j++)
            if(mat[i][j]==P || mat[i][j]==T)
                count++;
        if(count==4)
            return true;
            
        count=0;
        for(j=0;j<4;j++)
            if(mat[j][i]==P || mat[j][i]==T)
                count++;
        if(count==4)
            return true;
    }
    
    count=0;
    for(i=0;i<4;i++)
        if(mat[i][i]==P || mat[i][i]==T)
            count++;
    if(count==4)
        return true;
        
    count=0;
    for(i=0;i<4;i++)
        if(mat[i][3-i]==P || mat[i][3-i]==T)
            count++;
    if(count==4)
        return true;
        
    return false;
}

bool oWon(char mat[4][4]){
    int i, j, count;
    char P='O', T='T';
    for(i=0;i<4;i++){
        count=0;
        for(j=0;j<4;j++)
            if(mat[i][j]==P || mat[i][j]==T)
                count++;
        if(count==4)
            return true;
        count=0;
        for(j=0;j<4;j++)
            if(mat[j][i]==P || mat[j][i]==T)
                count++;
        if(count==4)
            return true;
    }
    count=0;
    for(i=0;i<4;i++)
        if(mat[i][i]==P || mat[i][i]==T)
            count++;
    if(count==4)
        return true;
    count=0;
    for(i=0;i<4;i++)
        if(mat[i][3-i]==P || mat[i][3-i]==T)
            count++;
    if(count==4)
        return true;
    return false;
}

bool hasNoPoints(char mat[4][4]){
    for(int i=0;i<4;i++)
        for(int j=0;j<4;j++)
            if(mat[i][j]=='.')
                return false;
    return true;
}
