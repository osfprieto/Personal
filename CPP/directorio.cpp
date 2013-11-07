#include<fstream>
using namespace std;

int main(){
    bool sum;
    int cont, cant = 3, i;
    char primero = '!', ultimo = '~';
    fstream escribir;
    
    escribir.open("directorio.txt", ios::out);
    
    char palabra[cant], final[cant];
    
    for(i=0; i<cant;i++){
        final[i]=primero;
        palabra[i]=primero;
    }
    palabra[cant-1]++;
    escribir<<final<<endl;
    
    while(strcmp(palabra, final)!=0){
        escribir<<palabra<<endl;
        sum = true;
        for(i=cant-1;i>=0 && sum;i--){
            palabra[i]++;
            if(palabra[i]>ultimo)
                palabra[i] = primero;
            else
                sum = false;
        }
    }
    escribir.close();
    return 0;
}
