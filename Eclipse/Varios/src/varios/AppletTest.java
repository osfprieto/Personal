package varios;

import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class AppletTest extends JApplet implements ActionListener{
	
		
		
		private long actualTime;
		private int horaActual;
		private int minutoActual;
		private JLabel labelHora;
		private JLabel labelMinuto;
		private JLabel labelUtc;
		private JTextField horaTextField;
		private JTextField minutoTextField;
		private JTextField utcTextField;
		private JPanel setupPanel;
		private int horaNum;
		private int minutoNum;
		private int utcNum;
		private JLabel labelRingTime;
		private JLabel labelActualTime;
		
		
		public void init(){
			
			labelHora = new JLabel("Hora");
			labelMinuto = new JLabel("Minutos:");
			labelUtc = new JLabel("UTC:");
			horaTextField = new JTextField();
			minutoTextField = new JTextField();
			utcTextField = new JTextField();
			setupPanel = new JPanel();
			setupPanel.removeAll();
			setupPanel.setLayout(new GridLayout(3, 2));
			setupPanel.add(labelHora);
			setupPanel.add(horaTextField);
			setupPanel.add(labelMinuto);
			setupPanel.add(minutoTextField);
			setupPanel.add(labelUtc);
			setupPanel.add(utcTextField);
			horaNum = minutoNum = -1;
			utcNum = 13;
			while(horaNum<0 || horaNum>23 || minutoNum<0 || minutoNum>59 || utcNum<-12 || utcNum>12){
				horaTextField.setText("");
				minutoTextField.setText("");
				utcTextField.setText("");
				JOptionPane.showMessageDialog(null, setupPanel, "Entra la hora de la alarma", JOptionPane.QUESTION_MESSAGE);
				try{
					horaNum = Integer.parseInt(horaTextField.getText());
					minutoNum = Integer.parseInt(minutoTextField.getText());
					utcNum = Integer.parseInt(utcTextField.getText());
					if(horaNum<0 || horaNum>23 || minutoNum<0 || minutoNum>59 || utcNum<-12 || utcNum>12){
						throw new NumberFormatException();
					}
				}
				catch(NumberFormatException e){
					JOptionPane.showMessageDialog(null, "Error tomando datos", "Error", JOptionPane.ERROR_MESSAGE);
					horaNum = minutoNum = -1;
					utcNum = 13;
				}
			}
			
			actualTime = System.currentTimeMillis();
			horaActual =(int) (actualTime%(1000*60*60*24))/(1000*60*60);
			horaActual -= utcNum;
			minutoActual = (int) (actualTime%(1000*60*60))/(1000*60);
			//JOptionPane.showMessageDialog(null, horaActual+" : "+minutoActual+"\n"+horaNum+" : "+minutoNum+", "+utcNum, "actualTime", JOptionPane.INFORMATION_MESSAGE);
			labelRingTime = new JLabel();
			labelRingTime.setText(((horaNum/10==0)?"0":"")+horaNum+" : "+((minutoNum/10==0)?"0":"")+minutoNum);
			labelActualTime = new JLabel();
			labelActualTime.setText(((horaActual/10==0)?"0":"")+horaActual+" : "+((minutoActual/10==0)?"0":"")+minutoActual);
			JPanel panel = new JPanel();
			panel.removeAll();
			panel.setLayout(new GridLayout(3, 1));
			panel.add(labelRingTime);
			panel.add(labelActualTime);
			panel.add(new JLabel("Utc: "+((utcNum>=0)?"+":"")+utcNum));
			JOptionPane.showMessageDialog(null, panel, "Tiempo", JOptionPane.PLAIN_MESSAGE);
		}
		
		public void actionPerformed(ActionEvent e) {
			
		}
}
