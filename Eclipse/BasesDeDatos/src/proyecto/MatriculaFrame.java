package proyecto;


@SuppressWarnings("serial")
public class MatriculaFrame extends SQLFrame{

	public MatriculaFrame(){
		super("Ingreso de matr�cula nueva", 5);
		
		//crear y asignar las preguntas al arreglo
		//agregar las preguntas al panel que ya est� creado
		//darle forma al formulario
		
		addPregunta("Id matr�cula", PanelPregunta.TIPO_ENTRADA_NUMERO, null, false);
		String[] tipoVinculacion = {"", "Estudiante", "Administrativo", "Docente", "No vinculado"};
		addPregunta("Vinculaci�n del padre/madre", PanelPregunta.TIPO_SELECCION_LISTA, tipoVinculacion, false);
		addPregunta("Fecha de matr�cula", PanelPregunta.TIPO_ENTRADA_DATE, null, false);
		addPregunta("A�o de vinculaci�n", PanelPregunta.TIPO_ENTRADA_DATE, null, false);
		addPregunta("Curso actual matr�cula", PanelPregunta.TIPO_ENTRADA_NUMERO, null, false);
		
	}
}
