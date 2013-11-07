package classes;

import gui.IngresarTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JOptionPane;

/*
 * La clase Vendedor es la clase que se encarga de venderle los productos ofrecidos por la empresa
 * a los clientes, por lo que es esta clase la que se entiende con la clas cliente.
 * La clase Vendedor se encarga de retirar objetos de tipo producto de los productoStock de la empresa
 * e ingresar "dinero" a los recursos de la empresa con respecto a los productos seleccionados*/
public class Vendedor extends Empleados implements EmpleadoInterface{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5376586806735399621L;
	private static int cantidadVendedores;//Variable que muestra el número de vendedores que hay trabajando para la empresa
	
	/*
	 * El constructor de un Vendedor no es muy distinto al constructor de un Empleado, ya que un Vendedor
	 * no tiene ningún otor método que no esté referenciado en la interfaz EmpleadoInterface ni sea 
	 * heredado de la clase Empleados*/
	public Vendedor(){
		super();
	}
	public Vendedor(String nombre, double cedulaCiuidadania, double salario, double telefono, String correoElctronico, String fechaNacimiento, String contrasenia){
		super (nombre, cedulaCiuidadania, salario, telefono, correoElctronico, fechaNacimiento, contrasenia);
	}
	
	/*
	 * El método setCantidadVendedores retorna true cuando el valor ingresado no es negativo, ya que
	 * una empresa no puede tener una cantidad negativa de trabajadores*/
	public static boolean setCantidadVendedores(int cantidadVendedores) {
		if(cantidadVendedores>=0){
			Vendedor.cantidadVendedores = cantidadVendedores;
			return true;
		}
		else{
			return false;
		}
	}

	public static int getCantidadVendedores() {
		return cantidadVendedores;
	}
	
	/*
	 * El método toString retorna los atributos de un Vendedor determinado*/
	public String toString(){
		return ("Vendedor:\n"+super.toString());
	}
	
	/*
	 * El método trabajar implementa las órdenes que realiza un determinado objeto de tipo Empleados
	 * con respecto a su función específica, para el caso de Vendedor, el método crea facturas de venta
	 * con respecto a los clientes que están comprando los productos y agrega el nuevo "dinero" a los
	 * recursos de la misma, al extraer los productos de la empresa*/
	public FacturaVenta trabajar(Empresa empresa, ArrayList<Productos> productos, Object cliente) {
		/* genera facutra*/
		
		FacturaVenta fac= new FacturaVenta(new Date(System.currentTimeMillis()), productos, (Cliente) cliente);
		
		HashMap<String, Integer> tempSell = new HashMap<String, Integer>();
		
		for(int i=0;i<productos.size();i++){
			tempSell.put(productos.get(i).getReferencia(), 0);
		}
		
		for(int i=0; i<productos.size();i++){
			tempSell.put(productos.get(i).getReferencia(), tempSell.get(productos.get(i).getReferencia())+productos.get(i).getCantidadStock());
		}
		boolean cantidadNoValida = false;
		for(int i=0; i<productos.size();i++){
			if(tempSell.get(productos.get(i).getReferencia())  >   IngresarTest.empresa.getProducto(productos.get(i).getReferencia()).getCantidadStock()  ){
				cantidadNoValida = true;
			}
		}
		
		/* verifica Stock*/
		if(cantidadNoValida){
			JOptionPane.showMessageDialog(null, "No hay suficientes productos para soportar\nesta venta.", "$$$", JOptionPane.ERROR_MESSAGE);
			fac = null;
		}
		else{
			
			/* ingresa a recursos*/
			IngresarTest.empresa.setRecursos(IngresarTest.empresa.getRecursos()+fac.getValorTotalVentas());
			
			/* retira del Stock*/
			for(int i=0; i<productos.size();i++){
				IngresarTest.empresa.setProducto(productos.get(i).getReferencia(), IngresarTest.empresa.getProducto(productos.get(i).getReferencia()).getCantidadStock()  -  productos.get(i).getCantidadStock(), IngresarTest.empresa.getProducto(productos.get(i).getReferencia()).getValorVenta(), IngresarTest.empresa.getProducto(productos.get(i).getReferencia()).getValorCompra());
			}			
			/* agrega factura al regsitro*/
			IngresarTest.empresa.getRegistroContaduria().agregarFacturaVenta(fac);
		}
		return fac;
	}
	
	/*
	 * El método modificar se encarga de cambiar todos los atributos de un objeto instanciado
	 * en la clase Vendedor*/
	public void modificar(String nombre, double cedulaCiudadania, double salario, double telefono, String correoElectronico, String fechaNacimiento){
		this.setNombre(nombre);
		this.setCedulaCiudadania(cedulaCiudadania);
		this.setSalario(salario);
		this.setTelefono(telefono);
		this.setCorreoElectronico(correoElectronico);
		this.setFechaNacimiento(fechaNacimiento);
	}
	
}
