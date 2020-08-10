package model;

public class Simulacion {
	private Poblacion poblacion;
	private Terreno terreno;
	static Simulacion self;
	
	public Simulacion(){
		poblacion = Poblacion.getInstance();
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
	public void createTerreno() {
		terreno = new Terreno();
	}
	public void createGeneracionInicial() {
		poblacion.CrearGeneracionInicial();	
	}
}
