package model;
import java.util.Random;

import javax.swing.JLabel;

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
		return Cruzar(robotsElegidos);
	}
	
	public Robot[] Cruzar(Robot[] robotsElegidos) {
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
					int [][] comportamientos = new int[6][3];
					comportamientos[0]=robotsElegidos[i].getComportamiento().avanzar;
					comportamientos[1]=robotsElegidos[i+1].getComportamiento().avanzar;
					comportamientos[2]=robotsElegidos[i].getComportamiento().observar;
					comportamientos[3]=robotsElegidos[i+1].getComportamiento().observar;
					comportamientos[4]=robotsElegidos[i].getComportamiento().esperar;
					comportamientos[5]=robotsElegidos[i+1].getComportamiento().esperar;
					for(int n=0; n<comportamientos.length; n=n+2) {
						for(int k=0; k<=(comportamientos[n].length/2)-1; k++) {
							int inter = comportamientos[n][k];
							comportamientos[n][k] = comportamientos[n+1][k];
							comportamientos[n+1][k] = inter; 
						}
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
		return Mutar(robotsElegidos);
	}
	
	public Robot[] Mutar(Robot[] robotsElegidos) {
		Random rand;
		Poblacion pob=Poblacion.getInstance();
		int cantMutar=(int)(pob.SizePoblacion*pob.probabilidadMutacion);
		for(int i=0; i <= cantMutar; i++) {
			rand = new Random();
			rand.nextInt(robotsElegidos.length);
			robotsElegidos[i].Definir();
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