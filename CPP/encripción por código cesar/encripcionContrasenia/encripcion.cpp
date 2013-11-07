#include<iostream>
#include<fstream>
#include<conio.h>
using namespace std;

int main(){
    int i, s=1, n;
    long double license, pass;
    char archI[40], archO[40], temp[256];
    fstream leer, escribir;
    leer.open("save.osfl", ios::in);
    leer>>license;
    leer.close();
    cout<<"Desarrollado por osfprieto@hotmail.com\n"<<endl;
    cout<<"entre su numero de licencia:"<<endl;
    cin>>pass;
    if(pass==license){
        cout<<"1. Encriptar\n2. Desencriptar\n3. salir"<<endl;
        s=getch();
        while(s!=0){
            if(s=='1'){
                cout<<"Entre el nombre de su archivo a encriptar:"<<endl;
                cin>>archI;
                cout<<"Entre el numero de su archivo de salida:"<<endl;
                cin>>archO;
                
                n=0;
                while(archI[n]!=0){
                    n++;
                }
                archI[n]='.';
                archI[n+1]='t';
                archI[n+2]='x';
                archI[n+3]='t';
                archI[n+4]=0;
                n=0;
                while(archO[n]!=0){
                    n++;
                }
                archO[n]='.';
                archO[n+1]='o';
                archO[n+2]='s';
                archO[n+3]='f';
                archO[n+4]='e';
                archO[n+5]=0;
                
                leer.open(archI, ios::in);
                escribir.open(archO, ios::out);
                
                cout<<"Entre una contrasenia para encriptar en el rango [-90, 90]"<<endl;
                cin>>n;
                
                escribir<<n;
                leer.getline(temp, 256);
                while(temp[0]!='~'){
                    i=0;
                    while(temp[i]!=0){
                        if (n>0){//hacia adelante
                            if (temp[i]+n>125){
                                temp[i]+=n-125+32;
                                escribir<<temp[i];
                            }
                            else{
                                temp[i]+=n;
                                escribir<<temp[i];
                            }
                        }
                        else if(n<0){//hacia atras
                            if (temp[i]+n<32){
                                temp[i]+=n+125-32;
                                escribir<<temp[i];
                            }
                            else{
                                temp[i]+=n;
                                escribir<<temp[i];
                            }
                        }
                        i++;
                    }
                    escribir<<endl;
                    leer.getline(temp, 256);
                }    
            }
            else if(s=='2'){
                cout<<"Entre el nombre de su archivo a desencriptar:"<<endl;
                cin>>archI;
                cout<<"Entre el numero de su archivo de salida:"<<endl;
                cin>>archO;
                
                n=0;
                while(archI[n]!=0){
                    n++;
                }
                archI[n]='.';
                archI[n+1]='o';
                archI[n+2]='s';
                archI[n+3]='f';
                archO[n+4]='e';
                archI[n+5]=0;
                n=0;
                while(archO[n]!=0){
                    n++;
                }
                archO[n]='.';
                archO[n+1]='t';
                archO[n+2]='x';
                archO[n+3]='t';
                archO[n+4]=0;
                
                leer.open(archI, ios::in);
                escribir.open(archO, ios::out);
                
                long double cont;
                leer>>cont;
                cout<<"Entre la contrasenia de su archivo:"<<endl;
                cin>>n;
                
                if(cont==n){
                    n*=(-1);
                    leer.getline(temp, 256);
                    while(temp[0]!='~'){
                        i=0;
                        while(temp[i]!=0){
                            if (n>0){//hacia adelante
                                if (temp[i]+n>125){
                                    temp[i]+=n-125+32;
                                    escribir<<temp[i];
                                }
                                else{
                                    temp[i]+=n;
                                    escribir<<temp[i];
                                }
                            }
                            else if(n<0){//hacia atras
                                if (temp[i]+n<32){
                                    temp[i]+=n+125-32;
                                    escribir<<temp[i];
                                }
                                else{
                                    temp[i]+=n;
                                    escribir<<temp[i];
                                }
                            }
                            i++;
                        }
                        escribir<<endl;
                        leer.getline(temp, 256);
                    }
                }
                else{
                    system("cls");
                    cout<<"Contrasenia invalida"<<endl;
                    getch();
                }
            }
            else if(s=='3'){
                s=0;
            }
        }
    }
    else{
        system("cls");
        cout<<"licencia invalida";
        getch();
    }
    return 0;
}







/*leer.getline(temp, 256);
while(temp[0]!='~'){
    i=0;
    while(temp[i]!=0){
        if (n>0){//hacia adelante
            if (temp[i]+n>125){
                temp[i]+=n-125+32;
                escribir<<temp[i];
            }
            else{
                temp[i]+=n;
                escribir<<temp[i];
            }
        }
        else if(n<0){//hacia atras
            if (temp[i]+n<32){
                temp[i]+=n+125-32;
                escribir<<temp[i];
            }
            else{
                temp[i]+=n;
                escribir<<temp[i];
            }
        }
        i++;
    }
    escribir<<endl;
    leer.getline(temp, 256);
}
*/
