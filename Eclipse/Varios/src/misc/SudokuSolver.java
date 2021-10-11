package misc;

import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class SudokuSolver {

	private static boolean mat[][][];
	private static int solve[][];
	private static int lastCount = 0;
	
	public static void main (String args[]){
		
		JFileChooser fc = new JFileChooser();
		int ret = fc.showDialog(null, "Abrir");
		if(ret==JFileChooser.APPROVE_OPTION){
			try {
				Scanner sc = new Scanner(fc.getSelectedFile());
				mat = new boolean[9][9][9];
				solve = new int[9][9];
				
				for(int i=0;i<9;i++){ // Read
					for(int j=0;j<9;j++){
						int num = sc.nextInt();
						solve[i][j] = num;
						if(num==0){ // Unkown number.
							for(int k=0; k<9; k++){
								mat[i][j][k] = true;
							}
						} else { // Given number.
							mat[i][j][num-1] = true; // true means that it's possible that that number is there.
						}
					}
				}
				
				while(!isSolved()){
					
					for(int i=0; i<9; i++){
						for(int j=0;j<9;j++){
							trySolving(i, j);
						}
					}
					
					int count = countMissing();
					if (count==lastCount){
						printSolution();
						printPossibleCount();
						System.err.println("We are not good enough yet.");
						System.exit(0);
					}
					lastCount = count;
					System.out.println(lastCount);
				}
				
				printPossibleCount();
				printSolution();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private static void printSolution(){
		System.out.println("Soultion");
		for(int i=0;i<9;i++){
			for(int j=0; j<9; j++){
				System.out.print(solve[i][j]+"\t");
				if((j+1)%3==0)
					System.out.print("\t");
			}
			System.out.println();
				if((i+1)%3==0)
					System.out.println();
		}
	}
	
	private static void printPossibleCount(){
		System.out.println("Possible count");
		for(int i=0;i<9;i++){
			for(int j=0; j<9; j++){
				int count=0;
				for(int k=0; k<9; k++){
					if(mat[i][j][k])
						count++;
				}
				System.out.print(count+"\t");
				if((j+1)%3==0)
					System.out.print("\t");
			}
			System.out.println();
				if((i+1)%3==0)
					System.out.println();
		}
	}
	
	private static void trySolving(int iIndex, int jIndex){
		//System.out.println("("+iIndex+", "+jIndex+")");
		//printSolution();
		//printPossibleCount();
		
		if(solve[iIndex][jIndex]==0){ // Find
			
			// Se bloquean posibilidades de una celda con cada n�mero de una celda relacionada que se ya est� en solve.
			// Try to solve the cell by checking which numbers we can't put there.
			// Horizontal
			for(int j=0; j<9; j++){
				if(j!=jIndex && solve[iIndex][j]>0){
					mat[iIndex][jIndex][solve[iIndex][j]-1] = false;
				}
			}
			
			// Vertical
			for(int i=0; i<9; i++){
				if(i!=iIndex && solve[i][jIndex]>0){
					mat[iIndex][jIndex][solve[i][jIndex]-1] = false;
				}
			}
			
			// Cuadros
			int iBase = iIndex/3;
			int jBase = jIndex/3;
			iBase *= 3;
			jBase *=3;
			for(int i=0; i<3; i++){
				for(int j=0; j<3; j++){
					if( (iBase+i!=iIndex || jBase+j!=jIndex) && solve[iBase+i][jBase+j]>0){
						mat[iIndex][jIndex][solve[iBase+i][jBase+j]-1] = false;
					}
				}
			}
			
			clean();
			
			
			// Miramos si una celda tiene una posibilidad de tener un n�mero y si ninguna otra celda relacionada puede
			// contener ese n�mero significa que esa celda contiene ese n�mero as� tenga m�s posibilidades.
			// Esta condici�n se tiene que cumplir s�lo en una de las direcciones: horizontal, vertical o en los cuadros.
			
			// Try to solve the cell checking which number we can put here but we can't put in any other related cell
			if(solve[iIndex][jIndex]==0){
			
				for(int k=0; k<9; k++){
					if(mat[iIndex][jIndex][k]){
						int countOtherCellsThatCanHaveK;
						
						// Horizontal
						countOtherCellsThatCanHaveK = 0;
						for(int j=0; j<9; j++){
							if(j!=jIndex && mat[iIndex][j][k]){
								countOtherCellsThatCanHaveK++;
							}
						}
						if(countOtherCellsThatCanHaveK==0){
							put(k+1, iIndex, jIndex);
						} else {
						
							// Vertical
							countOtherCellsThatCanHaveK = 0;
							for(int i=0; i<9; i++){
								if(i!=iIndex && mat[i][jIndex][k]){
									countOtherCellsThatCanHaveK++;
								}
							}
							if(countOtherCellsThatCanHaveK==0){
								put(k+1, iIndex, jIndex);
							} else {
							
								// Cuadros
								countOtherCellsThatCanHaveK = 0;
								for(int i=0; i<3; i++){
									for(int j=0; j<3; j++){
										if( (iBase+i!=iIndex || jBase+j!=jIndex) && mat[iBase+i][jBase+j][k]){
											countOtherCellsThatCanHaveK++;
										}
									}
								}
								if(countOtherCellsThatCanHaveK==0){
									put(k+1, iIndex, jIndex);
								}
							}
						}
					}
				}
				
				clean();
			}
			
			// Ejemplo: Un n�mero no se ha encontrado con exactitud en un cuadro pero las posibilidades
			// se�alan que el n�mero puede estar s�lo en una columna o fila, podemos asumir que el n�mero,
			// donde sea que est�, va a bloquear en la direcci�n de esa columna o fila, por lo que podemos
			// bloquear ese n�mero de las posibilidades de otras casillas en esa misma fila o columna.
			if(solve[iIndex][jIndex]==0){
				// Por cada n�mero, recorrer en direcciones horizontales y verticales
				for(int k=0; k<9; k++){
					if(mat[iIndex][jIndex][k]){
						boolean strictBlocking = false;
						
						// Horizontal
						for(int jBaseTemp = 0; jBaseTemp<9 && !strictBlocking; jBaseTemp+=3){
							if(jBaseTemp != jBase){
								boolean lineBlocking = false;
								
								// Alg�n otro en la l�nea tiene posibilidad de ese n�mero.
								for(int j=0; j<3; j++){
									lineBlocking = lineBlocking || mat[iIndex][jBaseTemp+j][k];
								}
								
								if(lineBlocking){ // Verificar que ese que tiene posibilidad en la l�nea no tenga posibilidad fuera de la l�nea
									boolean outOfLinePossibilities = false;
									for(int i=0; i<3; i++){
										if(iBase+i!=iIndex){
											for(int j=0; j<3;j++){
												outOfLinePossibilities = outOfLinePossibilities || mat[iBase+i][jBaseTemp+j][k];
											}
										}
									}
									strictBlocking = !outOfLinePossibilities;
								}
							}
						}
						
						// Vertical
						for(int iBaseTemp = 0; iBaseTemp<9 && !strictBlocking; iBaseTemp+=3){
							if(iBaseTemp != iBase){
								boolean lineBlocking = false;
								
								// Alg�n otro en la l�nea tiene posibilidad de ese n�mero.
								for(int i=0; i<3; i++){
									lineBlocking = lineBlocking || mat[iBaseTemp+i][jIndex][k];
								}
								
								if(lineBlocking){ // Verificar que ese que tiene posibilidad en la l�nea no tenga posibilidad fuera de la l�nea
									boolean outOfLinePossibilities = false;
									for(int j=0; j<3; j++){
										if(jBase+j!=jIndex){
											for(int i=0; i<3;i++){
												outOfLinePossibilities = outOfLinePossibilities || mat[iBaseTemp+i][jBase+j][k];
											}
										}
									}
									strictBlocking = !outOfLinePossibilities;
								}
							}
							
						}
						
						
						
						if(strictBlocking){
							mat[iIndex][jIndex][k] = false;
						}
					}
				}
				
				clean();
			}
			
		} else { // Use the number in here to block other possibilities.
			
			// El n�mero ya fue encontrado, lo usamos para bloquear posibilidades en las casillas relacionadas.
			int k = solve[iIndex][jIndex]-1;
			// Horizontal
			for(int j=0; j<9; j++){
				if(j!=jIndex){
					mat[iIndex][j][k] = false;
				}
			}
			
			// Vertical
			for(int i=0; i<9; i++){
				if(i!=iIndex){
					mat[i][jIndex][k] = false;
				}
			}
			
			// Cuadros
			int iBase = iIndex/3;
			int jBase = jIndex/3;
			iBase *= 3;
			jBase *=3;
			for(int i=0; i<3; i++){
				for(int j=0; j<3; j++){
					if(iBase+i!=iIndex || jBase+j!=jIndex){
						mat[iBase+i][jBase+j][k] = false;
					}
				}
			}
			
			clean();
		}
	}
	
	private static void clean(){
		// Resumir: Recorrer el tablero y resolver para las casillas que tengan una sola posibilidad, quitando esta posibilidad de las relacionadas
		for(int i=0; i<9; i++){
			for(int j=0;j<9;j++){
				if(solve[i][j]==0){ // We check if there is just one possibility for this cell.
					int countPossibles = 0;
					int lastNumber = 0;
					for(int k=0; k<9; k++){
						if(mat[i][j][k]){
							countPossibles++;
							lastNumber = k+1;
						}
					}
					if(countPossibles==0){
						System.err.println("We blocked all posibilities: "+i+", "+j);
						printSolution();
						printPossibleCount();
						System.exit(0);
					} else if(countPossibles==1){
						put(lastNumber, i, j);
					}
				} else { // We sync mat according to solve.
					for(int k=0;k<9;k++)
						mat[i][j][k] = false;
					mat[i][j][solve[i][j]-1] = true;
				}
			}
		}
	}
	
	private static void put(int number, int iIndex, int jIndex){
		if(solve[iIndex][jIndex]==0){
			System.out.println("Found this ("+iIndex+", "+jIndex+") = "+number);
			solve[iIndex][jIndex] = number;
			//Take out all the other possibilities in the cell (clean).
			for(int k=0; k<9; k++)
				if(k!=number-1)
					mat[iIndex][jIndex][k] = false;
			// Take the number out of the possibilities of the related cells.
			// Horizontal
			for(int j=0; j<9; j++)
				if(j!=jIndex)
					mat[iIndex][j][number-1] = false;
			// Vertical
			for(int i=0; i<9; i++)
				if(i!=iIndex)
					mat[i][jIndex][number-1] = false;
			// Cuadros
			int iBase = iIndex/3;
			int jBase = jIndex/3;
			iBase *= 3;
			jBase *=3;
			for(int i=0; i<3; i++){
				for(int j=0; j<3; j++){
					if(iBase+i!=iIndex || jBase+j!=jIndex){
						mat[iBase+i][jBase+j][number-1] = false;
					}
				}
			}
		}
	}
	
	private static boolean isSolved(){
		return countMissing()==0;
	}
	
	private static int countMissing(){
		int count = 0;
		for(int i=0;i<9;i++){
			for(int j=0; j<9; j++){
				if(solve[i][j]==0){
					count++;
				}
			}
		}
		return count;
	}
	
}
