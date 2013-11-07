#include<iostream>
using namespace std;

int main(){
    int i, cont;
    string img, mvi;
    
    img = "rename IMG_9422.jpg IMG0004.jpg";
    mvi = "rename MVI_9422.avi MVI0004.avi";
    
    if(i==9466 || i==9467 || i==9468 || i==9491 || i==9585 || i==9586 || i==9590 || i==9632 || i==9633)
        system(mvi.c_str());
    else
        system(img.c_str());
    for(i=9422;i<=9950;i++){
        
        cont=14;
        
        img[cont]++;
        mvi[cont]++;
        
        if(img[cont]>'9')
            img[cont]='0';
        if(mvi[cont]>'9')
            mvi[cont]='0';
        
        while(img[cont]=='0'){
            cont--;
            img[cont]++;
            mvi[cont]++;
            if(img[cont]>'9')
                img[cont]='0';
            if(mvi[cont]>'9')
                mvi[cont]='0';
        }
        
        cont=26;
        
        img[cont]++;
        mvi[cont]++;
        
        while(img[cont]=='0'){
            cont--;
            img[cont]++;
            mvi[cont]++;
        }
        
        if(i==9466 || i==9467 || i==9468 || i==9491 || i==9585 || i==9586 || i==9590 || i==9632 || i==9633){
            cout<<mvi<<endl;
            system(mvi.c_str());
        }
        else{
            cout<<img<<endl;
            system(img.c_str());
        }
    }
    /*delete[] img;
    delete[] mvi;*/
    return 0;
}
//rename IMG_7865.jpg IMG0001.jpg
//rename MVI_7892.avi MVI0001.avi
