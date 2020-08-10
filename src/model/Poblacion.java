package model;

import java.util.HashMap;

import javax.swing.Icon;
import javax.swing.JLabel;

import controller.Helpers;

public class Poblacion {
	static Poblacion self;
	public HashMap<Integer, Generacion> Generacion;
	public HashMap<String, Icon> hashImagenes;
	public int SizePoblacion = 24;
	public double probabilidadMutacion = 0.3; //P(M) = 1 / SizePoblacion 
													//La probabilidad que un individuo sea mutado
	public final double maxSizePoblacion = SizePoblacion * probabilidadMutacion *10;
	private JLabel[][] lblTerreno;
	private JLabel[][] lblBloques;
	public int tiempoEspera = 5;
	private Poblacion() {
		Generacion = new HashMap<Integer, Generacion>();
		hashImagenes = new HashMap<String, Icon>();
		cargarImagenes();
	}

	private void cargarImagenes() {
		hashImagenes.put("ROBOT", Helpers.getImagen("Robot", ".png", 520, 610));
	}

	public static Poblacion getInstance() {
		if (self == null)  
			self = new Poblacion(); 
        return self;
	}

	public void CrearGeneracionInicial() {
		Generacion generacion = new Generacion(SizePoblacion, 0);
		generacion.GeneracionAleatoria();
		Generacion.put(0, generacion);
	}

	public void ComportarGeneracion() {
		Generacion.get(Generacion.size() - 1).ComportarRobots();
		
	}
	
	public void PausaGeneracion() {
		Generacion.get(Generacion.size() - 1).PausaRobots();
		
	}
	
	public void CrearNuevaGeneracion() {
		/* Definir el valor objetivo -> en nuestro caso es llegar a la casilla 0,19
		 * y su valor de aptitud
		 * Definir un valor de aptitud para cada robot, entre mas alto, el individuo es mejor
		 * 	este sirve para poder aplicar la seleccion.
		 * Entre mas alto el valor de aptitud mayor probabilidad de seleccion del individuo.
		 * Con los individuos seleccionados se hace el cruce para obtener la nueva generacion.
		 * */
		Robot[] nuevaGen = Generacion.get(Generacion.size() - 1).seleccionarElegidos();
		Generacion gen = new Generacion(Generacion.size(), nuevaGen);
		Generacion.put(gen.getNumeroGeneracion(),gen);
	}

	public JLabel[][] getLblTerreno() {
		return lblTerreno;
	}

	public void setLblTerreno(JLabel[][] lblTerreno) {
		this.lblTerreno = lblTerreno;
	}
	
	public JLabel[][] getLblBloques() {
		return lblBloques;
	}

	public void setLblBloques(JLabel[][] lblBloques) {
		this.lblBloques = lblBloques;
	}

	public boolean isAllDead() {
		Robot[] robots = Generacion.get(Generacion.size() - 1).robots;
		int numeroRobotsMuertos = 0;
		for (Robot robot : robots) {
			if(!robot.isAlive()) {
				numeroRobotsMuertos++;
			}
		}
		
		return numeroRobotsMuertos >= (robots.length*0.75);
	}

	
}
