/*
calle 13 con avenida cali a las 8 de la mañana
sobre la trece pasando la cali
debajo del puente
John Florez Pantalón negro zapatos negros
toyota roja con estacas
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define RIGHT 1
#define DOWN 2
#define LEFT 3
#define UP 4

#define BALDOSA_LIBRE 0
#define AZUL 1
#define SIN_BALDOSA 2
#define BALDOSA_CAMINADA 3
#define JUAN 4

//Variables globales

int **cuarto;
char *msg;

int ancho;
int alto;

int anchoCorte;
int altoCorte;

int filAzul;
int colAzul;

int posFil;
int posCol;

int cantidad;
int cantidadReal;

int direccion;

//Funciones y procedimientos

void llenarMatrizCuarto();
void encontrarPosicionInicial();
int caminar(int cantidad);
void imprimir();
void resultados();
bool finaliza();
void darPaso();
void clockwiseTurn();
void counterClockwiseTurn();
bool obstaculoIzquierda();
bool obstaculoFrente();
bool obstaculoFrenteIzquierda();

int main(){
    
    llenarMatrizCuarto();
    encontrarPosicionInicial();
    //imprimir();
    caminar(cantidad);
    resultados();
    delete[] cuarto;
    system("pause");
    return 0;
}

/*
llnarMatriz Cuarto llena la matriz de datos con las baldosas después de pedir
los datos de input al usuario por la consola cada vez que se entran los datos
verifica que se hayan recibido los tipos de dato correctos, no verifica los límites
*/

void llenarMatrizCuarto(){
    int n, i, j;
    /*if(scanf("%d", &ancho)==1)
        printf("si!\t%d", ancho);*/
    printf("Por favor entre el ancho y alto del cuarto de Juan\n");
    n = scanf("%d %d", &ancho, &alto);
    if(n!=2){
        msg = new char[strlen("Datos del tamanio del caurto leidos incorrectamente")+1];
        msg = "Datos del tamanio del caurto leidos incorrectamente";
        printf("%s\n", msg);
        delete[] msg;
        return;
    }
    //printf("%d, %d\n", ancho, alto);
    printf("Por favor entre el ancho y alto del cuadro de corte de Juan\n");
    n = scanf("%d %d", &anchoCorte, &altoCorte);
    if(n!=2){
        msg = new char[strlen("Datos del tamanio del cuadro de corte leidos incorrectamente")+1];
        msg = "Datos del tamanio del caurto leidos incorrectamente";
        printf("%s\n", msg);
        delete[] msg;
        return;
    }
    //printf("%d, %d\n", anchoCorte, altoCorte);
    printf("Por favor entre la cantidad de casillas que Juan quiere caminar\n");
    n = scanf("%d", &cantidad);
    if(n!=1){
        msg = new char[strlen("Numero de casillas leido incorrectamente")+1];
        msg = "Numero de casillas leido incorrectamente";
        printf("%s\n", msg);
        delete[] msg;
        return;
    }
    //printf("%d\n", cantidad);
    printf("Por favor entre la fila y columna de la casilla azul de Juan\n");
    n = scanf("%d %d", &filAzul, &colAzul);
    if(n!=2){
        msg = new char[strlen("Datos de la casilla azul de Juan leidos incorrectamente")+1];
        msg = "Datos de la casilla azul de Juan leidos incorrectamente";
        printf("%s\n", msg);
        delete[] msg;
        return;
    }
    //printf("%d, %d\n", filAzul, colAzul);
    
    filAzul--;
    colAzul--;
    
    system("cls");
    /*
    En el siguiente bloque de ciclos se encarga de crear el espacio en memoria
    para la matriz, ya que se está usando memoria dinámica, y por lo tanto la
    matriz se ve accesada a través apuntadores y llenarlo de baldosas libres
    */
    cuarto = new int*[alto];
    for(i=0; i<alto;i++){
        cuarto[i] = new int[ancho];
        for(j=0;j<ancho;j++)
            cuarto[i][j]=BALDOSA_LIBRE;
    }
    
    //se ubica la casilla azul
    cuarto[filAzul][colAzul] = AZUL;
    system("pause");
    //En el siguiente bloque de ciclos, se quitan las baldosas de las esquinas
    for(i=0;i<altoCorte;i++){
        for(j=0;j<anchoCorte;j++){
            cuarto[i][j]=SIN_BALDOSA;
            cuarto[i][ancho-1-j]=SIN_BALDOSA;
            cuarto[alto-1-i][j]=SIN_BALDOSA;
            cuarto[alto-1-i][ancho-1-j]=SIN_BALDOSA;
        }
    }
}
/*
esta función imprime en pantalla una representación de la matriz que se encuetre
actualmente en memoria representando al cuarto de Juan
*/
void imprimir(){
    system("cls");
    int i, j;
    for(i=0;i<alto;i++){
        for(j=0;j<ancho;j++){
            if(cuarto[i][j]==BALDOSA_LIBRE)
                printf("%c", ' ');
            else if(cuarto[i][j]==BALDOSA_CAMINADA)
                printf("%c", '+');
            else if(cuarto[i][j]==SIN_BALDOSA)
                printf("%c", 219);
            else if(cuarto[i][j]==AZUL)
                printf("%c", 'A');
            else if(cuarto[i][j]==JUAN)
                if(direccion == RIGHT)
                    printf("%c", '>');
                else if(direccion == LEFT)
                    printf("%c", '<');
                else if(direccion == DOWN)
                    printf("%c", 'V');
                else if(direccion == UP)
                    printf("%c", '^');
        }
        printf("\n");
    }
}
/*
La funcion encontrarPosicionInical halla la primera baldosa libre para que Juan
pueda caminar
*/
void encontrarPosicionInicial(){
    int i;
    posFil = 0;
    for(i=0;i<ancho;i++)
        if(cuarto[0][i] == BALDOSA_LIBRE){
            posCol = i;
            cuarto[posFil][posCol] = JUAN;
            direccion = RIGHT;
            return; 
        }
}
/*
la función caminar se encarga de dirigir a Juan a través del cuarto buscando los
obstáculos
*/
int caminar(int cantidad){
    cantidadReal = 0;
    while(!finaliza()){
        imprimir();
        system("pause");
        if(obstaculoIzquierda()){
            if(obstaculoFrente())
                clockwiseTurn();
            else
                darPaso();
        }
        else if(obstaculoFrenteIzquierda())
            darPaso();
        else{
            counterClockwiseTurn();
            darPaso();
        }
    }
    return cantidadReal;
}
/*
La función resultados se encaga de mostrar los resultados requeridos por las
especificaciones para la muestra en pantalla de cada uno, aquí también se
calculan algunos de los mismos.
*/
void resultados(){
    system("cls");
    printf("Posicion final de Juan\nFila\t%d\n\t%d\n", (posFil+1), (posCol+1));
    
    if(cantidadReal == cantidad){
        msg = new char[strlen("Juan camino sobre las baldosas que queria!")+1];
        msg = "Juan camino sobre las baldosas que queria!";
        printf("%s\n", msg);
        delete[] msg;
    }
    else{
        msg = new char[strlen("Juan no camino sobre las baldosas que queria")+1];
        msg = "Juan no camino sobre las baldosas que queria";
        printf("%s\n", msg);
        delete[] msg;
    }
    
    int noCaminadas = 0, i, j;
    
    for(i=0;i<alto;i++)
        for(j=0;j<ancho;j++)
            if(cuarto[i][j]==BALDOSA_LIBRE)
                noCaminadas++;
    
    printf("Juan no camino sobre %d de las baldosas\n", noCaminadas);
    
}
/*
La funcion finaliza retorna un valor booleano que es true en caso de que
Juan ya halla llegado a la casilla azul
*/
bool finaliza(){
    if(direccion == RIGHT)
        if(cuarto[posFil][posCol+1]==AZUL)
            return true;
        else
            return false;
    else if(direccion == LEFT)
        if(cuarto[posFil][posCol-1]==AZUL)
            return true;
        else
            return false;
    if(direccion == UP)
        if(cuarto[posFil-1][posCol]==AZUL)
            return true;
        else
            return false;
    if(direccion == DOWN)
        if(cuarto[posFil+1][posCol]==AZUL)
            return true;
        else
            return false;
    else
        return false;
}
/*
La función darPaso se encarga de avanzar una casilla a Juan hacia la dirección
que tenga, dejando las baldosas anteriores como caminadas*/
void darPaso(){
    cuarto[posFil][posCol] = BALDOSA_CAMINADA;
    if(direccion == RIGHT)
        posCol++;
    else if(direccion == LEFT)
        posCol--;
    else if(direccion == UP)
        posFil--;
    else if(direccion == DOWN)
        posFil++;
    cuarto[posFil][posCol] = JUAN;
    cantidadReal++;
}
/*
gira a Juan en el sentido de las manecillas del reloj
*/
void clockwiseTurn(){
    direccion%=4;
    direccion++;
}
/*
gira a Juan en contra de las manecillas del reloj
*/
void counterClockwiseTurn(){
    for(int i=0;i<3;i++)
        clockwiseTurn();
}
/*
la función obstaculoIzquierda retorna verdadero y Juan tiene una pared o baldosa
caminada a su izquierda con respecto a la dirección que está mirando
*/
bool obstaculoIzquierda(){
    if(direccion == RIGHT)
        if(posFil == 0 || cuarto[posFil-1][posCol]==SIN_BALDOSA || cuarto[posFil-1][posCol]==BALDOSA_CAMINADA)
            return true;
        else
            return false;
    else if(direccion == LEFT)
        if(posFil == alto-1 || cuarto[posFil+1][posCol]==SIN_BALDOSA || cuarto[posFil+1][posCol]==BALDOSA_CAMINADA)
            return true;
        else
            return false;
    else if(direccion == UP)
        if(posCol == 0 || cuarto[posFil][posCol-1]==SIN_BALDOSA || cuarto[posFil][posCol-1] == BALDOSA_CAMINADA)
            return true;
        else
            return false;
    else if(direccion == DOWN)
        if (posCol == ancho-1 || cuarto[posFil][posCol+1]==SIN_BALDOSA || cuarto[posFil][posCol+1]==BALDOSA_CAMINADA)
            return true;
        else
            return false;
    else
        return false;
}

/*
la función obstaculoFrente retorna verdadero y Juan tiene una pared o baldosa
caminada en frente con respecto a la dirección que está mirando
*/
bool obstaculoFrente(){
    if(direccion == RIGHT)
        if(posCol == ancho-1 || cuarto[posFil][posCol+1]==SIN_BALDOSA || cuarto[posFil][posCol+1]==BALDOSA_CAMINADA)
            return true;
        else
            return false;
    else if(direccion == LEFT)
        if(posCol == 0 || cuarto[posFil][posCol-1]==SIN_BALDOSA || cuarto[posFil][posCol-1]==BALDOSA_CAMINADA)
            return true;
        else
            return false;
    else if(direccion == UP)
        if(posFil == 0 || cuarto[posFil-1][posCol]==SIN_BALDOSA || cuarto[posFil-1][posCol] == BALDOSA_CAMINADA)
            return true;
        else
            return false;
    else if(direccion == DOWN)
        if (posFil == alto-1 || cuarto[posFil+1][posCol]==SIN_BALDOSA || cuarto[posFil+1][posCol]==BALDOSA_CAMINADA)
            return true;
        else
            return false;
    else
        return false;
}
/*
la función obstaculoFrenteIzquierda retorna verdadero y Juan tiene una pared o baldosa
caminada en la baldosa diagonal al frente a la izquierda con respecto a la dirección
que está mirando
*/
bool obstaculoFrenteIzquierda(){
    if(direccion == RIGHT)
        if(cuarto[posFil-1][posCol+1]==SIN_BALDOSA || cuarto[posFil-1][posCol+1]==BALDOSA_CAMINADA)
            return true;
        else
            return false;
    else if(direccion == LEFT)
        if(cuarto[posFil+1][posCol-1]==SIN_BALDOSA || cuarto[posFil+1][posCol-1]==BALDOSA_CAMINADA)
            return true;
        else
            return false;
    else if(direccion == UP)
        if(cuarto[posFil-1][posCol-1]==SIN_BALDOSA || cuarto[posFil-1][posCol-1] == BALDOSA_CAMINADA)
            return true;
        else
            return false;
    else if(direccion == DOWN)
        if (cuarto[posFil+1][posCol+1]==SIN_BALDOSA || cuarto[posFil+1][posCol+1]==BALDOSA_CAMINADA)
            return true;
        else
            return false;
    else
        return false;
}
