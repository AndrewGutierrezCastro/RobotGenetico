package model;
import java.util.Random;

import javax.swing.JLabel;

public class Generacion {
	public Robot[] robots;
	public Robot mejorRobotActual;
	public Posicion objetivo;
	public double distanciaMinima;
	public int aptitudGeneral;
	public Generacion(int size) {
		robots = new Robot[size];
		objetivo = new Posicion(0,19);
	}
	public void GeneracionAleatoria() {
		/*Este metodo crea robots y los define llamando metodos
		 * que setean valores aleatorios.
		 * */
		for (int i = 0; i < robots.length; i++) {
			robots[i] = new Robot();
			robots[i].Definir();
			Random rand = new Random();//Temporal
			robots[i].setValorAptitud(rand.nextInt(50)); //valor de aptitud random temporal para pruebas
		}								
		mejorRobotActual = robots[0];
		distanciaMinima = Double.MAX_VALUE;
		seleccionarElegidos();		
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
		/*Este metodod llama a toda la generacion de robots y los comporta
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
	
	public void seleccionarElegidos() {

		for(Robot robot : robots) {
			this.aptitudGeneral = this.aptitudGeneral + robot.getValorAptitud(); //for temporal para pruebas
		}
		Robot[] robotsElegidos = new Robot[robots.length]; 
		float[] probabilidades = new float[robots.length+1];
		int numeroDeEleccion;
		probabilidades[0] = 0;
		Random rand = new Random();
		for(int i=0; i<robots.length; i++) {
			probabilidades[i+1] = ((float)robots[i].getValorAptitud() / this.aptitudGeneral) * 100;
			if(i>0) {
				probabilidades[i+1] = probabilidades[i+1] + probabilidades[i];
			}
		}
		for(int j=0; j<robots.length; j++) {
			numeroDeEleccion = rand.nextInt(100);
			for(int k=1; k<robots.length; k++) {
				if(probabilidades[k] >= numeroDeEleccion && probabilidades[k-1] <= numeroDeEleccion ){
					robotsElegidos[j] = robots[k].clone();
					break;
				}
			}
		}
		Cruzar(robotsElegidos);
	}
	
	public void Cruzar(Robot[] robotsElegidos) {
		Random rand = new Random();
		int numOmitido;
		for(int i = 0; i < robotsElegidos.length; i = i+2) {
			System.out.println("********************************"+i);
			System.out.println(robotsElegidos[i]);

			System.out.println(robotsElegidos[i+1]);

			numOmitido = rand.nextInt(3);
			for(int j=0; j<3; j++) {
				if(numOmitido != j) {
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
					}
				}
			}
		}
	}

	public void SetLblTerrenoRobots() {
		JLabel[][] lblTerreno = Poblacion.getInstance().getLblTerreno();
		for (Robot robot : robots) {
			robot.setTerreno(lblTerreno);
		}
	}
	
}