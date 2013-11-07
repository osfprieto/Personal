#include<fstream>
#include<iostream>
#include<string.h>
using namespace std;

int main(){
    char fileName[20];
    cout<<"Nombre archivo:"<<endl;
    cin>>fileName;
    fstream escribir;
    escribir.open(fileName, ios::out);
    escribir<<"kmas"<<endl;
    escribir<<"yo soy Omar"<<endl;
    escribir.close();
    system("pause");
    remove(fileName);//Remueve el archivo ingresando el nombre como const *char
    return 0;
}
