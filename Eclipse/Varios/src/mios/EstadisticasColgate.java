package mios;

import java.io.*;
import java.util.*;
import javax.swing.*;

/* Esta clase es una modificación más completa de la clase SentenceStatistics
 * ya que en este caso, el método main, revisa todo el contenido de un archivo
 * de texto en vez de solo la primera linea del archivo.
 * En esta versión, los archivos a analizar deben se guardados en la carpeta
 * "filesToAnalize", al ingresar el nombre, se debe ingresar con la extensión
 * ".txt"
 * Para que el programa pueda ejecutar con toda su funcionalidad, el archivo
 * "back.bk" debe estar en la carpeta "src", aunque no pasa nada si el archivo
 * no se encuentra(simplemente no se muestra ninguna imagen al finalizar)*/

public class EstadisticasColgate {
	
	public static void main(String[] args) throws IOException{
		
		/*Se recibe el nombre del archivo a analizar*/
		String fileName = "datosColgate.txt";

		/*Se intenta abrir y operar al archivo*/
		try{
			
			/*Se instancian e inicializan las variables que serán utilizadas para leer al arvchivo*/
			File file = new File(""+fileName);
			FileReader fileReader= new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringTokenizer st;
			String cad="", entrada;
			
			/* personas: número de participantes registrados
			 * mayor: el número mayor de votos registrado
			 * votos: el número de votos temporal para cada persona
			 * encima: cantidad de personas por encuma de mis votos
			 * misVotos: este número lo entro manualmente en el código, es el número de votos que tengo
			 * totalEncima: la suma de la cantidad de votos totales de las personas que están por encima mío
			 * media: esta se define después de ejecutar el programa una vez y poder saber la media, para poder calcular la desviación estandar de una manera más sencilla
			 * desv: en un comienzo almacena la sumatoria de las desviaciones de cada dato al cuadrado, luego muestra la desviación media sqrt(s^2
			 * mayorABase: cuenta de personas que tienen una votación mayor a la indicada en la variable base
			 * base: ingresada en el código, indica el límite que quiero tener en cuenta para saber cuántas personas hay con una votación mayo a una determinada*/
			int personas=0, mayor=0, votos, encima=0, misVotos=4905, totalEncima=0, media=5218, desv=0, mayorABase=0, base = 0;
			
			/*Se lee la primera línea para poder analizar si el archivo está vacío y tener una condición inicial de la lectura para el ciclo while*/
			entrada = bufferedReader.readLine();
			
			/*Se leen las líneas del archivo, contándo cuantas hay*/
			while(entrada!=null){
				st = new StringTokenizer(entrada);
				while (st.hasMoreTokens()){
					cad = st.nextToken();
					if(cad.equals("Votar"))
						personas++;
					try{
						votos = Integer.parseInt(cad);
					}catch(NumberFormatException e){
						votos = 0;
					}
					if(votos > mayor)
						mayor = votos;
					if(votos > misVotos){
						System.out.println(votos);
						encima++;
						totalEncima += votos;
						desv += (media-votos)*(media-votos);
					}
					if (votos>base)
						mayorABase++;
				}
				entrada = bufferedReader.readLine();
			}
			
			bufferedReader.close();
			
			totalEncima /= encima;
			desv /= encima;
			
			desv = ((int)(Math.sqrt((double)(desv))));
			
			JLabel label = new JLabel("<html><hr>Personas "+personas+"<br>Mayor Votado "+mayor+"<br>Por encima mío "+encima+"<br>Media de los de encima "+totalEncima+"<br>Desviación "+desv+"<br>Mayor a "+base+" "+mayorABase+"<hr></html>");
			
			JOptionPane.showMessageDialog(null, label, "Datos", JOptionPane.INFORMATION_MESSAGE);
			
		}	
		/* En caso de que el nombre del archivo ingresado no sea encontrado, se muestra un mensaje en
		 * pantalla*/
		catch(FileNotFoundException e){
			JOptionPane.showMessageDialog(null, "Archivo no encontrado!", "Alerta!", JOptionPane.ERROR_MESSAGE);
		}
		
	}	
}
