package playerFactory;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import uno.model.UNOFachada;
import uno.model.UNOModelo;



public class ControladorJugadores
{
	public static int MAX_JUGADORES = 4;
	private ArrayList<String> jugadores = new ArrayList<String>();
	private boolean[] humanos = new boolean[MAX_JUGADORES];
	private String[] automaticos = new String[MAX_JUGADORES];
	private IJugador[] automaticosInstanciados = new IJugador[MAX_JUGADORES];
	private boolean configurado = false;
	public ArrayList<String> darJugadoresAutomaticos()
	{
		return jugadores;
	}

	
	public boolean cargarJugadoresAutomaticosConIntrospeccion(){
		
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
//		URL packageURL = loader.getResource("player");
		File folder = new File("./bin/player/");
		System.out.println(folder.getAbsolutePath());
        File[] contenuti = folder.listFiles();
        String entryName;
        
        for(File actual: contenuti){
            entryName = actual.getName();
            entryName = entryName.substring(0, entryName.lastIndexOf('.'));
            jugadores.add(entryName);
        }
		
		return true;
	}
	
//	public static void main(String[] args)
//	{
//		ControladorJugadores cj = new ControladorJugadores();
//		cj.cargarJugadoresAutomaticosConIntrospeccion();
//		System.out.println(cj.darJugadoresAutomaticos());
//	}

	public void jugar(int turnoActual, UNOFachada fachada)
	{
		if(!this.configurado)
		{
			javax.swing.JOptionPane.showMessageDialog(null,"Debe configurar primero los jugadores automaticos!");
			return;
		}
		// saque el jugador asignado para el turno si no es humano
		
		if (!humanos[turnoActual-1])
		{
			//jugador previamente instanciado
			IJugador jugadorInstancia = this.automaticosInstanciados[turnoActual-1];
			jugadorInstancia.jugar(turnoActual, fachada);
		}
		else
		{
			javax.swing.JOptionPane.showMessageDialog(null,"El jugador Actual es Humano");
		}
	}

	public void setup(boolean[] huma, String[] auto)
	{
		this.configurado = true;
		this.humanos= huma;
		this.automaticos = auto;
		try
		{
			for(int i =0; i< UNOModelo.NUMERO_JUGADORES; i++)
			{
				if(!humanos[i])
				{
					String nombreclaseautomatica = automaticos[i];
					Class claseautomatica = Class.forName("player."+nombreclaseautomatica);
					IJugador jugadorInstancia = (IJugador)claseautomatica.newInstance();
					this.automaticosInstanciados[i] = jugadorInstancia;
				}
			}
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} 
		catch (InstantiationException e)
		{
			e.printStackTrace();
		} 
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
	}
	public boolean esPosibleJuegoAutonomo()
	{
		for(int i = 0; i< UNOModelo.NUMERO_JUGADORES;i++)
		{
			if(humanos[i]) return false;
			if(automaticos[i]==null) return false;
		}
		return true;
	}
}
