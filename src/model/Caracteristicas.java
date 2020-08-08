package model;
import java.util.Random;

import model.Hardware.TiposHardware;

public class Caracteristicas extends Genetico implements Cloneable {
	public Hardware Bateria;
	public Hardware Camara;
	public Hardware Motor;
	public Hardware Generador;
	
	public Caracteristicas() {
		Definir();
	}
	
	public Caracteristicas clone() {
		Caracteristicas caracteristicas = new Caracteristicas();
		caracteristicas.Bateria = this.Bateria.clone();
		caracteristicas.Motor = this.Motor.clone();
		caracteristicas.Camara = this.Camara.clone();
		return caracteristicas;
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
		TiposHardware[] listaHardware = Hardware.TiposHardware.values();
		
		numeroRandom = rand.nextInt(3);
		this.Bateria = new Hardware(listaHardware[numeroRandom]);
		rand = new Random();
		numeroRandom = rand.nextInt(3);
		this.Camara = new Hardware(listaHardware[numeroRandom]);
		rand = new Random();
		numeroRandom = rand.nextInt(3);
		this.Motor = new Hardware(listaHardware[numeroRandom]);
		numeroRandom = rand.nextInt(3);
		this.Generador = new Hardware(listaHardware[numeroRandom]);
	}
	
}
