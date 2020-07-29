package model;

import java.util.Random;

public class Comportamiento implements Genetico {
	public int[] avanzar; // 0: asi mismo,  1:esperar 2:observar
	public int[] esperar; // 0: asi mismo,  1:avanzar 2:observar
	public int[] observar;// 0: asi mismo,  1:avanzar 2:esperar
	public Estado estado;
	
	public Comportamiento() {
		
		avanzar = new int[]{50, 50, 0};// -> MOTOR
		esperar = new int[]{0, 35, 65};// -> BATERIA&CAMARA
		observar = new int[]{0, 80, 20};// -> MAL MOTOR PERO CON BUENA CAMARA
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
	
	public void verificarProba() {
		if(avanzar[0]<=0){
			avanzar[0]=10;
			avanzar[1]=90;}
		if(avanzar[1]<=0){
			avanzar[1]=10;
			avanzar[0]=90;}
		if(esperar[1]<=0){
			esperar[1]=10;
			esperar[2]=90;}
		if(esperar[2]<=0){
			esperar[2]=10;
			esperar[1]=90;}
		if(observar[1]<=0){
			observar[1]=10;
			observar[2]=90;}
		if(observar[2]<=0){
			observar[2]=10;
			observar[1]=90;}
	}

	public void Cruce(Object obj) {
		//TODO hacer el cruce de comportamiento con otro comportamiento
		Comportamiento comportamiento = (Comportamiento) obj;
	}
	public void Mutar() {
		//TODO llamar este metodo luego de la creacion para mutar
	}
}
