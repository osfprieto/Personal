package mios;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ManInTheMiddleStandAlone {

	private static int peekedPort = 6314;
	private static String redirectHost = "localhost";// "190.26.196.134";  // /mcip/index.php
	private static int redirectPort = 80;
	private static String requestsFile = "requests.txt";
	private static String responsesFile = "responses.txt";
	
	
	public static void main(String[] args) {
		if(args.length>=1){
			peekedPort = Integer.parseInt(args[0]);
		}
		if(args.length>=2){
			redirectHost = args[1];
		}
		if(args.length>=3){
			redirectPort = Integer.parseInt(args[2]);
		}
		if(args.length>=4){
			requestsFile = args[3];
		}
		if(args.length>=5){
			responsesFile = args[4];
		}

		try{
			ServerSocket peekedServerSocket = new ServerSocket(peekedPort);
			while(true){
				Socket peekedSocket = peekedServerSocket.accept();
				PeekedClient peekedClient = new PeekedClient(peekedSocket);
				Thread thread = new Thread(peekedClient);
				thread.start();
			}
		}catch (Exception e){
			
		}
	}
	
	private static class PeekedClient implements Runnable{

		private Socket redirectSocket;
		private Socket peekedSocket;
		private InputStream clientToServerInputStream;
		private OutputStream serverToClientOutputStream;
		private OutputStream serverToRedirectedOutputStream;
		private InputStream redirectedToServerInputStream;
		
		public PeekedClient(Socket peekedSocket){
			try{
				this.peekedSocket = peekedSocket;
				this.clientToServerInputStream = peekedSocket.getInputStream();
				this.serverToClientOutputStream = peekedSocket.getOutputStream();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			try {
				System.out.println("New peeked");
				redirectSocket = new Socket(redirectHost, redirectPort);
				
				serverToRedirectedOutputStream = redirectSocket.getOutputStream();
				redirectedToServerInputStream = redirectSocket.getInputStream();
				
				Thread upThread = new Thread(new Runnable(){
					@Override
					public void run(){
						byte[] b = new byte[1024];
						try{
							FileOutputStream requestsFos = new FileOutputStream(new File (requestsFile));
							int readCount = clientToServerInputStream.read(b, 0, 1024);
							while(readCount>=0){
								System.out.println("Request received");
								requestsFos.write(b, 0, readCount);
								requestsFos.flush();
								serverToRedirectedOutputStream.write(b, 0, readCount);
								readCount = clientToServerInputStream.read(b, 0, 1024);
							}
							clientToServerInputStream.close();
							serverToRedirectedOutputStream.flush();
							serverToRedirectedOutputStream.close();
							requestsFos.close();
						} catch (Exception e){
							e.printStackTrace();
						}
					}
				});
				
				Thread downThread = new Thread(new Runnable(){
					@Override
					public void run(){
						byte[] b = new byte[1024];
						try{
							FileOutputStream responsesFos = new FileOutputStream(new File(responsesFile));
							int readCount = redirectedToServerInputStream.read(b, 0, 1024);
							while(readCount>=0){
								System.out.println("Response received");
								responsesFos.write(b, 0, readCount);
								responsesFos.flush();
								serverToClientOutputStream.write(b, 0, readCount);
								readCount = redirectedToServerInputStream.read(b, 0, 1024);
							}
							redirectedToServerInputStream.close();
							serverToClientOutputStream.flush();
							serverToClientOutputStream.close();
							responsesFos.close();
						} catch (Exception e){
							e.printStackTrace();
						}
					}
				});
				
				upThread.start();
				downThread.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
