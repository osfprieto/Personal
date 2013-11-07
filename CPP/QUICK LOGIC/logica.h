struct Datos
{
                         char perfil[20];
                          int puntaje;
                          int robot;
                          int color; 
};
struct Juego
{
       char laberinto[20][80];
       struct Datos datos;     
};
int mover(struct Juego* juego);
void aletorio(struct Juego* juego);
//void sIn(int n);
//void sOut();

