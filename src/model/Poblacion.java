package model;

import java.util.HashMap;

public class Poblacion {
	public HashMap<Integer, Generacion> Generacion;
	public final int SizePoblacion = 10;
	
	
	
	public Poblacion() {
		Generacion = new HashMap<Integer, Generacion>();
	}



	public void CrearGeneracionInicial() {
		Generacion generacion = new Generacion(SizePoblacion);
		generacion.GeneracionAleatoria();
		Generacion.put(0, generacion);
		
	}
}
