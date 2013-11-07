#include <stdio.h>
#include <stdlib.h>
#include <stdio.h>
#include <conio.h>
#include "logica.h"

int pantallaEntrada(struct Juego* juego)
{
    int f, c;
    FILE* pantalla = fopen("grafico/pantallaEntrada.txt", "r");
    if (pantalla!=0)
    {
          for (f=0;f<20;f++)
          {
              fread(juego->laberinto[f], 1, 81, pantalla);
          }
	  for (f=0;f<20;f++)
	  {
		for (c=0;c<80;c++)
		{
			if (juego->laberinto[f][c]!=32)
			{
				juego->laberinto[f][c]=219;
			}
		}
	  }   
    }
    else 
    {
         printf ("no se puede leer la pantalla inicial");
    }
    fclose(pantalla); 

}

int lab(int n, struct Juego* juego)
{
	int f, c;
     FILE *archivo;
    if (n==1)
    {
       archivo = fopen("grafico/laberinto1.txt", "r");
       if (archivo!=0)
       {
       for (f=0;f<20;f++)
        {
                   fread(juego->laberinto[f], 1, 81, archivo);
        }
	for (f=0;f<20;f++)
	  {
		for (c=0;c<80;c++)
		{
			if (juego->laberinto[f][c]!=32)
			{
				juego->laberinto[f][c]=219;
			}
		}
	  }
    
    fclose (archivo);
    }
    else
    {
            printf ("error abriendo el laberinto 1.");
    }          
    }/*hasta aquí va el código del laberinto 1*/
    
 else if (n==2)
      {
               archivo = fopen("grafico/laberinto2.txt", "r");
       if (archivo!=0)
       {
       for (f=0;f<20;f++)
        {
                   fread(juego->laberinto[f], 1, 81, archivo);
        }
	for (f=0;f<20;f++)
	  {
		for (c=0;c<80;c++)
		{
			if (juego->laberinto[f][c]!=32)
			{
				juego->laberinto[f][c]=219;
			}
		}
	  }
    
    fclose (archivo);
    }
    else
    {
            printf ("error abriendo el laberinto 2.");
    }          
    }/*hasta aquí va el laberinto 2*/
    if (n==3)
    {
       archivo = fopen("grafico/laberinto3.txt", "r");
       if (archivo!=0)
       {
       for (f=0;f<20;f++)
        {
                   fread(juego->laberinto[f], 1, 81, archivo);
        }
	for (f=0;f<20;f++)
	  {
		for (c=0;c<80;c++)
		{
			if (juego->laberinto[f][c]!=32)
			{
				juego->laberinto[f][c]=219;
			}
		}
	  }
    
    fclose (archivo);
    }
    else
    {
            printf ("error abriendo el laberinto 3.");
    }          
    }/*hasta aquí va el código del laberinto 3*/
if (n==4)
    {
       archivo = fopen("grafico/laberinto4.txt", "r");
       if (archivo!=0)
       {
       for (f=0;f<20;f++)
        {
                   fread(juego->laberinto[f], 1, 81, archivo);
        }
	for (f=0;f<20;f++)
	  {
		for (c=0;c<80;c++)
		{
			if (juego->laberinto[f][c]!=32)
			{
				juego->laberinto[f][c]=219;
			}
		}
	  }
    
    fclose (archivo);
    }
    else
    {
            printf ("error abriendo el laberinto 4.");
    }          
    }/*hasta aquí va el código del laberinto 4*/
if (n==5)
    {
       archivo = fopen("grafico/laberinto5.txt", "r");
       if (archivo!=0)
       {
       for (f=0;f<20;f++)
        {
                   fread(juego->laberinto[f], 1, 81, archivo);
        }
	for (f=0;f<20;f++)
	  {
		for (c=0;c<80;c++)
		{
			if (juego->laberinto[f][c]!=32)
			{
				juego->laberinto[f][c]=219;
			}
		}
	  }
    
    fclose (archivo);
    }
    else
    {
            printf ("error abriendo el laberinto 5.");
    }          
    }/*hasta aquí va el código del laberinto 5*/

}
int cargarDatos(struct Datos* datos)
{
    FILE* archivo;
    archivo = fopen("manejoDeArchivos/datosJugador.txt", "r");
    if (archivo!=0)
    {
		
                   int f=0, c=0;
                   
				   while( fscanf (archivo, "%s\t%d\t%d\t%x\n", &datos[f].perfil, &datos[f].puntaje, & datos[f].robot, & datos[f].color) == 4)
				   {
					   f++;
				   }

                   fclose (archivo);

				   return f;
    }
    else
    {
        return -1;
    }   
}
int pantallaFinal(struct Juego* juego, int m)
{
    int f, c;
    if (m==1)
    {
             FILE* archivo = fopen ("grafico/pantallaGanadora.txt", "r");
             if (archivo != 0)
             {
                for (f=0;f<20;f++)
                {
                                  fread(juego->laberinto[f], 1, 81, archivo);
                }
	            for (f=0;f<20;f++)
	            {
		                          for (c=0;c<80;c++)
		                          {
			                                        if (juego->laberinto[f][c]!=32)
                                                    {
				                                                                   juego->laberinto[f][c]=219;
                                                    }
                                   }
               }                            
             }
             else
             {
                 //system ("cls");
                 printf ("error abriendo la pantalla ganadora\n");
             } 
    }
    else if (m==2)
    {
             FILE* archivo = fopen ("grafico/pantallaEscape.txt", "r");
             if (archivo != 0)
             {
                for (f=0;f<20;f++)
                {
                                  fread(juego->laberinto[f], 1, 81, archivo);
                }
	            for (f=0;f<20;f++)
	            {
		                          for (c=0;c<80;c++)
		                          {
			                                        if (juego->laberinto[f][c]!=32)
                                                    {
				                                                                   juego->laberinto[f][c]=219;
                                                    }
                                   }
               }                            
             }
             else
             {
                 system ("cls");
                 printf ("error abriendo la pantalla escape");
             }          
    }
}
