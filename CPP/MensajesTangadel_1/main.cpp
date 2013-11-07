 /*
            MENSAJER�A TANGADEL
Mensajer�a Tangadel es un programa b�sico de env�o y recepci�n de mensajes
de texto plano que se encarga de almacenar y manipular los datos de los
usuario que se registren en el.
*/
#include"manejoDatos.h"
using namespace std;

/*
La funci�n "main" del programa, que es la primera en ejecutarse como tal
se encarga de mostrar el men� principal del programa donde se puede escoger
una de tres tareas: ingresar como usuario, registrarse como usuario y salir
en estos casos, la funci�n redirige al programa hacia las funciones almacenadas
en el archivo "manejoDatos.h" donde est�n todas las funciones encargadas
del manejo de la informaci�n del usuario.
*/
int main(){
    system("color 8a");
    char seleccion;
    PlaySound("mgsBackGround.wav", 0, SND_LOOP | SND_ASYNC);
    ini:
        system("cls");
        cout<<"            Mensajeria Tangadel y asosociados"<<endl;
        cout<<"            ---------------------------------"<<endl<<endl;
        
        cout<<"Selecciona una opcion:"<<endl;
        cout<<"1. Ingresar como usuario."<<endl;
        cout<<"2. Registrarme como usuario."<<endl;
        cout<<"3. salir."<<endl;
        
        seleccion = getch();
        
        if(seleccion == '1')
            ingresar();
        else if(seleccion =='2')
            registrar();
        else if(seleccion=='3')
            goto salir;
        else
            goto ini;
        goto ini;
    salir:
        system("cls");
        cout<<"Estas seguro que deseas salir?"<<endl;
        cout<<"1. Si."<<endl;
        cout<<"2. No."<<endl;
        seleccion = getch();
        if(seleccion == '2')
            goto ini;
        else if(seleccion == '1')
            return 0;
        else
            goto salir;
}
