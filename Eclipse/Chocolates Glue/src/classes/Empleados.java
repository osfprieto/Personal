package classes;

import java.io.Serializable;
import java.util.Date;

/*
 * La clase Staff se encarga de generalizar los distintos aspectos, comportamientos y
 * atributos de los que dispone un integrante de un grupo de trabajo en una empresa,
 * ya sea un administrador o un empleado (comprador, administrador o contador)*/
public abstract class Empleados implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3735981524242921743L;

	public static final double SMMLV = 520000;//variable estática constante que indica el Salario Mínimo Mensual Legal Vigente
	
	private String nombre;//nombre del empleado
	private double cedulaCiudadania;//cédula de cuidadanía del empleda determinado
	private double salario;//salrio que gana cada empleado la empresa
	private double telefono;//número telefónico un empleado de la empresa
	private String correoElectronico;//dirección de correo electrónico de un empleado de la empresa
	private String fechaNacimiento;//fecha de nacimiento de un empleado de una empresa
	private String contrasenia;
	
	/*
	 * Constructor default de un objeto de la clase Empleados*/
	public Empleados(){
		this.setNombre("John Doe");
		this.setCedulaCiudadania(123456789);
		this.setSalario(Empleados.SMMLV);
		this.setTelefono(1000000);
		this.setCorreoElectronico("correo@default.oop");
		this.setFechaNacimiento(new Date(System.currentTimeMillis()).toString());
		this.setContrasenia("123456");
	}
	
	/*Constructor de un objeto de la clase empleados que recibe los parámetros de todos
	 * los atributos de un empleado*/
	public Empleados(String nombre, double cedulaCiudadania, double salario, double telefono, String correoElectronico, String fechaNacimiento, String contrasenia){
		this.setNombre(nombre);
		this.setCedulaCiudadania(cedulaCiudadania);
		this.setSalario(salario);
		this.setTelefono(telefono);
		this.setCorreoElectronico(correoElectronico);
		this.setFechaNacimiento(fechaNacimiento);
		this.setContrasenia(contrasenia);
	}
	
	public void modificar(String nombre, double salario, double telefono,
			String correo){
		this.setNombre(nombre);
		this.setSalario(salario);
		this.setTelefono(telefono);
		this.setCorreoElectronico(correo);
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	/*
	 * El método setCedulaCiudadanía modifica el número de cédula de ciudadanía de un empleado.
	 * Este método retorna valores booleanos con el fin evitar errores al momento de modificar algún
	 * valor, cuando se retorna true, significa que el método no ha tenido errores, de resto se retorna
	 * false, en este caso, solo en el momento en el que el número ingresado es negativo*/
	public boolean setCedulaCiudadania(double cedulaCiudadania) {
		if(cedulaCiudadania > 0){
			this.cedulaCiudadania = cedulaCiudadania;
			return true;
		}
		else{
			return false;
		}
	}

	public double getCedulaCiudadania() {
		return cedulaCiudadania;
	}
	
	/*
	 * El método setSalario modifica el salario de un empleado dado.
	 * Este método usa valores booleanos de retorno para identficar los casos en los que
	 * los datos de ingreso al método no son válidos, en este caso, retorna false al momento
	 * que el salario propuesto es menor al Salario Mínimo Legal Vigente, de otra manera, asigna el
	 * salario nuevo y retorna true.*/
	public boolean setSalario(double salario) {
		if(salario >= Empleados.SMMLV){
			this.salario = salario;
			return true;
		}
		else{
			this.salario = Empleados.SMMLV;
			return false;
		}
	}

	public double getSalario() {
		return salario;
	}

	/*
	 * El método setTelefono asigna un número de teléfono a un empleado dado.
	 * Este método retorna false en caso de que el número ingresado no tenga siete
	 * dígitos, ya que un número de teléfono fijo consta de siete dígitos, en cambio,
	 * si el número consta de siete dígitos, asígna el nuevo número y retorna true.*/
	public boolean setTelefono(double telefono) {
		if(telefono>1000000 && telefono<9999999){
			this.telefono = telefono;
			return true;
		}
		else{
			this.telefono = 1000000;
			return false;
		}
	}
	public double getTelefono() {
		return telefono;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	/*
	 * El método toString se encarga de mostrar los atributos escenciales de un Empleado como el nombre,
	 * la cédula, el salario y su correo electróninco */
	public String toString(){
		return ("Nombre: "+this.getNombre()+"\nCédula de Cuidadanía: "+
				((Double)this.getCedulaCiudadania()).longValue()+"\nTeléfono: "+
				this.getTelefono()+"\nSalario: "+this.getSalario()+"\nCorreo electrónico: "+
				this.getCorreoElectronico());
	}
	
	
	/*
	 * método que toma dinero de los recursos de una
	 * empresa para dárselos como salario a un empleado
	 */
	public void cobrarSalario(Empresa empresa){
		empresa.setRecursos(empresa.getRecursos()-this.getSalario());
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getContrasenia() {
		return contrasenia;
	}
}
