package proyecto;

@SuppressWarnings("serial")
public class MadreFrame extends SQLFrame{
	
	public MadreFrame(){
		super("Ingreso de datos de la madre", 13);
		
		//crear y asignar las preguntas al arreglo
		//agregar las preguntas al panel que ya está creado
		//darle forma al formulario
		
		addPregunta("Número de documento", PanelPregunta.TIPO_ENTRADA_NUMERO, null, false);
		String[] tipoDocumento = {"", "CC", "TI", "CE"};
		addPregunta("Tipo de documento", PanelPregunta.TIPO_SELECCION_LISTA, tipoDocumento, false);
		addPregunta("Nombres y apellidos", PanelPregunta.TIPO_ENTRADA_TEXTO, null, false);
		String[] sexo = {"Femenino"};
		addPregunta("Sexo", PanelPregunta.TIPO_ENTRADA_TEXTO, sexo, false);
		addPregunta("Edad", PanelPregunta.TIPO_ENTRADA_NUMERO, null, false);
		String[] tipoVinculacion = {"", "estudiante_UN", "empleado_oficial", "docente_UN", "estudiante", "empleado", "independiente"};
		addPregunta("Tipo de vinculación", PanelPregunta.TIPO_SELECCION_LISTA, tipoVinculacion, false);
		addPregunta("Correo electrónico", PanelPregunta.TIPO_ENTRADA_TEXTO, null, false);//hacer algo en la base de datos que verifique el buen estado del correo (correo like '%@% and correo not like '@%' and correo not like '%@')
		addPregunta("Número de celular", PanelPregunta.TIPO_ENTRADA_NUMERO, null, false);
		addPregunta("Número fijo 1", PanelPregunta.TIPO_ENTRADA_NUMERO, null, true);
		addPregunta("Número fijo 2", PanelPregunta.TIPO_ENTRADA_NUMERO, null, true);
		addPregunta("Dirección", PanelPregunta.TIPO_ENTRADA_TEXTO, null, false);
		String[] escolaridad = {"", "Básica primaria", "Secundaria", "Media", "Técnica/Tecnológica", "Pregrado en curso", "Pregrado terminado", "Postgrado en curso", "Postgrado terminado"};
		addPregunta("Escolaridad", PanelPregunta.TIPO_SELECCION_LISTA, escolaridad, false);
		String[] estadoCivil = {"", "Soltero", "Casado", "Viudo", "Separado"};
		addPregunta("Estado civil", PanelPregunta.TIPO_SELECCION_LISTA, estadoCivil, true);
	}
}
