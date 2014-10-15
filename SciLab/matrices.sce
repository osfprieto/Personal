//El apóstrofe indica un arreglo en columna o simplemente usar el ; en cada fina de fila

F1 = [1 2 3]
F2 = [4 5 6]
F3 = [7 8 9]

MF = [F1; F2; F3]

C1 = [1 2 3]'
C2 = [4 5 6]'
C3 = [7 8 9]'

MC = [C1 C2 C3]

disp(MF)
disp(MC)

A = [9 8 7; 6 5 4; 3 2 1]

i = 2
j = 1

disp(A)

//Elemento i j
disp(A(i, j))

//Fila i
disp(A(i,:))

//Columna j
disp(A(:,j))

//Scilab tiene la función inv(A)
//Hacer código para sacar inversa de una matriz
//D:\Documentos\Workspaces\DevC++ workspace\Matrices\matrices.cpp:214
