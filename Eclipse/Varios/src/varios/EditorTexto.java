package varios;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

import javax.swing.*;

@SuppressWarnings("serial")
public class EditorTexto extends JFrame implements ActionListener{

	private File archivo;
	
	private JTextArea area;
	private JScrollPane scroll;
	
	private JButton botonCerrar;
	private JButton botonGuardar;
	private JButton botonGuardarComo;
	private JButton botonLimpiar;
	private JButton botonAbrir;
	private JButton botonAumentar;
	private JButton botonDisminuir;
	
	private JPanel panelEditor;
	private JPanel panelBotones;
	private JPanel panelPrincipal;
	private JPanel panelTamanio;
	
	private JFileChooser fileChooser;
	private Container cont;
	
	public static void main(String[] args) {
		EditorTexto et = new EditorTexto();
		et.setVisible(true);
	}
	
	public EditorTexto(){
		super("osfprieto's notepad");
		cont = getContentPane();
		archivo = null;
		area = new JTextArea();
		area.setTabSize(4);
		//area.setFont(new Font("Ubuntu Italic", Font.BOLD, 24));
		scroll = new JScrollPane(area);
		//tamanio = area.getFont().getSize();
		
		botonCerrar = new JButton("Cerrar");
		botonCerrar.addActionListener(this);
		botonGuardar = new JButton("Guardar");
		botonGuardar.addActionListener(this);
		botonGuardarComo = new JButton("Guardar como");
		botonGuardarComo.addActionListener(this);
		botonLimpiar = new JButton("Limpiar");
		botonLimpiar.addActionListener(this);
		botonAbrir = new JButton("Abrir");
		botonAbrir.addActionListener(this);
		botonAumentar = new JButton("+");
		botonAumentar.addActionListener(this);
		botonDisminuir = new JButton("-");
		botonDisminuir.addActionListener(this);
		
		panelEditor = new JPanel();
		panelEditor.setLayout(new BorderLayout());
		panelEditor.add(scroll, BorderLayout.CENTER);
		
		panelTamanio = new JPanel();
		panelTamanio.setLayout(new GridLayout(1, 2));
		panelTamanio.add(botonAumentar);
		panelTamanio.add(botonDisminuir);
		
		panelBotones = new JPanel();
		panelBotones.setLayout(new GridLayout(6, 1));
		panelBotones.add(botonAbrir);
		panelBotones.add(botonGuardar);
		panelBotones.add(botonGuardarComo);
		panelBotones.add(botonLimpiar);
		panelBotones.add(panelTamanio);
		panelBotones.add(botonCerrar);
		
		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BorderLayout());
		panelPrincipal.add(panelEditor, BorderLayout.CENTER);
		panelPrincipal.add(panelBotones, BorderLayout.WEST);
		
		fileChooser = new JFileChooser();
		fileChooser.setMultiSelectionEnabled(false);
		cont.removeAll();
		cont.add(panelPrincipal);
		setBounds(300, 100, 800, 500);
		setUndecorated(true);
	}
	
	private void guardar(){
		if(archivo==null)
			guardarComo();
		else{
			try{
				FileWriter fw = new FileWriter(archivo);
				PrintWriter pw = new PrintWriter(fw);
				String lineas[] = area.getText().split("\n");
				for(int i=0;i<lineas.length;i++)
					pw.printf("%s%n", lineas[i]);
				pw.close();
				JOptionPane.showMessageDialog(null, "Archivo guardado", "Exito", JOptionPane.INFORMATION_MESSAGE);
			}
			catch(IOException e){
				JOptionPane.showMessageDialog(null, "Error en tiempo de guardado", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void guardarComo(){
		fileChooser.setApproveButtonText("Guardar");
		int respuesta = fileChooser.showOpenDialog(null);
		if(respuesta==JFileChooser.APPROVE_OPTION){
			archivo = fileChooser.getSelectedFile();
			guardar();
		}
	}
	
	private void abrir(){
		fileChooser.setApproveButtonText("Abrir");
		int respuesta = fileChooser.showOpenDialog(null);
		if(respuesta==JFileChooser.APPROVE_OPTION){
			try{
				archivo = fileChooser.getSelectedFile();
				StringBuilder sb = new StringBuilder();
				@SuppressWarnings("resource")
				Scanner sc = new Scanner(archivo);
				while(sc.hasNext())
					sb.append(sc.nextLine()).append("\n");
				area.setText(sb.toString());
			}
			catch(FileNotFoundException e){
				JOptionPane.showMessageDialog(null, "Archivo no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void aumentarFuente(){
		Font font = area.getFont();
		area.setFont(new Font(font.getName(), font.getStyle(), font.getSize()+2));
	}
	private void disminuirFuente(){
		Font font = area.getFont();
		area.setFont(new Font(font.getName(), font.getStyle(), font.getSize()-2));
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(botonCerrar))
			System.exit(0);
		else if(e.getSource().equals(botonLimpiar))
			area.setText("");
		else if(e.getSource().equals(botonGuardar))
			guardar();
		else if(e.getSource().equals(botonGuardarComo))
			guardarComo();
		else if(e.getSource().equals(botonAbrir))
			abrir();
		else if(e.getSource().equals(botonAumentar))
			aumentarFuente();
		else if(e.getSource().equals(botonDisminuir))
			disminuirFuente();
	}
}
