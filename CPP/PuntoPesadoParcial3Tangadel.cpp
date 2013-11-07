#include<iostream>
#include<fstream>
#include<conio.h>
using namespace std;

#define MANIANA 0
#define TARDE 1
#define NOCHE 2

void guardarDatos();
void inicializarDatos();
void insertarTrabajador();
void listarTrabajadores();

struct Trabajador{
    char nombre[80];
    char apellidos[80];
    int turno;
    bool existe;
};

Trabajador trabajadores[10];

int main(){
    char seleccion;
    inicializarDatos();
    inicio:
        system("cls");
        cout<<"             Nuestra Empresa S. A."<<endl<<endl;
        cout<<"A. Insertar Trabajador."<<endl;
        cout<<"B. Listar Trabajadores."<<endl;
        cout<<"X. Salir."<<endl;
        seleccion= getch();
        if(seleccion == 'A' || seleccion=='a')
            insertarTrabajador();
        else if(seleccion == 'B' || seleccion == 'b')
            listarTrabajadores();
        else if(seleccion == 'X' || seleccion == 'x')
            goto salir;
        goto inicio;
    salir:
        system("cls");
        cout<<"Esta seguro de que desea salir?"<<endl;
        cout<<"1. Si"<<endl;
        cout<<"2. No"<<endl;
        seleccion = getch();
        if (seleccion == '1'){
            system("cls");
            cout<<"Programa de Julián Gaitán Ávila - Programación de Computadores"<<endl;
            system("pause");
            return 0;
        }
        else if(seleccion == '2')
            goto inicio;
        else
            goto salir;
}

void inicializarDatos(){
    for(int i=0;i<10;i++)
        trabajadores[i].existe = false;
}
void insertarTrabajador(){
    system("cls");
    cout<<"Entre el numero del trabajador:"<<endl;
    int n;
    cin>>n;
    if((n<0 && n>11)||trabajadores[n].existe) {
        system("cls");
        cout<<"este trabajador ya existe o ha ingresado un valor no valido"<<endl;
        system("pause");
        return;
    }
    system("cls");
    cout<<"entre el nombre de su trabajador"<<endl;
    getch();
    cin.getline(trabajadores[n].nombre, 80);
    cout<<"entre el apellido de su trabajador"<<endl;
    cin.getline(trabajadores[n].apellidos, 80);
    cout<<"ingrese el turno de su trabajador"<<endl;
    trabajadores[n].existe = true;
    cout<<"0. maniana"<<endl;
    cout<<"1. tarde"<<endl;
    cout<<"2. noche"<<endl;
    int c;
    cin>>c;
    trabajadores[n].turno = c%3;
}
void listarTrabajadores(){
    system("cls");
    cout<<"Codigo\tnombre y apellidos\tturno"<<endl;
    for(int i=0;i<10;i++){
        if(trabajadores[i].existe){
            cout<<i+1<<'\t'<<trabajadores[i].nombre<<' '<<trabajadores[i].apellidos<<'\t';
            if(trabajadores[i].turno == 0)
                cout<<"Maniana"<<endl;
            else if(trabajadores[i].turno == 1)
                cout<<"Tarde"<<endl;
            else if(trabajadores[i].turno == 2)
                cout<<"Noche"<<endl;
        }
    }
    system("pause");
}
void guardarDatos(){
    fstream escribir;
    escribir.open("datos.txt", ios::out);
    for(int i=0;i<10;i++){
        if(trabajadores[i].existe){
            escribir<<trabajadores[i].nombre<<endl;
            escribir<<trabajadores[i].apellidos<<endl;
        }
    }
}
