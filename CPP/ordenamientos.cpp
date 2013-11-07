#include<fstream>
using namespace std;

int main(){
    fstream cout;
    cout.open("ordenamiento.txt", ios::out);
    int i, cont=0, num=1234, temp;
    bool present[10], presente;
    while(cont<120){
        temp = num;
        for(i=0;i<5;i++)
            present[i] = false;
        
        for(i=0;i<5;i++, temp/=10)
            present[temp%10] = true;
        
        presente = true;
        for(i=0;i<5;i++)
            presente = presente&present[i];
        
        if(presente){
            cout<<num<<',';
            cont++;
        }
        num++;
    }
    
    return 0;
}
