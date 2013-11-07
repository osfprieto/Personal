package tests;

import javax.swing.JOptionPane;

public class Test {
	public static void main(String[] args) {
		String input = JOptionPane.showInputDialog(null, "Entre su cosito:");
		String[] result = input.split(";");
		for(int i=0;i<result.length;i++)
			System.out.println(result[i]);
	}
}
