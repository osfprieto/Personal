package topCoder;

import java.util.Scanner;

public class NumeralSystem {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		String cIn1, cIn2;
		int m3, c3, x3, i3;
		for(int k=0;k<t;k++){
			int m1=0, c1=0, x1=0, i1=0;
			int m2=0, c2=0, x2=0, i2=0;
			cIn1 = sc.next();
			cIn2 = sc.next();
			for(int j=cIn1.length()-1;j>=0;j--){
				if(cIn1.charAt(j) == 'i' && j>0){
					if(cIn1.charAt(j-1)<'a' || cIn1.charAt(j-1)>'z'){
						i1 = Integer.parseInt(""+cIn1.charAt(j-1));
					}
					else
						i1=1;
				}
				else if(cIn1.charAt(j) == 'i' && j==0){
					i1 = 1;
				}
				else if(cIn1.charAt(j) == 'x' && j>0){
					if(cIn1.charAt(j-1)<'a' || cIn1.charAt(j-1)>'z'){
						x1 = Integer.parseInt(""+cIn1.charAt(j-1));
					}
					else
						x1=1;
				}
				else if(cIn1.charAt(j) == 'x' && j==0){
					x1 = 1;
				}
				else if(cIn1.charAt(j) == 'c' && j>0){
					if(cIn1.charAt(j-1)<'a' || cIn1.charAt(j-1)>'z'){
						c1 = Integer.parseInt(""+cIn1.charAt(j-1));
					}
					else
						c1=1;
				}
				else if(cIn1.charAt(j) == 'c' && j==0){
					c1 = 1;
				}
				else if(cIn1.charAt(j) == 'm' && j>0){
					if(cIn1.charAt(j-1)<'a' || cIn1.charAt(j-1)>'z'){
						m1 = Integer.parseInt(""+cIn1.charAt(j-1));
					}
					else
						m1=1;
				}
				else if(cIn1.charAt(j) == 'm' && j==0){
					m1 = 1;
				}
			}
			for(int j=cIn2.length()-1;j>=0;j--){
				if(cIn2.charAt(j) == 'i' && j>0){
					if(cIn2.charAt(j-1)<'a' || cIn2.charAt(j-1)>'z'){
						i2 = Integer.parseInt(""+cIn2.charAt(j-1));
					}
					else
						i2=1;
				}
				else if(cIn2.charAt(j) == 'i' && j==0){
					i2 = 1;
				}
				else if(cIn2.charAt(j) == 'x' && j>0){
					if(cIn2.charAt(j-1)<'a' || cIn2.charAt(j-1)>'z'){
						x2 = Integer.parseInt(""+cIn2.charAt(j-1));
					}
					else
						x2=1;
				}
				else if(cIn2.charAt(j) == 'x' && j==0){
					x2 = 1;
				}
				else if(cIn2.charAt(j) == 'c' && j>0){
					if(cIn2.charAt(j-1)<'a' || cIn2.charAt(j-1)>'z'){
						c2 = Integer.parseInt(""+cIn2.charAt(j-1));
					}
					else
						c2=1;
				}
				else if(cIn2.charAt(j) == 'c' && j==0){
					c2 = 1;
				}
				else if(cIn2.charAt(j) == 'm' && j>0){
					if(cIn2.charAt(j-1)<'a' || cIn2.charAt(j-1)>'z'){
						m2 = Integer.parseInt(""+cIn2.charAt(j-1));
					}
					else
						m2=1;
				}
				else if(cIn2.charAt(j) == 'm' && j==0){
					m2 = 1;
				}
			}
			
			
			m3 = m1 + m2;
			c3 = c1 + c2;
			x3 = x1 + x2;
			i3 = i1 + i2;
			
			if(i3>9){
				x3++;
				i3%=10;
			}
			if(x3>9){
				c3++;
				x3%=10;
			}
			if(c3>9){
				m3++;
				c3%=10;
			}
			
			String cout = "";
			
			if(m3>0){
				if(m3>1)
					cout+=m3+"";
				cout+="m";
			}
			if(c3>0){
				if(c3>1)
					cout+=c3+"";
				cout+="c";
			}
			if(x3>0){
				if(x3>1)
					cout+=x3+"";
				cout+="x";
			}
			if(i3>0){
				if(i3>1)
					cout+=i3+"";
				cout+="i";
			}
			
			System.out.println(cout);
			
		}
	}
}
