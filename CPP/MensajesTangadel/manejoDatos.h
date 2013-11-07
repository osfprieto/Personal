/*
A través del archivo "manejoDatos.h" se maneja toda la información con
respecto al programa, desde el momento en el que un usuario se registra
hasta el momento de manipular los mensajes entre ususarios, este archivo
en general usa las funciones de fstream para el manejo de la información
con los archivos y se entiende con los usuarios a través de las funciones
registradas en el archivo "manejoInputs.h"
*/

#include<fstream>
#include<string.h>
#include<windows.h>
#include"manejoInputs.h"
using namespace std;

void ingresar();
void registrar();
void aplicaciones(const string usuario);
void alertar(char *msg);
void menu(const string usuario);
void escribirMensaje(const string usuario);
void leerMensajes(const string usuario);
void eliminarMensajes(const string usuario);
bool fechaValida(int birthdateDay,int birthdateMonth,int birthdateYear);
void escribirMensaje(const string usuarioRemitente);
void horoscopo(const string usuario);
void laberinto(const string usuario);
void numeros(const string usuario);
int asignarSigno(int dia, int mes);
int asignarSignoBackUp();

/*
La función "ingresar" se encarga de recibir el usuario y password de un
usuario, si este se encuentra registrado y el password concuerda con el registro,
se ve dirigido a la función "menu" con este usuario como argumento
*/
void ingresar(){
    system("cls");
    cout<<endl<<endl<<endl<<"\t\t\tIngresa tu usuario"<<endl<<endl<<"\t\t\t";
    string usuarioInput = recibirPalabra();
    
    system("cls");
    string passInput = recibirPassword();
    
    string fileName = nombrarArchivo(usuarioInput);
    char nombreArchivo[50];
    int i=0;
    while(fileName[i]!=0){
        nombreArchivo[i] = fileName[i];
        i++;
    }
    nombreArchivo[i]=0;
    
    fstream leer;//file stream, tiene la capacidad de leer y escribir en archivos
    leer.open(nombreArchivo, ios::in);//especifica el manejo de leer, en este caso como un input(recibe algo)        
    system("cls");
    string usuario, pass;
    leer>>usuario>>pass;
    
    char usuarioChar[75], usuarioInputChar[75], passChar[75], passInputChar[75];
    //
    i=0;
    while(usuario[i]!=0){
        usuarioChar[i] = usuario[i];
        i++;
    }
    usuarioChar[i]=0;
    
    i=0;
    while(usuarioInput[i]!=0){
        usuarioInputChar[i] = usuarioInput[i];
        i++;
    }
    usuarioInputChar[i]=0;
    
    i=0;
    while(pass[i]!=0){
        passChar[i] = pass[i];
        i++;
    }
    passChar[i]=0;
    
    i=0;
    while(passInput[i]!=0){
        passInputChar[i] = passInput[i];
        i++;
    }
    passInputChar[i]=0;
    
    if(strcmp(usuarioChar, usuarioInputChar)==0 && strcmp(passChar, passInputChar)==0){
        menu(usuario);
    }
    else
        alertar("Usuario o contraseña errados.");
}

/*
La función "registrar" se encarga de recibir los datos de un nuevo usuario
para registrarlo en los archivos auxiliares del programa para su posterior uso
esta se encarga de recibir tanto los datos relevantes como el usuario y contraseña
como algunos no tanto como bien son la fecha de cumpleaños y el correo
electrónico. Algunos de estos datos necesitan validación como en el caso que una
fecha sea apropiada o que un correo contenga el caracter '@' por lo que en el
caso en el que sean ingresados erróneamente, estos se vuelven a pedir al usuario
*/
void registrar(){
        int birthdateDay, birthdateMonth, birthdateYear;
        string email;
    iniRegistrar:
        system("cls");
        cout<<endl<<endl<<endl<<" \t\t\tIngresa tu usuario"<<endl<<endl<<"\t\t\t";
        string usuario = recibirPalabra();
        
        system("cls");
        string pass = recibirPassword();
    
    recibirMail:
    
        system("cls");
        cout<<endl<<endl<<endl<<"\t\t\tIngresa tu e-mail"<<endl<<endl<<"\t\t\t";
        email = recibirPalabra();
        
        int i=0;
        while(email[i]!=0){
            if(email[i]=='@')
                break;
            i++;
            if(email[i]==0){
                alertar("Correo ingresado inválido!\nTenga en cuenta el @.");
                goto recibirMail;
            }
        }
    
    recibirCumpleanios:
    
        system("cls");
        cout<<endl<<endl<<endl<<"\t\t\tIngresa tu fecha de cumpleanios"<<endl<<endl<<"\t\tdia: ";
        cin>>birthdateDay;
        cout<<endl<<"\t\tmes: ";
        cin>>birthdateMonth;
        cout<<endl<<"\t\tanio: ";
        cin>>birthdateYear;
        
        if(!fechaValida(birthdateDay, birthdateMonth, birthdateYear)){
            alertar("Fecha de cumpleaños no válida!");
            goto recibirCumpleanios;
        }
        
        string fileName = nombrarArchivo(usuario);
        char nombreArchivo[50];
        i=0;
        while(fileName[i]!=0){
            nombreArchivo[i] = fileName[i];
            i++;
        }
        nombreArchivo[i]=0;
        
        fstream escribir, leer;
        leer.open(nombreArchivo, ios::in);
        string usuarioExiste;
        leer>>usuarioExiste;
        leer.close();
        if(usuarioExiste == ""){
            escribir.open(nombreArchivo, ios::out);
            escribir<<usuario<<endl;
            escribir<<pass<<endl;
            escribir<<email<<endl;
            escribir<<birthdateDay<<endl;
            escribir<<birthdateMonth<<endl;
            escribir<<birthdateYear<<endl;
            escribir.close();
        }
        else{
            alertar("Este usuario ya ha sido registrado.");
            return;
        }
        
        system("cls");
        alertar("Usuario Registrado con éxito!");
}

/*
La cunción "alertar" se encarga de recibir un parámetro mensaje, el cúal es mostrado
en un cuadro de aviso através de la función "MessageBox" de "windows.h".
Esta función es utilizada para ganeralizar los casos en los que se deben dar avisos
al usuario y hacerlo de una manera que conserve el mismo estilo y estructura.
De la misma manera, esta estrucutra ahorra algo de tiempo al momento de programar
y evita cambios en los modos de aviso
*/
void alertar(char *msg = "Algo ha sucedido"){
    MessageBox(0, msg, "Atención!",
               MB_OK | MB_ICONINFORMATION);//MB_OK: botón de aceptar
                                    //MB_ICONERROR: ícono de error, la crucecita roja
                                    //MB_ICONINFORMATION: ícono de información, signo de admiración azul
    /*system("cls");
    cout<<endl<<"Error --- "<<msg<<endl;
    getch();
    system("cls");*/
}

/*
La función "menu" se encarga de mostrarle el menu personal al usuario
que haya podido ingresar exitosamente al sistema, desde aquí el usuario puede
elegir entre cuatro opciones: escribir mensaje, leer mensajes, borrar mensajes
o cerrar sesión, cada una de las tres primeras opciones redirigen a una función
con un nombre alusivo a esta opcion en donde se desarrolla la petición como tal.
Por otro lado, al momento de seleccionar cerrar sesión la función retorna a la
función main 
*/
void menu(const string usuario){//vamos acá, toca mirar esto
    char seleccionMenu;
    iniMenu:
        system("cls");
        cout<<endl<<endl<<endl<<"\t\t\tMENU "<<usuario<<endl<<endl;
        cout<<"\t\t1. Escribir mensaje."<<endl;
        cout<<"\t\t2. Leer Mensajes."<<endl;
        cout<<"\t\t3. Eliminar Mensajes."<<endl;
        cout<<"\t\t4. Aplicaciones."<<endl;
        cout<<"\t\t5. Cerrar sesion."<<endl;
        seleccionMenu = getch();
        if(seleccionMenu == '1')
            escribirMensaje(usuario);
        else if(seleccionMenu == '2')
            leerMensajes(usuario);
        else if(seleccionMenu == '3')
            eliminarMensajes(usuario);
        else if(seleccionMenu == '4')
            aplicaciones(usuario);
        else if(seleccionMenu == '5')
            goto salirMenu;
        goto iniMenu;
    salirMenu:
        system("cls");
        cout<<endl<<endl<<endl<<"\t\t\t¿Cerrar sesion?"<<endl<<endl;
        cout<<"\t\t1. Si."<<endl;
        cout<<"\t\t2. No."<<endl;
        seleccionMenu = getch();
        if(seleccionMenu == '2')
            goto iniMenu;
        else if(seleccionMenu != '1')
            goto salirMenu;
}

/*
La función "leerMensajes" se encarga de leer desde el archivo del usuario
los mensajes que tenga almacenados para imprimirlos en pantalla uno por uno
*/
void leerMensajes(const string usuario){
    system("cls");
    char temp[152];
    //int n;
    string fileName = nombrarArchivo(usuario);
    char nombreArchivo[50];
    int i=0;
    while(fileName[i]!=0){
        nombreArchivo[i] = fileName[i];
        i++;
    }
    nombreArchivo[i]=0;
    
    fstream leer;
    
    leer.open(nombreArchivo, ios::in);
    
    leer.getline(temp, 70);//usuario
        //cout<<temp<<endl;
    leer.getline(temp, 80);//contrseña
        //cout<<temp<<endl;
    leer.getline(temp, 10);//email
        //cout<<temp<<endl;
    leer>>temp;//dia
        //cout<<temp<<endl;
    leer>>temp;//mes
        //cout<<temp<<endl;
    leer>>temp;//año
        //cout<<temp<<endl;
        //alertar("1");
    leer.getline(temp, 70);
        //cout<<temp<<endl;
        //alertar("2");
    leer.getline(temp, 70);
    while(temp[0]!=0){
            //alertar("3");
        system("cls");
        cout<<endl<<"De: "<<temp<<endl;
        leer.getline(temp, 80);
        cout<<"Asunto: "<<temp<<endl;
        leer.getline(temp, 152);
            //alertar("4");
        while(temp[0]!='.'){
                //alertar("5");
            cout<<temp<<endl;
            leer.getline(temp, 152);
        }
        getch();
        leer.getline(temp, 70);
    }
    alertar("Ya has visto todos los mensajes!");
}

/*
La función "eliminarMensajes" se encarga de borrar todos los mensajes que tenga
un usuario al momento de llamar a esta opción
*/
void eliminarMensajes(const string usuario){
    char seleccionBorrar;
    iniDelete:
        system("cls");
        cout<<endl<<endl<<endl<<"\t\t\t¿Eliminar Mensajes?"<<endl<<endl;
        cout<<"\t\t\t1. Si."<<endl;
        cout<<"\t\t\t2. No."<<endl;
        seleccionBorrar = getch();
        
        if(seleccionBorrar == '2')
            return;
        else if(seleccionBorrar != '1')
            goto iniDelete;
        
        string fileName = nombrarArchivo(usuario);
        char nombreArchivo[50];
        int i=0;
        while(fileName[i]!=0){
            nombreArchivo[i] = fileName[i];
            i++;
        }
        nombreArchivo[i]=0;
        
        fstream escribir, leer;
        
        char usuarioFile[75], pass[75], email[75];
        int birthdateDay, birthdateMonth, birthdateYear;
        
        leer.open(nombreArchivo, ios::in);
        leer>>usuarioFile;
        leer>>pass;
        leer>>email;
        leer>>birthdateDay;
        leer>>birthdateMonth;
        leer>>birthdateYear;
        leer.close();
        
        escribir.open(nombreArchivo, ios::out);
        escribir<<usuario<<endl;
        escribir<<pass<<endl;
        escribir<<email<<endl;
        escribir<<birthdateDay<<endl;
        escribir<<birthdateMonth<<endl;
        escribir<<birthdateYear<<endl;
        escribir.close();
        
        alertar("Mensajes eliminados con éxito!");
}

/*
la función "fechaValida" se encarga de decir si la fecha ingresada como parámetros
en día, mes y año, es válida, tomando en cuenta rangos mayores a cero, meses de un año
días de un mes con respecto al mes y si es añor bisiesto o no.
*/
bool fechaValida(int birthdateDay,int birthdateMonth,int birthdateYear){
    if(birthdateDay < 1 ||  birthdateMonth < 1 || birthdateYear<0 || birthdateMonth > 12)
        return false;
    
    if(birthdateMonth == 2){
        if((birthdateYear % 4 == 0 && birthdateYear % 100 != 0) || birthdateYear % 400 == 0){
            return ((birthdateDay>29)?false:true);
        }
        else{
            return ((birthdateDay>28)?false:true);
        }
    }
    else if(birthdateMonth == 1 || birthdateMonth == 3 || birthdateMonth == 5 || birthdateMonth == 7 ||birthdateMonth == 8 ||birthdateMonth == 10 ||birthdateMonth == 12){
        return ((birthdateDay>31)?false:true);
    }
    else if(birthdateMonth == 4 || birthdateMonth == 6 || birthdateMonth == 9 || birthdateMonth == 11){
        return ((birthdateDay>30)?false:true);
    }
}

/*
La función "escribirMensaje" se encarga de recibir el mensaje de un usuario remitente
y gaurdarlo en el perfil del usuario destinatario con la información del remitente
*/
void escribirMensaje(const string usuarioRemitente){
    
    fstream leer, escribir;
    string usuarioDestinatario, asunto, temp;
    char nombreArchivo[50];
    
    inicioEscribirMensaje:
    
        system("cls");
        cout<<endl<<endl<<endl<<"\t\t\tIngrese el usuario del destinatario"<<endl<<endl;
        usuarioDestinatario = recibirPalabra();
        
        string fileName = nombrarArchivo(usuarioDestinatario);
        int i=0;
        while(fileName[i]!=0){
            nombreArchivo[i] = fileName[i];
            i++;
        }
        nombreArchivo[i]=0;
        
        leer.open(nombreArchivo, ios::in);
        leer>>temp;
        leer.close();
        if(temp == ""){
            alertar("Este usuario no existe!");
            return;
        }
        
        escribir.open(nombreArchivo, ios::out | ios::app);//app = append ~ applicate at end
        
        system("cls");
        cout<<endl<<endl<<endl<<"\t\t\tIngrese el asunto:"<<endl<<endl;
        asunto = recibirPalabra();
        
        escribir<<usuarioRemitente<<endl;
        escribir<<asunto<<endl;
        
        system("cls");
        cout<<"Ingrese su mensaje, maximo 20 lineas, para finalizar antes ingrese una linea con solo \'.\'"<<endl;
        
        for(int i=0;i<20;i++){
            temp = recibirLinea(150);
            if(temp[0]=='.')
                break;
            escribir<<temp<<endl;
        }
        escribir<<'.'<<endl;
        escribir.close();
        alertar("Mensaje Enviado!");
}
/*
En esta función se destacan todas las aplicaciones dispobibles para un usuario
Esta se encarga de mostrarle al usario estas aplicacinoes para el elija a su
gusto, al seleccionar, esta llama a la aplicación seleccionada como tal para que
se ejecute en la consola
*/

void aplicaciones(const string usuario){
    char seleccion;
    iniAplicaciones:
        system("cls");
        cout<<endl<<endl<<endl<<"\t\t\tAPLICACIONES "<<usuario<<endl<<endl;
        
        cout<<"\t\t\t1. Horoscopo."<<endl;
        cout<<"\t\t\t2. Laberinto."<<endl;
        cout<<"\t\t\t3. Tus numeros de hoy."<<endl;
        cout<<"\t\t\t4. Volver."<<endl;
        
        seleccion = getch();
        
        if(seleccion == '1')
            horoscopo(usuario);
        else if(seleccion == '2')
            laberinto(usuario);
        else if(seleccion == '3')
            numeros(usuario);
        else if(seleccion == '4')
            return;
        goto iniAplicaciones;
}

/*
La función Horoscopo se encarga de leer de un archivo al azar de los disponibles
una descripición de Horóscopo con respecto a la fecha de nacimiento de el usuario
para imprirla en pantalla
*/
void horoscopo(const string usuario){
    char temp[512];
    string signos[12] = {"ARIES", "TAURO", "GEMINIS", "CANCER", "LEO", "VIRGO", "LIBRA", "ESCORPION", "SAGITARIO", "CAPRICORNIO", "ACUARIO", "PISCIS"};
    string fileName = nombrarArchivo(usuario);
    int dia, mes;
    fstream leer;
    
    //aquí lee la fecha de cumpleaños de una persona
    
    leer.open(fileName.c_str(), ios::in);
    
    leer.getline(temp, 70);
    leer.getline(temp, 80);
    leer.getline(temp, 10);
    leer>>dia;
    leer>>mes;
    
    leer.close();
    
    //aquí leo el archivo al azar con los distintos horóscopos
    int signo = asignarSigno(dia, mes);
    if (signo == 0){
        alertar("Lectura de horóscopo erronea!\nIngresar Manualmente!");
        signo = asignarSignoBackUp();
    }
    system("cls");
    //cout<<signo<<endl;
    fileName = "horoscopo/horoscopo";
    int numeroSeleccion = GetTickCount()%2;//por ahora solo hay dos archivos, ntonces de esa cantidad que hay
    //la aleatoreidad de la selección se da por el momento en el que se llame a la aplicación
    //ya que esta toma el tiempo de la máquina desde que fué iniciada y opera con respecto a este
    char letraSelecion = '0' + ((char)numeroSeleccion);
    fileName += letraSelecion;
    fileName = nombrarArchivo(fileName);
    //cout<<fileName<<endl;
    leer.open(fileName.c_str(), ios::in);
    cout<<endl<<endl<<endl<<"\t\t\tHOROSCOPO "<<usuario<<' '<<signos[signo-1]<<endl;
    for(int i=0;i<signo;i++){
        leer.getline(temp, 512);
    }
    cout<<endl<<temp<<endl;
    leer.close();
    getch();
}

/*
La función asignarSigno se encarga de recibir el día y mes de nacimiento para
retornar un número entre 1 y 12 que identifica el signo de una persona. retorna
0 si los números están errados.
*/
int asignarSigno(int dia, int mes){
    if((mes==3 && dia>=21 && dia <=31) || (mes==4 && dia>=1 && dia<=20))//Aries
        return 1;
    else if((mes==4 && dia>=21 && dia <=31) || (mes==5 && dia>=1 && dia<=20))//Tauro
        return 2;
    else if((mes==5 && dia>=21 && dia <=31) || (mes==6 && dia>=1 && dia<=20))//Géminis
        return 3;
    else if((mes==6 && dia>=21 && dia <=31) || (mes==7 && dia>=1 && dia<=22))//Cáncer
        return 4;
    else if((mes==7 && dia>=23 && dia <=31) || (mes==8 && dia>=1 && dia<=22))//Leo
        return 5;
    else if((mes==8 && dia>=23 && dia <=31) || (mes==9 && dia>=1 && dia<=22))//Virgo
        return 6;
    else if((mes==9 && dia>=23 && dia <=31) || (mes==10 && dia>=1 && dia<=22))//Libra
        return 7;
    else if((mes==10 && dia>=23 && dia <=31) || (mes==11 && dia>=1 && dia<=21))//Escorpión
        return 8;
    else if((mes==11 && dia>=22 && dia <=31) || (mes==12 && dia>=1 && dia<=21))//Sagitario
        return 9;
    else if((mes==12 && dia>=22 && dia <=31) || (mes==1 && dia>=1 && dia<=19))//Capricornio
        return 10;
    else if((mes==1 && dia>=20 && dia <=31) || (mes==2 && dia>=1 && dia<=17))//Acuario
        return 11;
    else if((mes==2 && dia>=18 && dia <=31) || (mes==3 && dia>=1 && dia<=20))//Piscis
        return 12;
    else//Inválida
        return 0;
}
/*
La Función adignarSignoBackUp se encarga de pedir manualmente la escogencia del signo
de una persona a través de las teclase de dirección en caso de que la lectura de los datos del usuario
no haya sido satisfactoria.
*/
int asignarSignoBackUp(){
    int i=0, seleccion=0;
    string signos[12] = {"Aries", "Tauro", "Geminis", "Cancer", "Leo", "Virgo", "Libra", "Escorpion", "Sagitario", "Capricornio", "Acuario", "Piscis"};
    system("cls");
    seleccion = getch();
    while(seleccion!=13){
        system("cls");
        cout<<endl<<endl;
        if(seleccion == 0xe0){
            seleccion = getch();    
            if(seleccion == 72 && i > 0)
                i--;
            if(seleccion == 80 && i < 11)
                i++;
        }
        
        for(int j=0;j<12;j++)
            if(i==j)
                cout<<"\t\t->\t"<<signos[j]<<endl;
            else
                cout<<"\t\t\t"<<signos[j]<<endl;
        seleccion = getch();
    }
    return i+1;
}

/*
La función laberinto toma un laberinto al azar de los archivos dispoibles para
crear un juego donde el usuario debe pasar de un lado de la pantalla al otro
midiendo tiempo. El usuario puede salir en cualquier momento presinando la tecla
enter. El laberinto se ve guardado en 
*/
void laberinto(const string usuario){
    system("cls");
    alertar("Bienvenid@ al laberinto!\nUsa las flechas para moverte.\nPresiona la tecla Enter en el momento que quieras salir.");
    int i, j, moveTo;
    long time;
    struct Laberinto laberinto;
    fstream leer;
    string fileName = "laberintos/laberinto";//para inicializar los laberintos (nombre de los archivos)
    int seleccionLaberinto = GetTickCount()%5;//selecciona un laberinto al azar de los archivos disponibles
    fileName+= ((char)(seleccionLaberinto+'0'));
    fileName = nombrarArchivo(fileName);//nombra al archivo a usar
    leer.open(fileName.c_str(), ios::in);
    char temp[85];
    laberinto.cursor = usuario[0];//inicializa los datos del laberinto como el cursor, las paredes, el suelo, posiciones iniciales y finales
    laberinto.wall = 219;
    laberinto.floor = ' ';
    laberinto.y = 0;
    laberinto.x = 1;
    laberinto.xOut = 13;
    laberinto.yOut = 14;
    laberinto.pasos = 0;
    for(i=0;i<15;i++){//este conjunto de ciclos lee del archivo seleccionado y guarda los datos del laberinto de pared y suelo en la matriz lab
        leer>>temp;
        for(j=0;j<15;j++){
            if(temp[j]=='0')
                laberinto.lab[i][j]=false;
            else
                laberinto.lab[i][j]=true;
        }
    }
    laberinto.timeIni = GetTickCount();//toma el tiempo de inicio
    system("cls");
    cout<<"Laberinto: "<<usuario<<endl;
    
    while(moveTo != 13 && !(laberinto.x==laberinto.xOut && laberinto.y==laberinto.yOut)){//ciclo while que se rompe al presionar la tecla enter o al llegar a la posición final
        system("cls");
        time = GetTickCount()-laberinto.timeIni;//se calcula el tiempo de juego con respecto a la toma inicial y a la actual
        cout<<"\t\t\tTiempo\t\t"<<time/1000<<':'<<(time%1000)/10<<endl;
        cout<<"\t\t\tPasos\t\t"<<laberinto.pasos<<endl;
        cout<<"\t\t\tPuntaje\t\t"<<((1000000-(laberinto.pasos*time)>=0)?(1000000-(laberinto.pasos*time)):0)<<endl<<endl;
        for(i=0;i<15;i++){//con estos ciclos se imprimen las paredes, el cursor y el suelo del laberinto
            cout<<"\t\t\t";
            for(j=0;j<15;j++){
                if(i==laberinto.x && j==laberinto.y)
                    cout<<laberinto.cursor;
                else if(laberinto.lab[i][j])
                    cout<<laberinto.wall;
                else
                    cout<<laberinto.floor;
            }
            cout<<endl;
        }
        moveTo = getch();
        if(moveTo == 224)//En este bloque de if se analiza la tecla entrada para realizar el moviemiento del cursor
        {
            moveTo = getch();    
            if(moveTo == 75 && laberinto.y > 0 && !(laberinto.lab[laberinto.x][laberinto.y-1])){//izquierda
                laberinto.y--;
                laberinto.pasos++;
            }
            if(moveTo == 77 && laberinto.y < 14 && !(laberinto.lab[laberinto.x][laberinto.y+1])){//derecha
                laberinto.y++;
                laberinto.pasos++;
            }
            if(moveTo == 72 && laberinto.x > 0 && !(laberinto.lab[laberinto.x-1][laberinto.y])){//arriba
                laberinto.x--;
                laberinto.pasos++;
            }
            if(moveTo == 80 && laberinto.x < 14 && !(laberinto.lab[laberinto.x+1][laberinto.y])){//abajo
                laberinto.x++;
                laberinto.pasos++;
            }
        }
    }
    system("cls");
    if(laberinto.x==laberinto.xOut && laberinto.y==laberinto.yOut)//en caso de que haya llegado a la posición final felicita al jugador
        alertar("Felicitaciones!\nHas terminado el laberinto!");
    else//en caso de que se haya salido presionando la tecla enter
        alertar("Puedes volver a empezar en cualquier momento\n::D");
}

/*
Esta función se encarga de "mostrar los números respectivos para una persona en
un día determinado" en realidad toma números al azar para mostrarlos de distintas
maneras, después de mostrarlos en pantalla, retorna.
*/
void numeros(const string usuario){
    system("cls");
    cout<<endl<<endl<<endl<<"\t\t\tNUMEROS "<<usuario<<endl<<endl;
    getch();
    cout<<"\t\tTu numero de la suerte de hoy es "<<(GetTickCount()%100)<<endl<<endl;
    getch();
    cout<<"\t\tTu numero maldito de hoy es "<<(GetTickCount()%100)<<endl<<endl;
    getch();
    cout<<"\t\tTu numero para con tu pareja de hoy es "<<(GetTickCount()%100)<<endl<<endl;
    getch();
}
