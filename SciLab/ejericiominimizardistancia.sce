function ans = derivadadistancia(x)
    numerador = -3 - x + 2*x^3
    denominador = sqrt(10 - 6*x - x*x + x*x*x*x)
    ans = numerador/denominador
endfunction
