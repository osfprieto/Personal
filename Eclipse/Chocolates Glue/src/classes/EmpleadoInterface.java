package classes;

import java.util.ArrayList;

/*
 * La interface Empleado se encarga de enunciar los métodos de los que
 * dispondrán los distintos empleados de una empresa*/
public interface EmpleadoInterface {
	/*
	 * El método trabajar está pensado para asignar instrucciones a un empleado para que
	 * realice algunas actividades en pro del desarrollo de la empresa para la cuál trabaja*/
	public abstract Factura trabajar(Empresa empresa, ArrayList<Productos> productos, Object contacto);
}
