#include<iostream>
#include<fstream>
using namespace std;

int main(){
    fstream escribir;
    escribir.open("temp.txt", ios::out);
    escribir<<"kmas"<<endl<<"lalalalala"<<endl;
    escribir.close();
    system("pause");
    system("del temp.txt");
    return 0;
}
