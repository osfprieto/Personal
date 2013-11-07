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

	public static final double SMMLV = 520000;//variable est�tica constante que indica el Salario M�nimo Mensual Legal Vigente
	
	private String nombre;//nombre del empleado
	private double cedulaCiudadania;//c�dula de cuidadan�a del empleda determinado
	private double salario;//salrio que gana cada empleado la empresa
	private double telefono;//n�mero telef�nico un empleado de la empresa
	private String correoElectronico;//direcci�n de correo electr�nico de un empleado de la empresa
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
	
	/*Constructor de un objeto de la clase empleados que recibe los par�metros de todos
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
	 * El m�todo setCedulaCiudadan�a modifica el n�mero de c�dula de ciudadan�a de un empleado.
	 * Este m�todo retorna valores booleanos con el fin evitar errores al momento de modificar alg�n
	 * valor, cuando se retorna true, significa que el m�todo no ha tenido errores, de resto se retorna
	 * false, en este caso, solo en el momento en el que el n�mero ingresado es negativo*/
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
	 * El m�todo setSalario modifica el salario de un empleado dado.
	 * Este m�todo usa valores booleanos de retorno para identficar los casos en los que
	 * los datos de ingreso al m�todo no son v�lidos, en este caso, retorna false al momento
	 * que el salario propuesto es menor al Salario M�nimo Legal Vigente, de otra manera, asigna el
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
	 * El m�todo setTelefono asigna un n�mero de tel�fono a un empleado dado.
	 * Este m�todo retorna false en caso de que el n�mero ingresado no tenga siete
	 * d�gitos, ya que un n�mero de tel�fono fijo consta de siete d�gitos, en cambio,
	 * si el n�mero consta de siete d�gitos, as�gna el nuevo n�mero y retorna true.*/
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
	 * El m�todo toString se encarga de mostrar los atributos escenciales de un Empleado como el nombre,
	 * la c�dula, el salario y su correo electr�ninco */
	public String toString(){
		return ("Nombre: "+this.getNombre()+"\nC�dula de Cuidadan�a: "+
				((Double)this.getCedulaCiudadania()).longValue()+"\nTel�fono: "+
				this.getTelefono()+"\nSalario: "+this.getSalario()+"\nCorreo electr�nico: "+
				this.getCorreoElectronico());
	}
	
	
	/*
	 * m�todo que toma dinero de los recursos de una
	 * empresa para d�rselos como salario a un empleado
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
