package proyecto;

@SuppressWarnings("serial")
public class estudiante extends DatosMadre{
	public estudiante(String numeroDocumento){
		super("Datos de estudiante", 5, numeroDocumento);
		
		addPregunta("Institución", PanelPregunta.TIPO_ENTRADA_TEXTO, null, false);
		addPregunta("Estudios que adelanta", PanelPregunta.TIPO_ENTRADA_TEXTO, null, false);
		addPregunta("Semestre actual", PanelPregunta.TIPO_ENTRADA_NUMERO, null, false);
		String[] dedicacion = {"", "Medio tiempo", "Tiempo completo"};
		addPregunta("Dedicación", PanelPregunta.TIPO_SELECCION_LISTA, dedicacion, false);
		addPregunta("Costo matrícula", PanelPregunta.TIPO_ENTRADA_NUMERO, null, false);	
	}
}
