#include<iostream>
#include<math.h>
using namespace std;

int main(){
    
    double x, temp, limit, limitInf, limitSup;
    
    cin>>limit;
    
    limitSup = limit+0.001;
    limitInf = limit-0.001; 
    
    for(x=0;x<180;x+=0.0001){
        temp = (1/tan(x))/x;
        if(temp<limitSup && temp>limitInf)
            break;
        else
            cout<<x<<'\t'<<temp<<endl;
    }
    cout<<x<<'\t'<<temp<<endl;
    
    system("pause");
    return 0;
}
