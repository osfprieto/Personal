import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ManInTheMiddleServer {

	private static ArrayList<PeekerClient> peekers = new ArrayList<PeekerClient>();
	private static String redirectHost = "facebook.com"; 
	private static int redirectPort = 6314;
	
	public static void main(String[] args) {
		int portToListen = 6314;
		int portPeekers = 5314;
		if(args.length>=1){
			portToListen = Integer.parseInt(args[0]);
		}
		if(args.length>=2){
			portPeekers= Integer.parseInt(args[1]);
		}
		Thread peekedThread = new Thread(new PeekedRunnable(portToListen));
		Thread peekerThread = new Thread(new PeekerRunnable(portPeekers));
		
		peekedThread.start();
		peekerThread.start();
	}
	
	private static void broadcastMessageToPeekers(byte[] b, int count){
		System.out.println("Broadcasting");
		for(PeekerClient client : peekers){
			client.sendMessage(b, count);
		}
	}
	
	private static void closePeekerClient(PeekerClient client){
		try {
			client.getOutStream().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			client.getInStream().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			client.getSocket().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		peekers.remove(client);
	}

	private static class PeekedRunnable implements Runnable{
		private ServerSocket peekedServerSocket;
		private int peekedPort;
		
		public PeekedRunnable(int peekedPort){
			this.peekedPort = peekedPort;
		}

		@Override
		public void run() {
			try {
				peekedServerSocket = new ServerSocket(peekedPort);
				while(true){
					Socket peekedSocket = peekedServerSocket.accept();
					PeekedClient peekedClient = new PeekedClient(peekedSocket);
					Thread thread = new Thread(peekedClient);
					thread.start();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static class PeekerRunnable implements Runnable{
		
		private ServerSocket peekerServerSocket;
		private int peekerPort;
		
		public PeekerRunnable(int peekerPort){
			this.peekerPort = peekerPort;
		}

		@Override
		public void run() {
			try{
				peekerServerSocket = new ServerSocket(peekerPort);
				while(true){
					Socket peekerSocket = peekerServerSocket.accept();
					PeekerClient peekerClient = new PeekerClient(peekerSocket);
					peekers.add(peekerClient);
					Thread thread = new Thread(peekerClient);
					thread.start();
				}
			} catch (Exception e){
				e.printStackTrace();
			}
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
							int readCount = clientToServerInputStream.read(b, 0, 1024);
							while(readCount>=0){
								broadcastMessageToPeekers(b, readCount);
								serverToRedirectedOutputStream.write(b, 0, readCount);
								readCount = clientToServerInputStream.read(b, 0, 1024);
							}
							clientToServerInputStream.close();
							serverToRedirectedOutputStream.flush();
							serverToRedirectedOutputStream.close();
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
							int readCount = redirectedToServerInputStream.read(b, 0, 1024);
							while(readCount>=0){
								broadcastMessageToPeekers(b, readCount);
								serverToClientOutputStream.write(b, 0, readCount);
								readCount = redirectedToServerInputStream.read(b, 0, 1024);
							}
							redirectedToServerInputStream.close();
							serverToClientOutputStream.flush();
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
	
	private static class PeekerClient implements Runnable{
		private Socket socket;
		
		private OutputStream outStream;  // Server to client
		private InputStream inStream;  // Client to server
		
		private byte b[] = new byte[1024];
		
		public PeekerClient(Socket socket){
			this.socket = socket;
			try {
				outStream = socket.getOutputStream();
				inStream = socket.getInputStream();
			} catch (Exception e) {
				closePeekerClient(this);
			}
		}
		
		@Override
		public void run(){
			try {
				System.out.println("New peeker");
				int readBytesCount = inStream.read(b, 0, 1024);
				while(readBytesCount>=0){
					String st = new String(b, 0, readBytesCount);
					if(st.startsWith("sethost")){
						redirectHost = st.substring(st.lastIndexOf(" ")).trim();
						System.out.println("Redirecting to "+redirectHost);
					} else if(st.startsWith("setport")){
						redirectPort = Integer.parseInt(st.substring(st.lastIndexOf(" ")).trim());
						System.out.println("Redirecting through port "+redirectPort);
					}
					readBytesCount = inStream.read(b, 0, 1024);
					if(st.toLowerCase().equals("bye"))
						break;
				}
				closePeekerClient(this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void sendMessage(byte[] b, int count){
			try {
				outStream.write(b, 0, count);
				outStream.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public Socket getSocket(){
			return socket;
		}
		
		public OutputStream getOutStream() {
			return outStream;
		}

		public InputStream getInStream() {
			return inStream;
		}
	}
}
