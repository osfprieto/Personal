
package cliente;

import java.net.*;
import java.io.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

public class client extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = -714106510693842886L;
    public String lectura;
    public String output;
    public JTextField salida,entrada;


    public client() {
        super("Cliente");
        lectura = new String();
        output = new String();
        JButton envio = new JButton("Enviar");
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        p3.setLayout(new FlowLayout());
        p3.add(envio);
        JLabel nombre = new JLabel(" Nombre del archivo:");
        JLabel enviado = new JLabel("Estado Archivo");
        entrada = new JTextField(lectura, 30);
        salida = new JTextField(output, 30);
        p1.setLayout(new FlowLayout(FlowLayout.LEFT));
        p2.setLayout(new FlowLayout(FlowLayout.LEFT));
        p1.add(nombre);
        p1.add(entrada);
        p2.add(enviado);
        p2.add(salida);
        add("North", p1);
        add("Center", p2);
        add("South", p3);
    }


	public void start() {
        setSize(400, 150);
        setVisible(true);
    }

    public boolean action(Event evt, Object obj) {
        if (evt.target instanceof Button) {

            if ("Enviar".equals(obj)) {
                output = "el archivo esta siendo analizado...";
                salida.setText(output);
                try {
                    envio();
                } catch (IOException ex) {
                    Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
                    output = "ERROR...";
                    salida.setText(output);
                }

            }
            return (true);
        }
        return (true);
    }

    public void envio() throws IOException {
        String x = new String();
        x = entrada.getText();
        Socket s = new Socket(InetAddress.getByName("127.0.0.1"), 1337);
        OutputStream out = s.getOutputStream();
        InputStream in = s.getInputStream();
        if (new File(x).exists()) {
            out.write(x.getBytes());
            byte flag[] = new byte[1];
            in.read(flag);
            if (flag[0] == (byte) 1) {
                FileInputStream flin = new FileInputStream(x);
                byte ar[] = new byte[(int) (new File(x).length())];
                flin.read(ar);
                out.write(ar);
                output = "Archivo enviado";
                salida.setText(output);
            } else {
                output = "Archivo ya existente";
                salida.setText(output);
            }
        } else {
            output = "Archivo Corrupto o Inexistente";
        }
        salida.setText(output);
    }
}
