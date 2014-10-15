function ans=gauss_seidel(A, b, x0, iteraciones_maximas, error_aceptado, display_process)
    iteraciones = 0
    n = length(x0)
    xk_prev = ones(n, 1)
    xk_prev = xk_prev * %inf
    xk = x0
    errores = ones(n, 1)
    errores = errores * %inf
    if display_process then
        for i=1:n
            printf ("x%d\t\t", i)
        end
        printf("Error sistema\n")
    end
    while iteraciones < iteraciones_maximas & max(errores)>error_aceptado
        x_prev = xk
        for i = 1:n
            o = 0
            for j = 1:n
                if i<j | i>j then
                    o = o + A(i, j)*x_prev(j, 1)
                end
            end
            xk(i, 1) = (b(i, 1)-o)/A(i,i)
        end
        errores = abs(xk-x_prev)
        iteraciones = iteraciones + 1
        if display_process then
            for i=1:n
                printf ("%f\t", xk(i, 1))
            end
            printf("%f\n", max(errores))
        end
    end
    ans = xk
endfunction

//0 si es dominante
//i perteneciente a 1:n si no es dominante diagonalmente, i indica dónde hubo problema.
function ans=es_dominante(A)
    ans = -1
    n = sqrt(length(A))
    for i = 1:n
        term = A(i, i)
        if sum(A(i,1:n)) - term > term | sum(A(1:n,i)) - term > term then
            ans = i
        end
    end
    if ans==-1 then
        ans=0
    end
endfunction
