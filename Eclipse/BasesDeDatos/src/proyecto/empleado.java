package proyecto;

@SuppressWarnings("serial")
public class empleado extends DatosMadre{
	public empleado(String numeroDocumento){
		super("Datos de empleado", 5, numeroDocumento);
		
		addPregunta("Empresa", PanelPregunta.TIPO_ENTRADA_TEXTO, null, false);
		addPregunta("Teléfono", PanelPregunta.TIPO_ENTRADA_NUMERO, null, false);
		addPregunta("Cargo", PanelPregunta.TIPO_ENTRADA_TEXTO, null, false);
		addPregunta("Salario", PanelPregunta.TIPO_ENTRADA_NUMERO, null, false);
		String[] dedicacion = {"", "Medio tiempo", "Tiempo completo"};
		addPregunta("Dedicación", PanelPregunta.TIPO_SELECCION_LISTA, dedicacion, false);
	}
}
