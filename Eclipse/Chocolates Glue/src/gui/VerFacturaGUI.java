package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import classes.FacturaCompra;
import classes.FacturaVenta;
import classes.Productos;

public class VerFacturaGUI extends JFrame{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7598700455410088167L;
	private JTextField codigo; // Campo de texto para ingresar el código de la factura
	private JButton generarFactura; // Botón que genera la factura, a partir de un código ingresado
	private JButton aceptar; // Botón para salir, luego de haber visto una factura
	private JButton cancelar; // Botón para devolverse a la AdministradorGUI, desde VerFacturaGUI
	private JTable tablaProductos; // Tabla de datos que contiene los productos de la factura
	private String[] campos={"Referencia","Descripción","Valor Venta","Valor Compra",
			"Cantidad en Stock"};	
	private Object[][] dataProductos; // Datos de los productos de la Factura
	private ArrayList<Productos> productosFactura; // Arreglo con los productos que contiene la Factura
	private FacturaVenta facturaVenta; // Factura de Venta encontrada
	private FacturaCompra facturaCompra; // Factura de Compra encontrada
	private JLabel etiquetaValorTotal; // Etiqueta con el totalizado de la factura
	private JLabel eitquetaCodigo; // Etiqueta que acompaña al campo de texto código
	private ButtonHandler handler; // Listener para los botones
	private JLabel etiquetaCliente; // etiqueta para el Cliente
	private JLabel etiquetaProveedor; // etiqueta para el Proveedor
	private JLabel etiquetaCedula; // etiqueta para la cédula del Cliente
	private JLabel etiquetaNIT; // eitqueta para el NIT del proveedor
	
	VerFacturaGUI(){
		super("Ver Factura");
		setLayout(new FlowLayout());
		productosFactura=new ArrayList<Productos>();
		eitquetaCodigo=new JLabel("Ingrese el código de la factura: ");
		cancelar=new JButton("Cancelar");
		cancelar.setBackground(Color.red);
		codigo=new JTextField();
		codigo.setColumns(12);
		handler=new ButtonHandler();
		generarFactura=new JButton("Generar Factura");
		generarFactura.setBackground(Color.green);
		generarFactura.addActionListener(handler);
		cancelar.addActionListener(handler);
		aceptar=new JButton("Aceptar");
		aceptar.setBackground(Color.gray);	
		aceptar.addActionListener(handler);
		etiquetaValorTotal=new JLabel();
		etiquetaCliente= new JLabel();
		etiquetaProveedor= new JLabel();
		etiquetaCedula= new JLabel();
		etiquetaNIT= new JLabel();
	}
	public void abrir(){
		this.setVisible(true);
		this.setBounds(100,150,250,150);
		super.getContentPane().removeAll();
		this.codigo.setText("");
		add(eitquetaCodigo);
		add(codigo);
		add(generarFactura);
		add(cancelar);
		productosFactura.clear();		
		etiquetaNIT.setText("");
		etiquetaCliente.setText("");
		etiquetaProveedor.setText("");
		etiquetaNIT.setText("");
	}
	public void cerrar(){
		this.setVisible(false);
	}
	public class ButtonHandler implements ActionListener{
		private boolean existeFactura=false; // Flag para reconocer la existencia de la factura
		private boolean esDeCompra=false; // Flag para reconocer si la factura es de Compra
		private double valorTotal; //Variable que contiene el valorTotal de la compra/venta
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(generarFactura)){
				
				if(!codigo.getText().isEmpty()){
					
				for(FacturaCompra facCompra: IngresarTest.empresa.getRegistroContaduria()
							.getRegistroCompras()){
						if(facCompra.getCodigo().equals(codigo.getText())){
							existeFactura=true;
							
							facturaCompra=facCompra;
							productosFactura.addAll(facCompra.getProductos());
							esDeCompra=true;
						}
				}				
				for(FacturaVenta facVenta: IngresarTest.empresa.getRegistroContaduria()
							.getRegistroVentas()){
						if(facVenta.getCodigo().equals(codigo.getText())){
							existeFactura=true;
							facturaVenta=facVenta;
							productosFactura.addAll(facVenta.getProductos());
						}
				}				
				if(!existeFactura){
					JOptionPane.showMessageDialog(null,"No se ha encontrado una factura con ese código");					
				}
				else{
					
					VerFacturaGUI.super.getContentPane().removeAll();
					if(esDeCompra){
						
						etiquetaProveedor.setText("Proveedor: "+ facturaCompra.getProveedorOCliente());
						etiquetaNIT.setText("                                                    " +
								"NIT: "+ facturaCompra.getProveedor().getNit());
						dataProductos=new Object[productosFactura.size()][5];
						for(int i=0;i<productosFactura.size();i++){
							dataProductos[i][0]=productosFactura.get(i).getReferencia();
							dataProductos[i][1]=productosFactura.get(i).getNombre();
							dataProductos[i][2]=productosFactura.get(i).getValorVenta();
							dataProductos[i][3]=productosFactura.get(i).getValorCompra();
							dataProductos[i][4]=productosFactura.get(i).getCantidadStock();
						}
						tablaProductos=new JTable(dataProductos,campos);
						JScrollPane pane=new JScrollPane(tablaProductos);
						add(etiquetaProveedor);
						add(etiquetaNIT);
						add(pane);
						for(Productos pro: productosFactura){
							valorTotal+=pro.getValorCompra();
						}
						etiquetaValorTotal.setForeground(Color.red);
						etiquetaValorTotal.setText("Valor Total: "+ valorTotal+
								"                                              ");
						
						add(etiquetaValorTotal);
						add(aceptar);
						VerFacturaGUI.super.setBounds(100, 150, 500, 520);
					}
					else{
						etiquetaCliente.setText("Cliente: "+facturaVenta.getCliente().getNombre());
						etiquetaCedula.setText("                                                  " +
								"Cédula: "+facturaVenta.getCliente().getCédula());
						dataProductos=new Object[productosFactura.size()][5];
						for(int i=0;i<productosFactura.size();i++){
							dataProductos[i][0]=productosFactura.get(i).getReferencia();
							dataProductos[i][1]=productosFactura.get(i).getNombre();
							dataProductos[i][2]=productosFactura.get(i).getValorVenta();
							dataProductos[i][3]=productosFactura.get(i).getValorCompra();
							dataProductos[i][4]=productosFactura.get(i).getCantidadStock();
						}
						tablaProductos=new JTable(dataProductos,campos);
						JScrollPane pane=new JScrollPane(tablaProductos);
						add(etiquetaCliente);
						add(etiquetaCedula);
						add(pane);
						for(Productos pro: productosFactura){
							valorTotal+=pro.getValorCompra();
						}
						etiquetaValorTotal.setForeground(Color.green);
						etiquetaValorTotal.setText("Valor Total: "+ valorTotal+
								"                                              ");
						
						add(etiquetaValorTotal);
						add(aceptar);
						VerFacturaGUI.super.setBounds(100, 150, 500,520);
					}
				}				
			}
			else{
				JOptionPane.showMessageDialog(null, "Por favor llene el campo código");
			}
			}
			if(e.getSource().equals(cancelar)){
				IngresarTest.frameVerFactura.cerrar();
				IngresarTest.frameAdministrador.abrir();
			}
			if(e.getSource().equals(aceptar)){
				int opcionUsuario; // variable Local para la opción elegida por el usuario
				opcionUsuario=JOptionPane.showConfirmDialog(null,"Desea Ver otra Factura","",0);
				if(opcionUsuario==0){
					IngresarTest.frameVerFactura.abrir();					
				}
				else{
					IngresarTest.frameVerFactura.cerrar();
					IngresarTest.frameAdministrador.abrir();
				}
			}
		}
		
	}
}
