
package server;
import java.net.*;
import java.io.*;
import java.awt.*;

public class Servidor extends Frame{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1228810199059407922L;
	TextField titulo,Estado;
    public Servidor(){
    super("Servidor");
    Panel p1 = new Panel();
    Panel p2=new Panel();
   Label l2=new Label("Estado:");
    titulo=new TextField("........SERVIDOR...........",40);
    titulo.setEditable(false);
    Estado=new TextField(40);
    p1.setLayout(new FlowLayout());
    p1.add(titulo);
    p2.setLayout(new FlowLayout());
    p2.add(Estado);
    p2.add(l2);
    add("North", p1);
    add("Center", p2);
    }
    
    @SuppressWarnings("deprecation")
	public void start() {
        resize(400, 150);
        show();
    }

    public void ejecutar(){
    try {
            ServerSocket ss = new ServerSocket(1337); //se define ael lpuerto al que se va aconectar
            while (true) {
                Socket s = ss.accept(); // se crea el socket para tealizar la coneccion
                Estado.setText("Se conecto " + s.getInetAddress() + " en el puerto " + s.getPort());
                InputStream in = s.getInputStream(); // se define el flujo de entrada
                OutputStream out = s.getOutputStream(); // se define el flujo de alida
                byte entrada[] = new byte[256]; //se crea un arreglo lara el lo que llegue
                int llegada = in.read(entrada);//se verifica el nombre de el archivo
                if (llegada < 0) {
                    Estado.setText("El archvo esta mal");
                    continue;
                }
                String nombreArchivo = new String(entrada, 0, llegada);
                Estado.setText("Archivo:" + nombreArchivo);
                byte a[] = new byte[1];
                if (new File(nombreArchivo).exists()) {
                    a[0] = (byte) 0;
                    Estado.setText(nombreArchivo + " ya existia");
                    out.write(a); // envia un 0 para decir que el archivo ya exsiste
                    continue;
                } else {
                    a[0] = (byte) 1;
                    out.write(a);
                    byte ar[] = new byte[100000];// se define este arreglo para ubicar ahi el archivo que se va a pasar
                    int llegadaAr = in.read(ar);
                    if (llegadaAr < 0) {
                        continue;
                    } else {
                        FileOutputStream flout = new FileOutputStream(nombreArchivo); // se define el flujo de salida de archivos
                        for (int n = 0; n < llegadaAr; n++) {
                            flout.write(ar[n]);
                        }
                        Estado.setText("Archivo recibido");
                    }
                }
            }
        } catch (IOException phi) {
            System.err.println("Ups!");
        }
    }
}

