package varios;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SocketServer {

	private static ArrayList<Client> clients = new ArrayList<Client>();
	
	public static void main(String[] args) {
		try {
			@SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket(314);
			while(true){
				Socket socket = serverSocket.accept();
				System.out.println("New connection: "+socket.getInetAddress().toString());
				broadcastMessage(socket.getInetAddress().toString()+" connected");
				Client client = new Client(socket);
				clients.add(client);
				Thread thread = new Thread(client);
				thread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void broadcastMessage(String msg){
		for(Client client : clients){
			client.sendMessage(msg);
		}
	}
	
	private static void closeClient(Client client){
		System.out.println("Client leaving: "+client.getSocket().getInetAddress().toString());
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
		clients.remove(client);
	}

	private static class Client implements Runnable{
		private Socket socket; 
		
		private OutputStream outStream;
		private InputStream inStream;
		
		private byte b[] = new byte[1024];
		
		public Client(Socket socket){
			this.socket = socket;
			try {
				outStream = socket.getOutputStream();
				inStream = socket.getInputStream();
			} catch (Exception e) {
				System.out.println("Can't stablish connection with "+socket.getInetAddress().toString());
				closeClient(this);
			}
		}
		
		@Override
		public void run(){
			try {
				int readBytesCount = inStream.read(b, 0, 1024);
				String st = "";
				while(readBytesCount>=0){
					st = new String(b, 0, readBytesCount);
					broadcastMessage(socket.getInetAddress().toString()+": "+st);
					readBytesCount = inStream.read(b, 0, 1024);
					if(st.toLowerCase().equals("bye"))
						break;
				}
				closeClient(this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void sendMessage(String msg){
			try {
				outStream.write(msg.getBytes());
				outStream.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public Socket getSocket() {
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
