
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import classes.Factura;
import classes.FacturaCompra;
import classes.FacturaVenta;

@SuppressWarnings("serial")
public class GenerarRegistroGUI extends JFrame{
	private String[] nombreColumnas={"Código","Fecha","Cantidad de Productos",
			"Valor","Tipología","Cliente/Proveedor"}; // Arreglo con los campos de la tabla
	private Object[][] data; // Arreglo de dos dimensiones con los datos de las facturas en un cierto intervalo de tiempo
	private ArrayList<Factura> facturasRegistro;  // Arreglo con las facturas en un intervalo de tiempo 
	private JTable tablaDatos; // Tabla con las facturas en un intervalo de tiempo
	private JLabel totalIngresos; // etiqueta con el totalizado de los ingresos para un intervalo de tiempo
	private JLabel totalEgresos; // etiqueta con el totalizado de los egresos para un intervalo de tiempo
	private JLabel totalNeto; // Etiqueta con el total Neto de las facturas para un intervalo de tiempo
	private JSpinner diaInicio; //JSpinner con el día inicial del intervalo
	private JSpinner diaFinal; // JSpinner con el día final del intervalo
	private JSpinner mesInicio; //JSpinner con el mes inicial del intervalo
	private JSpinner mesFinal; // JSpinner con el mes final del intervalo
	private JSpinner anioInicio; // JSpinner con año inicial del intervalo
	private JSpinner anioFinal; // JSpinner con año final del intervalo
	private JPanel intervaloSetInicio; // Jpanel con los datos necesarios para generar el inicio del intervalo
	private JPanel intervaloSetFinal; // JPanel con los datos necesarios para generar el final del intervalo
	private JPanel botones; // JPanel con los botones confirmar y cancelar
	private JButton generarRegistro; // Botón para confirmar el generado del registro
	private JButton cancelar; // Botón para regresar al menú de administrador
	private JButton aceptar; // Botón para salir, luego de haber visto un registro
	private ButtonHandler handler; // Listener para los botones
	
	public GenerarRegistroGUI(){
		super("Generar Registro de Facturas");
		super.setLayout(new BorderLayout());
		totalIngresos=new JLabel();
		totalEgresos=new JLabel();
		totalNeto=new JLabel();
		facturasRegistro=new ArrayList<Factura>();
		intervaloSetInicio= new JPanel();
		intervaloSetInicio.setLayout(new FlowLayout());
		intervaloSetInicio.add(new JLabel("Fecha Inicial: "));
		intervaloSetFinal=new JPanel(new FlowLayout());	
		intervaloSetFinal.add(new JLabel("Fecha Final: "));
		botones=new JPanel(new FlowLayout());		
		generarRegistro= new JButton("Generar Registro");
		generarRegistro.setBackground(Color.green);
		cancelar=new JButton("Cancelar");		
		cancelar.setBackground(Color.red);
		aceptar=new JButton("Aceptar");
		aceptar.setBackground(Color.gray);
		handler=new ButtonHandler();
		generarRegistro.addActionListener(handler);
		cancelar.addActionListener(handler);
		aceptar.addActionListener(handler);		
		diaInicio=new JSpinner(new SpinnerNumberModel(1,1,31,1));
		diaFinal=new JSpinner(new SpinnerNumberModel(1,1,31,1));
		mesInicio=new JSpinner(new SpinnerNumberModel(1,1,12,1));
		mesFinal=new JSpinner(new SpinnerNumberModel(1,1,12,1));
		anioInicio=new JSpinner(new SpinnerNumberModel(2010,2010,2020,1));
		anioFinal=new JSpinner(new SpinnerNumberModel(2010,2010,2020,1));
		diaInicio.setSize(10,10);
		diaFinal.setSize(10,10);
		mesInicio.setSize(10,10);
		mesFinal.setSize(10,10);
		anioInicio.setSize(20,10);
		anioFinal.setSize(20,10);
		botones.add(generarRegistro);
		botones.add(cancelar);
		intervaloSetInicio.add(new JLabel("Día: "));
		intervaloSetInicio.add(diaInicio);
		intervaloSetFinal.add(new JLabel("Día: "));
		intervaloSetFinal.add(diaFinal);
		intervaloSetInicio.add(new JLabel("Mes: "));
		intervaloSetInicio.add(mesInicio);
		intervaloSetFinal.add(new JLabel("Mes: "));
		intervaloSetFinal.add(mesFinal);
		intervaloSetInicio.add(new JLabel("Año: "));
		intervaloSetInicio.add(anioInicio);
		intervaloSetFinal.add(new JLabel("Año:"));
		intervaloSetFinal.add(anioFinal);
		
		Container cont = this.getContentPane();
		cont.removeAll();
		
		cont.setLayout(new BorderLayout());
		
		cont.add(intervaloSetInicio, BorderLayout.WEST);
		cont.add(intervaloSetFinal, BorderLayout.EAST);
		cont.add(botones, BorderLayout.SOUTH);
		
		this.setContentPane(cont);
		this.setBounds(150, 150, 900, 100);
	}
	/*
	 * El método abrir de GenerarRegistroGUI, se encarga de
	 * inicializar una venta con la GUI para generar un registro
	 * de facturas en un intervalo determinado de tiempo
	 */
	public void abrir(){
		this.getContentPane().removeAll();
		add(intervaloSetInicio,BorderLayout.WEST);
		add(intervaloSetFinal,BorderLayout.EAST);
		add(botones,BorderLayout.SOUTH);
		facturasRegistro.clear();
		totalIngresos.setText("");
		totalEgresos.setText("");
		totalNeto.setText("");
		diaInicio.setValue(1);
		mesInicio.setValue(1);
		anioInicio.setValue(2010);
		diaFinal.setValue(1);
		mesFinal.setValue(1);
		anioFinal.setValue(2010);
		this.setBounds(150, 100, 730, 110);
		this.setVisible(true);
	}
	/*
	 * El método cerrar se encarga de cerrar esta ventana
	 */
	public void cerrar(){
		this.setVisible(false);
	}
	public class ButtonHandler implements ActionListener{
		
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(cancelar)){
				IngresarTest.frameGenerarRegistro.cerrar();
				IngresarTest.frameAdministrador.abrir();				
			}
			if(e.getSource().equals(generarRegistro)){
				Date fechaInicio= new Date((Integer.parseInt(anioInicio.getValue().toString())-1900),
						Integer.parseInt(mesInicio.getValue().toString()),
						Integer.parseInt(diaInicio.getValue().toString()),0, 00,01);// fecha inicial del intervalo
				Date fechaFinal=new Date((Integer.parseInt(anioFinal.getValue().toString())-1900),
						Integer.parseInt(mesFinal.getValue().toString()),
						Integer.parseInt(diaFinal.getValue().toString()),23,59,59);
				if(fechaFinal.before(fechaInicio)){
					JOptionPane.showMessageDialog(null,"Por favor ingrese un intervalo válido");
					
				}
				
				for(FacturaCompra facComp: IngresarTest.empresa.getRegistroContaduria().getRegistroCompras()){
						if(facComp.getDate().before(fechaFinal)&&
								facComp.getDate().after(fechaInicio)){
							facturasRegistro.add(facComp);
						}					
				}				
								
				for(FacturaVenta facVenta: IngresarTest.empresa.getRegistroContaduria().getRegistroVentas()){
						if(facVenta.getDate().after(fechaInicio)&&
								facVenta.getDate().before(fechaFinal)){
							facturasRegistro.add(facVenta);
						}
				}
				if(facturasRegistro.isEmpty()&&fechaFinal.after(fechaInicio)){
					JOptionPane.showMessageDialog(null,"No se ha encontrado ninguna factura en el intervalo considerado");
				}
				else if(fechaFinal.after(fechaInicio)){
				data=new Object[facturasRegistro.size()][6];
				for(int i=0;i<facturasRegistro.size();i++){
					data[i][0]=facturasRegistro.get(i).getCodigo();
					data[i][1]=facturasRegistro.get(i).getDate().toString();
					data[i][2]=facturasRegistro.get(i).getProductos().size();
					data[i][3]=facturasRegistro.get(i).getValorTotal();					
					if(facturasRegistro.get(i).getClass().toString().equals("class classes.FacturaCompra"))
						data[i][4]="Compra";
					else{
						data[i][4]="Venta";
					}
					data[i][5]=facturasRegistro.get(i).getProveedorOCliente();
				}
				tablaDatos=new JTable(data, nombreColumnas);
				setLayout(new FlowLayout());
				GenerarRegistroGUI.super.getContentPane().removeAll();
				GenerarRegistroGUI.super.setBounds(150,80,480,580);
				JScrollPane scrollPane=new JScrollPane(tablaDatos);
				GenerarRegistroGUI.super.add(scrollPane);	
				totalIngresos.setText("Total Ingresos: "+ IngresarTest.empresa
						.getRegistroContaduria().getIngresos(fechaInicio, fechaFinal)
						+"                                                               ");
				totalEgresos.setText("Total Egresos: "+ IngresarTest.empresa
						.getRegistroContaduria().getEgresos(fechaInicio, fechaFinal)
						+"                                                               ");
				totalIngresos.setForeground(Color.GREEN);
				totalEgresos.setForeground(Color.red);
				totalNeto.setText("                        Suma neta: "+ IngresarTest.empresa.getRegistroContaduria()
					.getSaldoTotal(fechaInicio, fechaFinal)+"                                                                             ");
				if(IngresarTest.empresa.getRegistroContaduria().getSaldoTotal(fechaInicio, fechaFinal)<0){
					totalNeto.setForeground(Color.red);
				}
				else{
					totalNeto.setForeground(Color.green);
				} 
				add(new JLabel("RESUMEN                                              "));
				add(totalIngresos);
				add(totalEgresos);
				add(totalNeto);
				add(aceptar);
				}
			}
			if(e.getSource().equals(aceptar)){
				int opcionElegida; // Respuesta del usuario
				opcionElegida=JOptionPane.showConfirmDialog(null,"Desea Generar otro registro?","",0);
				if(opcionElegida==0){
					IngresarTest.frameGenerarRegistro.abrir();
				}
				else{
					IngresarTest.frameGenerarRegistro.cerrar();
					IngresarTest.frameAdministrador.abrir();
					
				}
				
			}
		}
		
	}
}
	

