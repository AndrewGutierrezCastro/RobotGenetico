package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.Stream;

import javax.swing.JLabel;

import Application.App;

public class Robot implements Genetico {
	private Comportamiento comportamiento;
	private Caracteristicas caracteristicas;
	public Direccion posicion;
	public JLabel lblRobot;
	private ArrayList<Direccion> direcciones;
	Random rand = new Random();
	public Robot() {
		comportamiento = new Comportamiento();
		caracteristicas = new Caracteristicas();
		direcciones = new ArrayList<Direccion>();
		this.posicion = Direccion.ABAJO;
		this.posicion.x = 19;
		this.posicion.y = 0;
	}
	
	private void Comportarse() {
		
		int menosProbable = 100,
			masProbable = 0,
			numeroRandom = rand.nextInt(100);
		int[] arrayComportamientoAcutal= new int[2];
		switch (comportamiento.estado) {
		case AVANZANDO:
			Avanzar();
			arrayComportamientoAcutal = comportamiento.avanzar;
			break;
		case OBSERVANDO:
			Observar();
			arrayComportamientoAcutal = comportamiento.observar;
			break;
		default:
			break;
		}
		//Se obtiene la accion mas probable a tomar por ejemplo un 50%
		masProbable = 100 - Math.max(arrayComportamientoAcutal[0], arrayComportamientoAcutal[1]);
		
		//Se obtiene la accion menos probable a tomar por ejemplo un 10%
		menosProbable = 100 - Math.min(arrayComportamientoAcutal[0], arrayComportamientoAcutal[1]);

		
		/*
		 * Finalmente tendriamos 
		 * masProbable = 50 
		 * menosProbable = 90
		 * 
		 * La pregunta funciona: Si obtuvo una probabilidad de x
		 * x  suficiente para tomar la probabilidad mas remota?
		 * x  				........la probabilidad mas usual?  
		 */
		if(numeroRandom >= menosProbable) {
			//Obtener el siguiente comportamiento segun su estado actual
			comportamiento.getNextComportamiento(menosProbable, comportamiento.avanzar);
		}else {
			comportamiento.getNextComportamiento(masProbable, comportamiento.avanzar);
		}
	}
	
	private void Observar() {
		/*
		 * Este metodo lo que hace es utilizar la camara para observar y determinar la mejor ruta
		 * basandose en la suma del costo de atravesar los bloques.
		 * Entre menos mejor.
		 * */
		Direccion posicionRevisada;
		Bloque[][] terreno = Simulacion.getInstance().getTerreno().terreno;
		boolean puedeInteractuar;
		int costeEnergeticoBloque,
		distanciaVisualizacion = (int) this.caracteristicas.Camara.costo() / 1000;//Cantidad de distancia que puede ver el robot
		
		HashMap<Integer, ArrayList<Direccion>> direccionesMap //Un HashMap para guardar las direcciones que ve la camara 
				= new HashMap<Integer, ArrayList<Direccion>>();
		HashMap<Integer, Integer> costesDireccionesMap 		//Otro HasMap para guardar el coste de pasar por las direcciones
				= new HashMap<Integer, Integer>();			//que tiene el otro hash y su llave del otro HashMap
		HashMap<Integer, Integer> tamannosMap 				//
				= new HashMap<Integer, Integer>();
		for (int i = 0; i < 4 ; i++) { // 4 Direcciones posibles
			posicionRevisada = this.posicion.Copy();
			direccionesMap.put(i, new ArrayList<Direccion>());
			costeEnergeticoBloque = 0;
			for (int j = 0; j < distanciaVisualizacion; j++) {//Cantidad de distancia que puede ver el robot
					
				posicionRevisada = posicionRevisada.Mover(i);
				puedeInteractuar = RevisarDesplazamiento(posicionRevisada, terreno, this.caracteristicas.Motor);
					//Revisar si el motor es igual o mayor al coste de pasar por el bloque
				if (puedeInteractuar){
					direccionesMap.get(i).add(posicionRevisada.Copy()); //Agregar la direccion que se puede recorrer
					costeEnergeticoBloque += terreno[posicionRevisada.x][posicionRevisada.y].consumo;
				}else {
					break; //Si en la direccion que mira encuentra el camino bloqueado no seguir revisando
				}
			}
			tamannosMap.put(i, direccionesMap.get(i).size());//Guardar la cantidad de direcciones posibles de recorrer
			costesDireccionesMap.put(i, costeEnergeticoBloque);//Guardar los costes de la lista de direcciones
		}
		
		//Revisar cual es el camino con mayor distancia y menor coste 
		int costeMinimo = Integer.MAX_VALUE,
			tamannoMaximo = Integer.MIN_VALUE,
			indiceListaOptima = 0;
		//Revisar en todas las direcciones disponibles 
		for (int i = 0; i < direccionesMap.size(); i++) {		
			if(tamannosMap.get(i) > tamannoMaximo && costesDireccionesMap.get(i) < costeMinimo) {
				tamannoMaximo = tamannosMap.get(i);
				costeMinimo = costesDireccionesMap.get(i);
				indiceListaOptima = i;
			}
		}
		this.direcciones = direccionesMap.get(indiceListaOptima);
	}

	private void Avanzar() {
		/*AVANZAR:
		 * 2 CASOS POSIBLES 
		 * Tener direcciones en la lista de direcciones:
		 * 			Solamente toma la cantidad de posiciones que determina su camara y
		 * 			las recorre  
		 * No tener direcciones en la lista de direcciones:
		 * 			Toma una direccion que cumpla de primero en orden de direccion en cruz 
		 * */
		if(direcciones.size() > 0) {
			this.posicion = direcciones.remove(0);
		}else {
			Bloque[][] terreno = Simulacion.getInstance().getTerreno().terreno;
			for(int i = 3; i <= 0; i--){// Ciclo que busca una direccion que cumpla para moverse
				Direccion posicionRevisada = this.posicion.Mover(i);
				if(RevisarDesplazamiento(posicionRevisada, terreno, this.caracteristicas.Motor)) {
					this.posicion = posicionRevisada;
					break; //Salir si encuentra una posicion que no se salga del rango del terreno
						  // y la misma culpa la interaccion.
				}
			}
		}
			
	}
	private boolean RevisarDesplazamiento(Direccion pPosicion, Bloque[][] terreno, Hardware hardware){
		/*Este metodo revisa el desplazamiento de una direccion con x & y no salga del terreno de tamanno definido 
		 * por el tamanno del terreno.
		 * Retorna la true si la direccion del tipo de bloque deja interactuar el hardware enviado como parametro 
		 * Por ejemplo un motor(PENSADO ASI ORIGINALMENTE)
		 * Puede usarse para cualquier otro componenete con funcion distinta
		 * Hardware
		 * BASICO		NORMAL
		 * MEDIO		MODERADO, NORMAL
		 * AVANZADO		DIFICIL, MODERADO, NORMAL
		 * BLOQUEADO	null
		 * */
		Hardware resultado = Hardware.AVANZADO;
		int indiceMinimo = 0,
			indiceMaximo = terreno.length;//Se supone una matriz cuadrada
		if(pPosicion.x >= indiceMaximo || pPosicion.y >= indiceMaximo
				|| pPosicion.x < indiceMinimo || pPosicion.y < indiceMinimo) {
				return false; //Si se sale del terreno no seguir revisando el camino
			}
			//Para hacer la asociacion de tipo de hardware a tipo de bloque se debe revisar
			//y cambiar por su equivalencia
			Bloque bloqueActual = terreno[pPosicion.x][pPosicion.y];
			switch (bloqueActual) {
			case NORMAL:
				resultado = Hardware.BASICO;
				break;
			case MODERADO:
				resultado = Hardware.MEDIO;
				break;
			case DIFICIL:
				resultado = Hardware.AVANZADO;	
				break;
			case BLOQUEADO:
				return false;
			}
			if (resultado != null && resultado.energia() <= hardware.energia()){
				return true;
			}
		return false;
	}

	@Override	
	public void Definir() {
		caracteristicas.Definir();
		comportamiento.Definir();
		lblRobot = new JLabel();
	}
	
	@Override
	public void Cruce(Object obj) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void Mutar() {
		Stream.of(comportamiento,
				  caracteristicas)
				.forEach((R)-> R.Mutar());
		
	}
	
}
