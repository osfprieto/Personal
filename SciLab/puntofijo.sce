function ans = f (x)
    ans = exp(-x)-x
endfunction

function ans = g(x) //Despejando x de f(x)
    ans = exp(-x)
endfunction

function ans=puntofijo(x0, funcion_principal, funcion_despejada, iteraciones_maximas, error_aceptado)
    iteraciones = 0
    x_previo = %inf
    x = x0
    while iteraciones<iteraciones_maximas & abs(x_previo-x)>error_aceptado
        x_previo = x
        x = funcion_despejada(x)
        printf("%f\t%f\n", x, funcion_principal(x))
        iteraciones = iteraciones + 1
    end
    ans = x
endfunction
