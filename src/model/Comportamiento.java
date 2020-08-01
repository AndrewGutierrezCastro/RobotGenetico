package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Comportamiento implements Genetico {
	public int[] avanzar; // 0: asi mismo,  2:observar
	public int[] observar;// 0: asi mismo,  1:avanzar 
	public Estado estado;
	
	public Comportamiento() {
		
		avanzar = new int[]{50, 50, 0};// -> MOTOR
		observar = new int[]{0, 80, 20};// -> MAL MOTOR PERO CON BUENA CAMARA
		estado = Estado.ESPERANDO;
	}
	
	public void getNextComportamiento(int valor, int[] comportamientoActual) {
		int indiceActual = 0;
		ArrayList<Estado> estadoAvanzando;
		for(int i = 0; i < comportamientoActual.length; i++) {
			 if(comportamientoActual[i] == valor) {
				 indiceActual = i;
				 break;
			 }
		}
		
		switch (this.estado) {
		case AVANZANDO:
			estadoAvanzando = new ArrayList<Estado>(
					Arrays.asList(Estado.AVANZANDO, Estado.OBSERVANDO));
			this.estado = estadoAvanzando.get(indiceActual);
			break;
		case OBSERVANDO:
			estadoAvanzando = new ArrayList<Estado>(
					Arrays.asList(Estado.OBSERVANDO, Estado.AVANZANDO));	
			this.estado = estadoAvanzando.get(indiceActual);
			break;
		default:
			break;
		}
	}
	
	public void Definir() {
		Random rand = new Random();
		rand.setSeed(12134315);
		
		avanzar[0] = rand.nextInt(100);// 0 - 100 = 45
		observar[0] = rand.nextInt(100);
		
		avanzar[1] = rand.nextInt(100 - avanzar[0]);// 100 - 45 = 55
		observar[1] = rand.nextInt(100 - observar[0]);
		
	}
	
	public void verificarProba() {
//		if(avanzar[0]<=0){
//			avanzar[0]=10;
//			avanzar[1]=90;}
//		if(avanzar[1]<=0){
//			avanzar[1]=10;
//			avanzar[0]=90;}
//		if(esperar[1]<=0){
//			esperar[1]=10;
//			esperar[2]=90;}
//		if(esperar[2]<=0){
//			esperar[2]=10;
//			esperar[1]=90;}
//		if(observar[1]<=0){
//			observar[1]=10;
//			observar[2]=90;}
//		if(observar[2]<=0){
//			observar[2]=10;
//			observar[1]=90;}
	}

	public void Cruce(Object obj) {
		//TODO hacer el cruce de comportamiento con otro comportamiento
		Comportamiento comportamiento = (Comportamiento) obj;
	}
	public void Mutar() {
		//TODO llamar este metodo luego de la creacion para mutar
	}

}
