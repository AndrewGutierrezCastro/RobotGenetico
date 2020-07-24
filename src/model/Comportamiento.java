package model;

import java.util.Random;
import java.util.stream.Stream;

public class Comportamiento implements Genetico {
	private int[] avanzar;
	private int[] esperar;
	private int[] observar;
	private Estado estado;
	private static int numComportamientos = 3;
	
	public Comportamiento() {
		
		avanzar = new int[numComportamientos];
		esperar = new int[numComportamientos];
		observar = new int[numComportamientos];
		estado = Estado.ESPERANDO;
	}
	
	public void Definir() {
		Random rand = new Random();
		rand.setSeed(12134315);
		
		avanzar[0] = rand.nextInt(100);
		esperar[0] = rand.nextInt(100);
		observar[0] = rand.nextInt(100);
		
		avanzar[1] = rand.nextInt(100 - avanzar[0]);
		esperar[1] = rand.nextInt(100 - esperar[0]);
		observar[1] = rand.nextInt(100 - observar[0]);
		
		avanzar[2] = rand.nextInt(100 - (avanzar[0] + avanzar[1]) );
		esperar[2] = rand.nextInt(100 - (esperar[0] + esperar[1]) );
		observar[2] = rand.nextInt(100 - (observar[0] + observar[1]) );
	}
	public void Cruce(Object obj) {
		//TODO hacer el cruce de comportamiento con otro comportamiento
		Comportamiento comportamiento = (Comportamiento) obj;
	}
	public void Mutar() {
		//TODO llamar este metodo luego de la creacion para mutar
	}
}
