package model;

public enum Bloque {
	NORMAL(0),
	MODERADO(25), 
	DIFICIL(50),
	BLOQUEADO(0);
	
	public int consumo;//El consumo de atravezar dicho bloque
	Bloque(int pConsumo) {
		consumo = pConsumo;
	}
}
