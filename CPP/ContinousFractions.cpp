#include<iostream>
using namespace std;
int main(){
    int caso=1, p, q, i, r, j, maxDigits, chars;
    
    cin>>p>>q;
    
    while(p>0 && q>0){
        cout<<"Case "<<caso<<':'<<endl<<p<<" / "<<q<<endl;
        
        int f[100];
        i=0;
        while(q!=0){
            f[i] = p/q;
            r = p%q;
            p = q;
            q = r;
            i++;
        }
        f[i-1]--;
        //la cantidad de caracteres a imprimir por linea es en tital cuatro veces la
        //cantidad de resultados almacenados + la cantidad de dígitos del nùmero mayor a imprimir
        //la cantidad de lineas a imprimir es la dos veces la cantidad de datos obtenidos + 1
        maxDigits=0;
        for(j=0;j<i;j++){
            r=0;
            int temp = f[j];
            while(temp>0){
                r++;
                temp/=10;
            }
            if(r>maxDigits)
                maxDigits = r;
        }
        chars = 4*i+maxDigits+1;
        for(j=1;j<=i*2+1;j++){
            if(j%2 == 1){
                //impresión de puntos con solo el uno en la linea
                
            }
            else{
                //impresión de puntos con el número determinado, el más y la lina de guiones
                int pos = j/2-1;
                p=f[pos];
                int temp=0;
                while(p>0){
                    temp++;
                    p/=10;
                }
                for(r=1;r<=chars;r++){
                    if(r<=4*pos)
                        cout<<'.';
                    else if(r<4*(pos+1)){
                        r+=(3+maxDigits);
                        cout<<f[pos]<<".+.";
                    }
                    else{
                        cout<<'-';
                    }
                }
                for(p=0;p<maxDigits-temp;p++)
                    cout<<'-';
            }
            cout<<endl;
        }
        cout<<endl;
        cin>>p>>q;
        caso++;
    }
    
    return 0;
}
