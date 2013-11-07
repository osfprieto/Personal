package proyecto;

@SuppressWarnings("serial")
public class DatosMadre extends SQLFrame{
	public DatosMadre(String name, int maxPreguntas, String numeroDocumento) {
		super(name, maxPreguntas+1);
		String[] num = {numeroDocumento};
		addPregunta("Número de documento", PanelPregunta.TIPO_ENTRADA_NUMERO, num, false);
	}
}
