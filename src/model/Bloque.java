package model;

public enum Bloque {
	NORMAL(0.3),
	MODERADO(0.5), 
	DIFICIL(0.8),
	BLOQUEADO(1.0);
	
	public double consumo;
	Bloque(double pConsumo) {
		consumo = pConsumo;
	}
	
	
}
