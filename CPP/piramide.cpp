#include<stdlib.h>
#include<iostream.h>

int main()
{
    /*con los siguientes comandos, solo limpiolapantalla y le doy un color 
    distinto a l apantalla*/
  system ("cls");
  system("color 4a");
  printf ("PIRAMIDE\n\n");
  
  /*aqui declaro los componentes del ejercicio*/
  
  int n=0, l=0, a=0, b=0, r=0; 
  printf ("\nentre la cantidad de niveles que quiere que tenga su piramide:\n\n");
  l=scanf("%d", & n);
  
  /*con este if verifico que los datos estan entre los rangos*/ 
  
  if (l==1 && n>0 && n< 40)
  {
           
           /*1- con el primer ciclo imprimo los espacios de a linea por linea
           2- con el segundo ciclo imprimo los datos acendendentemente
           3- imprimo el numero del medio de la piramide
           4- con el tercer ciclo imprimo los datos descendentemente */
           
           for (a=1;a<=n;a++)
           {
               for (b=1;b<=n-a;b++)
               {
                   printf (" ");
               }
               for (b=a;b<2*a-1;b++)
               {
                   printf ("%d", b%10);
               }
               printf("%d", (2*a-1)%10);
               for (b=2*a-2;b>=a;b--)
               {
                   printf ("%d", b%10);
               }
               printf ("\n");
           }
  }
  else if (l!=1 || n<=0 || n>=40)//revisa que el tamaño de la pirámide este dentro de los límites de la pantalla
  {
     printf ("\n\n\edatos erroneos..."); 
  }
  printf ("\n\nentre 1 si desea repetir el procedimiento:\n");
  scanf ("%d", & r);
  if (r==1)
  {
           main();
  }
}
