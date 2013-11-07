#define C 70
#define F 24
#include <iostream>
#include <conio.h>
using namespace std;
int main()
{
    int x=0, y=0, i,j, key=32;
    while(key != 0xd)
    {
        key = getch();
        if(key == 0xe0)
        {
            key = getch();    
            if(key == 0x4b && x > 0)
            {
                y--;
            }
            if(key == 0x4d && x < C-1)
            {
                y++;
            }
            if(key == 0x48 && y > 0)
            {
                x--;
            }
            if(key == 0x50 && y < F-1)
            {
                x++;
            }    
            system("cls");
            for(i=0;i<F;i++){
                for(j=0;j<C;j++){
                    if(x==i && y==j){
                        cout<<'o';
                    }
                    else{
                        cout<<' ';
                    }
                }
                cout<<endl;
            }
        }
    }
}
