#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <conio.h>
#include "logica.h"
#include "interfazGrafica.h"
#include "archivos.h"

void imprimirLab(struct Juego* juego)
{   
    /*char color[9];
    sprintf (color, "color %x", juego->datos.color);
    system (color);*/
        int f, c;   
           //system ("cls");
           //printf ("\t\t\t\tQUICK LOGIC\n\n\n\n");
          for (f=0;f<20;f++)
         {
              for (c=0;c<80;c++)
              {                  
				  if(juego->laberinto[f][c] == juego->datos.robot)
				  {
					  printf ("%c", juego->datos.robot);
				  }
				  else if (juego->laberinto[f][c]!=32)
                  {
                       printf ("%c", 219);
                  }
                  else
                  {
                      printf ("%c", 32);
                  }
              }
          }
	//printf ("%1600s", juego->laberinto); 
    //system("pause");
    //menu1(&juego,n,datos) ;             
}

int menu1(struct Juego* juego, int n, struct Datos* datos)
{
    /*char color[9];
    sprintf (color, "color %x", juego->datos.color);
    system (color);*/
    int seleccion=0;
    while (seleccion==0 || (seleccion != '1' && seleccion != '2' && seleccion != '3' && seleccion != '4' && seleccion != '5'))
    {
    system ("cls");
    printf ("\t\t\t\tQUICK LOGIC\n\n\n\n");
    printf ("1. crear perfil.\n");
    printf ("2. cargar perfil.\n");
    printf ("3. opciones.\n");
    printf ("4. mayores puntajes.\n");
    printf ("5. salir.\n\n");
    seleccion = getch();
    }
    if(seleccion == '1')
    {
         menuCrearPerfil(juego, n, datos);
         return 0;
    }
    else if (seleccion == '2')
    {
         menuCargarPerfil(juego, n, datos);
         return 0;
    }
    else if (seleccion=='3')
    {
         printf ("\n\t\tlo lamentamos, esta opcion aun no esta completa.\n");
         system ("pause");
        //menuOpciones(&juego, n, datos);
        return 0;
    }
    else if (seleccion=='4')
    {
        mayoresPuntajes(juego, n, datos);
        return 0;
    }
    else if (seleccion=='5')
    {
        int r=0;
        system ("cls");
        printf ("Esta seguro que desea salir del juego?\n1. si\n2. no");
        r=getch();
        if (r=='1')
        {
            system("exit");
        }
        else if (r=='2')
        {
            menu1(juego, n, datos);
        }
        return 1;
    }
    
    return 0; 
}
void menuCrearPerfil(struct Juego* juego, int n, struct Datos* datos)
{
    int a;
	FILE* archivo=0;
    system("cls");
    printf ("\t\t\t\tCrear perfil.\n\t\t\t\t-------------\n");
    printf ("Por favor entre su nombre de usuario:\n"); 
    scanf ("%s", juego->datos.perfil);
    system ("cls");
    printf ("Ahora entre el color de su robot junto con el color de sus paredes:\n\n\n");
    system ("color/?");
    scanf ("%x", &juego->datos.color);
    system ("cls");
    printf ("Ahora entre el codigo para el robot que desee usar en sus laberintos:\n\n");
    for (a=1;a<256;a++)
    {
        printf ("%d. %c\t", a, a);
    }  
    printf ("Si desea usar un robot que no este mostrado en la tabla puede ingresar este codigo si lo desea:\n");
    scanf ("%d", &juego->datos.robot);
    juego->datos.puntaje=0;
    
    archivo= fopen ("manejoDeArchivos/datosJugador.txt", "a");
    if (archivo!=0)
    {
        fprintf (archivo, "\n%s\t%d\t%d\t%hx", juego->datos.perfil, juego->datos.puntaje, juego->datos.robot, juego->datos.color);
        fclose(archivo);
    }
    else 
    {
        printf ("error abriendo y guardando datos del nuevo perfil.\n");
    }
    menuJuego(juego, n, datos);
}
void menuCargarPerfil(struct Juego* juego, int n, struct Datos* datos)
{
    char r, s=0;
    
    while (s==0)
    {
        system("cls");
        printf ("\t\t\t\tCargar perfil.\n\t\t\t\t--------------\n\n");
        for (r=0;r<n-1;r++)
        {
            printf (("%d. %s\n"), (r+1)%10, datos[r].perfil);
        }
    
        s = getch();
        if (s=='1')
        {
            strcpy(juego->datos.perfil, datos[0].perfil);
            juego->datos.color=datos[0].color;
            juego->datos.puntaje=datos[0].puntaje;
            juego->datos.robot=datos[0].robot;
        }
        else if (s=='2')
        {
            strcpy(juego->datos.perfil, datos[1].perfil);
            juego->datos.color=datos[1].color;
            juego->datos.puntaje=datos[1].puntaje;
            juego->datos.robot=datos[1].robot;
        }
        else if (s=='3')
        {
            strcpy(juego->datos.perfil, datos[2].perfil);
            juego->datos.color=datos[2].color;
            juego->datos.puntaje=datos[2].puntaje;
            juego->datos.robot=datos[2].robot;
        }
        else if (s=='4')
        {
            strcpy(juego->datos.perfil, datos[3].perfil);
            juego->datos.color=datos[3].color;
            juego->datos.puntaje=datos[3].puntaje;
            juego->datos.robot=datos[3].robot;
        }
        else if (s=='5')
        {
            strcpy(juego->datos.perfil, datos[4].perfil);
            juego->datos.color=datos[4].color;
            juego->datos.puntaje=datos[4].puntaje;
            juego->datos.robot=datos[4].robot;
        }
        else if (s=='6')
        {
            strcpy(juego->datos.perfil, datos[5].perfil);
            juego->datos.color=datos[5].color;
            juego->datos.puntaje=datos[5].puntaje;
            juego->datos.robot=datos[5].robot;
        }
        else if (s=='7')
        {
            strcpy(juego->datos.perfil, datos[6].perfil);
            juego->datos.color=datos[6].color;
            juego->datos.puntaje=datos[6].puntaje;
            juego->datos.robot=datos[6].robot;
        }
        else if (s=='8')
        {
            strcpy(juego->datos.perfil, datos[7].perfil);
            juego->datos.color=datos[7].color;
            juego->datos.puntaje=datos[7].puntaje;
            juego->datos.robot=datos[7].robot;
        }
        else if (s=='9')
        {
            strcpy(juego->datos.perfil, datos[8].perfil);
            juego->datos.color=datos[8].color;
            juego->datos.puntaje=datos[8].puntaje;
            juego->datos.robot=datos[8].robot;
        }
        else if (s=='0')
        {
            strcpy(juego->datos.perfil, datos[9].perfil);
            juego->datos.color=datos[9].color;
            juego->datos.puntaje=datos[9].puntaje;
            juego->datos.robot=datos[9].robot;
        }
        menuJuego(juego, n, datos);
        return;
    }
}
int menuOpciones(struct Juego* juego, int n, struct Datos* datos)
{
    
    return 1;
}
void mayoresPuntajes(struct Juego* juego, int n, struct Datos* datos)
{
    system ("cls");
    printf ("\t\t\t\tQUICK LOGIC\n\n\n\n"); 
    int a;
    for (a=0;a<10;a++)
    {
        printf ("%d. Jugador: %s\t\tPuntaje: %d\n", a+1, datos[a].perfil, datos[a].puntaje);
    }
    system ("pause");
    menu1(juego, n, datos);
}
/*void mayoresPuntajes(struct Juego* juego, int n, struct Datos* datos)
{
     int f=0;
     for (f=0;f<10;f++)
     {
         printf ("%d. %20s\t%d", f+1, datos[f].perfil, datos[f].puntaje);
     }
     menu1(&juego, n, datos);
}*/
void menuJuego(struct Juego* juego, int n, struct Datos* datos)
{
     int l=1, m=1;
     system ("cls");
     printf ("\t\t\t\tQUICK LOGIC\n\n\n\n");
     printf ("entre el numero del laberinto que desea resolver:\n");
     scanf ("%d", &l);
     lab(l, juego);
     system("cls");
     printf ("\t\t\t\tQUICK LOGIC\n\n\n\n");
     imprimirLab(juego);
     system("pause");
     m = mover(juego);
     pantallaFinal(juego, m);
     printf ("\t\t\t\tQUICK LOGIC\n\n\n\n");
     imprimirLab(juego);
     system("pause");
     return;
}
