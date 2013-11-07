/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaDosIngeSoftII;

import java.util.Calendar;

/**
 *
 * @author Omar Simón Francisco Prieto Chacón
 */
public class Forecast {
    
    private double temperatura;
    private Calendar fecha;
    
    //Constructor default
    public Forecast(){}
    
    //constructor 
    //recibe 2 parametros temperatura de tipo String y fecha de tipo Date
    //temperatura indica un valor numerico
    //fecha con formato DD/MM/YYYY y es obtenida del sistema
    //crea un objeto Reporte
    public Forecast(String temperatura, Calendar fecha){
        this.temperatura = Double.parseDouble(temperatura);
        this.fecha = fecha;
    }
    
    public Forecast(double temperatura, Calendar fecha){
        this.temperatura = temperatura;
        this.fecha = fecha;
    }

     /*
      * Constructor que recibe como parametro la temperatura y por defecto
      * asigna a la fecha el valor de la fecha al momento de ingreso
      */

    

    public Forecast(Double temperatura){
        this.temperatura = temperatura;
        this.fecha = Calendar.getInstance();
    }
    

    /**
     * @return the temperatura
     */

    public Double getTemperatura() {

        return temperatura;
    }

    /**
     * @param temperatura the temperatura to set
     */
    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    /**
     * @return the fecha
     */
    public Calendar getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }
}