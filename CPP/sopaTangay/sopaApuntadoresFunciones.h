
#include<iostream>
using namespace std;
#include<time.h>
#include<windows.h>
#include<string.h>
#include<math.h>
#include<conio.h>

int contar(int dim, int c, int f, char **sopa, string cad, int *res){
    
    
    int cont = 0, i, j, k, pos, k1;
    
    for (i=0;i<f;i++){
       for (j=0;j<c;j++){
           if (sopa[i][j]==(char)(cad[0])){
              //horizontal derecha                      
              pos=0;                      
              for (k=0;k<dim;k++){
                  if (j+(dim-1)<c){
                      if(sopa[i][j+k]==(char)(cad[k])){//revisa el ingreso con la matriz y si ubica letra por letra, va guardando la posición de cada una de las letras en el vetor res                 
                         res[2*dim*cont+pos]=i;pos++;                      
                         res[2*dim*cont+pos]=j+k;pos++;
                         if(k==dim-1){
                            pos=0;cont++;   
                         }
                      }
                      else{//si en algún momento encuentra una letra que no corresponda al ingreso, reinicia esas posiciones de res que habían sido cambiadas y las coloca como -1
                           pos=0;
                           for (k1=0;k1<dim;k1++){
                               res[2*dim*cont+pos]=-1;pos++;                      
                               res[2*dim*cont+pos]=-1;pos++;    
                           }  
                           break;   
                      } 
                  }        
              } 
              //horizontal izquierda
              pos=0;                      
              for (k=0;k<dim;k++){
                  if (j-(dim-1)>=0){
                      if(sopa[i][j-k]==(char)(cad[k])){                      
                         res[2*dim*cont+pos]=i;pos++;                      
                         res[2*dim*cont+pos]=j-k;pos++;
                         if(k==dim-1){
                            pos=0;cont++;   
                         }
                      }
                      else{
                           pos=0; 
                           for (k1=0;k1<dim;k1++){
                               res[2*dim*cont+pos]=-1;pos++;                      
                               res[2*dim*cont+pos]=-1;pos++;    
                           } 
                           break;    
                      } 
                  }        
              } 
              //vertical abajo
              pos=0;
              for (k=0;k<dim;k++){
                  if (i+(dim-1)<f){
                      if(sopa[i+k][j]==(char)(cad[k])){                      
                         res[2*dim*cont+pos]=i+k;pos++;                      
                         res[2*dim*cont+pos]=j;pos++;
                         if(k==dim-1){
                            pos=0;cont++;   
                         }
                      }
                      else{
                           pos=0; 
                           for (k=0;k<dim;k++){
                               res[2*dim*cont+pos]=-1;pos++;                      
                               res[2*dim*cont+pos]=-1;pos++;    
                           }
                           break;     
                      } 
                  }        
              }
              //vertical arriba
              pos=0;
              for (k=0;k<dim;k++){
                  if (i-(dim-1)>=0){
                      if(sopa[i-k][j]==(char)(cad[k])){                      
                         res[2*dim*cont+pos]=i-k;pos++;                      
                         res[2*dim*cont+pos]=j;pos++;
                         if(k==dim-1){
                            pos=0;cont++;   
                         }
                      }
                      else{
                           pos=0;
                           for (k1=0;k1<dim;k1++){
                               res[2*dim*cont+pos]=-1;  
                               pos++;res[2*dim*cont+pos]=-1;
                               pos++;    
                           }
                           break;     
                      } 
                  }        
              }
              //diagonal abajo-derecha
              pos=0;
              for (k=0;k<dim;k++){
                  if (i+(dim-1)<f && j+(dim-1)<c){
                      if(sopa[i+k][j+k]==(char)(cad[k])){                      
                         res[2*dim*cont+pos]=i+k;pos++;                      
                         res[2*dim*cont+pos]=j+k;pos++;
                         if(k==dim-1){
                            pos=0;cont++;break;  
                         }
                      }
                      else{
                           pos=0;
                           for (k1=0;k1<dim;k1++){
                               res[2*dim*cont+pos]=-1;  
                               pos++;res[2*dim*cont+pos]=-1;
                               pos++;    
                           }  
                           break;   
                      } 
                  }        
              }
              //diagonal arriba-derecha
              pos=0;
              for (k=0;k<dim;k++){
                  if (i-(dim-1)>=0 && j+(dim-1)<c){
                      if(sopa[i-k][j+k]==(char)(cad[k])){                      
                         res[2*dim*cont+pos]=i-k;pos++;                      
                         res[2*dim*cont+pos]=j+k;pos++;
                         if(k==dim-1){
                            pos=0;cont++;break;   
                         }
                      }
                      else{
                           pos=0;
                           for (k1=0;k1<dim;k1++){
                               res[2*dim*cont+pos]=-1;pos++;
                               res[2*dim*cont+pos]=-1;pos++;    
                           } 
                           break;    
                      } 
                  }        
              }
              //diagonal arriba-izquierda
              pos=0;
              for (k=0;k<dim;k++){
                  if (i-(dim-1)>=0 && j-(dim-1)>=0){
                      if(sopa[i-k][j-k]==(char)(cad[k])){                      
                         res[2*dim*cont+pos]=i-k;pos++;                      
                         res[2*dim*cont+pos]=j-k;pos++;
                         if(k==dim-1){
                            pos=0;cont++;break;   
                         }
                      }
                      else{
                           pos=0;
                           for (k1=0;k1<dim;k1++){
                               res[2*dim*cont+pos]=-1;pos++;
                               res[2*dim*cont+pos]=-1;pos++;    
                           } 
                           break;    
                      } 
                  }        
              }
              //diagonal abajo-izquierda
              pos=0;
              for (k=0;k<dim;k++){
                  if (i+(dim-1)<f && j-(dim-1)>=0){
                      if(sopa[i+k][j-k]==(char)(cad[k])){                      
                         res[2*dim*cont+pos]=i+k;pos++;                      
                         res[2*dim*cont+pos]=j-k;pos++;
                         if(k==dim-1){
                            pos=0;cont++;break;  
                         }
                      }
                      else{
                           pos=0;
                           for (k1=0;k1<dim;k1++){
                               res[2*dim*cont+pos]=-1;pos++;
                               res[2*dim*cont+pos]=-1;pos++;    
                           }
                           break;     
                      } 
                  }        
              }
                                                    
           }   
       }
   }
   
   return cont;
    
}

void iniciarMenu(int &f, int &c){
    
    double c1, f1;
    
    cout<<"                               SOPA DE LETRAS\n\n";  
    cout<<"                 Tama"<<(char)164<<"o horizontal mayor o igual a vertical\n"; 
    cout<<"                si digita un tamaño mayor o menor se asignara\n                     el valor maximo o minimo por defecto\n";
    cout<<"       digite un valor entero, los decimales se tomaran como enteros \n";
    cout<<"                             -----Ingrese-----\n\n\n";
    cout<<"Tama"<<(char)164<<"o horizontal, max 28 min 2\t";
    cin>>c1;
    cout<<"\n\ntama"<<(char)164<<"o vertical, max 18 min 2  \t";
    cin>>f1;
    c1=floor(c1);// aproxima por piso al int
    f1=floor(f1);
    if (c1<2)
       c1=(int)2; 
    if (c1>28)
       c1=(int)28; 
    if (f1<2)
       f1=(int)2; 
    if (f1>18)
       f1=(int)18;
    if (c1<f1)     
       c1=f1;
    
    c=(int)(c1);
    f=(int)(f1);
    
}

void imprimirSopa(char **sopa, int f, int c){
    int i, j, k;
    system("cls");
    cout<<"            ----------------------------------------------\n";
    cout<<"                           SOPA DE LETRAS\n";
    cout<<"            ----------------------------------------------\n";
    for (i=0;i<f;i++){
        cout<<"\n    ";
        for (k=0;k<(31-c);k++){
            cout<<" ";//imprime caracteres ' ' para centrar la matriz
        }
        for (j=0;j<c;j++){
           cout<<sopa[i][j]<<" ";//imprime la matriz
        }
    }
    
    cout<<endl<<endl;
    
}


void sopa(){
    ini:
    
    system("cls");
    system("color 7");
    
    srand(time(NULL));//para esto incluye a time.h, siembra la semilla del Random
    int letra,dim,cont,k,i,j,k1,i1; 
    double c1,f1;
    string cad;
    letra=0;
    
    int c,f;
    
    iniciarMenu(f, c);
    
    char **sopa = new char*[c];
    
    for(i=0;i<c;i++){
        sopa[i] = new char[f];
    }
    
    int *res = new int[c*f];//maybe res stands for results
    int *resh = new int[c];
    int *resv = new int[f];
    
    for (i=0;i<f*c;i++){
       res[i]=-1;
    }
    for (i=0;i<f;i++){
       for (j=0;j<c;j++){  
           letra=rand()%26;//selecciona la letra determinada a poner
           sopa[i][j]=97+letra;//ubica la letra determinada con respecto a la codificación char, por eso le agrega 97
       }
    }
    char **sopa2 = new char*[c];
    
    for(i=0;i<c;i++){
        sopa2[i] = new char[f];
    }
    
///////////////////////////////////////////////////////
    
    imprimirSopa(sopa, f, c);

////////////////////////////////////////////////////////
    
    cout<<"\n\nDigite la palabra que desea buscar\t";
    cin>>cad;
    dim=cad.length();//para esto incluye string.h... bla.length()
    if (dim>c){
      while(dim>c){
         cout<<"\n\nPalabra muy grande, digite de nuevo\t";
         cin>>cad;
         dim=cad.length();
      }               
    }
    
////////////////////////////////////////////////////////////////////////////////////////////

    cont=contar(dim, c, f, sopa, cad, res);
   
/////////////////////////////////////////////////////////////////////////////////////////////


   for (i=0;i<2*dim*cont;i++){
       if (res[i]!=-1 && i%2==0){
          resv[i/2]=res[i];
       } 
       if (res[i]==-1 && i%2==0)
          break;   
   }
   for (i=1;i<2*dim*cont;i++){
       if (res[i]!=-1 && i%2==1){
          resh[i/2]=res[i];
       }
        if (res[i]==-1 && i%2==1)
          break;    
   }
   for (int i2=0;i2<f;i2++){
       for (int j2=0;j2<c;j2++){
              sopa2[i2][j2]=sopa[i2][j2];     
       }
   }
   for (i=0;i<dim*cont;i++){
       sopa2[resv[i]][resh[i]]=toupper(sopa[resv[i]][resh[i]]);
   }
   
///////////////////////////////////////////////////////
    
    imprimirSopa(sopa2, f, c);

////////////////////////////////////////////////////////

   if (cont!=1){system("color e");
      cout<<"La palabra: \""<<cad<<"\" esta "<<cont<<" veces\n";
   }
   else
      cout<<"La palabra: \""<<cad<<"\" esta 1 vez\n";    
   
   cout<<"\n\nDesea volver a jugar?"<<endl;
   
   cout<<'Y';
   
   cout<<'/';
   
   cout<<'N'<<'\t';
   char cond;
   cond=getch();
   if (cond=='y' || cond=='Y'){
        goto ini;
    }
    for(i=0;i<c;i++){
        delete[] sopa[i];
    }
    delete[] sopa;
    delete[] res;
    delete[] resh;
    delete[] resv;
}
