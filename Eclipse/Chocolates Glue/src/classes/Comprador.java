package classes;

import gui.IngresarTest;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

/*
 * La clase Comprador es la clase que se encarga de comprar los productos ofrecidos por la empresa
 * a los Proveedores, por lo que es esta clase la que se entiende con la clas Proveedor.
 * La clase Comprador se encarga de retirar "dinero" de los recursos de la empresa
 * e ingresar los productos de los proveedores a los productosStock de la empresa*/
public class Comprador extends Empleados implements EmpleadoInterface{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5775855165319657545L;
	private static int cantidadCompradores;//Variable Constante que muestra cuantos Compradores pertenecen a la empresa
	
	/*
	 * El constructor de un Comprador no es muy distinto al constructor de un Empleado, ya que un Comprador
	 * no tiene ningún otor método que no esté referenciado en la interfaz EmpleadoInterface ni sea 
	 * heredado de la clase Empleados*/
	public Comprador(){
		super();
	}
	public Comprador(String nombre, double cedulaCiuidadania, double salario, double telefono, String correoElctronico, String fechaNacimiento, String contrasenia){
		super (nombre, cedulaCiuidadania, salario, telefono, correoElctronico, fechaNacimiento, contrasenia);
	}
	
	/*
	 * El método setCantidadVendedores retorna true cuando el valor ingresado no es negativo, ya que
	 * una empresa no puede tener una cantidad negativa de trabajadores*/
	public static boolean setCantidadCompradores(int cantidadCompradores) {
		if(cantidadCompradores >= 0){
			Comprador.cantidadCompradores = cantidadCompradores;
			return true;
		}
		else{
			return false;
		}
	}

	public static int getCantidadCompradores() {
		return cantidadCompradores;
	}
	
	/*
	 * El método toString retorna los atributos de un Comprador determinado*/
	public String toString(){
		return ("Comprador:\n"+super.toString());
	}
	
	/*
	 * El método trabajar implementa las órdenes que realiza un determinado objeto de tipo Empleados
	 * con respecto a su función específica, para el caso de comprador, el método crea facturas de compra
	 * con respecto a los proveedores que están vendiendo los productos y agrega el nuevo productu a los
	 * recursos de la misma, extrayendo dinero de los recursos de la empresa*/
	public FacturaCompra trabajar(Empresa empresa, ArrayList<Productos> productos, Object proveedor) {
		/* genera facutra*/
		FacturaCompra fac = new FacturaCompra(new Date(System.currentTimeMillis()), productos, (Proveedor) proveedor);

		/* verifica costos*/
		if(fac.getValorTotalCompras() > IngresarTest.empresa.getRecursos()){
			JOptionPane.showMessageDialog(null, "No hay suficientes recuros para soportar\nesta compra.", "$$$", JOptionPane.ERROR_MESSAGE);
			fac = null;
		}
		else{
			
			for(int i=0; i<productos.size();i++){
				/* ingresa al stock*/
				Productos tempProducto = IngresarTest.empresa.getProducto(productos.get(i).getReferencia());
				IngresarTest.empresa.setProducto(tempProducto.getReferencia(), tempProducto.getCantidadStock()+productos.get(i).getCantidadStock(), tempProducto.getValorVenta(), tempProducto.getValorCompra());
			}
			
			/* retira de los recursos*/
			IngresarTest.empresa.setRecursos(empresa.getRecursos()-fac.getValorTotalCompras());
			
			/* agrega factura al regsitro*/
			IngresarTest.empresa.getRegistroContaduria().agregarFacturaCompra(fac);
		}
		return fac;
	}
	
	/*
	 * El método modificar se encarga de cambiar todos los atributos de un objeto instanciado
	 * en la clase Comprador*/
	public void modificar(String nombre, double cedulaCiudadania, double salario, double telefono, String correoElectronico, String fechaNacimiento){
		this.setNombre(nombre);
		this.setCedulaCiudadania(cedulaCiudadania);
		this.setSalario(salario);
		this.setTelefono(telefono);
		this.setCorreoElectronico(correoElectronico);
		this.setFechaNacimiento(fechaNacimiento);
	}
}
