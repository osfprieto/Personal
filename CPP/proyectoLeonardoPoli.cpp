#include<iostream>
using namespace std;

double funcion(double t, double w);

int main(){
    double a, b, alpha, t, w, h;
    int n, i;
    
    cout<<"Entre a"<<endl;
    cin>>a;
    cout<<"Entre b"<<endl;
    cin>>b;
    cout<<"Entre alpha"<<endl;
    cin>>alpha;
    cout<<"Entre N"<<endl;
    cin>>n;
    
    h = (b-a)/n;
    t = a;
    w = alpha;
    cout<<'('<<t<<", "<<w<<')'<<endl;
    for(i=1;i<=n;i++){
        w += h*funcion(t, w);
        t = a + i*h;
        cout<<'('<<t<<", "<<w<<')'<<endl;
    }
    system("pause");
}

double funcion(double t, double y){
    
    //acá se va a definir la función diferencial determinada
    
    return 1+(t-y)*(t-y);
    
}
