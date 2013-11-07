package classes;

import java.io.Serializable;


/*
 * la clase Cliente se encarga de manejar los datos
 * de un objeto de tipo Cliente
 */

public class Cliente implements  Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 230292930370947728L;
	private String nombre; //nombre del cliente
	private int telefono; // telefono del cliente
	private String correo; // correo del cliente
	private double c�dula;
	
	public Cliente(String nombre, int telefono, String correo, double c�dula) {
		this.nombre = nombre;
		this.telefono = telefono;
		this.correo = correo;
		this.c�dula = c�dula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public double getC�dula() {
		return c�dula;
	}
	/*
	 * toString(), se encarga de imprimir los datos de un
	 * objeto instanciado como Cliente 
	 */

	public String toString() {
		return "Cliente.\nNombre: " + nombre + "\nTelefono: " + telefono
				+ "\nCorreo Electr�nico: " + correo + "\nC�dula: " + ((Double)c�dula).longValue() + "";
	}
	/*
	 * El m�todo modificar se encarga de modificar todos los 
	 * datos a la vez de un objeto instanciado en la clase
	 * Cliente
	 */
	public void modificar(String nombre, int telefono, 
			String correo){
		this.setNombre(nombre);
		this.setTelefono(telefono);
		this.setCorreo(correo);		
	}
	
}
