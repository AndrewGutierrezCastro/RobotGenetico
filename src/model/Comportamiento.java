package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Comportamiento extends Genetico {
	public int[] avanzar; // 0: asi mismo,1:observar
	public int[] observar;// 0: asi mismo,1:esperar
	public Estado estado;
	
	public Comportamiento() {
		avanzar = new int[2];
		observar = new int[2];
		estado = Estado.OBSERVANDO;
		Definir();
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
		
		avanzar[0] = rand.nextInt(100);// 0 - 100 = 45
		observar[0] = rand.nextInt(100);
		
		avanzar[1] = 100 - avanzar[0];// 100 - 45 = 55
		observar[1] = 100 - observar[0];
		
	}
	
	public void verificarProba() {
		if(avanzar[0]<=0){
			avanzar[0]=10;
			avanzar[1]=90;}
		if(avanzar[1]<=0){
			avanzar[1]=10;
			avanzar[0]=90;}
		if(observar[0]<=0){
			observar[0]=10;
			observar[1]=90;}
		if(observar[1]<=0){
			observar[1]=10;
			observar[0]=90;}
	}

	public void Cruce(Object obj) {
		//TODO hacer el cruce de comportamiento con otro comportamiento
		Comportamiento comportamiento = (Comportamiento) obj;
	}
	
	public void Mutar() {
		//TODO llamar este metodo luego de la creacion para mutar
	}

}
