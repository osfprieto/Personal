#include<iostream>
#include<string.h>
#include<conio.h>
using namespace std;

void magicMat();

int main(){
    system("color c");
    //variables de "recorrido"
    int n=4, i, j, k;
    //variables temporales de ingreso y operación de datos
    double notaPromedio;
    string temp;
    float tempNota;
    char selec;
    //variables de almacenamiento
    string nombres[n];
    float notas[n];
    //variable para saber si ya hay datos de estudiantes ingresados y manejo de errores
    bool ingresados = false, error = false;
    ini:
        cout<<"Hoy tenemos capacidad de proceso para "<<n<<" estudiantes!"<<endl;
        cout<<"Por favor entre su seleccion:"<<endl<<endl;
        cout<<"A. Ingreso de los estudiantes."<<endl;
        cout<<"B. Ver estudiantes por orden de nombres en la lista."<<endl;
        cout<<"C. Ver estudiantes por orden de notas de menor a mayor."<<endl;
        cout<<"D. Promedio de estudiantes."<<endl;
        cout<<"E. Matriz Magica."<<endl;
        cout<<"F. Salir"<<endl;
        if(error){
            cout<<endl<<"Entrada no valida. Intente de nuevo."<<endl;
            error = false;
        }
        selec = getch();
        system("cls");
        if(selec=='A' || selec=='a')
            goto ingresoDatos;
        else if((selec=='B' || selec=='b') && ingresados)
            goto verOrdenNombre;
        else if((selec=='C' || selec=='c') && ingresados)
            goto verOrdenNotas;
        else if((selec=='D' || selec=='d') && ingresados)
            goto verPromedio;
        else if(selec=='E' || selec=='e')
            goto matrizMagica;
        else if(selec=='F' || selec=='f')
            goto exit;
        else
            error = true;
            goto ini;
    ingresoDatos:
        for(i=0;i<n;i++){
            cout<<"Nombre "<<(i+1)<<':'<<' ';
            getline(cin, nombres[i], '\n');
            notas[i]=-1;
            while(notas[i]<0 || notas[i]>5.0){
                cout<<"Nota parcial de "<<nombres[i]<<':'<<endl;
                cin>>notas[i];
                if(notas[i]<0 || notas[i]>5.0){
                    cout<<"Nota Ingresada no valida, ingresela de nuevo:"<<endl;
                }
            }
        }
        ingresados = true;
        cout<<"Datos Ingresados correctamente.\nPresione cualquier tecla para continuar.";
        selec = getch();
        goto reIni;
        
    verOrdenNombre:
        
        for(i=0;i<n;i++){
            for(j=i;j<n;j++){
                if((nombres[i].compare(nombres[j]))>0){
                    
                    temp = nombres[i];
                    nombres[i] = nombres[j];
                    nombres[j] = temp;
                    
                    tempNota = notas[i];
                    notas[i] = notas[j];
                    notas[j] = tempNota;
                }
            }
        }
        cout<<"A continuacion estan los estudiantes ordenados por nombre:"<<endl<<endl;
        cout<<"Estudiante\t\tNota"<<endl<<endl;
        for(i=0;i<n;i++){
            cout<<nombres[i]<<'\t'<<notas[i]<<endl;
        }
        goto reIni;
        
    verOrdenNotas:
        
        for(i=0;i<n;i++){
            for(j=i;j<n;j++){
                if(notas[i]>notas[j]){
                    
                    tempNota = notas[i];
                    notas[i]= notas[j];
                    notas[j] = tempNota;
                    
                    temp = nombres[i];
                    nombres[i] = nombres[j];
                    nombres[j] = temp;
                }
            }
        }
        cout<<"A continuacion estan los estudiantes ordenados por nota:"<<endl<<endl;
        cout<<"Estudiante\t\tNota"<<endl<<endl;
        for(i=0;i<n;i++){
            cout<<nombres[i]<<'\t'<<notas[i]<<endl;
        }
        goto reIni;
        
    verPromedio:
        
        for(i=0;i<n;i++){
            for(j=i;j<n;j++){
                if((nombres[i].compare(nombres[j]))>0){
                    
                    temp = nombres[i];
                    nombres[i] = nombres[j];
                    nombres[j] = temp;
                    
                    tempNota = notas[i];
                    notas[i] = notas[j];
                    notas[j] = tempNota;
                }
            }
        }
        cout<<"A continuacion estan los estudiantes:"<<endl;
        cout<<"Estudiante\t\tNota"<<endl<<endl;
        notaPromedio = 0;
        for(i=0;i<n;i++){
            cout<<nombres[i]<<'\t'<<notas[i]<<endl;
            notaPromedio+=notas[i];
        }
        notaPromedio/=n;
        cout<<"La nota promedio de los estudiantes es de: "<<notaPromedio<<endl;
        goto reIni;
        
    matrizMagica:
        /////////////////////////////////////////////////
        magicMat();
        /////////////////////////////////////////////////
        goto reIni;
        
    reIni:
        cout<<endl<<endl<<"Desea volver al menu principal?\n\n\tY / N"<<endl;
        selec = getch();
        system("cls");
        if(selec=='Y' || selec=='y')
            goto ini;
        else
            goto exit;
    exit:
        cout<<endl<<endl<<"Esta seguro de que desea salir?\n\n\tY / N"<<endl;
        selec = getch();
        system("cls");
        if(selec=='Y' || selec=='y')
            return 0;
        else
            goto ini;
}

void magicMat(){
int m, sumauno, l, x, y, z, i, j;
        int cont=0, contdos=0, sumados=0, sumatres=0, sumacuatro=0, A=0, k=0;
        cout<<"Ingrese el tamanio de su matriz:"<<endl;
        cin>>m;
        l=m*2+2;
        int magica[m][m];
        int arreglo[l];
        int arreglodos[l+1];
        int arr[m*m];
        bool ver=true;
        cout<<"Ingrese los numero de sus matriz. Ordenelos primero la fila 1, luego la fila 2, hasta fila n. Recuerde que los numeros no se deben repetir"<<endl;
        for(i=0; i<=m-1; i++){
            for(j=0;j<=m-1;j++){
                magica[i][j]=-1;
            }
        }//inicializa matriz en -1
        for(k=0;k<=m*m-1;k++){
            arr[k]=-2;
        }             
        for(i=0; i<m; i++){
            for(j=0;j<m;j++){
                cout<<"ingrese posicion ("<<(i+1)<<","<<(j+1)<<"):";
                ver=true;
                while(ver==true){
                    cin>>magica[i][j];
                    arr[A]=magica[i][j];
                    cont=0;
                    for(k=0; k<m*m; k++){
                        if (magica[i][j]!=arr[k]){
                            x=0;
                        }
                        else{
                            x=1;
                        }
                        cont=cont+x;
                    }
                    if(cont>1){
                        ver=true;
                        cout<<"Este numero ya fue ingresado, por favor vuelva a ingresarlo"<<endl;
                        cout<<"posisicion("<<(i+1)<<","<<(j+1)<<"):";
                    }
                    else{
                        ver=false;
                    }
                    A=A+1;
                }
            }//condicional
        }// hasta aqui asignacion de valor de la matriz
        k=0;             
        for(i=0;i<m;i++){
            sumauno=0;
            for(j=0;j<m;j++){
                sumauno=sumauno+magica[i][j];
            }
            arreglo[k]=sumauno;
            k++;
        }//hasta aqui halla suma de columnas
        for(i=0;i<m;i++){
            sumados=0;
            for(j=0;j<m;j++){
                sumados=sumados+magica[j][i];
            }
            arreglo[k]=sumados;
            k++;
        }//hasta aqui halla suma filas
        j=0;                         
        for(i=0;i<m;i++){
            sumatres=sumatres+magica[i][j];
            j++;
        }
        arreglo[k]=sumatres;
        k++;//hasta aqui primer diagonal
           
        for(i=0;i<m;i++){
            m=2;
            sumacuatro=sumacuatro+magica[i][m];
            m=m-1;
        }
        arreglo[k]=sumacuatro;//hasta aqui segunda diagonal
        //comenzamos a comparar para ver si es magica o no
        for(k=1;k<m;k++){
            arreglodos[k]=arreglo[k-1];
        }//otro arreglo que nos ayuda a comparar
        for(k=1;k<m;k++){
            i=0;
            if(arreglo[i]!=arreglodos[k]){
                y=1;
            }
            else{
                y=0;
            }
            contdos+=y;
        }
        if(contdos>0){
            cout<<"la matriz ingresada no es magica"<<endl;
        }
        else{
            cout<<"La matriz ingresada  es magica"<<endl;
        }  
}
