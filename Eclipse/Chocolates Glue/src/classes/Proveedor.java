package classes;


import java.io.Serializable;
import java.util.HashMap;
/*
 * La clase provedor se encarga de manejar los datos
 * para un objeto de tipo Proveedor que surte los 
 * productos a chocolates glue S.A.
 */

public class Proveedor implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7083290316425929112L;
	private String nombre; // nombre del proveedor
	private HashMap<String,Productos> productosOfertados= //Arreglo de productos ofertador por el proveedor
			new HashMap<String,Productos>();
	private double nit; // identificación única del proveedor
	private int telefono; // teléfono del proveedor
	
	public Proveedor(String nombre, HashMap<String,Productos> productosOfertados,
			double nit, int telefono) {	
		this.nombre = nombre;
		this.productosOfertados = productosOfertados;
		this.nit = nit;
		this.telefono = telefono;
	}
	public Proveedor(String nombre,	double nit, int telefono) {	
		this.nombre = nombre;	
		this.nit = nit;
		this.telefono = telefono;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getNit() {
		return nit;
	}
	public void setNit(double nit) {
		this.nit = nit;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	/*
	 * toString() se encarga de imprimir los datos más relevantes
	 * de Proveedor 
	 */
	public String toString() {
		return "Provedor:\nNombre: " + nombre	+ "\nNIT: " + nit 
					+ "\nTelefono: " + telefono + "\nProductos Ofertados:\n"/*+ imprimeOferta()*/;
	}
	/*
	 * imprimerOferta, se encarga de imprimir los productos
	 * ofertados por el proveedor
	 */
	public String imprimeOferta(){
		String oferta = "";
		for(Productos i: productosOfertados.values()){
			oferta+= i.toString()+"\n";
		}
		return oferta;
	}
	/*
	 * el método modificar, se encarga de modificar los
	 * atributos más imporatantes de Proveedor
	 */
	public void modificar(String nombre,int telefono){
		this.setNombre(nombre);		
		this.setTelefono(telefono);
	}
}
