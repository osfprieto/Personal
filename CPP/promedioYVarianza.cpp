#include<iostream>
using namespace std;

int main(){
    
    int n, i;
    double *datos, promedio, varianza;
    
    cout<<"Entre la cantidad de datos:"<<endl;
    cin>>n;
    while(n>0){
        system("cls");
        
        delete[] datos;
        
        datos = new double[n];
        
        cout<<"Entre sus datos:"<<endl<<endl;
        for(i=0;i<n;i++){
            cout<<(i+1)<<".\t";
            cin>>datos[i];
        }
        promedio = 0;
        for(i=0;i<n;i++)
            promedio+=datos[i];
        promedio /= n;
        
        varianza = 0;
        for(i=0;i<n;i++)
            varianza += ((promedio-datos[i])*(promedio-datos[i]));
        varianza /= (n-1);
        
        system("cls");
        cout<<"Sus datos:"<<endl<<endl;
        for(i=0;i<n;i++)
            cout<<(i+1)<<".\t"<<datos[i]<<endl;
        cout<<endl<<"Promedio = "<<promedio<<endl;
        cout<<"Varianza = "<<varianza<<endl<<endl;
        
        cout<<"Entre la cantidad de datos:"<<endl;
        cin>>n;
    }
    return 0;
}
