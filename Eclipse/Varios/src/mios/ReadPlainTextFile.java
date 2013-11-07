package mios;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;

public class ReadPlainTextFile {

	public static void main(String[] args){
		
		String fileName = JOptionPane.showInputDialog(null, "Entre el nombre del archivo a leer:", "Leer archivo de texto.", JOptionPane.QUESTION_MESSAGE);
		int s = Integer.parseInt(JOptionPane.showInputDialog(null, "Entre el número de lineas mínimo que quiere leer:", "Leer archivo \""+fileName+'\"', JOptionPane.QUESTION_MESSAGE));
		int n = Integer.parseInt(JOptionPane.showInputDialog(null, "Entre el número de lineas máximo que quiere leer:", "Leer archivo \""+fileName+'\"', JOptionPane.QUESTION_MESSAGE));
		int i;
		StringBuilder show = new StringBuilder();
		String temp;
		try {
			FileReader fr = new FileReader(fileName);
			BufferedReader bf = new BufferedReader(fr);
			temp = bf.readLine();
			for(i=0;i<n && temp!=null && !temp.equals("") ;i++){
				if(i>=s)
					show.append(temp).append('\n');
				temp = bf.readLine();
			}
			bf.close();
			JTextArea area = new JTextArea();
			area.setText(show.toString());
			JScrollPane sp = new JScrollPane(area);
			JOptionPane.showMessageDialog(null, sp, "Archivo "+fileName, JOptionPane.INFORMATION_MESSAGE);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Archivo no encontrado!", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		catch (IOException e){
			JOptionPane.showMessageDialog(null, "IOException!", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
	}

}