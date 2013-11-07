#include <stdio.h>
#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include <conio.h>
#include <windows.h>
//#include <C:/Dev-Cpp/include/c++/3.4.2/backward/iostream.h>
#include <mmsystem.h>
//#pragma comment(lib,"winmm.lib")
#include "logica.h"
#include "interfazGrafica.h"

#define C 80
#define F 20

int mover(struct Juego* juego)
{
     //system ("cls");
     //printf("\n\n\n\n");
    //char datos [][];
    int x=0, y=1, key, x1, y1, t1, t2, t3, puntaje=0, pasos=0;
    
    /*char color[9];
    sprintf (color, "color %x", juego->datos.color);
    system (color);*/
     t1=GetTickCount();
     
        key = getch();  

	x1=0;
	y1=1;
    while(key != 0xd  && juego->laberinto[18][79] != juego->datos.robot)
    {
        
        if(key == 0xe0)
        {
            key = getch();  
            if(key != 0xd  || juego->laberinto[18][80] != juego->datos.robot)  
            {
            if(key == 0x4b && x > 0)
            {
                   if (juego->laberinto[y][x-1]==32)
                   {
                      x1=x-1;
                      y1=y;
                      pasos++;
                      juego->laberinto[y1][x1]=juego->datos.robot;
                      juego->laberinto[y][x]=32;
                   }
            }
            
            if(key == 0x4d && x < C-1)
            {
                   if (juego->laberinto[y][x+1]==32)
                   {
                      x1=x+1;
                      y1=y;
                      pasos++;
                      juego->laberinto[y1][x1]=juego->datos.robot;
                      juego->laberinto[y][x]=32;
                   }
            }
        
            if(key == 0x48 && y > 0)
            {
                   if (juego->laberinto[y-1][x]==32)
                   {
                      x1=x;
                      y1=y-1;
                      pasos++;
                      juego->laberinto[y1][x1]=juego->datos.robot;
                      juego->laberinto[y][x]=32;
                   }
            }
            
            if(key == 0x50 && y < F-1)
            {
                   if (juego->laberinto[y+1][x]==32)
                   {
                      x1=x;
                      y1=y+1;
                      pasos++;
                      juego->laberinto[y1][x1]=juego->datos.robot;
                      juego->laberinto[y][x]=32;
                   }
            } 
            
        }
            if (key == 0xd)
            {
                     return 2;
            }
            if (juego->laberinto[18][80] == juego->datos.robot)
            {
                     return 1;
            }   
            system("cls");
            //textattr("%x", juego->datos.color);
            x=x1;
            y=y1;
            t2=GetTickCount();
            t3=t2-t1;
            puntaje = ((1000000-100*pasos)/t3)*100;

            /*for(i = 0; i < F; i++)
            {
                for(j = 0; j < C; j++)
                {
                    laberinto[i][j] = 32;
                }
            } */    
            //laberinto[y][x] = "%c", 115;
            juego->laberinto[F-1][C-1] = 0;//laberinto[F-1][C-1] = 0;
            printf ("                                  QUICK LOGIC                   \n");
            printf ("--------------------------------------------------------------------------------");
            printf ("Jugador: %s.                          Mayor puntaje: %d\n", juego->datos.perfil, juego->datos.puntaje);
            printf ("Tiempo: %d                            Puntaje: %d\n", t3, puntaje);
            
            //printf("%1600s", juego->laberinto);//printf("%1600s", laberinto);
			imprimirLab(juego);
        }

		key = getch();
    }
    if(juego->datos.puntaje<puntaje)
    {
        juego->datos.puntaje=puntaje;
    }
    if (key == 0xd)
    {
         return 2;
    }
    if (juego->laberinto[18][80] == juego->datos.robot)
    {
         return 1;
    }   
}

void aletorio(struct Juego* juego)
{	
	char a=219;
	int x,y,f,c;
      for (f=0;f<20;f++)
          {
			  juego->laberinto[f][0]=a;
            juego->laberinto[f][79]=a;
          }
      for (c=0;c<80;c++)
          {
            juego->laberinto[0][c]=a;
            juego->laberinto[19][c]=a;
          }
         
           
   x=rand();
   x=(x%19)+1;
  
        for (f=1;f<79;f++)
            {
                juego->laberinto[x][f]=a;   
            }
   y=rand();
   y=(y%19)+1;
       for (c=1;c<79;c++)
                {
                    juego->laberinto[y][c]=a;   
                }
  
 for (f=0;f<20;f++)
            {
                for (c=0;c<80;c++)
                    {
                    printf("%c", juego->laberinto[y][c]);
                   
                    }
            }

}
void sIn(int n)
{
     if (n==1)
     {
          PlaySound("manejoDeArchivos/jamesBond.wav", 0, SND_LOOP | SND_ASYNC);
     }
     else if (n==2)
     {
          //PlaySound();    
     }
     else if (n==3)
     {
          //PlaySound();
     }
     return;
}
void sOut()
{
     PlaySound(0, 0, SND_ASYNC);
     return;
}


