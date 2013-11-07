#include<iostream>
#include<conio.h>

using namespace std;

//caracteres especiales

#define t_a (char)0xe1//contiene las vocales minusculas con tilde
#define t_e (char)0xe9
#define t_i (char)0xed
#define t_o (char)0xf3
#define t_u (char)0xfa

#define t_A (char)0xc1//contiene las vocales mayusculas con tilde
#define t_E (char)0xc9
#define t_I (char)0xcd
#define t_O (char)0xd3
#define t_U (char)0xda

#define enie (char)0xf1//contine la ñ minuscula y mayuscula
#define ENIE (char)0xd1

/*
En la Esctructura Laberinto se define la forma de un laberinto a usar por la
aplicación de los laberints, en esta se guardan todos los datos de la aplicación
como la casilla actual del usuario, un mapa binario de las paredes del laberinto
seleccionado, la casilla de salida y el tiempo de inicio al igual que el tiempo
acutal de juego.
*/
struct Laberinto{
    bool lab[15][15];//true for walls, false for floor
    int x;
    int y;
    char cursor, wall, floor;
    int pasos;
    int xOut;
    int yOut;
    long timeIni;
};


/*
la función "recibirPalabra" retorna un objeto del tipo string ingresado
desde la consola sin espacios
*/
string recibirPalabra(){//recibe una palabra en consola
    string palabra;
    cin>>palabra;
    return palabra;
}

/*
La función "recibirLinea" retorna un objeto del tipo string ingresado
desde la consola con espacios de máximo n caracteres.
*/
string recibirLinea(int n){//recibe una linea en consola: stand by
    string linea;
    char *line;
    line = new char[75];
    cin.getline(line, n);
    linea = line;
    delete[] line;
    return linea;
}

/*
La función "recibirPassword" se encarga de recibir una cadena por consola y
un objeto de tipo string con el password ingresado sin mostrarlo en pantalla
esta función se desarrolla através de la función "getch" de la librería "conio.h"
e imprime '*' por cada caracter ingresado, para el entendimiento con el
usuario.
*/
string recibirPassword(){//el password debe tener mínimo seis caracteres
                        //no puede tener espacios, solo puede usar caracteres a
                        //' '=32<a<126='~' y máximo 70 caracteres
    int i=-1, j;
    char input;
    string pass = "";
    string temp;
    system("cls");
    cout<<"Entra tu password (minimo 6 caracteres, maximo 70 caracteres sin espacios):"<<endl;
    input = getch();
    while(input != 13 || i <6){//si se entra enter (char = 13) pero la contraseña es menor a seis caracteres, sigue recibiendo
        if(input == 8 && i>=0){
            temp="";
            i--;
            for(j=0;j<=i;j++){
                temp+=pass[j];
            }
            pass="";
            for(j=0;j<=i;j++){
                pass+=temp[j];
            }
            cout<<input<<' '<<input;
        }
        else if(input>=33 && input<=125 && i<70){//deja de recibir al tener 70 caracteres en el password
            pass+=input;
            cout<<'*';
            i++;
        }
        input = getch();
    }
    return pass;
}

string nombrarArchivo(string nombre){
    nombre+=".osf";
    return nombre;
}
