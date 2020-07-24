package model;

public class Caracteristicas implements Genetico {
	private Hardware Bateria;
	private Hardware Camara;
	private Hardware Motor;
	
	public Caracteristicas() {
		Definir();
	}

	@Override
	public void Cruce(Object obj) {
		Caracteristicas caracteristicas = (Caracteristicas) obj;
	}
	@Override
	public void Mutar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Definir() {
		Bateria = Hardware.MEDIO;
		Camara = Hardware.MEDIO;
		Motor = Hardware.MEDIO;		
	}
	
}
