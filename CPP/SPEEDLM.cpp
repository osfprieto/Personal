#include<iostream>
using namespace std;

int main(){
    
    int n, i, miles, hours, total, time;
    cin>>n;
    while(n>0){
        total = 0;
        time = 0;
        for(i=0;i<n;i++){
            cin>>miles>>hours;
            total += miles*(hours-time);
            time = hours;
        }
        cout<<total<<" miles"<<endl;
        cin>>n;
    }
    return 0;
}
