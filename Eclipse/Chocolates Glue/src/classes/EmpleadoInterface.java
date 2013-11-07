package classes;

import java.util.ArrayList;

/*
 * La interface Empleado se encarga de enunciar los m�todos de los que
 * dispondr�n los distintos empleados de una empresa*/
public interface EmpleadoInterface {
	/*
	 * El m�todo trabajar est� pensado para asignar instrucciones a un empleado para que
	 * realice algunas actividades en pro del desarrollo de la empresa para la cu�l trabaja*/
	public abstract Factura trabajar(Empresa empresa, ArrayList<Productos> productos, Object contacto);
}
