package varios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BinarySearch {
	public static void main(String[] args) {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer line;
		try {
			line = new StringTokenizer(sc.readLine());
			int n = Integer.parseInt(line.nextToken());
			int q = Integer.parseInt(line.nextToken());
			int i;
			
			ArrayList<String> datos = new ArrayList<String>();
			String data[] = sc.readLine().split(" ");
			
			for(i=0;i<n;i++)
				datos.add(data[i]);
			
			for(i=0;i<q;i++)
				System.out.println(datos.indexOf(sc.readLine()));
		} catch (IOException e) {
			
		}
		
	}
}
