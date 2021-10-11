package misc;

import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class HttpClient {
	public static void main(String[] args) {
		try {
			URL url = new URL("http://localhost:314/test");
			InputStream inputStream = url.openStream();
			Scanner sc = new Scanner(inputStream);
			while(sc.hasNext())
				System.out.println(sc.nextLine());
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
