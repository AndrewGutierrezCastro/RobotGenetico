package model;
import java.util.Random;

import javax.swing.JLabel;

import model.Hardware.TiposHardware;

public class Generacion {
	public int numeroGeneracion;
	public Robot[] robots;
	public Robot mejorRobotActual;
	public Posicion objetivo;
	public double distanciaMinima;
	public double aptitudGeneral;
	public Generacion(int size, int pNumeroGeneracion) {
		robots = new Robot[size];
		objetivo = new Posicion(0,19);
		numeroGeneracion = pNumeroGeneracion;
	}
		
	public Generacion(int numeroGeneracion, Robot[] robots) {
		super();
		this.numeroGeneracion = numeroGeneracion;
		this.robots = robots;
	}

	public void GeneracionAleatoria() {
		/*Este metodo crea robots y los define llamando metodos
		 * que setean valores aleatorios.
		 * */
		for (int i = 0; i < robots.length; i++) {
			robots[i] = new Robot();
			robots[i].Definir();
			Random rand = new Random();//Temporal
		}								
		mejorRobotActual = robots[0];
		distanciaMinima = Double.MAX_VALUE;		
	}
	
	public void ComportarRobots() {
		/*Este metodod llama a toda la generacion de robots y los comporta
		 *Guarda al mejor robot de la generacion siendo el mas cercano al objetivo
		 *ademas de la distancia que estuvo del objetivo.
		 * */
		SetLblTerrenoRobots();
		for (Robot robot : robots) {
			robot.Start();
		}		
	}
	
	public void PausaRobots() {
		for (Robot robot : robots) {
			robot.Pause();
		}
		
	}
	
	public void getMejorRobot() {
		/*Este metodo llama a toda la generacion de robots y los comporta
		 *Guarda al mejor robot de la generacion siendo el mas cercano al objetivo
		 *ademas de la distancia que estuvo del objetivo.
		 * */
		for (Robot robot : robots) {
			if(objetivo.distancia(robot.posicion) < distanciaMinima) {
				mejorRobotActual = robot;
				distanciaMinima = objetivo.distancia(robot.posicion);
			}
		}
	}
	
	private void Fitness() {
		
	}
	
	public Robot[] seleccionarElegidos() {

		for(Robot robot : robots) {
			this.aptitudGeneral = this.aptitudGeneral + robot.getValorAptitud(); //for temporal para pruebas
		}
		Robot[] robotsElegidos = new Robot[robots.length]; 
		float[] probabilidades = new float[robots.length+1];
		int numeroDeEleccion;
		probabilidades[0] = 0;
		Random rand;
		for(int i=0; i<robots.length; i++) {
			probabilidades[i+1] = (float) ((robots[i].getValorAptitud() / this.aptitudGeneral) * 100);
			if(i>0) {
				probabilidades[i+1] = probabilidades[i+1] + probabilidades[i];
			}
		}
		for(int j=0; j<robots.length; j++) {
			rand = new Random();
			numeroDeEleccion = rand.nextInt(100);
			for(int k=1; k<probabilidades.length; k++) {
				if(probabilidades[k] >= numeroDeEleccion && probabilidades[k-1] <= numeroDeEleccion ){
					robotsElegidos[j] = robots[j].clone();
					break;
				}
			}
		}
		return CruzarCaracteristicas(robotsElegidos);
	}
	
	public Robot[] CruzarCaracteristicas(Robot[] robotsElegidos) {
		Random rand = new Random();
		int numOmitido;
		int numOmitido2;
		
		for(int i = 0; i < robotsElegidos.length; i = i+2) {
//			System.out.println("********************************"+i);
//			System.out.println(robotsElegidos[i]);
//			System.out.print(robotsElegidos[i].getComportamiento().avanzar[0]);
//			System.out.print(" ");
//			System.out.println(robotsElegidos[i].getComportamiento().avanzar[1]);
//			System.out.print(robotsElegidos[i].getComportamiento().observar[0]);
//			System.out.print(" ");
//			System.out.println(robotsElegidos[i].getComportamiento().observar[1]);
//			System.out.println(robotsElegidos[i+1]);
//			System.out.print(robotsElegidos[i+1].getComportamiento().avanzar[0]);
//			System.out.print(" ");
//			System.out.println(robotsElegidos[i+1].getComportamiento().avanzar[1]);
//			System.out.print(robotsElegidos[i+1].getComportamiento().observar[0]);
//			System.out.print(" ");
//			System.out.println(robotsElegidos[i+1].getComportamiento().observar[1]);
			try {
			numOmitido = rand.nextInt(4);
			numOmitido2 = rand.nextInt(4);
			for(int j=0; j<3; j++) {
				if(numOmitido == j || numOmitido2 == j) {
					switch(j) {
						case 0:
							Hardware bateria = robotsElegidos[i].getCaracteristicas().Bateria;
							robotsElegidos[i].getCaracteristicas().Bateria = robotsElegidos[i+1].getCaracteristicas().Bateria;
							robotsElegidos[i+1].getCaracteristicas().Bateria = bateria;
							break;
						case 1:
							Hardware motor = robotsElegidos[i].getCaracteristicas().Motor;
							robotsElegidos[i].getCaracteristicas().Motor = robotsElegidos[i+1].getCaracteristicas().Motor;
							robotsElegidos[i+1].getCaracteristicas().Motor = motor;
							break;
						case 2:
							Hardware camara = robotsElegidos[i].getCaracteristicas().Camara;
							robotsElegidos[i].getCaracteristicas().Camara = robotsElegidos[i+1].getCaracteristicas().Camara;
							robotsElegidos[i+1].getCaracteristicas().Camara = camara;
							break;
						case 3:
							Hardware generador = robotsElegidos[i].getCaracteristicas().Generador;
							robotsElegidos[i].getCaracteristicas().Generador = robotsElegidos[i+1].getCaracteristicas().Generador;
							robotsElegidos[i+1].getCaracteristicas().Generador = generador;
					}
				}
			}
		}catch (NullPointerException e){
			
			
		}
//			System.out.println("---------------------------------"+i);
//			System.out.println(robotsElegidos[i].toString());
//			System.out.print(robotsElegidos[i].getComportamiento().avanzar[0]);
//			System.out.print(" ");
//			System.out.println(robotsElegidos[i].getComportamiento().avanzar[1]);
//			System.out.print(robotsElegidos[i].getComportamiento().observar[0]);
//			System.out.print(" ");
//			System.out.println(robotsElegidos[i].getComportamiento().observar[1]);
//			System.out.println(robotsElegidos[i+1].toString());
//			System.out.print(robotsElegidos[i+1].getComportamiento().avanzar[0]);
//			System.out.print(" ");
//			System.out.println(robotsElegidos[i+1].getComportamiento().avanzar[1]);
//			System.out.print(robotsElegidos[i+1].getComportamiento().observar[0]);
//			System.out.print(" ");
//			System.out.println(robotsElegidos[i+1].getComportamiento().observar[1]);
		}
		return CruzarComportamiento(robotsElegidos);
	}
	public Robot[]CruzarComportamiento(Robot[] robotsElegidos){
		Random rand = new Random();
		int numOmitido;		
		for(int i = 0; i < robotsElegidos.length; i = i+2) {
//			System.out.println("***************************************************"+i);
//			System.out.println(robotsElegidos[i].toString());
//			System.out.print(robotsElegidos[i].getComportamiento().avanzar[0]);
//			System.out.print(" ");
//			System.out.print(robotsElegidos[i].getComportamiento().avanzar[1]);
//			System.out.print(" ");
//			System.out.println(robotsElegidos[i].getComportamiento().avanzar[2]);
//			System.out.print(robotsElegidos[i].getComportamiento().observar[0]);
//			System.out.print(" ");
//			System.out.print(robotsElegidos[i].getComportamiento().observar[1]);
//			System.out.print(" ");
//			System.out.println(robotsElegidos[i].getComportamiento().observar[2]);
//			System.out.print(robotsElegidos[i].getComportamiento().esperar[0]);
//			System.out.print(" ");
//			System.out.print(robotsElegidos[i].getComportamiento().esperar[1]);
//			System.out.print(" ");
//			System.out.println(robotsElegidos[i].getComportamiento().esperar[2]);
//			System.out.println(robotsElegidos[i+1].toString());
//			System.out.print(robotsElegidos[i+1].getComportamiento().avanzar[0]);
//			System.out.print(" ");
//			System.out.print(robotsElegidos[i+1].getComportamiento().avanzar[1]);
//			System.out.print(" ");
//			System.out.println(robotsElegidos[i+1].getComportamiento().avanzar[2]);
//			System.out.print(robotsElegidos[i+1].getComportamiento().observar[0]);
//			System.out.print(" ");
//			System.out.print(robotsElegidos[i+1].getComportamiento().observar[1]);
//			System.out.print(" ");
//			System.out.println(robotsElegidos[i+1].getComportamiento().observar[2]);
//			System.out.print(robotsElegidos[i+1].getComportamiento().esperar[0]);
//			System.out.print(" ");
//			System.out.print(robotsElegidos[i+1].getComportamiento().esperar[1]);
//			System.out.print(" ");
//			System.out.println(robotsElegidos[i+1].getComportamiento().esperar[2]);
			try {
			numOmitido = rand.nextInt(3);
			for(int j=0; j<3; j++) {
				if(numOmitido == j) {
					switch(j) {
						case 0:
							int[]avaTemp = robotsElegidos[i].getComportamiento().avanzar;
							robotsElegidos[i].getComportamiento().avanzar = robotsElegidos[i+1].getComportamiento().avanzar;
							robotsElegidos[i+1].getComportamiento().avanzar = avaTemp;
							break;
						case 1:
							int[]obsTemp = robotsElegidos[i].getComportamiento().observar;
							robotsElegidos[i].getComportamiento().observar = robotsElegidos[i+1].getComportamiento().observar;
							robotsElegidos[i+1].getComportamiento().observar = obsTemp;
							break;
						case 2:
							int[]espTemp = robotsElegidos[i].getComportamiento().esperar;
							robotsElegidos[i].getComportamiento().esperar = robotsElegidos[i+1].getComportamiento().esperar;
							robotsElegidos[i+1].getComportamiento().esperar = espTemp;
							break;
					}
				}
			}
		}catch (NullPointerException e){
			
			
		}
//					System.out.println("---------------------------------"+i);
//					System.out.println(robotsElegidos[i].toString());
//					System.out.print(robotsElegidos[i].getComportamiento().avanzar[0]);
//					System.out.print(" ");
//					System.out.print(robotsElegidos[i].getComportamiento().avanzar[1]);
//					System.out.print(" ");
//					System.out.println(robotsElegidos[i].getComportamiento().avanzar[2]);
//					System.out.print(robotsElegidos[i].getComportamiento().observar[0]);
//					System.out.print(" ");
//					System.out.print(robotsElegidos[i].getComportamiento().observar[1]);
//					System.out.print(" ");
//					System.out.println(robotsElegidos[i].getComportamiento().observar[2]);
//					System.out.print(robotsElegidos[i].getComportamiento().esperar[0]);
//					System.out.print(" ");
//					System.out.print(robotsElegidos[i].getComportamiento().esperar[1]);
//					System.out.print(" ");
//					System.out.println(robotsElegidos[i].getComportamiento().esperar[2]);
//					System.out.println(robotsElegidos[i+1].toString());
//					System.out.print(robotsElegidos[i+1].getComportamiento().avanzar[0]);
//					System.out.print(" ");
//					System.out.print(robotsElegidos[i+1].getComportamiento().avanzar[1]);
//					System.out.print(" ");
//					System.out.println(robotsElegidos[i+1].getComportamiento().avanzar[2]);
//					System.out.print(robotsElegidos[i+1].getComportamiento().observar[0]);
//					System.out.print(" ");
//					System.out.print(robotsElegidos[i+1].getComportamiento().observar[1]);
//					System.out.print(" ");
//					System.out.println(robotsElegidos[i+1].getComportamiento().observar[2]);
//					System.out.print(robotsElegidos[i+1].getComportamiento().esperar[0]);
//					System.out.print(" ");
//					System.out.print(robotsElegidos[i+1].getComportamiento().esperar[1]);
//					System.out.print(" ");
//					System.out.println(robotsElegidos[i+1].getComportamiento().esperar[2]);
			
		}
		return Mutar(robotsElegidos);
	}
	
	
	public Robot[] Mutar(Robot[] robotsElegidos) {
		Random rand;
		boolean eleccGen;
		int comp;
		int numMut;
		int prob1;
		int prob2;
		int prob3;
		int eleccHard;
		int eleccCal;
		TiposHardware[] listaHardware = Hardware.TiposHardware.values();
		Hardware reemplazo = robotsElegidos[0].getCaracteristicas().Bateria;
		Poblacion pob=Poblacion.getInstance();
		double cantMutar=(pob.SizePoblacion*pob.getProbabilidadMutacion());
		System.out.println(cantMutar);
		for(int i=0; i <= cantMutar; i++) {
			rand = new Random();
			eleccGen=rand.nextBoolean();
			System.out.println(eleccGen);
			rand = new Random();
			numMut = rand.nextInt(robotsElegidos.length);
			System.out.println("*********************************");
			System.out.println(robotsElegidos[numMut].toString());
			System.out.print(robotsElegidos[numMut].getComportamiento().avanzar[0]);
			System.out.print(" ");
			System.out.print(robotsElegidos[numMut].getComportamiento().avanzar[1]);
			System.out.print(" ");
			System.out.println(robotsElegidos[numMut].getComportamiento().avanzar[2]);
			System.out.print(robotsElegidos[numMut].getComportamiento().observar[0]);
			System.out.print(" ");
			System.out.print(robotsElegidos[numMut].getComportamiento().observar[1]);
			System.out.print(" ");
			System.out.println(robotsElegidos[numMut].getComportamiento().observar[2]);
			System.out.print(robotsElegidos[numMut].getComportamiento().esperar[0]);
			System.out.print(" ");
			System.out.print(robotsElegidos[numMut].getComportamiento().esperar[1]);
			System.out.print(" ");
			System.out.println(robotsElegidos[numMut].getComportamiento().esperar[2]);
			if(eleccGen) {
				rand = new Random();
				prob1 = rand.nextInt(100);
				prob2 = rand.nextInt(100-prob1);
				prob3 = 100-(prob1+prob2);
				rand = new Random();
				comp=rand.nextInt(3);
				switch(comp) {
					case 0:
						robotsElegidos[numMut].getComportamiento().avanzar[0] = prob1;
						robotsElegidos[numMut].getComportamiento().avanzar[1] = prob2;
						robotsElegidos[numMut].getComportamiento().avanzar[2] = prob3;
						break;
					case 1:
						robotsElegidos[numMut].getComportamiento().observar[0] = prob1;
						robotsElegidos[numMut].getComportamiento().observar[1] = prob2;
						robotsElegidos[numMut].getComportamiento().observar[2] = prob3;
						break;
					case 2:
						robotsElegidos[numMut].getComportamiento().esperar[0] = prob1;
						robotsElegidos[numMut].getComportamiento().esperar[1] = prob2;
						robotsElegidos[numMut].getComportamiento().esperar[2] = prob3;
						break;
				}
			}
			else {
				rand = new Random();
				eleccHard = rand.nextInt(4);
				switch(eleccHard) {
					case 0:
						rand = new Random();
						eleccCal = rand.nextInt(3);
						robotsElegidos[numMut].getCaracteristicas().Bateria = new Hardware(listaHardware[eleccCal]);
						break;
					case 1:
						rand = new Random();
						eleccCal = rand.nextInt(3);
						robotsElegidos[numMut].getCaracteristicas().Camara = new Hardware(listaHardware[eleccCal]);
						break;
					case 2:
						rand = new Random();
						eleccCal = rand.nextInt(3);
						robotsElegidos[numMut].getCaracteristicas().Motor = new Hardware(listaHardware[eleccCal]);
						break;
					case 3:
						rand = new Random();
						eleccCal = rand.nextInt(3);
						robotsElegidos[numMut].getCaracteristicas().Generador = new Hardware(listaHardware[eleccCal]);
						break;
				}


	
			}
			System.out.println("---------------------------------");
			System.out.println(robotsElegidos[numMut].toString());
			System.out.print(robotsElegidos[numMut].getComportamiento().avanzar[0]);
			System.out.print(" ");
			System.out.print(robotsElegidos[numMut].getComportamiento().avanzar[1]);
			System.out.print(" ");
			System.out.println(robotsElegidos[numMut].getComportamiento().avanzar[2]);
			System.out.print(robotsElegidos[numMut].getComportamiento().observar[0]);
			System.out.print(" ");
			System.out.print(robotsElegidos[numMut].getComportamiento().observar[1]);
			System.out.print(" ");
			System.out.println(robotsElegidos[numMut].getComportamiento().observar[2]);
			System.out.print(robotsElegidos[numMut].getComportamiento().esperar[0]);
			System.out.print(" ");
			System.out.print(robotsElegidos[numMut].getComportamiento().esperar[1]);
			System.out.print(" ");
			System.out.println(robotsElegidos[numMut].getComportamiento().esperar[2]);
		}
		return robotsElegidos;
	}
	
	@Override
	public String toString() {
		return "Generacion: "+ (numeroGeneracion+1);
	}

	public void SetLblTerrenoRobots() {
		JLabel[][] lblTerreno = Poblacion.getInstance().getLblTerreno();
		for (Robot robot : robots) {
			robot.setTerreno(lblTerreno);
		}
	}
	
	public int getNumeroGeneracion() {
		return numeroGeneracion;
	}
	
	public void setNumeroGeneracion(int numeroGeneracion) {
		this.numeroGeneracion = numeroGeneracion;
	}
	
}