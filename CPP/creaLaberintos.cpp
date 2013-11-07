#include <iostream>//editar para creador de mapas de laberintos de 1's y 0's en archivos de texto
#include <fstream>
#include <conio.h>
using namespace std;

int main(){
    system("color c");
    int s = 0, c = 92, x = 0, y = 0, i, j;
    printf("                 BIENVENIDO AL DIBUJA-LABERINTOS OSF ::D\n");
    printf("                 ------------------------------------\n");
    printf("                   Contactanos osfprieto@hotmail.com\n");
    printf("\n");
    printf("\n");
    printf("En realidad esta pantalla de entrada esta para dar las instrucciones\n");
    printf("de como se usa este pequenio programa:\n");
    printf("\n");
    printf("              para dibujar                           para no dibujar\n");
    printf("\n");
    printf("                 W                                           I\n");
    printf("                ASD                                         JKL\n");
    printf("\n");
    printf("Ahora entre el caracter que desee usar como cursor:\n");
    scanf("%c", &c);
    int mat[24][80];
    system("cls");
    for (i=0;i<24;i++){
        for (j=0;j<80;j++){
            mat[i][j] = 0;
        }
    }
    while(s != 13){
            s = getch();
            if (s == 'a'){
                  if(x>0){
                       mat[y][x]=1;
                       x--;
                  }
            }
            else if (s == 'd'){
                  if(x<79){
                       mat[y][x]=1;
                       x++;
                  }
            }
            else if (s == 'w'){
                  if(y>0){
                       mat[y][x]=1;
                       y--;
                  }
            }
            else if (s == 's'){
                  if(y<23){
                       mat[y][x]=1;
                       y++;
                  }
            }
            else if (s == 'j'){
                  if(x>0){
                       mat[y][x]=0;
                       x--;
                  }
            }
            else if (s == 'l'){
                  if(x<79){
                       mat[y][x]=0;
                       x++;
                  }
            }
            else if (s == 'i'){
                  if(y>0){
                       mat[y][x]=0;
                       y--;
                  }
            }
            else if (s == 'k'){
                  if(y<23){
                       mat[y][x]=0;
                       y++;
                  }
            }
            system("cls");
            for (i=0;i<24;i++){
                for (j=0;j<80;j++){
                    if (i==y && j==x){
                             printf("%c", c);
                    }
                    else if(mat[i][j] == 0){
                         printf(" ");
                    }
                    else{
                         printf("%c", 219);
                    }
                }
            }
    }
    
    fstream escribir;
    
    escribir.open("laberintoCreado.txt", ios::out);
    
    for(i=0;i<24;i++){
        for(j=0;j<80;j++){
            if(mat[i][j] == 1){
                escribir<<'1';
            }
            else if(mat[i][j] == 0){
                escribir<<'0';
            }
        }
        escribir<<endl;
    }
    
    escribir.close();
    
    return 0;
}
