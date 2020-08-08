package model;

public enum Bloque {
	NORMAL(0),
	MODERADO(15), 
	DIFICIL(25),
	BLOQUEADO(0);
	
	public int consumo;//El consumo de atravezar dicho bloque
	Bloque(int pConsumo) {
		consumo = pConsumo;
	}
}
