#include<allegro.h>
#include<stdio.h>

int moverTexto();

int drawingPrimitives01();

int bounceBall();
void moveBall(int n);
void changeColor();

int pixel();

int doubleBuffering();

int negate();

int image();

int unkownSize();

int mouse();
bool mouseInfo();
void draw();

int bounceImg();

int dir, x, y, width = 320, height = 480, radius = 3, tempX, tempY, red=255, green=0, blue=0, cnt=0, maxIterations=50, pix=0, f, c, color, color1=0xff0000, color2=0xff, colorBack=0;
BITMAP *screenBuf=NULL, *buffy=NULL;

int main(){
    
    srand(time(NULL));
    //bounceBall();
    //drawingPrimitives01();
    //moverTexto();
    //pixel();
    //doubleBuffering();
    //image();
    //negate();
    //unkownSize();
    //mouse();
    //bounceImg();
    
    return 0;   
}
END_OF_MAIN();

int moverTexto(){
    int x=20, y=30;
    allegro_init();
    install_keyboard();
    set_gfx_mode(GFX_AUTODETECT_WINDOWED, 320,240,0,0);
    while(!key[KEY_ESC]){
        textout_ex(screen, font, "kmas!", x, y, makecol(0, 0, 0), -1);
        if (key[KEY_UP]) --y;
        else if (key[KEY_DOWN]) ++y;
        else if (key[KEY_RIGHT]) ++x;
        else if (key[KEY_LEFT]) --x;
        textout_ex(screen, font, "kmas!", x, y, makecol(255, 0, 0), -1);
        rest(10);
        clear_keybuf();
    }
    return 0;
}

int drawingPrimitives01(){
    
    allegro_init();
    install_keyboard();
    set_gfx_mode(GFX_AUTODETECT_WINDOWED,400,400,0,0);
    clear_to_color(screen, colorBack);
    int cnt;
    for(cnt=0; cnt<400; cnt+=3){
        putpixel(screen,cnt,400-cnt,makecol(255,0,0));;
        putpixel(screen,cnt-1,400-cnt-1,makecol(0,255,0));
        putpixel(screen,cnt+1,400-cnt+1,makecol(0,0,255));
    }
    
    circle(screen, 200, 200, 200, makecol(255, 0, 0));
    circle(screen, 200, 200, 190, makecol(255, 0, 0));
    
    floodfill(screen, 5, 200, makecol(0, 0, 255));
    
    circlefill(screen,100,200,50,makecol(0,255,0));
    rect(screen,150,75,250,125,makecol(0,0,255));
    rectfill(screen,175,250,225,350,makecol(0,255,255));
    
    line(screen,0,200,400,200,makecol(0,0,0));
    line(screen,200,0,200,400,makecol(0,0,0));
    
    floodfill(screen,100,100,makecol(255,255,0));
    floodfill(screen,300,300,makecol(255,255,0));
    floodfill(screen,90,300,makecol(255,0,255));
    floodfill(screen,300,90,makecol(255,0,255));
    
    triangle(screen,280,180, 320,180, 300,220,makecol(0,0,255));
    
    readkey();
    return 0;
}

int bounceBall(){
    clear_keybuf();
    allegro_init();
    install_keyboard();
    set_color_depth(32);
    set_gfx_mode(GFX_AUTODETECT_WINDOWED,width,height,0,0);
    clear_to_color(screen, colorBack);
    dir = rand() % 4;
    x = rand()% width+radius;
    y = rand()% height+radius;
    while(!key[KEY_ESC]){
        moveBall(1);
    }
    
    return 0;
}
void moveBall(int n){
    tempX=x;
    tempY=y;
    switch(dir){
        case 0:
            if((x <= radius) || (y <= radius)){
                dir = rand() % 4;
            }
            else{
                --x;
                --y;
            }
        break;
        case 1:
            if(((x <= radius) || (y >= (height - radius)))){
                dir = rand() % 4;
            }
            else{
                --x;
                ++y;
        }
        break;
        case 2:
            if(((x >= (width - radius)) || (y <= radius))){
                dir = rand() % 4;
            }
            else{
                ++x;
                --y;
            }
        break;
        case 3:
            if((((x >= (width - radius)) || (y >= (height - radius))))){
                dir = rand() % 4;
            }
            else{
                ++x;
                ++y;
            }
        break;
    }
    if(n==1){
        acquire_screen();
        circlefill (screen,tempX,tempY,radius, colorBack);
        if(cnt>=maxIterations){
            cnt=0;
            changeColor();
        }
        else{
            cnt++;
        }
        circlefill (screen,x,y,radius,color);
        release_screen();
    }
    else if(n==2){
        blit(buffy,screenBuf,0,0,tempX-radius-1,tempY-radius-1,radius*2+2,radius*2+2);
        blit(screenBuf,buffy,x-radius-1,y-radius-1,0,0,radius*2+2,radius*2+2);
        if(cnt>=maxIterations){
            cnt=0;
            changeColor();
        }
        else{
            cnt++;
        }
        circlefill (screenBuf,x,y,radius,color);
        blit(screenBuf,screen,0,0,0,0,width,height);
    }

    rest(5);
}
void changeColor(){
    red=rand()%256;
    green=rand()%256;
    blue=rand()%256;
    color=makecol(red, green, blue);
    color1=makecol(green, blue, red);
    color2=makecol(blue, red, green);
}
int pixel(){
    allegro_init();
    install_keyboard();
    set_color_depth(32);
    set_gfx_mode(GFX_AUTODETECT_WINDOWED,256,256,0,0);
    
    for(f=0; f<255; f++){
        for(c = 0; c<255; c++){
            putpixel(screen,c,f,makecol(c, f,128));
        }
    }
    readkey();
    return 0;
}
int doubleBuffering(){
    
    allegro_init();
    install_keyboard();
    set_color_depth(32);
    set_gfx_mode(GFX_AUTODETECT_WINDOWED,256,256,0,0);
    
    screenBuf=NULL;
    screenBuf=create_bitmap(256, 256);
    
    for(f=0;f<256;f++){
        for(c=0;c<256;c++){
            putpixel(screenBuf, c, f, makecol(c, f, 128));
        }
    }
    
    blit(screenBuf,screen,0,0,0,0,256,256);
    
    readkey();
    
    destroy_bitmap(screenBuf);
    
    return 0;
}
int image(){
    allegro_init();
    install_keyboard();
    set_color_depth(32);
    set_gfx_mode(GFX_AUTODETECT_WINDOWED,640,480,0,0);
    
    //pics 320x480
    
    screenBuf=NULL;
    screenBuf=load_bitmap("picA.bmp", NULL);
    blit(screenBuf, screen, 0, 0, 0, 0, 320, 480);
    
    destroy_bitmap(screenBuf);
    
    screenBuf=load_bitmap("picB.bmp", NULL);;
    blit(screenBuf, screen, 0, 0, 321, 0, 320, 480);
    
  
    /*for(f=0;f<220;f+=5){
        circle(screen, f, 2*f, 20, makecol(red, green, blue));
        changeColor();
    }*/
    
    readkey();
    
    destroy_bitmap(screenBuf);
    
    return 0;
}
int negate(){
    
    allegro_init();
    install_keyboard();
    set_color_depth(32);
    set_gfx_mode(GFX_AUTODETECT_WINDOWED,640,480,0,0);
    
    screenBuf=NULL;
    screenBuf=load_bitmap("picA.bmp", NULL);
    blit(screenBuf, screen, 0, 0, 0, 0, 320, 480);
    
    for(f=0;f<480;f++){
        for(c=0;c<320;c++){
            pix=getpixel(screenBuf, c, f);
            red=getr(pix);
            green=getg(pix);
            blue=getb(pix);
            putpixel(screenBuf, c, f, makecol(255-red, 255-green, 255-blue));
        }
    }
    
    blit(screenBuf, screen, 0, 0, 321, 0, 320, 480);
    
    readkey();
    
    destroy_bitmap(screenBuf);
}
int unkownSize(){
    
    allegro_init();
    install_keyboard();
    set_color_depth(32);
    
    int windowWidth=0, windowHeight=0, upLX=0, upLY=0, dwRX=0, dwRY=0;
    
    screenBuf=NULL;
    screenBuf = load_bitmap("picA.bmp", NULL);
    
    get_clip_rect(screenBuf, &upLX, &upLY, &dwRX, &dwRY);
    width=dwRX+1;
    height=dwRX+1;
    
    if(width%4==0){
        windowWidth=width;
    }
    else{
        windowWidth=width/4*4+4;
    }
    
    if(height<108){
        windowHeight = 108;
    }
    else if(height%108==0){
        windowHeight = height;
    }
    else{
        windowHeight = height/108*108+108;
    }
    windowHeight*=2;
    windowHeight++;
    
    destroy_bitmap(screenBuf);
    
    set_gfx_mode(GFX_AUTODETECT_WINDOWED, windowWidth, windowHeight, 0, 0);
    screenBuf=load_bitmap("picA.bmp", NULL);
    
    blit(screenBuf, screen, 0, 0, 0, 0, width, height);
    
    char charArray[20];
    sprintf(charArray, "%d", width);
    textout_ex(screen, font, charArray, 10, 10, 0xff0000, -1);
    sprintf(charArray, "%d", height);
    textout_ex(screen, font, charArray, 10, 20, 0xff00, -1);
    sprintf(charArray, "%d", windowWidth);
    textout_ex(screen, font, charArray, 10, 30, 0xff, -1);
    sprintf(charArray, "%d", windowHeight);
    textout_ex(screen, font, charArray, 10, 40, 0xffffff, 0);
    
    for(f = 0;f < height;f++){
        for(c = 0;c < width;c++){
            pix = getpixel(screenBuf,c,f);
            red = getr(pix);
            green = getg(pix);
            blue = getb(pix);
            putpixel(screenBuf, c,f,makecol(255-red,255-green,255-blue));
        }
    }
    
    blit(screenBuf, screen, 0, 0, 0, height+1, width, height);
    readkey();
    destroy_bitmap(screenBuf);
    
    return 0;
}
int mouse(){
    
    allegro_init();
    install_keyboard();
    install_mouse();
    set_color_depth(32);
    set_gfx_mode(GFX_AUTODETECT_WINDOWED, width, height, 0, 0);
    clear_to_color(screen, colorBack);
    screenBuf=create_bitmap(width, height);
    
    show_mouse(screen);

    while(!key[KEY_ESC]){
        while(mouseInfo()){
            draw();
        }
        changeColor();
    }
    
    return 0;
}
bool mouseInfo(){
    
    x=mouse_x;
    y=mouse_y;
    
    if(mouse_b & 1){
        color = color1;
        return true;
    }
    else if(mouse_b & 2){
        color =color2;
        return true;
    }
    else{
        return false;
    }
    
}
void draw(){
    
    show_mouse(NULL);
    
    circlefill(screenBuf, x, y, radius, color);
    blit(screenBuf, screen, 0, 0, 0, 0, width, height);
    
    show_mouse(screen);
    
}
int bounceImg(){
    
    allegro_init();
    install_keyboard();
    set_color_depth(32);
    set_gfx_mode(GFX_AUTODETECT_WINDOWED, width, height, 0, 0);
    
    screenBuf=load_bitmap("picA.bmp", NULL);
    
    buffy = create_bitmap(radius*2 + 2,radius*2 + 2);
    
    blit(screenBuf,buffy,x-radius-1,y-radius-1,0,0,radius*2+2,radius*2+2);

    dir = rand() % 4;
    
    while(!key[KEY_ESC]){
        moveBall(2);
    }
    
    return 0;
}
