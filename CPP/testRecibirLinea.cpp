#include<iostream>
#include<conio.h>
using namespace std;

string recibirLinea();

int main(){
    string in = "";
    while(true){
        in = recibirLinea();
        cout<<'\t'<<in<<endl;
    }
    return 0;
}


string recibirLinea(){//recibe una linea en consola: stand by
    string linea;
    char *line;
    line = new char[1401];
    cin.getline(line, 1400);
    linea = line;
    delete[] line;
    return linea;
}
