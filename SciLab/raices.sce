//osfprieto@gmail.com

//p = poly ([376 242.2 -34.8 9.9], "t", "coeff")
//p = poly ([376 242.2 -34.8 9.9], "t", "c")
//p = poly ([376 242.2 -34.8 9.9], "t", "roots")
//p = poly ([376 242.2 -34.8 9.9], "t", "r")
//help poly
//roots(p)

function ans = f(a)
    ans = 9.9*a^3 -34.8*a^2 +242.2*a + 376 - 1000
endfunction

function ans = p(y)
    ans = 29.7*y^2 -69.6*y + 242
endfunction

function [ans, iteraciones] = biseccion(iteraciones_maximas, error_aceptado, a, b, funcion_usar)
    c = (a+b)/2
    i = 0
    while(abs(funcion_usar(c))>error_aceptado & i<iteraciones_maximas)
      if(funcion_usar(a)<0 & funcion_usar(c)<0) then
          a = c
      elseif(funcion_usar(a)>0 & funcion_usar(c)>0)
          a = c
      else
          b = c
      end
      i = i+1
      c = (a+b)/2
   end
   ans = c
   iteraciones = i
endfunction

function [ans, iteraciones] = posicion_falsa(iteraciones_maximas, error_aceptado, a, b, funcion_usar)
    if (a>b) then
        temp = a
        a = b
        b = temp
    end
    c = b-(funcion_usar(b)*(b-a))/(funcion_usar(b)-funcion_usar(a))
    i = 0
    while(abs(funcion_usar(c))>error_aceptado & i<iteraciones_maximas)
      if(funcion_usar(a)<0 & funcion_usar(c)<0) then
          a = c
      elseif(funcion_usar(a)>0 & funcion_usar(c)>0)
          a = c
      else
          b = c
      end
      i = i+1
      c = b-(funcion_usar(b)*(b-a))/(funcion_usar(b)-funcion_usar(a))
   end
   ans = c
   iteraciones = i
endfunction

function [ans, iteraciones] = newton_raphson(iteraciones_maximas, error_aceptado, x0, funcion_usar, derivada)
    i = 0
    xi = x0
    while(abs(funcion_usar(xi))>error_aceptado & i<iteraciones_maximas)
        xi = xi - funcion_usar(xi)/derivada(xi)
        i = i + 1
    end
    ans = xi
    iteraciones = i
endfunction

function [ans, iteraciones] = secante(iteraciones_maximas, error_aceptado, x0, x1, funcion_usar)
    i = 0
    xi_2 = x0
    xi_1 = x1
    xi = xi_1- (funcion_usar(xi_1)*(xi_1-xi_2))/(funcion_usar(xi_1)-funcion_usar(xi_2))
    while(abs(funcion_usar(xi))>error_aceptado & i<iteraciones_maximas)
        xi = xi_1- (funcion_usar(xi_1)*(xi_1-xi_2))/(funcion_usar(xi_1)-funcion_usar(xi_2))
        i = i + 1
    end
    ans = xi
    iteraciones = i
endfunction
