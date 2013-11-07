package classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class RegistroContaduria implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2724296285222950127L;
	private Date fechaInicio; //fecha desde la que empieza el registro
	private Date fechaFin; //fecha hasta donde va el registro
	private double ingresos; //ingresos para la compa–ia consignados en el registro de ventas dentro de las fechas dadas
	private double egresos;  //egresos para la compa–ia consignados en el registro de ventas dentro de las fechas dadas
	private  ArrayList<FacturaVenta> registroVentas = new ArrayList<FacturaVenta>(); //registro de ventas modelado como una lista de facturas de venta
	private  ArrayList<FacturaCompra> registroCompras = new ArrayList<FacturaCompra>();  //registro de compra modelado como una lista de facturas de compra

	public RegistroContaduria(Date fechaInicio, Date fechaFin/*,
			ArrayList<FacturaCompra> registroCompra*/) {
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.registroCompras = new ArrayList<FacturaCompra>();
		this.registroVentas = new ArrayList<FacturaVenta>();
		//this.registroCompras = registroCompra;
	}
	
	public ArrayList<FacturaVenta> getRegistroVentas(){
		return this.registroVentas;
	}
	
	public ArrayList<FacturaCompra> getRegistroCompras(){
		return this.registroCompras;
	}
	
	public void agregarFacturaCompra(FacturaCompra fac){
		this.registroCompras.add(fac);
	}
	public void agregarFacturaVenta(FacturaVenta fac){
		this.registroVentas.add(fac);
	}

	public double getIngresos(Date inicio, Date fin){ // calcula los ingresos de un registro
		ingresos=0;
		for (int i = 0; i < registroVentas.size(); i++) {
			Date tempDate = registroVentas.get(i).getDate();
			if((tempDate.after(inicio) || tempDate.equals(inicio)) && (tempDate.before(fin) || tempDate.equals(fin))){
				FacturaVenta temp = registroVentas.get(i);
				ingresos=ingresos+temp.getValorTotalVentas();
			}
		}
		return ingresos;
	}

	public double getEgresos(Date inicio, Date fin){ // calcula los egresos de un registro
		egresos=0;
		for (int i = 0; i < registroCompras.size(); i++){
			Date tempDate = registroCompras.get(i).getDate();
			if((tempDate.after(inicio) || tempDate.equals(inicio)) && (tempDate.before(fin) || tempDate.equals(fin))){
				FacturaCompra temp = registroCompras.get(i);
				egresos=egresos+temp.getValorTotalCompras();
			}
		}
		return egresos;	
	}

	public double getSaldoTotal(Date inicio, Date fin){ // calcula el saldo total de un registro
		return this.getIngresos(inicio, fin)-this.getEgresos(inicio, fin);

	}

	public String toString(Date inicio, Date fin){ // imprime toda la informacion de un registro de ventas
		return
		"REGISTRO DE VENTAS"+
		"\nDesde: "+this.fechaInicio+". Hasta: "+this.fechaFin+
		"\nIngresos: "+this.getIngresos(inicio, fin)+
		"\nEgresos: "+this.getEgresos(inicio, fin)+
		"\nSaldo Total: "+this.getSaldoTotal(inicio, fin);
	}
}