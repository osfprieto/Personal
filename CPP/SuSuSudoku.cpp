//#include<iostream>//
#include<fstream>
using namespace std;

bool lleno(int mat[9][9]);
void llenar(int mat[9][9], int i, int j);

int main(){
    
    fstream cin, cout;
    
    cin.open("SuSuSudokuIN.txt", ios::in);
    cout.open("SuSuSudokuOUT.txt", ios::out);
    
    int t, k, i, j, cont;
    long line;
    int mat[9][9];
    bool iniciado = false;
    cin>>t;
    
    for(k=0;k<t;k++){
        for(i=0;i<9;i++){
            cin>>line;
            for(j=8;j>=0;j--){
                mat[i][j] = line%10;
                line/=10;
            }
        }
        cont = 0;
        while(cont<5 && !lleno(mat)){
            for(i=0;i<9;i++)
                for(j=0;j<9;j++)
                    if(mat[i][j]==0)
                        llenar(mat, i, j);
            cont++;
        }
        if(iniciado)
            cout<<endl;
        else
            iniciado = true;
        if(lleno(mat))
            for(i=0;i<9;i++){
                for(j=0;j<9;j++)
                    cout<<mat[i][j];
                cout<<endl;
            }
        else
            cout<<"Could not complete this grid."<<endl;
        
    }
    
    cin.close();
    cout.close();
    
    return 0;
}

void llenar(int mat[9][9], int i, int j){
    int k, cont=0, index;
    bool check[10];
    for(k=0;k<10;k++)
        check[k] = false;
    
    for(k=0;k<9;k++){
        check[mat[i][k]] = true;
        check[mat[k][j]] = true;
        check[mat[3*(i/3)+k/3][3*(j/3)+k%3]] = true;
    }
    
    for(k=1;k<10;k++)
        if(!check[k]){
            cont++;
            index = k;
        }
    
    if(cont==1)
        mat[i][j] = index;
    
}

bool lleno(int mat[9][9]){
    int i, j;
    for(i=0;i<9;i++)
        for(j=0;j<9;j++)
            if(mat[i][j]==0)
                return false;
    return true;
}
