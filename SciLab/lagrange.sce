//osfprieto@gmail.com
function vectorYInterpolar = vectorLagrange(xConocidos, yConocidos, vectorXInterpolar)
    i = 1
    resultados = []
    while(i<=length(vectorXInterpolar))
        resultados(i) = polinomioLagrange(xConocidos, yConocidos, vectorXInterpolar(i))
        i = i+1
    end
    vectorYInterpolar = resultados'
endfunction

function yInterpolar = polinomioLagrange(xConocidos, yConocidos, xInterpolar)
    suma = 0
    i = 1
    k = length(xConocidos)
    while(i<=k)
        suma = suma + yConocidos(i)*multiplicatoriaLagrange(xInterpolar, xConocidos, i)
        i = i+1
    end
    yInterpolar = suma
endfunction

function multiplicador = multiplicatoriaLagrange(xInterpolar, xConocidos, i)
    k = length(xConocidos)
    j = 1
    resultado = 1
    while(j<=k)
        if(i~=j) then
            resultado = resultado*((xInterpolar-xConocidos(j))/(xConocidos(i)-xConocidos(j)))
        end
        j = j+1
    end
    multiplicador = resultado
endfunction

function probarLagrange()
    x = [1 2 3 4 5]
    //y = [7 5 18 2 10]
    y = [603 762 1115 1384 1966]
    xBuscar = [0:0.1:6]
    yBuscar = vectorLagrange(x, y, xBuscar)
    plot(x, y, 'o', xBuscar, yBuscar, '-')
    
    x = [2 3]
    y = [762 1115]
    //disp(polinomioLagrange(x, y, 2.8))
endfunction
