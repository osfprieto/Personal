function [Py] = Proyectos(t)
    Py = 9.9*t^3-34.8*t^2+42.2*t+376
endfunction

function [y] = f(t)
    y = 9.9*t^3-34.8*t^2+42.2*t+376 - 1000
endfunction

function result = test(testid)
    result = 1
    if(testid == 0) then
        t = 0:0.1:5
        plot2d(t, Proyectos(t))
    elseif(testid == 1) then
        t = (0:0.1:5)'
        plot2d(t, [Proyectos(t) f(t)])
    elseif(testid == 2) then
        p = poly ([376 242.2 -34.8 9.9], "t", "coeff")
        disp(p)
        disp(roots (p))
        p = poly ([376-1000 242.2 -34.8 9.9], "t", "coeff")
        disp(p)
        disp(roots (p))
    else
        result  = 0
    end
endfunction
