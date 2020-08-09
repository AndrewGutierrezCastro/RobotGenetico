package model;

public enum Estado {
	AVANZANDO(0),
	OBSERVANDO(1),
	ESPERANDO(2);
	
	public int indice;
	Estado(int pIndice){
		this.indice = pIndice;
	}
}
