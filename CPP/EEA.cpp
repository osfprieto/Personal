#include <iostream>
using namespace std;

#define NOT_VALID 0

int inverse(int a, int b, int opcion);

int main(){
    
    int a, b, x, y, temp;
    
    cout<<"Debe usar numeros coprimos"<<endl;
    cout<<"Ingrese un numero:"<<endl;
    cin>>a;
    cout<<"Ingrese un numero:"<<endl;
    cin>>b;
    
    while(a!=0 && b!=0){
        if(b>a){
            temp = a;
            a = b;
            b = temp;
        }
        
        x = inverse(a, b, 0);
        y = inverse(a, b, 1);
        
        system("cls");
        
        if(x!=NOT_VALID || y!=NOT_VALID){
            cout<<"A = "<<a<<endl;
            cout<<"B = "<<b<<endl;
            cout<<"X = inverso multiplicativo de A mod B = "<<x<<endl;
            cout<<"Y = inverso multiplicativo de B mod A = "<<y<<endl;
        }
        else
            cout<<"Por favor fijese que los numeros sean coprimos"<<endl;
        
        cout<<endl;
        system("pause");
        system("cls");
        
        cout<<"Debe usar numeros coprimos"<<endl;
        cout<<"Ingrese un numero:"<<endl;
        cin>>a;
        cout<<"Ingrese un numero:"<<endl;
        cin>>b;
    }

    return 0;
}

int inverse(int a, int b, int opcion){//siempre a es el mayor
    if(opcion==0)//quiere x (inverso multiplicativo de a mod b)
        if(a==1 && b==0)
            return 1;
        else if(b!=0)
            return inverse(b, a%b, 1);
        else
            return NOT_VALID;
    else if(opcion==1)//quiere y (inverso multiplicativo de b mod a)
        if(a==1 && b==0)
            return 0;
        else if(b!=0)
            return inverse(b, a%b, 0) - (a/b)*inverse(b, a%b, 1);
        else
            return NOT_VALID;
    else
        return NOT_VALID;
}
