#include<iostream>
#include<conio.h>

using namespace std;

void pro(bool mat[24][80], int n, int &x, int &y);
void imp(bool mat[24][80], char c);
void inic(bool mat[24][80]);

int main(){
    system("color 8a");
    int n=0, x=0, y=0;
    char c=111;
    bool mat[24][80];
    inic(mat);
    c = getch();
    n=getch();
    while(n!=13){
        pro(mat, n, x, y);
        imp(mat, c);
        n=getch();
    }
    return 0;
}

void pro(bool mat[24][80], int n, int &x, int &y){
    mat[x][y]=false;
    if(n==77 && y<79){
        y++;
    }
    else if(n==75 && y>0){
        y--;
    }
    else if(n==80 && x<23){
        x++;
    }
    else if(n==72 && x>0){
        x--;
    }
    else if(n==77 && y==79){
        y=0;
    }
    else if(n==75 && y==0){
        y=79;
    }
    else if(n==80 && x==23){
        x=0;
    }
    else if(n==72 && x==0){
        x=23;
    }
    mat[x][y]=true;
    return;
}
void imp(bool mat[24][80], char c){
    system("cls");
    int i, j;
    for(i=0;i<24;i++){
        for(j=0;j<80;j++){
            if(mat[i][j])
                cout<<c;
            else
                cout<<' ';
        }
    }
    return;
}
void inic(bool mat[24][80]){
    int i, j;
    for(i=0;i<24;i++){
        for(j=0;j<80;j++){
            mat[i][j]=false;
        }
    }
    mat[0][0]=true;
    return;
}
