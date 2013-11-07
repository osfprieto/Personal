package classes;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/*
 * La clase Adminstrador se encarga de manejar todos los aspectos referentes a la nómina de la empresa*/
public class Administrador extends Empleados{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7735783247027783924L;
	private static int cantidadAdministradores;//constante que muestra cuantos administradores tiene la empresa. Mínimo debe de haber uno
	
	
	/*
	 * Los constructores de la clase Administrador solo difieren de los constructores de la clase
	 * Empleados en que se instancia al String que conservará la contraseña del Administrador*/
	public Administrador(){
		super();
	}
	public Administrador(String nombre, double cedulaCiudadania, double salario, double telefono, String correoElectronico, String fechaNacimiento, String contrasenia){
		super(nombre, cedulaCiudadania, salario, telefono, correoElectronico, fechaNacimiento, contrasenia);
	}
	
	/*
	 * El método setCantidadAdminstradores retorna true cuando el valor ingresado es mayor a 0
	 * Esto implica a que siempre deba de haber por lo menos uno de estos*/
	public static boolean setCantidadAdministradores(int cantidadAdministradores) {
		if(cantidadAdministradores > 0){
			Administrador.cantidadAdministradores = cantidadAdministradores;
			return true;
		}
		else{
			return false;
		}
	}
	public static int getCantidadAdministradores() {
		return cantidadAdministradores;
	}
	
	
	/*
	 * El método toString retorna los atributos de un Administrador determinado*/
	public String toString(){
		return ("Adminstrador;\n"+super.toString());
	}
	
	/*
	 * El método contratarEmpleado agrega un nuevo empleado a la base de datos del sistema*/
	public void contratarEmpleado(Empleados empleado, Empresa empresa){
		HashMap<Double, Empleados> empleados = empresa.getEmpleados();
		empleados.put(((Double)empleado.getCedulaCiudadania()), empleado);
		empresa.setEmpleados(empleados);
	}
	
	/*
	 * El método despedirEmpleado elimina a un determinado Empleado de la base de datos del sistema
	 * de la empresa, en caso de que un adiminstrador intente despedir a otro el método retorna valor
	 * falso y no lo despide ya que un Administrador no puede despedir a otro*/
	public boolean despedirEmpleado(Empleados empleado, Empresa empresa){
		boolean ret = false;
		HashMap<Double, Empleados> empleados = empresa.getEmpleados();
		empleados.get(empleado.getCedulaCiudadania());
		if(((Administrador)empleado).getContrasenia().equals(null)){/*revisa si la persona a la que se está
			intentando despedir tiene una contraseña asignada que en este caso solo la tienen los admins y
			en un dado caso que no tega, procede a despedirlo ya que un administrador no puede despedir a otro*/
			empleados.remove((Double)empleado.getCedulaCiudadania());
			ret = true;
		}
		empresa.setEmpleados(empleados);
		return ret;
	}
	/*
	 * El método pagar salario es llamado por el administrador de una empresa al momento de
	 * determinarse que es el momento en el que cada uno de los trabajadores cobre su sueldo*/
	public void pagarSalario(Empresa empresa){
		HashMap<Double, Empleados> empleados = empresa.getEmpleados();
		Set<Double> cedulas = empleados.keySet();
		for(Iterator<Double> iterador = cedulas.iterator();iterador.hasNext();){
			Double cedulaTemp = iterador.next();
			empleados.get(cedulaTemp).cobrarSalario(empresa);
		}
	}
	
	/*
	 * El método cambiarSalario modifica los valores de un Empleado determinado, retorna falso al no
	 * poderse llevar a cabo el proceso por salario propuesto menor al SSMLV perteneciente a la clase
	 * Empleados*/
	public boolean cambiarSalario(Empleados empleado, Empresa empresa, double salario){
		return empresa.getEmpleados().get(empleado.getCedulaCiudadania()).setSalario(salario);
	}
	
	/*
	 * El método agregarProveedor añade un nuevo proveedor al conjunto de proveedores de productos
	 * de la empresa*/
	public void agregarProveedor(Proveedor proveedor, Empresa empresa){
		HashMap<Double, Proveedor> proveedores = empresa.getProveedores();
		proveedores.put(((Double)proveedor.getNit()), proveedor);
		empresa.setProveedores(proveedores);
	}
	/*
	 * El método agregarProducto recibe un Productos como parámetro y lo agrega del otro parámetro al HashMap de Empresa*/
	public void agregarProducto(Empresa empresa, Productos producto){
		empresa.agregarProducto(producto);
	}
	
	/*
	 * El método modificar se encarga de cambiar todos los atributos de un objeto instanciado
	 * en la clase Administrador*/
	public void modificar(String nombre, double cedulaCiudadania, double salario, double telefono, String correoElectronico, String fechaNacimiento, String contrasenia){
		this.setNombre(nombre);
		this.setCedulaCiudadania(cedulaCiudadania);
		this.setSalario(salario);
		this.setTelefono(telefono);
		this.setCorreoElectronico(correoElectronico);
		this.setFechaNacimiento(fechaNacimiento);
		this.setContrasenia(contrasenia);
	}
}