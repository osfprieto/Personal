package mios;

import java.io.IOException;

public class ShellTest {

	public static void main(String[] args) {
       
        try {
        	Runtime rt = Runtime.getRuntime();
			/*Process p = */rt.exec("cmd.exe dir");
			//p.destroy();
			System.out.println("run complete. Exit value = "/*+p.exitValue()*/);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
