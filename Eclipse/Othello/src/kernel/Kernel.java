package kernel;

import gui.GUI;
import java.io.*;
import javax.swing.*;

public class Kernel{
	public static final String path = "..\\";
	public static final String file = path+ "blankOver.png";
	//Intenta jugar en el cuadro de la coordenada x,y
	public static void play(int x, int y){
		Icon base = GUI.t, baser = GUI.tr, enemy = GUI.b, actual = GUI.button[x][y].getIcon();
		if(base.equals(GUI.b)){enemy = GUI.w;}
		int howMany = 0;
		boolean play = false, other = false;
		if(actual.equals(GUI.g)){
			//Chequeo vertical hacia arriba
			for(int i=x-1; i>=0; i--){
				Icon act = GUI.button[i][y].getIcon();
				if(act!=null && act.equals(base)){
					if(other){
						play = true;
						for(int k=i+1; k<=x; k++){
							GUI.button[k][y].setIcon(base);
							GUI.button[k][y].setRolloverIcon(baser);
							howMany++;
						}
						howMany--;
						break;
					}
					break;
				}else if(act.equals(enemy)){
					other = true;
				}else{
					break;
				}
			}
			
			//Chequeo vertical hacia abajo
			other = false;
			for(int i=x+1; i<GUI.SIZE; i++){
				Icon act = GUI.button[i][y].getIcon();
				if(act.equals(base)){
					if(other){
						play = true;
						for(int k=i-1; k>=x; k--){
							GUI.button[k][y].setIcon(base);
							GUI.button[k][y].setRolloverIcon(baser);
							howMany++;
						}
						howMany--;
						break;
					}
					break;
				}else if(act.equals(enemy)){
					other = true;
				}else{
					break;
				}
			}
			
			//Chequeo horizontal hacia la izquierda
			other = false;
			for(int i=y-1; i>=0; i--){
				Icon act = GUI.button[x][i].getIcon();
				if(act.equals(base)){
					if(other){
						play = true;
						for(int k=i+1; k<=y; k++){
							GUI.button[x][k].setIcon(base);
							GUI.button[x][k].setRolloverIcon(baser);
							howMany++;
						}
						howMany--;
						break;
					}
					break;
				}else if(act.equals(enemy)){
					other = true;
				}else{
					break;
				}
			}
			
			//Chequeo horizontal hacia la derecha
			other = false;
			for(int i=y+1; i<GUI.SIZE; i++){
				Icon act = GUI.button[x][i].getIcon();
				if(act.equals(base)){
					if(other){
						play = true;
						for(int k=i-1; k>=y; k--){
							GUI.button[x][k].setIcon(base);
							GUI.button[x][k].setRolloverIcon(baser);
							howMany++;
						}
						howMany--;
						break;
					}
					break;
				}else if(act.equals(enemy)){
					other = true;
				}else{
					break;
				}
			}
			
			//Chequeo diagonal: arriba izquierda
			other = false;
			for(int i=x-1, j=y-1; i>=0 && j>=0; i--, j--){
				Icon act = GUI.button[i][j].getIcon();
				if(act.equals(base)){
					if(other){
						play = true;
						for(int k=i+1, l=j+1; k<=x && l<=y; k++, l++){
							GUI.button[k][l].setIcon(base);
							GUI.button[k][l].setRolloverIcon(baser);
							howMany++;
						}
						howMany--;
						break;
					}
					break;
				}else if(act.equals(enemy)){
					other = true;
				}else{
					break;
				}
			}
			
			//Chequeo diagonal: arriba derecha
			other = false;
			for(int i=x-1, j=y+1; i>=0 && j<GUI.SIZE; i--, j++){
				Icon act = GUI.button[i][j].getIcon();
				if(act.equals(base)){
					if(other){
						play = true;
						for(int k=i+1, l=j-1; k<=x && l>=y; k++, l--){
							GUI.button[k][l].setIcon(base);
							GUI.button[k][l].setRolloverIcon(baser);
							howMany++;
						}
						howMany--;
						break;
					}
					break;
				}else if(act.equals(enemy)){
					other = true;
				}else{
					break;
				}
			}
			
			//Chequeo diagonal: abajo derecha
			other = false;
			for(int i=x+1, j=y+1; i<GUI.SIZE && j<GUI.SIZE; i++, j++){
				Icon act = GUI.button[i][j].getIcon();
				if(act.equals(base)){
					if(other){
						play = true;
						for(int k=i-1, l=j-1; k>=x && l>=y; k--, l--){
							GUI.button[k][l].setIcon(base);
							GUI.button[k][l].setRolloverIcon(baser);
							howMany++;
						}
						howMany--;
						break;
					}
					break;
				}else if(act.equals(enemy)){
					other = true;
				}else{
					break;
				}
			}
			
			//Chequeo diagonal: abajo izquierda
			other = false;
			for(int i=x+1, j=y-1; i<GUI.SIZE && j>=0; i++, j--){
				Icon act = GUI.button[i][j].getIcon();
				if(act.equals(base)){
					if(other){
						play = true;
						for(int k=i-1, l=j+1; k>=x && l<=y; k--, l++){
							GUI.button[k][l].setIcon(base);
							GUI.button[k][l].setRolloverIcon(baser);
							howMany++;
						}
						howMany--;
						break;
					}
					break;
				}else if(act.equals(enemy)){
					other = true;
				}else{
					break;
				}
			}
		}
		
		//Se pudo jugar
		if(play){
			GUI.cambiarTurno();
			GUI.cambiarPuntos(howMany, enemy);
			GUI.cambiarTiempo(base);
			if(GUI.canPlay){
				GUI.alertar("");
			}
		}else{
			GUI.alertar("Jugada no posible!");
		}
	}
	
	//Guardar el juego
	public static void saveGame(){
		try{
			BufferedWriter game = new BufferedWriter(new FileWriter(file));
			game.write(GUI.whtTm+ "\t" +GUI.blckTm+ "\t" +GUI.tmTPlWht+ "\t" +GUI.tmTPlBlck+ "\t");
			game.newLine();
			game.write(GUI.whtPnts+ "\t" +GUI.blckPnts+ "\t");
			if(GUI.t.equals(GUI.b)){
				game.write("1\t");
			}else{
				game.write("2\t");
			}
			game.newLine();
			for(int i=0; i<GUI.SIZE; i++){
				for(int j=0; j<GUI.SIZE; j++){
					Icon act = GUI.button[i][j].getIcon();
					if(act.equals(GUI.b)){//Negro
						game.write("1\t");
					}else if(act.equals(GUI.w)){//Blanco
						game.write("2\t");
					}else{//Verde
						game.write("0\t");
					}
				}
				game.newLine();
			}
			game.close();
			GUI.canPlay = false;
			GUI.alertarGrande("Juego guardado correctamente!");
		}catch(Exception e){
			String sge = "Error al guardar el juego!";
			sge += "\nPuede deberse a que:";
			sge += "\n- El archivo está en uso.";
			sge += "\n- Usted está jugando desde un navegador web.";
			GUI.alertarGrande(sge);
		}
	}
	
	//Cargar el juego
	public static void loadGame(){
		try{
			BufferedReader game = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(file))));
			String gameData = "";
			String gameLine = "";
			int lines = 0;
			int numbers = 0;
			int[] tms = new int[4], pnts = new int[2];
			int turn;
			Icon[][] bg = new Icon[GUI.SIZE][GUI.SIZE];
			while((gameLine = game.readLine()) != null){
				gameData += gameLine;
				lines++;
			}
			if(lines != GUI.SIZE + 2){//No se leyeron las n+2 líneas
				throw new Exception();
			}
			tms[0] = Integer.parseInt(gameData.substring(0, gameData.indexOf("\t")));
			gameData = gameData.substring(gameData.indexOf("\t") + 1);
			numbers++;
			tms[1] = Integer.parseInt(gameData.substring(0, gameData.indexOf("\t")));
			gameData = gameData.substring(gameData.indexOf("\t") + 1);
			numbers++;
			tms[2] = Integer.parseInt(gameData.substring(0, gameData.indexOf("\t")));
			gameData = gameData.substring(gameData.indexOf("\t") + 1);
			numbers++;
			tms[3] = Integer.parseInt(gameData.substring(0, gameData.indexOf("\t")));
			gameData = gameData.substring(gameData.indexOf("\t") + 1);
			numbers++;
			pnts[0] = Integer.parseInt(gameData.substring(0, gameData.indexOf("\t")));
			gameData = gameData.substring(gameData.indexOf("\t") + 1);
			numbers++;
			pnts[1] = Integer.parseInt(gameData.substring(0, gameData.indexOf("\t")));
			gameData = gameData.substring(gameData.indexOf("\t") + 1);
			numbers++;
			turn = Integer.parseInt(gameData.substring(0, gameData.indexOf("\t")));
			gameData = gameData.substring(gameData.indexOf("\t") + 1);
			numbers++;
			while(gameData.length() > 0){
				for(int i=0; i<GUI.SIZE; i++){
					for(int j=0; j<GUI.SIZE; j++){
						int id = Integer.parseInt(gameData.substring(0, gameData.indexOf("\t")));
						gameData = gameData.substring(gameData.indexOf("\t") + 1);
						numbers++;
						bg[i][j] = (id==2 ? GUI.w : (id==1 ? GUI.b : GUI.g));
					}
				}
			}
			if(numbers != GUI.SIZE*GUI.SIZE + 7){//No se leyeron los n*n+7 datos
				throw new Exception();
			}
			GUI.rgstrTm = System.currentTimeMillis();
			GUI.whtTm = tms[0];
			GUI.blckTm = tms[1];
			GUI.tmTPlWht = tms[2];
			GUI.tmTPlBlck = tms[3];
			GUI.noLmts[0] = tms[0]==-1 ? true : false;
			GUI.noLmts[1] = tms[1]==-1 ? true : false;
			GUI.cambiarTiempo(GUI.g);
			GUI.whtPnts = pnts[0];
			GUI.blckPnts = pnts[1];
			GUI.cambiarPuntos(0, GUI.g);
			GUI.t = turn==1 ? GUI.w : GUI.b;
			GUI.moves = 0;
			GUI.cambiarTurno();
			for(int i=0; i<GUI.SIZE; i++){
				for(int j=0; j<GUI.SIZE; j++){
					GUI.button[i][j].setIcon(bg[i][j]);
					if(bg[i][j].equals(GUI.b)){
						GUI.button[i][j].setRolloverIcon(GUI.br);
					}else if(bg[i][j].equals(GUI.w)){
						GUI.button[i][j].setRolloverIcon(GUI.wr);
					}
				}
			}
			game.close();
		}catch(Exception e){
			String lge = "Error al cargar el juego!";
			lge += "\nPuede deberse a que:";
			lge += "\n- El archivo no existe.";
			lge += "\n- El archivo está en uso.";
			lge += "\n- El archivo fue modificado fuera del juego.";
			lge += "\n- Usted está jugando desde un navegador web.";
			GUI.alertarGrande(lge);
			GUI.newGame.doClick();
		}
	}
}
