#include<iostream>
#include<math.h>
using namespace std;

int main(){
    
    int n, i;
    double a, b, c, x1, x2;
    
    cin>>n;
    
    for(i=0;i<n;i++){
        cin>>a>>b>>c;
        
        if(b*b-4*a*c<0){
            cout<<"No real solution!"<<endl;
        }
        else{
            double d = 4*a*c;
            x1 = ((-1)*b + sqrt(b*b-(d)))/(2*a);
            x2 = ((-1)*b - sqrt(b*b-(d)))/(2*a);
            if(x2==x1){
                printf("%.3f\n", x2);
            }
            else if(x2<x1){
                printf("%.3f %.3f\n", x2, x1);
            }
            else if(x1<x2){
                printf("%.3f %.3f\n", x1, x2);
            }
        }
        
    }
    
    return 0;
}
