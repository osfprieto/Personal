package classes;

import java.util.ArrayList;
import java.util.Date;

public class FacturaCompra extends Factura{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7689651357524348874L;
	private Proveedor proveedor; //nombre del proveedor
	private static double valorTotalCompras; //valor total de las compras registradas en la factura

	public FacturaCompra(Date fecha, ArrayList<Productos> factura,
			Proveedor proveedor) {
		super(fecha, factura);
		this.proveedor = proveedor;
	}

	public double getValorTotalCompras(){ //Este metodo retorna el total de las compras de una factura 
		valorTotalCompras=0;
		for (int i = 0; i < factura.size(); i++) {
			Productos temp = factura.get(i);
			valorTotalCompras=valorTotalCompras+temp.getValorCompra();
		}
		return valorTotalCompras;
	}

	public String printProductos(){  //Este metodo crea una lista imprimible de todos los articulos que aparecen en 
		//una factura de compra, su precio y el total de la factura

		System.out.println("Referencia                     Valor");
		for (int i = 0; i < factura.size(); i++) {
			Productos temp = factura.get(i);
			System.out.println(temp.getReferencia()+"                          "+temp.getValorCompra());
		}
		return "";
	}

	public String getProveedorOCliente() {		
		return this.proveedor.getNombre()+"\n"+this.proveedor.getNit();
	}
	public Proveedor getProveedor(){
		return this.proveedor;
	}
	
	public String toString(){  // imprime toda la informacion de una factura de compra
		return this.NOMBRE+"\nFactura de Compra"+"\nFecha: "+fecha+"\nProveedor: "+
		this.proveedor.getNombre()+"\nNIT: "+this.proveedor.getNit()+this.printProductos()+"\nTotal: "+
		this.getValorTotalCompras();
		/*String ret = this.NOMBRE;
		ret += "\nFactura de Compra: " ;
		ret += "\nFecha: ";
		ret += fecha.toString();
		ret += "\nProveedor: ";
		ret += this.nombreEmpresa;
		ret += this.printProductos();
		ret += "\nTotal: ";
		ret += this.getValorTotalCompras();
		return ret;*/
	}
}