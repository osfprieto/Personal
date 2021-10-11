package misc;

import java.util.ArrayList;
import java.util.Scanner;

public class SithRulers {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = Integer.parseInt(sc.nextLine().trim());
		int i;
		ArrayList<Trio> trios = new ArrayList<Trio>();
		for(i=0;i<n;i++){
			String name = sc.nextLine().trim();
			String tokens[] = sc.nextLine().trim().split(" ");
			double inicio = Double.parseDouble(tokens[0]);
			double fin = Double.parseDouble(tokens[1]);
			trios.add(new Trio(name, inicio, fin));
		}
		n = Integer.parseInt(sc.nextLine().trim());
		for(i=0;i<n;i++){
			int year = Integer.parseInt(sc.nextLine().trim());
			System.out.println("Galactic year "+year+": "+encontrar(trios, year));
		}
		
	}
	
	public static String encontrar(ArrayList<Trio> trios, int year){
		int i;
		Trio trio;
		String ret="";
		for(i=0;i<trios.size();i++){
			trio = trios.get(i);
			if(trio.isInYear(year))
				ret += trio.name+", ";
		}
		if(ret.equals(""))
			return "None";
		else
			return ret.substring(0, ret.length()-2);
	}
	
	public static class Trio{
		public String name;
		public int inicio;
		public int fin;
		public Trio(String name, double inicio, double fin){
			this.name = name;
			this.inicio = (int)inicio;
			this.fin = (int)fin;
		}
		public boolean isInYear(int year){
			return inicio<=year && fin>=year;
		}
	}
}
