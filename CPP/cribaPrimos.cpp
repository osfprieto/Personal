#include<fstream>
using namespace std;

int main(){
    
    fstream cout;
    cout.open("cribaPrimos.txt", ios::out);
    
    
    int n = 1000000;
    int raiz = 1000;
    
    int i, j;
    bool primo[n];
    for(i=0;i<n;i++)
        primo[i] = true;
    
    for(i=2;i<=raiz;i++)
        if(primo[i])
            for(j=2*i;j<n;j+=i)
                primo[j] = false;
    
    for(i=2;i<n;i++)
        if(primo[i])
            cout<<i<<endl;
    
    
    
    cout.close();
    return 0;
}
