package uno.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import playerFactory.ControladorJugadores;
import uno.model.UNOModelo;


public class PlayerSetupDialog extends JFrame implements ActionListener
{

	/**
	 * serialVersionUID
	 * long
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JCheckBox humano1Check = new JCheckBox("Si / No");
	JCheckBox humano2Check = new JCheckBox("Si / No");
	JCheckBox humano3Check = new JCheckBox("Si / No");
	JCheckBox humano4Check = new JCheckBox("Si / No");
	JComboBox automatico1Combo = new JComboBox();
	JComboBox automatico2Combo = new JComboBox();
	JComboBox automatico3Combo = new JComboBox();
	JComboBox automatico4Combo = new JComboBox();
	private ControladorJugadores controladorJugadores;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					PlayerSetupDialog frame = new PlayerSetupDialog(new ControladorJugadores());
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public PlayerSetupDialog(ControladorJugadores cj)
	{
		this.controladorJugadores= cj;
		cargarJugadoresAutomaticos();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 507, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[15.00][110.00,grow][73.00,grow][grow][][-66.00]", "[][][][][][34.00,bottom][][][][][][][][]"));
		
		JLabel lblConfiguracionDeJugadores = new JLabel("Configuracion de Jugadores");
		contentPane.add(lblConfiguracionDeJugadores, "cell 2 0");
		
		JLabel lblJugador_3 = new JLabel("Jugador");
		contentPane.add(lblJugador_3, "cell 1 3");
		
		JLabel lblHumano = new JLabel("Humano");
		contentPane.add(lblHumano, "cell 2 3,alignx center");
		
		JLabel lblAutomtico = new JLabel("Autom\u00E1tico");
		contentPane.add(lblAutomtico, "cell 3 3,alignx center");
		
		JLabel lblJugador = new JLabel("Jugador 1 (Verde)");
		contentPane.add(lblJugador, "cell 1 5,alignx left");
		
		
		
		
		contentPane.add(humano1Check, "cell 2 5,alignx center");
		
		//listeners
		humano1Check.addActionListener(this);
		
		//commands
		humano1Check.setActionCommand("Humano1");
		
		
		contentPane.add(automatico1Combo, "cell 3 5,growx");
		
		JLabel lblJugador_1 = new JLabel("Jugador 2 (Azul)");
		contentPane.add(lblJugador_1, "cell 1 6");
		
//		humano2Check = new JCheckBox("Si / No");
		
		contentPane.add(humano2Check, "cell 2 6,alignx center");
		humano2Check.addActionListener(this);
		humano2Check.setActionCommand("Humano2");
		
//		automatico2Combo = new JComboBox();
		contentPane.add(automatico2Combo, "cell 3 6,growx");
		
		JLabel lblJugador_2 = new JLabel("Jugador 3 (Rojo)");
		contentPane.add(lblJugador_2, "cell 1 7");
		
//		humano3Check = new JCheckBox("Si / No");
		contentPane.add(humano3Check, "cell 2 7,alignx center");
		humano3Check.addActionListener(this);
		humano3Check.setActionCommand("Humano3");
		
//		automatico3Combo = new JComboBox();
		contentPane.add(automatico3Combo, "cell 3 7,growx");
		
		JLabel lblNewLabel = new JLabel("Jugador 4 (Amarillo)");
		contentPane.add(lblNewLabel, "cell 1 8");
		
//		humano4Check = new JCheckBox("Si / No");
		
		contentPane.add(humano4Check, "cell 2 8,alignx center");
		humano4Check.addActionListener(this);
		humano4Check.setActionCommand("Humano4");
		
//		automatico4Combo = new JComboBox();
		contentPane.add(automatico4Combo, "cell 3 8,growx");
		
		JButton aceptarButton = new JButton("Aceptar");
		contentPane.add(aceptarButton, "flowx,cell 3 12,alignx right");
		aceptarButton.addActionListener(this);
		
		JButton cancelarButton = new JButton("Cancelar");
		cancelarButton.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(cancelarButton, "cell 3 12,alignx right");
		cancelarButton.addActionListener(this);
		
	}

	private void cargarJugadoresAutomaticos()
	{
//		this.controladorJugadores.cargarArchivo();
		this.controladorJugadores.cargarJugadoresAutomaticosConIntrospeccion();
		ArrayList<String> automaticos = this.controladorJugadores.darJugadoresAutomaticos();
		
		ComboBoxModel dlm1 = new DefaultComboBoxModel(automaticos.toArray());
		this.automatico1Combo.setModel(dlm1);
		ComboBoxModel dlm2 = new DefaultComboBoxModel(automaticos.toArray());
		this.automatico2Combo.setModel(dlm2);
		ComboBoxModel dlm3 = new DefaultComboBoxModel(automaticos.toArray());
		this.automatico3Combo.setModel(dlm3);
		ComboBoxModel dlm4 = new DefaultComboBoxModel(automaticos.toArray());
		this.automatico4Combo.setModel(dlm4);
		
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getActionCommand().equals("Humano1"))
		{
			if(humano1Check.isSelected())
			{
				automatico1Combo.setEnabled(false);
			}
			else
			{
				automatico1Combo.setEnabled(true);
			}
		}
		else if(ae.getActionCommand().equals("Humano2"))
		{
			if(humano2Check.isSelected())
			{
				automatico2Combo.setEnabled(false);
			}
			else
			{
				automatico2Combo.setEnabled(true);
			}
		}
		else if(ae.getActionCommand().equals("Humano3"))
		{
			if(humano3Check.isSelected())
			{
				automatico3Combo.setEnabled(false);
			}
			else
			{
				automatico3Combo.setEnabled(true);
			}
		}
		else if(ae.getActionCommand().equals("Humano4"))
		{
			if(humano4Check.isSelected())
			{
				automatico4Combo.setEnabled(false);
			}
			else
			{
				automatico4Combo.setEnabled(true);
			}
		}
		else if ( ae.getActionCommand().equals("Cancelar"))
		{
			this.setVisible(false);
		}
		else if ( ae.getActionCommand().equals("Aceptar"))
		{
			boolean humanos[] = new boolean[UNOModelo.NUMERO_JUGADORES];
			String automaticos[] = new String[UNOModelo.NUMERO_JUGADORES];
			if(humano1Check.isSelected())
			{
				humanos[0]=true;
			}
			else
			{
				automaticos[0]=(String)automatico1Combo.getSelectedItem();
			}
			
			if(humano2Check.isSelected())
			{
				humanos[1]=true;
			}
			else
			{
				automaticos[1]=(String)automatico2Combo.getSelectedItem();
			}
			if(humano3Check.isSelected())
			{
				humanos[2]= true;
			}
			else
			{
				automaticos[2]=(String)automatico3Combo.getSelectedItem();
			}
			if(humano4Check.isSelected())
			{
				humanos[3]=true;
			}
			else
			{
				automaticos[3]=(String)automatico4Combo.getSelectedItem();
			}
			
			//instrucciones para modificar la organizacion de los jugadores
			this.controladorJugadores.setup(humanos, automaticos);
			this.setVisible(false);
		}
	}

}
