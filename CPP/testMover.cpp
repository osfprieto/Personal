#include<iostream>
#include<conio.h>
using namespace std;
int main(){
    
    int x = 18, y = 10, i, j, moveTo = 0;
    
    bool lab[20][80];
    
    for(i=0;i<20;i++)
        for(j=0;j<80;j++)
            lab[i][j]=false;
    
    lab[5][10]=true;
    
    while(moveTo != 13){
        moveTo = getch();
        system("cls");
        if(moveTo == 224)
        {
            moveTo = getch();    
            if(moveTo == 75 &&  y > 0 && !( lab[x][y-1]))//izquierda
                 y--;
            if(moveTo == 77 &&  y < 79 && !( lab[x][y+1]))//derecha
                 y++;
            if(moveTo == 72 &&  x > 0 && !( lab[x-1][y]))//arriba
                 x--;
            if(moveTo == 80 &&  x < 19 && !( lab[x+1][y]))//abajo
                 x++;
        }
        for(i=0;i<20;i++){
            for(j=0;j<80;j++){
                if(lab[i][j])
                    cout<<((char)(219));
                else if(x==i && y==j)
                    cout<<'o';
                else
                    cout<<' ';
            }
        }
    }
}
