package model;

public enum Direccion {
	ARRIBA(-1,0),
	ABAJO(1,0),
	DERECHA(0,1),
	IZQUIERDA(0,-1),
	POSICION(0,0);
	
	public int x;
	public int y;
	private Direccion(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public void Sumar(Direccion a) {
		this.x += a.x;
		this.y += a.y;
	}
	public void SUBIR() {
		this.Sumar(ARRIBA);
	}
	public void BAJAR() {
		this.Sumar(ABAJO);
	}
	public void DESPLAZARSEIZQ() {
		this.Sumar(IZQUIERDA);
	}
	public void DESPLAZARDER() {
		this.Sumar(DERECHA);
	}
	
	public Direccion Mover(int direccion) {
		Direccion resultado = null;
		resultado = Copy();
		switch (direccion) {
		case 0:
			resultado.SUBIR();
			break;
		case 1:
			resultado.BAJAR();
			break;
		case 2:
			resultado.DESPLAZARSEIZQ();
			break;
		case 3:
			resultado.DESPLAZARDER();
			break;
		default:
			break;
		}
		return resultado;
	}
	Direccion Copy() {
		Direccion copia = POSICION;
		copia.x = this.x;
		copia.y = this.y;
		return copia;
	}
}
