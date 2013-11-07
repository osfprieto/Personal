package classes;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
/*
 * la clase empresa, se encarga de manejar los datos 
 * para la empresa de nombre CHOCOLATES GLUE S.A.
 */
public class Empresa implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8157012412443576602L;
	private final String NOMBRE="CHOCOLATES GLUE S.A."; //nombre de la empresa
	private HashMap<Double,Cliente> clientes= // diccionario con los clientes de la empresa
			new HashMap<Double,Cliente>(); 
	private HashMap<Double,Empleados> empleados= //diccionario con los empleados de la empresa
			new HashMap<Double,Empleados>();
	private HashMap<Double,Proveedor> proveedores= // diccionario con los proveedores de la empresa
			new HashMap<Double,Proveedor>();
	private double nit; // identificiación única de la empresa
	private HashMap<String,Productos> productoStock= // diccionario con los productos en bodega de la empresa
			new HashMap<String,Productos>();
	private Administrador administrador; // Administrador de la empresa
	private double recursos;
	private RegistroContaduria registroContaduria;//Regostro de las ventas y compras de la empresa
	
	public Empresa(){
		/*//Administrador admin=new Administrador("Pepito Perez",52227048,1000000, 4501670,"weje@hotmail.com", null, "pep123");
		Comprador comp1=new Comprador();
		Vendedor vend1=new Vendedor("Gabriel Prieto",1,560000 ,4789563,"agellgab@hotmail.com", null, "123");
		//this.administrador=admin;
		Cliente cliente = new Cliente("Omar Prieto", 6149436, "osfprieto@hotmail.com", 1032448947);
		clientes.put(cliente.getCédula(), cliente);
		empleados.put(vend1.getCedulaCiudadania(), vend1);
		empleados.put(comp1.getCedulaCiudadania(), comp1);
		Productos producto = new Productos();
		this.agregarProducto(producto);
		Proveedor proveedor = new Proveedor("Proveedor default", 987654321, 2763400);
		proveedores.put(proveedor.getNit(), proveedor);*/
		recursos = 1000000;
		registroContaduria = new RegistroContaduria(new Date(System.currentTimeMillis()), new Date(Long.MAX_VALUE));
	}
	
	public HashMap<Double, Cliente> getClientes() {
		return clientes;
	}
	
	public double getNit() {
		return nit;
	}
	public void setNit(double nit) {
		this.nit = nit;
	}
	public HashMap<Double, Empleados> getEmpleados(){
		return this.empleados;
	}
	public void setEmpleados(HashMap<Double, Empleados> empleados){
		this.empleados = empleados;
	}
	public Administrador getAdministrador() {
		return administrador;
	}
	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}
	public String getNOMBRE() {
		return NOMBRE;
	}
	public HashMap<Double, Proveedor> getProveedores(){
		return this.proveedores;
	}
	public void setProveedores(HashMap<Double, Proveedor> proveedores){
		this.proveedores = proveedores;
	}
	public void setRecursos(double recursos) {
		this.recursos = recursos;
	}
	public double getRecursos() {
		return recursos;
	}
	/*
	 * toString() se encarga de imprimir los principales
	 * atributos de la empresa CHOCOLATES GLUE S.A. 
	 */
	public String toString() {
		return "Empresa [NOMBRE=" + NOMBRE + ", nit=" + nit
				+ ", administrador=" + administrador + ", recursos=" + recursos
				+ "]";
	}
	/*
	 * el método getCliente, se encarga de buscar un cliente
	 * por su cédula en el diccionario e imprimir sus 
	 * características más importantes
	 */
	public String getCliente(double cedula){
		return clientes.get((Double)cedula).toString();		
	}
	
	public Cliente getObjetoCliente(Double cedula){
		return clientes.get(cedula);
	}
	
	public void agregarProducto(Productos producto){
		this.productoStock.put(producto.getReferencia(), producto);
	}
	
	/*
	 * el método getEmpleado, se encarga de buscar un empleado
	 * por su cédula en el diccionario e imprimir sus 
	 * características más importantes
	 */
	public String getEmpleado(double cedula){
		return empleados.get((Double) cedula).toString();
	}
	public Empleados getObjetoEmpleado(double cedula){
		return empleados.get((Double)cedula);
	}
	/*
	 * el método getProveedor, se encarga de buscar un proveedor
	 * por su nit en el diccionario e imprimir sus 
	 * características más importantes
	 */
	public String getProveedor(double nit){
		return proveedores.get((Double)nit).toString();
	}
	public Proveedor getObjetoProveedor(double nit){
		return proveedores.get((Double)nit);
	}
	/*
	 * El método getProducto se encarga de retornar
	 * la referencia a un producto determinado dentro
	 * del productoStock de la empresa*/
	public Productos getProducto(String referencia){
		return this.productoStock.get(referencia);
	}
	
	public HashMap<String, Productos> getProductos(){
		return this.productoStock;
	}
	/*
	 * el método getDatosProducto, se encarga de buscar un producto
	 * por su referencia en el diccionario e imprimir sus 
	 * características más importantes
	 */
	public String getDatosProducto(String referencia){
		return this.productoStock.get(referencia).toString();
	}
	/*
	 * el método setCliente, se encarga de buscar en el 
	 * diccionario un cliente por su cédula y modificar
	 * sus atributos más relevantes
	 */
	public void setCliente(double cedula,String nombre, 
				int telefono, String correo){
		clientes.get((Double)cedula).modificar(nombre, telefono, 
					correo);
	}
	/*
	 * el método setEmpleado, se encarga de buscar en el 
	 * diccionario un empleado por su cédula y modificar
	 * sus atributos más relevantes, los campos de nombre,
	 * cédula y fecha de nacimiento no son modificables
	 */
	public void setEmpleado(double cedula, double salario, double telefono, String correoElectronico){
		Empleados empleado = empleados.get((Double)cedula);
		empleado.setCorreoElectronico(correoElectronico);
		empleado.setTelefono(telefono);
		empleado.setSalario(salario);
	}
	/*
	 * el método setProveedor, se encarga de buscar en el 
	 * diccionario un proveedor por su cédula y modificar
	 * sus atributos más relevantes
	 */
	public void setProveedor(double nit,String nombre,
				int telefono){
		proveedores.get((Double)nit).modificar(nombre, telefono);
	}
	/*
	 * el método setProductos, se encarga de buscar en el
	 * diccionario un producto por su referencia y modificar
	 * sus atributos más relevantes
	 */
	public void setProducto(String referencia, int cantidadStock, 
			double valorVenta, double valorCompra){
		productoStock.get(referencia).modificar(cantidadStock, valorVenta, 
					valorCompra);
	}

	public void setRegistroContaduria(RegistroContaduria registroContaduria) {
		this.registroContaduria = registroContaduria;
	}

	public RegistroContaduria getRegistroContaduria() {
		return registroContaduria;
	}

	public void setProductos(HashMap<String, Productos> put) {
		this.productoStock=put;	
	}
}
