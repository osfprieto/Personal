package classes;

import java.io.Serializable;

/*
 * la clase Productos, se encarga de manejar los datos
 * de un objeto instanciado como Productos.
 */
public class Productos implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7889683022288843726L;
	private String referencia; // referencia del producto
	private String nombre;
	private int cantidadStock; // Existencia del producto
	private double valorVenta; // valor al que se vende el producto
	private double valorCompra; // valor al que se compra el producto
	public String getReferencia() {
		return referencia;
	}
	
	public Productos(){
		this.referencia = "1A4888-10Q";
		this.setNombre("Choco-Choco");
		this.cantidadStock = 10;
		this.valorVenta = 154;
		this.valorCompra = 102;
	}
	
	public Productos(String referencia, String nombre, int cantidadStock, double valorVenta,
			double valorCompra) {
	
		this.referencia = referencia;
		this.setNombre(nombre);
		this.cantidadStock = cantidadStock;
		this.valorVenta = valorVenta;
		this.valorCompra = valorCompra;
	}


	public int getCantidadStock() {
		return cantidadStock;
	}
	public void setCantidadStock(int cantidadStock) {
		this.cantidadStock = cantidadStock;
	}
	public double getValorVenta() {
		return valorVenta;
	}
	public void setValorVenta(double valorVenta) {
		this.valorVenta = valorVenta;
	}
	public double getValorCompra() {
		return valorCompra;
	}
	public void setValorCompra(double valorCompra) {
		this.valorCompra = valorCompra;
	}
	/*
	 * toString() se encarga de imprimir los datos de un objeto
	 * instanciado en la clase Producto 
	 */
	public String toString() {
		return "Producto:\nReferencia: " + referencia + "\nCantidad en Stock: "
				+ cantidadStock + "\nValor de Venta=" + valorVenta
				+ "\nValor de Compra=" + valorCompra;
	}
	/*
	 * el método modificar, se encarga de modificar
	 * los atributos más relevantes de Productos
	 */
	public void modificar(int cantidadStock, double valorVenta,
			double valorCompra){
		this.setCantidadStock(cantidadStock);
		this.setValorVenta(valorVenta);
		this.setValorCompra(valorCompra);
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

		
}
