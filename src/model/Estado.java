package model;

public enum Estado {
	OBSERVANDO(0),
	AVANZANDO(1),
	ESPERANDO(2);
	
	public int indice;
	Estado(int pIndice){
		this.indice = pIndice;
	}
}
