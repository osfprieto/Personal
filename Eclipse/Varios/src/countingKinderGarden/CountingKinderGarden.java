package countingKinderGarden;

/*import java.util.*;

public class CountingKinderGarden {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer in;
		String token;
		in = new StringTokenizer(sc.nextLine());
		int n, i;
		char c;
		while(true){
			n=0;
			while(in.hasMoreTokens()){
				token = in.nextToken();
				for(i=0;i<token.length();i++){
					c = token.charAt(i);
					if((c>='a' && c<='z') || (c>='A' && c<='Z')){
						n++;
						break;
					}
				}
			}
			if(n<1)
				break;
			System.out.println(n);
			in = new StringTokenizer(sc.nextLine());
		}
	}

}*/

/*import java.util.*;

public class CountingKinderGarden {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNextLine()) {
            String line = in.nextLine();
            int cnt = 0;
            int i = 0;
            while (i < line.length()) {
                if (Character.isLetter(line.charAt(i))) {
                    ++cnt;
                    while (Character.isLetter(line.charAt(i))) ++i;
                }
                ++i;
            }
            System.out.println(cnt);
        }
    }
}*/

