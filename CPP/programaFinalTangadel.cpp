#include<iostream>
#include<string.h>
#include<conio.h>
using namespace std;

int main(){
    int n=4, i, j, k;
    string temp;
    int c=2;
    float tempNota;
    char letra;
    
    string nombres[n];
    float notas[n];
    
    cout<<"Varios programas a su eleccion"<<endl;
    cout<<endl;
    while(c>1){ cout<<"a para ingresar el nombre de sus estudiantes sin espacio entre el nombre y el apellido"<<endl;
    cout<<"b para ver los nombres en orden de lista"<<endl;
    cout<<"c para ver las calificaciones ordenadas de menor a mayor"<<endl;
    cout<<"d para sacar el promedio de sus estudiantes hasta la fecha en el semestre"<<endl;
    cout<<"e para la matriz magica"<<endl;
  cin>>letra;
             switch(letra){case 'a': {//ingresar los 4 estudiantes con sus notas
                                system("cls");
    for(i=0;i<n;i++){
        cout<<"nombre"<<(i+1)<<"=";
        getline(cin, nombres[i], '\n');
        notas[i]=-1;
        while(notas[i]<0 || notas[i]>5.0){
                         cout<<"Nota parcial "<<nombres[i]<<": ";
            cin>>notas[i];
            if(notas[i]<0 || notas[i]>5.0){
                cout<<"Nota Ingresada no valida, ingresela de nuevo:"<<endl;
            }
        }
    }
    break;}
    case 'b': {//ordena alfabeticamente
    cout<<"Analizando y ordenando..."<<endl;
    
    for(i=0;i<n;i++){
        for(j=i;j<n;j++){
            if((nombres[i].compare(nombres[j]))>0){
                
                temp = nombres[i];
                nombres[i] = nombres[j];
                nombres[j] = temp;
                
                tempNota = notas[i];
                notas[i] = notas[j];
                notas[j] = tempNota;
            }
        }
    }
    
    
    system("cls");
    
    cout<<"Los nombres ordenados y las notas son:"<<endl;
    
    for(i=0;i<n;i++){
        cout<<(i+1)<<". "<<nombres[i]<<'\t'<<notas[i]<<endl;
    }
    break;}
    case 'c': {//ordena de menor a mayor
         system("cls");
         system("color 3F");
    for(i=0;i<n;i++){
        for(j=i;j<n;j++){
            if(notas[i]>notas[j]){
                tempNota = notas[i];
                notas[i]= notas[j];
                notas[j] = tempNota;
                temp = nombres[i];
                nombres[i] = nombres[j];
                nombres[j] = temp;
            }
        }
    }
    
    
    cout<<"Las nombres con sus notas rodenandas son:"<<endl;
    
    for(i=0;i<n;i++){
        cout<<(i+1)<<". "<<nombres[i]<<'\t'<<notas[i]<<endl;
}
break;}
case 'd': { //promedio
     system("cls");
     system("color 1F");
    int n=0, i, j, k, temp;
    float tempNota;
    float templab;
    float tempqui;
    float temptall;
    float temppro;
    
    while(n<=0){
        cout<<"Ingrese el numero de estudiantes que va a ordenar:"<<endl;
        cin>>n;
        if(n<=0){
            cout<<"Numero ingresado no valido."<<endl;
            system("pause");
            system("cls");
        }
    }
    
    char nombres[n][30];
    float notas[n];
    float lab[n];
    float tall[n];
    float pro[n];
    cout<<"Entre los nombres sin espacio entre el nombre de pila y el apellido, seguidos de las notas:"<<endl;
    cout<<"Primero sera la nota del parcial, segundo la nota del laboratorio, tercero la nota de quices y finalmente la nota de talleres"<<endl;
    
    for(i=0;i<n;i++){
        cout<<"Ingrese el nombre de su estudiante No."<<(i+1)<<".:";
        cin>>nombres[i];
        notas[i]=-1;
        lab[i]=-1;
        tall[i]=-1;
        while(notas[i]<0 || notas[i]>5.0){
            cout<<"ingrese la nota del parcial:  ";
            cin>>notas[i];
            if(notas[i]<0 || notas[i]>5.0){
                cout<<"Nota Ingresada no valida, ingresela de nuevo:"<<endl;
            }
        }
        while (lab[i]<0|| lab[i]>5.0){
              cout<<"Ingrese la nota de laboratorio:  ";
              cin>>lab[i];
              if (notas[i]<0 || notas[i]>5.0){
                cout<<"nota de laboratorio ingresada no valida, ingresela de nuevo"<<endl;
                }
        }
        while (tall[i]<0|| tall[i]>5.0){
              cout<<"Ingrese la nota del taller:  ";
              cin>>tall[i];
              if (tall[i]<0 || tall[i]>5.0){
                cout<<"nota de taller ingresada no valida, ingresela de nuevo"<<endl;
                }
        }              
    }
    
    for(i=0; i<=n-1; i++){
             pro[i]=(lab[i]*0.15+tall[i]*0.15+notas[i]*0.20)*2;
             }
    cout<<endl<<"Analizando y ordenando..."<<endl;
    
    for(i=0;i<n;i++){
        for(j=i;j<n;j++){
            if(strcmp(nombres[i], nombres[j])>0){
                for(k=0;k<30;k++){
                    temp = nombres[i][k];
                    nombres[i][k] = nombres[j][k];
                    nombres[j][k] = temp;
                }
                tempNota = notas[i];
                notas[i] = notas[j];
                notas[j] = tempNota;
                temppro= pro[i];
                pro[i]= pro[j];
                pro[j]= temppro;
            }
        }
    }
    
    
    
    cout<<"Los nombres ordenados con su promedio del semestre son:"<<endl;
    
    for(i=0;i<n;i++){
        cout<<(i+1)<<". "<<nombres[i]<<'\t'<<pro[i]<<endl;
    }

break;}
case 'e': {//matriz mágica
     system("cls");
     system("color 1F");
int n;
int m;
int cont=0;
int contdos=0;
int sumauno;
int sumados=0;
int sumatres=0;
int sumacuatro=0;
int k=0;
int l;
int A=0;
cout<<"Ingrese el numero de filas y columnas que quiere que sea su matriz magica"<<endl;
cin>>n;
m=n;
l=n*2+2;
       int magica[n][m];
       int arreglo[l];
       int arreglodos[l+1];
       int arr[n*n];
       int i;
       int j;
       int w;
       int x;
       int y;
       bool ver=true;
       cout<<"Ingrese los numero de sus matriz. Ordenelos primero la fila 1, luego la fila 2, hasta fila n. Recuerde que los numeros no se deben repetir"<<endl;
       for(i=0; i<=n-1; i++)
       {for(j=0;j<=m-1;j++){
            magica[i][j]=-1;
                    }
                    }//inicializa matriz en -1
       for(k=0;k<=n*n-1;k++)
       { arr[k]=-2;
                            }             
                               
       for(i=0; i<n; i++)
       { for(j=0;j<m;j++){
            cout<<"ingrese posicion ("<<i<<","<<j<<"):";
            ver=true;
            while(ver==true){
            cin>>magica[i][j];
            arr[A]=magica[i][j];
            cont=0;
            for(k=0; k<=n*n-1; k++)
            { if (magica[i][j]!=arr[k])
            {x=0;
                 } else { x=1;
                         }
                                    cont=cont+x;
                                               
                     }
                     if (cont>1)
                                    { ver=true;
                                    cout<<"Este numero ya fue ingresado, por favor vuelva a ingresarlo"<<endl;
                                    cout<<"posisicion("<<i<<","<<j<<"):";
                                               } else { ver=false;
                                                      } 
                     }
                     A=A+1;
                     }
                     }//condicional
             // hasta aqui asignacion de valor de la matriz
       k=0;             
       for(i=0;i<=n-1;i++)
       { sumauno=0;
       for(j=0;j<=m-1;j++)
       { 
       sumauno=sumauno+magica[i][j];
                             }
                             arreglo[k]=sumauno;
                             k=k+1;
                          }  //hasta aqui halla suma de columnas
       for(i=0;i<=n-1;i++)
       {sumados=0;
       for(j=0;j<=m-1;j++)
       { sumados=sumados+magica[j][i];
                          }
                          arreglo[k]=sumados;
                          k=k+1;
                          } //hasta aqui halla suma filas
       j=0;                         
       for(i=0;i<=n-1;i++)
       {sumatres=sumatres+magica[i][j];
       j=j+1;
                        }
       arreglo[k]=sumatres;
       k=k+1;//hasta aqui primer diagonal
       
       for(i=0;i<=n-1;i++)
       { m=2;
                          sumacuatro=sumacuatro+magica[i][m];
       m=m-1;     
                        }
                        
        arreglo[k]=sumacuatro;//hasta aqui segunda diagonal
      //comenzamos a comparar para ver si es magica o no
      for(k=1;k<=n*n-1;k++)
      { arreglodos[k]=arreglo[k-1];
                         }//otro arreglo que nos ayuda a comparar
      for(k=1;k<=n*n-1;k++)
      { i=0;
      if (arreglo[i]!=arreglodos[k])
      { y=1;
           } else { y=0;
                  }
           contdos=contdos+y;
                        }
                        if (contdos>0){
                          cout<<"la matriz ingresada no es magica"<<endl;} else {
                                    cout<<"La matriz ingresada  es magica"<<endl;}  
}
break;}
cout<<"ingrese #>2 para escoger del menu o 1 para terminar"<<endl;
  cin>>c;
  system("cls");
}
    system("pause");
    return 0;
}
