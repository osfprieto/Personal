package poligran.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.miginfocom.swing.MigLayout;
import poligran.model.BuscaminasModelo;

public class BuscaminasGUI extends JFrame implements ActionListener, MouseListener
{
	/**
	 * serialVersionUID long
	 */
	private static final long serialVersionUID = 1L;
	private BuscaminasCellRenderer buscaminasRenderer = new BuscaminasCellRenderer();

	private BuscaminasModelo buscaminasModelo = new BuscaminasModelo();
	private boolean ganador = false;
	private boolean perdedor = false;
	private JTable tblTablero = new JTable();

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
					BuscaminasGUI window = new BuscaminasGUI();
					window.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BuscaminasGUI()
	{
		initComponents();
		this.buscaminasModelo = new BuscaminasModelo();
		refrescarVista();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initComponents()
	{
		tblTablero = new BuscaminasTable(this.buscaminasRenderer);

		this.tblTablero.setAlignmentX(CENTER_ALIGNMENT);
		this.tblTablero.setAlignmentY(CENTER_ALIGNMENT);
		setTitle("Buscaminas!");
		setBounds(100, 100, 351, 276);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("", "[50px][498.00][50px,grow]", "[-16.00][50][298.00][100px,grow]"));

		tblTablero = new JTable();
		getContentPane().add(tblTablero, "cell 1 2,alignx center,growy");
		tblTablero.setFont(new Font("Arial", Font.BOLD, 18));

		tblTablero.setRowSelectionAllowed(false);
		tblTablero.setModel(new DefaultTableModel(new Object[][] { { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "" }, }, new String[] { "", "", "", "", "", "", "", "", "", "" }));

		// Listeners
		tblTablero.addMouseListener(this);

		JButton btnNuevoJuego = new JButton("Nuevo Juego");
		getContentPane().add(btnNuevoJuego, "flowx,cell 1 3,alignx center");

		JButton btnRefrescar = new JButton("Refrescar");
		getContentPane().add(btnRefrescar, "cell 1 3");
		btnNuevoJuego.addActionListener(this);
		btnRefrescar.addActionListener(this);

	}

	public void refrescarVista()
	{
		int[][] tablero = this.buscaminasModelo.getTablero();
		boolean[][] mostrar = this.buscaminasModelo.getCasillas_abiertas();
		// llenar el jtable model
		TableModel tm = tblTablero.getModel();
		for (int i = 0; i < BuscaminasModelo.MAX_TABLERO; i++)
		{
			for (int j = 0; j < BuscaminasModelo.MAX_TABLERO; j++)
			{
				// this.tblTablero.prepareRenderer(tblTablero.getCellRenderer(i,
				// j), i, j);
				if (mostrar[i][j])
				{
					if (tablero[i][j] == BuscaminasModelo.MINA)
					{
						tm.setValueAt("\u263B", i, j);
					}
					else if (tablero[i][j] == BuscaminasModelo.VACIO)
					{
						tm.setValueAt(" ", i, j);
					}
					else
					{
						tm.setValueAt("" + tablero[i][j], i, j);
					}
				}
				else
				{
					tm.setValueAt("\u2592", i, j);
				}

			}
		}
		this.tblTablero.setModel(tm);

	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getActionCommand().equals("Refrescar"))
		{
			refrescarVista();
		}
		else
		{
			this.buscaminasModelo = new BuscaminasModelo();
			refrescarVista();
			this.ganador = false;
			this.perdedor = false;
		}

	}

	@Override
	public void mouseClicked(MouseEvent me)
	{
		if (!ganador && !perdedor)
		{
			JTable table = (JTable) me.getSource();
			int columna = table.getSelectedColumn();
			int fila = table.getSelectedRow();
			boolean boom = this.buscaminasModelo.jugar(fila, columna);
			refrescarVista();
			if (!boom)
			{
				javax.swing.JOptionPane.showMessageDialog(null, "Has pisado una mina! Perdiste");
				this.perdedor = true;
			}

			if (this.buscaminasModelo.validarGanador())
			{
				javax.swing.JOptionPane.showMessageDialog(null, "Ganaste!");
				this.ganador = true;
			}
		}
		else
		{
			javax.swing.JOptionPane.showMessageDialog(null, "Por favor inicia un nuevo juego");
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

}
