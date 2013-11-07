package proyecto;


@SuppressWarnings("serial")
public class MatriculaFrame extends SQLFrame{

	public MatriculaFrame(){
		super("Ingreso de matrícula nueva", 5);
		
		//crear y asignar las preguntas al arreglo
		//agregar las preguntas al panel que ya está creado
		//darle forma al formulario
		
		addPregunta("Id matrícula", PanelPregunta.TIPO_ENTRADA_NUMERO, null, false);
		String[] tipoVinculacion = {"", "Estudiante", "Administrativo", "Docente", "No vinculado"};
		addPregunta("Vinculación del padre/madre", PanelPregunta.TIPO_SELECCION_LISTA, tipoVinculacion, false);
		addPregunta("Fecha de matrícula", PanelPregunta.TIPO_ENTRADA_DATE, null, false);
		addPregunta("Año de vinculación", PanelPregunta.TIPO_ENTRADA_DATE, null, false);
		addPregunta("Curso actual matrícula", PanelPregunta.TIPO_ENTRADA_NUMERO, null, false);
		
	}
}
