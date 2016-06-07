package mios;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {

	private static Thread inputHandlingThread;  // From server to client.
	private static Thread outputHandlingThread;  // From client to server.
	
	private static OutputStream outStream;  // Closes on EOF
	private static InputStream inStream;  // Closes on "bye"
	
	private static Socket socket;
	private static boolean running;
	
	public static void main(String[] args) {
		try {
			socket = new Socket("localhost", 314);
			running = true;
			
			outStream = socket.getOutputStream();
			inStream = socket.getInputStream();
			
			inputHandlingThread = new Thread(new InputHandler());
			outputHandlingThread = new Thread(new OutputHandler());
			
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
		byte b[] = new byte[1024];
		@Override
		public void run() {
			try {
				int readBytesCount = inStream.read(b, 0, 1024);
				String st = "";
				while(readBytesCount>=0 && running){
					st = new String(b, 0, readBytesCount);
					System.out.println(st);
					readBytesCount = inStream.read(b, 0, 1024);
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
