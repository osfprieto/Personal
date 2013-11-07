package coursera;

//https://class.coursera.org/algs4partI-002/assignment/view?assignment_id=1

public class Percolation{

	private boolean open[][];
	private boolean full[][];
	
	public Percolation(int n){
		open = new boolean[n][n];
		full = new boolean[n][n];
		int i, j;
		for(i=0;i<n;i++)
			for(j=0;j<n;j++)
				open[i][j] = full[i][j] = false;
	}
	
	public void open(int i, int j) throws IndexOutOfBoundsException{
		
	}
	
}
