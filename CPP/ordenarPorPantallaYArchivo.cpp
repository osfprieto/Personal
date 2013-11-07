/*Con este programa se pueden ordenar diferentes arreglos
de tama�o n, el c�al se ingresa al iniciar el procedimiento.
El procedimiento se puede realizar de alguno de dos m�todos:
    - Desde varias entradas en pantalla que se dan:
        Se siguen las instruccinoes que se ir�n dando en pantalla.
    - Desde la lectura de un archivo de entrada:
        El programa pide el nombre de un archivo del c�al leer
        los datos. Se recomienda que el archivo sea de texto,
        es decir que tenga la terminaci�n ".txt" en su nombre.
        Este archivo debe tener la informaci�n del arrgelo: en
        la primera l�nea debe estar en n�mero n del tama�o del
        arreglo, seguido de las n l�neas, cada una con un dato
        correspondiente a la posoci�n n del arreglo. De esta
        misma manera, el procedimiento por este camino no
        mostrar� los datos en pantalla, sino que construir� un
        archivo "out.txt" el c�al contendr� los datos ya organizados
        uno por uno en cada l�nea del archivo.*/

#include<iosrtream>
#include<fstream>
#include<conio.h>
using namespace std;

int main(){
    
    char select;
    bool repeat = true;
    system("color 8a");
    while(repeat){
        system("cls");
        
        cout<<"1. Ordenar arreglo de numeros ingresados por pantalla."<<endl;
        cout<<"2. Ordenar arreglo de numeros guardados en un archivo."<<endl;
        
        repeat = ordenar(getch());
        
        repeat = repito();
    }
    return 0:
    
}

bool ordenar(char select){
    
    int size, i, j;
    double temp;
    
    if(select == '1'){//procedimiento por pantalla
        
        system("cls");
        cout<<"De qu� tama�o es su arreglo?"<<endl;
        cin>>size;
        
        double datos[size];
        
        //leer por medio de la entrada a la consola
        system("cls");
        cout<<"Ahora entre sus datos:"<<endl;
        for(i=0;i<size;i++){
            cout<<"X"<<(i+1)<<" = ";
            cin>>datos[i];
        }
        
        //ordenar
        for(i=0;i<size;i++){
            for(j=i;j<size;j++){
                if(datos[j]<datos[i]){
                    temp = datos[i];
                    datos[i] = datos[j];
                    datos[j] = temp;
                }
            }
        }
        
        //mostrar en orden en la pantalla
        system("cls");
        cout<<"Sus datos ordenados son:"<<endl;
        for(i=0;i<size;i++){
            cout<<"X"<<(i+1)<<" = "<<datos[i]<<endl;
        }
        getch();
        return false;
    }
    else if(select == '2'){//procedimiento por archivos
        
        char nombre[25];
        fstream leer, escribir;
        
        //leer desde el archivo con un nombre determinado
        system("cls");
        cout<<"Entre el nombre de su archivo\nRecuerde que si es un archivo de texto, este debe estar con la terminaci�n \".txt\":"<<endl;
        
        cin>>nombre;
        
        leer.open(nombre, ios::in);
        escribir.open("out.txt", ios::out);
        
        leer>>size;
        
        double datos[size];
        
        for(i=0;i<size;i++){
            leer>>datos[i];
        }
        
        //ordenar
        for(i=0;i<size;i++){
            for(j=i;j<size;j++){
                if(datos[j]<datos[i]){
                    temp = datos[i];
                    datos[i] = datos[j];
                    datos[j] = temp;
                }
            }
        }
        
        //escribir en el archivo de salida "out.txt"
        escribir<<"Arreglo ordenado:"<<endl;
        for(i=0;i<size;i++){
            escribir<<"X"<<(i+1)<<" = "<<datos[i]<<endl;
        }
        
        return false;
    }
    else{
        return true;
    }
    
}

bool repito(){
    
    char select;
    
    system("cls");
    
    cout<<"Quiere realizar el procedimiento de nuevo?\nPresione \'S\' para aceptar."<<endl;
    
    select = getch();
    
    if(select == 's' || select == 'S'){
        return true;
    }
    else{
        return false;
    }
    
}
