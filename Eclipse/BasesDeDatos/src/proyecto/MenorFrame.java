package proyecto;

@SuppressWarnings("serial")
public class MenorFrame extends SQLFrame{
	
	public MenorFrame(String documentoMadre, String documentoPadre, String idMatricula){
		super("Ingreso de datos del menor", 26);
		
		addPregunta("Número de identificación", PanelPregunta.TIPO_ENTRADA_NUMERO, null, false);
		String[] matricula = {idMatricula};
		addPregunta("Id matrícula", PanelPregunta.TIPO_ENTRADA_NUMERO, matricula, false);
		String[] padre = {documentoPadre};
		addPregunta("Documento del padre", PanelPregunta.TIPO_ENTRADA_NUMERO, padre, false);
		String[] madre = {documentoMadre};
		addPregunta("Documento de la madre", PanelPregunta.TIPO_ENTRADA_NUMERO, madre, false);
		addPregunta("Nombres", PanelPregunta.TIPO_ENTRADA_TEXTO, null, false);
		addPregunta("Apellidos", PanelPregunta.TIPO_ENTRADA_TEXTO, null, false);
		String[] sexo = {"", "Masculino", "Femenino"};
		addPregunta("Sexo", PanelPregunta.TIPO_SELECCION_LISTA, sexo, false);
		addPregunta("Fecha nacimiento", PanelPregunta.TIPO_ENTRADA_DATE, null, false);
		addPregunta("País nacimiento", PanelPregunta.TIPO_ENTRADA_TEXTO, null, false);
		addPregunta("Ciudad nacimiento", PanelPregunta.TIPO_ENTRADA_TEXTO, null, false);
		addPregunta("Departamento nacimiento", PanelPregunta.TIPO_ENTRADA_TEXTO, null, false);
		addPregunta("Nombre ARS (si aplica)", PanelPregunta.TIPO_ENTRADA_TEXTO, null, true);
		addPregunta("Nombre EPS (si aplica)", PanelPregunta.TIPO_ENTRADA_TEXTO, null, true);
		addPregunta("Discapacidad (si aplica)", PanelPregunta.TIPO_ENTRADA_PARRAFO, null, true);
		String[] sino = {"", "Si", "No"};
		addPregunta("Vive con el padre", PanelPregunta.TIPO_SELECCION_LISTA, sino, false);
		addPregunta("Vive con la madre", PanelPregunta.TIPO_SELECCION_LISTA, sino, false);
		addPregunta("Dirección 1", PanelPregunta.TIPO_ENTRADA_TEXTO, null, false);
		addPregunta("Teléfono 1", PanelPregunta.TIPO_ENTRADA_NUMERO, null, false);
		addPregunta("Barrio 1", PanelPregunta.TIPO_ENTRADA_TEXTO, null, false);
		addPregunta("Localidad 1", PanelPregunta.TIPO_ENTRADA_TEXTO, null, false);
		addPregunta("Dirección 2 (si aplica)", PanelPregunta.TIPO_ENTRADA_TEXTO, null, true);
		addPregunta("Teléfono 2 (si aplica)", PanelPregunta.TIPO_ENTRADA_NUMERO, null, true);
		addPregunta("Barrio 2 (si aplica)", PanelPregunta.TIPO_ENTRADA_TEXTO, null, true);
		addPregunta("Localidad 2 (si aplica)", PanelPregunta.TIPO_ENTRADA_TEXTO, null, true);
		addPregunta("Institución anterior", PanelPregunta.TIPO_ENTRADA_TEXTO, null, true);
		addPregunta("Causa del retiro", PanelPregunta.TIPO_ENTRADA_PARRAFO, null, true);
	}
}
