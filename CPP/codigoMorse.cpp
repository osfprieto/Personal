#include<iostream>
#include<windows.h>
using namespace std;

void sonar(char *msg);

int main(){
    int i, j;
 
    char l[37]={'a','b','c','d',
     'e','f','g','h','i','j','k','l','m',
     'n','o','p','q','r','s','t',
     'u','v','w','x','y','z','1',
     '2','3','4','5',
     '6','7','8','9','0',' '};
 
    char m[37][6]={{".-"},{"-..."},
     {"-.-."},{"-.."},{"."},{"..-."}
     ,{"--."},{"...."},{".."},
     {".---"},{"-.-"},{".-.."}
     ,{"--"},{"-."},{"---"},{".--."},
     {"--.-"},{".-."},
     {"..."},{"-"},{"..-"},
     {"...-"},
     {".--"},{"-..-"},
     {"-.--"},{"--.."},
     {".----"},{"..---"},
     {"...--"},{"....-"},
     {"....."},{"-...."},
     {"--..."},{"---.."},
     {"---."},
     {"-----"},{"//"}};
 
    char *cadena;
    cadena = new char[10000];
    while(cadena[0] != 'b' || cadena[1] != 'y' || cadena[2] != 'e'){
        system("cls");
        cin.getline(cadena, 9999, '\n');
        for(i=0; cadena[i]!='\0'; i++)
            for(j=0; j<37; j++)
                if(cadena[i]==l[j]){
                    cout << m[j];
                    sonar(m[j]);
                }
        cout<<endl;
        system("pause");
    }
    return 0;
    delete[] cadena;
}

void sonar(char *msg){
    if(msg == "//")
        Beep(0, 100);
    else{
        int n=0;
        while(msg[n]!=0){
            if(msg[n]=='-')
                Beep(100, 100);
            if(msg[n]=='.')
                Beep(100, 50);
            Beep(0, 50);
            n++;
        }
    }
}
