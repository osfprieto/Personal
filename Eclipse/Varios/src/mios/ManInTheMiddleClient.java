package mios;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class ManInTheMiddleClient {

	private static Thread inputHandlingThread;  // From server to client.
	private static Thread outputHandlingThread;  // From client to server.
	
	private static OutputStream outStream;  // Closes on EOF
	private static InputStream inStream;  // Closes on "bye"
	
	private static Socket socket;
	private static boolean running;
	
	public static void main(String[] args) {
		try {
			String server = "localhost";
			int port = 5314;
			if(args.length>=1){
				server = args[0];
			}
			if(args.length>=2){
				port = Integer.parseInt(args[1]);
			}
			
			socket = new Socket(server, port);
			running = true;
			
			outStream = socket.getOutputStream();
			inStream = socket.getInputStream();
			
			inputHandlingThread = new Thread(new InputHandler()); // Client-to-server
			outputHandlingThread = new Thread(new OutputHandler()); // Server-to-client
			
			inputHandlingThread.start();
			outputHandlingThread.start();
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	private static void closeOut(){
		running = false;
		try {
			outStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if(!inputHandlingThread.isAlive()){
			try {
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void closeIn(){
		running = false;
		try {
			inStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(!outputHandlingThread.isAlive()){
			try {
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static class InputHandler implements Runnable{ // From server to client
		File requestDataFile;
		byte b[] = new byte[1024];
		@Override
		public void run() {
			try {
				JFileChooser fileChooser = new JFileChooser();
				int result = fileChooser.showDialog(null, "Select dumping file");
				if(result == JFileChooser.APPROVE_OPTION){
					requestDataFile = fileChooser.getSelectedFile();
					FileOutputStream fos = new FileOutputStream(requestDataFile);
					int readBytesCount = inStream.read(b, 0, 1024);
					while(readBytesCount>=0 && running){
						System.out.println("Receiving data");
						fos.write(b, 0, readBytesCount);
						readBytesCount = inStream.read(b, 0, 1024);
					}
					fos.close();
				}
				closeIn();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static class OutputHandler implements Runnable{ // From client to server
		private Scanner sc = new Scanner(System.in);
		@Override
		public void run() {
			while(sc.hasNext() && running){
				String line = sc.nextLine();
				try {
					outStream.write(line.getBytes());
					outStream.flush();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(line.toLowerCase().equals("bye"))
					break;
			}
			closeOut();
		}
	}
}
