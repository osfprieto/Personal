//ofprietoc@unal.edu.co

//Desarrollo profesor
function [y]=f(x)
    y = cos(x)
endfunction

function [f2] = derivada2(f, xi, h)
    f2 = (feval(xi-h, f)-2*feval(xi, f)+feval(xi+h, f))/(h^2)
endfunction

//Desarrollo adicional.

//Realiza la prueba para 
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
    disp("Error de la aprximación")
    disp(errorAproximacion)
    disp("Promedio de los errores")
    disp(mean(errorAproximacion))
    
    plot(x, derivadaReal', '-', x, derivadaAproximada', '-', x, errorAproximacion', '-')
endfunction

//Retonar un vecotr con los valores aproximados de la derivada enésima
//de una función de valores dados con respecto al tamaño del salto.
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
