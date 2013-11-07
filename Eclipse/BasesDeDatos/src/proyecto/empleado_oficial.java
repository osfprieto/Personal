package proyecto;

@SuppressWarnings("serial")
public class empleado_oficial extends DatosMadre{
	public empleado_oficial(String numeroDocumento){
		super("Datos de empleado oficial", 8, numeroDocumento);
		
		addPregunta("Fecha ingreso", PanelPregunta.TIPO_ENTRADA_DATE, null, false);
		addPregunta("Cargo", PanelPregunta.TIPO_ENTRADA_TEXTO, null, false);
		String[] nivel = {"", "Asistencial", "Técnico", "Profesional", "Directivo"};
		addPregunta("Nivel", PanelPregunta.TIPO_SELECCION_LISTA, nivel, true);
		addPregunta("Salario", PanelPregunta.TIPO_ENTRADA_NUMERO, null, false);
		addPregunta("Grado", PanelPregunta.TIPO_ENTRADA_NUMERO, null, true);
		addPregunta("Dependencia", PanelPregunta.TIPO_ENTRADA_TEXTO, null, false);
		addPregunta("Origen de otros ingresos (si aplica)", PanelPregunta.TIPO_ENTRADA_TEXTO, null, true);
		addPregunta("Cuantía de otros ingresos (si aplica)", PanelPregunta.TIPO_ENTRADA_NUMERO, null, true);
	}
}
