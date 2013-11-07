#include <stdio.h>
#include <stdlib.h>
#include "logica.h"
#include "archivos.h"
#include "interfazGrafica.h"


int main()
{
    struct Juego juego;
    struct Datos datos[10];
    
    int f=0, c=0, n=0, m=0, s=0; 
    system("color 8a");   
    n=cargarDatos(datos);
    sIn(1);
    pantallaEntrada(&juego);
    printf ("\t\t\t\tQUICK LOGIC\n\n\n\n");
    imprimirLab(&juego);
    //sOut();
    system("pause"); 
    while (s==0)
    {   
        //pantallaFinal(&juego, 1);
        //imprimirLab(&juego);
        s = menu1(&juego, n, datos);
        if (s==1)
        {
                 return;
        }
        /*lab(1, &juego);
        printf ("\t\t\t\tQUICK LOGIC\n\n\n\n");
        imprimirLab(&juego);
        system("pause");    
        printf ("laberinto1();");
        m = mover(&juego);
        pantallaFinal(&juego, m);
        printf ("\t\t\t\tQUICK LOGIC\n\n\n\n");
        imprimirLab(&juego);
        system("pause");    
        menu1(&juego, n, datos);
        system("PAUSE");*/	
    }    
    return 0;
}
