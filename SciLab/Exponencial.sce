function [fact]= fact(n)
    fact=1;
    for i=1:1:n
        fact=fact*i;
    end
endfunction

function [aprx, c]=exponencial(x, ni, err)
    format('v', 10)
    aprx=0;
    tn = %inf;
    vv = exp(x);
    
    n=0
    while(n<ni & tn>err)
        tn = (x^(n))/fact(n)
        c(n+1) = tn
        aprx = aprx+tn
        eva = abs(vv-aprx)
        evr = (eva/vv)*100
        eaa = abs(tn);
        ear = (eaa/aprx)*100
        disp([tn,aprx, eva, evr, tn, ear])
        n = n + 1
    end
    plot2d(c)
    disp(poly(c, "x"))
endfunction
