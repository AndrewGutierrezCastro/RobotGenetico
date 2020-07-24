package model;

public class Simulacion {
	private Poblacion poblacion;
	private Terreno terreno;
	static Simulacion self;
	
	public Simulacion() {
		poblacion = new Poblacion();
		poblacion.CrearGeneracionInicial();	
		terreno = new Terreno();	
	}
	
	public static Simulacion getInstance() {
		if (self == null)
			self = new Simulacion();
		return self;
	}

	public Terreno getTerreno() {
		return terreno;
	}

	public void setTerreno(Terreno terreno) {
		this.terreno = terreno;
	}
	
	
}
