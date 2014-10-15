//osfprieto@gmail.com
//Ejericio usado en la exposición
function probarAjusteDeCurvas()
    x = [0 1 2 3 4 5]
    y = [2.1 7.7 13.6 27.2 40.9 61.1]
    coef_lineal = ajuste_de_curvas(x, y, 1)
    sem_lineal = calcular_semejanza(y, eval_vec_coef(x, coef_lineal)')
    coef_cuadratico = ajuste_de_curvas(x, y, 2)
    sem_cuadratico = calcular_semejanza(y, eval_vec_coef(x, coef_cuadratico)')
    
    poly_lineal = poly(coef_lineal, "x", "coef")
    poly_cuadratico = poly(coef_cuadratico, "x", "coef")
    
    disp("Coeficientes lineales:")
    disp(poly_lineal)
    disp("Semejanza lineal")
    disp(sem_lineal)
    
    disp("Coeficientes cuadrático:")
    disp(poly_cuadratico)
    disp("Semejanza cuadrático:")
    disp(sem_cuadratico)
    
    xBuscar = 0:0.2:6
    yBuscar_lineal = eval_vec_coef(xBuscar, coef_lineal)
    yBuscar_cuadratico = eval_vec_coef(xBuscar, coef_cuadratico)
    plot(x, y, 'o', xBuscar, yBuscar_lineal', '-', xBuscar, yBuscar_cuadratico', '-')
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
