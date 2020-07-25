package model;

import java.util.Random;

public class Comportamiento implements Genetico {
	private int[] avanzar; // 0: asi mismo,  1:esperar 2:observar
	private int[] esperar; // 0: asi mismo,  1:avanzar 2:observar
	private int[] observar;// 0: asi mismo,  1:avanzar 2:esperar
	private Estado estado;
	private static int numComportamientos = 3;
	
	public Comportamiento() {
		
		avanzar = new int[numComportamientos];// -> MOTOR
		esperar = new int[numComportamientos];// -> BATERIA&CAMARA
		observar = new int[numComportamientos];// -> MAL MOTOR PERO CON BUENA CAMARA
		estado = Estado.ESPERANDO;
	}
	
	public void Definir() {
		Random rand = new Random();
		rand.setSeed(12134315);
		
		avanzar[0] = rand.nextInt(100);// 0 - 100 = 45
		esperar[0] = rand.nextInt(100);
		observar[0] = rand.nextInt(100);
		
		avanzar[1] = rand.nextInt(100 - avanzar[0]);// 100 - 45 = 55
		esperar[1] = rand.nextInt(100 - esperar[0]);
		observar[1] = rand.nextInt(100 - observar[0]);
		
		avanzar[2] = 100 - (avanzar[0] + avanzar[1]) ;
		esperar[2] = 100 - (esperar[0] + esperar[1]) ;
		observar[2] = 100 - (observar[0] + observar[1]) ;
	}
	public void Cruce(Object obj) {
		//TODO hacer el cruce de comportamiento con otro comportamiento
		Comportamiento comportamiento = (Comportamiento) obj;
	}
	public void Mutar() {
		//TODO llamar este metodo luego de la creacion para mutar
	}
}
