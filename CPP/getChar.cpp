#include<iostream>
#include<conio.h>
using namespace std;

int main(){
    
    int n;
    n=getch();
    cout<<"Char\tCode"<<endl;
    while(n!=13){
        printf("%c\t%d\n", n, n);
        n=getch();
    }
    
    return 0;
}
