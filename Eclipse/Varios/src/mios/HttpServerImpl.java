package mios;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Scanner;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class HttpServerImpl {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(314), 0);
        server.createContext("/", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {    	
            String res = "OK";
            exchange.sendResponseHeaders(200, res.length());
            OutputStream os = exchange.getResponseBody();
            os.write(res.getBytes());
            os.close();
            
            StringBuffer sb = new StringBuffer();
            sb.append("Remote address: ").append(exchange.getRemoteAddress().toString()).append("\n");
            sb.append("Protocol: ").append(exchange.getProtocol()).append("\n");
            sb.append("Method: ").append(exchange.getRequestMethod()).append("\n");
            sb.append("Request URI: ").append(exchange.getRequestURI()).append("\n");
            
            Headers headers = exchange.getRequestHeaders();
            for(String key : headers.keySet()){
            	sb.append("Header '").append(key).append("': ");
            	for(String value : headers.get(key))
            		sb.append("'").append(value).append("'");
            	sb.append("\n");
            }
            	
            sb.append("Request body start-----\n");
            Scanner sc = new Scanner(exchange.getRequestBody());
            while(sc.hasNext())
            	sb.append(sc.nextLine()).append("\n");
            sc.close();
            sb.append("Request body end-------\n");
            
            System.out.println(sb.toString());
        }
    }

}