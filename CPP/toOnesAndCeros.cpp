#include<fstream>
#include<iostream>
using namespace std;

int main(){
    
    fstream escribir, leer;
    
    char temp[256];
    
    string name;
    
    int i;
    
    cout<<"Entre el nombre del archivo a pasar a 1's y 0's:"<<endl;
    
    cin>>name;
    
    leer.open(name.c_str(), ios::in);
    
    name += "ToOnesAndCeros";
    
    escribir.open(name.c_str(), ios::out);
    
    leer.getline(temp, 255);
    
    while(temp[0]!=0){
        i=0;
        while(temp[i]!=0){
            if(temp[i]==' ')
                escribir<<'0';
            else
                escribir<<'1';
            i++;
        }
        escribir<<endl;
        leer.getline(temp, 255);
    }
    
    return 0;
}
