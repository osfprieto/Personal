#include<iostream>
#include<cmath>
using namespace std;
#define MAX_DOUBLE 32000;

int main(){
    double x1, x2, x3, x4, y1, y2, y3, y4, m1, m2, b1, b2, x, dist;
    int caso = 1;
    cin>>x1>>y1>>x2>>y2;
    bool pared;
    while(x1!=0 || y1!=0 || x2!=0 || y2!=0){
        cin>>x3>>y3>>x4>>y4;
        
        if(x1!=x2)
            m1 = (y2-y1)/(x2-x1);
        else
            m1 = MAX_DOUBLE;
        if(x3!=x4)
            m2 = (y4-y3)/(x4-x3);
        else m2 = MAX_DOUBLE;
        
        b1 = y1 - m1*x1;
        b2 = y3 - m2*x3;
        
        cout<<m1<<", "<<m2<<", "<<b1<<", "<<b2<<endl;
        if(m1!=m2){
            x = (b2-b1)/(m1-m2);
            
            if(x3<=x4 && x3<=x && x<=x4)
                pared = true;
            else if(x4<x3 && x4<=x && x<=x3)
                pared = true;
        }
        else
            pared = false;
        
        if(pared){
            dist = (sqrt((x1-x3)*(x1-x3)+(y1-y3)*(x1-y3)) + sqrt((x2-x3)*(x2-x3)+(y2-y3)*(x2-y3))) / 2;
            double temp = (sqrt((x1-x4)*(x1-x4)+(y1-y4)*(x1-y4)) + sqrt((x2-x4)*(x2-x4)+(y2-y4)*(x2-y4))) / 2;
            if(temp < dist)
                dist = temp;
        }
        else
            dist = sqrt((x1-x2)*(x1-x2)+(y1-y2)*(x1-y2));
        
        cout<<"Case "<<caso++<<": "<<dist<<endl;
        cin>>x1>>y1>>x2>>y2;
    }
    
    return 0;
}

/*

y1 = m1x+b1
y2 = m2x+b2

m1x + b1 = m2x + b2

x(m1-m2) = b2-b1
x = (b2-b1)/(m1-m2)

*/
