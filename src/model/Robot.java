package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.Stream;
import javax.swing.JLabel;

public class Robot implements Genetico {
	public Comportamiento comportamiento;//Estas son las dos variables a considerar para el 
	public Caracteristicas caracteristicas;//algoritmo genetico
	public int valorAptitud; //basado de 0 a 100
	
	public Posicion posicion;
	public JLabel lblRobot;
	private ArrayList<Posicion> direcciones;
	Random rand = new Random();
	
	public Robot() {
		comportamiento = new Comportamiento();
		caracteristicas = new Caracteristicas();
		direcciones = new ArrayList<Posicion>();
		posicion = new Posicion();
		valorAptitud = 0;
	}
	
	public void Comportarse() {
		if(isAlive()) {
			int menosProbable = 100,
				masProbable = 0,
				numeroRandom = rand.nextInt(100);
			int[] arrayComportamientoActual = new int[2];
			switch (comportamiento.estado) {
			case AVANZANDO:
				Avanzar();
				arrayComportamientoActual = comportamiento.avanzar;
				break;
			case OBSERVANDO:
				Observar();
				arrayComportamientoActual = comportamiento.observar;
				break;
			default:
				break;
			}
			
			//Se obtiene la accion mas probable a tomar por ejemplo un 50%
			masProbable = 100 - Math.max(arrayComportamientoActual[0], arrayComportamientoActual[1]);
			
			//Se obtiene la accion menos probable a tomar por ejemplo un 10%
			menosProbable = 100 - Math.min(arrayComportamientoActual[0], arrayComportamientoActual[1]);
	
			
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
				comportamiento.getNextComportamiento(100 - menosProbable, arrayComportamientoActual);
			}else {
				comportamiento.getNextComportamiento(100 - masProbable, arrayComportamientoActual);
			}
		}
	}

	private void Observar() {
		/*
		 * Este metodo lo que hace es utilizar la camara para observar y determinar la mejor ruta
		 * basandose en la suma del costo de atravesar los bloques.
		 * Entre menos mejor.
		 * */
		Posicion posicionRevisada;
		Bloque[][] terreno = Simulacion.getInstance().getTerreno().terreno;
		boolean puedeInteractuar;
		int costeEnergeticoBloque,
		distanciaVisualizacion = (int) this.caracteristicas.Camara.getEnergia() / 1000;//Cantidad de distancia que puede ver el robot
		
		HashMap<Integer, ArrayList<Posicion>> direccionesMap //Un HashMap para guardar las direcciones que ve la camara 
				= new HashMap<Integer, ArrayList<Posicion>>();
		HashMap<Integer, Integer> costesDireccionesMap 		//Otro HasMap para guardar el coste de pasar por las direcciones
				= new HashMap<Integer, Integer>();			//que tiene el otro hash y su llave del otro HashMap
		HashMap<Integer, Integer> tamannosMap 				
				= new HashMap<Integer, Integer>();
		for (int i = 0; i < 4 ; i++) { // 4 Direcciones posibles
			posicionRevisada = this.posicion.Copy();
			direccionesMap.put(i, new ArrayList<Posicion>());
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
			if(costesDireccionesMap.get(i) < costeMinimo & tamannosMap.get(i) > tamannoMaximo) {
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
		consumirEnergia();
		if(direcciones.size() > 0) {
			setPosicion(direcciones.remove(0));
		}else {
			Bloque[][] terreno = Simulacion.getInstance().getTerreno().terreno;
			for(int i = 0; i < 10; i++){// Ciclo que busca una direccion que cumpla para moverse
				int numeroRandom = rand.nextInt(4) ;
				Posicion posicionRevisada = this.posicion.Mover( numeroRandom );
				if(RevisarDesplazamiento(posicionRevisada, terreno, this.caracteristicas.Motor)) {
					setPosicion(posicionRevisada);
					break; //Salir si encuentra una posicion que no se salga del rango del terreno
						  // y la misma culpa la interaccion.
				}
			}
		}			
	}

	private void consumirEnergia() {
		/*CONSUMIR ENERGIA
		 * Este metodo consume energia segun el bloque donde esta actualmente el robot
		 * por definicion el consumo de energia se hace al salir del bloque
		 * */
		int costeEnergico = 0;
		switch (this.comportamiento.estado) {
		case AVANZANDO:
			costeEnergico = Simulacion.getInstance().getTerreno().terreno[posicion.x][posicion.y].consumo;
			break;
		case OBSERVANDO:
			costeEnergico =(int) (this.caracteristicas.Camara.getEnergia() - 1000) / 40;
			break;
		default:
			break;
		}	
		caracteristicas.Bateria.setEnergia(caracteristicas.Bateria.getEnergia() - costeEnergico);
	}

	private boolean RevisarDesplazamiento(Posicion pPosicion, Bloque[][] terreno, Hardware hardware){
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
		Hardware.TiposHardware resultado = Hardware.TiposHardware.BASICO;
		int indiceMinimo = 0,
			indiceMaximo = terreno.length;//Se supone una matriz cuadrada
		if(pPosicion.x >= indiceMaximo | pPosicion.y >= indiceMaximo
				| pPosicion.x < indiceMinimo | pPosicion.y < indiceMinimo) {
				return false; //Si se sale del terreno no seguir revisando el camino
			}
			//Para hacer la asociacion de tipo de hardware a tipo de bloque se debe revisar
			//y cambiar por su equivalencia
			Bloque bloqueActual = terreno[pPosicion.x][pPosicion.y];
			switch (bloqueActual) {
			case NORMAL:
				resultado = Hardware.TiposHardware.BASICO;
				break;
			case MODERADO:
				resultado = Hardware.TiposHardware.MEDIO;
				break;
			case DIFICIL:
				resultado = Hardware.TiposHardware.AVANZADO;	
				break;
			case BLOQUEADO:
				return false;
			}
			return resultado.getEnergia() <= hardware.getEnergia();
	}
	
	private void setPosicion(Posicion pDireccion) {
		this.posicion = pDireccion;
	}
	
	public Posicion getPosicion() {
		return posicion;
	}
	
	private boolean isAlive() {
		/*Determina si el Robot puede continuar su funcionamiento
		 * Vivo o muerto
		 * */
		return this.caracteristicas.Bateria.getEnergia() > 0;
		
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

	
	@Override
	public String toString() {
		String toString = "C:"+caracteristicas.Camara.getName()+" M:"+caracteristicas.Motor.getName()+"\n";
		toString += "x:"+this.posicion.x +" y:"+this.posicion.y+" Alive:"+isAlive();
		return toString;
	}
	
	
}
