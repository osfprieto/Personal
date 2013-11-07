package proyecto;

@SuppressWarnings("serial")
public class estudiante_UN extends DatosMadre{
	
	public estudiante_UN(String numeroDocumento){
		super("Datos de estudiante UN", 12, numeroDocumento);
		
		addPregunta("Valor préstamo a estudiantes (si hay)", PanelPregunta.TIPO_ENTRADA_NUMERO, null, true);
		addPregunta("Valor matrícula", PanelPregunta.TIPO_ENTRADA_NUMERO, null, false);
		addPregunta("Año de inicio del programa", PanelPregunta.TIPO_ENTRADA_NUMERO, null, false);
		String[] semestre = {"", "I", "II"};
		addPregunta("Semestre de inicio", PanelPregunta.TIPO_SELECCION_LISTA, semestre, false);
		addPregunta("Código UN", PanelPregunta.TIPO_ENTRADA_NUMERO, null, false);
		String[] tipoPrograma = {"", "Pregrado", "Especialización", "Maestría", "Doctorado"};
		addPregunta("Tipo de programa", PanelPregunta.TIPO_SELECCION_LISTA, tipoPrograma, false);
		addPregunta("Nombre del programa", PanelPregunta.TIPO_ENTRADA_TEXTO, null, false);
		addPregunta("Fecha graduación (expectativa si aplica)", PanelPregunta.TIPO_ENTRADA_DATE, null, true);
		addPregunta("Semestre que cursa", PanelPregunta.TIPO_ENTRADA_NUMERO, null, false);
		addPregunta("P.A.P.A.", PanelPregunta.TIPO_ENTRADA_NUMERO, null, false);
		addPregunta("Origen de otros Ingresos (si aplica)", PanelPregunta.TIPO_ENTRADA_TEXTO, null, true);
		addPregunta("Cuantía de otros ingresos (si aplica)", PanelPregunta.TIPO_ENTRADA_NUMERO, null, true);
		
	}
	
}
