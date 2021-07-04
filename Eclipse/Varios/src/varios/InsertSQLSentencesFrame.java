package varios;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class InsertSQLSentencesFrame/* extends JFrame implements ActionListener*/{

	
	
	public static void main(String[] args) {
		
	    String nombreTabla, nombresColumnas[], datosTemp[];
	    
	    JTextArea textArea = new JTextArea();
	    JTextArea textAreaInto = new JTextArea();
	    
	    int i, j, n, m;// n para columnas y m para filas
	    
	    
	    nombreTabla = JOptionPane.showInputDialog(null, "Entre el nombre de su tabla:");
	    
	    n = Integer.parseInt(JOptionPane.showInputDialog(null, "Inserte la cantidad de columnas de su tabla:"));
	    
	    nombresColumnas = new String[n];
	    datosTemp = new String[n];
	    
	    for(i=0;i<n;i++){
	        nombresColumnas[i] = JOptionPane.showInputDialog(null, "Ingrese el nombre de su columna "+(i+1)+":");
	    }
	    
	    m = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese cuantas filas va a insertar:"));
	    
	    
	    for(j=0;j<m;j++){//moviéndose por las filas
	        for(i=0;i<n;i++){//moviéndose por las columnas
	            datosTemp[i] = JOptionPane.showInputDialog(null, (i+1)+". "+nombresColumnas[i]+":\nFila "+(j+1));
	        }
	        textArea.append("insert "+nombreTabla+" values (");
	        if(datosTemp[0].contains(" "))
	        	textArea.append('\"'+datosTemp[0]+'\"');
	        else
	        	textArea.append(datosTemp[0]);
	        for(i=1;i<n;i++){
	            textArea.append(", ");
	            if(datosTemp[i].contains(" "))
		        	textArea.append('\"'+datosTemp[i]+'\"');
		        else
		        	textArea.append(datosTemp[i]);
	        }
	        textArea.append(")\n");
	        
	        textAreaInto.append("insert into "+nombreTabla+" values (");
	        if(datosTemp[0].contains(" "))
	        	textAreaInto.append('\"'+datosTemp[0]+'\"');
	        else
	        	textAreaInto.append(datosTemp[0]);
	        for(i=1;i<n;i++){
	            textAreaInto.append(", ");
	            if(datosTemp[i].contains(" "))
		        	textAreaInto.append('\"'+datosTemp[i]+'\"');
		        else
		        	textAreaInto.append(datosTemp[i]);
	        }
	        textAreaInto.append(")\n");
	    }
	    
	    JScrollPane scrollPane = new JScrollPane(textArea);
	    JScrollPane scrollPaneInto = new JScrollPane(textAreaInto);
	    
	    JOptionPane.showMessageDialog(null, scrollPane, "Insert Sentences", JOptionPane.NO_OPTION);
	    JOptionPane.showMessageDialog(null, scrollPaneInto, "Insert Sentences Into", JOptionPane.NO_OPTION);

	}
	
	/*public InsertSQLSentencesFrame(){
		super("Sentencias de Insert para SQL");
	}
	
	
	public void actionPerformed(ActionEvent arg0) {
		
	}*/
}
