#include<iostream>
using namespace std;

#define NOT_VALID 0

int inverse(int a, int b, int opcion);
int crt(int *a, int *n, int k);

int main(){
    int k, i, N;
    
    cout<<"Ingrese K:\n";
    cin>>k;
    
    while(k>0){
        system("cls");
        cout<<"Los N ingresados deben ser coprimos en todo par"<<endl;
        int a[k], n[k];
        N=1;
        for(i=0;i<k;i++){
            cout<<"Ingrese A sub "<<i+1<<":\n";
            cin>>a[i];
            cout<<"Ingrese N sub "<<i+1<<":\n";
            cin>>n[i];
        }
        
        system("cls");
        
        cout<<"X =\tA\tmod\tN\n"<<endl;
        for(i=0;i<k;i++)
            cout<<"X =\t"<<a[i]<<"\tmod\t"<<n[i]<<endl;
        
        cout<<"X = "<<crt(a, n, k)<<endl;
        
        cout<<endl;
        system("pause");
        system("cls");
        cout<<"Ingrese K:\n";
        cin>>k;
    }
    
}

int crt(int *a, int *n, int k){
    int N=1, x=0, i;
    for(i=0;i<k;i++)
        N*=n[i];
    for(i=0;i<k;i++)
        x+=a[i]*inverse(n[i], N/n[i], 1)*N/n[i];
    
    while(x<0)
        x+=N;
    
    return x;
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
