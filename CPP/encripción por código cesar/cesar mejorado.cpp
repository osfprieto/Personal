#include<iostream>
#include<fstream>
using namespace std;

bool revisarEspeciales(char c);

int main(){
    
    int n=0, l=0, i;
    char arch[256], temp[256];
    fstream leer, escribir;
    cout<<"Desarrollado por osfprieto ::D\n                 osfprieto@hotmail.com\n"<<endl;
    cout<<"Cuantos espacios desea correr el alfabeto?"<<endl;
    cin>>n;
    cout<<"Entre le nombre de su archivo a encriptar por codigo cesar(debe estar en la misma carpeta que este ejecutable):"<<endl;
    cin>>arch;
    while(arch[l]!=0){
        l++;
    }
    arch[l]  ='.';
    arch[l+1]='t';
    arch[l+2]='x';
    arch[l+3]='t';
    arch[l+4]=0;
    leer.open(arch, ios::in);
    escribir.open("encripcion.txt", ios::out);
    leer.getline(temp, 256);
    while(temp[0]!='~'){
        i=0;
        while(temp[i]!=0){
            if(revisarEspeciales(temp[i])){
                escribir<<temp[i];
            }
            else if (n>0){//hacia adelante
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
    escribir<<'~';
    leer.close();
    escribir.close();
    return 0;
}
bool revisarEspeciales(char c){
    return (c<=31 || c>=127)?true:false;
}
