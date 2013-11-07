#include<conio.h>
#include<iostream>
using namespace std;

int main(){
    char c;
    c = getch();
    
    while(c!=13){//el ciclo sale al presionar la tecla intro
        
        if(c==-32)
            c = getch();//no hace nada porque lo que se le entró fué una flecha
        else if(c==8){//backspace
            cout<<c;//se devuelve
            cout<<' ';//imprime un espacio para dar la alusión a borrado
            cout<<c;//se vuelve a devolver para dejar el espacio a continuación
        }
        else if(c>=32 && c<126)//caracteres
            cout<<(char)(c);//output
        
        c=getch();//el comando de entrada para el siguiente caracter
    }
    
    return 0;
}
