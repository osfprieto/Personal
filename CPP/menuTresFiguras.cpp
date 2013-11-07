#include<iostream>
#include<conio.h>
using namespace std;

void imprimir_menu();
void calcular_area_circulo();
void calcular_area_triangulo();
void calcular_area_rectangulo();

int main(){
    char entrada = '1';
    
    while(entrada!='s' && entrada!='S'){
        
        imprimir_menu();
        
        entrada = getch();
        
        if(entrada=='1')
            calcular_area_circulo();
        else if (entrada=='2')
            calcular_area_triangulo();
        else if(entrada=='3')
            calcular_area_rectangulo();
        
    }
    
}

void imprimir_menu(){
    system("cls");
    cout<<"Ingrese su seleccion, presione 'S' para salir:"<<endl<<endl;
    cout<<"1. Calcular el area de un circulo."<<endl;
    cout<<"2. Calcular el area de un triangulo."<<endl;
    cout<<"3. Calcular el area de un rectangulo."<<endl;
}

void calcular_area_circulo(){
    system("cls");
    double radio;
    double pi = 3.14159;
    
    cout<<"Ingrese el radio del circulo: ";
    cin>>radio;
    
    double area = radio*radio*pi;
    
    cout<<endl<<endl<<"El area del circulo es: "<<area<<endl;
    system("pause");
}

void calcular_area_triangulo(){
    system("cls");
    double base;
    double altura;
    
    cout<<"Ingrese la base del triangulo: ";
    cin>>base;
    cout<<"Ingrese la altura del triangulo: ";
    cin>>altura;
    
    double area = (base*altura)/2;
    
    cout<<endl<<"El area del triangulo es: "<<area<<endl;
    system("pause");
}

void calcular_area_rectangulo(){
    system("cls");
    double base;
    double altura;
    
    cout<<"Ingrese la base del rectangulo: ";
    cin>>base;
    cout<<"Ingrese la altura del rectangulo: ";
    cin>>altura;
    
    double area = base*altura;
    
    cout<<endl<<"El area del rectangulo es: "<<area<<endl;
    system("pause");
}
