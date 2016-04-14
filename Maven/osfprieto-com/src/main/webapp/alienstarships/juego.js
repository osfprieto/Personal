//Script para manejar el juego

var METLIMINICIAL=10;//límite inicial de meteoritos
var METLIM;//límete actual de meteoritos
var SALTO=20
var FRAMERATE=150;//cantidad de milisegundos de actualización de los movimientos y del juego
var CONTEOPARASALTO;
var LIMITEPARASALTO=1;//cantidad de veces que se actualiza el cuadro para que los meteoritos avancen un salto
var METEOROS=0;
var TIEMPOAGREGARMETEORO=5;//dado en segundos
var TIEMPOMETEOROAGREGADO;//Date de la última vez que se agregó un meteorito más al juego
var STATE=0;//Estado del juego: 0 para el menú, 1 para en juego, 2 para cerrar intervalos, 3 para salir al menú de inicio, 4 para ver las instrucciones
var IMAGEN=0;//número de la imagen que se va a usar
var SHIPY=540;
var SHIPX=0;
var METEY=[];
var METEX=[];
var TIME_INICIO;
var TIME_FINAL;
var DURACION;
var INTERVALO;
var HANDLING;

//manejo de información de las imágenes
var back=[];
var menu=[];
var ship=[];
var met=[];
var crash=[];
var help=[];
var buffered = false;

function iniciar(){
	STATE=0;
	IMAGEN=0;
	SHIPX=487;
	METEOROS=0;
	METEX=[];
	METEY=[];
	CONTEOPARASALTO=0;
	METLIM = METLIMINICIAL;
	if(!buffered){
		buffered=true;
		bufferImages();
	}
	var canvas=document.getElementById('canvas');
	var ctx=canvas.getContext('2d');
	var back = menu[0];
	ctx.drawImage(back, 0, 0, 1024, 600);
	document.body.addEventListener('touchmove', function(event) {
		event.preventDefault();
	}, false);
	INTERVALO = setInterval(function(){
			actualizar();
	},FRAMERATE);
}

function manejarEvento(event, tipo){
	
	if(HANDLING)
		return;
	HANDLING=true;
	var x;
	var y;
	if(tipo==0){//click
		x = event.clientX;
		y = event.clientY;
	}
	else if(tipo==1){//touch
		x = event.touches[0].clientX;
		y = event.touches[0].clientY;
	}
	
	if(STATE==0){//menú de inicio
		if(x>=372 && x<=652 && y>=384 && y<=467){//quiere jugar
			STATE=1;
			audio(1); // Poner la música del juego.
			TIME_INICIO = new Date();
			TIEMPOMETEOROAGREGADO = TIME_INICIO;
		}
		if(x>=410 && x<=610 && y>=490 && y<=540){//quiere ver las instrucciones
			STATE=4;
		}
	}
	else if(STATE==1){//juego
		if(x<=SHIPX && SHIPX>=25){
			SHIPX-=SALTO;
		}
		else if(x>SHIPX && SHIPX<=950){
			SHIPX+=SALTO;
		}
	}
	else if(STATE==4){//Volver de las instrucciones
		if(x>=60 && x<=260 && y>=40 && y<=90){
			STATE=1;
			audio(1); // Poner la música del juego.
			TIME_INICIO = new Date();
			TIEMPOMETEOROAGREGADO = TIME_INICIO;
		}
	}
	HANDLING=false;
}

function audio(seleccion){
	var audio=document.getElementById('audio');
	if(seleccion==0){
		audio.src="audio/intro.mp3";
	}
	else if(seleccion==1){
		audio.src="audio/gameplay.mp3";
	}
}

function actualizar(){
	CONTEOPARASALTO++;
	CONTEOPARASALTO%=LIMITEPARASALTO;
	IMAGEN++;
	IMAGEN%=2;
	var canvas=document.getElementById('canvas');
	var ctx=canvas.getContext('2d');
	var img;
	if(STATE==0){ // Menú
		img = menu[IMAGEN];
	}
	if(STATE==1){
		img = back[IMAGEN];
	}
	if(STATE==4){
		img = help[IMAGEN];
	}
	ctx.drawImage(img, 0, 0);
	if(STATE==1){//Dibujar nave y meteoros usando variables globales
		img = ship[IMAGEN];
		ctx.drawImage(img, SHIPX, SHIPY);
		
		var actualDate = new Date();
		var acutalTime = actualDate.getTime();
		
		if((acutalTime-TIEMPOMETEOROAGREGADO.getTime())/1000>TIEMPOAGREGARMETEORO){
			TIEMPOMETEOROAGREGADO=actualDate;
			METLIM++;
		}
		
		if(METEOROS<METLIM){
			var temp = Math.floor(Math.random()*1000)%975+25;
			METEY[METEOROS]=25;
			METEX[METEOROS]=temp;
			METEOROS++;
		}
		
		var distancia=1000;
		var temp;
		
		for (var i in METEX){
			var rand = Math.floor(Math.random()*100)%4;
			img = met[rand];
			if(METEX[i]>=25 && METEX[i]<=1000 && METEY[i]>=25 && METEY[i]<=575){
				ctx.drawImage(img, METEX[i], METEY[i]);
			}
			if(CONTEOPARASALTO==0){
				METEY[i]+=SALTO;
			}
			if(METEY[i]>=575){
				var temp = Math.floor(Math.random()*1000)%975+25;
				METEY[i]=25;
				METEX[i]=temp;
			}
			temp = Math.sqrt((METEX[i]-SHIPX)*(METEX[i]-SHIPX)+(METEY[i]-SHIPY)*(METEY[i]-SHIPY));
			if (temp<distancia){
				distancia=temp;
			}
		}
		
		ctx.fillStyle="#FF0000";
		ctx.font="30px sans-serif";
		ctx.fillText("Asteroides: "+METLIM,10,40);
		
		if(distancia<40){//Se estrella
			TIME_FINAL = new Date();
			DURACION = TIME_FINAL.getTime()-TIME_INICIO.getTime();
			
			img = crash[IMAGEN];
			ctx.drawImage(img, SHIPX, SHIPY);
			
			STATE=2;
			IMAGEN=0;
			SHIPX=487;
			METEOROS=0;
		}
	}
	if(STATE==2){//Terminó el juego
		clearInterval(INTERVALO);
		var mins = Math.floor(DURACION/1000/60);
		var segs = Math.floor((DURACION-mins*1000*60)/1000);
		var duracionJuego="";
		
		if(mins<10){
			duracionJuego = "0"+mins+":";
		}
		else{
			duracionJuego = mins+":";
		}
		if(segs<10){
			duracionJuego += "0"+segs;
		}
		else{
			duracionJuego += ""+segs;
		}
		
		alert("Felicidades! Tu tiempo fue "+duracionJuego+" con "+METLIM+" asteroides!");
		STATE=3;
		audio(0); // Poner el audio introductorio.
	}
	if(STATE==3){
		iniciar();
	}
}

function bufferImages(){
	back = [];
	menu = [];
	ship = [];
	met = [];
	crash = [];
	help = [];
	
	back[0] = new Image();
	back[0].src = "img/back0.png";
	back[1] = new Image();
	back[1].src = "img/back1.png";
	
	menu[0] = new Image();
	menu[0].src = "img/menu0.png";
	menu[1] = new Image();
	menu[1].src = "img/menu1.png";
	
	ship[0] = new Image();
	ship[0].src = "img/ship0.png";
	ship[1] = new Image();
	ship[1].src = "img/ship1.png";
	
	met[0] = new Image();
	met[0].src = "img/met0.png";
	met[1] = new Image();
	met[1].src = "img/met1.png";
	met[2] = new Image();
	met[2].src = "img/met2.png";
	met[3] = new Image();
	met[3].src = "img/met3.png";
	
	crash[0] = new Image();
	crash[0].src = "img/crash0.png";
	crash[1] = new Image();
	crash[1].src = "img/crash1.png";
	
	help[0] = new Image();
	help[0].src = "img/help0.png";
	help[1] = new Image();
	help[1].src = "img/help1.png";
}