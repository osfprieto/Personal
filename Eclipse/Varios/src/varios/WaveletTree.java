package varios;

public class WaveletTree {

	public String alphabet;
	public String representation;
	public WaveletTree left;
	public WaveletTree right;
	
	public static void main(String[] args){
		String string = "omar simon francisco prieto chacon";
		WaveletTree wt = new WaveletTree(string);
		System.out.println(string);
		for(int i=1;i<=string.length();i++)
			System.out.print(wt.rank("o", i));
		System.out.println(wt.select("o", 2));
		//WaveletTree wt = new WaveletTree("ahhahaahahade");
		//String cad = "asdfe";
		//System.out.println(cad.indexOf("d"));
	}
	
	public WaveletTree(String string){
		alphabet = "";
		int i;
		for(i=0;i<string.length();i++)
			if(!alphabet.contains(string.subSequence(i, i+1)))
				alphabet += string.charAt(i);
		
		representation = "";
		String leftString = "";
		String rightString = "";
		for(i=0;i<string.length();i++)
			if(alphabet.indexOf(""+string.charAt(i))<alphabet.length()/2){
				representation += "0";
				if(alphabet.length()>2)
					leftString += string.charAt(i);
			}
			else{
				representation += "1";
				if(alphabet.length()>2)
					rightString += string.charAt(i);
			}
		//System.out.println(alphabet);
		//System.out.println(string);
		//System.out.println(representation);
		if(alphabet.length()>2){
			left = new WaveletTree(leftString);
			right = new WaveletTree(rightString);
		}
	}
	
	
	public String access(int i){//muestra el caracter en la posición i de la cadena
		if(alphabet.length()==2)
			if(representation.charAt(i)=='0')
				return alphabet.charAt(0)+"";
			else if(representation.charAt(i)=='1')
				return alphabet.charAt(1)+"";
		if(alphabet.length()==1)
			return alphabet;
		
		int nuevoI = rank10(""+representation.charAt(i), i);
		
		if(representation.charAt(i)=='0')
			return left.access(nuevoI);
		else if(representation.charAt(i)=='1')
			return right.access(nuevoI);
		
		return "";
	}
	
	private int rank10(String a, int i){//muestra cuántas veces aparece a en la subcadena [1, i](1 o 0)
		int j;
		int cont = 0;
		for(j=0;j<i;j++)
			if((representation.charAt(j)+"").equals(a))
				cont++;
		return cont;
	}
	
	public int rank(String a, int i){//muestra cuántas veces aparece a en la subcadena [1, i](1 o 0)
		if(alphabet.length()==2)
			if(a.equals(alphabet.charAt(0)+""))
				return rank10("0", i);
			else//if(a.equals(alphabet.charAt(1)+""))
				return rank10("1", i);
		else if(alphabet.length()==1)
			return i;
		
		if(alphabet.indexOf(a)<alphabet.length()/2)//left(0)
			return left.rank(a, rank10("0", i));
		else//right(1)
			return right.rank(a, rank10("1", i));
	}
	
	public int select(String a, int j){//muestra en qué posición está la j-ésima posición de a en la cadena
		if(alphabet.length()==2){
			char busqueda;
			if(alphabet.indexOf(a)==alphabet.length()/2)
				busqueda='0';
			else//1
				busqueda='1';
			int cont=0;
			int i=0;
			while(cont<j && i<representation.length())
				if(representation.charAt(i++)==busqueda)
					cont++;
			return i;
		}
		if(alphabet.length()==1){
			char busqueda = '1';
			int cont=0;
			int i=0;
			while(cont<j && i<representation.length())
				if(representation.charAt(i++)==busqueda)
					cont++;
			return i;
		}
		
		if(alphabet.indexOf(a)<alphabet.length()/2)//0
			return select10("0", left.select(a, j));
		else//1
			return select10("1", right.select(a, j));
	}
	
	private int select10(String a, int j){
		int cont=0;
		int i=0;
		while(cont<j && i<representation.length())
			if(representation.charAt(i++)==a.charAt(0))
				cont++;
		return i;
	}
	
}
