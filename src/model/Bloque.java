package model;

public enum Bloque {
	NORMAL(0),
	MODERADO(5), 
	DIFICIL(8),
	BLOQUEADO(10);
	
	public int consumo;
	Bloque(int pConsumo) {
		consumo = pConsumo;
	}
	
	
}
