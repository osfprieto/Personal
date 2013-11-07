#include<iostream>
#include<string.h>
using namespace std;

int main(){
    
    char strIn[80];
    char strOut[] = "Hola!";
    
    while(strcmp(strIn, "bye")!=0){
        
        system("cls");
        
        cout<<strOut<<endl;
        
        gets(strIn);
        
        cout<<((strcmp(strIn, strOut)==0)?("equal :)"):("not equal :("))<<endl;
        
        system("pause");
    
    }
    
    return 0;
}
