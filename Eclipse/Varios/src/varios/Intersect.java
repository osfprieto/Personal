package varios;

import java.awt.Point;
import java.util.Scanner;

public class Intersect {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		int n, xs, ys, xe, ye, xl, yt, xr, yb, i;
		Line l[] = new Line[5];
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		
		for(i=0;i<n;i++){
			xs = sc.nextInt();
			ys = sc.nextInt();
			xe = sc.nextInt();
			ye = sc.nextInt();
			xl = sc.nextInt();
			yt = sc.nextInt();
			xr = sc.nextInt();
			yb = sc.nextInt();
			
			l[0] = new Line(xs, ys, xe, ye);//linea
			l[1] = new Line(xl, yt, xr, yt);//superior
			l[2] = new Line(xl, yb, xr, yb);//inferior
			l[3] = new Line(xl, yb, xl, yt);//izquierda
			l[4] = new Line(xr, yb, xr, yt);//derecha
			
			System.out.println((l[0].intersects(l[1]) || l[0].intersects(l[2]) || l[0].intersects(l[3]) || l[0].intersects(l[4]))?"T":"F");
		}
	}
	
	public static class Line{
		
		Point ini;
		Point fin;
		
		public Line(int xi, int yi, int xf, int yf){
			ini = new Point(xi, yi);
			fin = new Point(xf, yf);
		}
		
		public boolean intersects(Line linea){
			
			return false;
		}
	}
}
