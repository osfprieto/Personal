package classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public abstract class Factura implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -995405051170530429L;
	protected final String NOMBRE="CHOCOLATES GLUE S.A."; //nombre de la empresa
	protected Date fecha; //fecha de la factura
	protected String codigo;
	protected double valorTotal;
	protected ArrayList<Productos> factura = new ArrayList<Productos>(); //factura modelada como una lista de productos

	public Factura(Date fecha, ArrayList<Productos> factura) {
		this.fecha = fecha;
		this.factura = factura;
	}
	
	public Date getDate(){
		return this.fecha;
	}

	public String getCodigo(){
		return codigo;
	}
	
	public ArrayList<Productos> getProductos(){
		return factura;
	}
	
	public double getValorTotal(){		 
		valorTotal=0;
		for (int i = 0; i < factura.size(); i++) {
			Productos temp = factura.get(i);
			valorTotal=valorTotal+temp.getValorCompra();
		}
		return valorTotal;
}
	
	public abstract String printProductos();
	public abstract String toString();
	public abstract String getProveedorOCliente();
}