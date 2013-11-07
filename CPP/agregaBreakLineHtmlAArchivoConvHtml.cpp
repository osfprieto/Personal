#include<fstream>
#include<iostream>
using namespace std;

int main(){
    char arch[256], temp[512];
    fstream leer, escribir;
    leer.open("conv.txt", ios::in);
    escribir.open("convHtml.txt", ios::out);
    leer.getline(temp, 512);
    while(temp[0]!='~'){
	    escribir<<temp<<" <br/>"<<endl;
	    cout<<" - "<<temp<<endl;
        leer.getline(temp, 512);
    }
    leer.close();
    escribir.close();
    return 0;
}

