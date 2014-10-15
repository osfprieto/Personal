//ofprietoc@unal.edu.co
function probarAjusteDeCurvas()
    //Los datos tabulados
    x = [0.0000000000001 1 2 3 4 5]//El 0.0000001 es para aproximarlo a 0 y poder calcular la aproximación logarítmica
    y = [2.1 7.7 13.6 27.2 40.9 61.1]
    //x = [1 4 6 8 13 15 16 20]
    //y = [21.6 19.8 19.1 26.4 26.1 ]
    
    //Regresión lineal
    coef_lineal = ajuste_de_curvas(x, y, 1)
    y_lineal = evaluar_vect_pol(x, coef_lineal)
    sem_lineal = calcular_semejanza(y, y_lineal')
    
    //Regresión cuadrática
    coef_cuadratica = ajuste_de_curvas(x, y, 2)
    y_cuadratica = evaluar_vect_pol(x, coef_cuadratica)
    sem_cuadratica = calcular_semejanza(y, y_cuadratica')
    
    //Regresión exponencial
    //y = ar^x = ae^(bx) = a(e^b)^x
    //log(y) = log(ar^x) = log(a)+ xlog(r)
    //r = e^b
    //b = ln(r)
    log_y = log(y)
    coef_exp = ajuste_de_curvas(x, log_y, 1)
    y_exp = evaluar_vect_exp(x, coef_exp)
    sem_exp = calcular_semejanza(y, y_exp')
    
    //Regresión logarítmica
    //y = a + blog(x)
    log_x = log(x)
    coef_log = ajuste_de_curvas(log_x, y, 1)
    y_log = evaluar_vect_log(x, coef_log)
    sem_log = calcular_semejanza(y, y_log')
    
    //Plot
    xBuscar = 0.2:0.2:6
    yBuscar_lineal = evaluar_vect_pol(xBuscar, coef_lineal)
    yBuscar_cuadratica = evaluar_vect_pol(xBuscar, coef_cuadratica)
    yBuscar_exp = evaluar_vect_exp(xBuscar, coef_exp)
    yBuscar_log = evaluar_vect_log(xBuscar, coef_log)
    
    plot(x, y, '.', xBuscar, yBuscar_lineal', xBuscar, yBuscar_cuadratica', '-', xBuscar, yBuscar_exp', '-', xBuscar, yBuscar_log', '-')
    
    //Semejanzas
    disp("Semejanzas")
    disp("Lineal")
    disp(sem_lineal*100)
    disp("Cuadrática")
    disp(sem_cuadratica*100)
    disp("Exponencial")
    disp(sem_exp*100)
    disp("Logarítmica")
    disp(sem_log*100)
    
    //Coefientes
    disp("Coeficientes")
    disp("Lineal")
    disp(poly(coef_lineal, "x", "coef"))
    disp("Cuadrática")
    disp(poly(coef_cuadratica, "x", "coef"))
    disp("Exponencial")
    disp(exp(coef_exp))
    disp("Logarítmica")
    disp(coef_log)
    
endfunction

//Calcula la semejanza en función de los datos medidos y los datos aproximados
function semejanza=calcular_semejanza(y_medidos, y_aproximados)
    y_mean = mean(y_medidos)
    em = sum((y_medidos-y_mean).*(y_medidos-y_mean))
    ea = sum((y_medidos-y_aproximados).*(y_medidos-y_aproximados))
    sem = (em-ea)/em
    semejanza = sem
endfunction

//Evalúa un vector con los coeficientes para una ecuación logarítmica
function vector_evaluado=evaluar_vect_log(x, coeficientes)
    //y = a + blog(x)
    a = coeficientes(1)
    b = coeficientes(2)
    y = []
    for i=1:1:length(x)
        y(i) = a + b*log(x(i))
    end
    vector_evaluado = y
endfunction

//Evalúa un vector con los coeficientes para una ecuación exponencial
function vector_evaluado=evaluar_vect_exp(x, coeficientes)
    //y = ar^x
    b = coeficientes(1)
    m = coeficientes(2)
    a = exp(b)
    r = exp(m)
    y = []
    for i=1:1:length(x)
        y(i) = a*r^x(i)
    end
    vector_evaluado = y
endfunction

//Evalúa un vector dado con los coeficientes dados.
function vector_evaluado=evaluar_vect_pol(x, coeficientes)
    y = []
    for i=1:1:length(x)
        y(i) = evaluar_coef_pol(x(i), coeficientes)
    end
    vector_evaluado = y
endfunction

//Evalúa un valor dado con los coeficientes dados.
function evaluado=evaluar_coef_pol(x, coeficientes)
    grado_polinomio = length(coeficientes)-1
    suma = 0
    for i=0:1:grado_polinomio
        suma = suma + x^i*coeficientes(i+1)
    end
    evaluado = suma
endfunction

//Calcula los coeficientes de la regresión polinomial del grado que se
//solicite con respecto a los puntos ingresados.
function coeficientes = ajuste_de_curvas(x_conocidos, y_conocidos, grado_polinomio)
    matriz = generar_matriz(x_conocidos, y_conocidos, grado_polinomio+1)
    vector = generar_vector_solucion(x_conocidos, y_conocidos, grado_polinomio+1)
    coef = linsolve(matriz, -vector)
    coeficientes = coef
endfunction

//Genera el vector B usado para calcular los coeficientes de la regresión.
function vector=generar_vector_solucion(x_conocidos, y_conocidos, tamanio_vector)
    grado = tamanio_vector-1
    arreglo = 1:1:tamanio_vector
    vect = matrix(arreglo, tamanio_vector, 1)
    for i=1:1:tamanio_vector
        vector(i, 1) = sumatoria_x_y(x_conocidos, y_conocidos, grado)
        grado = grado-1
    end
endfunction

//Genera la matriz A usada para calcular los coeficientes de la regresión.
//Usa la sumatorias en x de distintos grados.
function mat=generar_matriz(x_conocidos, y_conocidos, tamanio_matriz)
    m = length(x_conocidos)
    matriz = []
    for i = 1:1:tamanio_matriz
        grado = tamanio_matriz-i
        for j = 1:1:tamanio_matriz
            matriz(i, j) = sumatoria_x(x_conocidos, grado)
            grado = grado+1
        end
    end
    
    mat = matriz
endfunction

//Calcula la sumatoria de i=1 hasta n de yi*xi^n
function sumatoria=sumatoria_x_y(x_conocidos, y_conocidos, grado)
    suma = 0
    m = length(x_conocidos)
    for j=1:1:m
        suma = suma + x_conocidos(j)^grado*y_conocidos(j)
    end
    sumatoria = suma
endfunction

//Calcula la sumatoria de i=1 hasta n de xi^n
function sumatoria=sumatoria_x(x_conocidos, grado)
    suma = 0
    m = length(x_conocidos)
    for j=1:1:m
        suma = suma + x_conocidos(j)^grado
    end
    sumatoria = suma
endfunction
