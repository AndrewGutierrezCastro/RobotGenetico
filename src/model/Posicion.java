package model;

	
public	class Posicion{
	enum Direccion {
		ARRIBA(-1,0),
		ABAJO(1,0),
		DERECHA(0,1),
		IZQUIERDA(0,-1);
		
		public int x;
		public int y;
		private Direccion(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public int x = 19, y = 0;

	public Posicion(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public Posicion() {
		super();
	}
	public Posicion Copy() {
		Posicion copia = new Posicion(this.x, this.y);
		return copia;
	}

	public Posicion Mover(int direccion) {
		Posicion resultado = Copy();
		switch (direccion) {
		case 0:
			resultado.DESPLAZARSEIZQ();
			break;
		case 1:
			resultado.BAJAR();
			break;
		case 2:		
			resultado.DESPLAZARDER();
			break;
		case 3:		
			resultado.SUBIR();
			break;
		default:
			break;
		}
		return resultado;
	}
	public void Sumar(Direccion a) {
		this.x += a.x;
		this.y += a.y;
	}
	public void SUBIR() {
		this.Sumar(Direccion.ARRIBA);
	}
	public void BAJAR() {
		this.Sumar(Direccion.ABAJO);
	}
	public void DESPLAZARSEIZQ() {
		this.Sumar(Direccion.IZQUIERDA);
	}
	public void DESPLAZARDER() {
		this.Sumar(Direccion.DERECHA);
	}
	public double distancia(Posicion posicion) {
		double resultado = 0.0;
		resultado = Math.sqrt(Math.pow((posicion.x - this.x),2) + Math.pow((posicion.y-this.y),2));
		return  resultado;
	}
}

