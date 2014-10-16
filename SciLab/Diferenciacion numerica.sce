//ofprietoc@unal.edu.co
//angarciariv@unal.edu.co

//Desarrollo profesor
function demoSegundaDer()
    vv = -sin(0.8)
    disp("Cálculo de f''''(0.8)")
    disp(vv)
    disp(["H" "VA" "EV" "EA"])
    
    h1 = 0.1
    d1 = derivada2(f, 0.8, h1)
    ev1 = abs(vv-d1)
    disp([h1 d1 ev1])
    
    h01 = 0.01
    d01 = derivada2(f, 0.8, h01)
    ev01 = abs(vv-d01)
    disp([h01 d01 ev01 abs(ev01-ev1)])
    
    h001 = 0.001
    d001 = derivada2(f, 0.8, h001)
    ev001 = abs(vv-d001)
    disp([h001 d001 ev001 abs(ev001-ev01)])
    
endfunction

function [y]=f(x)
    y = cos(x)
endfunction

function [f2] = derivada2(f, xi, h)
    f2 = (feval(xi-h, f)-2*feval(xi, f)+feval(xi+h, f))/(h^2)
endfunction

//Desarrollo adicional.

//Realiza la prueba para un arreglo de valores que se generan con
//función seno y luego de eso revisa los errores.
function realizarPrueba(h, x_inicial, x_final)
    x = x_inicial:h:x_final
    y = cos(x')
    derivadaReal = -sin(x')
    derivadaAproximada = diferencia_n_vector(y, h, 1)
    errorAproximacion = abs(derivadaReal-derivadaAproximada)
    disp("Valores de x usados")
    disp(x')
    disp("Valores calculados de la función cos(x)")
    disp(y)
    disp("Valores reales de la derivada d/dx(cos(x)) = -sin(x)")
    disp(derivadaReal)
    disp("Valores aproximados de la derivada d/dx(cos(x)) = -sin(x)")
    disp(derivadaAproximada)
    disp("Error de la aproximación")
    disp(errorAproximacion)
    disp("Promedio de los errores")
    disp(mean(errorAproximacion))
    
    plot(x, derivadaReal', '-', x, derivadaAproximada', '-', x, errorAproximacion', '-')
endfunction

//Retonar un vecotr con los valores aproximados de la derivada enésima
//de una función de valores dados con respecto al tamaño del salto.
//n: Grado de la derivada que se quiere obtener.
function vector_diferenciado=diferencia_n_vector(y_conocidos, h, n)
    vec = y_conocidos
    for i=1:n
        vec = diferenciar_vector(vec, h)
        disp("Derivada de orden")
        disp(i)
        disp(vec)
    end
    vector_diferenciado = vec
endfunction

//Retorna un vector con los valores aproximados de la derivada de
//una función de valores dados con respecto al tamaño del salto.
//y_conocidos: Datos medidos para cada punto xi igualmente separado
//otros
//h: Espacio entre cada uno de los puntos xi
function vector_diferenciado=diferenciar_vector(y_conocidos, h)
    //Tamaño mínimo del vector: 3
    //Derivadas centradas en el centro del vector
    //Derivadas progresivas en el inicio
    //Derivadas regresivas en el final
    //yi = f(xi)
    n = length(y_conocidos)
    if n>=3 then
        dif = []
        
        //Diferencia progresiva al inicio del vector
        dif(1) = (-3*y_conocidos(1)+4*y_conocidos(2)-y_conocidos(3))/(2*h)
        
        //Diferencias centradas
        for i=2:(n-1)
            dif(i) = (y_conocidos(i+1) - y_conocidos(i-1))/(2*h)
        end
        
        //Diferencia regresiva al final de vector
        //dfx(j,5)=(fx(j,3)-2*fx(j,2)+fx(j,1))/(h(1,j)^2);
        dif(n) = (3*y_conocidos(n)-4*y_conocidos(n-1)+y_conocidos(n-2))/(2*h)

        vector_diferenciado = dif
    else
        disp("Datos erróneos.")
        vector_diferenciado = []
    end
endfunction


//Ejercicio derivadas direccionales.

//Función multivariable
function z=fmulti(x, y)
    z = x*x+y*y
endfunction

//Recibe dos vectores de valores
//X contiene los valores x a usar para calcular los valores z
//Y contiene los valores y a usar para calcular los valores z
//dibujar tiene la instrucción de dibujar los valores en pantalla o no.
//Devuelve una matriz con los valores evaluados después de 
function z=evalFMulti(valores_x, valores_y, dibujar)
    z_mat = []
    m = length(valores_x)
    n = length(valores_y)
    for i = 1:m
        for j = 1:n
            z_mat(i,j) = fmulti(valores_x(i), valores_y(j))
        end
    end
    if dibujar then
        plot3d(valores_x, valores_y, z_mat)
    end
    z = z_mat
endfunction

//Demostración de la derivada direccional
//origen: vector desde dónde se quiere empezar a caminar
//pasos: Cantidad de pasos que queremos dar
//dv: Vector direccional
//h: Distancia del paso que queremos dar
//n: Orden de la derivada a calcular
function demoDerivadaDireccional(origen, pasos, dv, h, n)
    //Sólo dibujamos
    evalFMulti(-5:h:5, -5:h:5, %t)
    
    //Convertir el vector dirección en vector unitario
    dvu = (1.0/norm(dv))*dv
    
    //Nuestro vector de movimiento (h en versión de vectores)
    hv = h*dvu

    vectorDiferenciar = []
    puntosEvaluados = []
    
    actual = origen
    
    //Calculamos los valores de la función sobre la línea del vector dirección.
    for i=1:pasos
        vectorDiferenciar(i) = fmulti(actual(1), actual(2))
        puntosEvaluados(i, 1) = actual(1)
        puntosEvaluados(i, 2) = actual(2)
        actual = actual + hv
    end
    
    disp("Puntos en donde se evaluó fmult")
    disp(puntosEvaluados)
    disp("Valores evaluados sobre el vector de dirección")
    disp(vectorDiferenciar)
    
    vector_diferenciado = diferencia_n_vector(vectorDiferenciar, h, n)
endfunction
