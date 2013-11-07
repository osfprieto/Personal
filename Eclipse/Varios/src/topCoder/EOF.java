package topCoder;

import java.util.Scanner;
import java.util.StringTokenizer;

public class EOF {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int cases = 1;
		String sSize = sc.nextLine();
		
		while(!sSize.equals("0")) {
			int size = Integer.parseInt(sSize);
			String line = sc.nextLine();
			//System.out.println(size + " " +line);
			String buffer = "";
			StringTokenizer st = new StringTokenizer(line, " ", true);
			System.out.println("Case " + cases + ":\n");
			while(st.hasMoreTokens()) {
				String preS = st.nextToken();
				if ((buffer + preS).trim().length() == size) {
					System.out.println((buffer + preS).trim());
					buffer = "";
				} else if ((buffer + preS).trim().length() > size) {
					if (buffer.trim().length() == size) System.out.println(buffer.trim());
					else {
						for (int k=0; k < (size - buffer.trim().length()) / 2; k++) {
							System.out.print(" ");
						}
						
						System.out.println(buffer.trim());
						buffer = preS;
					}
				} else {
					buffer += preS;
				}
			}
			
			if (buffer.trim().length() == size) System.out.println(buffer.trim());
			else {
				for (int k=0; k < (size - buffer.trim().length()) / 2; k++) {
					System.out.print(" ");
				}
				
				System.out.println(buffer.trim());
			}
			System.out.println();
			cases++;
			sSize = sc.nextLine();
		}
	}

}
