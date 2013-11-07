//recibe un n�mero de entre 2 y 9 d�gitos con ceros a la izquierda
//para denotar la cantidad de d�gitos a usar, y muestra c�antos datos
//hay que sumar para obtener un n�mero pal�ndrome con esa cantidad de
//d�gitos
//acm livearchive 4868 - Palindrometer
//http://livearchive.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=410&page=show_problem&problem=2869
#include<iostream>
using namespace std;

void sumarUno();
bool palindrome();

char input[10];
int digits, miles;

int main(){
    cin>>input;
    
    while(!(input[0]=='0' && input[1]==0)){
        
        digits = 0;
        miles = 0;
        while(input[digits]!=0)
            digits++;
        
        while(!palindrome()){
            sumarUno();
            miles++;
        }
        
        cout<<miles<<endl;
        
        cin>>input;
        
    }
    return 0;
}

bool palindrome(){
    int i;
    for(i=0;i<digits/2;i++){
        if(input[i]!=input[digits-i-1]){
            return false;
        }
    }
    return true;
}

void sumarUno(){
    bool carry = true;
    int i=1;
    while(carry){
        input[digits-i]++;
        if(input[digits-i]<='9')
            carry = false;
        else
            input[digits-i++]='0';
    }
}
