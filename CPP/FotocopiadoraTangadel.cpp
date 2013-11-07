#include<iostream>
using namespace std;

void analizar(int *tempCost, int n);

int main(){
    
    int n=1, i, costo, tempCost;
    cout<<"Entre la cantidad de documentos originales a fotocopiar:"<<endl<<"Entre 0 para cerrar"<<endl;
    cin>>n;
    while(n<0){
        if(n<0){
            cout<<"Cantidad Erronea"<<endl;
        }
        cout<<"Entre la cantidad de documentos originales a fotocopiar:"<<endl<<"Entre 0 para cerrar"<<endl;
        cin>>n;
    }
    system("cls");
    while (n!=0){
        costo=0;
        int doc[n];
        for(i=0;i<n;i++){
            doc[i]=0;
            cout<<"Entre la cantidad de fotocopias del documento "<<i+1<<':'<<endl;
            cin>>doc[i];
            while(doc[i]<=0){
                if(doc[i]<=0){
                    cout<<"Cantidad erronea"<<endl;
                }
                cout<<"Entre la cantidad de fotocopias del documento "<<i+1<<':'<<endl;
                cin>>doc[i];
            }
        }
        system("cls");
        cout<<"Doc\tCant\tCosto"<<endl;
        for(i=0;i<n;i++){
            tempCost=100*doc[i];
            analizar(&tempCost, doc[i]);
            costo+=tempCost;//costo = costo + tempCost;
            cout<<i+1<<'\t'<<doc[i]<<'\t'<<tempCost<<endl;
        }
        cout<<"-------------------------------------------"<<endl;
        cout<<'\t'<<"total"<<'\t'<<costo<<endl;
        system("pause");
        system("cls");
        cout<<"Entre la cantidad de documentos originales a fotocopiar:"<<endl<<"Entre 0 para cerrar"<<endl;
        cin>>n;
    }
    
    return 0;
}

void analizar(int *tempCost,int n){
    
    if(n>200 && n<=400){
        *tempCost = (*tempCost)*88/100;
    }
    else if(n>400){
        *tempCost = (*tempCost)*82/100;
    }
    
}
