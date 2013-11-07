#include<iostream>
#include<fstream>
#include<string>
using namespace std;
int main(){
    char cad[50], cad2[20], aux;
    cin>>cad;
    for(int i=0;i<15;i++)
        cad2[i] = cad[i];
    
    for(int i=0;i<strlen(cad2);i++){
        for(int j=i; j<strlen(cad2);j++)
            if(cad2[j]<cad2[i]){
                aux = cad[i];
                cad[i] = cad[j];
                cad[j] = aux;
            }
    }
    cout<<cad2<<endl;
    system("pause");
    return 0;
}
