package varios;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

public class ReadTextFile {

	public static void main(String[] args) {
		
		String fileName = JOptionPane.showInputDialog(null, "Entre el nombre del archivo a leer:", "Leer archivo de texto.", JOptionPane.QUESTION_MESSAGE);
		int s = Integer.parseInt(JOptionPane.showInputDialog(null, "Entre el número de lineas mínimo que quiere leer:", "Leer archivo \""+fileName+".txt\"", JOptionPane.QUESTION_MESSAGE));
		int n = Integer.parseInt(JOptionPane.showInputDialog(null, "Entre el número de lineas máximo que quiere leer:", "Leer archivo \""+fileName+".txt\"", JOptionPane.QUESTION_MESSAGE));
		int i;
		String show = "";
		String temp;
		try {
			FileReader fr = new FileReader(fileName+".txt");
			BufferedReader bf = new BufferedReader(fr);
			temp = bf.readLine();
			for(i=0;i<n && temp!=null && !temp.equals("") ;i++){
				if(i>=s)
					show+=temp+'\n';
				temp = bf.readLine();
			}
			bf.close();
			JOptionPane.showMessageDialog(null, show, "Archivo "+fileName, JOptionPane.INFORMATION_MESSAGE);
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
