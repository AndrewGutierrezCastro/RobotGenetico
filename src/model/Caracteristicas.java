package model;
import java.util.Random;

public class Caracteristicas implements Genetico {
	public Hardware Bateria;
	public Hardware Camara;
	public Hardware Motor;
	
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
		Random rand = new Random();
		int numeroRandom;
		Hardware[] listaHardware = Hardware.values();
		
		numeroRandom = rand.nextInt(3);
		this.Bateria = listaHardware[numeroRandom];
		numeroRandom = rand.nextInt(3);
		this.Camara = listaHardware[numeroRandom];
		numeroRandom = rand.nextInt(3);
		this.Motor = listaHardware[numeroRandom];

	}
	
}
