#include<iostream>
using namespace std;

bool canDo(int *posiciones, int *combustible, int i, int s, int opcion);

int main(){
    int c, s, i, j, temp, run=0;
    bool cw, cc;
    
    cin>>c>>s;
    
    while(c>0 && s>0){
        int posiciones[s];
        int combustible[s];
        
        for(i=0;i<s;i++)
            cin>>posiciones[i]>>combustible[i];
        
        for(i=0;i<s;i++)
            for(j=0;j<s;j++)
                if(posiciones[j]>posiciones[i]){
                    temp = posiciones[i];
                    posiciones[i] = posiciones[j];
                    posiciones[j] = temp;
                    temp = combustible[i];
                    combustible[i] = combustible[j];
                    combustible[j] = temp;
                }
        
        cout<<"Case "<<++run<<":";
        
        for(i=0;i<s;i++){
            cw = false;
            cc = false;
            cw = canDo(posiciones, combustible, i, s, 1);
            cc = canDo(posiciones, combustible, i, s, -1);
            if(cw && cc)
                cout<<" "<<posiciones[i]<<"CCC";
            else if(cc)
                cout<<" "<<posiciones[i]<<"CC";
            else if(cw)
                cout<<" "<<posiciones[i]<<"C";
        }
        
        cout<<endl;
                        
        cin>>c>>s;
    }
    
    return 0;
}

bool canDo(int *posiciones, int *combustible, int i, int s, int opcion){//opcion = 1 -> clockwise, opcion = -1 -> counterClockwise
    
    int tank=0, cont=0, index=i;
    bool iniciado = false;
    
    while((tank>0 && cont<s) || !iniciado){
        iniciado = true;
        tank += combustible[index];
        tank -= posiciones[(index+opcion+s)%s]-posiciones[index];
        index+=opcion;
        index+=s;
        index%=s;
        cont++;
    }
    
    if (cont==s)
        return true;
    else
        return false;
}
