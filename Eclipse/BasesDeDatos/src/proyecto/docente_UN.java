package proyecto;

@SuppressWarnings("serial")
public class docente_UN extends DatosMadre{

	public docente_UN(String numeroDocumento){
		super("Datos de docente UN", 8, numeroDocumento);
		
		addPregunta("Facultad", PanelPregunta.TIPO_ENTRADA_TEXTO, null, false);
		addPregunta("Departamento", PanelPregunta.TIPO_ENTRADA_TEXTO, null, false);
		String[] categoria = {"", "Auxiliar", "Asistente asociado", "Titular investigador"};
		addPregunta("Categor�a", PanelPregunta.TIPO_SELECCION_LISTA, categoria, false);
		String[] dedicacion = {"", "C�tedra", "Medio tiempo", "Tiempo completo", "Exclusiva"};
		addPregunta("Dedicaci�n", PanelPregunta.TIPO_SELECCION_LISTA, dedicacion, false);
		addPregunta("Salario", PanelPregunta.TIPO_ENTRADA_NUMERO, null, false);
		addPregunta("Fecha ingreso", PanelPregunta.TIPO_ENTRADA_DATE, null, false);
		addPregunta("Origen otros ingresos (si aplica)", PanelPregunta.TIPO_ENTRADA_TEXTO, null, true);
		addPregunta("Cuant�a otros ingresos (si aplica)", PanelPregunta.TIPO_ENTRADA_NUMERO, null, true);
	}
	
}
