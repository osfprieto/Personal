package mios;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ManInTheMiddleStandAlone {

	private static final int BUFFER_SIZE = 8192;
	
	private static int peekedPort = 6314;
	private static String redirectHost = "190.26.196.134";  // /mcip/index.php
	private static int redirectPort = 80;
	private static String requestsFile = "requests.txt";
	private static String responsesFile = "responses.txt";
	
	private static FileOutputStream requestsFos;
	private static FileOutputStream responsesFos;
	
	
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
			requestsFos = new FileOutputStream(new File (requestsFile));
			responsesFos = new FileOutputStream(new File(responsesFile));
			while(true){
				Socket peekedSocket = peekedServerSocket.accept();
				PeekedClient peekedClient = new PeekedClient(peekedSocket);
				Thread thread = new Thread(peekedClient);
				thread.start();
			}
		}catch (Exception e){
			e.printStackTrace();
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
						byte[] b = new byte[BUFFER_SIZE];
						try{
							int readCount = clientToServerInputStream.read(b, 0, BUFFER_SIZE);
							while(readCount>=0){
								System.out.println("Request received");
								requestsFos.write(b, 0, readCount); // We log the original data sent from the peeked client
								requestsFos.flush();
								String temp = new String(b, 0, readCount);
								temp = temp.replaceAll("Host: \\d+\\.\\d+\\.\\d+\\.\\d+:\\d+", "Host: "+redirectHost+":"+redirectPort);
								serverToRedirectedOutputStream.write(temp.getBytes(), 0, temp.getBytes().length); // We send an updated request to the server 
								serverToRedirectedOutputStream.flush();
								readCount = clientToServerInputStream.read(b, 0, BUFFER_SIZE);
							}
							clientToServerInputStream.close();
							serverToRedirectedOutputStream.close();
						} catch (Exception e){
							e.printStackTrace();
						}
					}
				});
				
				Thread downThread = new Thread(new Runnable(){
					@Override
					public void run(){
						byte[] b = new byte[BUFFER_SIZE];
						try{
							int readCount = redirectedToServerInputStream.read(b, 0, BUFFER_SIZE);
							while(readCount>=0){
								System.out.println("Response received");
								responsesFos.write(b, 0, readCount);
								responsesFos.flush();
								serverToClientOutputStream.write(b, 0, readCount);
								serverToClientOutputStream.flush();
								readCount = redirectedToServerInputStream.read(b, 0, BUFFER_SIZE);
							}
							redirectedToServerInputStream.close();
							serverToClientOutputStream.close();
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
