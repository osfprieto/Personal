package classes;

import java.util.ArrayList;
import java.util.Date;

public class FacturaVenta extends Factura{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7474124266555510899L;
	private Cliente cliente; //nombre comprador
	private static double valorTotalVentas; //valor total de las ventas registradas en la factura

	public FacturaVenta(Date fecha, ArrayList<Productos> factura,
			Cliente cliente) {
		super(fecha, factura);
		this.cliente = cliente;
	}

	public double getValorTotalVentas(){ //Este metodo retorna el total de las ventas de una factura 
		valorTotalVentas=0;
		for (int i = 0; i < factura.size(); i++) {
			Productos temp = factura.get(i);
			valorTotalVentas=valorTotalVentas+temp.getValorVenta()*temp.getCantidadStock();
		}
		return valorTotalVentas;
	}

	public String printProductos(){  //Este metodo crea una lista imprimible de todos los articulos que aparecen en 
		//una factura de venta, su precio y el total de la factura

		String ret = "Referencia                     Valor                     Cantidad\n";
		for (int i = 0; i < factura.size(); i++) {
			Productos temp = factura.get(i);
			ret += temp.getReferencia()+"                          "+temp.getValorVenta()*temp.getCantidadStock()+
			"                "+temp.getCantidadStock()+"\n";
		}
		return ret;
	}

	public String getProveedorOCliente() {
		return this.cliente.getNombre()+"\n"+this.cliente.getCédula();
	}
	
	public Cliente getCliente(){
		return this.cliente;
	}
	
	public String toString(){ // imprime toda la informacion de una factura de venta
		return this.NOMBRE+"\nFactura de Venta"+"\nFecha: "+fecha+"\nCliente: "+
		this.cliente.getNombre()+"\n"+this.cliente.getCédula()+"\n"+this.printProductos()+"\nTotal: "+
		this.getValorTotalVentas();
	}
}
