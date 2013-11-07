#include<iostream>
#include<iomanip>
using namespace std;

int main(){
    double L;
    double pi2 = 2*3.14159265359;
    cin>>L;
    while(L!=0){
        std::cout << std::setprecision(2) << (L*L)/pi2<<endl;
        cin>>L;
    }
}
