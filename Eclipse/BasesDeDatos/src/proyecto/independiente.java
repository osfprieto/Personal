package proyecto;

@SuppressWarnings("serial")
public class independiente extends DatosMadre{
	public independiente(String numeroDocumento){
		super("Datos de independiente", 2, numeroDocumento);
		
		addPregunta("Actividad económica", PanelPregunta.TIPO_ENTRADA_TEXTO, null, false);
		addPregunta("Valor de los ingresos", PanelPregunta.TIPO_ENTRADA_NUMERO, null, true);
	}
}
