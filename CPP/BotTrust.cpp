#include<Fstream>
using namespace std;

int main(){
    fstream leer, escribir;
    leer.open("BotTrust.in", ios::in);
    escribir.open("BotTrust.out", ios::out);
    int n, t, posB, posO, s, button, pasos, pasosAnteriores, i, j;
    char selec;
    bool prevSelec;
    leer>>n;
    for(i=0;i<n;i++){
        leer>>t;
        s=0;
        pasos = 0;
        selec=='U';
        posB=posO=1;
        for(j=0;j<t;j++){
            
            prevSelec = selec;
            
            leer>>selec>>button;
            
            pasosAnteriores = pasos;
            
            if(selec=='O' || selec == 'o'){
                prevSelec = true;
                pasosAnteriores = pasos;
                pasos = button - posO;
                posO = button;
                if(pasos < 0)
                    pasos*=(-1);
                if(prevSelec){
                    if (pasosAnteriores<pasos){
                        s+=(pasos-pasosAnteriores+1);
                        s--;
                    }
                    else
                        s++;
                }
                else{
                    s+=pasos;
                    s++;
                }
            }
            else if(selec == 'B' || selec == 'b'){
                prevSelec = false;
                pasos = button - posB;

                posB = button;
                if(pasos < 0)
                    pasos*=(-1);
                if(!prevSelec){
                    if (pasosAnteriores<pasos){
                        s+=(pasos-pasosAnteriores);
                        s--;
                    }
                    else{
                        s++;
                    }
                }
                else{
                    s+=pasos;

                    s++;
                }
            }
            
        }
        escribir<<"Case #"<<(i+1)<<": "<<s<<endl;
    }
}
