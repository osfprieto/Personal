#include<iostream>
using namespace std;

int main(){

    char arr[200];
    cin.getline(arr, 200);
    int nword=0,i=0,n=0;
    /*for(i=0;i<200;i++){
        if((arr[i]!=0)||((arr[i]==0)&&(arr[i+1]!=0))){
            n++;
         }
    }*/
    while(arr[i]!=0){
        if(arr[i]==' ' && arr[i+1]!=0){
            n++;
        }
        i++;
    }
    if(arr[i]==0 && arr[i-1]!=0){
        n++;
    }
    cout<<n<<endl;
    sleep(10);
    //system("pause");
    return 0;
}
