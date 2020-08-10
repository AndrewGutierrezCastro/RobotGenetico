package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Comportamiento extends Genetico implements Cloneable{
	public int[] avanzar; // 0: AVANZAR, 1:OBSERVAR, 2:ESPERAR
	public int[] observar;// 0: AVANZAR, 1:OBSERVAR, 2:ESPERAR
	public int[] esperar;
	public Estado estado;
	
	public Comportamiento() {
		avanzar = new int[3];
		observar = new int[3];
		esperar = new int[3];
		estado = Estado.AVANZANDO;
		Definir();
	}
	
	public void getNextComportamiento(int valor, int[] comportamientoActual) {
		int indiceActual = 2;
		/*
		 * ArrayList<Estado> estadoAvanzando = new ArrayList<Estado>( Arrays.asList(
		 * Estado.OBSERVANDO, Estado.ESPERANDO,Estado.AVANZANDO));
		 */
		for(int i = 0; i < comportamientoActual.length; i++) {
			 if(comportamientoActual[i] == valor) {
				 indiceActual = i;
				 break;
			 }
		}
		this.estado =
			Arrays.asList(
					Estado.AVANZANDO,
				Estado.OBSERVANDO,
				Estado.ESPERANDO).get(indiceActual);
	}
	
	public void Definir() {
		Boolean cadenaCorrecta = false;
		ArrayList<int[]> comportamientos = new ArrayList<int[]>(Arrays.asList(avanzar, observar, esperar));
		while(true) {
			for (int[] comportamiento : comportamientos) {
				Random rand = new Random();
				comportamiento[0] = rand.nextInt(100);
				int numComportamiento = 100 - comportamiento[0];
				comportamiento[1] = rand.nextInt(numComportamiento);
				comportamiento[2] = 100 - (comportamiento[0] + comportamiento[1]);
				if( Arrays.stream(comportamiento).sum() != 100) {
					break;
				}
			}
			break;
			
		}
		
	}
	
	public Comportamiento clone() {
		Comportamiento comportamientoClonado = new Comportamiento();
		comportamientoClonado.avanzar = this.avanzar.clone();
		comportamientoClonado.observar = this.observar.clone();
		comportamientoClonado.esperar = this.observar.clone();
		return comportamientoClonado;
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
