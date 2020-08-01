package model;

import java.util.HashMap;

public class Poblacion {
	static Poblacion self;
	public HashMap<Integer, Generacion> Generacion;
	public final int SizePoblacion = 5;
	
	private Poblacion() {
		Generacion = new HashMap<Integer, Generacion>();
	}

	public static Poblacion getInstance() {
		if (self == null)  
			self = new Poblacion(); 
        return self;
	}


	public void CrearGeneracionInicial() {
		Generacion generacion = new Generacion(SizePoblacion);
		generacion.GeneracionAleatoria();
		Generacion.put(0, generacion);
	}

	public void ComportarGeneracion() {
		Generacion.get(Generacion.size() - 1).ComportarRobots();
		
	}
}
